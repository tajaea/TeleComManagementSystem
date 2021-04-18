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

public class MakeComplaint {

	private JFrame frame;
	JButton btnSubmit;
	private CustomerDashBoard custDash;
	ComplaintController complaintControl;
	JLabel lblCustomeridentifier;
	JTextArea complainttextArea ;
	JComboBox complaintTypecomboBox; 
	JButton btnBack;
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
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 12));
		frame.setBounds(100, 100, 473, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		complaintControl = new ComplaintController(this);
		complaintControl.setClient(client); 
		
		JLabel lblReportAProblem = new JLabel("REPORT A PROBLEM");
		lblReportAProblem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblReportAProblem.setBounds(189, 11, 159, 30);
		frame.getContentPane().add(lblReportAProblem);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCustomerId.setBounds(35, 60, 122, 21);
		frame.getContentPane().add(lblCustomerId);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblType.setBounds(35, 92, 73, 14);
		frame.getContentPane().add(lblType);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDetails.setBounds(35, 130, 73, 14);
		frame.getContentPane().add(lblDetails);
		
		 lblCustomeridentifier = new JLabel("Customeridentifier");
		lblCustomeridentifier.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCustomeridentifier.setBounds(189, 63, 133, 14);
		frame.getContentPane().add(lblCustomeridentifier);
		
		String[] detailtypeList = {"No Service","Bill Complaint", "Property Destruction"};
		complaintTypecomboBox = new JComboBox(detailtypeList);
		complaintTypecomboBox.setBounds(189, 88, 106, 22);
		complaintTypecomboBox.setVisible(true);

		frame.getContentPane().add(complaintTypecomboBox);
		
		 btnSubmit = new JButton("Submit");
		
		
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSubmit.setBounds(340, 324, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		complainttextArea = new JTextArea();
		complainttextArea.setBounds(189, 130, 222, 164);
		frame.getContentPane().add(complainttextArea);
		
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
