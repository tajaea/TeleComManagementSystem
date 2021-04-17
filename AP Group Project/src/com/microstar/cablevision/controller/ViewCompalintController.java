package com.microstar.cablevision.controller;


import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.MakeComplaint;
import com.microstar.cablevision.views.ViewAllComplaintCustomer;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Complaint;
 

public class ViewCompalintController {

	ViewAllComplaintCustomer ViewComplaintView;
	MakeComplaint complaint;
	Complaint complaintObj; 
	Customer customerObj;
	Object[] row = new Object[5];
	private Client con;
	DefaultTableModel Model = new DefaultTableModel();
	
	
	public ViewCompalintController(ViewAllComplaintCustomer ViewComplaintView){
		this.ViewComplaintView = ViewComplaintView;
		
	}
	public void setClient(Client client) {
		con = client;//Assigning the Client instance that was created
	}
	
	public void populateTable() {
		ArrayList<Complaint> arrayList = new ArrayList<Complaint>();
		
		arrayList=con.readCustomerComplaint(); // Returns an arraylist of the complaints made by the signed in ucustomer
		
		for(Complaint complain:arrayList) {
			
			ViewComplaintView.getModel().insertRow(ViewComplaintView.getTable().getModel().getRowCount(), new Object[] {
				complain.getComplaintID(),complain.getCustomerId(),complain.getType(),
				complain.getDetails(),complain.getStatus(),complain.getComplaintDate(),
				complain.getComplaintTime()
			});
		}
		
		}
	public void returnCustomer() {
		con.sendAction("Get Complaint");
		System.out.println(customerObj);
		con.sendCustomer(customerObj);
	}
	
	public void returnToCustomerDashboard() {

		ViewComplaintView.getFrame().dispose();//close make a complaint window
		new CustomerDashBoard(con).setCustomerObject(customerObj);// return to customer dashboard
	}
	
	public void setCustomer(Customer customerObj) {

		this.customerObj=customerObj;
	}
}
