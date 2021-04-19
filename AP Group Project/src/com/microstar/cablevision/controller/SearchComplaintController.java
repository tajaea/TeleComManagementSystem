package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.MakeComplaint;
import com.microstar.cablevision.views.SearchComplaint;
import com.microstar.cablevision.views.ShowRowDataForEmployee;
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
	ShowRowDataForEmployee showRowDataForEmployee = new ShowRowDataForEmployee();
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
	
	public void viewTableDetails() {
		int index = searchComplaint.getTable().getSelectedRow();
		TableModel model = searchComplaint.getTable().getModel();
		
		String complaintId = model.getValueAt(index, 0).toString();
		String customerId = model.getValueAt(index, 1).toString();
		String complaintType = model.getValueAt(index, 2).toString();
		String complaintDetail = model.getValueAt(index, 3).toString();
		String complaintStatus = model.getValueAt(index, 4).toString();
		
		showRowDataForEmployee.setVisible(true);
		showRowDataForEmployee.pack();
		showRowDataForEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		showRowDataForEmployee.getComplaintIDTextField().setText(complaintId);
		showRowDataForEmployee.getCustomerIDTextField().setText(customerId);
		showRowDataForEmployee.getComplaintTypeTextField().setText(complaintType);
		showRowDataForEmployee.getComplaintDetailTextField().setText(complaintDetail);
		showRowDataForEmployee.getComplaintStatusTextField().setText(complaintStatus);
		
		//System.out.println(complaintList.get(index));
	}

	public void returnTodasboard() {
		searchComplaint.getFrame().dispose();
		new CustomerDashBoard(con);// return to customer dashboardod stub
		
	}
	
	public void setCustomer(Customer customer) {
		this.customerObj = customer;
	}
}
