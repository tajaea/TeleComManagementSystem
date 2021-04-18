package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.EmployeeTechnicianView;
import com.microstar.cablevision.views.LoginView;
import com.microstar.cablevision.views.ViewAllComplaintsTechnician;

import microStarCableVision.Client;
import microStarCableVision.Employee;

public class TechnicianController {
	EmployeeTechnicianView window;
	Client clientObj;
	Employee empObj;
	//String id = empObj.getStaff_Id();
	
	public TechnicianController(EmployeeTechnicianView view) {
		setWindow(view);
	}
	
	private void setWindow(EmployeeTechnicianView view) {
		window = view;
	}
		
	public void setClient(Client client) {
		clientObj = client;
	}
	
	public void getLoginPage() {
		clientObj.closeConnection();
		window.dispose();
		new LoginView(new Client());
	}
	
	/*public void getChatViewPage() {
		window.dispose();
		new ChatView(id,clientObj.getConnectionSocket());
	}*/
	
	public void getViewAllComplaintsGui() {
		window.dispose();
		new ViewAllComplaintsTechnician(clientObj).setEmp(empObj);;
		//setEmpObj(empObj);
	}
	
	public void setEmpObj(Employee employee) {
		this.empObj = employee;
		window.getNameTextField().setText(this.empObj.getFirst_Name() + " " + this.empObj.getLast_Name());
	}
	
	public void endConnection() {
		clientObj.closeConnection();
	}
}
