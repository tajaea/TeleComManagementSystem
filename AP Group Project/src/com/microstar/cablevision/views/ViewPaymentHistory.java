package com.microstar.cablevision.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;

import com.microstar.cablevision.controller.ViewPaymentHistoryController;
import com.microstar.cablevision.controller.ViewPaymentStatusController;
import com.microstar.cablevision.security.Security;

import microStarCableVision.Client;
import microStarCableVision.Customer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewPaymentHistory {

	private JFrame frame;
	private JTable table;
	private JLabel customerIDValue;
	private JLabel lastNameValue ;
	private JButton exitButton;
	DefaultTableModel Model = new DefaultTableModel();

	ViewPaymentHistoryController ViewPaymentHistorycontrol;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPaymentHistory window = new ViewPaymentHistory();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("An error occurred while trying to fetch your Payment History. Please try again later");
					Security.logger.error("An exception was caught in the main method of the ViewPaymentHistory class");
				}
			}
		});*/
		
		new ViewPaymentHistory(new Client ());
	}

	/**
	 * Create the application.
	 */
	public ViewPaymentHistory(Client con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client con) {
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
		
		ViewPaymentHistorycontrol = new ViewPaymentHistoryController(this);
		ViewPaymentHistorycontrol.setClient(con);
		
		JLabel customerIDLabel = new JLabel("Customer ID");
		customerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		customerIDLabel.setBounds(10, 11, 116, 35);
		frame.getContentPane().add(customerIDLabel);
		
		 customerIDValue = new JLabel("CUS0652e6");
		customerIDValue.setFont(new Font("Dubai", Font.PLAIN, 17));
		customerIDValue.setBounds(136, 11, 164, 35);
		frame.getContentPane().add(customerIDValue);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameLabel.setBounds(10, 57, 116, 35);
		frame.getContentPane().add(lastNameLabel);
		
		 lastNameValue = new JLabel("Anglin");
		lastNameValue.setFont(new Font("Dubai", Font.PLAIN, 20));
		lastNameValue.setBounds(136, 57, 152, 35);
		frame.getContentPane().add(lastNameValue);
		
		 exitButton = new JButton("Exit");
		exitButton.setBounds(10, 425, 116, 35);
		frame.getContentPane().add(exitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 116, 774, 298);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() == 2) {
					ViewPaymentHistorycontrol.viewTableDetails();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Payment ID", "Customer ID", " Amount Paid", " Balance Remaining", " Date Paid", " Payment Method"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(97);
		

		Model = (DefaultTableModel) table.getModel();
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
			ViewPaymentHistorycontrol.setCustomerInformation(customerObj);
		}

		public JFrame getFrame() {
			return frame;
		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}

		public JTable getTable() {
			return table;
		}

		public void setTable(JTable table) {
			this.table = table;
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

		

		public DefaultTableModel getModel() {
			return Model;
		}

		public void setModel(DefaultTableModel model) {
			Model = model;
		}
		
		
		
}
