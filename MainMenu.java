import java.util.*;
import java.io.*;

/**
 * 
 * This Class handles the implementation of the MainMenu and the resulting
 * changes that the user can do to the said Menu. It also handles
 * creation/editing/removing of packages and menu items for the menu
 * 
 * @author JKF
 *
 */
public class MainMenu implements Serializable {

	/**
	 * long serialVersionUID is a serial number to allow Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList<MenuItems> menu is to store the MenuItems and Packages in the menu
	 */
	protected  ArrayList<MenuItems> menu = new ArrayList<MenuItems>();

	/**
	 * ArrayList<Package> is to store the number of Packages in it
	 */
	protected  ArrayList<Package> packageMenu = new ArrayList<Package>();

	/**
	 * int MAXSIZE is the maximum number of items for the Menu
	 */
	private static int MAXSIZE = 100;

	/**
	 * int MAXPACK is the maximum number of packages for the packages array
	 */
	private static int MAXPACK = 10;

	/**
	 * int nmenu is the number of MenuItems in the main menu
	 */
	protected  int nmenu = 0;

	/**
	 * THis method deals with the creation/addition of a MainCourse to an existing
	 * Menu or a existing/New Package
	 * 
	 * @param s_one gets the name for the main course
	 * @param d_one gets the price to be fixed for the main course
	 * @param s_two gets the description for the item
	 */
	public void addMainCourse(String s_one, Double d_one, String s_two) {
		MainCourse _maincourse = new MainCourse(s_one, d_one, s_two);

		if (menu.isEmpty()) {
			menu.add(0, _maincourse);
			// System.out.println(menu.size());
			return;
		}
		
		int index = 0;
		while (menu.get(index).compareTo(_maincourse) < 0 && index < menu.size()) {
			//System.out.println(index);
			index++;
		}
		menu.add(index, _maincourse);
	}

	/**
	 * The method deals with the creation/addition of a Drink to an existing Menu or
	 * a existing/New Package
	 * 
	 * @param s_one gets the name for the main course
	 * @param d_one gets the price to be fixed for the main course
	 * @param s_two gets the description for the item
	 */
	public void addDrink(String s_one, Double d_one, String s_two) {
		Drink _drink = new Drink(s_one, d_one, s_two);

		int index = 0;
		if (menu.isEmpty()) {
			menu.add(0, _drink);
			// System.out.println(menu.size());
			return;
		}
		
		while (menu.get(index).compareTo(_drink) < 0 && index < menu.size()) {
			index++;
		}
		menu.add(index, _drink);
	}

	/**
	 * The method deals with the creation/addition of a Dessert to an existing Menu
	 * or a existing/New Package
	 * 
	 * @param s_one gets the name for the main course
	 * @param d_one gets the price to be fixed for the main course
	 * @param s_two gets the description for the item
	 *
	 */
	public void addDessert(String s_one, Double d_one, String s_two) {
		Dessert _dessert = new Dessert(s_one, d_one, s_two);

		int index = 0;
		if (menu.isEmpty()) {
			menu.add(0, _dessert);
			// System.out.println(menu.size());
			return;
		}
		
		while (menu.get(index).compareTo(_dessert) < 0 && index < menu.size()) {
			index++;
		}
		menu.add(index, _dessert);
	}

	/**
	 * The method deals with the addition of newly created Package to the menu
	 * 
	 * @param p gets the package to be added to the Menu
	 */
	public void addPackage(Package p) {

		if (menu.isEmpty()) {
			menu.add(0, p);
			return;
		}
		int index = 0;

		while (menu.get(index).getType().compareTo("APackage") == 0) {
			index++;
			if (index >= menu.size()) {
				menu.add(p);
				packageMenu.add(p);
				return;
			}
		}
		menu.add(index, p);
		packageMenu.add(p);
	}

