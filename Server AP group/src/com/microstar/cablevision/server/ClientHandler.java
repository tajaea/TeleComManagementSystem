package com.microstar.cablevision.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microstar.cablevision.database.CRUD;
import com.microstar.cablevision.security.Authentication;

import microStarCableVision.Complaint;
import microStarCableVision.Customer;
import microStarCableVision.Employee;
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
	private String onlinemsg;
	private String CUSTOMER = "CUS";
	private String EMPLOYEE = "EMP";
	
	

	public ClientHandler(Server server, Socket clientSocket) {
		this.server = server;
		this.connectionSocket = clientSocket;
	}

	@Override
	public void run() {
		System.out.println("run");
		String action = " ";
		getDatabaseConnection();

		try {
			this.configureStreams();
			while (true) {
				try {
					action = (String) objIs.readObject();
					System.out.println(action + " action");
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
						
					/*case "Get Employee":

						break;*/
						
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
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (ClassCastException cl) {
					cl.printStackTrace();
				}
				/*
				 * catch(EOFException ex) {
				 * System.out.println("Client has Ended Conection With Server"); break; }
				 * System.out.println("after conncetion close");
				 */
			}

		} catch (IOException e) {
			System.out.println("Client has Ended Conection With Server");
			// this.closeConnection();
		}

	}

	private void sendloginValue(Integer status) {
		try {
			objOs.writeObject(status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void returnCustomer(Customer cust) {
		try {
			objOs.writeObject(cust);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Employee function
	private void returnEmployee(Employee employee) {
		try {
			objOs.writeObject(employee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void send(String onlinemsg) {
		try {
			objOs.writeObject(onlinemsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			// create database
			// JOptionPane.showMessageDialog(null, "connection successful","CONNECTION
			// STATUS",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private ArrayList<Complaint> getCustomerComplaint(Customer customer) {
		ArrayList<Complaint> complaintlist= new ArrayList<Complaint>();
		ArrayList<Responses> responselist= new ArrayList<Responses>();		
		
		try (PreparedStatement pst=dBConn.prepareStatement(CRUD.readAllComplaints());
		ResultSet resultSet=state.executeQuery(CRUD.readAllComplaints())){
		
		while (resultSet.next()) {
			complaintlist.add(new Complaint(resultSet.getInt("complaint_id"), resultSet.getString("cust_id"), resultSet.getString("type"), resultSet.getString("details"),responselist,resultSet.getString("status"),resultSet.getString("date"),resultSet.getString("time")));
		}
		System.out.println(complaintlist.size()+"The size");
		
		//ViewComplaintView.getTable().setModel(DbUtils.resultSetToTableModel(result));
		}catch(Exception e){
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empObj;
	}
}
