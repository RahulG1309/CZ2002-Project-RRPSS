import java.io.Serializable;

/**
 * 
 * This class is the basic/template class on which MenuItems such as
 * MainCourse/Dessert/Drink/Package are created
 * 
 * @author JKF
 */
public class MenuItems implements Serializable, Comparable<MenuItems> {

	/**
	 * long serialVersionUID allows for the serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * String name - is the name of the MenuItems
	 */
	protected String name;

	/**
	 * double price - is the price of the MenuItems
	 */
	protected double price;

	/**
	 * String Description - is the description of the MenuItems
	 */
	protected String description;

	/**
	 * String type - is the type of MenuItems ie MainCourse,Drinks,Desserts,APackage
	 */
	protected String type;

	/**
	 * Default Constructor
	 */
	public MenuItems() {
		super();
	}

	/**
	 * Constructor class
	 * 
	 * @param name        set name for the MenuItem
	 * @param price       sets price for the MenuItem
	 * @param description sets description for the MenuItem
	 */
	public MenuItems(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.type = "MenuItems";
	}

	/**
	 * @return returns the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name sets name for MenuItems
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price sets price for the MenuItems
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return returns the Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description sets description for the MenuItems
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the type of the MenuItem (when it is called by the sub classes the
	 *         type gets converted to suit the respective class that calls it eg
	 *         when MainCourse class calls it the type gets converted as
	 *         "MainCourse")
	 */
	public String getType() {
		return type;
	}

	/**
	 * this method compares with other MenuItems Object
	 */
	public int compareTo(MenuItems M) {
		return this.getType().compareTo(M.getType());
	}

	/**
	 * This method prints the details of this MenuItem object
	 */
	public void print() {
		System.out.println(this.name + "\t" + this.price + "\t" + this.description + "\t" + this.type);
		System.out.println();
	}
}