	/**
	 * This method deals with the searching of a MenuItem in the Menu by getting a
	 * String name input from the user
	 * 
	 * @param name gets the name to be searched in the Menu
	 * @return It returns the MenuItem object of the required name from the Menu
	 */
	public  MenuItems searchByName(String name) {
		int index = 0;
		while (menu.get(index).getName().compareTo(name) != 0 && index < menu.size()) {
			index++;
		}
		if (index < menu.size()) {
			return menu.get(index);
		}
		return null;
	}

	/**
	 * This method deals with the searching of MenuItem in the Menu by getting the
	 * index from the user
	 * 
	 * @param index gets the index to be searched in the Menu
	 * @return It returns the MenuItem object of the required index from the Menu
	 */
	public  MenuItems searchByIndex(int index) {
		return menu.get(index);
	}

	/**
	 * This method deals with the editing of the name of a MenuItem ie Individual
	 * MenuItems (or Package Names which are stored as MenuItems)
	 * 
	 * @param index gets the index of the MenuItems from the Menu
	 * @param Name  the new name to replace the pre-existing name of the said
	 *              MenuItem
	 */
	public void editItemName(int index, String Name) {
		MenuItems o = menu.get(index);
		o.setName(Name);
		menu.set(index, o);
	}

	/**
	 * This method deals with the editing of the price of a MenuItem ie Individual
	 * MenuItems
	 * 
	 * @param index gets the index of the MenuItems from the Menu
	 * @param Price the new price to replace the pre-existing price of the said
	 *              MenuItem
	 */
	public void editItemPrice(int index, Double Price) {
		MenuItems o = menu.get(index);
		o.setPrice(Price);
		menu.set(index, o);
	}

	/**
	 * This method deals with the editing of the description of a MenuItem
	 * 
	 * @param index       gets the index of the MenuItems from the Menu
	 * @param Description the new description to replace the pre-existing
	 *                    description of the said MenuItem
	 */
	public void editItemDescription(int index, String Description) {
		MenuItems o = menu.get(index);
		o.setDescription(Description);
		menu.set(index, o);
	}

	/**
	 * This method removes any item from the Main Menu ie Individual MenuItems or
	 * Packages
	 * 
	 * @param index gets the index from the user of the object to remove
	 */
	public void removeItem(int index) {
		// menu.remove(M);
		// MenuItems o=menu.get(index);
		menu.remove(index);
	}

	/**
	 * This method prints out all the contents of the Menu ie Individual MenuItems
	 * and the Packages
	 */
	public void print() {
		TableGenerator.printtable(menu, 0);
	}

	/**
	 * This method creates a Package and gets the user to input the Name, Price of
	 * the Package they are creating It also gets the user to choose the MenuItems
	 * which would be then stored in the Package The Description of the Package is
	 * the name of the contents of the Package chosen by the user ie the MenuItems
	 * that have been selected to be in the Package eg. Description: "Chicken
	 * Biriyani+Tea+Halwa"
	 */
	public void createPackage(String pname, double pprice) {

		Package p = new Package(pname, pprice, "");
		MenuItems m;
		int size = 0;
		Scanner sc = new Scanner(System.in);

		printMenuItems(); // change no1
		System.out.println("Enter index to put in Package(Enter -1 to exit)");
		int i = sc.nextInt();
		while (i != -1) {
			m = searchByIndex(i - 1);
			p.addPackageItem(m);
			size++;
			// p.print();
			if (size == 10) {
				System.out.println("You've entered the max number of times");
				break;
			}
			System.out.println("Enter index of the items to put in Package(Enter -1 to exit)");
			i = sc.nextInt();
		} // package.size

		p.updatedescription();
		p.print();
		this.addPackage(p);
		System.out.println(this.packageMenu.size());
		// packageMenu.add(p);
		// convertpackage(p); //add converted package->menuitem to main menu

	}

	/**
	 * This method prints only the MenuItems of the entire menu excluding the
	 * Packages
	 */
	public void printMenuItems() {
		// System.out.println("printing");
		ArrayList<MenuItems> temp = new ArrayList<>();
		int j;
		for (j = 0; j < menu.size(); j++) {
			if (menu.get(j).getType().compareTo("APackage") != 0) {
				break;
			}
		}
		nmenu = j;
		for (int i = j; i < menu.size(); i++) {
			temp.add(menu.get(i));
		}
		TableGenerator.printtable(temp, j);
	}

