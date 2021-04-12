package com.microstar.cablevision.views;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.microstar.cablevision.controller.SignUpController;

import microStarCableVision.Client;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField emailTextField;
	private JTextField phonenumberTextField;
	private JPasswordField userpasswordField;
	private JPasswordField userconfirmpasswordField;
	JButton signupButton;
	JLabel loginLabel;
	JComboBox usertypecomboBox;
	JComboBox emptypecomboBox;
	SignUpController control;

	
	public SignUpView(Client con) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 482);
		setTitle("Sign Up");
		setLocation(490,180);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		control = new SignUpController(this);
		control.setClient(con);
		JPanel signupHeaderPanel = new JPanel();
		signupHeaderPanel.setBounds(0, 0, 434, 40);
		contentPane.add(signupHeaderPanel);
		signupHeaderPanel.setLayout(null);
		
		JLabel signUpLabel = new JLabel("Sign Up");
		signUpLabel.setBounds(195, 6, 62, 23);
		signUpLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		signupHeaderPanel.add(signUpLabel);
		
		JPanel signupBodyPanel = new JPanel();
		signupBodyPanel.setBounds(0, 40, 434, 344);
		contentPane.add(signupBodyPanel);
		signupBodyPanel.setLayout(null);
		
		JLabel firstnameLabel = new JLabel("Firstname");
		firstnameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		firstnameLabel.setBounds(34, 11, 105, 24);
		signupBodyPanel.add(firstnameLabel);
		
		firstnameTextField = new JTextField();
		firstnameTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
		firstnameTextField.setColumns(10);
		firstnameTextField.setBounds(166, 11, 200, 24);
		signupBodyPanel.add(firstnameTextField);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		emailLabel.setBounds(34, 101, 105, 24);
		signupBodyPanel.add(emailLabel);
		
		JLabel lastnameLabel = new JLabel("Lastname");
		lastnameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lastnameLabel.setBounds(34, 56, 105, 24);
		signupBodyPanel.add(lastnameLabel);
		
		JLabel lblNewLabel = new JLabel("Phone Number");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 145, 105, 24);
		signupBodyPanel.add(lblNewLabel);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
		lastnameTextField.setBounds(166, 56, 200, 24);
		signupBodyPanel.add(lastnameTextField);
		lastnameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
		emailTextField.setBounds(166, 101, 200, 24);
		signupBodyPanel.add(emailTextField);
		emailTextField.setColumns(10);
		
		phonenumberTextField = new JTextField();
		phonenumberTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
		phonenumberTextField.setBounds(166, 145, 200, 24);
		signupBodyPanel.add(phonenumberTextField);
		phonenumberTextField.setColumns(10);
		
		JLabel usertypeLabel = new JLabel("User Type");
		usertypeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		usertypeLabel.setBounds(34, 192, 105, 24);
		signupBodyPanel.add(usertypeLabel);
		
		String[] usertypeList = {"Customer","Employee"};
		usertypecomboBox = new JComboBox(usertypeList);
		usertypecomboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		usertypecomboBox.setBounds(166, 191, 97, 22);
		signupBodyPanel.add(usertypecomboBox);
		
		JLabel userpasswordLabel = new JLabel("Password");
		userpasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		userpasswordLabel.setBounds(34, 241, 105, 24);
		signupBodyPanel.add(userpasswordLabel);
		
		userpasswordField = new JPasswordField();
		userpasswordField.setFont(new Font("Calibri", Font.PLAIN, 14));
		userpasswordField.setBounds(163, 241, 200, 24);
		signupBodyPanel.add(userpasswordField);
		
		JLabel confirmpasswordLabel = new JLabel("Confirm Password");
		confirmpasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		confirmpasswordLabel.setBounds(34, 291, 105, 24);
		signupBodyPanel.add(confirmpasswordLabel);
		
		userconfirmpasswordField = new JPasswordField();
		userconfirmpasswordField.setFont(new Font("Calibri", Font.PLAIN, 14));
		userconfirmpasswordField.setBounds(163, 291, 200, 24);
		signupBodyPanel.add(userconfirmpasswordField);
		
		String[] emptypeList = {"Representative","Technician"};
		emptypecomboBox = new JComboBox(emptypeList);
		emptypecomboBox.setVisible(false);
		emptypecomboBox.setBounds(273, 191, 93, 22);
		signupBodyPanel.add(emptypecomboBox);
		
		JPanel signupFooterPanel = new JPanel();
		signupFooterPanel.setBounds(0, 382, 434, 61);
		contentPane.add(signupFooterPanel);
		signupFooterPanel.setLayout(null);
		
		signupButton = new JButton("Get Started");
		signupButton.setFocusable(false);
		signupButton.setVerticalAlignment(SwingConstants.BOTTOM);
		signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupButton.setFont(new Font("Calibri", Font.BOLD, 14));
		signupButton.setBounds(274, 14, 105, 28);
		signupFooterPanel.add(signupButton);
		
		JLabel messageLabel = new JLabel("Already a user?");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		messageLabel.setBounds(34, 18, 90, 14);
		signupFooterPanel.add(messageLabel);
		
		loginLabel = new JLabel("Login");
		loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		loginLabel.setBounds(118, 19, 35, 14);
		signupFooterPanel.add(loginLabel);
		this.setVisible(true);
		registerButton();
		
	}
	
	private void registerButton() {
		loginLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isLeftMouseButton(event)) {
					control.getLoginPage();
				}
			}
		});
		
		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.signUp();
			}
			
		});
		
		usertypecomboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(usertypecomboBox.getSelectedItem().toString()=="Employee") {
					emptypecomboBox.setVisible(true);
				}
				else
				{
					emptypecomboBox.setVisible(false);
				}
			}
		});
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				control.endConnection();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public JTextField getFirstnameTextField() {
		return firstnameTextField;
	}

	public void setFirstnameTextField(JTextField firstnameTextField) {
		this.firstnameTextField = firstnameTextField;
	}

	public JTextField getLastnameTextField() {
		return lastnameTextField;
	}

	public void setLastnameTextField(JTextField lastnameTextField) {
		this.lastnameTextField = lastnameTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(JTextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	public JTextField getPhonenumberTextField() {
		return phonenumberTextField;
	}

	public void setPhonenumberTextField(JTextField phonenumberTextField) {
		this.phonenumberTextField = phonenumberTextField;
	}

	public JPasswordField getUserpasswordField() {
		return userpasswordField;
	}

	public void setUserpasswordField(JPasswordField userpasswordField) {
		this.userpasswordField = userpasswordField;
	}

	public JPasswordField getUserconfirmpasswordField() {
		return userconfirmpasswordField;
	}

	public void setUserconfirmpasswordField(JPasswordField userconfirmpasswordField) {
		this.userconfirmpasswordField = userconfirmpasswordField;
	}

	public JComboBox getUsertypecomboBox() {
		return usertypecomboBox;
	}

	public void setUsertypecomboBox(JComboBox usertypecomboBox) {
		this.usertypecomboBox = usertypecomboBox;
	}

	public JComboBox getEmptypecomboBox() {
		return emptypecomboBox;
	}

	public void setEmptypecomboBox(JComboBox emptypecomboBox) {
		this.emptypecomboBox = emptypecomboBox;
	}
	
	
}
