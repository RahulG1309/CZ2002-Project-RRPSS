import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author A Restaurant App deals with the management of all the classes and all
 *         the ManagerClasses and facilitates the functioning of the Restaurant
 */
public class RestaurantApp {

	/**
	 * Main App to run the program
	 * @param args
	 * @throws FileNotFoundException - Signals that an attempt to open the file denoted by a specified pathname has failed.
	 * @throws ClassNotFoundException - Thrown when an application tries to load in a class through its string name but no definition for the class with the specified name could be found.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException, IOException {

		FileReader.readAllFiles();
//		MainMenuMgr.intiateMenu();
//		ReservationMgr.readAllReservations();
//		EmployeeMgr.readEmployees();
//		CustomerMgr.read();
		// Reading all files, modified
		int year, month, day;

		Scanner sc = new Scanner(System.in);
		int choice, selection;
		do {
			System.out.println("-------------------------------");
			System.out.println("Welcome to Gandhi Restaurant!");
			System.out.println("Working hours : Mon-Sun 24 hours\n");
			System.out.println("1. Menu Items");
			System.out.println("-------------------------------");
			System.out.println("2. Promotional Set Packages");
			System.out.println("-------------------------------");
			System.out.println("3. Orders");
			System.out.println("-------------------------------");
			System.out.println("4. Reservations");
			System.out.println("-------------------------------");
			System.out.println("5. Check Table Availability");
			System.out.println("-------------------------------");
			System.out.println("6. Print Order Invoice");
			System.out.println("-------------------------------");
			System.out.println("7. Print Sale Revenue Report");
			System.out.println("-------------------------------");
			System.out.println("8. Exit");
			System.out.println("-------------------------------");
			System.out.println("Please enter your choice");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				do {
					System.out.println("1. Create a Menu Item");
					System.out.println("2. Edit a Menu Item");
					System.out.println("3. View Menu Items");
					System.out.println("4. Remove a Menu Item");
					System.out.println("5. Exit Sub-Menu");
					System.out.println("-------------------------------");
					System.out.println("Enter your choice");
					selection = sc.nextInt();
					switch (selection) {
					case 1:
						// System.out.println("case 1 of case 3");
						MainMenuMgr.createMenuItem();
						break;
					case 2:
						// System.out.println("case 2 of case 3");
						MainMenuMgr.edititem_Menu();
						break;
					case 3:
						// System.out.println("case 3 of case 3");
						MainMenuMgr.viewMenuItems();
						break;
					case 4:
						// System.out.println("case 4 of case 3");
						MainMenuMgr.removeitem_Menu();
						break;
					case 5:
						selection = 6;
						break;
					default:
						System.out.println("Invalid Choice, exiting Sub-Menu.");
						selection = 6;
						// break;
					}
				} while (selection < 6 && selection > 0);
				break;

			case 2:
				do {
					System.out.println("1. Create a Promotional Set Package");
					System.out.println("2. Edit a Promotional Set Package");
					System.out.println("3. View Promotional Set Packages");
					System.out.println("4. Remove a Promotional Set Package");
					System.out.println("5. Exit Sub-Menu");
					System.out.println("-------------------------------");
					System.out.println("Enter your choice");
					selection = sc.nextInt();
					switch (selection) {
					case 1:
						MainMenuMgr.createPackage();
						break;
					case 2:
						// System.out.println("case 2 of case 2");
						MainMenuMgr.editPackage();
						break;
					case 3:
						// System.out.println("case 3 of case 2");
						MainMenuMgr.viewPackage();
						break;
					case 4:
						// System.out.println("case 4 of case 2");
						MainMenuMgr.removePackage();
						break;
					case 5:
						// System.out.println("Exit sub menu :)");
						selection = 6;
						break;
					default:
						System.out.println("Invalid Choice, exiting Sub-Menu.");
						selection = 6;
						// break;
					}
				} while (selection < 6 && selection > 0);
				break;

			case 3:
				do {
					System.out.println("1. Create an Order");
					System.out.println("2. Edit an Order");
					System.out.println("3. View an Order");
					System.out.println("4. Remove an Order");
					System.out.println("5. Exit Sub-menu");
					System.out.println("-------------------------------");
					int tableNo;

					System.out.println("Enter your choice");
					selection = sc.nextInt();
					switch (selection) {
					case 1:
						System.out.println("Enter Table Number");
						tableNo = sc.nextInt();
						if (OrderMgr.findOrder(tableNo) != -1) {
							System.out.println("Table already has an Order!\n");
							break;
						}
						System.out.println("Enter your Order, here's the Menu!");
						int option;
						ArrayList<MenuItems> items = new ArrayList<MenuItems>();
						do {
							MainMenuMgr.viewMenu();
							System.out.println("\nEnter your choice, 0 to exit.");
							option = sc.nextInt();
							if (option == 0) {
								break;
							}
							//items.add(MainMenu.menu.get(option - 1));
							items.add(MainMenuMgr.Menu.searchByIndex(option -1));
						} while (option != 0);
						System.out.println("Creating an Order for Table: " + tableNo);
						OrderMgr.createOrder(items, tableNo);
						break;
					case 2:
						System.out.println("Enter Table Number");
						tableNo = sc.nextInt();
						if (OrderMgr.findOrder(tableNo) == -1) {
							System.out.println("Order not found.\n");
							break;
						}
						System.out.println("1. Add an Item");
						System.out.println("2. Remove an Item");
						switch (sc.nextInt()) {
						case 1:
							MainMenuMgr.viewMenu();
							System.out.println("\nEnter an Item to Add");
							OrderMgr.addItem(tableNo, MainMenuMgr.Menu.searchByIndex(sc.nextInt() - 1));
							break;

						case 2:
							OrderMgr.viewOrder(tableNo);
							System.out.println("\nEnter an Item to Remove");
							OrderMgr.removeItem(tableNo, sc.nextInt() - 1);
							break;

						default:
							System.out.println("Invalid Choice, exiting Sub-Menu.");
							break;
						}
						break;
					case 3:
						System.out.println("Enter Table Number");
						tableNo = sc.nextInt();
						OrderMgr.viewOrder(tableNo);
						break;
					case 4:
						System.out.println("Enter Table Number");
						tableNo = sc.nextInt();
						OrderMgr.removeOrder(tableNo);
						break;
					case 5:
						selection = 6;
						break;
					default:
						System.out.println("Invalid Choice, exiting Sub-Menu.");
						selection = 6;
						break;
					}
				} while (selection < 6 && selection > 0);
				break;

			case 4:
				do {
					System.out.println("1. Create a Reservation");
					System.out.println("2. Check a Reservation");
					System.out.println("3. Remove a Reservation");
					System.out.println("4. Walk In");
					System.out.println("5. View Reservations");
					System.out.println("6. Exit Sub-Menu");
					System.out.println("-------------------------------");
					System.out.println("Enter your choice");
					selection = sc.nextInt();
					switch (selection) {
					case 1:
//						Calendar cal = Calendar.getInstance();
//						System.out.println("Enter Date(DD/MM/YYYY) : ");
//						String dateInput = sc.nextLine();
						System.out.println("Enter the time and date for the Reservation");
						System.out.println("Enter Year : ");
						year = sc.nextInt();
						System.out.println("Enter Month(number) : ");
						month = sc.nextInt();
						System.out.println("Enter Date : ");
						day = sc.nextInt();
						System.out.println("Enter Hours(24 hour format) : ");
						int hour = sc.nextInt();
						System.out.println("Enter minutes : ");
						int minute = sc.nextInt();
						Calendar aDate = new GregorianCalendar(year, month - 1, day, hour, minute);
						System.out.println("The date of Reservation provided : " + aDate.getTime());

						System.out.println("Enter the Customer details for the Reservation");
						System.out.println("Enter Name : ");
						String name = sc.next();
						System.out.println("Enter Contact Number: ");
						long number = sc.nextLong();
						System.out.println("Enter number of pax : ");
						int pax = sc.nextInt();

						ReservationMgr.createReservation(name, number, pax, aDate);
						ReservationMgr.tabledets();

						break;
					case 2:
						System.out.println("Enter the Customer's Contact Number");
						long number1 = sc.nextLong();
						Reservation res = ReservationMgr.findReservation(number1);
						if (res != null) {
							System.out.println("Your reservation is ");
							res.printReservation();
						} else {
							System.out.println("No Reservation by this customer!");
						}
						break;
					case 3:
						System.out.println("Enter the Customer's Contact Number");
						number1 = sc.nextLong();
						res = ReservationMgr.findReservation(number1);

						if (res != null) {
							res.printReservation();
							System.out.println("Removing...");
							ReservationMgr.removeReservation(res);
						} else {
							System.out.println("No Reservation by this customer!");
						}
						break;
					case 4:
						System.out.println("Enter the Customer details for the Reservation");
						System.out.println("Enter Name : ");
						String name1 = sc.next();
						System.out.println("Enter Contact Number: ");
						long number2 = sc.nextLong();
						System.out.println("Enter number of pax : ");
						int pax1 = sc.nextInt();
						
						ReservationMgr.walkIn(name1, number2, pax1);
					
					case 5:
						System.out.println("Enter the Table No to see reservations of that table or");
						System.out.println("input -1 to see for all tables");
						System.out.println("TableNo : ");
						int t = sc.nextInt();
						if(t==-1) {
							ReservationMgr.tabledets();
						}
						else {
							ReservationMgr.tabledets(t);
						}

					case 6:
						selection = 7;
						break;
					default:
						System.out.println("Invalid Choice, exiting Sub-Menu.");
						selection = 7;
						// break;
					}
				} while (selection < 7 && selection > 0);
				break;
			case 5:
//				Calendar cal = Calendar.getInstance();
//					System.out.println("Enter Date(DD/MM/YYYY) : ");
//					String dateInput = sc.nextLine();
				System.out.println("Enter Year : ");
				year = sc.nextInt();

				System.out.println("Enter Month(number) : ");
				month = sc.nextInt();

				System.out.println("Enter Date : ");
				day = sc.nextInt();

				System.out.println("Enter Hours(24 hour format) : ");
				int hour = sc.nextInt();

				System.out.println("Enter minutes : ");
				int minute = sc.nextInt();

				Calendar aDate = new GregorianCalendar(year, month - 1, day, hour, minute);
				System.out.println(aDate.getTime());

				// Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
				// System.out.println(dateInput+"\t"+date1);

				ReservationMgr.checkTableAvailability(aDate);
				break;

			case 6:
				System.out.println("Enter Table Number");
				int tableNo = sc.nextInt();
				if (OrderMgr.findOrder(tableNo) == -1) {
					System.out.println("Order not found.\n");
					break;
				}
				OrderMgr.printInvoice(tableNo);
//				ReservationMgr.emptyTable(tableNo);
//				ReservationMgr.tabledets();
				break;
			case 7:
				do {
					System.out.println("1. Daily Sales Report");
					System.out.println("2. Monthly Sales Report");
					System.out.println("3. Yearly Sales Report");
					System.out.println("4. Complete Sales Report");
					System.out.println("5. Exit Sub-Menu");
					System.out.println("-------------------------------");
					selection = sc.nextInt();
					switch (selection) {
					case 1:
						System.out.println("Enter Year: ");
						year = sc.nextInt();

						System.out.println("Enter Month (0-12): ");
						month = sc.nextInt();

						System.out.println("Enter Day: ");
						day = sc.nextInt();

						SalesRevenueReport.generateSalesReport(day, month, year);
						break;

					case 2:

						System.out.println("Enter Year : ");
						year = sc.nextInt();

						System.out.println("Enter Month(number) : ");
						month = sc.nextInt();

						SalesRevenueReport.generateSalesReport(month, year);
						break;

					case 3:
						System.out.println("Enter Year : ");
						year = sc.nextInt();

						SalesRevenueReport.generateSalesReport(year);
						break;

					case 4:

						SalesRevenueReport.generateSalesReport();
						break;
					case 5:
						selection = 6;
					default:
						System.out.println("Exiting Sub-Menu.");
						selection = 6;
						// break;
					}
				} while (selection < 6 && selection > 0);
				break;
			default:
				System.out.println("Invalid choice, Exiting Menu.");
				choice = 7;
			}
		} while (choice < 7 && choice > 0);
		sc.close();
	}
}
