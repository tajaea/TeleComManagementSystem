package com.microstar.cablevision.controller;
import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Complaint;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.ViewPaymentStatus;


public class ViewPaymentStatusController {

	ViewPaymentStatus ViewPaymentStatus;
	
	Complaint complaintObj; 
	Customer customerObj;
	private Client con;
	public ViewPaymentStatusController(ViewPaymentStatus ViewPaymentStatus) {
		this.ViewPaymentStatus = ViewPaymentStatus;
		
		
	}
	
	public void setClient(Client client) {
		con = client;//Assigning the Client instance that was created
	}
	
	public void returnCustomer() {
		con.sendAction("Get Complaint");
		System.out.println(customerObj);
		con.sendCustomer(customerObj);
	}
	
	public void returnToCustomerDashboard() {
		//viewPaymentHistory.getFrame().dispose();//close  a  window
		new CustomerDashBoard(con).setCustomerObject(customerObj);// return to customer dashboard
	}
	
	
	
	public void setCustomerInformation(Customer customerObj) {

		this.customerObj=customerObj;//Assign the customer object from the database to the object that is in the controller
		
		ViewPaymentStatus.getCustomerIDValue().setText(this.customerObj.getCustomerID());
		ViewPaymentStatus.getLastNameValue().setText(this.customerObj.getLastName());
		ViewPaymentStatus.getPaymentStatus().setText(" Yor balance is in trouble");
		

	} 
}
