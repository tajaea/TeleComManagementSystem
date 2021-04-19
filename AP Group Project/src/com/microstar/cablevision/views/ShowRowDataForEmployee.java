package com.microstar.cablevision.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ShowRowDataForEmployee extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField complaintIDTextField;
	private JTextField customerIDTextField;
	private JTextField complaintTypeTextField;
	private JTextField complaintDetailTextField;
	private JTextField complaintStatusTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowRowDataForEmployee frame = new ShowRowDataForEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowRowDataForEmployee() {
		setMinimumSize(new Dimension(750, 300));
		setMaximumSize(new Dimension(750, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setMinimumSize(new Dimension(750, 300));
		contentPane.setMaximumSize(new Dimension(750, 300));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon("images\\star.png"));
		logoImageLabel.setBounds(10, 11, 110, 100);
		contentPane.add(logoImageLabel);
		
		JLabel complaintID = new JLabel("Complaint ID");
		complaintID.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintID.setBounds(130, 11, 120, 33);
		contentPane.add(complaintID);
		
		JLabel customerID = new JLabel("Customer ID");
		customerID.setFont(new Font("Dubai", Font.PLAIN, 20));
		customerID.setBounds(130, 55, 120, 33);
		contentPane.add(customerID);
		
		JLabel complaintType = new JLabel("Complaint Type");
		complaintType.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintType.setBounds(130, 99, 144, 33);
		contentPane.add(complaintType);
		
		JLabel complaintDetail = new JLabel("Complaint Detail");
		complaintDetail.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintDetail.setBounds(130, 143, 144, 33);
		contentPane.add(complaintDetail);
		
		JLabel complaintStatus = new JLabel("Status");
		complaintStatus.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintStatus.setBounds(130, 187, 74, 27);
		contentPane.add(complaintStatus);
		
		complaintIDTextField = new JTextField();
		complaintIDTextField.setEditable(false);
		complaintIDTextField.setBounds(260, 19, 86, 20);
		contentPane.add(complaintIDTextField);
		complaintIDTextField.setColumns(10);
		
		customerIDTextField = new JTextField();
		customerIDTextField.setEditable(false);
		customerIDTextField.setColumns(10);
		customerIDTextField.setBounds(260, 63, 86, 20);
		contentPane.add(customerIDTextField);
		
		complaintTypeTextField = new JTextField();
		complaintTypeTextField.setEditable(false);
		complaintTypeTextField.setColumns(10);
		complaintTypeTextField.setBounds(284, 107, 190, 25);
		contentPane.add(complaintTypeTextField);
		
		complaintDetailTextField = new JTextField();
		complaintDetailTextField.setEditable(false);
		complaintDetailTextField.setColumns(10);
		complaintDetailTextField.setBounds(284, 151, 440, 30);
		contentPane.add(complaintDetailTextField);
		
		complaintStatusTextField = new JTextField();
		complaintStatusTextField.setEditable(false);
		complaintStatusTextField.setColumns(10);
		complaintStatusTextField.setBounds(214, 192, 110, 27);
		contentPane.add(complaintStatusTextField);
	}

	public JTextField getComplaintIDTextField() {
		return complaintIDTextField;
	}

	public void setComplaintIDTextField(JTextField complaintIDTextField) {
		this.complaintIDTextField = complaintIDTextField;
	}

	public JTextField getCustomerIDTextField() {
		return customerIDTextField;
	}

	public void setCustomerIDTextField(JTextField customerIDTextField) {
		this.customerIDTextField = customerIDTextField;
	}

	public JTextField getComplaintTypeTextField() {
		return complaintTypeTextField;
	}

	public void setComplaintTypeTextField(JTextField complaintTypeTextField) {
		this.complaintTypeTextField = complaintTypeTextField;
	}

	public JTextField getComplaintDetailTextField() {
		return complaintDetailTextField;
	}

	public void setComplaintDetailTextField(JTextField complaintDetailTextField) {
		this.complaintDetailTextField = complaintDetailTextField;
	}

	public JTextField getComplaintStatusTextField() {
		return complaintStatusTextField;
	}

	public void setComplaintStatusTextField(JTextField complaintStatusTextField) {
		this.complaintStatusTextField = complaintStatusTextField;
	}	
}
