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
	DefaultTableModel model = new DefaultTableModel();
	ArrayList<Complaint> nsComplaintList;
	ArrayList<Complaint> bcComplaintList;
	ArrayList<Complaint> pdComplaintList;
	
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
		
		//ArrayList<Complaint> nsComplaintList = new ArrayList<Complaint>();
		nsComplaintList = clientObj.readComplaints();
		
		if(rCWindow.getNSTable().getModel().getRowCount()<0) {
			for(Complaint complaint: nsComplaintList) {
				((DefaultTableModel)rCWindow.getNSTable().getModel()).insertRow(rCWindow.getNSTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)rCWindow.getNSTable().getModel()).setRowCount(0);
			for(Complaint complaint: nsComplaintList) {
				((DefaultTableModel)rCWindow.getNSTable().getModel()).insertRow(rCWindow.getNSTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewNSComplaintDetails() {
		int  index = rCWindow.getNSTable().getSelectedRow();
		System.out.println( nsComplaintList.get(index));
	}
	
	public void populateBCTableForRep(String type) {
		clientObj.sendAction("Get BCComplaint");
		clientObj.sendComplaintType(type);
		//ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readComplaints();
		
		if(rCWindow.getBCTable().getModel().getRowCount()<0) {
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)rCWindow.getBCTable().getModel()).insertRow(rCWindow.getBCTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)rCWindow.getBCTable().getModel()).setRowCount(0);
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)rCWindow.getBCTable().getModel()).insertRow(rCWindow.getBCTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewBCComplaintDetails() {
		int  index = rCWindow.getBCTable().getSelectedRow();
		System.out.println(bcComplaintList.get(index));
	}
	
	public void populatePDTableForRep(String type) {
		clientObj.sendAction("Get PDComplaint");
		clientObj.sendComplaintType(type);
		//ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readComplaints();
		
		if(rCWindow.getBCTable().getModel().getRowCount()<0) {
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)rCWindow.getPDTable().getModel()).insertRow(rCWindow.getPDTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)rCWindow.getPDTable().getModel()).setRowCount(0);
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)rCWindow.getPDTable().getModel()).insertRow(rCWindow.getPDTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewPDComplaintDetails() {
		int  index = rCWindow.getPDTable().getSelectedRow();
		System.out.println(pdComplaintList.get(index));
	}
	
	public void returnToRepGui() {
		rCWindow.dispose();
		new EmployeeRepresentativeView(clientObj).setEmp(this.empObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
	}
}
