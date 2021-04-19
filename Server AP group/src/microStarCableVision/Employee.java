package microStarCableVision;

import java.io.Serializable;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.microstar.cablevision.database.CustomerSessionFactoryBuilder;
import com.microstar.cablevision.database.EmployeeSessionFactoryBuilder;
import com.microstar.cablevision.security.Security;
@Entity
@Table(name = "employee")
public class Employee implements Serializable //Creating the Parent Class as Employee

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Creating the General/Main attributes that all Employees would share.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "employee_ID")
	private String empId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private int telephoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private String jobTitle;
	
	@Transient
	private ArrayList<Messages> messageList;
	@Transient
	private ArrayList<Complaint> complaintList;
	@Transient
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
		public boolean create() {
			try {
				Session session = EmployeeSessionFactoryBuilder.getSessionFactory().getCurrentSession();
				Transaction transaction = session.beginTransaction();
				session.save(this);
				transaction.commit();
				System.out.println("Employee Record successfully created");
				Security.logger.info("Employee Record successfully created");
				session.close();
				return true;
			} catch (Exception e) {
				Security.logger.fatal("Could not create Employee using ORM");
				return false;
			}
	}

}
