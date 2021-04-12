package com.microstar.cablevision.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.microstar.cablevision.controller.RepresentativeController;

import microStarCableVision.Client;

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
	
	static RepresentativeController repController;
	
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
		repController = new RepresentativeController(null, this, null);
		repController.setClient(client);
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
							repController.returnToRepGui1();
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
		noServiceTableSPane.setBounds(10, 415, 744, -403);
		noServiceDetailPanel.add(noServiceTableSPane);
		
		noServiceDetailTable = new JTable();
		noServiceTableSPane.setViewportView(noServiceDetailTable);
		
		billComplaintDetailPanel = new JPanel();
		billComplaintDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(billComplaintDetailPanel);
		billComplaintDetailPanel.setLayout(null);
		
		billCTableSPane = new JScrollPane();
		billCTableSPane.setBounds(10, 416, 744, -404);
		billComplaintDetailPanel.add(billCTableSPane);
		
		billCDetailTable = new JTable();
		billCTableSPane.setViewportView(billCDetailTable);
		
		pDestructionDetailPanel = new JPanel();
		pDestructionDetailPanel.setBounds(10, 11, 764, 427);
		complaintsOptionsDetails.add(pDestructionDetailPanel);
		pDestructionDetailPanel.setLayout(null);
		
		pDTableSPane = new JScrollPane();
		pDTableSPane.setBounds(10, 412, 744, -400);
		pDestructionDetailPanel.add(pDTableSPane);
		
		pDDetailTable = new JTable();
		pDTableSPane.setViewportView(pDDetailTable);
		
		scrollBar = new JScrollBar();
		scrollBar.setForeground(new Color(255, 245, 238));
		scrollBar.setBackground(new Color(255, 245, 238));
		scrollBar.setBounds(1067, 11, 17, 449);
		contentPane.add(scrollBar);
		setVisible(true);
	}
}
