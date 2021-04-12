package com.microstar.cablevision.views;

import javax.swing.JFrame;



import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.microstar.cablevision.controller.RepresentativeController;

import microStarCableVision.Client;
import microStarCableVision.Employee;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class EmployeeRepresentativeView extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField greetingsTextField;
	JTextField nameTextField;
	JTextField technicianTextField;
	JTextField txtCustomerQueries;
	JTextField txtCustomerServices;
	JTextField bannerTextField;
	JPanel mainGreetingsPanel;
	JButton signOutButton;
	JLabel userImageLabel;
	JPanel callTechnicianPanel;
	JLabel toolsImageLabel;
	JButton assignTechnicianButton;
	JPanel customerComplaintsPanel;
	JLabel customerComplaintsLabel;
	JButton viewAllButton;
	JPanel customerServicesPanel;
	JButton viewAllServicesButton;
	JLabel customerServicesLabel;
	
	static RepresentativeController repController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					control = new LoginController(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		new EmployeeRepresentativeView(new Client());
	}

	public EmployeeRepresentativeView(Client client) {
		setResizable(false);
		getContentPane().setMinimumSize(new Dimension(1150, 714));
		getContentPane().setMaximumSize(new Dimension(1523, 914));
		setMinimumSize(new Dimension(1150, 714));
		repController = new RepresentativeController(this, null, null);
		repController.setClient(client); 
		setTitle("Representative DashBoard");
		getContentPane().setBackground(new Color(255, 245, 238));
		setBackground(new Color(255, 245, 238));
		getContentPane().setForeground(new Color(192, 192, 192));
		setMaximumSize(new Dimension(1523, 914));
		getContentPane().setLayout(null);
		
		mainGreetingsPanel = new JPanel();
		mainGreetingsPanel.setBounds(156, 11, 953, 65);
		mainGreetingsPanel.setBackground(new Color(30, 144, 255));
		getContentPane().add(mainGreetingsPanel);
		mainGreetingsPanel.setLayout(null);
		
		//Added a mouse listener to the sign out button, to return back to the login page when clicked.
		signOutButton = new JButton("Sign-out");
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signOutButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							repController.getLoginPage();;
						}
					}
				});
			}
		});
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		signOutButton.setBounds(843, 21, 100, 28);
		mainGreetingsPanel.add(signOutButton);
		
		greetingsTextField = new JTextField();
		greetingsTextField.setBackground(new Color(30, 144, 255));
		greetingsTextField.setEditable(false);
		greetingsTextField.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 30));
		greetingsTextField.setText("Good Afternoon,");
		greetingsTextField.setBounds(10, 11, 230, 43);
		mainGreetingsPanel.add(greetingsTextField);
		greetingsTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(new Color(30, 144, 255));
		nameTextField.setEditable(false);
		nameTextField.setFont(new Font("Dubai", Font.BOLD | Font.ITALIC, 30));
		nameTextField.setText("");
		nameTextField.setBounds(242, 11, 268, 43);
		mainGreetingsPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		userImageLabel = new JLabel("User Image");
		userImageLabel.setIcon(new ImageIcon("images\\user.png"));
		userImageLabel.setBounds(21, 11, 125, 105);
		getContentPane().add(userImageLabel);
		
		callTechnicianPanel = new JPanel();
		callTechnicianPanel.setBackground(new Color(245, 245, 220));
		callTechnicianPanel.setBounds(814, 216, 305, 225);
		getContentPane().add(callTechnicianPanel);
		callTechnicianPanel.setLayout(null);
		
		toolsImageLabel = new JLabel("");
		toolsImageLabel.setIcon(new ImageIcon("images\\settings.png"));
		toolsImageLabel.setBounds(124, 11, 71, 66);
		callTechnicianPanel.add(toolsImageLabel);
		
		technicianTextField = new JTextField();
		technicianTextField.setBackground(new Color(245, 245, 220));
		technicianTextField.setEditable(false);
		technicianTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		technicianTextField.setText("TECHNICIAN");
		technicianTextField.setBounds(83, 88, 130, 33);
		callTechnicianPanel.add(technicianTextField);
		technicianTextField.setColumns(10);
		
		assignTechnicianButton = new JButton("Assign Technician");
		assignTechnicianButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		assignTechnicianButton.setBackground(new Color(218, 165, 32));
		assignTechnicianButton.setBounds(10, 132, 285, 79);
		callTechnicianPanel.add(assignTechnicianButton);
		
		customerComplaintsPanel = new JPanel();
		customerComplaintsPanel.setBackground(new Color(245, 245, 220));
		customerComplaintsPanel.setLayout(null);
		customerComplaintsPanel.setBounds(423, 216, 305, 225);
		getContentPane().add(customerComplaintsPanel);
		
		customerComplaintsLabel = new JLabel("");
		customerComplaintsLabel.setIcon(new ImageIcon("images\\customer-service-agent.png"));
		customerComplaintsLabel.setBounds(127, 11, 73, 79);
		customerComplaintsPanel.add(customerComplaintsLabel);
		
		txtCustomerQueries = new JTextField();
		txtCustomerQueries.setBackground(new Color(245, 245, 220));
		txtCustomerQueries.setEditable(false);
		txtCustomerQueries.setText("Customer Complaints");
		txtCustomerQueries.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCustomerQueries.setColumns(10);
		txtCustomerQueries.setBounds(52, 84, 205, 33);
		customerComplaintsPanel.add(txtCustomerQueries);
		
		viewAllButton = new JButton("View All");
		viewAllButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewAllButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							repController.getViewAllComplaintsGui();
						}
					}
				});
			}
		});
		
		viewAllButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewAllButton.setBackground(new Color(218, 165, 32));
		viewAllButton.setBounds(10, 128, 285, 79);
		customerComplaintsPanel.add(viewAllButton);
		
		customerServicesPanel = new JPanel();
		customerServicesPanel.setBackground(new Color(245, 245, 220));
		customerServicesPanel.setLayout(null);
		customerServicesPanel.setBounds(21, 216, 305, 225);
		getContentPane().add(customerServicesPanel);
		
		txtCustomerServices = new JTextField();
		txtCustomerServices.setBackground(new Color(245, 245, 220));
		txtCustomerServices.setText("Customer Services");
		txtCustomerServices.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCustomerServices.setEditable(false);
		txtCustomerServices.setColumns(10);
		txtCustomerServices.setBounds(53, 81, 183, 33);
		customerServicesPanel.add(txtCustomerServices);
		
		viewAllServicesButton = new JButton("View All");
		viewAllServicesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewAllServicesButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							repController.getAllServicesGui();
						}
					}
				});
			}
		});
		
		viewAllServicesButton.setForeground(new Color(0, 0, 0));
		viewAllServicesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewAllServicesButton.setBackground(new Color(218, 165, 32));
		viewAllServicesButton.setBounds(10, 125, 285, 79);
		customerServicesPanel.add(viewAllServicesButton);
		
		customerServicesLabel = new JLabel("");
		customerServicesLabel.setIcon(new ImageIcon("images\\24-hours.png"));
		customerServicesLabel.setBounds(127, 11, 76, 61);
		customerServicesPanel.add(customerServicesLabel);
		
		bannerTextField = new JTextField();
		bannerTextField.setForeground(new Color(0, 0, 0));
		bannerTextField.setEditable(false);
		bannerTextField.setFont(new Font("Dubai", Font.ITALIC, 25));
		bannerTextField.setBackground(new Color(255, 245, 238));
		bannerTextField.setText("MicroStar CableVision.");
		bannerTextField.setBounds(21, 639, 267, 35);
		getContentPane().add(bannerTextField);
		bannerTextField.setColumns(10);
		setVisible(true);
	}

	public void setEmp(Employee employee) {
		repController.setEmpObj(employee);//return employee object 
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}
	
	
}
