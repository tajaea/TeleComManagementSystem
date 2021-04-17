package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.views.EmployeeRepresentativeView;
import com.microstar.cablevision.views.ViewAllComplaintsRepresentative;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Employee;

public class ViewAllComplaintsRep {
	ViewAllComplaintsRepresentative rCWindow;
	Client clientObj;
	Employee empObj;
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	
	public ViewAllComplaintsRep(ViewAllComplaintsRepresentative view) {
		setWindow(view);
	}

	private void setWindow(ViewAllComplaintsRepresentative view) {
		rCWindow = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void populateNSTableForRep(String type) {
		clientObj.sendAction("Get NSComplaint");
		clientObj.sendComplaintType(type);
		
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
	
	public void populateBCTableForRep(String type) {
		clientObj.sendAction("Get BCComplaint");
		clientObj.sendComplaintType(type);
		ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: bcComplaintList) {
			rCWindow.getbcModel().insertRow(rCWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
			});
		}
	}
	
	public void populatePDTableForRep(String type) {
		clientObj.sendAction("Get PDComplaint");
		clientObj.sendComplaintType(type);
		ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readCustomerComplaint();
		
		for(Complaint complaint: pdComplaintList) {
			rCWindow.getpdModel().insertRow(rCWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
			});
		}
	}
	
	public void returnToRepGui() {
		rCWindow.dispose();
		new EmployeeRepresentativeView(clientObj).setEmp(this.empObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
	}
}
