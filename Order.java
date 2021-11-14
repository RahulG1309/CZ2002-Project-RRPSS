import java.io.Serializable;
import java.util.Calendar;
import java.util.ArrayList;

/**
 * The entity class of Order made by staff on the system
 *
 * @author Rahul
 *
 */
public class Order implements Serializable, Comparable<Order> {

	/**
	 * A UID to allow serialization of Objects of this Class
	 */
	private static final long serialVersionUID = -8723025433254208009L;

	/**
	 * The list of items of type MenuItems in order
	 */
	private ArrayList<MenuItems> items;

	/**
	 * The employee that creates the order
	 */
	private Employee employee;

	/**
	 * The time at which the order invoice is generated
	 */
	private Calendar timestamp;

	/**
	 * The number of the table that placed the order
	 */
	private int tableNo; // This uniquely identifies an order

	/**
	 * The total price of the order placed
	 */
	private double totalPrice;

	/**
	 * A default constructor
	 */
	public Order() {
		super();
	}

	/**
	 * A parameterized constructor
	 *
	 * @param items      ArrayList of type MenuItems that are placed in the order
	 * @param employee   that placed the order
	 * @param tableNo    of the table that placed the order
	 * @param totalPrice of order
	 *
	 */
	public Order(ArrayList<MenuItems> items, Employee employee, int tableNo, double totalPrice) {
		this.items = items;
		this.employee = employee;
		this.tableNo = tableNo;
		this.totalPrice = totalPrice;
		this.timestamp = Calendar.getInstance();
	}

	/**
	 * Getter of the list of items in the order
	 * 
	 * @return this items list
	 */
	public ArrayList<MenuItems> getItems() {
		return this.items;
	}

	/**
	 * Getter of the number of the table that placed the order
	 * 
	 * @return this table's number
	 */
	public int getTableNo() {
		return this.tableNo;
	}

	/**
	 * Getter of the employee assigned to the table that placed the order
	 * 
	 * @return the employee that created the order
	 */
	public Employee getEmployee() {
		return this.employee;
	}

	/**
	 * Getter of the system time at that instant
	 * 
	 * @return the current time
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * Getter of the total price of the order including taxes
	 * 
	 * @return total price of order
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Setter of the time of which order was placed
	 * 
	 * @param timestamp of order
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Setter of the total price of the placed order
	 * 
	 * @param totalPrice of order
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Implements the compareTo function by using the time stamp of the order as the
	 * key for comparing Order Objects returns -1 if date is before date2
	 * 
	 * @param order that is to be compared
	 */
	@Override
	public int compareTo(Order order) {
		return this.getTimestamp().compareTo(order.getTimestamp());
	}
}
