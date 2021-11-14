import java.io.Serializable;

/**
 * Entity class for an Employee of the Restaurant
 * 
 * @author Rahul
 *
 */
public class Employee implements Serializable {
	/**
	 * A UID to allow serialization of Objects of this Class
	 */
	private static final long serialVersionUID = 2351660791696894328L;
	/**
	 * Name of the employee
	 */
	private String name;
	/**
	 * Gender of the employee
	 */
	private boolean gender;
	/**
	 * ID of the employee
	 */
	private int employeeID;
	/**
	 * Job Title of the employee (Waiter, Manager etc.)
	 */
	private String jobTitle;
	/**
	 * Variable that informs whether the employee is assigned to a table or not
	 * currently
	 */
	private boolean assigned;

	/**
	 * Default Constructor
	 */
	public Employee() {
		super();
	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param name       Name of the employee
	 * @param employeeID ID of the employee
	 * @param gender     Gender of the employee
	 * @param jobTitle   Job Title of the employee (Waiter, Manager etc.)
	 * @param assigned   Variable that informs whether the employee is assigned to a
	 *                   table or not currently
	 */
	public Employee(String name, int employeeID, boolean gender, String jobTitle, boolean assigned) {
		super();
		this.name = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
		this.assigned = assigned;
	}

	/**
	 * Getter of the name of employee
	 * 
	 * @return name of employee
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of the gender of employee
	 * 
	 * @return gender of employee
	 */
	public boolean getGender() {
		return this.gender;
	}

	/**
	 * Getter of the employee ID of employee
	 * 
	 * @return employeeID of the employee
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * Returns the assigned to table status of the employee
	 * 
	 * @return whether employee is assigned or not
	 */
	public boolean getAssigned() {
		return assigned;
	}

	/**
	 * Getter of the job title of the employee
	 * 
	 * @return job title of employee
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Setter of the name of an employee
	 * 
	 * @param name of employee
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter of the gender of employee
	 * 
	 * @param gender of employee
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	/**
	 * Setter of the employeeID of employee
	 * 
	 * @param employeeID of employee
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * Setter of the job of the employee
	 * 
	 * @param jobTitle of employee
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Sets the value of whether employee is assigned to a table to false when they
	 * are unassigned from a table
	 */
	public void unassign() {
		this.assigned = false;
	}

	/**
	 * Sets the value of whether employee is assigned to a table to true when they
	 * are assigned to a table
	 */
	public void assign() {
		this.assigned = true;
	}
}
