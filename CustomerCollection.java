public class CustomerCollection {
	/**
	 * MAXCUSTOMER : Represents MAXIMUM amount of customers Customer[] Customers :
	 */
	private static int MAXCUSTOMER = 1000;

	/**
	 * Represents an array of Customer class representing the Customer Database
	 */
	private Customer[] Customers = new Customer[MAXCUSTOMER];


	/**
	 * Returns true if the the Customer of the Table with the given Unique Table
	 * Identifier is a member, otherwise returns false
	 * 
	 * @param tableNo
	 * @return boolean
	 */
	public boolean checkMembership(int tableNo) {
		Customer temp;
		temp = ReservationMgr.getCustomer(tableNo);
		if (temp != null) {
			return temp.isMember();
		} else {
			System.out.println("Member Not found");
			return false;
		}
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
	public Customer findCustomer(long number) {
		for (int i = 0; i < Customers.length; i++) {
			try {
				if (Customers[i].getContactNo() == number) {
					return Customers[i];
				}
			} catch (NullPointerException E) {
				break;
			}
		}
		return null;
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
	public Customer createCustomer(String name, long contactNo, boolean member) {
		int i = 0;
		while (Customers[i] != null) {
			i++;
		}
		Customers[i] = new Customer(name, contactNo, member);
		CustomerFile.write(Customers[i]);
		System.out.println("New Customer Created");
		return Customers[i];
	}

	/**
	 * Makes the parameter Customer C a member
	 * 
	 * @param C
	 */
	public void makeMember(Customer C) {
		C.makeMember();
	}
	
	public void read() {
		CustomerFile.read(Customers, MAXCUSTOMER);
	}

	/**
	 * Writes one Customer from the Customers array into file FILENAME
	 * 
	 * @param C : Customer to be written is passed as parameter
	 */
	public void write(Customer C) {
		CustomerFile.write(C);
	}
}
