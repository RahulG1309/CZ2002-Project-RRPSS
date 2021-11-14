import java.util.*;

/**
 * 
 * This class is a subclass of the MenuItems which wholly deals with the
 * constituents of the Package (consisting of multiple MenuItems and the details
 * of the Package)
 * 
 * @author JKF
 */
public class Package extends MenuItems {

	/**
	 * long serialVersionUID is a serial number to allow Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * int SIZE - it deals with the maximum number of items a Package can hold
	 */
	protected static final int SIZE = 10;

	/**
	 * ArrayList<MenuItems> packageItems - stores the MenuItems that would be
	 * contained in the Package
	 */
	private ArrayList<MenuItems> packageItems = new ArrayList<MenuItems>();

	/**
	 * double actualPrice - stores the actual price of all the individual MenuItems
	 * (not to be confused with the pre-defined price of the Package which is not
	 * the same as the actualPrice)
	 */
	private double actualPrice;

	/**
	 * Default Constructor
	 */
	public Package() {
		super();
		this.type = "APackage";
	}

	/**
	 * Constructor class
	 * 
	 * @param name        the name of the Package
	 * @param price       the price of the Package
	 * @param description the description of the Package - the names of the
	 *                    constituents MenuItems in the Package
	 */
	public Package(String name, double price, String description) {
		super(name, price, description);
		this.type = "APackage";
	}

	/**
	 * Calculates the actual price of the Package by adding the price of the
	 * individual MenuItems
	 */
	public void calculateActualPrice() {
		for (MenuItems M : packageItems) {
			this.actualPrice += M.getPrice();
		}
	}

	/**
	 * 
	 * @return returns the actualPrice
	 */
	public double getActualPrice() {
		return actualPrice;
	}

	/**
	 * the method deals with the adding of MenuItems into its array which will be
	 * the array of consitutent MenuItems in this Package
	 * 
	 * @param M gets input of individual MenuItems to store in Package array
	 */
	public void addPackageItem(MenuItems M) {
		if (packageItems.isEmpty()) {
			packageItems.add(M);
			return;
		}

		if (packageItems.size() < SIZE) {
			int index = 0;
			while (packageItems.get(index).compareTo(M) < 0) {
				index++;
				if (index >= packageItems.size()) {
					packageItems.add(M);

					return;
				}
			}
			if (index < packageItems.size()) {
				packageItems.add(M);
			}
		} else {
			System.out.println("Package Full");
		}
	}

	/**
	 * This method deals with the removal of MenuItems from the Package arrat
	 * 
	 * @param M the MenuItem to remove
	 */
	public void removePackageItem(MenuItems M) {
		if (packageItems.size() > 0) {
			packageItems.remove(M);
		} else {
			System.out.println("Package is Empty");
		}

	}

	/**
	 * 
	 * @param index - gets the index of the item to remove from the
	 *              ArrayList<MenuItems> packageItems
	 */
	public void removePackageItem(int index) {
		if (packageItems.size() > 0) {
			packageItems.remove(index);
		} else {
			System.out.println("Package is Empty");
		}

	}

	/**
	 * This method deals with the size of the Package Array
	 * 
	 * @return returns the size of the array (0<SIZE<10)
	 */
	public static int getSize() {
		return SIZE;
	}

	/**
	 * This method returns the PackageArray for easier access
	 * 
	 * @return returns the Package ArrayList<MenuItems>
	 */
	public ArrayList<MenuItems> getPackageItems() {
		return packageItems;
	}

	/**
	 * this method removes package item from the Package ArrayList<MenuItems> by
	 * getting the name
	 * 
	 * @param name the name of the MenuItem to remove from the ArrayList
	 */
	public void removePackageItem(String name) {
		int index = 0;
		while (packageItems.get(index).getName().compareTo(name) != 0 && index < packageItems.size()) {
			index++;
		}
		if (index < packageItems.size()) {
			packageItems.remove(index);
		}
	}

	/**
	 * This method prints all the content of the Package array (ie the MenuItems)
	 */
	public void printPackageItems() {
		TableGenerator.printpackagecontents(packageItems, this.getName());
	}

	/**
	 * This method prints the details of the Package and not its constituents (ie
	 * the Name,Price and description of the Package)
	 */
	@Override
	public void print() {
		System.out.println(this.name + "\t" + this.price + "\t" + this.description + "\t" + this.type);
		System.out.println();
	}

	/**
	 * This method deals with updating the description of the Package that prints
	 * the names of all the constituent MenuItems stored in it
	 */
	public void updatedescription() {
		String d = "Contents : ";
		if (!packageItems.isEmpty()) {
			for (MenuItems m : packageItems) {
				d += m.getName() + " + ";
			}
			d = d.substring(0, d.length() - 2);
			this.description = d;
		} else {
			this.description = "Contents : None";
		}

	}

}
