package microStarCableVision;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.microstar.cablevision.security.Authentication;
import java.util.ArrayList;
public class Client{

	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	private String state = " ";
	private int amount;
	
	

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void addComplaint(Complaint comp) {
		try {
			objOs.writeObject(comp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendUser(Authentication auth) {
		try {
			objOs.writeObject(auth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Customer getCustomer() {
		Customer customer = null;
		try {
			customer = (Customer)objIs.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
	public void sendCustomer(Customer customer) {
		try {
			objOs.writeObject(customer);
		}catch(IOException e){
			
		}
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList <Complaint> readCustomerComplaint() {
		ArrayList<Complaint> array = new ArrayList<Complaint>();
		try {
			 array=(ArrayList<Complaint>) objIs.readObject();
			 System.out.println(array);
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
	
	public Complaint getComplaint() {
		Complaint complaint = null;
		try {
			complaint = (Complaint)objIs.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public int numOfUsers() {
		 this.amount = 0 ;
		try {
			amount = (int)objIs.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUserType() {
		String userType = " ";
		try {
			userType = (String) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userType;
	}
	
	public Integer getloginValue() {
		Integer loginValue = 0;
		try {
			loginValue = (Integer) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginValue;
	}
	
	public Employee getEmployee() {
		Employee employee = null;
		try {
			employee = (Employee)objIs.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	public boolean SuccessStatus() {
		boolean flag = false;
		try {
			flag = (boolean) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
