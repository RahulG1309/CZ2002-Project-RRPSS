import java.io.Serializable;

/**
 * <p>
 * An Object of Customer represents a single customer with relevant customer
 * details such as name, contact number and membership status.
 * </p>
 * <p>
 * Customer class is Serializable to enable writing of Customers to the file.
 * The readObject and writeObject methods provide implementation details of
 * reading and writing Customer Objects to the file respectively.
 * </p>
 * <p>
 * Customer class is comparable and utilizes the contactNo field as the key to
 * compare two customer objects.
 * </p>
 * 
 * @author Dhruv
 *
 */
public class Customer implements Serializable, Comparable<Customer> {

	/**
	 * A UID to allow serialization of Objects of this Class
	 */
	private static final long serialVersionUID = 8532030939766867255L;

	/**
	 * The name of the customer who made the reservation
	 */
	private String name;

	/**
	 * The contact number of the customer, also can be used as a customerID
	 */
	private long contactNo; // Key value for Customer entries

	/**
	 * Shows whether customer is a member or not
	 */
	private boolean member;

	/**
	 * Constructs a default Customer Object
	 */
	public Customer() {
		super();
	}

	/**
	 * Constructs a Customer Object with the provided name, contact number, and
	 * membership details
	 * 
	 * @param name
	 * @param contactNo
	 * @param member
	 */
	public Customer(String name, long contactNo, boolean member) {
		this.name = name;
		this.contactNo = contactNo;
		this.member = member;
	}

	/**
	 * Returns the Name of the Customer Object
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the Contact number of the Customer Object
	 * 
	 * @return long contactNo
	 */
	public long getContactNo() {
		return contactNo;
	}

	/**
	 * Returns the membership status of the Customer
	 * 
	 * @return boolean member
	 */
	public boolean isMember() {
		return member;
	}

	/**
	 * Sets the member field of the customer to true
	 */
	public void makeMember() {
		this.member = true;
	}

	/**
	 * Implements the compareTo function by using the contact Number of the Customer
	 * as the key for comparing Customer Objects
	 *
	 */
	public int compareTo(Customer c) {
		if (this.getContactNo() < c.getContactNo()) {
			return -1;
		} else if (this.getContactNo() > c.getContactNo()) {
			return 1;
		} else {
			return 0;
		}
	}
}
