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
import javax.swing.JLayeredPane;
import java.awt.Cursor;

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
	JButton noServiceButton;
	JButton billCButton;
	JButton pDestructionButton;
	JButton exitButton;
	
	ViewAllComplaintsRep viewcomplaintControl;
	JPanel NSPanel;
	JPanel BCPanel;
	JPanel PDPanel;
	private JTable NSTable;
	private JTable BCTable;
	private JScrollPane BCscrollPane;
	private JTable PDTable;
	private JScrollPane PDscrollPane;
	
	/**
	 * Create the frame.
	 */
	public ViewAllComplaintsRepresentative(Client client) {
		setResizable(false);
		setMinimumSize(new Dimension(1100, 500));
		setMaximumSize(new Dimension(1100, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(125,45);
		setTitle("View All Complaints");
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
		noServiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		noServiceButton.setFont(new Font("Dubai", Font.PLAIN, 23));
		noServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				noServiceButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							NSPanel.setVisible(true);
							BCPanel.setVisible(false);
							PDPanel.setVisible(false);
							
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
		billCButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		billCButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		billCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				billCButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							NSPanel.setVisible(false);
							BCPanel.setVisible(true);
							PDPanel.setVisible(false);
							
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
		pDestructionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pDestructionButton.setFont(new Font("Dubai", Font.PLAIN, 20));
		pDestructionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pDestructionButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							NSPanel.setVisible(false);
							BCPanel.setVisible(false);
							PDPanel.setVisible(true);
							
							String pdType = "Property Destruction";
							viewcomplaintControl.populatePDTableForRep(pdType);
						}
					}
				});
			}
		});
		pDestructionButton.setBounds(10, 316, 233, 37);
		scomplaintsOptionsTab.add(pDestructionButton);
		
		exitButton = new JButton("Back");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(273, 11, 811, 449);
		contentPane.add(layeredPane);
	
		
		NSPanel = new JPanel();
		NSPanel.setVisible(false);
		NSPanel.setBounds(0, 0, 811, 449);
		layeredPane.add(NSPanel);
		NSPanel.setLayout(null);

		JScrollPane NSscrollPane = new JScrollPane();
		NSscrollPane.setBounds(0, 0, 811, 449);
		NSPanel.add(NSscrollPane);
		
		
		BCPanel = new JPanel();
		BCPanel.setVisible(false);
		BCPanel.setBounds(0, 0, 811, 449);
		layeredPane.add(BCPanel);
		BCPanel.setLayout(null);
		
		BCscrollPane = new JScrollPane();
		BCscrollPane.setBounds(0, 0, 811, 449);
		BCPanel.add(BCscrollPane);
		
		BCTable = new JTable();
		BCTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() == 2) {
					viewcomplaintControl.viewBCComplaintDetails();
				}
			}
		});
		BCTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Complaint ID", "Customer ID", "Complaint Type", "Complaint Detail", "Status", "Date", "Time"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		BCTable.getColumnModel().getColumn(1).setPreferredWidth(97);
		BCTable.getColumnModel().getColumn(2).setPreferredWidth(101);
		BCscrollPane.setViewportView(BCTable);
		
		PDPanel = new JPanel();
		PDPanel.setVisible(false);
		PDPanel.setBounds(0, 0, 811, 449);
		layeredPane.add(PDPanel);
		PDPanel.setLayout(null);
		

		PDscrollPane = new JScrollPane();
		PDscrollPane.setBounds(0, 0, 811, 449);
		PDPanel.add(PDscrollPane);

		
		
		PDTable = new JTable();
		PDTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() == 2) {
					viewcomplaintControl.viewPDComplaintDetails();
				}
			}
		});
		PDscrollPane.setViewportView(PDTable);
		PDTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Complaint ID", "Customer ID", "Complaint Type", "Complaint Detail", "Status", "Date", "Time"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		PDTable.getColumnModel().getColumn(1).setPreferredWidth(92);
		PDTable.getColumnModel().getColumn(2).setPreferredWidth(101);
		
		NSTable = new JTable();
		NSTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getClickCount() == 2) {
					viewcomplaintControl.viewNSComplaintDetails();
				}
			}
		});
		
		NSTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Complaint ID", "Customer ID", "Complaint Type", "Complaint Detail", "Status", "Date", "Time"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		NSTable.getColumnModel().getColumn(1).setPreferredWidth(91);
		NSTable.getColumnModel().getColumn(2).setPreferredWidth(94);
		NSscrollPane.setViewportView(NSTable);
		setVisible(true);
	}
		
	public void setEmp(Employee employee) {
		viewcomplaintControl.setEmpObj(employee);
	}
	
	public JTable getNSTable() {
		return NSTable;
	}
	
	public JTable getBCTable() {
		return BCTable;
	}
	
	public JTable getPDTable() {
		return PDTable;
	}
}
