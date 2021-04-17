package com.microstar.cablevision.views;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.controller.ViewAllComplaintsRep;

import microStarCableVision.Client;
import microStarCableVision.Employee;

import java.awt.Dimension;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ViewAllComplaintsRepresentative extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	JPanel scomplaintsOptionsTab;
	JLabel logoImageLabel;
	JPanel complaintsOptionsDetails;
	JPanel noServiceDetailPanel;
	JPanel billComplaintDetailPanel;
	JPanel pDestructionDetailPanel;
	JButton noServiceButton;
	JButton billCButton;
	JButton pDestructionButton;
	JButton exitButton;
	JTable noServiceDetailTable;
	JTable billCDetailTable;
	JScrollPane billCTableSPane;
	JScrollPane noServiceTableSPane;
	JTable pDDetailTable;
	JScrollPane pDTableSPane;
	
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel bcmodel = new DefaultTableModel();
	DefaultTableModel pdmodel = new DefaultTableModel();
	ViewAllComplaintsRep viewcomplaintControl;
	
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllComplaintsRepresentative frame = new ViewAllComplaintsRepresentative(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		new ViewAllComplaintsRepresentative(new Client());
	}
	/**
	 * Create the frame.
	 */
	public ViewAllComplaintsRepresentative(Client client) {
		setResizable(false);
		setMinimumSize(new Dimension(1100, 500));
		setMaximumSize(new Dimension(1100, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		viewcomplaintControl = new ViewAllComplaintsRep(this);
		viewcomplaintControl.setClient(client);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setMinimumSize(new Dimension(1100, 500));
		contentPane.setMaximumSize(new Dimension(1100, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scomplaintsOptionsTab = new JPanel();
		scomplaintsOptionsTab.setBounds(10, 11, 253, 449);
		scomplaintsOptionsTab.setBackground(new Color(255, 245, 238));
		contentPane.add(scomplaintsOptionsTab);
		scomplaintsOptionsTab.setLayout(null);
		
		logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon("images\\star.png"));
		logoImageLabel.setBounds(62, 5, 110, 100);
		scomplaintsOptionsTab.add(logoImageLabel);
		
		noServiceButton = new JButton("No Service");
		noServiceButton.setFont(new Font("Dubai", Font.PLAIN, 23));
		noServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				noServiceButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							noServiceDetailPanel.setVisible(true);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(false);
							
							String nsType = "No Service";
							viewcomplaintControl.populateNSTableForRep(nsType);
						}
					}
				});
			}
		});
		noServiceButton.setBounds(10, 116, 233, 37);
		scomplaintsOptionsTab.add(noServiceButton);
		
		billCButton = new JButton("Bill Complaint");
		billCButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		billCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				billCButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(true);
							pDestructionDetailPanel.setVisible(false);
							
							String bcType = "Bill Complaint";
							viewcomplaintControl.populateBCTableForRep(bcType);
						}
					}
				});
			}
		});
		billCButton.setBounds(10, 213, 233, 37);
		scomplaintsOptionsTab.add(billCButton);
		
		pDestructionButton = new JButton("Property Destruction");
		pDestructionButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		pDestructionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pDestructionButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(true);
							
							String pdType = "Property Destruction";
							viewcomplaintControl.populatePDTableForRep(pdType);
						}
					}
				});
			}
		});
		pDestructionButton.setBounds(10, 316, 233, 37);
		scomplaintsOptionsTab.add(pDestructionButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							viewcomplaintControl.returnToRepGui();
						}
					}
				});
			}
		});
		exitButton.setBounds(10, 415, 89, 23);
		scomplaintsOptionsTab.add(exitButton);
		
		complaintsOptionsDetails = new JPanel();
		complaintsOptionsDetails.setBounds(273, 11, 784, 449);
		complaintsOptionsDetails.setBackground(new Color(255, 245, 238));
		contentPane.add(complaintsOptionsDetails);
		complaintsOptionsDetails.setLayout(null);
		
		noServiceDetailPanel = new JPanel();
		noServiceDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(noServiceDetailPanel);
		noServiceDetailPanel.setLayout(null);
		
		noServiceTableSPane = new JScrollPane();
		noServiceTableSPane.setBounds(0, 415, 754, -374);
		//contentPane.add(noServiceTableSPane);
		noServiceDetailPanel.add(noServiceTableSPane);
		
		noServiceDetailTable = new JTable();
		noServiceDetailTable.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
					"Complaint ID", "Customer ID", "Type", "Details", "Status", "Date", "Time"
			}
		) {
			/**
			 * 
			 */
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false	
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		noServiceDetailTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		noServiceDetailTable.getColumnModel().getColumn(6).setPreferredWidth(80);
		model = (DefaultTableModel) noServiceDetailTable.getModel();
		noServiceDetailTable.setBackground(Color.WHITE);
		noServiceDetailTable.setRowHeight(30);
		noServiceTableSPane.setViewportView(noServiceDetailTable);
		
		billComplaintDetailPanel = new JPanel();
		billComplaintDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(billComplaintDetailPanel);
		billComplaintDetailPanel.setLayout(null);
		
		billCTableSPane = new JScrollPane();
		billCTableSPane.setBounds(0, 415, 754, -374);
		//billCTableSPane.setBounds(10, 416, 744, -404);
		//contentPane.add(billCTableSPane);
		billComplaintDetailPanel.add(billCTableSPane);
		
		billCDetailTable = new JTable();
		billCDetailTable.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"Complaint ID", "Customer ID", "Type", "Details", "Status", "Date", "Time"
				}
			) {
				/**
				 * 
				 */
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false	
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			billCDetailTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(1).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(4).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			billCDetailTable.getColumnModel().getColumn(6).setPreferredWidth(80);
			bcmodel = (DefaultTableModel) billCDetailTable.getModel();
			billCDetailTable.setBackground(Color.WHITE);
			billCDetailTable.setRowHeight(30);
		billCTableSPane.setViewportView(billCDetailTable);
		
		pDestructionDetailPanel = new JPanel();
		pDestructionDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(pDestructionDetailPanel);
		pDestructionDetailPanel.setLayout(null);
		
		pDTableSPane = new JScrollPane();
		pDTableSPane.setBounds(0, 415, 754, -374);
		//pDTableSPane.setBounds(10, 412, 744, -400);
		//contentPane.add(pDTableSPane);
		pDestructionDetailPanel.add(pDTableSPane);
		
		pDDetailTable = new JTable();
		pDDetailTable.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"Complaint ID", "Customer ID", "Type", "Details", "Status", "Date", "Time"
				}
			) {
				/**
				 * 
				 */
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false	
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			pDDetailTable.getColumnModel().getColumn(0).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(1).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(3).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(4).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(5).setPreferredWidth(80);
			pDDetailTable.getColumnModel().getColumn(6).setPreferredWidth(80);
			pdmodel = (DefaultTableModel) pDDetailTable.getModel();
			pDDetailTable.setBackground(Color.WHITE);
			pDDetailTable.setRowHeight(30);
		pDTableSPane.setViewportView(pDDetailTable);
		setVisible(true);
	}
		
	public void setEmp(Employee employee) {
		viewcomplaintControl.setEmpObj(employee);
	}
	
	public JTable getNoServiceDetailTable() {
		return noServiceDetailTable;
	}
	public void setNoServiceDetailTable(JTable noServiceDetailTable) {
		this.noServiceDetailTable = noServiceDetailTable;
	}
	
	public JTable getBillCDetailTable() {
		return billCDetailTable;
	}
	public void setBillCDetailTable(JTable billCDetailTable) {
		this.billCDetailTable = billCDetailTable;
	}
	public JTable getpDDetailTable() {
		return pDDetailTable;
	}
	public void setpDDetailTable(JTable pDDetailTable) {
		this.pDDetailTable = pDDetailTable;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public DefaultTableModel getbcModel() {
		return bcmodel;
	}
	public void setbcModel(DefaultTableModel model) {
		this.bcmodel = model;
	}
	public DefaultTableModel getpdModel() {
		return pdmodel;
	}
	public void setpdModel(DefaultTableModel model) {
		this.pdmodel = model;
	}
	public Object[] getRow() {
		return row;
	}
	public void setRow(Object[] row) {
		this.row = row;
	}
}
