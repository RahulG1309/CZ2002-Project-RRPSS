import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

public class TableReservationsCollection {
	private int NUMTABLES = 10;
	private TableReservations[] Tables = new TableReservations[NUMTABLES];
	private static int EXPIRY = 15;
	
	/**
	 * Checks tables for capacity and whether it is available at the reservation
	 * time.
	 * 
	 * @param pax         number of people the reservation is made for
	 * @param arrivalTime for which the reservation is made for
	 * @return table number of a table that is available to be reserved
	 */
	public int checkTableCapacity(int pax, Calendar arrivalTime) {
		for (int i = 0; i < NUMTABLES; i++) {
			if (Tables[i].getCapacity() == pax) {
				if (Tables[i].checkAvailability(arrivalTime)) {
					return i;
				}
			}
		} // Searching for exact capacity matches
		for (int i = 0; i < NUMTABLES; i++) {
			if (Tables[i].getCapacity() > pax) {
				if (Tables[i].checkAvailability(arrivalTime)) {
					return i;
				}
			}
		} // Searching for larger capacity matches
		return -1;
	}
	
	/**
	 * Checks the availability of the tables at a given time.
	 * @param time of reservation
	 */
	public void checkTableAvailability(Calendar time) {
		for (int i = 0; i < Tables.length; i++) {
			Tables[i].printTableDetails();
			if (Tables[i].checkAvailability(time)) {
				System.out.println("Status: Available");
			} else {
				System.out.println("Status: Not Available");
			}
		}
	}
	
	/**
	 * Finds reservation by contact number of the customer
	 * 
	 * @param contactNo of the customer who the reservation is made for
	 * @return reservation that has been made
	 */
	public Reservation findReservation(long contactNo) {
		Reservation temp = new Reservation();
		for (int i = 0; i < Tables.length; i++) {
			temp = Tables[i].findReservation(contactNo);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Checks at which table the reservation has been made.
	 * 
	 * @param res reservation that is to be checked for
	 * @return table number of the table that contains the reservation
	 */
	public int checkReservation(Reservation res) {
		for (int i = 0; i < Tables.length; i++) {
			if (Tables[i].checkReservation(res)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Removes a reservation from the record of reservations and the file.
	 * 
	 * @param res reservation that is to be removed from the records
	 */
	public void removeReservation(Reservation res) {
		int tableIndex = checkReservation(res);
		if (tableIndex != -1) {
			Tables[tableIndex].removeReservation(res);
			ReservationsFile.writeAllReservations(Tables, NUMTABLES);
			ReservationsFile.readAllReservations(Tables, NUMTABLES);
		} else {
			System.out.println("Reservation not found!");
		}
	}
	
	/**
	 * checks whether there are any tables that have a reservation
	 * that at least EXPIRY minutes have passed since the time where its made for
	 * and the table is still unoccupied
	 */
	public void checkExpiry() {
		for (int i = 0; i < Tables.length; i++) {
			if(Tables[i] == null || Tables[i].getPq() == null || Tables[i].getPq().isEmpty())
			{
				continue;
			}
			Reservation res = Tables[i].peekReservation();
			
			Calendar cal = Calendar.getInstance();
			Date currentTime = cal.getTime();
			Calendar expiryTime = (Calendar) (res.getTimestamp()).clone();
			expiryTime.add(Calendar.MINUTE, EXPIRY);

			if (expiryTime.before(currentTime)) {
				removeReservation(res);
			}
		}
	}
	
	/**
	 * Marks the reserved table as occupied when a customer walks in.
	 * 
	 * @param res reservation of the customer
	 */
	public void checkIn(Reservation res) {
		for (int i = 0; i < Tables.length; i++) {
			if (Tables[i].checkReservation(res)) {
				Tables[i].occupyTable();
				ReservationsFile.writeAllReservations(Tables, NUMTABLES);
				ReservationsFile.readAllReservations(Tables, NUMTABLES);
			}
		}
	}

	/**
	 * Marks the table that has been called as unoccupied.
	 * 
	 * @param tableno of the table that is to be marked empty
	 */
	public void emptyTable(int tableno) {
		checkExpiry();
		for (int i = 0; i < Tables.length; i++) {
			if (Tables[i].getTableNo() == tableno) {
				Tables[i].freeTable();
				ReservationsFile.writeAllReservations(Tables, NUMTABLES);
				ReservationsFile.readAllReservations(Tables, NUMTABLES);
				return;
			}

		}
		System.out.println("Error: Table Not Found");

	}

	/**
	 * Gets the customer currently at a table.
	 * 
	 * @param tableno of the table whose customer is required
	 * @return customer at given table
	 */
	public Customer getCustomer(int tableno) {
		for (int i = 0; i < Tables.length; i++) {
			if (Tables[i].getTableNo() == tableno) {
				return Tables[i].pq.peek().getCustomer();
			}
		}
		System.out.println("Error: Table Not Found");
		return null;

	}

	/**
	 * Initiates the tables
	 */
	public void intiateTables() {
		PriorityQueue<Reservation> pq = new PriorityQueue<>();
		Tables = new TableReservations[NUMTABLES];
		int[] multiplier = { 2, 2, 4, 4, 6, 6, 8, 8, 10, 10 };
		for (int i = 0; i < NUMTABLES; i++) {
			Tables[i] = new TableReservations(i, multiplier[i], true, pq);
		}
	}

	/**
	 * Prints table details
	 */
	public void tabledets() {
		for (TableReservations temp : Tables) {
			temp.printTableDetails();
		}
	}
	
	/**
	 * Adds Reservation res to the PriorityQueue of table Reservations at table with index index
	 * @param res
	 * @param index
	 */
	public void makeReservation(Reservation res, int index) {
		Tables[index].makeReservation(res);
		ReservationsFile.writeAllReservations(Tables, NUMTABLES);
		ReservationsFile.readAllReservations(Tables, NUMTABLES);
	}

	/**
	 * Reads all the Reservations from the file and stores them in an object of type
	 * TableReservations for each table
	 */
	public void readAllReservations() {
		ReservationsFile.readAllReservations(Tables, NUMTABLES);
	}

	/**
	 * Writes all the reservations from each table into the file.
	 */
	public void writeAllReservations() {
		ReservationsFile.writeAllReservations(Tables, NUMTABLES);
	}
}
