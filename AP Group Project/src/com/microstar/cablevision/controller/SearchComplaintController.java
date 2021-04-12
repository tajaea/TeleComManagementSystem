package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.MakeComplaint;
import com.microstar.cablevision.views.SearchComplaint;
import com.microstar.cablevision.views.ViewAllComplaintCustomer;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Complaint;
public class SearchComplaintController {
	SearchComplaint searchComplaint;
	Complaint complaintObj; 
	Customer customerObj;
	Object[] row = new Object[5];
	private Client con;
	
	public SearchComplaintController(SearchComplaint searchComplaint) {
		searchComplaint = searchComplaint;
				}

	public void setClient(Client client) {

		con = client;
	}

	public void returnTodasboard() {
		searchComplaint.getFrame().dispose();
		//searchComplaint.getFrame().dispose();//close make a complaint window
		new CustomerDashBoard(con);// return to customer dashboardod stub
		
	}
	

}
