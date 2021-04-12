package microStarCableVision;

import java.io.Serializable;

import java.util.ArrayList;

import com.microstar.cablevision.security.Security;

//Creating the Parent Class as Employee
public class Employee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Creating the General/Main attributes that all Employees would share.
	private String empId;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String email;
	private int telephoneNumber;
	private ArrayList<Messages> messageList;
	private ArrayList<Complaint> complaintList;
	private String password;
	final String ID_PREFIX = "EMP";
	
	
	//Default Constructor
	public Employee() 
	{
		empId = "";
		firstName = "";
		lastName = "";
		jobTitle = "";
		email = "";
		telephoneNumber = 0;
		this.messageList = new ArrayList<Messages>();
		this.complaintList = new ArrayList<Complaint>();
	}

	//Primary Constructor
	public Employee(String empId, String firstName, String lastName, String jobTitle, String email, int telephoneNumber, char[] password) 
	{
		this.empId = ID_PREFIX+empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = Security.hashPassword((String.valueOf(password)+this.empId).getBytes());
	}
	
	public Employee(String empId, String firstName, String lastName, String jobTitle, ArrayList<Messages> messageList, ArrayList<Complaint> complaintList, String email, int telephoneNumber) 
	{
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.messageList = messageList;
		this.complaintList = complaintList;
	}
	

	//Copy Constructor
	public Employee(Employee E) 
	{
		empId = E.empId;
		firstName = E.firstName;
		lastName = E.lastName;
		jobTitle = E.jobTitle;
		email = E.email;
		telephoneNumber = E.telephoneNumber;
		messageList = E.messageList;
		complaintList = E.complaintList;
	}
	
	//Getters and Setters
	public String getStaff_Id() {
		return empId;
	}

	public void setStaff_Id(String staff_Id) {
		this.empId = staff_Id;
	}

	public String getFirst_Name() {
		return firstName;
	}

	public void setFirst_Name(String first_Name) {
		this.firstName = first_Name;
	}

	public String getLast_Name() {
		return lastName;
	}

	public void setLast_Name(String last_Name) {
		this.lastName = last_Name;
	}

	public String getJob_Title() {
		return jobTitle;
	}
	
	public void setJobTitle(String type) {
		this.jobTitle = type;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public ArrayList<Messages> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Messages> messageList) {
		this.messageList = messageList;
	}

	public ArrayList<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(ArrayList<Complaint> complaintList) {
		this.complaintList = complaintList;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", jobTitle="
				+ jobTitle + ", email=" + email + ", telephoneNumber=" + telephoneNumber + ", messageList="
				+ messageList + ", complaintList=" + complaintList + ", password=" + password + "]";
	}
}
