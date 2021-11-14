import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class OrderCollection {
	/**
	 * An ArrayList that contains all the current orders (Order objects)
	 */
	private ArrayList<Order> OrderList = new ArrayList<Order>();
	
	/**
	 * Creates an empty order and adds it to the list of orders Assigns an employee
	 * to the table and order
	 *
	 * @param items    ArrayList of MenuItems to store the order
	 * @param employee that created the order
	 * @param tableNo  of the table that placed the order
	 *
	 */
	public void createOrder(ArrayList<MenuItems> items, int tableNo) {
		Employee employee = EmployeeMgr.assignEmployee();
		if (employee == null) {
			System.out.println("No waiters are free currently!");
			return;
		}
		double totalPrice = 0.0;
		for (MenuItems item : items) {
			totalPrice += item.getPrice();
		}
		Order order = new Order(items, employee, tableNo, totalPrice);
		OrderList.add(order);
	}// Creates an empty order and adds it to the list also assigns an employee

	/**
	 * Finds the corresponding order from the list of orders
	 *
	 * @param tableNo of the table that placed the order
	 *
	 * @return the index of the order in list of orders
	 *
	 */
	public int findOrder(int tableNo) {
		if (OrderList.size() == 0) {
			return -1;
		}
		for (int i = 0; i < OrderList.size(); i++) {
			if (OrderList.get(i).getTableNo() == tableNo) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Adds item into list of items in order after checking if order and item exists
	 *
	 * @param tableNo of the table that placed the order
	 * @param item    of type menu item that is to be found in the order
	 *
	 */
	public void addItem(int tableNo, MenuItems item) {
		int orderNo = findOrder(tableNo);
		if (orderNo == -1) {
			System.out.println("Order not found.\n");
		} else {
			OrderList.get(orderNo).getItems().add(item);
			double price = OrderList.get(orderNo).getTotalPrice() + item.getPrice();
			OrderList.get(orderNo).setTotalPrice(price);
			System.out.println("Item added successfully!");
		}
	}

	/**
	 * Removes item from the list of items present in the order after checking if
	 * order exists and items is present in the order
	 *
	 * @param tableNo of the table that placed the order
	 * @param index   of menu item that is to be found in the order
	 *
	 */
	public void removeItem(int tableNo, int itemIndex) {
		int orderNo = findOrder(tableNo);
		if (orderNo == -1) {
			System.out.println("Order not found.\n");
		} else {
			if (itemIndex < 0 || itemIndex >= OrderList.get(orderNo).getItems().size()) {
				System.out.println("Item not found in Order.\n");
			} else {
				double price = OrderList.get(orderNo).getTotalPrice()
						- OrderList.get(orderNo).getItems().get(itemIndex).getPrice();
				OrderList.get(orderNo).getItems().remove(itemIndex);
				OrderList.get(orderNo).setTotalPrice(price);
				System.out.println("Item has been removed.\n");
			}
		}
	}

	/**
	 * Displays order from list of orders after checking if order exists
	 *
	 * @param tableNo of the table that placed the order
	 *
	 */
	public void viewOrder(int tableNo) {
		int orderNo = findOrder(tableNo);
		if (orderNo == -1) {
			System.out.println("Order not found.\n");
		} else {
			System.out.println("Order for Table: " + tableNo);
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for (MenuItems item : OrderList.get(orderNo).getItems()) {
				if (!map.containsKey(item.getName())) {
					map.put(item.getName(), 1);
				} else {
					map.put(item.getName(), map.get(item.getName()) + 1);
				}
//				System.out.println(i + 1 + ". " + OrderList.get(orderNo).getItems().get(i).getName() + "\t\t\t\t"
//						+ OrderList.get(orderNo).getItems().get(i).getPrice());
			}
//			int count = 1;
			ArrayList <String> items = new ArrayList <String>();
			ArrayList <Double> prices = new ArrayList <Double>();
			for (Map.Entry<String, Integer> mapItem : map.entrySet()) {
//				System.out.println(count++ + ". " + mapItem.getKey() + " x" + mapItem.getValue() + "\t\t\t"
//						+ (MainMenuMgr.Menu.searchByName(mapItem.getKey()).getPrice() * mapItem.getValue()));
				items.add(mapItem.getKey());
				prices.add((MainMenuMgr.Menu.searchByName(mapItem.getKey()).getPrice() * mapItem.getValue()));
				
			}
			TableGenerator.printInvoice(items, prices);
			System.out.println("----------------------------------------\n");
		}
	}

	/**
	 * Removes the order of the given table from the list of orders
	 * 
	 * @param tableNo of the table whose order has to be removed
	 */
	public void removeOrder(int tableNo) {
		int orderNo = findOrder(tableNo);
		if (orderNo == -1) {
			System.out.println("Order not found.\n");
		} else {
			OrderList.remove(orderNo);
		}
	}

	/**
	 * Prints invoice from list of orders after checking if order exists The order
	 * is written into the file The employee is unassigned from the table The table
	 * is emptied The order is removed from list of orders
	 *
	 * @param tableNo of the table that placed the order
	 *
	 */
	public void printInvoice(int tableNo) throws FileNotFoundException, IOException {
		int orderNo = findOrder(tableNo);
		if (orderNo == -1) {
			System.out.println("Order not found.\n");
		} else {
			Calendar currentTime = Calendar.getInstance();
			OrderList.get(orderNo).setTimestamp(currentTime);
			System.out.println("\nInvoice for Table: " + tableNo);
			System.out.println("Served by: " + OrderList.get(orderNo).getEmployee().getName());
			System.out.println("Timestamp: " + OrderList.get(orderNo).getTimestamp().getTime());
			System.out.println();
			viewOrder(tableNo);
			double total = OrderList.get(orderNo).getTotalPrice();
			if (CustomerMgr.checkMembership(tableNo)) {
				System.out.println(
						"Membership Discount (10%)\t\t" + roundValue(-0.1 * OrderList.get(orderNo).getTotalPrice()));
				total -= 0.1 * OrderList.get(orderNo).getTotalPrice();
			}
			System.out.println("Service Charge (10%)\t\t\t" + roundValue(0.1 * total));
			System.out.println("GST (7%)\t\t\t\t" + roundValue(0.07 * total));
			total = 1.17 * total;
			System.out.println("TOTAL\t\t\t\t\t" + roundValue(total));
			// After printing we shall write the order to a file, unassign the waiter, clear
			// the reservation and finally remove the order
			OrderMgr.writeOrder(OrderList.get(orderNo));
			EmployeeMgr.unassignEmployee(OrderList.get(orderNo).getEmployee());
			ReservationMgr.emptyTable(tableNo);
			OrderList.remove(orderNo);
		}
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
