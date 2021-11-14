import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author pratham
 * The class is used when the system restarts to read all the Customer,Reservation,Employee and Menu Data files.
 *  
 */
public class FileReader {
	
	/**
	 *  The method reads all data files
	 */
	public static void readAllFiles() {
		
		try {
			CustomerMgr.read();
			ReservationMgr.readAllReservations();
			MainMenuMgr.readAll();
			EmployeeMgr.readEmployees();
		} catch (NullPointerException E) {
			System.out.println("Reading Done");
			//E.printStackTrace();
		} catch (ClassNotFoundException E) {
			System.out.println("Class not found error");
		}
		
	}
	
}
