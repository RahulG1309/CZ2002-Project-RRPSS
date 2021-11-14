public class MainCourse extends MenuItems {

	/**
	 * long serialVersionUID is a serial number to allow Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This class deals with the creation of a MainCourse object
	 * 
	 * @param name        gets the Name for the Main course
	 * @param price       gets the price for the Main COurse
	 * @param description gets the description for the MAin COurse
	 */
	public MainCourse(String name, double price, String description) {
		super(name, price, description);
		this.type = "MainCourse";
	}

}
