package com.microstar.cablevision.controller;

import java.util.ArrayList;

import com.microstar.cablevision.views.EmployeeRepresentativeView;
import com.microstar.cablevision.views.ViewAllComplaintsRepresentative;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Employee;

public class ViewAllComplaintsRep {
	ViewAllComplaintsRepresentative rCWindow;
	Client clientObj;
	Employee empObj;
	
	public ViewAllComplaintsRep(ViewAllComplaintsRepresentative view) {
		setWindow(view);
	}

	private void setWindow(ViewAllComplaintsRepresentative view) {
		rCWindow = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void populateTableForRep() {
		ArrayList<Complaint> complaintList = new ArrayList<Complaint>();
		complaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: complaintList) {
			rCWindow.getModel().insertRow(rCWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
				complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
				complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
				complaint.getComplaintTime()
			});
		}
	}
	
	public void returnNSComplaintForEmployee(String type) {
		clientObj.sendAction("Get NSComplaint");
	}
	
	public void returnToRepGui() {
		rCWindow.dispose();
		new EmployeeRepresentativeView(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
	}
}
