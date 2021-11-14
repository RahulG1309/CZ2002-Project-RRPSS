import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OrderFile {
	/**
	 * The name of the file that will contain the invoices
	 */
	private static String FILENAME = "Invoices.txt";
	
	/**
	 * Writing invoices into the file
	 *
	 * @param order that has been placed
	 *
	 */
	public static void writeOrder(Order order) throws FileNotFoundException, IOException {
		//System.out.println("Writing");
		FileOutputStream F = new FileOutputStream(FILENAME, true);
		ObjectOutputStream S = new ObjectOutputStream(F);
		S.writeObject(order);
		S.close();
		F.close();
	}
	
	/**
	 * Reads all the orders from invoice file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readAllOrders(ArrayList<Order> OrderList) throws ClassNotFoundException {
		// Employee[] staff = new Employee[MAXSIZE];
		try {
			FileInputStream F = new FileInputStream(FILENAME);
			ObjectInputStream S = null;
			Order temp = new Order();
			while (temp != null) {
				S = new ObjectInputStream(F);
				temp = (Order) S.readObject();
				OrderList.add(temp);
			}
			S.close();
			F.close();
		} catch (NullPointerException E) {
			System.out.println("Writting Done");
			E.printStackTrace();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			//System.out.println("File not closed error");
		}
	}
}
