import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EmployeeFile {
	/**
	 * Name of the file that has the Employees' Information
	 */
	private static String FILENAME = "Employees.txt";
	
	public static void readEmployees(ArrayList<Employee> Staff) throws ClassNotFoundException {
		try {
			FileInputStream F = new FileInputStream(FILENAME);
			ObjectInputStream S = null;
			Employee temp = new Employee();
			while (temp != null) {
				S = new ObjectInputStream(F);
				temp = (Employee) S.readObject();
				Staff.add(temp);
			}
			S.close();
			F.close();
		} catch (NullPointerException E) {
			System.out.println("Writting Done");
		} catch (FileNotFoundException E) {
			System.out.println("File not found error");
		} catch (IOException I) {
			//System.out.println("File not closed error");
		} 
	}

	/**
	 * Writes an employee into the file of saved employees
	 * 
	 * @param employee that is to be written into file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeEmployee(Employee employee) throws FileNotFoundException, IOException {
		FileOutputStream F = new FileOutputStream(FILENAME, true);
		ObjectOutputStream S = new ObjectOutputStream(F);
		S.writeObject(employee);
		S.close();
		F.close();
	}
}
