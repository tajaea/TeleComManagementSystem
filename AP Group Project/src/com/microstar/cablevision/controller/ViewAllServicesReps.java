package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.EmployeeRepresentativeView;
import com.microstar.cablevision.views.ViewAllServicesRepresentative;

import microStarCableVision.Client;
import microStarCableVision.Employee;

public class ViewAllServicesReps {
	ViewAllServicesRepresentative rSWindow;
	Client clientObj;
	Employee empObj;
	
	public ViewAllServicesReps(ViewAllServicesRepresentative view) {
		setWindow(view);
	}

	private void setWindow(ViewAllServicesRepresentative view) {
		rSWindow = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void returnToRepGui() {
		rSWindow.dispose();
		new EmployeeRepresentativeView(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
	}
}
