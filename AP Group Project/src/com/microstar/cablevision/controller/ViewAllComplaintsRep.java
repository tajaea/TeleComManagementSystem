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
	
	public void populateNSTableForRep() {
		ArrayList<Complaint> nsComplaintList = new ArrayList<Complaint>();
		nsComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: nsComplaintList) {
			rCWindow.getModel().insertRow(rCWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
				complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
				complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
				complaint.getComplaintTime()
			});
		}
	}
	
	
	public void populateBCTableForRep() {
		ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: bcComplaintList) {
			rCWindow.getModel().insertRow(rCWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
			});
		}
	}
	
	public void populatePDTableForRep() {
		ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: pdComplaintList) {
			rCWindow.getModel().insertRow(rCWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
			});
		}
	}
		
	public void returnComplaintForEmployee(String type) {
		clientObj.sendAction("Get NSComplaint");
		clientObj.sendAction("Get BCComplaint");
		clientObj.sendAction("Get PDComplaint");
	}
	
	public void returnToRepGui() {
		rCWindow.dispose();
		new EmployeeRepresentativeView(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
	}
}
