package com.microstar.cablevision.views;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import com.microstar.cablevision.controller.ComplaintController;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Customer;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Color;

public class MakeComplaint {

	private JFrame frame;
	JButton btnSubmit;
	private CustomerDashBoard custDash;
	ComplaintController complaintControl;
	JLabel lblCustomeridentifier;
	JTextArea complainttextArea ;
	JComboBox complaintTypecomboBox; 
	JButton btnBack;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeComplaint window = new MakeComplaint();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		new MakeComplaint( new Client());
	}

	/**
	 * Create the application.
	 * @param customerDashBoard 
	 */
	public MakeComplaint(Client client) {
		initialize(client);
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @param customerDashBoard 
	 */
	private void initialize(Client client) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.setResizable(false);
		frame.getContentPane().setMinimumSize(new Dimension(650, 300));
		frame.getContentPane().setMaximumSize(new Dimension(650, 300));
		frame.setMinimumSize(new Dimension(650, 300));
		frame.setMaximumSize(new Dimension(650, 300));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 12));
		frame.setBounds(100, 100, 473, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		complaintControl = new ComplaintController(this);
		complaintControl.setClient(client); 
		
		JLabel lblReportAProblem = new JLabel("REPORT A PROBLEM");
		lblReportAProblem.setFont(new Font("Dubai", Font.PLAIN, 16));
		lblReportAProblem.setBounds(10, 11, 160, 38);
		frame.getContentPane().add(lblReportAProblem);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Dubai", Font.PLAIN, 17));
		lblCustomerId.setBounds(34, 60, 108, 21);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dubai", Font.PLAIN, 18));
		lblType.setBounds(34, 92, 59, 27);
		frame.getContentPane().add(lblType);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setFont(new Font("Dubai", Font.PLAIN, 17));
		lblDetails.setBounds(34, 139, 73, 27);
		frame.getContentPane().add(lblDetails);
		
		 lblCustomeridentifier = new JLabel("Customeridentifier");
		lblCustomeridentifier.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCustomeridentifier.setBounds(152, 64, 122, 21);
		frame.getContentPane().add(lblCustomeridentifier);
		
		String[] detailtypeList = {"No Service","Bill Complaint", "Property Destruction"};
		complaintTypecomboBox = new JComboBox(detailtypeList);
		complaintTypecomboBox.setBounds(152, 95, 132, 27);
		complaintTypecomboBox.setVisible(true);

		frame.getContentPane().add(complaintTypecomboBox);
		
		 btnSubmit = new JButton("Submit");
		
		
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSubmit.setBounds(535, 324, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(118, 152, 506, 161);
		frame.getContentPane().add(scrollPane);
		
		complainttextArea = new JTextArea();
		scrollPane.setViewportView(complainttextArea);
		
		 btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(10, 324, 89, 23);
		frame.getContentPane().add(btnBack);
		
		frame.setVisible(true);
		RegisterListener();
	}
	
	public void RegisterListener() {
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complaintControl.Submit();
				
			 }
			
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complaintControl.returnToCustomerDashboard();
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	} 

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/*public void setComplaintObject(Complaint complaintObj) {
		complaintControl.setComplaintInformation(complaintObj);
	}*/
	
	public void setCustomerInformation(Customer customerObj) {
		
		complaintControl.setCustomerInformation(customerObj);
	}
	
	
	public JLabel getLblCustomeridentifier() {
		return lblCustomeridentifier;
	}

	public void setLblCustomeridentifier(JLabel lblCustomeridentifier) {
		this.lblCustomeridentifier = lblCustomeridentifier;
	}

	public JTextArea getComplainttextArea() {
		return complainttextArea;
	}

	public void setComplainttextArea(JTextArea complainttextArea) {
		this.complainttextArea = complainttextArea;
	}

	public JComboBox getComplaintTypecomboBox() {
		return complaintTypecomboBox;
	}

	public void setComplaintTypecomboBox(JComboBox complaintTypecomboBox) {
		this.complaintTypecomboBox = complaintTypecomboBox;
	}
}
