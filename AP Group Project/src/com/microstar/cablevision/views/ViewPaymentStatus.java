package com.microstar.cablevision.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewPaymentStatus {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPaymentStatus window = new ViewPaymentStatus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewPaymentStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.getContentPane().setMinimumSize(new Dimension(800, 500));
		frame.getContentPane().setMaximumSize(new Dimension(800, 500));
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setMaximumSize(new Dimension(800, 500));
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel customerIDLabel = new JLabel("Customer ID");
		customerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		customerIDLabel.setBounds(10, 11, 116, 35);
		frame.getContentPane().add(customerIDLabel);
		
		JLabel customerIDValue = new JLabel("CUS0652e6");
		customerIDValue.setFont(new Font("Dubai", Font.PLAIN, 17));
		customerIDValue.setBounds(136, 11, 101, 35);
		frame.getContentPane().add(customerIDValue);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameLabel.setBounds(10, 57, 116, 35);
		frame.getContentPane().add(lastNameLabel);
		
		JLabel lastNameValue = new JLabel("Anglin");
		lastNameValue.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameValue.setBounds(136, 57, 80, 35);
		frame.getContentPane().add(lastNameValue);
		
		JLabel paymentStatus = new JLabel("Your Account Has An Oustanding Balance");
		paymentStatus.setFont(new Font("Dubai", Font.PLAIN, 25));
		paymentStatus.setBounds(10, 104, 560, 154);
		frame.getContentPane().add(paymentStatus);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(10, 425, 116, 35);
		frame.getContentPane().add(exitButton);
		frame.setVisible(true);
	}

}
