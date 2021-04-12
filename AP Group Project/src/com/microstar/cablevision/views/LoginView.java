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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.microstar.cablevision.controller.LoginController;

import microStarCableVision.Client;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JLabel titleLabel;
	private JLabel errorLabel;
	private JLabel messageLabel;
	private JPanel panel;
	private JButton loginBtn;
	static LoginController control;
	private JLabel signupLabel;

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
		
		new LoginView(new Client());
	}

	/**
	 * Create the frame.
	 */
	public LoginView(Client client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		setTitle("Login");
		setLocation(500,250);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		control = new LoginController(this);
		control.setClient(client);
		JLabel userIdLabel = new JLabel("ID Number");
		userIdLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		userIdLabel.setBounds(94, 46, 76, 16);
		contentPane.add(userIdLabel);
		
		JLabel passwrodLabel = new JLabel("Password");
		passwrodLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		passwrodLabel.setBounds(94, 73, 76, 16);
		contentPane.add(passwrodLabel);
		
		idTextField = new JTextField();
		idTextField.setToolTipText("");
		idTextField.setBounds(201, 43, 120, 20);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 70, 120, 20);
		contentPane.add(passwordField);
		
		titleLabel = new JLabel("Login");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		titleLabel.setBounds(94, 11, 65, 21);
		contentPane.add(titleLabel);
		
	    loginBtn = new JButton("Login");
		loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginBtn.setFont(new Font("Calibri", Font.BOLD, 14));
		loginBtn.setBounds(176, 119, 89, 27);
		contentPane.add(loginBtn);
		
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setBounds(94, 100, 227, 14);
		contentPane.add(errorLabel);
		
		panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBounds(157, 157, 150, 20);
		contentPane.add(panel);
		panel.setLayout(null);
		
		messageLabel = new JLabel("Not a Customer? ");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		messageLabel.setBounds(0, 0, 88, 20);
		panel.add(messageLabel);
		
		signupLabel = new JLabel("Sign Up");
		signupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signupLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		signupLabel.setBounds(90, 2, 46, 17);
		panel.add(signupLabel);
		setVisible(true); 
		registerButton();
	}
	
private void registerButton() {
		
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.Login();
				
			}
			
		});
		
	signupLabel.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			if(SwingUtilities.isLeftMouseButton(event)) {
				control.getSignUpPage();
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

public JTextField getIdTextField() {
	return idTextField;
}

public void setIdTextField(JTextField idTextField) {
	this.idTextField = idTextField;
}

public JPasswordField getPasswordField() {
	return passwordField;
}

public void setPasswordField(JPasswordField passwordField) {
	this.passwordField = passwordField;
}

public JLabel getErrorLabel() {
	return errorLabel;
}

public void setErrorLabel(JLabel errorLabel) {
	this.errorLabel = errorLabel;
}


}
