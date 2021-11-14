/**
 * 
 * This class deals with managing the Customer Database of the restaurant And
 * facilitates other managers to link with respective customers such as orderMgr
 * and ReserVationMgr
 * 
 * @author Dhruv
 */
public class CustomerMgr {
	/**
	 * Represents an array of Customer class representing the Customer Database
	 */
	private static CustomerCollection C = new CustomerCollection();


	/**
	 * Returns true if the the Customer of the Table with the given Unique Table
	 * Identifier is a member, otherwise returns false
	 * 
	 * @param tableNo
	 * @return boolean
	 */
	public static boolean checkMembership(int tableNo) {
		return C.checkMembership(tableNo);
	}

	/**
	 * <p>
	 * Searches for the Customer by the parameter long number
	 * </p>
	 * <p>
	 * Returns Customer object if found, otherwise returns null
	 * </p>
	 * 
	 * @param number
	 * @return Customer
	 */
	public static Customer findCustomer(long number) {
		return C.findCustomer(number);
	}

	/**
	 * <p>
	 * Creates a new Customer Object with the specified parameters and adds them to
	 * the end of the Customers array
	 * </p>
	 * <p>
	 * Returns the created Customer Object
	 * </p>
	 * <p>
	 * Updates the file FILENAME with the new Customers array
	 * </p>
	 * 
	 * @param name
	 * @param contactNo
	 * @param member
	 * @return Customer
	 */
	public static Customer createCustomer(String name, long contactNo, boolean member) {
		return C.createCustomer(name, contactNo, member);
	}

	/**
	 * Makes the parameter Customer C a member
	 * 
	 * @param C
	 */
	public static void makeMember(Customer C) {
		C.makeMember();
	}
	
	public static void read() {
		C.read();
	}

	/**
	 * Writes one Customer from the Customers array into file FILENAME
	 * 
	 * @param A : Customer to be written is passed as parameter
	 */
	public static void write(Customer A) {
		C.write(A);
	}
}
