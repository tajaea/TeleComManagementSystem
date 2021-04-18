package microStarCableVision;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microstar.cablevision.security.Security;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Customer attributes
	protected String customerID;
	private String firstName;
	private String lastName;
	private String email;
	private int telephoneNumber;
	private ArrayList<Messages> messageList;
	private ArrayList<Complaint> complainList;
	private String password;
	final String ID_PREFIX = "CUS";
	//private char issueType;
	//private  String details;
	
	//added sql connection variable.
	private java.sql.Connection connection;
	
	//Primary Constructor
	public Customer(String customerID, String firstName, String lastName, String email, int telephoneNumber,char[] password){

		this.customerID = ID_PREFIX+customerID; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = Security.hashPassword((String.valueOf(password)+this.customerID).getBytes());
		//this.issueType = issueType;
		//this.details = details;
	}
	
	public Customer(String customerID, String firstName, String lastName, String email, int telephoneNumber,ArrayList<Messages> messageList){

		this.customerID = customerID; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.messageList = messageList;
		this.complainList = new ArrayList<Complaint>();
		//this.issueType = issueType;
		//this.details = details;
	}
	
	

	//Default Constructor
	public Customer() {

		this.customerID = " ";
		this.firstName = " ";
		this.lastName =" ";
		this.email = " ";
		this.telephoneNumber = 0;
		this.messageList = new ArrayList<Messages>();
		this.complainList = new ArrayList<Complaint>();
		//this.issueType = '\0';
		//details = " ";
	}
	//Copy Constructor
	public Customer( Customer c) {


		c.customerID  = customerID;
		c.firstName = firstName;
		c.lastName = lastName;
		c.email = email;
		c.telephoneNumber = telephoneNumber;
		c.messageList = messageList;
		c.complainList = complainList;
		
		//c.issueType = issueType;
		//c.details = details;
	}
	
	//Mutators and accessors for the Customer class
	public Customer(String customerID)
	{

		this.customerID = customerID; 
		
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public ArrayList<Complaint> getComplainList() {
		return complainList;
	}

	public void setComplainList(ArrayList<Complaint> complainList) {
		this.complainList = complainList;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public char getissueType() {
		return issueType;
	}
	public void setissueType(char issueType) {
		this.issueType = issueType;
	}
	public String getdetails() {
		return details;
	}
	public void setdetails(String details) {
		this.details = details;
	}*/
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", telephoneNumber=" + telephoneNumber + ", password="+ password+"]";
	}
	
}