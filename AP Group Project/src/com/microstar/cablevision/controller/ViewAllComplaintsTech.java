package com.microstar.cablevision.controller;

import java.util.ArrayList;

import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.ViewAllComplaintsTechnician;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Employee;

public class ViewAllComplaintsTech {
	ViewAllComplaintsTechnician tWindow;
	Client clientObj;
	Employee employeeObj;
	
	public ViewAllComplaintsTech(ViewAllComplaintsTechnician view) {
		setWindow(view);
	}

	private void setWindow(ViewAllComplaintsTechnician view) {
		tWindow = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void populateNSTableForTech() {
		ArrayList<Complaint> nsComplaintList = new ArrayList<Complaint>();
		nsComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: nsComplaintList) {
			tWindow.getModel().insertRow(tWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
				complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
				complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
				complaint.getComplaintTime()
			});
		}
	}
	
	
	public void populateBCTableForTech() {
		ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: bcComplaintList) {
			tWindow.getModel().insertRow(tWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
			});
		}
	}
	
	public void populatePDTableForTech() {
		ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: pdComplaintList) {
			tWindow.getModel().insertRow(tWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
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
	
	public void returnToTechGui() {
		tWindow.dispose();
		new EmployeeTechnicianView(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		employeeObj = employee;
	}
}
