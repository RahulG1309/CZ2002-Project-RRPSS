import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CustomerFile {
	/**
	 * String FILENAME : Represent the directory of Customer database file int
	 * 
	 */
	private static String FILENAME = "Customer.txt";
	/**
	 * Reads all the Customers from the file FILENAME and stores them in the
	 * Customers array with size MAXCUSTOMER
	 */
	public static void read(Customer[] Customers, int MAXCUSTOMER) {
		try {
			FileInputStream F = new FileInputStream(FILENAME);
			ObjectInputStream S = null;
			Customer temp = new Customer();
			int i = 0;
			while (temp != null) {
				S = new ObjectInputStream(F);
				temp = (Customer) S.readObject();
				Customers[i] = temp;
				i++;
				temp = new Customer();
				if (i >= MAXCUSTOMER) {
					break;
				}
			}
			S.close();
			F.close();
		} catch (NullPointerException E) {
			System.out.println("Reading Done");
			E.printStackTrace();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			// System.out.println("File not closed error");
			// I.printStackTrace();
		} catch (ClassNotFoundException E) {
			System.out.println("Class not found error");
		}
	}

	/**
	 * Writes one Customer from the Customers array into file FILENAME
	 * 
	 * @param C : Customer to be written is passed as parameter
	 */
	public static void write(Customer C) {
		try {
			FileOutputStream F = new FileOutputStream(FILENAME, true);
			ObjectOutputStream S = new ObjectOutputStream(F);
			S.writeObject(C);
			S.close();
			F.close();
		} catch (NullPointerException E) {
			System.out.println("Writting Done");
			E.printStackTrace();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			System.out.println("File not closed error");
		}

	}
}
