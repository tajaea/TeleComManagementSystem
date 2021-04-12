package com.microstar.cablevision.views;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
	String id, clientID = "";

	
	

	public ChatView() 
	{
		initialize();
	}

	public ChatView(String id, Socket s) 
	{ 
		initialize(); 
		this.id = id;
		try {
			frame.setTitle("Live Chat ---- ChatID " + id); 
			dlm = new DefaultListModel<String>(); 
			activeUsersList.setModel(dlm);
			din = new DataInputStream(s.getInputStream()); 
			dout = new DataOutputStream(s.getOutputStream());
			new Read().start(); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	class Read extends Thread 
	{

		public void run() {
			while (true) {
				try
				{
					String m = din.readUTF(); 
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
						List<String> clientList = activeUsersList.getSelectedValuesList(); 
						if (clientList.size() == 0) 
							flag = 1;
						for (String selectedUsr : clientList) 
						{
							if (clientID.isEmpty())
								clientID += selectedUsr;
							else
								clientID += "," + selectedUsr;
						}
						msgToServer = cast + ":" + clientID + ":" + msg; 
						
						if (cast.equalsIgnoreCase("multicast")) 
						{ 
							if (flag == 1) 
							{ 
								JOptionPane.showMessageDialog(frame, "No user selected");
							} else { 
								dout.writeUTF(msgToServer);
								sendField.setText("");
								msgBox.append("< You sent msg to " + clientID + ">" + msg + "\n"); 
							}
						} 
						clientID = ""; 
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "User does not exist anymore."); 
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
				try 
				{
					dout.writeUTF("exit"); 
					msgBox.append("You are disconnected now.\n");
					frame.dispose();  
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		exitchatBtn.setBounds(480,420, 89, 38);
		frame.getContentPane().add(exitchatBtn);

		JLabel lblNewLabel = new JLabel("Active Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(390, 5, 95, 16);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
		
	}
}
