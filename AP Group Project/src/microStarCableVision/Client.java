package microStarCableVision;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.microstar.cablevision.security.Authentication;
import com.microstar.cablevision.security.Security;

import java.util.ArrayList;
public class Client{

	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	private String complaintType = "";
	private String state = " ";
	private int amount;
	private int complaintID = 0;
	

	public Socket getConnectionSocket() {
		return connectionSocket;
	}

	public void setConnectionSocket(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	public Client() {
		this.createConnection();
		this.configureStreams();
	}
	
	/**
	 * Configuring of streams
	 */
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the configureStreams method of the Client class");
		}
	}
		
	/**
	 * Refreshes client's connection server after each action
	 */
	public void refreshSocket() {
	 createConnection();
	 configureStreams();
	}
	
	/**
	 * Establishes a new Socket instance
	 */
	private void createConnection() {
		try {
			connectionSocket = new Socket("127.0.0.1",8888);
			System.out.println("port"+connectionSocket.getPort());
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the createConnection method of the Client class");
		}
	}
	
	/**
	 * Closes client server connection
	 */
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the closeConnection method of the Client class");
		}
		
	}
	/**
	 * writes action to server
	 * @param action
	 */
	public void sendAction(String action) {
		try {
			this.action = action;
			objOs.writeObject(this.action);
		
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the sendAction method of the Client class");
		}
	}
	
	/**
	 * Writes customer object to server
	 * @param cust
	 */
	public void addCustomer(Customer cust) {
		try {
			objOs.writeObject(cust);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the addCustomer method of the Client class");
		}
	}

	
	public void addComplaint(Complaint comp) {
		try {
			objOs.writeObject(comp);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the addComplaint method of the Client class");
		}
	}
	
	public void sendResponse(Responses responseObj) {
		try {
			objOs.writeObject(responseObj);
		}catch(IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the sendResponse method of the Client class");
		}	
	}
	
	public void sendUser(Authentication auth) {
		try {
			objOs.writeObject(auth);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the sendUser method of the Client class");
		}
	}
	
	public Customer getCustomer() {
		Customer customer = null;
		try {
			customer = (Customer)objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the getCustomer method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the getCustomer method of the Client class");
		}
		return customer;
	}
	
	public void sendCustomer(Customer customer) {
		try {
			objOs.writeObject(customer);
		}catch(IOException e){
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the sendCustomer method of the Client class");
		}
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList <Complaint> readCustomerComplaint() {
		ArrayList<Complaint> array = new ArrayList<Complaint>();
		try {
			 array=(ArrayList<Complaint>) objIs.readObject();
			 System.out.println(array);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the readCustomerComplaint method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the readCustomerComplaint method of the Client class");
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList <Complaint> readComplaints() {
		ArrayList<Complaint> array = new ArrayList<Complaint>();
		try {
			 array=(ArrayList<Complaint>) objIs.readObject();
			 System.out.println(array);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the readComplaints method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the readComplaints method of the Client class");
		}
		
		return array;
	}
	
	public Complaint getComplaint() {
		Complaint complaint = null;
		try {
			complaint = (Complaint)objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the getComplaint method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the getComplaint method of the Client class");
		}
		return complaint;
	}
	public String UsersOnlineState() {
		try {
			state = (String)objIs.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public String readMessage() {
		String message = " ";
		try {
			message = (String)objIs.readObject();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public void writeMessage(Messages message) {
		try {
			objOs.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeUserChatID(String userID) {
		try {
			objOs.writeObject(userID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int numOfUsers() {
		 this.amount = 0 ;
		try {
			amount = (int)objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the numOfUsers method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the numOfUsers method of the Client class");
		}
		return amount;
	}
	
	/*public void receiveResponse() {
		try {
			if(action.equalsIgnoreCase("Add Customer")){
				boolean flag = (boolean) objIs.readObject();
				if(flag==true) {
					JOptionPane.showMessageDialog(null,"Added Successfully","Record Status",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			if(action.equalsIgnoreCase("Get Customer")) {
				//Customer customer = new Customer();
				Customer customer = (Customer)objIs.readObject();
				if(customer.customerID.isBlank()) {
					JOptionPane.showMessageDialog(null,"Record not found","Record Status",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					System.out.println(customer);
				}
			}
		}
		catch(ClassCastException | ClassNotFoundException | IOException cl) {
			cl.printStackTrace();
		}
	}*/
	
	public void addEmployee(Employee emp) {
		try {
			objOs.writeObject(emp);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the addEmployee method of the Client class");
		}
	}
	
	public String getUserType() {
		String userType = " ";
		try {
			userType = (String) objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the getUserType method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the getUserType method of the Client class");
		}
		return userType;
	}
	
	public Integer getloginValue() {
		Integer loginValue = 0;
		try {
			loginValue = (Integer) objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the getloginValue method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the getloginValue method of the Client class");
		}
		
		return loginValue;
	}
	
	public Employee getEmployee() {
		Employee employee = null;
		try {
			employee = (Employee)objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the getEmployee method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the getEmployee method of the Client class");
		}
		
		return employee;
	}
	
	public boolean SuccessStatus() {
		boolean flag = false;
		try {
			flag = (boolean) objIs.readObject();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the SuccessStatus method of the Client class");
		} catch (ClassNotFoundException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("A ClassNotFound Exception was caught in the SuccessStatus method of the Client class");
		}
		return flag;
	}

	public void sendComplaintType(String type) {
		try {
			this.complaintType = type;
			objOs.writeObject(this.complaintType);
		} catch (IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the sendComplaintType method of the Client class");
		}
		
	}
	
	public void searchComplaintID(int id) {
		// TODO Auto-generated method stub
		try {
			this.complaintID = id;
			objOs.writeObject(this.complaintID);
		}catch(IOException e) {
			System.out.println("An error occurred while trying to connect to the server. Please try again later");
			Security.logger.error("An Input/Output Exception was caught in the searchComplaintID method of the Client class");
		}
	}
}
