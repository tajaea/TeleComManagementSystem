package com.microstar.cablevision.controller;



import java.util.Arrays;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.SignUpView;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Employee;

public class SignUpController {
	SignUpView window;
	private Client con;	
	Customer customer = null;
	Employee employee = null;
	
	public SignUpController(SignUpView view) {
		setWindow(view);
	}
	private void setWindow(SignUpView signupView) {
		window = signupView;
	}
	
	public void signUp() {
		/*
		 * * Assigning the values that is entered in the fields in the sign up page
		 */
		String fname = window.getFirstnameTextField().getText();
		String lname = window.getLastnameTextField().getText();
		String email = window.getEmailTextField().getText();
		String phonenumber = window.getPhonenumberTextField().getText();
		char [] password = window.getUserpasswordField().getPassword();
		char [] confirmpass = window.getUserconfirmpasswordField().getPassword();
		UUID id = UUID.randomUUID(); // Generates unique id number 
		
		/**
		 * Validation of fields 
		 */
		if(!fname.isBlank()&& !lname.isBlank() && !email.isBlank() && !phonenumber.isBlank() && password.length > 0 && confirmpass.length > 0) {
			if(email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
				if(phonenumber.matches("^\\(?([1-9]{3})\\)?[-.\\s]?([0-9]{4})$")) {
					if(Arrays.equals(password, confirmpass)) {
						if(window.getUsertypecomboBox().getSelectedIndex() == 1) {
							if(window.getEmptypecomboBox().getSelectedIndex() == 0) {
								employee = new Employee((String)id.toString().subSequence(0, 6),fname, lname,window.getEmptypecomboBox().getSelectedItem().toString(), email,Integer.parseInt(phonenumber),password);
								con.sendAction("Add Employee");
								con.addEmployee(employee);
							}
							else {
								employee = new Employee((String)id.toString().subSequence(0, 6),fname, lname,window.getEmptypecomboBox().getSelectedItem().toString(), email,Integer.parseInt(phonenumber),password);
								con.sendAction("Add Employee");
								con.addEmployee(employee);
							}
						}
						else {
								customer = new  Customer((String)id.toString().subSequence(0, 6),fname, lname, email,Integer.parseInt(phonenumber),password);
								con.sendAction("Add Customer");
								con.addCustomer(customer);
							}
						routePage(con.SuccessStatus());
					}
					else {
						JOptionPane.showMessageDialog(window,"Passwords do not match.");
					}
				}
				else {
					JOptionPane.showMessageDialog(window,"Invalid phone number.");
				}
			}
			else {
				JOptionPane.showMessageDialog(window,"Invalid email address.");
			}		
		}
		else {
			JOptionPane.showMessageDialog(window,"Please ensure that there are no empty fields.");
		}
		
}
	/**
	 * Checks boolean status returned from server when the signup process was successful
	 * promtps the user with ID number
	 * Routes to the Login screen;
	 * @param status
	 */
	private void routePage(boolean status) {
		if(status) {
			if(customer!=null) {
				JOptionPane.showMessageDialog(window ,"Please pay close attention to this window as it contains your ID NUMBER "+ "which you will use to log into the system "+customer.getCustomerID(),"Important Message",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(employee!=null){
				JOptionPane.showMessageDialog(window ,"Please pay close attention to this window as it contains your ID NUMBER "+ "which you will use to log into the system "+employee.getStaff_Id(),"Important Message",JOptionPane.INFORMATION_MESSAGE);
			}
			window.dispose();
			new LoginView(con);
		}
	}
	/**
	 * switches to the login page and passes the client instance
	 */
	public void getLoginPage() {
		window.dispose();
		new LoginView(con);
	}
	/**
	 * Assignment of client which was passed to the controller
	 * @param client
	 */
	public void setClient(Client client) {
		con = client;
	}
	
	/**
	 * Ends client connection to server
	 */
	public void endConnection() {
		con.closeConnection();
	}

}


