import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages orders sent by the staff to the kitchen.
 *
 * @author Rahul
 */
public class OrderMgr {
	/**
	 * An object OrderCollection for all the current orders (Order objects)
	 */
	private static OrderCollection O = new OrderCollection();
	
	/**
	 * Creates an empty order and adds it to the list of orders Assigns an employee
	 * to the table and order
	 *
	 * @param items    ArrayList of MenuItems to store the order
	 * @param employee that created the order
	 * @param tableNo  of the table that placed the order
	 *
	 */
	public static void createOrder(ArrayList<MenuItems> items, int tableNo) {
		O.createOrder(items, tableNo);
	}// Creates an empty order and adds it to the list also assigns an employee

	/**
	 * Finds the corresponding order from the list of orders
	 *
	 * @param tableNo of the table that placed the order
	 *
	 * @return the index of the order in list of orders
	 *
	 */
	public static int findOrder(int tableNo) {
		return O.findOrder(tableNo);
	}

	/**
	 * Adds item into list of items in order after checking if order and item exists
	 *
	 * @param tableNo of the table that placed the order
	 * @param item    of type menu item that is to be found in the order
	 *
	 */
	public static void addItem(int tableNo, MenuItems item) {
		O.addItem(tableNo, item);
	}

	/**
	 * Removes item from the list of items present in the order after checking if
	 * order exists and items is present in the order
	 *
	 * @param tableNo of the table that placed the order
	 * @param index   of menu item that is to be found in the order
	 *
	 */
	public static void removeItem(int tableNo, int itemIndex) {
		O.removeItem(tableNo, itemIndex);
	}

	/**
	 * Displays order from list of orders after checking if order exists
	 *
	 * @param tableNo of the table that placed the order
	 *
	 */
	public static void viewOrder(int tableNo) {
		O.viewOrder(tableNo);
	}

	/**
	 * Removes the order of the given table from the list of orders
	 * 
	 * @param tableNo of the table whose order has to be removed
	 */
	public static void removeOrder(int tableNo) {
		O.removeOrder(tableNo);
	}

	/**
	 * Prints invoice from list of orders after checking if order exists The order
	 * is written into the file The employee is unassigned from the table The table
	 * is emptied The order is removed from list of orders
	 *
	 * @param tableNo of the table that placed the order
	 *
	 */
	public static void printInvoice(int tableNo) throws FileNotFoundException, IOException {
		O.printInvoice(tableNo);
	}

	/**
	 * Writing invoices into the file
	 *
	 * @param order that has been placed
	 *
	 */
	public static void writeOrder(Order order) throws FileNotFoundException, IOException {
		OrderFile.writeOrder(order);
	}

	/**
	 * Rounds off the value that is given to two decimal places
	 * 
	 * @param value of type double that is to be rounded off
	 * @return rounded off value of given value
	 */
	public static double roundValue(double value) {
		value = value * 100;
		int temp = (int) Math.round(value);
		return temp / 100.0;
	}
}
