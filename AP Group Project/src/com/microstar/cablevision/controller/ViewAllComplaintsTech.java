package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.ViewAllComplaintsTechnician;

import microStarCableVision.Client;
import microStarCableVision.Complaint;
import microStarCableVision.Employee;
import microStarCableVision.Responses;

public class ViewAllComplaintsTech {
	ViewAllComplaintsTechnician tWindow;
	Client clientObj;
	Employee employeeObj;
	Responses responseObj;
	DefaultTableModel model = new DefaultTableModel();
	ArrayList<Complaint> nsComplaintList;
	ArrayList<Complaint> bcComplaintList;
	ArrayList<Complaint> pdComplaintList;
	
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
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getNoServiceDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: nsComplaintList) {
				((DefaultTableModel)tWindow.getNoServiceDetailTable().getModel()).insertRow(tWindow.getNoServiceDetailTable().getModel().getRowCount(), new Object[] {
					complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
					complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
					complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewNSComplaintDetails() {
		int index = tWindow.getNoServiceDetailTable().getSelectedRow();
		System.out.println(nsComplaintList.get(index));
	}
	
	public void populateBCTableForTech(String type) {
		clientObj.sendAction("Get BCComplaint");
		clientObj.sendComplaintType(type);
		
		ArrayList<Complaint> bcComplaintList = new ArrayList<Complaint>();
		bcComplaintList = clientObj.readComplaints();
		
		if(tWindow.getBillCDetailTable().getModel().getRowCount()<0) {
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).insertRow(tWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: bcComplaintList) {
				((DefaultTableModel)tWindow.getBillCDetailTable().getModel()).insertRow(tWindow.getBillCDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewBCComplaintDetails() {
		int index = tWindow.getBillCDetailTable().getSelectedRow();
		System.out.println(bcComplaintList.get(index));
	}
	
	public void populatePDTableForRep(String type) {
		clientObj.sendAction("Get PDComplaint");
		clientObj.sendComplaintType(type);
		
		ArrayList<Complaint> pdComplaintList = new ArrayList<Complaint>();
		pdComplaintList = clientObj.readComplaints();
		
		if(tWindow.getpDDetailTable().getModel().getRowCount()<0) {
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)tWindow.getpDDetailTable().getModel()).insertRow(tWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
		else {
			((DefaultTableModel)tWindow.getpDDetailTable().getModel()).setRowCount(0);
			for(Complaint complaint: pdComplaintList) {
				((DefaultTableModel)tWindow.getpDDetailTable().getModel()).insertRow(tWindow.getpDDetailTable().getModel().getRowCount(), new Object[] {
						complaint.getComplaintID(), complaint.getCustomerId(), complaint.getType(),
						complaint.getDetails(), complaint.getStatus(), complaint.getComplaintDate(),
						complaint.getComplaintTime()
				});
			}
		}
	}
	
	public void viewPDComplaintDetials() {
		int index = tWindow.getpDDetailTable().getSelectedRow();
		System.out.println(pdComplaintList.get(index));
	}
	
	public void submitNSResponseToCustomer() {
		String customerID = tWindow.getNsCustomerIDTextField().getText();
		String complaintID = tWindow.getNsComplaintIDTextField().getText();
		String responseDetails = tWindow.getnSResponseTextField().getText();
		String responseDate = tWindow.getnSDateTextField().getText();
		
		if(!customerID.isEmpty() && !complaintID.isEmpty() && !responseDetails.isEmpty() && !responseDate.isEmpty()) {
			responseObj = new Responses(employeeObj.getStaff_Id(), employeeObj.getFirst_Name(), employeeObj.getLast_Name());
			
			clientObj.sendAction("Send NSTechResponse");
			//clientObj.sendResponse(responseObj);
			if(clientObj.SuccessStatus()) {
				employeeObj.getResponseList().add(responseObj);
				returnToTechGui();
				//new EmployeeTechnicianView(clientObj);
			}else {
				JOptionPane.showMessageDialog(tWindow.getContentPane(), "An error occured while submiting your response to the complaint. Please Try Again", "Send Response Error", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(tWindow.getContentPane(), "Response or Date Text Field Cannot Be Empty. Please Try Again", "Empty Text Fields Error", JOptionPane.WARNING_MESSAGE);
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
