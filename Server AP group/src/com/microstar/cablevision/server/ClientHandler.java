package com.microstar.cablevision.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.microstar.cablevision.database.CRUD;
import com.microstar.cablevision.security.Authentication;
import com.microstar.cablevision.security.Security;
import com.microstar.cablevision.server.SS.PrepareCLientList;
import com.microstar.cablevision.server.SS;

import microStarCableVision.Complaint;
import microStarCableVision.Customer;
import microStarCableVision.Employee;
import microStarCableVision.Messages;
import microStarCableVision.Responses;

public class ClientHandler extends Thread implements Runnable {
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dBConn = null;
	private Statement state;
	private final Server server;
	private Customer customerobj = null;
	private Employee employeeObj = null;
	private Complaint complaintObj=null;
	private String CUSTOMER = "CUS";
	private String EMPLOYEE = "EMP";										
	int count = 50;
	 List<ClientHandler> clientlist;
	 private String chatID;
	 boolean flag = true;
	 boolean flag2 = false;
	

	public ClientHandler(Server server, Socket clientSocket) {
		this.server = server;
		this.connectionSocket = clientSocket;
	}

	@Override
	public void run() {
		String action = " ";
		getDatabaseConnection();
		
		Security.logger.info("Successfully Connected to Server");
		try {
			this.configureStreams();
			while (true) {
				try {
					if(flag2==false) {
						action = (String) objIs.readObject();
					}
					Security.logger.info(action + " action");
					switch (action) {
					case "Add Customer":
						customerobj = (Customer) objIs.readObject();
						//System.out.println(customerobj.getCustomerID() + " action");
						addCustomer(customerobj);
						break;

					// Added Employee case
					case "Add Employee":
						employeeObj = (Employee) objIs.readObject();
						//System.out.println(employeeObj.getStaff_Id() + " action");
						addEmployee(employeeObj);
						break;
						
					case "Add Complaint":
						complaintObj = (Complaint) objIs.readObject();
						addComplaint(complaintObj);
						break;
					case "Get Complaint":
						customerobj = (Customer) objIs.readObject();
						ArrayList<Complaint>complaint=getCustomerComplaint(customerobj);
						objOs.writeObject(complaint);
						break;
				
					/*Added new Actions that came from the ViewAllComplaintRep Controller*/
					case "Get NSComplaint":
						String nsType = (String) objIs.readObject();
						ArrayList<Complaint> nsComplaint = getCustomerComplaintForEmployee(nsType);
						objOs.writeObject(nsComplaint);
						break;
						
					case "Get BCComplaint":
						String bcType = (String) objIs.readObject();
						ArrayList<Complaint> bcComplaint = getCustomerComplaintForEmployee(bcType);
						objOs.writeObject(bcComplaint);
						break;
						
					case "Get PDComplaint":
						String pdType = (String) objIs.readObject();
						ArrayList<Complaint> pdComplaint = getCustomerComplaintForEmployee(pdType);
						objOs.writeObject(pdComplaint);
						break;
						
					case "Search Complaint":
						int id = (int) objIs.readObject();
						ArrayList<Complaint> searchComplaintList = searchComplaintForEmployee(id);
						objOs.writeObject(searchComplaintList);
						break;
						
					case "User Login":
						Authentication authe = (Authentication) objIs.readObject();
						if (authe.getUserid().substring(0, 3).equals(CUSTOMER)) {
							objOs.writeObject(CUSTOMER);
							customerobj = findUserById(authe);
							if (customerobj.getCustomerID().isBlank()) {
								sendloginValue(00);
							} else {
								sendloginValue(80);
								returnCustomer(customerobj);
								chatID = customerobj.getCustomerID();
							}
						}
						// Employee section
						else if (authe.getUserid().substring(0, 3).equals(EMPLOYEE)) {
							objOs.writeObject(EMPLOYEE);
							employeeObj = findEmployeeUserById(authe);

							if (employeeObj.getStaff_Id().isBlank()) {
								sendloginValue(00);
							} else {
								sendloginValue(80);
								returnEmployee(employeeObj);
								chatID = employeeObj.getStaff_Id();
							}
						} else {
							objOs.writeObject("UNKNOWN");
							sendloginValue(00);
						}
						// objOs.writeObject(customerobj);
						/*
						 * String message =
						 * customerobj.getFirstName()+" "+customerobj.getLastName()+" is online";
						 * this.onlinemsg = message; List<ClientHandler> clientlist =
						 * server.getClientList();
						 * 
						 * // Send current user state to all online login
						 * 
						 * this.objOs.writeObject(server.getClientList().size()); for(ClientHandler
						 * clienthandler:clientlist) {
						 * if(!clienthandler.customerobj.getCustomerID().equals(customerobj.
						 * getCustomerID())) { clienthandler.send(onlinemsg); } }
						 */
						clientlist =  server.getClientList();
						
						break;
						
					case "Message":
						//message = (Messages) objIs.readObject();
						//System.out.println("My "+message);
						break;
						
					case "chat":
						String userID = (String) objIs.readObject();
						new PrepareCLientList().start();
						new MsgRead(connectionSocket,userID).start();
						flag= false;
						break;
					}
				} catch (ClassNotFoundException e) {
					System.out.println("An error occurred in our server. Please try again later");	
					Security.logger.error("A ClassNotFound Exception was caught in the run method in the ClientHandler class");	
				} catch (ClassCastException cl) {
					System.out.println("An error occurred in our server. Please try again later");	
					Security.logger.error("A ClassCast Exception was caught in the run method in the ClientHandler class");	
				}
				/*
				 * catch(EOFException ex) {
				 * System.out.println("Client has Ended Conection With Server"); break; }
				 * System.out.println("after conncetion close");
				 */
			}

		} catch (IOException e) {
			Security.logger.error("Client has Ended Connection With Server");			
			this.closeConnection();
		}
	}