	/**
	 * This method prints only the Packages of the Menu excluding the MenuItems
	 */
	public void printPackage() {
		ArrayList<MenuItems> temp = new ArrayList<>();

		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getType().compareTo("APackage") == 0) {
				temp.add(menu.get(i));
			}
		}
		TableGenerator.printtable(temp, 0);

	}

	/**
	 * This method edits the Packages of the Menu. It does this by printing the
	 * available Packages and getting the user to choose from that Once the user
	 * chooses an option they are then asked to choose to edit between the
	 * name/price of the package or editing the content of the Packages
	 */
	public void printPackageContents(int index) {
		Package p = packageMenu.get(index);
		p.printPackageItems();

	}

	/**
	 * This method adds MenuItems to the specified Package
	 * 
	 * @param Pindex    - is the index of the Package to which the MenuItem should
	 *                  be added
	 * @param itemIndex - is the index of the MenuItem to be added to the Package
	 */
	public void addPackageItem(int Pindex, int itemIndex) {
		Package p = packageMenu.get(Pindex);
		p.addPackageItem(menu.get(itemIndex));
		p.updatedescription();
	}

	/**
	 * This method deals with the removal of MenuItem from the Package
	 * 
	 * @param Pindex     - get the Package which we want to remove Items from
	 * @param PitemIndex - the index of the MenuItem from the Package to remove
	 */
	public void removePackageItem(int Pindex, int PitemIndex) {
		//ArrayList<MenuItems> m = packageMenu.get(Pindex).getPackageItems();
		Package p = packageMenu.get(Pindex);
		p.removePackageItem(PitemIndex);
		p.updatedescription();

	}

	/**
	 * This method deals with the removal of Package by the index number
	 */
	public void removePackage(int index) {
		// print packages
		packageMenu.remove(index);
		menu.remove(index);
	}

	/**
	 * This method deals with the writing of objects into the FILENAME
	 * 
	 * @param o
	 * @throws IOException
	 */

	public void writeObject(ObjectOutputStream o) throws IOException {
		Package P = new Package();
		MenuItems M = new MenuItems();
		// System.out.println("writting");
		// System.out.println(packageMenu.size());
		for (Package pack : packageMenu) {
			// System.out.println("Writing a package");
			o.writeObject(pack);
			// pack.print();
			// pack.printPackageItems();
		}
		for (int i = 0; i < MAXPACK - packageMenu.size(); i++) {
			// System.out.println("Writing empty package");
			o.writeObject(P);
		}
		for (MenuItems m : menu) {
			if (m.getType().compareTo("APackage") != 0) {
				// System.out.println("Writing a menuItem");
				o.writeObject(m);
				// m.print();
			}
		}
		for (int i = 0; i < MAXSIZE - menu.size() + packageMenu.size(); i++) {
			o.writeObject(M);
			// System.out.println("Writing a empty menuitem");
		}
	}

	/**
	 * 
	 * @param o
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
//		Package P = new Package();
//		MenuItems M = new MenuItems();

		MenuItems m = new MenuItems();
		Package p = new Package();

		// p=(Package)o.readObject();
		int i = 0;
		while (p.getType().compareTo("APackage") == 0) {
			p = (Package) o.readObject();

			if (p.getName() != null) {
				// p.print();
				// p.printPackageItems();
				packageMenu.add(p);
				menu.add(p);
			}

			i++;
			p = new Package();

			if (i >= MAXPACK) {
				break;
			}
		}

		i = 0;
		while (i < (MAXSIZE - MAXPACK)) {
			m = (MenuItems) o.readObject();
			if (m.getName() != null) {
				menu.add(m);
			}
			i++;

			// m.print();
			m = new MenuItems();
		}
	}
}
