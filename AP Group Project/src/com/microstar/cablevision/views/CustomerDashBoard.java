package com.microstar.cablevision.views;

import java.awt.EventQueue; 





import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.microstar.cablevision.controller.CustomerDashboardController;

import microStarCableVision.Client;

import microStarCableVision.Customer;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CustomerDashBoard {

	private JFrame frame;

	private JFrame pframe;
	private JTextField idTextField;
	private JTextField fnameTextfield;
	private JTextField lnameTextfield;
	private JTextField emailTextfield;
	private JTextField passwordTextfield;
	private JFrame lframe;
	private JTextField txtComplaintId;
	private JButton btnComeChatWith;
	private JButton btnSignUpHere;
	private JButton btnSignOut;
	private JLabel lblFirstName;
	private JLabel lblLastname;
	private JLabel lblTeleno;
	private JLabel lblEmail;
	private JMenu mnReportAProblem;
	private JMenuItem mntmMakeComplaint ;
	private JMenuItem mntmViewAllComplaints;
	private JMenuItem mntmSearchForComplaint;
	private JMenuItem mntmAmountDue;
	CustomerDashboardController customerControl;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Client client = new Client();

					CustomerDashBoard window = new CustomerDashBoard(client);
					//CustomerDashBoard window = new CustomerDashBoard();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		// Creating an instance of the client in the customerDashboard	
		new CustomerDashBoard(new Client());
	}

	/**
	 * Create the application.
	 */
	public CustomerDashBoard(Client client) {
		// Initializing the components of the CustomerDashboard
		initialize(client);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client client) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.getContentPane().setMinimumSize(new Dimension(1100, 714));
		frame.getContentPane().setMaximumSize(new Dimension(1523, 914));
		frame.setMinimumSize(new Dimension(1100, 714));
		frame.setMaximumSize(new Dimension(1523, 914));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 12));
		frame.setBounds(100, 100, 697, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//initializing the customer control object
		customerControl = new CustomerDashboardController(this);
		customerControl.setClient(client);
		
		JLabel logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon("images\\star.png"));
		logoImageLabel.setBounds(18, 11, 109, 100);
		frame.getContentPane().add(logoImageLabel);

		btnComeChatWith = new JButton(" Chat With Us");
		btnComeChatWith.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnComeChatWith.setBounds(10, 528, 162, 49);
		frame.getContentPane().add(btnComeChatWith);

		JLabel locationStreetNameLabel = new JLabel("Gordon Town Road");
		locationStreetNameLabel.setFont(new Font("Dubai", Font.PLAIN, 18));
		locationStreetNameLabel.setVerticalAlignment(SwingConstants.TOP);
		locationStreetNameLabel.setBounds(16, 394, 156, 29);
		frame.getContentPane().add(locationStreetNameLabel);

		JLabel locationParishLabel = new JLabel("St. Andrew");
		locationParishLabel.setFont(new Font("Dubai", Font.PLAIN, 18));
		locationParishLabel.setBounds(18, 434, 95, 23);
		frame.getContentPane().add(locationParishLabel);

		JLabel locationCountryLabel = new JLabel("Kingston, Jamaica");
		locationCountryLabel.setFont(new Font("Dubai", Font.PLAIN, 18));
		locationCountryLabel.setBounds(18, 468, 142, 29);
		frame.getContentPane().add(locationCountryLabel);

		JLabel contactUsSocialMediaLabel = new JLabel("\r\n Facebook: Micro-Star Cable Vision |  Instagram: Micro-Star Cable Vision |  Email: MICROSTARCABLEVISION@GMAIL.COM");
		contactUsSocialMediaLabel.setFont(new Font("Dubai", Font.ITALIC, 13));
		contactUsSocialMediaLabel.setBounds(10, 619, 681, 23);
		frame.getContentPane().add(contactUsSocialMediaLabel);

		JLabel contactUsLabel = new JLabel("Contact us @ Tel: (876) 954-2300 or (876) 579-2300");
		contactUsLabel.setFont(new Font("Dubai", Font.ITALIC, 16));
		contactUsLabel.setBounds(10, 588, 379, 19);
		frame.getContentPane().add(contactUsLabel);

		JLabel adsHeaderLabel = new JLabel("UPGRADE TO THE MACRO STAR PLAN ");
		adsHeaderLabel.setFont(new Font("Dubai", Font.BOLD, 20));
		adsHeaderLabel.setBounds(673, 220, 361, 39);
		frame.getContentPane().add(adsHeaderLabel);

		JLabel lblForOnly = new JLabel("FOR ONLY $3000.00 Per Month");
		lblForOnly.setFont(new Font("Dubai", Font.BOLD, 15));
		lblForOnly.setBounds(673, 387, 218, 29);
		frame.getContentPane().add(lblForOnly);

		JLabel wifiSpeedLabel = new JLabel("Up to 50 Mbps\r\n\r\n");
		wifiSpeedLabel.setFont(new Font("Dubai", Font.PLAIN, 15));
		wifiSpeedLabel.setBounds(810, 261, 109, 22);
		frame.getContentPane().add(wifiSpeedLabel);

		JLabel extraChannelsLabel = new JLabel("200 channels");
		extraChannelsLabel.setFont(new Font("Dubai", Font.PLAIN, 15));
		extraChannelsLabel.setBounds(810, 307, 90, 23);
		frame.getContentPane().add(extraChannelsLabel);

		JLabel packageIncludeLabel = new JLabel("Package Include:");
		packageIncludeLabel.setFont(new Font("Dubai", Font.PLAIN, 18));
		packageIncludeLabel.setBounds(673, 261, 127, 23);
		frame.getContentPane().add(packageIncludeLabel);

		JLabel newFibreOpticLabel = new JLabel("New Fibre Optic Cable");
		newFibreOpticLabel.setFont(new Font("Dubai", Font.PLAIN, 15));
		newFibreOpticLabel.setBounds(810, 349, 142, 19);
		frame.getContentPane().add(newFibreOpticLabel);

		btnSignUpHere = new JButton("Sign Up Here");

		btnSignUpHere.setBounds(792, 416, 127, 39);
		frame.getContentPane().add(btnSignUpHere);

		JPanel headerPannel = new JPanel();
		headerPannel.setBackground(new Color(30, 144, 255));
		headerPannel.setBounds(158, 11, 916, 61);
		frame.getContentPane().add(headerPannel);
		headerPannel.setLayout(null);
		 btnSignOut = new JButton("Sign Out");

		btnSignOut.setBounds(806, 11, 100, 34);
		headerPannel.add(btnSignOut);

		JLabel lblMicrostarCablevision = new JLabel("Micro-Star CableVision\u2122 ");
		lblMicrostarCablevision.setBounds(10, 11, 399, 39);
		headerPannel.add(lblMicrostarCablevision);
		lblMicrostarCablevision.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 30));
		
		 lblFirstName = new JLabel("FIRST NAME");
		 lblFirstName.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 20));
		 lblFirstName.setBounds(399, 14, 145, 29);
		 headerPannel.add(lblFirstName);
		 
		  lblLastname = new JLabel("LASTNAME");
		  lblLastname.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 20));
		  lblLastname.setBounds(554, 14, 126, 29);
		  headerPannel.add(lblLastname);

		JLabel adsImageLabel = new JLabel("");
		adsImageLabel.setIcon(new ImageIcon("images\\ads.png"));
		adsImageLabel.setBounds(779, 78, 132, 133);
		frame.getContentPane().add(adsImageLabel);

		JLabel wifiImageLabel = new JLabel("New label");
		wifiImageLabel.setIcon(new ImageIcon("images\\performance.png"));
		wifiImageLabel.setBounds(916, 254, 39, 39);
		frame.getContentPane().add(wifiImageLabel);

		JLabel cableImageLabel = new JLabel("New label");
		cableImageLabel.setIcon(new ImageIcon("images\\cable-tv.png"));
		cableImageLabel.setBounds(913, 299, 39, 39);
		frame.getContentPane().add(cableImageLabel);

		JLabel cableWireImageLabel = new JLabel("New label");
		cableWireImageLabel.setIcon(new ImageIcon("images\\wiring.png"));
		cableWireImageLabel.setBounds(956, 339, 39, 39);
		frame.getContentPane().add(cableWireImageLabel);
		
		 lblTeleno = new JLabel("TELE-NO");
		lblTeleno.setBounds(143, 123, 115, 14);
		frame.getContentPane().add(lblTeleno);
		
		 lblEmail = new JLabel("EMAIL");
		 lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail.setBounds(87, 148, 167, 27);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTeleNo = new JLabel("TELE. NO.");
		lblTeleNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTeleNo.setBounds(18, 122, 84, 14);
		frame.getContentPane().add(lblTeleNo);
		
		JLabel lblEmail_1 = new JLabel("EMAIL");
		lblEmail_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmail_1.setBounds(18, 152, 59, 14);
		frame.getContentPane().add(lblEmail_1);
		
		JLabel locationImageLabel = new JLabel("");
		locationImageLabel.setIcon(new ImageIcon("images\\map.png"));
		locationImageLabel.setBounds(18, 261, 132, 133);
		frame.getContentPane().add(locationImageLabel);
		

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 245, 238));
		frame.setJMenuBar(menuBar);

		JMenu mnViewPaymentHistory = new JMenu(" Payment");
		menuBar.add(mnViewPaymentHistory);

		JMenuItem mntmPaymentStatus = new JMenuItem("Payment Status");
		mntmPaymentStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//crud operation
			}
		});
		mnViewPaymentHistory.add(mntmPaymentStatus);

		 mntmAmountDue = new JMenuItem("History");
		
		mnViewPaymentHistory.add(mntmAmountDue);

		JMenu menu = new JMenu("");
		menuBar.add(menu);

		 mnReportAProblem = new JMenu("Make a Complaint\r\n");
		
		menuBar.add(mnReportAProblem);
		
	 mntmMakeComplaint = new JMenuItem("Make Complaint");
		
		mnReportAProblem.add(mntmMakeComplaint);

		JMenu mnViewComplaintLogs = new JMenu("View Complaint Logs");
		menuBar.add(mnViewComplaintLogs);

		 mntmSearchForComplaint = new JMenuItem("Search For Complaint");
		
		mnViewComplaintLogs.add(mntmSearchForComplaint);

		 mntmViewAllComplaints = new JMenuItem("View All Complaints");
				mnViewComplaintLogs.add(mntmViewAllComplaints);
		
		 
		frame.setVisible(true);
		RegisterListener(client);
	}

	public void RegisterListener(Client client) {
		
		
		btnComeChatWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerControl.OpenChatWindow();
			}
		});
		
		btnSignUpHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//calling a sign up feature
			}
		});

		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerControl.SignOut();
				}
		});
		
		mnReportAProblem.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isLeftMouseButton(event)) {
				
				}
			}
		});
		mntmMakeComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerControl.getMakeComplaint();
			}
		});
		
		mntmViewAllComplaints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerControl.getViewAllComplaint();
			}
		});
		mntmSearchForComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchComplaint window = new SearchComplaint(client);
				window.frame.setVisible(true);	
			}
		});
		
		/*mntmAmountDue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//crud operation
			}
		}); */

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setCustomerObject(Customer customerObj) {

		//Passing the Customer object that has been returned from the database to the controller
		customerControl.setCustomerInformation(customerObj);
	}

	public JLabel getLblFirstName() {
		return lblFirstName;
	}

	public void setLblFirstName(JLabel lblFirstName) {
		this.lblFirstName = lblFirstName;
	}

	public JLabel getLblLastname() {
		return lblLastname;
	}

	public void setLblLastname(JLabel lblLastname) {
		this.lblLastname = lblLastname;
	}

	public JLabel getLblTeleno() {
		return lblTeleno;
	}

	public void setLblTeleno(JLabel lblTeleno) {
		this.lblTeleno = lblTeleno;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}
}
