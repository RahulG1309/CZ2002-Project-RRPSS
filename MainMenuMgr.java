import java.io.*;
import java.util.Scanner;

/**
 * 
 * @author JKF This class deals with the overall Management of the MainMenu
 *         Class and MenuItems and its subsidiary classes
 *
 */
public class MainMenuMgr implements Serializable {

	/**
	 * MainMenu menu is a copy of the original MainMenu
	 */
	protected static MainMenu Menu = new MainMenu();

	/**
	 * long serialVersionUID is a serial number to allow Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method deals with the editing of Items in the Main Menu This applies to
	 * both individual MenuItems and the details of the Packages
	 */
	public static void edititem_Menu() // MenuItem and Packages
	{
		Scanner sc = new Scanner(System.in);
		Menu.print();
		System.out.println("Enter index of item to edit");
		int index = sc.nextInt();
		while (index <= 0 || index > Menu.menu.size()) {
			System.out.println("Index out of Range!Try Again!");
			System.out.println("Enter index of item to edit");
			System.out.println(Menu.menu.size());

			index = sc.nextInt();

		}

		int check = 0;
		Menu.printMenuItems();
		do {
			System.out.println("What do you want to edit?\r\n" + "1. Name\r\n" + "2. Price\r\n" + "3. Description\r\n"
					+ "4. Exit Editing");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter New Name");
				String s = sc.next();
				Menu.editItemName(index - 1, s);
				Menu.print();
				break;
			case 2:
				System.out.println("Enter New Price");
				double d = sc.nextDouble();
				Menu.editItemPrice(index - 1, d);
				Menu.print();
				break;
			case 3:
				System.out.println("Enter New Description");
				sc.nextLine();
				String s1 = sc.nextLine();
				Menu.editItemDescription(index - 1, s1);
				Menu.print();
				break;
			case 4:
				check = 1;
				break;
			default:
				check = 1;
				break;
			}
		} while (check != 1);
		writeAll();
		// sc.close();
	}

	/**
	 * This method deals with the removal of any items (MenuItems or Packages) from
	 * the Main Menu
	 * 
	 */
	public static void removeitem_Menu() {
		if (Menu.menu.isEmpty()) {
			System.out.println(" No MenuItems present to remove!! ");
			return;
		}
		Menu.print();
		Scanner ss = new Scanner(System.in);
		System.out.println("Enter index of item to remove");
		int input = ss.nextInt();

		while (input <= 0 || input > Menu.menu.size()) {
			System.out.println("Index out of Range!Try Again!");
			System.out.println("Enter index of item to edit");
			input = ss.nextInt();

		}

		Menu.removeItem(input - 1);
		Menu.print();
		writeAll();
		// ss.close();
	}

	/**
	 * This method deals with the creation of a Menu Items. Once user has input what
	 * they want to create (i.e. MainCourse/Drinks/Dessert) it then calls the
	 * respective functions from the MainMenu class to create the MenuItems
	 */
	public static void createMenuItem() {
		String name;
		double price;
		String desc;
		int check = 0;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("What do you want to create?\r\n" + "1. Main Course\r\n" + "2. Drinks\r\n"
					+ "3. Dessert\r\n" + "4. to Exit\r\n");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter new Name for Main Course");
				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter Price for new Main Course");

				price = sc.nextDouble();
				sc.nextLine();

				System.out.println("Enter Description for new Main Course");
				desc = sc.nextLine();
				Menu.addMainCourse(name, price, desc);
				System.out.println("Main Course Created");
				Menu.print();

				break;
			case 2:
				System.out.println("Enter new Name for new Drink");

				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter Price for new Drink");

				price = sc.nextDouble();
				sc.nextLine();

				System.out.println("Enter Description for new Drink");
				desc = sc.nextLine();
				Menu.addDrink(name, price, desc);
				System.out.println("Drink Created");
				Menu.print();

				break;
			case 3:
				System.out.println("Enter new Name for Dessert");
				sc.nextLine();
				name = sc.nextLine();
				System.out.println("Enter Price for new Dessert");
				price = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter Description for new Dessert");
				desc = sc.nextLine();
				Menu.addDessert(name, price, desc);
				System.out.println("Dessert Created");
				Menu.print();

				break;
			case 4:
				check = 1;
				break;
			default:
				check = 1;
				break;
			}

		} while (check != 1);
		writeAll();
	}

	/**
	 * This method deals with the viewing of only MenuItems from the Main Menu
	 */
	public static void viewMenuItems() // Printing Only Menu Items for viewing here
	{
		Menu.printMenuItems();
	}
	
	/**
	 * This method deals with the viewing the full Menu from the Main Menu
	 */
	public static void viewMenu() 
	{
		Menu.print();
	}

	/**
	 * This method deals with the editing of Packages by calling a function from
	 * MainMenu
	 */

	public static void editPackage() // edit package - remove a package and add another new one
	{
		Scanner sc = new Scanner(System.in);
		Menu.printPackage(); // printing Package Items

		System.out.println("Enter Index of Package to edit");
		int index = sc.nextInt();

		Menu.printPackageContents(index - 1);

		int c;
		int check = 0;
		do {
			System.out.println("1. Add Item\r\n" + "2. Remove Item\r\n" + "3. Exit");
			int in = sc.nextInt();
			switch (in) {
			case 1:
				Menu.printMenuItems(); // Print Menu Items to add to Package
				System.out.println("Enter index of New item to add");
				c = sc.nextInt(); // the new index
				Menu.addPackageItem(index - 1, c - 1);
				Menu.printPackageContents(index - 1);// removing
				break;
			case 2:
				Menu.printPackageContents(index - 1);
				System.out.println("Enter index of New item to remove");
				c = sc.nextInt(); // the new index
				Menu.removePackageItem(index - 1, c - 1);
				Menu.printPackageContents(index - 1);// removing
				break;
			case 3:
				check = 1;
				break;
			default:
				check = 1;
				break;
			}
		} while (check != 1);// index of the item to edit

		// Menu.editPackage(index-1);
		System.out.println("Package Edited");
		writeAll();
	}

	/**
	 * This method deals with the viewing of only Packages from the Main Menu
	 */
	public static void viewPackage()// Printing only Package Items for viewing here
	{
		Menu.printPackage();
		// System.out.println("Packages viewed");
	}

	/**
	 * This method deals with the creation of Packages by calling a function from
	 * MainMenu class
	 */
	public static void createPackage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Package Name");
		String pname = sc.nextLine();
		System.out.println("Enter Price for the Package");
		double pprice = sc.nextDouble();
		Menu.createPackage(pname, pprice);
		System.out.println("Package Created");
		writeAll();
	}

	/**
	 * This method deals with the removal of a Package from the Main Menu
	 */
	public static void removePackage() {
		viewPackage();
		System.out.println("Enter index of Package to remove");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		Menu.removePackage(a - 1);
		System.out.println("Package successfully removed");
		viewPackage();
		writeAll();
	}

	/**
	 * Read into a File
	 */
	public static void readAll() {
		MainMenuFile.readAll(Menu);
	}

	/**
	 * Write into a File
	 */
	public static void writeAll() {
		MainMenuFile.writeAll(Menu);
	}

	public static void intiateMenu() {
		Menu.addMainCourse("Pizza", 20.0, "cool pizza");
		Menu.addMainCourse("Pasta", 10.0, "cool pasta");
		Menu.addDrink("Iced Milo", 3.0, "cool milo");
		Menu.addDrink("Iced Tea", 4.0, "cool tea");
		Menu.addDessert("Ice cream", 6.0, "cool ice cream");
		Menu.addDessert("ICe kacang", 7.0, "cool ice kacang");
		Package p1 = new Package("Package 1", 17.0, "MC+M+C");
		p1.addPackageItem(Menu.searchByIndex(0));
		p1.addPackageItem(Menu.searchByIndex(1));
		p1.addPackageItem(Menu.searchByIndex(2));

		// p1.print();
		Package p2 = new Package("Package 2", 14.0, "MC+M");
		p2.addPackageItem(Menu.searchByIndex(2));
		p2.addPackageItem(Menu.searchByIndex(3));
		p2.addPackageItem(Menu.searchByIndex(4));
		Package p3 = new Package("Package 2", 14.0, "MC+M");
		p3.addPackageItem(Menu.searchByIndex(2));
		p3.addPackageItem(Menu.searchByIndex(3));
		p3.addPackageItem(Menu.searchByIndex(4));
		// p2.print();
		Menu.addPackage(p1);
		Menu.addPackage(p2);
		Menu.addPackage(p3);
		// Menu.print();
	}

}
