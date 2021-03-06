package com.microstar.cablevision.views;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.microstar.cablevision.controller.ViewPaymentStatusController;
import com.microstar.cablevision.security.Security;

import microStarCableVision.Client;
import microStarCableVision.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ViewPaymentStatus {

	private JFrame frame;
	ViewPaymentStatusController ViewPaymentStatuscontrol;
	private JButton exitButton;
	Customer customerObj;
	private JLabel customerIDValue;
	private JLabel lastNameValue  ;
	private JLabel paymentStatus ;
	private JLabel logoImageLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/**EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPaymentStatus window = new ViewPaymentStatus(new Client() );
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("An error occurred while trying to fetch your Payment Status. Please try again later");
					Security.logger.error("An exception was caught in the main method of the ViewPaymentStatus class");
				}
			}
		});*/
		new ViewPaymentStatus(new Client() );
	}

	/**
	 * Create the application.
	 */
	public ViewPaymentStatus(Client con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client con) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.getContentPane().setMinimumSize(new Dimension(750, 350));
		frame.getContentPane().setMaximumSize(new Dimension(750, 350));
		frame.setMinimumSize(new Dimension(750, 350));
		frame.setMaximumSize(new Dimension(750, 350));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ViewPaymentStatuscontrol = new ViewPaymentStatusController(this);
		ViewPaymentStatuscontrol.setClient(con);
		
		logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon("images\\star.png"));
		logoImageLabel.setBounds(10, 11, 110, 100);
		frame.getContentPane().add(logoImageLabel);
		JLabel customerIDLabel = new JLabel("Customer ID");
		customerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		customerIDLabel.setBounds(149, 11, 116, 35);
		frame.getContentPane().add(customerIDLabel);
		
		 customerIDValue = new JLabel("value");
		customerIDValue.setFont(new Font("Dubai", Font.PLAIN, 17));
		customerIDValue.setBounds(275, 11, 101, 35);
		frame.getContentPane().add(customerIDValue);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameLabel.setBounds(149, 57, 116, 35);
		frame.getContentPane().add(lastNameLabel);
		
		lastNameValue = new JLabel("value");
		lastNameValue.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameValue.setBounds(275, 57, 80, 35);
		frame.getContentPane().add(lastNameValue);
		
		paymentStatus = new JLabel("Your Account Is Up To Date");
		paymentStatus.setFont(new Font("Dubai", Font.PLAIN, 25));
		paymentStatus.setBounds(149, 103, 560, 112);
		frame.getContentPane().add(paymentStatus);
		
		 exitButton = new JButton("Exit");
		
		exitButton.setBounds(10, 265, 116, 35);
		frame.getContentPane().add(exitButton);
		frame.setVisible(true);
		RegisterListener();
	}
	
	public void RegisterListener() {
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//ViewPaymentStatuscontrol.returnToCustomerDashboard();
			}
		});
	}

	public void setCustomerInformation(Customer customerObj) {

		//this.customerObj=customerObj;
		ViewPaymentStatuscontrol.setCustomerInformation(customerObj);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getCustomerIDValue() {
		return customerIDValue;
	}

	public void setCustomerIDValue(JLabel customerIDValue) {
		this.customerIDValue = customerIDValue;
	}

	public JLabel getLastNameValue() {
		return lastNameValue;
	}

	public void setLastNameValue(JLabel lastNameValue) {
		this.lastNameValue = lastNameValue;
	}

	public JLabel getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(JLabel paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	

	
}
