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
	private static Map<String, Socket> clientColl = new ConcurrentHashMap<>(); // keeps the mapping of all the															// usernames used and their socket connections
	private static Set<String> activeEmpSet = new HashSet<>(); // this set keeps track of all the active users 
	private static Set<String> activeCusSet = new HashSet<>();
	int count = 50;
	 List<ClientHandler> clientlist;
	

	public ClientHandler(Server server, Socket clientSocket) {
		this.server = server;
		this.connectionSocket = clientSocket;
	}

	@Override
	public void run() {
		System.out.println("run");
		Security.logger.info("run");
		String action = " ";
		getDatabaseConnection();

		try {
			this.configureStreams();
			while (true) {
				try {
					action = (String) objIs.readObject();
					System.out.println(action + " action");
					Security.logger.info(action + " action");
					switch (action) {
					case "Add Customer":
						customerobj = (Customer) objIs.readObject();
						System.out.println(customerobj.getCustomerID() + " action");
						addCustomer(customerobj);
						break;

					// Added Employee case
					case "Add Employee":
						employeeObj = (Employee) objIs.readObject();
						System.out.println(employeeObj.getStaff_Id() + " action");
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
								activeCusSet.add(customerobj.getCustomerID());
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
								activeEmpSet.add(employeeObj.getStaff_Id());
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
						break;
						
					case "Message":
						Messages message = (Messages) objIs.readObject();
						System.out.println(message);
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
			System.out.println("Client has Ended Conection With Server");
			Security.logger.error("An Input/Output Exception was caught in the run method in the ClientHandler class");			
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
		String sql = CRUD.createCustomer(customer);
		try {
			state = dBConn.createStatement();
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

	// Employee function
	private void addEmployee(Employee employee) {
		String sql = CRUD.createEmployee(employee);
		try {
			state = dBConn.createStatement();
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
				cusobj.setCustomerID(result.getString(1));
				cusobj.setFirstName(result.getString(2));
				cusobj.setLastName(result.getString(3));
				cusobj.setEmail(result.getString(4));
				cusobj.setTelephoneNumber(result.getInt(5));
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
				empObj.setStaff_Id(result.getString(1));
				empObj.setFirst_Name(result.getString(2));
				empObj.setLast_Name(result.getString(3));
				empObj.setEmail(result.getString(4));
				empObj.setTelephoneNumber(result.getInt(5));
				empObj.setJobTitle(result.getString(7));

			}
		} catch (SQLException e) {
			System.out.println("An error occurred in our database connection. Please try again later");	
			Security.logger.error("A SQL Exception was caught in the findEmployeeUserById method in the ClientHandler class");
		}
		return empObj;
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
			while (!clientColl.isEmpty()) {  // if allUserList is not empty then proceed further
				try {
					String message = new DataInputStream(s.getInputStream()).readUTF(); // read message from client
					System.out.println("message read ==> " + message); // just print the message for testing
					String[] msgList = message.split(":"); // I have used my own identifier to identify what action to take on the received message from client
														// i have appended actionToBeTaken:clients_for_receiving_msg:message
					
					if (msgList[0].equalsIgnoreCase("multicast")) { // if action is multicast then send messages to selected active users
						String[] sendToList = msgList[1].split(","); //this variable contains list of clients which will receive message
						for (String usr : sendToList) { // for every user send message
							try {
								if (activeUserSet.contains(usr)) { // check again if user is active then send the message
									new DataOutputStream(clientColl.get(usr).getOutputStream())
											.writeUTF("< " + Id + " >" + msgList[2]); // put message in output stream
								}
							} catch (Exception e) { // throw exceptions
								System.out.println("An error occurred in our chat server. Please try again later");	
								Security.logger.error("An Exception was caught in the run method in the MsgRead class");
							}
						}
					} else if (msgList[0].equalsIgnoreCase("exit")) { // if a client's process is killed then notify other clients
						activeUserSet.remove(Id); // remove that client from active usre set
						//msgBox.append(Id + " disconnected....\n"); // print message on server message board

						new PrepareCLientList().start(); // update the active and all user list on UI

						Iterator<String> itr = activeUserSet.iterator(); // iterate over other active users
						while (itr.hasNext()) {
							String usrName2 = itr.next();
							if (!usrName2.equalsIgnoreCase(Id)) { // we don't need to send this message to ourself
								try {
									new DataOutputStream(clientColl.get(usrName2).getOutputStream())
											.writeUTF(Id + " disconnected..."); // notify all other active user for disconnection of a user
								} catch (Exception e) { // throw errors
									e.printStackTrace();
								}
								new PrepareCLientList().start(); // update the active user list for every client after a user is disconnected
							}
						}
						//activeDlm.removeElement(Id); // remove client from Jlist for server
						//activeList.setModel(activeDlm); //update the active user list
					}
				} catch (Exception e) {
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An Exception was caught in the run in the MsgRead class");
				}
			}
		}
	}

	class PrepareCLientList extends Thread { // it prepares the list of active user to be displayed on the UI
		@Override
		public void run() {
			try {
				clientlist =  server.getClientList();
				String ids = "";
				Iterator<String> itr = activeUserSet.iterator(); // iterate over all active users
				while (itr.hasNext()) { // prepare string of all the users
					String key = itr.next();
					ids += key + ",";
				}
				if (ids.length() != 0) { // just trimming the list for the safe side.
					ids = ids.substring(0, ids.length() - 1);
				}
				itr = activeUserSet.iterator(); 
				while (itr.hasNext()) { // iterate over all active users
					String key = itr.next();
					try {
						new DataOutputStream(clientColl.get(key).getOutputStream())
								.writeUTF(":;.,/=" + ids); // set output stream and send the list of active users with identifier prefix :;.,/=
					} catch (Exception e) {
						System.out.println("An error occurred in our chat server. Please try again later");	
						Security.logger.error("An Exception was caught in the run in the PrepareCLientList class");
					}
				}
			} catch (Exception e) {
				System.out.println("An error occurred in our chat server. Please try again later");	
				Security.logger.error("An Exception was caught in the run in the PrepareCLientList class");
			}
		}
	}
}
