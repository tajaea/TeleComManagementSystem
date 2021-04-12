package com.microstar.cablevision.views;

import java.awt.Color;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.microstar.cablevision.controller.TechnicianController;

import microStarCableVision.Client;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	JPanel complaintsOptionsDetails;
	JPanel noServiceDetailPanel;
	JPanel billComplaintDetailPanel;
	JPanel pDestructionDetailPanel;
	JScrollBar scrollBar;
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
	JTextField nSResponseTextField;
	JTextField nSDateTextField;
	JLabel nSResponseLabel;
	JLabel nSResponseDateLabel;
	JButton nSSubmitResponseButton;
	JLabel bCResponseLabel;
	JLabel bCResponseDateLabel;
	JTextField bCResponseTectField;
	JTextField bCDateTextField;
	JButton bCSubmitResponseButton;
	JLabel pDResponseLabel;
	JTextField pDRessponseTextField;
	JButton pDSubmitResponseButton;
	JLabel pDResponseDateLabel;
	JTextField pDDateTextField;
	
	static TechnicianController techController;
	
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllComplaintsTechnician frame = new ViewAllComplaintsTechnician(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		new ViewAllComplaintsTechnician(new Client());
	}
	/**
	 * Create the frame.
	 */
	public ViewAllComplaintsTechnician(Client client) {
		setResizable(false);
		setMinimumSize(new Dimension(1100, 500));
		setMaximumSize(new Dimension(1100, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		techController = new TechnicianController(null, this);
		techController.setClient(client);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setMinimumSize(new Dimension(1100, 500));
		contentPane.setMaximumSize(new Dimension(1100, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scomplaintsOptionsTab = new JPanel();
		scomplaintsOptionsTab.setBackground(new Color(255, 245, 238));
		scomplaintsOptionsTab.setBounds(10, 11, 253, 449);
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
						}
					}
				});
			}
		});
		billCButton.setBounds(10, 218, 233, 37);
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
						}
					}
				});
			}
		});
		pDestructionButton.setBounds(10, 327, 233, 37);
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
							techController.returnToTechGui();
						}
					}
				});
			}
		});
		exitButton.setBounds(10, 415, 89, 23);
		scomplaintsOptionsTab.add(exitButton);
		
		complaintsOptionsDetails = new JPanel();
		complaintsOptionsDetails.setBackground(new Color(255, 245, 238));
		complaintsOptionsDetails.setBounds(273, 11, 784, 449);
		contentPane.add(complaintsOptionsDetails);
		complaintsOptionsDetails.setLayout(null);
		
		noServiceDetailPanel = new JPanel();
		noServiceDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(noServiceDetailPanel);
		noServiceDetailPanel.setLayout(null);
		
		noServiceTableSPane = new JScrollPane();
		noServiceTableSPane.setBounds(10, 270, 744, -258);
		noServiceDetailPanel.add(noServiceTableSPane);
		
		noServiceDetailTable = new JTable();
		noServiceTableSPane.setViewportView(noServiceDetailTable);
		
		nSResponseLabel = new JLabel("Response");
		nSResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		nSResponseLabel.setBounds(311, 274, 88, 31);
		noServiceDetailPanel.add(nSResponseLabel);
		
		nSResponseTextField = new JTextField();
		nSResponseTextField.setEditable(true);
		nSResponseTextField.setBounds(409, 268, 345, 97);
		noServiceDetailPanel.add(nSResponseTextField);
		nSResponseTextField.setColumns(10);
		
		nSResponseDateLabel = new JLabel("Date");
		nSResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		nSResponseDateLabel.setBounds(350, 392, 49, 24);
		noServiceDetailPanel.add(nSResponseDateLabel);
		
		nSDateTextField = new JTextField();
		nSDateTextField.setEditable(true);
		nSDateTextField.setBounds(409, 396, 126, 20);
		noServiceDetailPanel.add(nSDateTextField);
		nSDateTextField.setColumns(10);
		
		nSSubmitResponseButton = new JButton("Submit");
		nSSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 16));
		nSSubmitResponseButton.setBounds(665, 394, 89, 23);
		noServiceDetailPanel.add(nSSubmitResponseButton);
		
		billComplaintDetailPanel = new JPanel();
		billComplaintDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(billComplaintDetailPanel);
		billComplaintDetailPanel.setLayout(null);
		
		billCTableSPane = new JScrollPane();
		billCTableSPane.setBounds(10, 270, 744, -258);
		billComplaintDetailPanel.add(billCTableSPane);
		
		billCDetailTable = new JTable();
		billCTableSPane.setViewportView(billCDetailTable);
		
		bCResponseLabel = new JLabel("Response");
		bCResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		bCResponseLabel.setBounds(322, 278, 81, 35);
		billComplaintDetailPanel.add(bCResponseLabel);
		
		bCResponseTectField = new JTextField();
		bCResponseTectField.setEditable(true);
		bCResponseTectField.setBounds(413, 271, 341, 101);
		billComplaintDetailPanel.add(bCResponseTectField);
		bCResponseTectField.setColumns(10);
		
		bCResponseDateLabel = new JLabel("Date");
		bCResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		bCResponseDateLabel.setBounds(355, 395, 48, 21);
		billComplaintDetailPanel.add(bCResponseDateLabel);
		
		bCDateTextField = new JTextField();
		bCDateTextField.setEditable(true);
		bCDateTextField.setBounds(413, 397, 120, 20);
		billComplaintDetailPanel.add(bCDateTextField);
		bCDateTextField.setColumns(10);
		
		bCSubmitResponseButton = new JButton("Submit");
		bCSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 16));
		bCSubmitResponseButton.setBounds(665, 395, 89, 23);
		billComplaintDetailPanel.add(bCSubmitResponseButton);
		
		pDestructionDetailPanel = new JPanel();
		pDestructionDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(pDestructionDetailPanel);
		pDestructionDetailPanel.setLayout(null);
		
		pDTableSPane = new JScrollPane();
		pDTableSPane.setBounds(10, 270, 744, -258);
		pDestructionDetailPanel.add(pDTableSPane);
		
		pDDetailTable = new JTable();
		pDTableSPane.setViewportView(pDDetailTable);
		
		pDResponseLabel = new JLabel("Response");
		pDResponseLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		pDResponseLabel.setBounds(323, 271, 82, 35);
		pDestructionDetailPanel.add(pDResponseLabel);
		
		pDRessponseTextField = new JTextField();
		pDRessponseTextField.setEditable(true);
		pDRessponseTextField.setBounds(415, 270, 339, 101);
		pDestructionDetailPanel.add(pDRessponseTextField);
		pDRessponseTextField.setColumns(10);
		
		pDSubmitResponseButton = new JButton("Submit");
		pDSubmitResponseButton.setFont(new Font("Dubai", Font.PLAIN, 16));
		pDSubmitResponseButton.setBounds(665, 392, 89, 23);
		pDestructionDetailPanel.add(pDSubmitResponseButton);
		
		pDResponseDateLabel = new JLabel("Date");
		pDResponseDateLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
		pDResponseDateLabel.setBounds(350, 393, 55, 19);
		pDestructionDetailPanel.add(pDResponseDateLabel);
		
		pDDateTextField = new JTextField();
		pDDateTextField.setEditable(true);
		pDDateTextField.setBounds(415, 394, 118, 20);
		pDestructionDetailPanel.add(pDDateTextField);
		pDDateTextField.setColumns(10);
		
		scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(255, 245, 238));
		scrollBar.setBackground(new Color(255, 245, 238));
		scrollBar.setBounds(1067, 11, 17, 449);
		contentPane.add(scrollBar);
		setVisible(true);
	}
}
