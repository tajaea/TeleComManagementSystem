package microStarCableVision;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Font;

public class CustomerForm {

	private JFrame frame;
	private JTextField idTextField;
	private JTextField fnameTextfield;
	private JTextField lnameTextfield;
	private JTextField emailTextfield;
	private JTextField passwordTextfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerForm window = new CustomerForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 15));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblId.setBounds(85, 37, 109, 29);
		frame.getContentPane().add(lblId);
		
		JLabel lblFirstNme = new JLabel("First Name");
		lblFirstNme.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFirstNme.setBounds(85, 77, 109, 14);
		frame.getContentPane().add(lblFirstNme);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLastName.setBounds(85, 114, 109, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmail.setBounds(85, 151, 48, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(85, 189, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnEnter = new JButton("Sign In");
		btnEnter.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnEnter.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		idTextField = new JTextField();
		idTextField.setBounds(269, 41, 96, 20);
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		fnameTextfield = new JTextField();
		fnameTextfield.setBounds(269, 74, 96, 20);
		frame.getContentPane().add(fnameTextfield);
		fnameTextfield.setColumns(10);
		
		lnameTextfield = new JTextField();
		lnameTextfield.setBounds(269, 107, 96, 20);
		frame.getContentPane().add(lnameTextfield);
		lnameTextfield.setColumns(10);
		
		emailTextfield = new JTextField();
		emailTextfield.setBounds(269, 148, 96, 20);
		frame.getContentPane().add(emailTextfield);
		emailTextfield.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setBounds(269, 186, 96, 20);
		frame.getContentPane().add(passwordTextfield);
		passwordTextfield.setColumns(10);
		
		JLabel lblCustomerSignIn = new JLabel("Customer Sign in");
		lblCustomerSignIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCustomerSignIn.setBounds(137, 12, 202, 14);
		frame.getContentPane().add(lblCustomerSignIn);
	}
}