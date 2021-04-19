package com.microstar.cablevision.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.microstar.cablevision.security.Security;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Messages;

public class ChatView extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField sendField;
	private JList<String> activeUsersList;
	private JTextArea msgBox;
	private JButton exitchatBtn;
	DataInputStream din;
	DataOutputStream dout;
	DefaultListModel<String> dlm;
	private Customer cust;
	static Client client;
	String id, clientID = "";
	Read read;

	
	

	public ChatView() 
	{
		initialize();
	}

	public ChatView(String id,Client cli) 
	{ 
		initialize(); 
		this.id = id;
		try {
			setClient(cli);
			frame.setTitle("Live Chat ---- ChatID " + id); 
			dlm = new DefaultListModel<String>(); 
			activeUsersList.setModel(dlm);
			//din = new DataInputStream(cli.getConnectionSocket().getInputStream()); 
			//dout = new DataOutputStream(s.getOutputStream());
			read = new Read(cli);
			read.start(); 
		} catch (Exception ex) {
			System.out.println("An error occurred in our chat server. Please try again later");	
			Security.logger.error("An exception was caught in Chat View");
		}
	}

	class Read extends Thread 
	{
		boolean flag = true;
		private Client cli;
		public Read(Client cli) {
			this.cli = cli;
		}
		public void stopThread() {
			flag = false;
		}
		public void run() {
			while (flag) {
				try
				{
					String m = cli.readMessage(); 
					if (m.contains(":;.,/=")) 
					{ 
						m = m.substring(6); 
						dlm.clear(); 
						StringTokenizer st = new StringTokenizer(m, ",");
						while (st.hasMoreTokens())
						{
							String u = st.nextToken();
							if (!id.equals(u))
								dlm.addElement(u); 							
						}
					}
					else 
					{
						msgBox.append("" + m + "\n"); 
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An exception was caught in inner class Read in parent class ChatView");
					break;
				}
			}
		}
	}

	
	private void initialize() 
	{ 
		frame = new JFrame();
		frame.setBounds(400, 100, 636, 560);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setResizable(false);
		frame.setTitle("Live Chat");
		
		msgBox = new JTextArea();
		msgBox.setEditable(false);
		msgBox.setLineWrap(true);
		msgBox.setWrapStyleWord(true);
		msgBox.setBounds(5, 5, 347, 380);
		frame.getContentPane().add(msgBox);

		sendField = new JTextField();
		sendField.setHorizontalAlignment(SwingConstants.LEFT);
		sendField.setBounds(5, 400, 347, 84);
		frame.getContentPane().add(sendField);
		sendField.setColumns(10);

		JButton SendMsgBtn = new JButton("Send");
		SendMsgBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) 
			{
				String msg = sendField.getText(); 
				if (msg != null && !msg.isEmpty())
				{  
					try 
					{
						String msgToServer = "";
						int flag = 0; 
						String cast = "multicast"; 
						String receiver = activeUsersList.getSelectedValue(); 
						if (receiver.isEmpty()) {
							flag = 1;
						}
						else {
							clientID += receiver;
						}
							
						/*for (String selectedUsr : clientList) 
						{
							if (clientID.isEmpty())
								
							else
								clientID += "," + selectedUsr;
						}*/
						msgToServer = cast + ":" + clientID + ":" + msg; 
						
						if (cast.equalsIgnoreCase("multicast")) 
						{ 
							if (flag == 1) 
							{ 
								JOptionPane.showMessageDialog(frame, "No user selected");
							} else { 
								Messages message = new Messages(cust.getCustomerID(),clientID,msgToServer);
								//client.sendAction("Message");
								client.writeMessage(message);
								sendField.setText("");
								msgBox.append("< You sent msg to " + clientID + ">" + msg + "\n"); 
							}
						} 
						clientID = ""; 
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "User does not exist anymore."); 
						Security.logger.info("User does not exist anymore");

					}
				}
			}
		});
		SendMsgBtn.setBounds(380, 420, 89, 38);
		frame.getContentPane().add(SendMsgBtn);

		activeUsersList = new JList<String>();
		activeUsersList.setToolTipText("Active Users");
		activeUsersList.setBounds(390, 30, 200, 350);
		frame.getContentPane().add(activeUsersList);
		exitchatBtn = new JButton("Exit");
		exitchatBtn.addActionListener(new ActionListener() 
		{ 
			public void actionPerformed(ActionEvent e) {
				//try 
				//{
				client.sendAction("Message");
					client.writeMessage(new Messages("","","exit")); 
					msgBox.append("You are disconnected now.\n");
					read.stopThread();
					frame.dispose();
					new CustomerDashBoard(client).setCustomerObject(cust);
				}/* catch (IOException e1) {
					System.out.println("An error occurred in our chat server. Please try again later");	
					Security.logger.error("An Input/Output Exception was caught in the Action Listener in the ChatView class");
				}*/
			//}
		});
		exitchatBtn.setBounds(480,420, 89, 38);
		frame.getContentPane().add(exitchatBtn);

		JLabel lblNewLabel = new JLabel("Active Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(390, 5, 95, 16);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
		
	}

	public void setCustomer(Customer customerObj) {
		this.cust = customerObj;
		
	}
	public void setClient(Client cl) {
		client = cl;
	}
}
