package com.microstar.cablevision.controller;

import javax.swing.JOptionPane;


import com.microstar.cablevision.security.Authentication;
import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.EmployeeRepresentativeView;
import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.SignUpView;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Employee;

public class LoginController {
	LoginView window;
	private  Client con;
	
	public LoginController(LoginView view) {
		setWindow(view);
	}
	private void setWindow(LoginView view) {
		window = view;
	}
	public void setClient(Client client) {
		con = client;
	}
	
	public void Login() {
		char [] password = window.getPasswordField().getPassword();
		String idnum = window.getIdTextField().getText();
		if(password.length>0 && !idnum.isBlank()) {
			Authentication auth = new Authentication(idnum,password);
			System.out.println(auth);
			con.sendAction("User Login");
			con.sendUser(auth);
			String usertype = con.getUserType();
			if(usertype.equals("CUS")) {
				if(con.getloginValue()==80) {
					Customer customer = con.getCustomer();
					System.out.println(customer); 
					window.dispose();
					new CustomerDashBoard(con).setCustomerObject(customer);//customer dashboard
				
				}
				else {
					JOptionPane.showMessageDialog(window, "Invalid ID number or password","Login Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(usertype.equals("EMP")){
				if(con.getloginValue()==80) {
					Employee employee = con.getEmployee(); 
					System.out.println(employee);
					window.dispose();
					if(employee.getJob_Title().equalsIgnoreCase("Technician")) {
						new EmployeeTechnicianView(con).setEmp(employee);
					}else {
						new EmployeeRepresentativeView(con).setEmp(employee);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(window, "Invalid ID number or password","Login Error",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				con.getloginValue();
				JOptionPane.showMessageDialog(window, "Invalid ID number or password","Login Error",JOptionPane.ERROR_MESSAGE);
				
			}
			//con.receiveResponse();
			/*amount = con.numOfUsers();
			System.out.println("amount: "+amount);
			for(int i=0; i<amount;i++) {
			System.out.println(con.UsersOnlineState());
			}*/
			//System.out.println(con.UsersOnlineState());
			//con.refreshSocket();
		}
		else {
			JOptionPane.showMessageDialog(window,"Please ensure that there are no empty fields.");
		}
	}
	public void endConnection() {
		con.closeConnection();
	}
	
	/**
	 * routes to the sign up page
	 */
	public void getSignUpPage() {
		window.dispose();
		new SignUpView(con);
	}

}
