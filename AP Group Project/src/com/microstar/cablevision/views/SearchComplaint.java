package com.microstar.cablevision.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.controller.ComplaintController;
import com.microstar.cablevision.controller.SearchComplaintController;

import microStarCableVision.Client;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class SearchComplaint {
	JFrame frame;
	private JTextField txtComplaintId;
	private JTable table;
	DefaultTableModel Model = new DefaultTableModel();
	Object[] row = new Object[5];
	JButton btnSearch;
	private JButton btnBack;
	SearchComplaintController searchcomplaintControl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchComplaint window = new SearchComplaint();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		 new SearchComplaint(new Client());
	}

	/**
	 * Create the application.
	 * @param client 
	 */
	public SearchComplaint(Client client) {
		initialize(client);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param client 
	 */
	private void initialize(Client client) {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 434);
		frame.getContentPane().setLayout(null);
		
		searchcomplaintControl = new SearchComplaintController(this);
		searchcomplaintControl.setClient(client);
		JLabel lblSearchComplaint = new JLabel("Search Complaint");
		lblSearchComplaint.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSearchComplaint.setBounds(221, 11, 185, 14);
		frame.getContentPane().add(lblSearchComplaint);
		
		JLabel lblEnterComplaintId = new JLabel("Enter Complaint Id");
		lblEnterComplaintId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblEnterComplaintId.setBounds(67, 94, 124, 14);
		frame.getContentPane().add(lblEnterComplaintId);
		
		txtComplaintId = new JTextField(); 
		txtComplaintId.setBounds(214, 91, 152, 20);
		frame.getContentPane().add(txtComplaintId);
		txtComplaintId.setColumns(10);
		
		 btnSearch = new JButton("Search");
		
		btnSearch.setBounds(470, 90, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 142, 552, 207);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		Object[] columns = {"Complaint Id", "Customer Id", "Complaint Type", "Details", " Status"};
		
		Model.setColumnIdentifiers(columns);
		table.setModel(Model);
		table.setBackground(Color.cyan);
		table.setForeground(Color.WHITE);
		Font font = new Font ("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(608, 360, 89, 23);
		frame.getContentPane().add(btnBack);
		frame.setVisible(true);
		RegisterListener();
	}
	
	public void RegisterListener() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row[0]= "heo";
				row[1]= "jump";
				row[2]= "jump";
				row[3]= "jump";
				row[4]= "jump";
				

				Model.addRow(row);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchcomplaintControl.returnTodasboard();
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTxtComplaintId() {
		return txtComplaintId;
	}

	public void setTxtComplaintId(JTextField txtComplaintId) {
		this.txtComplaintId = txtComplaintId;
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

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}
	
	
}
