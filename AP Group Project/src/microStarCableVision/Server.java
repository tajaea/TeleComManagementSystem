package microStarCableVision;

import java.io.*;

import java.net.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


import javax.swing.JOptionPane;

public class Server implements Serializable {

/*public class Server {


	private static final long serialVersionUID = 1L;
	
	//The ServerSocket in the Java Networking API is used to create a server socket 
	//that listens on a certain TCP port for incoming connections from a client,In
	//our case that port is 5000.
	private ServerSocket serverSocket;
	
	//Added sql connection variable
	//private java.sql.Connection connection;
	
	//ArrayList with a object of the ServerConnections Class called serverList.
	ArrayList<ServerConnections> serverList = new ArrayList<ServerConnections>();
	
	//Default Constructor
	public Server() 
	{
		//Added check to server to see if it has connected to the database
		//Commented it upon further discussion.
		/*String url = "jdbc:mysql://localhost:3306/ap_project";
		try
		{
			connection = DriverManager.getConnection(url, "root", "");
			
			if(connection != null) {
				//If Connection was made to the xampp server
				JOptionPane.showMessageDialog(null, "Connected to Local Server",
						"JBDC Connection Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			
			System.out.println("[Server] Started");
			
			System.out.println("\n[Server] Waiting on a Client to connect.......");
			
			//Connecting/Listening to the Port for a incoming connection from the Client.
			serverSocket = new ServerSocket(5000);
			
			//The program will run until the condition is false.
			while(true) 
			{
				//The accept() method of ServerSocket class is used to accept the 
				//incoming request to the socket.
				Socket socket = serverSocket.accept();
				
				System.out.println("\n[Server] Client has connected.");
				
				//The Primary Constructor in the ServerConnections class, accepts as parameters
				//a socket and and a object of the Server class itself because we have to
				//communicate between these classes.
				ServerConnections serverConnections = new ServerConnections(socket, this);
				
				////Because we are using multi-threading .start() is used to start a thread.
				serverConnections.start();
				
				//Adding the serverConnections to the ArrayList
				serverList.add(serverConnections);
			}
		//This exception is related to Input and Output operations in the Java code. 
		//This exception happens when there is a failure during reading, writing 
		//and searching file or directory operations.
		}catch(IOException ioe) 
		{
			System.err.println(ioe);
		}
	}*/
	
	private ServerSocket serverSocket = null;
	private Socket socket;
	boolean running = true;
	public Server() {
		try
		{				
			serverSocket = new ServerSocket(40);
			
			while(true)
			{			
				socket = serverSocket.accept();
				System.out.println("Passes socket");
				ClientHandler clients = new ClientHandler(this.socket);
				System.out.println("Passes Client");
				clients.start();
				System.out.println("Passes start");
				//clients.id = count;
				//count++;								
			}			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	class ClientHandler extends Thread implements Runnable
	{
		private Socket socket;		
		private DataInputStream inputStream;
		private DataOutputStream outputStream;	
		
		public ClientHandler(Socket socket)
		{
			super("ClientHandlerThread");
			this.socket = socket;
		}	
		
		public void run()
		{
			try
			{				
				inputStream = new DataInputStream(socket.getInputStream());
				outputStream = new DataOutputStream(socket.getOutputStream());			
					
				while(true)
				{						
					String id = inputStream.readUTF();
					String password = inputStream.readUTF();
					
					if(running)
					{							
						outputStream.writeUTF(password);
						outputStream.writeUTF(id);
						System.out.println("Client "+id+" connected from address: "+socket.getInetAddress().toString()+"Password: "+password);
					}				
				}					
			} catch (IOException e) {	
				
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) 
	{
		//Calling Object of the Server Class.
		new Server();
	}
}
