import java.util.Calendar;

/**
 * A manager class that handles the Reservation Entities, reads and writes
 * reservations to file that stores the employees, checks table availabilities
 * and capacity, checks, removes, and creates reservations, checks expiry of
 * reservations and other related functions.
 * 
 * @author Neha
 *
 */
public class ReservationMgr {
	private static TableReservationsCollection T = new TableReservationsCollection();

	public ReservationMgr() {
		super();
	}

	/**
	 * Checks the availability of the tables at a given time.
	 * 
	 * @param time of reservation
	 */
	public static void checkTableAvailability(Calendar time) {
		T.checkExpiry(); // Removes all expired reservations
		T.checkTableAvailability(time);
	}

	/**
	 * Creates a reservation for the customer and assigns a table at a specified
	 * time and number of people.
	 * 
	 * @param name        of the customer who the reservation is made for
	 * @param contactNo   of the customer who the reservation is made for
	 * @param pax         of the group of customer who the reservation is made for
	 * @param arrivalTime time when the reservation starts
	 */
	public static void createReservation(String name, long contactNo, int pax, Calendar arrivalTime) {
		T.checkExpiry(); // Removes all expired reservations
		int tableIndex = T.checkTableCapacity(pax, arrivalTime);
		if (tableIndex != -1) {
			Customer customer = CustomerMgr.findCustomer(contactNo);
			if (customer == null) {
				customer = CustomerMgr.createCustomer(name, contactNo, false);
			}
			Reservation res = new Reservation(pax, customer, arrivalTime);
			T.makeReservation(res, tableIndex);
			System.out.println("Success! Your reservation  has been created successfully.");
			res.printReservation();
		} else {
			System.out.println("Unfortunately there are no tables availale at that time.");
		}
	}

	/**
	 * Finds reservation by contact number of the customer
	 * 
	 * @param contactNo of the customer who the reservation is made for
	 * @return reservation that has been made
	 */
	public static Reservation findReservation(long contactNo) {
		T.checkExpiry(); // Removes all expired reservations
		return T.findReservation(contactNo);
	}

	/**
	 * Checks at which table the reservation has been made.
	 * 
	 * @param res reservation that is to be checked for
	 * @return table number of the table that contains the reservation
	 */
	public static int checkReservation(Reservation res) {
		T.checkExpiry(); // Removes all expired reservations
		return T.checkReservation(res);
	}

	/**
	 * Marks the reserved table as occupied when a customer walks in.
	 * 
	 * @param res reservation of the customer
	 */
	public static void checkIn(Reservation res) {
		T.checkExpiry(); // Removes all expired reservations
		T.checkIn(res);
	}

	/**
	 * Marks the table that has been called as unoccupied.
	 * 
	 * @param tableno of the table that is to be marked empty
	 */
	public static void emptyTable(int tableno) {
		T.checkExpiry(); // Removes all expired reservations
		T.emptyTable(tableno);
	}

	/**
	 * Gets the customer currently at a table.
	 * 
	 * @param tableno of the table whose customer is required
	 * @return customer at given table
	 */
	public static Customer getCustomer(int tableno) {
		T.checkExpiry(); // Removes all expired reservations
		return T.getCustomer(tableno);
	}

	/**
	 * Initiates the tables
	 */
	public static void intiateTables() {
		T.intiateTables();
	}

	/**
	 * Prints table details
	 */
	public static void tabledets() {
		T.checkExpiry(); // Removes all expired reservations
		T.tabledets();
	}

	/**
	 * Reads all the Reservations from the file
	 */
	public static void readAllReservations() {
		T.readAllReservations();
	}

	public static void writeAllReservations() {
		T.writeAllReservations();
	}

	/**
	 * Removes a reservation from the record of reservations and the file.
	 * 
	 * @param res reservation that is to be removed from the records
	 */
	public static void removeReservation(Reservation res) {
		T.removeReservation(res);
	}
}
