package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.ViewAllComplaintsTechnician;

import microStarCableVision.Client;
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
	
	public void returnToTechGui() {
		tWindow.dispose();
		new EmployeeTechnicianView(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		employeeObj = employee;
	}
}
