package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.EmployeeRepresentativeView;

import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.ViewAllComplaintsRepresentative;
import com.microstar.cablevision.views.ViewAllServicesRepresentative;

import microStarCableVision.Client;
import microStarCableVision.Employee;

public class RepresentativeController {
	EmployeeRepresentativeView window;
	ViewAllComplaintsRepresentative rCWindow;
	ViewAllServicesRepresentative rSWindow;
	Client clientObj;
	Employee empObj;//create global employee object
	
	public RepresentativeController(EmployeeRepresentativeView view, ViewAllComplaintsRepresentative viewAllComplaintsRepresentative, ViewAllServicesRepresentative viewAllServicesRepresentative) {
		setWindow(view, viewAllComplaintsRepresentative, viewAllServicesRepresentative);
	}
	
	private void setWindow(EmployeeRepresentativeView view, ViewAllComplaintsRepresentative viewAllComplaintsRepresentative, ViewAllServicesRepresentative viewAllServicesRepresentative) {
		window = view;
		rCWindow = viewAllComplaintsRepresentative;
		rSWindow = viewAllServicesRepresentative;
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
	
	public void  returnToRepGui() {
		rSWindow.dispose();
		new EmployeeRepresentativeView(clientObj);
	}
	
	public void  returnToRepGui1() {
		rCWindow.dispose();
		new EmployeeRepresentativeView(clientObj);
	}

	public void setEmpObj(Employee employee) {
		this.empObj = employee;//set the global object to the local employee object
		window.getNameTextField().setText(this.empObj.getFirst_Name() + " " + this.empObj.getLast_Name());
	}
}
