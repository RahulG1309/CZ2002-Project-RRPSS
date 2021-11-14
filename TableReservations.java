import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>
 * An Object of TableReservations represents a Table with the Unique Table
 * Identifier, Capacity, empty state identifier and List of Reservations
 * associated with that Table
 * </p>
 * <p>
 * The class is Serializable. It utilizes the Reservation Class for creating the
 * Reservation List
 * </p>
 * 
 * @author Dhruv
 *
 */
public class TableReservations implements Serializable {

	/**
	 * A UID to allow serialization of Objects of this Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Maximum number of allowed reservations for a table
	 */
	private static int MAXLEN = 30;

	/**
	 * Minimum Milliseconds allowed between two reservations
	 */
	private static final long BETWEENRES = 120 * 60 * 1000;

	/**
	 * Holds the table number of the Table
	 */
	private int tableNo;

	/**
	 * Holds the capacity of the Table
	 */
	private int capacity;

	/**
	 * Holds whether the table is occupied or empty
	 */
	private boolean empty;

	/**
	 * A priority queue that contains the reservations made for this Table, sorted
	 * in chronological order
	 */
	PriorityQueue<Reservation> pq = new PriorityQueue<Reservation>();

	/**
	 * Constructs an empty TableReservations object
	 */
	public TableReservations() {
		super();
	}

	/**
	 * Constructs a TableReservations Object with the tableNo, Capacity, empty state
	 * identifier and Priority Queue of Scheduled Reservations
	 * 
	 * @param tableNo
	 * @param capacity
	 * @param empty
	 * @param pq
	 */
	public TableReservations(int tableNo, int capacity, boolean empty, PriorityQueue<Reservation> pq) {
		super();
		this.tableNo = tableNo;
		this.capacity = capacity;
		this.empty = empty;
		this.pq = pq;
	}

	/**
	 * Getter for the Unique Identification Number of the table
	 * 
	 * @return int tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Getter for the Capacity of the table
	 * 
	 * @return int capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Getter for the empty state identifier of the table
	 * 
	 * @return boolean empty
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * Sets the value of the empty field identifier of the table to true
	 */
	public void occupyTable() {
		this.empty = false;
	}

	/**
	 * Sets the value of the empty field identifier of the table to false
	 */
	public void freeTable() {
		this.empty = true;
	}

	/**
	 * Returns the Reservations PriorityQueue of the table
	 * 
	 * @return PriorityQueue<Reservations> pq
	 */
	public PriorityQueue<Reservation> getPq() {
		return pq;
	}
	
	/**
	 * Returns the earliest Reservation in the PriorityQueue of the Table
	 * @return Reservation R
	 */
	public Reservation peekReservation(){
		return pq.peek();
	}

	/**
	 * Adds Reservation res to the PriorityQueue of table Reservations
	 * 
	 * @param res
	 */
	public void makeReservation(Reservation res) {
		pq.add(res);
	}

	/**
	 * Removes Reservation res from the PriorityQueue of table Reservations
	 * 
	 * @param res
	 */
	public void removeReservation(Reservation res) {
		pq.remove(res);
	}

	/**
	 * Checks whether Reservation res is in the PriorityQueue of Table Reservations
	 * 
	 * @param res
	 * @return
	 */
	public boolean checkReservation(Reservation res) {
		return pq.contains(res);
	}

	/**
	 * <p>
	 * Checks whether the table is available for a Reservation at the time indicated
	 * by the passes Calendar Object cal
	 * </p>
	 * <p>
	 * A table is available for a Reservation if the new Reservation's time is at
	 * least BETWEENRES milliseconds after the previous Reservation and at least
	 * BETWEENRES milliseconds before the next Reservation
	 * </p>
	 * <p>
	 * Returns true if the Reservation with the requested time can be added and
	 * false if it cannot be added
	 * </p>
	 * 
	 * @param cal
	 * @return boolean
	 */
	public boolean checkAvailability(Calendar cal) {
		if (pq.isEmpty()) {
			return true;
		}
		Reservation[] temp = new Reservation[pq.size()];
		pq.toArray(temp);
		Arrays.sort(temp);

		Calendar prev = Calendar.getInstance();
		Calendar next = (Calendar) temp[0].getTimestamp().clone();

		int i = 0;
		while (next.compareTo(cal) < 0) {
			prev = (Calendar) next.clone();
			i++;
			if (i >= temp.length) {
				if (cal.getTimeInMillis() - prev.getTimeInMillis() > BETWEENRES)
					return true;
				else
					return false;
			}
			next = (Calendar) temp[i].getTimestamp().clone();
		}
		if (next.getTimeInMillis() - cal.getTimeInMillis() > BETWEENRES
				&& cal.getTimeInMillis() - prev.getTimeInMillis() > BETWEENRES) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prints table Identifier Number, Capacity and Ordered List of Reservations
	 */
	public void printTableDetails() {
		System.out.println("TableNo : " + this.getTableNo() + "\t Capacity : " + this.getCapacity());
		
		Reservation[] temp = new Reservation[pq.size()];
		pq.toArray(temp);
		for (int i = 0; i < pq.size(); i++) {
			temp[i].printReservation();
		}
		System.out.println("-------------------------------");


	}

	/**
	 * Specifies the writeObject function to write a TableReservations Object
	 * through the given ObjectOutput stream
	 * 
	 * @implNote The TableResrvation Object is written in a single block of TableNo,
	 *           Capacity, isEmpty and MAXLEN number of Reservations
	 * @param stream
	 * @throws IOException
	 */
	public void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeInt(this.tableNo);
		stream.writeInt(this.capacity);
		stream.writeBoolean(this.empty);

		int i;
		Reservation R = new Reservation();
		for (i = 0; i < MAXLEN && !(pq.isEmpty()); i++) {
			pq.poll().writeObject(stream);
		}
		for (int j = i; j < MAXLEN; j++) {
			R.writeObject(stream);
		}
	}

	/**
	 * Specifies the readObject function to read a TableReservations Object through
	 * the given ObjectOutput stream
	 * 
	 * @implNote The TableResrvation Object is read in a single block of TableNo,
	 *           Capacity, isEmpty and MAXLEN number of Reservations
	 * @param stream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		this.tableNo = stream.readInt();
		this.capacity = stream.readInt();
		this.empty = stream.readBoolean();

		Reservation res = new Reservation();
		res.readObject(stream);

		int i = 0;
		Reservation R = new Reservation();
		while (res.compareTo(R) == -1) {
			pq.add(res);
			res = new Reservation();
			res.readObject(stream);
			i++;
		}
		for (int j = i + 1; j < MAXLEN; j++) {
			res.readObject(stream);
		}
	}

	/**
	 * <p>
	 * Finds the Reservation in the Reservation List whose Customer has the contact
	 * number as parameter contactNo
	 * </p>
	 * <p>
	 * Returns Reservation Object whose Customer has the specified contact number,
	 * else returns null
	 * </p>
	 * 
	 * @param contactNo
	 * @return Reservation
	 */
	public Reservation findReservation(long contactNo) {
		Reservation[] temp = new Reservation[pq.size()];
		pq.toArray(temp);
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getCustomer().getContactNo() == contactNo) {
				return temp[i];
			}
		}
		return null;
	}
}
