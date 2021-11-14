import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainMenuFile {
	
	/**
	 * String FILENAME is for reading and writing from the Menu database
	 */
	private static final String FILENAME = "Menu.txt";
	
	/**
	 * Read into a File
	 */
	public static void readAll(MainMenu Menu) {
		try {
			File myObj = new File(FILENAME);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileInputStream F = new FileInputStream(FILENAME);
			ObjectInputStream S = new ObjectInputStream(F);

			Menu.readObject(S);

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
		} catch (ClassNotFoundException I) {
			System.out.println("Class not found");
			I.printStackTrace();
		}

	}

	/**
	 * Write into a File
	 */
	public static void writeAll(MainMenu Menu) {
		try {
			File myObj = new File(FILENAME);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileOutputStream F = new FileOutputStream(FILENAME);
			ObjectOutputStream S = new ObjectOutputStream(F);

			Menu.writeObject(S);

			S.close();
			F.close();

		} catch (NullPointerException E) {
			System.out.println("Writting Done");
			E.printStackTrace();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			System.out.println("File not closed error");
			I.printStackTrace();
		}
	}
}
