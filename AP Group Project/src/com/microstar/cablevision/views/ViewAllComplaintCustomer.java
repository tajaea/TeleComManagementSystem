package com.microstar.cablevision.views;

import java.awt.Color;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.controller.ComplaintController;
import com.microstar.cablevision.controller.ViewCompalintController;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Customer;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAllComplaintCustomer {

	private JFrame frame;
	private JTable table;
	JButton btnClickHere ;
	ViewCompalintController viewcomplaintControl;
	DefaultTableModel Model = new DefaultTableModel();
	Object[] row = new Object[5];
	Customer customerObj;
	Complaint complaintObj;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @param con 
	 */
	public ViewAllComplaintCustomer(Client con) { 
		initialize( con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-2, 74, 723, 222);
		frame.getContentPane().add(scrollPane);

		viewcomplaintControl = new ViewCompalintController(this);
		viewcomplaintControl.setClient(con);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Complaint ID", "Customer ID", "Complaint Type", "Details", "Status", "Complaint Date", "Complaint Time"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
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
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(94);

		Model = (DefaultTableModel) table.getModel();
		
		table.setForeground(Color.WHITE);
		Font font = new Font ("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

		btnClickHere = new JButton("VIEW YOUR COMPLAINTS");

		btnClickHere.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnClickHere.setBounds(221, 11, 185, 40);
		frame.getContentPane().add(btnClickHere);
		
		btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(8, 302, 89, 23);
		frame.getContentPane().add(btnBack);
		frame.setVisible(true);
		RegisterListener();
	}

	public void RegisterListener() {

		btnClickHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewcomplaintControl.returnCustomer();
				viewcomplaintControl.populateTable(); 
				btnClickHere.setVisible(false);
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//viewcomplaintControl.returnToCustomerDashboard();
			}
		});
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return Model;
		
	}

	public void setModel(DefaultTableModel model) {
		Model = model;
	}

	public Object[] getRow() {
		return row;
	}

	public void setRow(Object[] row) {
		this.row = row;
	}

	public void setCustomerInformation(Customer customerObj) {
		viewcomplaintControl.setCustomer(customerObj);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
