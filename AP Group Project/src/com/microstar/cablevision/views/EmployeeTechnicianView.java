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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.microstar.cablevision.controller.TechnicianController;

import microStarCableVision.Client;
import microStarCableVision.Employee;

public class EmployeeTechnicianView extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField greetingsTextField;
	JTextField nameTextField;
	JTextField technicianTextField;
	JTextField txtCustomerComplaints;
	JTextField bannerTextField;
	JPanel mainGreetingsPanel;
	JButton signOutButton;
	JLabel userImageLabel;
	JPanel customerLiveChatPanel;
	JLabel toolsImageLabel;
	JButton liveChatButton;
	JPanel customerComplaintPanel;
	JLabel customerServicesLabel;
	JButton viewAllComplaintsButton;
	
	static TechnicianController techController;
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
		
		new EmployeeTechnicianView(new Client());
	}

	public EmployeeTechnicianView(Client client) {
		setResizable(false);
		getContentPane().setMinimumSize(new Dimension(1150, 714));
		getContentPane().setMaximumSize(new Dimension(1523, 914));
		setMinimumSize(new Dimension(1150, 714));
		techController = new TechnicianController(this, null);
		techController.setClient(client);
		setTitle("Technician DashBoard");
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
		
		signOutButton = new JButton("Sign-out");
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signOutButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							techController.getLoginPage();
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
		nameTextField.setBounds(239, 11, 255, 43);
		mainGreetingsPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		userImageLabel = new JLabel("User Image");
		userImageLabel.setIcon(new ImageIcon("images\\user.png"));
		userImageLabel.setBounds(21, 11, 125, 105);
		getContentPane().add(userImageLabel);
		
		customerLiveChatPanel = new JPanel();
		customerLiveChatPanel.setBackground(new Color(245, 245, 220));
		customerLiveChatPanel.setBounds(618, 216, 305, 225);
		getContentPane().add(customerLiveChatPanel);
		customerLiveChatPanel.setLayout(null);
		
		toolsImageLabel = new JLabel("");
		toolsImageLabel.setIcon(new ImageIcon("images\\live-chat.png"));
		toolsImageLabel.setBounds(115, 11, 71, 66);
		customerLiveChatPanel.add(toolsImageLabel);
		
		technicianTextField = new JTextField();
		technicianTextField.setBackground(new Color(245, 245, 220));
		technicianTextField.setEditable(false);
		technicianTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		technicianTextField.setText("Live Chat");
		technicianTextField.setBounds(100, 88, 101, 33);
		customerLiveChatPanel.add(technicianTextField);
		technicianTextField.setColumns(10);
		
		liveChatButton = new JButton("Talk Now");
		liveChatButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		liveChatButton.setBackground(new Color(218, 165, 32));
		liveChatButton.setBounds(10, 132, 285, 79);
		customerLiveChatPanel.add(liveChatButton);
		
		liveChatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				liveChatButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						// TODO Auto-generated method stub
						if(SwingUtilities.isLeftMouseButton(event)) {
							techController.getChatViewPage();
						}
					}
				});
			}
		});
		
		customerComplaintPanel = new JPanel();
		customerComplaintPanel.setBackground(new Color(245, 245, 220));
		customerComplaintPanel.setLayout(null);
		customerComplaintPanel.setBounds(180, 216, 305, 225);
		getContentPane().add(customerComplaintPanel);
		
		customerServicesLabel = new JLabel("");
		customerServicesLabel.setIcon(new ImageIcon("images\\feedback.png"));
		customerServicesLabel.setBounds(123, 11, 73, 79);
		customerComplaintPanel.add(customerServicesLabel);
		
		txtCustomerComplaints = new JTextField();
		txtCustomerComplaints.setBackground(new Color(245, 245, 220));
		txtCustomerComplaints.setText("Customer Complaints");
		txtCustomerComplaints.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCustomerComplaints.setEditable(false);
		txtCustomerComplaints.setColumns(10);
		txtCustomerComplaints.setBounds(53, 81, 209, 33);
		customerComplaintPanel.add(txtCustomerComplaints);
		
		viewAllComplaintsButton = new JButton("View All");
		viewAllComplaintsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewAllComplaintsButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(SwingUtilities.isLeftMouseButton(event)) {
							techController.getViewAllComplaintsGui();
						}
					}
				});
			}
		});
		
		viewAllComplaintsButton.setForeground(new Color(0, 0, 0));
		viewAllComplaintsButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		viewAllComplaintsButton.setBackground(new Color(218, 165, 32));
		viewAllComplaintsButton.setBounds(10, 125, 285, 79);
		customerComplaintPanel.add(viewAllComplaintsButton);
		
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
		techController.setEmpObj(employee);
		
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}
}
