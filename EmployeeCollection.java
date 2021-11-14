import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeCollection {
	/**
	 * An ArrayList that contains unassigned employees, continuously updated
	 */
	private ArrayList<Employee> Staff = new ArrayList<Employee>();

	/**
	 * reads employees from a file to the Staff ArrayList
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readEmployees() throws ClassNotFoundException {
		EmployeeFile.readEmployees(Staff);
	}

	/**
	 * Writes an employee into the file of saved employees
	 * 
	 * @param employee that is to be written into file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeEmployee(Employee employee) throws FileNotFoundException, IOException {
		EmployeeFile.writeEmployee(employee);
	}

	/**
	 * Assigns an employee to a table when an order is to be created, also removes
	 * it from the ArrayList
	 * 
	 * @return employee that has been assigned to the table
	 */
	public Employee assignEmployee() {
		for (Employee temp : Staff) {
			if (temp.getJobTitle().compareTo("Waiter") == 0 && (!temp.getAssigned())) {
				temp.assign();
				Staff.remove(temp);
				return temp;
			}
		}
		return null;
	}

	/**
	 * Removes assigned employee from table after invoice has been generated, also
	 * adds the employee back to the ArrayList
	 * 
	 * @param E employee that is to be unassigned from the table
	 */
	public void unassignEmployee(Employee E) {
		E.unassign();
		Staff.add(E);
	}

//	public static void initializeEmployees() throws FileNotFoundException, IOException {
//		Employee[] staff = new Employee[12];
//		staff[0] = new Employee("Dhruv", 1001, false, "Waiter", false);
//		staff[1] = new Employee("Aaron", 1002, false, "Waiter", false);
//		staff[2] = new Employee("Caleb", 1003, false, "Waiter", false);
//		staff[3] = new Employee("Marco", 1004, false, "Waiter", false);
//		staff[4] = new Employee("Henry", 1005, false, "Waiter", false);
//		staff[5] = new Employee("Julia", 1006, true, "Waiter", false);
//		staff[6] = new Employee("Deb", 1007, true, "Waiter", false);
//		staff[7] = new Employee("Emma", 1008, true, "Waiter", false);
//		staff[8] = new Employee("Taylor", 1009, true, "Waiter", false);
//		staff[9] = new Employee("Anita", 1010, true, "Waiter", false);
//		staff[10] = new Employee("Samuel", 2001, false, "Manager", false);
//		staff[11] = new Employee("Erica", 2002, true, "Manager", false);
//		for (int i = 0; i < 12; i++) {
//			writeEmployee(staff[i]);
//		}
//		System.out.println("Staff has been loaded");
//	}

}
