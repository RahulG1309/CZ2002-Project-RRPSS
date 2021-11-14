import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReservationsFile {
	private static String FILENAME = "Reservation.txt";
	/**
	 * Reads all the Reservations from the file and stores them in an object of type
	 * TableReservations for each table
	 */
	public static void readAllReservations(TableReservations[] Tables, int NUMTABLES) {
		try {
			File myObj = new File("Reservation.txt");
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
			for (int i = 0; i < NUMTABLES; i++) {
				Tables[i] = new TableReservations();
				Tables[i].readObject(S);
			}
			S.close();
			F.close();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			//System.out.println("File not closed error//");
			//I.printStackTrace();
		} catch (ClassNotFoundException C) {
			System.out.println("Class not found exception");
		}
	}

	/**
	 * Writes all the reservations from each table into the file.
	 */
	public static void writeAllReservations(TableReservations[] Tables, int NUMTABLES) {
		try {
			FileOutputStream F = new FileOutputStream(FILENAME);
			ObjectOutputStream S = new ObjectOutputStream(F);
			for (int i = 0; i < NUMTABLES; i++) {
				Tables[i].writeObject(S);
			}
			S.close();
			F.close();
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			System.out.println("File not closed error");
		}
	}
}
