public class Dessert extends MenuItems {

	/**
	 * long serialVersionUID is a serial number to allow Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This class deals with the creation of a Dessert Object
	 * 
	 * @param name        gets the name for the Drink
	 * @param price       gets the price for the Drink
	 * @param description gets the description for the Drink
	 */
	public Dessert(String name, double price, String description) {
		super(name, price, description);
		this.type = "Dessert";
	}

}
