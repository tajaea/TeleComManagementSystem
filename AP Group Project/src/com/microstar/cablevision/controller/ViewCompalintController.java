package com.microstar.cablevision.controller;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.MakeComplaint;
import com.microstar.cablevision.views.ShowRowData;
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
	ArrayList<Complaint> arrayList;
	ShowRowData showRowData = new ShowRowData();
	
	public ViewCompalintController(ViewAllComplaintCustomer ViewComplaintView){
		this.ViewComplaintView = ViewComplaintView;
		
	}
	public void setClient(Client client) {
		con = client;//Assigning the Client instance that was created
	}
	
	public void populateTable() {
		arrayList = new ArrayList<Complaint>();
		
		arrayList=con.readCustomerComplaint(); // Returns an arraylist of the complaints made by the signed in ucustomer
		
		for(Complaint complain:arrayList) {
			
			ViewComplaintView.getModel().insertRow(ViewComplaintView.getTable().getModel().getRowCount(), new Object[] {
				complain.getComplaintID(),complain.getCustomerId(),complain.getType(),
				complain.getDetails(),complain.getStatus(),complain.getComplaintDate(),
				complain.getComplaintTime()
			});
		}
	}
	
	public void viewTableDetails() {
		int index = ViewComplaintView.getTable().getSelectedRow();
		TableModel model = ViewComplaintView.getTable().getModel();
		
		String complaintId = model.getValueAt(index, 0).toString();
		String customerId = model.getValueAt(index, 1).toString();
		String complaintType = model.getValueAt(index, 2).toString();
		String complaintDetail = model.getValueAt(index, 3).toString();
		String complaintStatus = model.getValueAt(index, 4).toString();
		String complaintDate = model.getValueAt(index, 5).toString();
		String complaintTime = model.getValueAt(index, 6).toString();
		
		showRowData.setVisible(true);
		showRowData.pack();
		showRowData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		showRowData.getComplaintIDTextField().setText(complaintId);
		showRowData.getCustomerIDTextField().setText(customerId);
		showRowData.getComplaintTypeTextField().setText(complaintType);
		showRowData.getComplaintDetailTextField().setText(complaintDetail);
		showRowData.getComplaintStatusTextField().setText(complaintStatus);
		showRowData.getComplaintDateTextField().setText(complaintDate);
		showRowData.getComplaintTimeTextField().setText(complaintTime);
		//System.out.println(arrayList.get(index));
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
