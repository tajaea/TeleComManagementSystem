package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.ChatView;
import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.MakeComplaint;
import com.microstar.cablevision.views.ViewAllComplaintCustomer;
import com.microstar.cablevision.views.ViewPaymentHistory;
import com.microstar.cablevision.views.ViewPaymentStatus;

import microStarCableVision.Client;
import microStarCableVision.Customer;

public class CustomerDashboardController {
	
	CustomerDashBoard customerDashboardview;
	Customer customerObj;
	//String id = customerObj.getCustomerID();
	private Client con; //Maintain the client instance
	
	public CustomerDashboardController(CustomerDashBoard view) {
		setWindow(view);
	}
	
	private void setWindow(CustomerDashBoard view) {
		// TODO Auto-generated method stub
		customerDashboardview = view;
	}

	public void setClient(Client client) {
		con = client;//Assigning the Client instance that was created
	}
	
	public void OpenChatWindow() {
		customerDashboardview.getFrame().dispose();
		new ChatView(customerObj.getCustomerID(),con).setCustomer(customerObj);
		con.sendAction("chat");
		con.writeUserChatID(customerObj.getCustomerID());
	}
	
	public void SignOut() {
		con.closeConnection();//close the connection to the server
		customerDashboardview.getFrame().dispose();//Close the Dashboard of a customer
		new LoginView(new Client());
	}
	
	public void setCustomerInformation(Customer customerObj) {
		this.customerObj=customerObj;//Assign the customer object from the database to the object that is in the controller
		
		customerDashboardview.getLblFirstName().setText(this.customerObj.getFirstName());
		customerDashboardview.getLblLastname().setText(this.customerObj.getLastName());
		String tele = Integer.toString(this.customerObj.getTelephoneNumber());
		
		customerDashboardview.getLblTeleno().setText(tele);
		customerDashboardview.getLblEmail().setText(this.customerObj.getEmail());
	} 
	
	public void getMakeComplaint() {
		customerDashboardview.getFrame().dispose();//Close the Dashboard of a customer
		//new MakeComplaint(con).setComplaintObject(complaintObj);
		new MakeComplaint(con).setCustomerInformation(customerObj);
	}
	
	public void getViewAllComplaint() {
		new ViewAllComplaintCustomer(con).setCustomerInformation(customerObj); 
	}
	
	public void endConnection() {
		con.closeConnection();
	}
	
	public void getViewPaymentStatus() {
		new ViewPaymentStatus(con).setCustomerInformation(customerObj); 		
	}
	
	public void getViewPaymentHistory() {
		new ViewPaymentHistory(con).setCustomerInformation(customerObj); 		
	}
}
