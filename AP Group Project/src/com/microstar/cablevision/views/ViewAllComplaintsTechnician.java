package com.microstar.cablevision.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.microstar.cablevision.controller.ViewAllComplaintsTech;

import microStarCableVision.Client;
import microStarCableVision.Employee;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;

public class ViewAllComplaintsTechnician extends JFrame {
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
	
	
	JLayeredPane layeredPane;
	JPanel noServiceDetailPanel;
	JPanel billComplaintDetailPanel;
	JPanel pDestructionDetailPanel;
	JScrollPane noServiceTableSPane;
	JTable noServiceDetailTable;
	JTable billCDetailTable;
	JScrollPane billCTableSPane;
	JTable pDDetailTable;
	JScrollPane pDTableSPane;
	JLabel nSResponseLabel;
	JTextField nSResponseTextField;
	JLabel nSResponseDateLabel;
	JTextField nSDateTextField;
	JButton nSSubmitResponseButton;
	JLabel bCResponseLabel;
	JTextField bCResponseTextField;
	JLabel bCResponseDateLabel;
	JTextField bCDateTextField;
	JButton bCSubmitResponseButton;
	JLabel pDResponseLabel;
	JTextField pDresponseTextField;
	JLabel pDResponseDateLabel;
	JTextField pDDateTextField;
	JButton pDSubmitResponseButton;
	JLabel nsCustomerIDLabel;
	JLabel nsComplaintIDLabel;
	JTextField nsCustomerIDTextField;
	JTextField nsComplaintIDTextField;
	JLabel bcCustomerIDLabel;
	JTextField bcCustomerIDTextField;
	JLabel bcComplaintIDLabel;
	JTextField bcComplaintIDTextField;
	JLabel pdCustomerIDLabel;
	JTextField pdCustomerIDTextField;
	JLabel pdComplaintIDLabel;
	JTextField pdComplaintIDTextField;
	
