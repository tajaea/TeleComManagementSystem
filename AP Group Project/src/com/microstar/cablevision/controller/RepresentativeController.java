package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.EmployeeRepresentativeView;

import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.ViewAllComplaintsRepresentative;
import com.microstar.cablevision.views.ViewAllServicesRepresentative;

import microStarCableVision.Client;
import microStarCableVision.Employee;

public class RepresentativeController {
	EmployeeRepresentativeView window;
	Client clientObj;
	Employee empObj;//create global employee object
	
	public RepresentativeController(EmployeeRepresentativeView view) {
		setWindow(view);
	}
	
	private void setWindow(EmployeeRepresentativeView view) {
		window = view;
	} 
	
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void getLoginPage() {
		clientObj.closeConnection();
		window.dispose();
		new LoginView(clientObj);
	}
	
	public void getViewAllComplaintsGui() {
		window.dispose();
		new ViewAllComplaintsRepresentative(clientObj);
	}
	
	public void getAllServicesGui() {
		window.dispose();
		new ViewAllServicesRepresentative(clientObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;//set the global object to the local employee object
		window.getNameTextField().setText(this.empObj.getFirst_Name() + " " + this.empObj.getLast_Name());
	}
}
