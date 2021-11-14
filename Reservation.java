import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <p>
 * An object of the Reservation Class specifies the parameters of a single
 * Reservation such as number of people who are catered to by the reservation,
 * name of the customer creating the Reservation and the time of the specified
 * Reservation
 * </p>
 * <p>
 * Reservation class is Serializable and Comparable. Two Reservations are
 * compared with the time of the Reservation(Calendar timestamp) taken as the
 * key.
 * </p>
 * <p>
 * Reservation class utilizes the Customer and Calendar classes.
 * </p>
 * 
 * @author Dhruv
 *
 */
public class Reservation implements Serializable, Comparable<Reservation> {
	private static final long serialVersionUID = -5791620781844215195L;

	/**
	 * The number of people for which the reservation has been made
	 */
	private int pax;

	/**
	 * The customer that made the reservation
	 */
	private Customer customer;

	/**
	 * the time for which the reservation has been made
	 */
	private Calendar timestamp;

	/**
	 * <p>
	 * Constructs a new Reservation Object with the Calendar timestamp set to 1
	 * January 2050, 1200 hours by default
	 * </p>
	 * 
	 * @implNote The Calendar is set so as to create a end-of-ReservationList flag
	 *           while reading and writing reservations
	 * 
	 */
	public Reservation() {
		super();
		Calendar aDate = new GregorianCalendar(2050, 1, 1, 12, 0);// default value
		this.timestamp = aDate;
	}

	/**
	 * Constructs a Reservation with the specified number of people catered to by
	 * the reservation(pax), Customer object making the Reservation and the Calendar
	 * timestamp for the Reservation.
	 * 
	 * @param pax
	 * @param customer
	 * @param timestamp
	 */
	public Reservation(int pax, Customer customer, Calendar timestamp) {
		this.pax = pax;
		this.customer = customer;
		this.timestamp = timestamp;
	}

	/**
	 * Getter method for time of Reservation
	 * 
	 * @return Calendar timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * Setter method for the time of the Reservation
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Getter for the number of people catered to by the Reservation
	 * 
	 * @return int pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Getter for the Customer Object with the details of the customer who made the
	 * Reservation
	 * 
	 * @return Customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Prints all the Reservation details in the format Customer Name, Customer
	 * Contact Number, Pax and Timestamp
	 */
	public void printReservation() {
		System.out.println("Reservation Details: ");
		System.out.println("\nName:\t\t\t" + customer.getName());
		System.out.println("\nContact No.:\t\t" + customer.getContactNo());
		System.out.println("\nPax:\t\t\t" + pax);
		System.out.println("\nTime:\t\t\t" + timestamp.getTime());
	}

	/**
	 * Implements the compareTo function by using the timestamp of the Reservation
	 * as the key for comparing Reservation Objects returns -1 if date is before
	 * date2
	 * 
	 * @param order that is to be compared
	 */
	public int compareTo(Reservation res) {
		return this.getTimestamp().compareTo(res.getTimestamp());
	}

	/**
	 * Specifies the writeObject function to write a Reservation Object through the
	 * given ObjectOutput stream
	 * 
	 * @param stream
	 * @throws IOException
	 */
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeInt(pax);
		// stream.writeObject(resId);
		stream.writeObject(customer);
		stream.writeObject(timestamp);
	}

	/**
	 * Specifies the readObject function to read a Reservation Object through the
	 * given ObjectInput stream
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		this.pax = stream.readInt();
		// this.resId = (String) stream.readObject();
		this.customer = (Customer) stream.readObject();
		this.timestamp = (Calendar) stream.readObject();
	}
}
