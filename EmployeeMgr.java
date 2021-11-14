import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A manager class that handles the Employee Entities: assigns employees and removes
 * assigned employee from tables
 * 
 * @author Rahul
 * 
 */
public class EmployeeMgr {
	/**
	 * An ArrayList that contains unassigned employees, continuously updated
	 */
	private static EmployeeCollection Staff = new EmployeeCollection();

	/**
	 * reads employees from a file to the Staff ArrayList
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readEmployees() throws ClassNotFoundException {
		Staff.readEmployees();
	}

	/**
	 * Writes an employee into the file of saved employees
	 * 
	 * @param employee that is to be written into file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeEmployee(Employee employee) throws FileNotFoundException, IOException {
		Staff.writeEmployee(employee);
	}

	/**
	 * Assigns an employee to a table when an order is to be created, also removes
	 * it from the ArrayList
	 * 
	 * @return employee that has been assigned to the table
	 */
	public static Employee assignEmployee() {
		return Staff.assignEmployee();
	}

	/**
	 * Removes assigned employee from table after invoice has been generated, also
	 * adds the employee back to the ArrayList
	 * 
	 * @param E employee that is to be unassigned from the table
	 */
	public static void unassignEmployee(Employee E) {
		Staff.unassignEmployee(E);
	}
}