	ViewAllComplaintsTech techComplaintControl;
	/**
	 * Create the frame.
	 */
	public ViewAllComplaintsTechnician(Client client) {
		setResizable(false);
		setMinimumSize(new Dimension(1100, 500));
		setMaximumSize(new Dimension(1100, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(125,45);
		setTitle("View All Complaints");
		techComplaintControl = new ViewAllComplaintsTech(this);
		techComplaintControl.setClient(client);
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
							noServiceDetailPanel.setVisible(true);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(false);
							
							String nsType = "No Service";
							techComplaintControl.populateNSTableForTech(nsType);
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
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(true);
							pDestructionDetailPanel.setVisible(false);
							
							String bcType = "Bill Complaint";
							techComplaintControl.populateBCTableForTech(bcType);
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
							noServiceDetailPanel.setVisible(false);
							billComplaintDetailPanel.setVisible(false);
							pDestructionDetailPanel.setVisible(true);
							
							String pdType = "Property Destruction";
							techComplaintControl.populatePDTableForRep(pdType);
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
							techComplaintControl.returnToTechGui();
						}
					}
				});
			}
		});
		exitButton.setBounds(10, 415, 89, 23);
		scomplaintsOptionsTab.add(exitButton);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(273, 11, 811, 449);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
			
			noServiceDetailPanel = new JPanel();
			noServiceDetailPanel.setBounds(0, 0, 811, 449);
			noServiceDetailPanel.setVisible(false);
			layeredPane.add(noServiceDetailPanel);
			noServiceDetailPanel.setLayout(null);
			
					noServiceTableSPane = new JScrollPane();
					noServiceTableSPane.setBounds(0, 0, 811, 275);
					noServiceDetailPanel.add(noServiceTableSPane);
					
					noServiceDetailTable = new JTable();
					noServiceDetailTable.setModel(new DefaultTableModel(
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
					noServiceDetailTable.getColumnModel().getColumn(1).setPreferredWidth(91);
					noServiceDetailTable.getColumnModel().getColumn(2).setPreferredWidth(94);
					noServiceTableSPane.setViewportView(noServiceDetailTable);
					
					nSResponseLabel = new JLabel("Response");
					nSResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
					nSResponseLabel.setBounds(352, 276, 93, 27);
					noServiceDetailPanel.add(nSResponseLabel);
					
					nSResponseTextField = new JTextField();
					nSResponseTextField.setBounds(455, 276, 346, 79);
					noServiceDetailPanel.add(nSResponseTextField);
					nSResponseTextField.setColumns(10);
					
					nSResponseDateLabel = new JLabel("Date");
					nSResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
					nSResponseDateLabel.setBounds(389, 374, 56, 27);
					noServiceDetailPanel.add(nSResponseDateLabel);
					
					nSDateTextField = new JTextField();
					nSDateTextField.setBounds(455, 378, 176, 22);
					noServiceDetailPanel.add(nSDateTextField);
					nSDateTextField.setColumns(10);
					
					nSSubmitResponseButton = new JButton("Submit");
					nSSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 15));
					
					nSSubmitResponseButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							nSSubmitResponseButton.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent event) {
									if(SwingUtilities.isLeftMouseButton(event)) {
										techComplaintControl.submitNSResponseToCustomer();
									}
								}
							});
						}
					});
					
					nSSubmitResponseButton.setBounds(702, 411, 99, 27);
					noServiceDetailPanel.add(nSSubmitResponseButton);
					
					nsCustomerIDLabel = new JLabel("Customer ID");
					nsCustomerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
					nsCustomerIDLabel.setBounds(10, 276, 115, 27);
					noServiceDetailPanel.add(nsCustomerIDLabel);
					
					nsCustomerIDTextField = new JTextField();
					nsCustomerIDTextField.setColumns(10);
					nsCustomerIDTextField.setBounds(135, 276, 176, 22);
					noServiceDetailPanel.add(nsCustomerIDTextField);
					
					nsComplaintIDLabel = new JLabel("Complaint ID");
					nsComplaintIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
					nsComplaintIDLabel.setBounds(10, 382, 115, 27);
					noServiceDetailPanel.add(nsComplaintIDLabel);
					
					nsComplaintIDTextField = new JTextField();
					nsComplaintIDTextField.setColumns(10);
					nsComplaintIDTextField.setBounds(135, 379, 176, 22);
					noServiceDetailPanel.add(nsComplaintIDTextField);
			
			
			billComplaintDetailPanel = new JPanel();
			billComplaintDetailPanel.setBounds(0, 0, 811, 449);
			billComplaintDetailPanel.setVisible(false);
			layeredPane.add(billComplaintDetailPanel);
			billComplaintDetailPanel.setLayout(null);
			
			billCTableSPane = new JScrollPane();
			billCTableSPane.setBounds(0, 0, 811, 275);
			billComplaintDetailPanel.add(billCTableSPane);
			
			billCDetailTable = new JTable();
			billCDetailTable.setModel(new DefaultTableModel(
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
			billCDetailTable.getColumnModel().getColumn(1).setPreferredWidth(97);
			billCDetailTable.getColumnModel().getColumn(2).setPreferredWidth(101);
			billCTableSPane.setViewportView(billCDetailTable);
			
			bCResponseLabel = new JLabel("Response");
			bCResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
			bCResponseLabel.setBounds(352, 276, 93, 27);
			billComplaintDetailPanel.add(bCResponseLabel);
			
			bCResponseTextField = new JTextField();
			bCResponseTextField.setBounds(455, 276, 346, 79);
			billComplaintDetailPanel.add(bCResponseTextField);
			bCResponseTextField.setColumns(10);
			
			bCResponseDateLabel = new JLabel("Date");
			bCResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
			bCResponseDateLabel.setBounds(389, 374, 56, 27);
			billComplaintDetailPanel.add(bCResponseDateLabel);
			
			bCDateTextField = new JTextField();
			bCDateTextField.setBounds(455, 378, 176, 22);
			billComplaintDetailPanel.add(bCDateTextField);
			bCDateTextField.setColumns(10);
			
			bCSubmitResponseButton = new JButton("Submit");
			bCSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 15));
			bCSubmitResponseButton.setBounds(702, 411, 99, 27);
			billComplaintDetailPanel.add(bCSubmitResponseButton);
			
			bcCustomerIDLabel = new JLabel("Customer ID");
			bcCustomerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
			bcCustomerIDLabel.setBounds(10, 276, 115, 27);
			billComplaintDetailPanel.add(bcCustomerIDLabel);
			
			bcCustomerIDTextField = new JTextField();
			bcCustomerIDTextField.setColumns(10);
			bcCustomerIDTextField.setBounds(135, 276, 176, 22);
			billComplaintDetailPanel.add(bcCustomerIDTextField);
			
			bcComplaintIDLabel = new JLabel("Complaint ID");
			bcComplaintIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
			bcComplaintIDLabel.setBounds(10, 382, 115, 27);
			billComplaintDetailPanel.add(bcComplaintIDLabel);
			
			bcComplaintIDTextField = new JTextField();
			bcComplaintIDTextField.setColumns(10);
			bcComplaintIDTextField.setBounds(135, 379, 176, 22);
			billComplaintDetailPanel.add(bcComplaintIDTextField);
			
			pDestructionDetailPanel = new JPanel();
			pDestructionDetailPanel.setBounds(0, 0, 811, 449);
			pDestructionDetailPanel.setVisible(false);
			layeredPane.add(pDestructionDetailPanel);
			pDestructionDetailPanel.setLayout(null);
			
			pDTableSPane = new JScrollPane();
			pDTableSPane.setBounds(0, 0, 811, 275);
			pDestructionDetailPanel.add(pDTableSPane);
			
				pDDetailTable = new JTable();
				pDDetailTable.setModel(new DefaultTableModel(
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
				pDDetailTable.getColumnModel().getColumn(1).setPreferredWidth(92);
				pDDetailTable.getColumnModel().getColumn(2).setPreferredWidth(101);
				pDTableSPane.setViewportView(pDDetailTable);
				
				pDResponseLabel = new JLabel("Response");
				pDResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
				pDResponseLabel.setBounds(352, 276, 93, 27);
				pDestructionDetailPanel.add(pDResponseLabel);
				
				pDresponseTextField = new JTextField();
				pDresponseTextField.setBounds(455, 276, 346, 79);
				pDestructionDetailPanel.add(pDresponseTextField);
				pDresponseTextField.setColumns(10);
				
				pDResponseDateLabel = new JLabel("Date");
				pDResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
				pDResponseDateLabel.setBounds(389, 374, 56, 27);
				pDestructionDetailPanel.add(pDResponseDateLabel);
				
				pDDateTextField = new JTextField();
				pDDateTextField.setBounds(455, 378, 176, 22);
				pDestructionDetailPanel.add(pDDateTextField);
				pDDateTextField.setColumns(10);
				
				pDSubmitResponseButton = new JButton("Submit");
				pDSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 15));
				pDSubmitResponseButton.setBounds(702, 411, 99, 27);
				pDestructionDetailPanel.add(pDSubmitResponseButton);
				
				pdCustomerIDLabel = new JLabel("Customer ID");
				pdCustomerIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
				pdCustomerIDLabel.setBounds(10, 276, 115, 27);
				pDestructionDetailPanel.add(pdCustomerIDLabel);
				
				pdCustomerIDTextField = new JTextField();
				pdCustomerIDTextField.setColumns(10);
				pdCustomerIDTextField.setBounds(135, 276, 176, 22);
				pDestructionDetailPanel.add(pdCustomerIDTextField);
				
				pdComplaintIDLabel = new JLabel("Complaint ID");
				pdComplaintIDLabel.setFont(new Font("Dubai", Font.PLAIN, 21));
				pdComplaintIDLabel.setBounds(10, 382, 115, 27);
				pDestructionDetailPanel.add(pdComplaintIDLabel);
				
				pdComplaintIDTextField = new JTextField();
				pdComplaintIDTextField.setColumns(10);
				pdComplaintIDTextField.setBounds(135, 379, 176, 22);
				pDestructionDetailPanel.add(pdComplaintIDTextField);
		setVisible(true);
	}
		
	public void setEmp(Employee employee) {
		techComplaintControl.setEmpObj(employee);
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
	
	public JTextField getNsCustomerIDTextField() {
		return nsCustomerIDTextField;
	}

	public void setNsCustomerIDTextField(JTextField nsCustomerIDTextField) {
		this.nsCustomerIDTextField = nsCustomerIDTextField;
	}

	public JTextField getNsComplaintIDTextField() {
		return nsComplaintIDTextField;
	}

	public void setNsComplaintIDTextField(JTextField nsComplaintIDTextField) {
		this.nsComplaintIDTextField = nsComplaintIDTextField;
	}

	public JTextField getnSResponseTextField() {
		return nSResponseTextField;
	}

	public void setnSResponseTextField(JTextField nSResponseTextField) {
		this.nSResponseTextField = nSResponseTextField;
	}

	public JTextField getnSDateTextField() {
		return nSDateTextField;
	}

	public void setnSDateTextField(JTextField nSDateTextField) {
		this.nSDateTextField = nSDateTextField;
	}

	public JButton getnSSubmitResponseButton() {
		return nSSubmitResponseButton;
	}

	public void setnSSubmitResponseButton(JButton nSSubmitResponseButton) {
		this.nSSubmitResponseButton = nSSubmitResponseButton;
	}

	public JTextField getbCResponseTextField() {
		return bCResponseTextField;
	}

	public void setbCResponseTextField(JTextField bCResponseTextField) {
		this.bCResponseTextField = bCResponseTextField;
	}

	public JTextField getbCDateTextField() {
		return bCDateTextField;
	}

	public void setbCDateTextField(JTextField bCDateTextField) {
		this.bCDateTextField = bCDateTextField;
	}

	public JButton getbCSubmitResponseButton() {
		return bCSubmitResponseButton;
	}

	public void setbCSubmitResponseButton(JButton bCSubmitResponseButton) {
		this.bCSubmitResponseButton = bCSubmitResponseButton;
	}

	public JTextField getpDresponseTextField() {
		return pDresponseTextField;
	}

	public void setpDresponseTextField(JTextField pDresponseTextField) {
		this.pDresponseTextField = pDresponseTextField;
	}

	public JTextField getpDDateTextField() {
		return pDDateTextField;
	}

	public void setpDDateTextField(JTextField pDDateTextField) {
		this.pDDateTextField = pDDateTextField;
	}

	public JButton getpDSubmitResponseButton() {
		return pDSubmitResponseButton;
	}

	public void setpDSubmitResponseButton(JButton pDSubmitResponseButton) {
		this.pDSubmitResponseButton = pDSubmitResponseButton;
	}

	public JTextField getCustomerIDTextField() {
		return nsCustomerIDTextField;
	}

	public void setCustomerIDTextField(JTextField customerIDTextField) {
		this.nsCustomerIDTextField = customerIDTextField;
	}

	public JTextField getComplaintIDTextField() {
		return nsComplaintIDTextField;
	}

	public void setComplaintIDTextField(JTextField complaintIDTextField) {
		this.nsComplaintIDTextField = complaintIDTextField;
	}

	public JTextField getBcCustomerIDTextField() {
		return bcCustomerIDTextField;
	}

	public void setBcCustomerIDTextField(JTextField bcCustomerIDTextField) {
		this.bcCustomerIDTextField = bcCustomerIDTextField;
	}

	public JTextField getBcComplaintTextField() {
		return bcComplaintIDTextField;
	}

	public void setBcComplaintTextField(JTextField bcComplaintTextField) {
		this.bcComplaintIDTextField = bcComplaintTextField;
	}

	public JTextField getPdCustomerIDTextField() {
		return pdCustomerIDTextField;
	}

	public void setPdCustomerIDTextField(JTextField pdCustomerIDTextField) {
		this.pdCustomerIDTextField = pdCustomerIDTextField;
	}

	public JTextField getPdComplaintIDTextField() {
		return pdComplaintIDTextField;
	}

	public void setPdComplaintIDTextField(JTextField pdComplaintIDTextField) {
		this.pdComplaintIDTextField = pdComplaintIDTextField;
	}
}
