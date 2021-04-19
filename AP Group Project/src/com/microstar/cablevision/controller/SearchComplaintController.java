package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
	DefaultTableModel model = new DefaultTableModel();
	ArrayList<Complaint> complaintList;
	Client con;
	
	public SearchComplaintController(SearchComplaint view) {
		setWindow(view);
	}

	private void setWindow(SearchComplaint view) {
		// TODO Auto-generated method stub
		searchComplaint = view;
	}

	public void setClient(Client client) {
		con = client;
	}
	
	public void searchComplaintForEmployee(int id) {
		con.sendAction("Search Complaint");
		con.searchComplaintID(id);
		
		//ArrayList<Complaint> nsComplaintList = new ArrayList<Complaint>();
		complaintList = con.readComplaints();
		
		if(searchComplaint.getTable().getModel().getRowCount()<0) {
			for(Complaint complaint: complaintList) {
				((DefaultTableModel)searchComplaint.getTable().getModel()).insertRow(searchComplaint.getTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)searchComplaint.getTable().getModel()).setRowCount(0);
			for(Complaint complaint: complaintList) {
				((DefaultTableModel)searchComplaint.getTable().getModel()).insertRow(searchComplaint.getTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
	}

	public void returnTodasboard() {
		searchComplaint.getFrame().dispose();
		//searchComplaint.getFrame().dispose();//close make a complaint window
		new CustomerDashBoard(con);// return to customer dashboardod stub
		
	}
}
