package com.microstar.cablevision.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.controller.ComplaintController;
import com.microstar.cablevision.controller.SearchComplaintController;

import microStarCableVision.Client;
import microStarCableVision.Customer;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class SearchComplaint {
	JFrame frame;
	private JTextField txtComplaintId;
	private JTable table;
	DefaultTableModel Model = new DefaultTableModel();
	Object[] row = new Object[5];
	JButton btnSearch;
	private JButton btnBack;
	SearchComplaint searchComplaintView;
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
		frame.setTitle("Search Complaint");
		frame.getContentPane().setBackground(new Color(255, 245, 238));
		frame.getContentPane().setMinimumSize(new Dimension(900, 500));
		frame.getContentPane().setMaximumSize(new Dimension(900, 500));
		frame.setMinimumSize(new Dimension(900, 500));
		frame.setMaximumSize(new Dimension(900, 500));
		frame.setResizable(false);
		frame.setBounds(100, 100, 737, 434);
		frame.getContentPane().setLayout(null);
		
		searchcomplaintControl = new SearchComplaintController(this);
		searchcomplaintControl.setClient(client);
		JLabel lblSearchComplaint = new JLabel("Search Complaint");
		lblSearchComplaint.setFont(new Font("Dubai", Font.PLAIN, 20));
		lblSearchComplaint.setBounds(10, 11, 159, 28);
		frame.getContentPane().add(lblSearchComplaint);
		
		JLabel lblEnterComplaintId = new JLabel("Enter Complaint Id");
		lblEnterComplaintId.setFont(new Font("Dubai", Font.PLAIN, 18));
		lblEnterComplaintId.setBounds(193, 50, 143, 30);
		frame.getContentPane().add(lblEnterComplaintId);
		
		txtComplaintId = new JTextField(); 
		txtComplaintId.setBounds(346, 56, 89, 20);
		frame.getContentPane().add(txtComplaintId);
		txtComplaintId.setColumns(10);
		
		 btnSearch = new JButton("Search");
		 btnSearch.setFont(new Font("Dubai", Font.PLAIN, 15));
		
		btnSearch.setBounds(498, 55, 95, 36);
		frame.getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 874, 284);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() == 2) {
					searchcomplaintControl.viewTableDetails();
				}
			}
		});
		Object[] columns = {"Complaint Id", "Customer Id", "Complaint Type", "Details", " Status"};
		
		Model.setColumnIdentifiers(columns);
		table.setModel(Model);
		//table.setBackground(Color.cyan);
		//table.setForeground(Color.WHITE);
		Font font = new Font ("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(10, 437, 89, 23);
		frame.getContentPane().add(btnBack);
		frame.setVisible(true);
		RegisterListener();
	}
	
	public void RegisterListener() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*row[0]= "heo";
				row[1]= "jump";
				row[2]= "jump";
				row[3]= "jump";
				row[4]= "jump";
				Model.addRow(row);*/
				String id = txtComplaintId.getText();
				int complaint_id = Integer.parseInt(id);
				searchcomplaintControl.searchComplaintForEmployee(complaint_id);	
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				searchcomplaintControl.returnTodasboard();
			}
		});
	}
	
	public void setCustomerInformation(Customer customerObj) {
		searchcomplaintControl.setCustomer(customerObj);
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
