package com.microstar.cablevision.server;

import java.awt.EventQueue; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.microstar.cablevision.security.Security;



public class SS {
	
	private static Map<String, Socket> clientColl = new ConcurrentHashMap<>(); // keeps the mapping of all the															// usernames used and their socket connections
	private static Set<String> activeUserSet = new HashSet<>(); // this set keeps track of all the active users 
	private JFrame frame; // jframe variable
	private ServerSocket ss; //server socket variable
	private JTextArea msgBox; // variable for server message board on UI
	private JList<String> allUserList;  // variable on UI
	private JList<String> activeList; // variable on UI
	private DefaultListModel<String> activeDlm = new DefaultListModel<String>(); // keeps list of active users for display on UI
	private DefaultListModel<String> allDlm = new DefaultListModel<String>(); // keeps list of all users for display on UI
	int count = 50;
	
	public static void main(String[] args) {  // functions starts here
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SS window = new SS();  // object creation
					window.frame.setVisible(true); // make jframe visible
				} catch (Exception e) {
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An Exception was caught in the main method in the SS class");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SS() {
		initialize();  // components of swing app will be initialized here.
		try {
			ss = new ServerSocket(8888);  // create a socket for server
			msgBox.append("Server started on port: " + 8888 + "\n"); // print messages to server message board
			msgBox.append("Waiting for the clients...\n");
			new ClientAccept().start(); // this will create a thread for client
		} catch (Exception e) {
			System.out.println("An error occurred in our chat server. Please try again later");	
			Security.logger.error("An Exception was caught in the default constructor in the SS class");
		}
	}

	class ClientAccept extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Socket clientSocket = ss.accept();  // create a socket for client
					String uName = new DataInputStream(clientSocket.getInputStream()).readUTF(); // this will receive the username sent from client register view
					DataOutputStream cOutStream = new DataOutputStream(clientSocket.getOutputStream()); // create an output stream for client
					if (activeUserSet != null && activeUserSet.contains(uName)) { // if username is in use then we need to prompt user to enter new name
						cOutStream.writeUTF("Username already taken");
					} else {
						clientColl.put(uName, clientSocket); // add new user to allUserList and activeUserSet
						activeUserSet.add(uName);
						cOutStream.writeUTF(""); // clear the existing message
						activeDlm.addElement(uName); // add this user to the active user JList
						if (!allDlm.contains(uName)) // if username taken previously then don't add to allUser JList otherwise add it
							allDlm.addElement(uName);
						activeList.setModel(activeDlm); // show the active and allUser List to the swing app in JList
						allUserList.setModel(allDlm);
						msgBox.append("Client " + uName + " Connected...\n"); // print message on server that new client has been connected.
						new MsgRead(clientSocket, uName).start(); // create a thread to read messages
						new PrepareCLientList().start(); //create a thread to update all the active clients
					}
				} catch (IOException ioex) {  // throw any exception occurs
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An IO Exception was caught in the run in the ClientAccept class");
				} catch (Exception e) {
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An Exception was caught in the run in the ClientAccept class");
				}
			}
		}
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
			while (allUserList != null && !clientColl.isEmpty()) {  // if allUserList is not empty then proceed further
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
						msgBox.append(Id + " disconnected....\n"); // print message on server message board

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
						activeDlm.removeElement(Id); // remove client from Jlist for server
						activeList.setModel(activeDlm); //update the active user list
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

	
	private void initialize() { //here components of Swing App UI are initilized
		frame = new JFrame();
		frame.setBounds(100, 100, 796, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Server");

		msgBox = new JTextArea();
		msgBox.setEditable(false);
		msgBox.setBounds(12, 29, 489, 435);
		frame.getContentPane().add(msgBox);
		msgBox.setText("Starting the Server...\n");

		allUserList = new JList<String>();
		allUserList.setBounds(526, 324, 218, 140);
		frame.getContentPane().add(allUserList);

		activeList = new JList<String>();
		activeList.setBounds(526, 78, 218, 156);
		frame.getContentPane().add(activeList);

		JLabel lblNewLabel = new JLabel("All Usernames");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(530, 295, 127, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Active Users");
		lblNewLabel_1.setBounds(526, 53, 98, 23);
		frame.getContentPane().add(lblNewLabel_1);

	}
}


