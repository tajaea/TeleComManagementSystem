package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.ViewAllComplaintsTechnician;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Employee;

public class ViewAllComplaintsTech {
	ViewAllComplaintsTechnician tWindow;
	Client clientObj;
	Employee employeeObj;
	Object[] row = new Object[5];
	DefaultTableModel model = new DefaultTableModel();
	
	public ViewAllComplaintsTech(ViewAllComplaintsTechnician view) {
		setWindow(view);
	}

	private void setWindow(ViewAllComplaintsTechnician view) {
		tWindow = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void populateNSTableForTech(String type) {
		clientObj.sendAction("Get NSComplaint");
		clientObj.sendComplaintType(type);
		
		ArrayList<Complaint> nsComplaintList = new ArrayList<Complaint>();
		nsComplaintList = clientObj.readComplaints();
		
		if(tWindow.getNoServiceDetailTable().getModel().getRowCount()<0) {
			for(Complaint complaint: nsComplaintList) {
				((DefaultTableModel)tWindow.getNoServiceDetailTable().getModel()).insertRow(tWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getNoServiceDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: nsComplaintList) {
				((DefaultTableModel)tWindow.getNoServiceDetailTable().getModel()).insertRow(tWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void populateBCTableForTech(String type) {
		clientObj.sendAction("Get BCComplaint");
		clientObj.sendComplaintType(type);
		
		ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readComplaints();
		
		if(tWindow.getBillCDetailTable().getModel().getRowCount()<0) {
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).insertRow(tWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).insertRow(tWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void populatePDTableForRep(String type) {
		clientObj.sendAction("Get PDComplaint");
		clientObj.sendComplaintType(type);
		
		ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readComplaints();
		
		if(tWindow.getpDDetailTable().getModel().getRowCount()<0) {
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)tWindow.getpDDetailTable().getModel()).insertRow(tWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getpDDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)tWindow.getpDDetailTable().getModel()).insertRow(tWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void returnToTechGui() {
		tWindow.dispose();
		new EmployeeTechnicianView(clientObj).setEmp(this.employeeObj);;
	}
	
	public void setEmpObj(Employee employee) {
		this.employeeObj = employee;
	}
}
