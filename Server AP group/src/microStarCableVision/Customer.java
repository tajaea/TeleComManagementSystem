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
import com.microstar.cablevision.security.Security;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Customer attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int ID;

	@Column(name = "customer_ID")
	private String customerID;

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

	@Transient
	final String ID_PREFIX = "CUS";

	// added sql connection variable.
	@Transient
	private java.sql.Connection connection;
	@Transient
	private ArrayList<Messages> messageList;
	@Transient
	private ArrayList<Complaint> complainList;

	// Primary Constructor
	public Customer(String customerID, String firstName, String lastName, String email, int telephoneNumber,
			char[] password) {

		this.customerID = ID_PREFIX + customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = Security.hashPassword((String.valueOf(password) + customerID).getBytes());
		// this.issueType = issueType;
		// this.details = details;
	}

	public Customer(String customerID, String firstName, String lastName, String email, int telephoneNumber,
			ArrayList<Messages> messageList, ArrayList<Complaint> complainList) {

		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.messageList = messageList;
		this.complainList = complainList;
		// this.issueType = issueType;
		// this.details = details;
	}

	// Default Constructor
	public Customer() {

		this.customerID = " ";
		this.firstName = " ";
		this.lastName = " ";
		this.email = " ";
		this.telephoneNumber = 0;
		this.messageList = new ArrayList<Messages>();
		this.complainList = new ArrayList<Complaint>();
		// this.issueType = '\0';
		// details = " ";
	}

	// Copy Constructor
	public Customer(Customer c) {

		c.customerID = customerID;
		c.firstName = firstName;
		c.lastName = lastName;
		c.email = email;
		c.telephoneNumber = telephoneNumber;
		c.messageList = messageList;
		c.complainList = complainList;

		// c.issueType = issueType;
		// c.details = details;
	}

	// Mutators and accessors for the Customer class
	public Customer(String customerID) {

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

	/*
	 * public char getissueType() { return issueType; } public void
	 * setissueType(char issueType) { this.issueType = issueType; } public String
	 * getdetails() { return details; } public void setdetails(String details) {
	 * this.details = details; }
	 */
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", telephoneNumber=" + telephoneNumber + ", password=" + password + "]";
	}
	
	public boolean create() {
		try {
			Session session = CustomerSessionFactoryBuilder.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			System.out.println("Customer Record successfully created");
			Security.logger.info("Customer Record successfully created");
			session.close();
			return true;
		} catch (Exception e) {
			Security.logger.fatal("Could not create Customer using ORM");
			return false;
		}

	}

}
