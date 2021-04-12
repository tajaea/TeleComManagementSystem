package com.microstar.cablevision.database;
import com.microstar.cablevision.security.Authentication;


import microStarCableVision.Customer;
import microStarCableVision.Employee;
import microStarCableVision.Complaint;
import microStarCableVision.Responses;
//import microStarCableVision.Complaint;
public class CRUD {
	public static void main(String[] args) {
	// Database
		/*
	String url = "jdbc:mysql://localhost:3306/";
	Connection myConn = null;
	myConn=DriverManager.getConnection(url,"root","");
	Statement stmt = myConn.createStatement();
	stmt.execute("CREATE DATABASE IF NOT EXISTS approject");
	*/
	}//Customer
	public static String Customer() {
	return "CREATE TABLE IF NOT EXISTS ap.`customer` ("
			+ "	 `customer_ID` varchar(50) PRIMARY KEY NOT NULL,"
			+ " `first_name` varchar(20) NOT NULL,"
			+ " `last_name` varchar(20) NOT NULL,"
			+ " `email` varchar(30) NOT NULL,"
			+ " `phone_number` int NOT NULL,"
			+"`password` varchar (255) NOT NULL"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	}
	//Employee
	public static String Employee() {
	return "CREATE TABLE IF NOT EXISTS ap.`employee` ("
	+ "	 `employee_ID` varchar(50) PRIMARY KEY NOT NULL,"
	+ "  `first_name` varchar(20) NOT NULL,"
	+ "  `last_name` varchar(20) NOT NULL,"
	+ "  `email` varchar(30) NOT NULL,"
	+ "  `phone_number` int NOT NULL,"
	+ "  `password` varchar(255) NOT NULL,"
	+ "	 `type` varchar(20) NOT NULL"
	+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}
	// Status
	public static String Status(){
		return "CREATE TABLE IF NOT EXISTS ap.`status` ("
				+ "  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "	 `cust_id` varchar(11) NOT NULL,"
				+ "	 `amount_due` float(11) NOT NULL,"
				+ "	 `payment_due_date` date NOT NULL,"
				+ "	 FOREIGN KEY (cust_id) REFERENCES customer(customer_ID)"
				+ ") AUTO_INCREMENT = 1, ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}
	// Complaint
	public static String Complaint() {
		return "CREATE TABLE IF NOT EXISTS ap.`complaint` ("
				+ "	 `complaint_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "	 `cust_id` varchar(11) NOT NULL,"
				+ "	 `type` varchar(35) NOT NULL,"
				+ "	 `details` varchar(255) NOT NULL,"
				+ "	 `status` varchar (20) NOT NULL,"
				+ "	 `date` varchar(20) NOT NULL,"
				+ "	 `time` varchar(20) NOT NULL,"
				+ "	 FOREIGN KEY (cust_id) REFERENCES customer(customer_ID)"
				+ ") AUTO_INCREMENT = 1, ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}
	public static String createComplaint(Complaint complaint) {
		System.out.println("I am in CRUD");
		
		return "INSERT INTO ap.complaint (cust_id,type,details,status,date,time)"+
				"VALUES ('"+ complaint.getCustomerId() + "','" + complaint.getType() + "','" + complaint.getDetails()+"','"+
				"Outstanding"+"','"+complaint.getComplaintDate()+ "','"+complaint.getComplaintTime()+"');";
	}
	
	public static String getComplaint(Customer customer) {
		return "SELECT * FROM ap.complaint WHERE cust_id = "+"'"+customer.getCustomerID()+"';";
	}
	public static String viewSpecificComplaint(Complaint complaint) {
		return "SELECT * FROM ap.complaint WHERE complaint_id = "+"'"+complaint.getComplaintID()+"';";
	}
	public static String readAllComplaints() {
		return "SELECT * FROM ap.complaint";
	}
	
	//Response
	public static String Response() {
		return "CREATE TABLE IF NOT EXISTS ap.`response` ("
				+ "	 `response_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "  `complaint_id` int(11) NOT NULL,"
				+ "	 `response` varchar(255) NULL,"
				+ "	 `response_date` date NULL,"
				+ "	 `responded_by` varchar(30) NULL,"
				+ "	 `date_of_visit` date NULL,"
				+ "	 FOREIGN KEY (complaint_id) REFERENCES complaint(complaint_id)"
				+ ") AUTO_INCREMENT = 1, ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}
	public static String getResponse(Complaint complaint) {
		return "SELECT * FROM ap.complaint WHERE cust_id ="+"'"+complaint.getCustomerId();
	}
	
	public static String createResponse(Responses response) {
		return "INSERT INTO ap.response (cust_id,response,response_date,responded_by, date_of_visit)"+
				"VALUES ('" + response.getCustID() +"', '"+ response.getResponse()+"', '"+response.getResponseDate()+"', '"
				+response.getRespondedBy()+"', '"+ response.getDateOfVisit()+"');";
	}
	//Complaint Response
	public static String complaintResponse() {
		return "CREATE TABLE IF NOT EXISTS ap.`complaint_response` ("
				+ "	 `cr_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "	 `complaint_id` int(11) NOT NULL,"
				+ "	 `response_id` int (11) NOT NULL,"
				+ "	 FOREIGN KEY (complaint_id) REFERENCES complaint(complaint_id),"
				+ "	 FOREIGN KEY (response_id) REFERENCES response(response_id)"
				+ ") AUTO_INCREMENT = 1, ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}

	// Payments
	public static String Payments() {
		return "CREATE TABLE IF NOT EXISTS ap.`payment` ("
				+ "	 `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "	 `cust_id` varchar(11) NOT NULL,"
				+ "	 `amount_paid` float(11) NOT NULL,"
				+ "	 `balance_remaining` float(11) NOT NULL,"
				+ "	 `date_paid` date NOT NULL,"
				+ "	 `payment_method` varchar(50) NOT NULL,"
				+ "	 FOREIGN KEY (cust_id) REFERENCES customer(customer_ID)"
				+ ") AUTO_INCREMENT = 1, ENGINE=InnoDB DEFAULT CHARSET=latin1; ";
	}
	public static String createPayments() {
		return "INSERT INTO ap.payments (cust_id,amount_paid,balance_remaining,date_paid,payment_method)"+
				"VALUES ('"+"cust_ID"+"', '"+"12000.00"+"', '"+"0.00"+"', '"+"3/4/2021"+"', '"+"Paid with card ending in xxxxx9897"+"');";
	}

 public static String createTables() {
	// System.out.println(Customer()+Payments()+Employee()+Status()+Complaint()+Response()+complaintResponse());
	return Customer();
}
 
public static String createEmployeeTables() {
	return Employee();
}
 
 public static String createCustomer(Customer customer) {
	 return " INSERT INTO ap.customer(customer_ID, first_name, last_name, email, phone_number, password)"+
		 "VALUES ('" + customer.getCustomerID() + "','" + customer.getFirstName() + "','" 
		 			+ customer.getLastName() + "','" + customer.getEmail() + "','" 
		 			+ customer.getTelephoneNumber() + "','" + customer.getPassword() + "');";
}
 
public static String createEmployee(Employee employee) {
	return "INSERT INTO ap.employee(employee_ID, first_name, last_name, email, phone_number, password, type)"+
			"VALUES ('" + employee.getStaff_Id() + "', '" + employee.getFirst_Name() + "', '"
			+ employee.getLast_Name() + "', '" + employee.getEmail() + "', '"
			+ employee.getTelephoneNumber() + "', '" + employee.getPassword() + "', '" + employee.getJob_Title() + "');";
}

 public static String login_credentials(Authentication auth) {
	 return "SELECT * FROM ap.customer WHERE customer_ID = "+ "'"+auth.getUserid()+"'" + " AND password = "+ "'"+auth.getPassword()+"'";
	 
 }
 
 public static String empLogin_Credentials(Authentication auth) {
	 return "SELECT * FROM ap.employee WHERE employee_ID = "+ "'"+auth.getUserid()+"'" + " AND password = "+ "'"+auth.getPassword()+"'";
 }

}
