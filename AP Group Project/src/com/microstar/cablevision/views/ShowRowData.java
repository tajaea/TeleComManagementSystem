package com.microstar.cablevision.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class ShowRowData extends JFrame {

	private JPanel contentPane;
	private JTextField complaintIDTextField;
	private JTextField customerIDTextField;
	private JTextField complaintTypeTextField;
	private JTextField complaintDetailTextField;
	private JTextField complaintStatusTextField;
	private JTextField complaintDateTextField;
	private JTextField complaintTimeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowRowData frame = new ShowRowData();
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
	public ShowRowData() {
		setMinimumSize(new Dimension(700, 350));
		setMaximumSize(new Dimension(700, 350));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setMinimumSize(new Dimension(700, 350));
		contentPane.setMaximumSize(new Dimension(700, 350));
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
		
		JLabel complaintDate = new JLabel("Date");
		complaintDate.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintDate.setBounds(130, 225, 74, 27);
		contentPane.add(complaintDate);
		
		JLabel complaintTime = new JLabel("Time");
		complaintTime.setFont(new Font("Dubai", Font.PLAIN, 20));
		complaintTime.setBounds(130, 270, 74, 27);
		contentPane.add(complaintTime);
		
		complaintIDTextField = new JTextField();
		complaintIDTextField.setBounds(260, 19, 86, 20);
		contentPane.add(complaintIDTextField);
		complaintIDTextField.setColumns(10);
		
		customerIDTextField = new JTextField();
		customerIDTextField.setColumns(10);
		customerIDTextField.setBounds(260, 63, 86, 20);
		contentPane.add(customerIDTextField);
		
		complaintTypeTextField = new JTextField();
		complaintTypeTextField.setColumns(10);
		complaintTypeTextField.setBounds(284, 107, 190, 25);
		contentPane.add(complaintTypeTextField);
		
		complaintDetailTextField = new JTextField();
		complaintDetailTextField.setColumns(10);
		complaintDetailTextField.setBounds(284, 151, 190, 25);
		contentPane.add(complaintDetailTextField);
		
		complaintStatusTextField = new JTextField();
		complaintStatusTextField.setColumns(10);
		complaintStatusTextField.setBounds(214, 192, 110, 27);
		contentPane.add(complaintStatusTextField);
		
		complaintDateTextField = new JTextField();
		complaintDateTextField.setColumns(10);
		complaintDateTextField.setBounds(214, 232, 110, 27);
		contentPane.add(complaintDateTextField);
		
		complaintTimeTextField = new JTextField();
		complaintTimeTextField.setColumns(10);
		complaintTimeTextField.setBounds(214, 275, 110, 27);
		contentPane.add(complaintTimeTextField);
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

	public JTextField getComplaintDateTextField() {
		return complaintDateTextField;
	}

	public void setComplaintDateTextField(JTextField complaintDateTextField) {
		this.complaintDateTextField = complaintDateTextField;
	}

	public JTextField getComplaintTimeTextField() {
		return complaintTimeTextField;
	}

	public void setComplaintTimeTextField(JTextField complaintTimeTextField) {
		this.complaintTimeTextField = complaintTimeTextField;
	}
}
