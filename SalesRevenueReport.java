import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;
//import java.util.Collections; 

/**
 * Generates the sales revenue report for the restaurant according to day, month
 * and year
 *
 * @author Rahul
 *
 */
public class SalesRevenueReport {

	/**
	 * List of orders that have been made by the restaurant
	 */
	private static ArrayList<Order> OrderList = new ArrayList<Order>();

	/**
	 * TreeMap (Sorted Hash Map) to facilitate the creation of sales reports of
	 * individual items
	 */
	private static TreeMap<String, Integer> map = new TreeMap<String, Integer>();

	/**
	 * Reads all the orders from invoice file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readAllOrders() throws ClassNotFoundException {
		OrderFile.readAllOrders(OrderList);
	}

	/**
	 * Generates daily sales report
	 *
	 * @param day
	 * @param month
	 * @param year
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void generateSalesReport(int day, int month, int year)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Calendar startDate = new GregorianCalendar(year, month - 1, day, 0, 0);
		Calendar endDate = (Calendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		readAllOrders();
		// Collections.sort(OrderList);
		// Sorting the invoices based on timestamp
		double totalRevenue = 0.0;
		map.clear();
		for (Order order : OrderList) {
			if (order.getTimestamp().after(startDate) && order.getTimestamp().before(endDate)) {
				totalRevenue += order.getTotalPrice();
				for (MenuItems item : order.getItems()) {
					if (!map.containsKey(item.getName())) {
						map.put(item.getName(), 1);
					} else {
						map.put(item.getName(), map.get(item.getName()) + 1);
					}
				}
			}
		}
		System.out.println("Sales Revenue Report for " + startDate.getTime() + " to " + endDate.getTime());
		int count = 1;
		for (Map.Entry<String, Integer> mapItem : map.entrySet()) {
			System.out.println(count++ + ". " + mapItem.getKey() + "\t\t" + mapItem.getValue());
		}
		System.out.println("Total Revenue\t\t" + totalRevenue);
		System.out.println();
	}

	/**
	 * Generates monthly sales report
	 *
	 * @param month
	 * @param year
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void generateSalesReport(int month, int year)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Calendar startDate = new GregorianCalendar(year, month - 1, 1, 0, 0);
		Calendar endDate = (Calendar) startDate.clone();
		endDate.add(Calendar.MONTH, 1);
		readAllOrders();
		// Collections.sort(OrderList);
		// Sorting the invoices based on timestamp
		double totalRevenue = 0.0;
		map.clear();
		for (Order order : OrderList) {
			if (order.getTimestamp().after(startDate) && order.getTimestamp().before(endDate)) {
				totalRevenue += order.getTotalPrice();
				for (MenuItems item : order.getItems()) {
					if (!map.containsKey(item.getName())) {
						map.put(item.getName(), 1);
					} else {
						map.put(item.getName(), map.get(item.getName()) + 1);
					}
				}
			}
		}
		System.out.println("Sales Revenue Report for " + startDate.getTime() + " to " + endDate.getTime());
		int count = 1;
		for (Map.Entry<String, Integer> mapItem : map.entrySet()) {
			System.out.println(count++ + ". " + mapItem.getKey() + "\t\t" + mapItem.getValue());
		}
		System.out.println("Total Revenue\t\t" + totalRevenue);
		System.out.println();
	}

	/**
	 * Generates annual sales report
	 *
	 * @param year
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void generateSalesReport(int year) throws FileNotFoundException, IOException, ClassNotFoundException {
		Calendar startDate = new GregorianCalendar(year, 0, 1, 0, 0);
		Calendar endDate = (Calendar) startDate.clone();
		endDate.add(Calendar.YEAR, 1);
		readAllOrders();
		// Collections.sort(OrderList);
		// Sorting the invoices based on timestamp
		double totalRevenue = 0.0;
		map.clear();
		for (Order order : OrderList) {
			if (order.getTimestamp().after(startDate) && order.getTimestamp().before(endDate)) {
				totalRevenue += order.getTotalPrice();
				for (MenuItems item : order.getItems()) {
					if (!map.containsKey(item.getName())) {
						map.put(item.getName(), 1);
					} else {
						map.put(item.getName(), map.get(item.getName()) + 1);
					}
				}
			}
		}
		System.out.println("Sales Revenue Report for " + startDate.getTime() + " to " + endDate.getTime());
		int count = 1;
		for (Map.Entry<String, Integer> mapItem : map.entrySet()) {
			System.out.println(count++ + ". " + mapItem.getKey() + "\t\t" + mapItem.getValue());
		}
		System.out.println("Total Revenue\t\t" + totalRevenue);
		System.out.println();
	}

	/**
	 * Generates sales report till date
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void generateSalesReport() throws FileNotFoundException, IOException, ClassNotFoundException {
		readAllOrders();
		// Collections.sort(OrderList);
		// Sorting the invoices based on timestamp
		double totalRevenue = 0.0;
		map.clear();
		for (Order order : OrderList) {
			totalRevenue += order.getTotalPrice();
			for (MenuItems item : order.getItems()) {
				if (!map.containsKey(item.getName())) {
					map.put(item.getName(), 1);
				} else {
					map.put(item.getName(), map.get(item.getName()) + 1);
				}
			}
		}
		System.out.println("Complete Sales Revenue Report");
		int count = 1;
		for (Map.Entry<String, Integer> mapItem : map.entrySet()) {
			System.out.println(count++ + ". " + mapItem.getKey() + "\t\t" + mapItem.getValue());
		}
		System.out.println("Total Revenue\t\t" + totalRevenue);
		System.out.println();
	}
}
