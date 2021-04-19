package com.microstar.cablevision.controller;
import microStarCableVision.Client;

import microStarCableVision.Customer;
import microStarCableVision.Complaint;

import java.util.ArrayList;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.ViewPaymentHistory;


public class ViewPaymentHistoryController {

	ViewPaymentHistory ViewPaymentHistory;
	
	Complaint complaintObj; 
	Customer customerObj;
	private Client con;
	ArrayList<Complaint> arrayList;
	public ViewPaymentHistoryController( ViewPaymentHistory  ViewPaymentHistory) {
		this. ViewPaymentHistory =  ViewPaymentHistory;
		
		
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

		con.sendAction("");
		
		this.customerObj=customerObj;//Assign the customer object from the database to the object that is in the controller
		
		ViewPaymentHistory.getCustomerIDValue().setText(this.customerObj.getCustomerID());
		ViewPaymentHistory.getLastNameValue().setText(this.customerObj.getLastName());
		//ViewPaymentHistory.getPaymentStatus().setText("");
		 		
			//arrayList = new ArrayList<Complaint>();
		
		arrayList=con.readCustomerComplaint(); // Returns an arraylist of the complaints made by the signed in ucustomer
		
		for(Complaint complain:arrayList) {
			
			ViewPaymentHistory.getModel().insertRow(ViewPaymentHistory.getTable().getModel().getRowCount(), new Object[] {
				
					
			complain.getComplaintID(),complain.getCustomerId(),complain.getType(),
				complain.getDetails(),complain.getStatus(),complain.getComplaintDate(),
				complain.getComplaintTime()
			});
		}

	} 
	public void viewTableDetails() {
		int index = ViewPaymentHistory.getTable().getSelectedRow();
		System.out.println(arrayList.get(index));
	}
	
}