	private void sendloginValue(Integer status) {
		try {
			objOs.writeObject(status);
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the sendloginValue method in the ClientHandler class");
			}
		}
	

	private void returnCustomer(Customer cust) {
		try {
			objOs.writeObject(cust);
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the returnCustomer method in the ClientHandler class");
		}
	}

	// Employee function
	private void returnEmployee(Employee employee) {
		try {
			objOs.writeObject(employee);
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the returnEmployee method in the ClientHandler class");
		}
	}

	private void send(String onlinemsg) {
		try {
			objOs.writeObject(onlinemsg);
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the send method in the ClientHandler class");
		}
	}

	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the configureStreams method in the ClientHandler class");
		}

	}

	// private static Connection getDatabaseConnection() {
	private static void getDatabaseConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			dBConn = DriverManager.getConnection(url, "root", "");
			if (dBConn != null) {
				Statement stmt = dBConn.createStatement();
				if (stmt.execute("CREATE DATABASE IF NOT EXISTS ap")) {
					stmt.execute(CRUD.Customer());
					stmt.execute(CRUD.Payments());
					stmt.execute(CRUD.Employee());
					stmt.execute(CRUD.Status());
					stmt.execute(CRUD.Complaint());
					stmt.execute(CRUD.Response());
					stmt.execute(CRUD.complaintResponse());
				} else {
					stmt.execute(CRUD.Customer());
					stmt.execute(CRUD.Payments());
					stmt.execute(CRUD.Employee());
					stmt.execute(CRUD.Status());
					stmt.execute(CRUD.Complaint());
					stmt.execute(CRUD.Response());
					stmt.execute(CRUD.complaintResponse());
				}
			}
		} catch (SQLException e) {
			System.out.println("An error occurred in our databse connection. Please try again later");	
			Security.logger.error("A SQL Exception was caught in the getDatabaseConnection method in the ClientHandler class");
		}
	}

	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the closeConnection method in the ClientHandler class");
		}

	}

	private void addCustomer(Customer customer) {
		try {
			if(customer.create()) {
				objOs.writeObject(true);
			}else {
				objOs.writeObject(false);
			}
		} catch (IOException e) {
			System.out.println("An error occurred while trying to create your account. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the addCustomer method of the Client class");
		}
	}

	// Employee function
	private void addEmployee(Employee employee) {
		try {
		if(employee.create()) {
			objOs.writeObject(true);
		}else {
			objOs.writeObject(false);
		}
		} catch (IOException e) {
			System.out.println("An error occurred while trying to create your account. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the addEmployee method of the ClientHandler class");
		}
	}

	private void addComplaint(Complaint complaint) {
		String sql = CRUD.createComplaint(complaint);
		
		try {
			if ((state.executeUpdate(sql) == 1)) {
				System.out.println("check 1");
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (SQLException e) {
			System.out.println("An error occurred in our database connection. Please try again later");	
			Security.logger.error("A SQL Exception was caught in the getDatabaseConnection method in the ClientHandler class");
		} catch (IOException e) {
			System.out.println("An error occurred in our server. Please try again later");	
			Security.logger.error("An Input/Output Exception was caught in the closeConnection method in the ClientHandler class");
		}
	}
	
	private ArrayList<Complaint> getCustomerComplaint(Customer customer) {
		ArrayList<Complaint> complaintlist= new ArrayList<Complaint>();
		ArrayList<Responses> responselist= new ArrayList<Responses>();		
		
		try {
		ResultSet resultSet=state.executeQuery(CRUD.getComplaint(customer));
		
		while (resultSet.next()) {
			complaintlist.add(new Complaint(resultSet.getInt("complaint_id"), resultSet.getString("cust_id"), resultSet.getString("type"), resultSet.getString("details"),responselist,resultSet.getString("status"),resultSet.getString("date"),resultSet.getString("time")));
		}
		}catch(Exception e){
			System.out.println("An error occurred in our database. Please try again later");	
			Security.logger.error("An exception was caught in the getCustomerComplaint method in the ClientHandler class");
		}
		return complaintlist;
	}
	
	private ArrayList<Complaint> getCustomerComplaintForEmployee(String type) {
		ArrayList<Complaint> complaintlist= new ArrayList<Complaint>();
		ArrayList<Responses> responselist= new ArrayList<Responses>();		
		
		try {
		ResultSet resultSet=state.executeQuery(CRUD.viewComplaintByType(type));
		
		while (resultSet.next()) {
			complaintlist.add(new Complaint(resultSet.getInt("complaint_id"), resultSet.getString("cust_id"), resultSet.getString("type"), resultSet.getString("details"),responselist,resultSet.getString("status"),resultSet.getString("date"),resultSet.getString("time")));
		}
		}catch(Exception e){
			System.out.println("An error occurred in our database. Please try again later");	
			Security.logger.error("An exception was caught in the getCustomerComplaintForEmployee method in the ClientHandler class");
		}
		//System.out.println(complaintlist.get(0).getDetails()+"Client Handler");
		return complaintlist;
	}
	
	private ArrayList<Complaint> searchComplaintForEmployee(int id) {
		// TODO Auto-generated method stub
		ArrayList<Complaint> complaintlist= new ArrayList<Complaint>();
		ArrayList<Responses> responselist= new ArrayList<Responses>();		

		try {
		ResultSet resultSet=state.executeQuery(CRUD.searchComplaintByID(id));

		while (resultSet.next()) {
			complaintlist.add(new Complaint(resultSet.getInt("complaint_id"), resultSet.getString("cust_id"), resultSet.getString("type"), resultSet.getString("details"),responselist,resultSet.getString("status"),resultSet.getString("date"),resultSet.getString("time")));
		}
		}catch(Exception e){
			System.out.println("An error occurred in our database. Please try again later");	
			Security.logger.error("An exception was caught in the getCustomerComplaintForEmployee method in the ClientHandler class");
		}
		//System.out.println(complaintlist.get(0).getDetails()+"Client Handler");
		return complaintlist;
		}
	
	private Customer findUserById(Authentication auth) {
		Customer cusobj = new Customer();
		String query = CRUD.login_credentials(auth);
		try {
			state = dBConn.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				cusobj.setCustomerID(result.getString("customer_ID"));
				cusobj.setFirstName(result.getString("first_name"));
				cusobj.setLastName(result.getString("last_name"));
				cusobj.setEmail(result.getString("email"));
				cusobj.setTelephoneNumber(result.getInt("phone_number"));
			}
		} catch (SQLException e) {
			System.out.println("An error occurred in our database connection. Please try again later");	
			Security.logger.error("A SQL Exception was caught in the findUserById method in the ClientHandler class");
		}
		return cusobj;
	}

	// Employee function
	private Employee findEmployeeUserById(Authentication auth) {
		Employee empObj = new Employee();
		String query = CRUD.empLogin_Credentials(auth);
		try {
			state = dBConn.createStatement();
			ResultSet result = state.executeQuery(query);
			if (result.next()) {
				empObj.setStaff_Id(result.getString("employee_ID"));
				empObj.setFirst_Name(result.getString("first_name"));
				empObj.setLast_Name(result.getString("last_name"));
				empObj.setEmail(result.getString("email"));
				empObj.setTelephoneNumber(result.getInt("phone_number"));
				empObj.setJobTitle(result.getString("type"));
			}
		} catch (SQLException e) {
			System.out.println("An error occurred in our database connection. Please try again later");	
			Security.logger.error("A SQL Exception was caught in the findEmployeeUserById method in the ClientHandler class");
		}
		return empObj;
	}
	
	public String getChatID() {
		return this.chatID;
	}
	

	class MsgRead extends Thread { // this class reads the messages coming from client and take appropriate actions
		Socket s;
		String Id;
		private MsgRead(Socket s, String uname) { // socket and username will be provided by client
			this.s = s;
			this.Id = uname;
		}

		@Override
		public void run() {
			while (!clientlist.isEmpty()&&flag==false) {  // if allUserList is not empty then proceed further
				try {
					System.out.println("read message");
					//Messages messageobj = (Messages) (new ObjectInputStream(connectionSocket.getInputStream()).readObject()); // read message from client
					Messages messageobj = (Messages) objIs.readObject(); // read message from client
					String message = messageobj.getMessageBody();
					System.out.println("message read ==> " + message); // just print the message for testing
					String[] msgList = message.split(":"); // I have used my own identifier to identify what action to take on the received message from client
														// i have appended actionToBeTaken:clients_for_receiving_msg:message
					
					if (msgList[0].equalsIgnoreCase("multicast")) { // if action is multicast then send messages to selected active users
						String sendToList = msgList[1]; //this variable contains list of clients which will receive message
						for (ClientHandler cli: clientlist) { // for every user send message
							try {
								if (sendToList.equalsIgnoreCase(cli.getChatID())) { // check again if user is active then send the message
									//new ObjectOutputStream(clientColl.get(sendToList).getOutputStream()).writeObject("< " + Id + " >" + msgList[2]);
										cli.send("< " + Id + " >" + msgList[2]);	
									System.out.println("pp");
									//cli.send("< " + Id + " >" + msgList[2]); // put message in output stream
								}
							} catch (Exception e) { // throw exceptions
								e.printStackTrace();
								System.out.println("An error occurred in our chat server. Please try again later");	
								Security.logger.error("An Exception was caught in the run method in the MsgRead class");
							}
						}
					} else if (msgList[0].equalsIgnoreCase("exit")) { 
						
						flag = true;
						flag2 = false;
						objOs.writeObject(" ");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("An error occurred in our chat serverer. Please try again later");	
					Security.logger.error("An Exception was caught in the run in the MsgRead class");
				}
			}
		}
	}

	class PrepareCLientList extends Thread { // it prepares the list of active user to be displayed on the UI
		@Override
		public void run() {
			try {
				flag2 = true;
				String ids = "";
				for(ClientHandler handle:clientlist) {
					System.out.println("CHat" +chatID.subSequence(0, 3).toString());
					if(chatID.subSequence(0, 3).toString().equalsIgnoreCase(CUSTOMER)) {
						String key = handle.getChatID();
						System.out.println("The key"+ key);
						if(key.subSequence(0, 3).toString().equalsIgnoreCase(EMPLOYEE)) {
							ids += key +",";
						}
					}
					else if(chatID.subSequence(0, 3).toString().equalsIgnoreCase(EMPLOYEE)) {
						String key = handle.getChatID();
						if(key.subSequence(0, 3).toString().equalsIgnoreCase(CUSTOMER)) {
						ids += key +",";
						}
					}
				}
				
				if (ids.length() != 0) { // just trimming the list for the safe side.
					ids = ids.substring(0, ids.length() - 1);
				}

				for(ClientHandler handler:clientlist) {
					
					//if(chatID.subSequence(0, 3).toString().equalsIgnoreCase(CUSTOMER)) {
						//if(handler.getChatID().subSequence(0, 3).toString().equalsIgnoreCase(EMPLOYEE)) {
							handler.send(":;.,/=" +ids);
						//}
					//}
					//else if(chatID.subSequence(0, 3).toString().equalsIgnoreCase(EMPLOYEE)) {
						//if(handler.getChatID().subSequence(0, 3).toString().equalsIgnoreCase(CUSTOMER)) {
							//handler.send(":;.,/=" +ids);
						//}
				    //}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("An error occurred in our chat server. Please try again later");	
				Security.logger.error("An Exception was caught in the run in the PrepareCLientList class");
			}
		}
	}
}
