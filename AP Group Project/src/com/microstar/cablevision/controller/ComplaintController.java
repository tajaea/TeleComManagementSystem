package com.microstar.cablevision.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.microstar.cablevision.views.CustomerDashBoard;
import com.microstar.cablevision.views.MakeComplaint;

import microStarCableVision.Client;
import microStarCableVision.Customer;
import microStarCableVision.Complaint;

public class ComplaintController {

	MakeComplaint MakeComplaintView;
	Complaint complaintObj; 
	Customer customerObj;

	private Client con;

	public ComplaintController(MakeComplaint complaintView ) {
		MakeComplaintView = complaintView;
	}

	public void setClient(Client client) {
		con = client;//Assigning the Client instance that was created
	}



	public void setCustomerInformation(Customer customerObj) {

		this.customerObj=customerObj;

		this.complaintObj=complaintObj;//Assign the complaint object from the database to the object that is in the controller
		MakeComplaintView.getLblCustomeridentifier().setText(this.customerObj.getCustomerID());

		//customerDashboardview.getLblFirstName().setText(this.customerObj.getFirstName());
	}

	public void returnToCustomerDashboard() {

		MakeComplaintView.getFrame().dispose();//close make a complaint window
		new CustomerDashBoard(con).setCustomerObject(customerObj);// return to customer dashboard
	}

	public void Submit() {

		String Details= MakeComplaintView.getComplainttextArea().getText();

		if(!Details.isEmpty()){
			complaintObj = new Complaint(customerObj.getCustomerID(), MakeComplaintView.getComplaintTypecomboBox().getSelectedItem().toString(),Details);

			con.sendAction("Add Complaint");
			con.addComplaint(complaintObj);
			if(con.SuccessStatus()) {
				customerObj.getComplainList().add(complaintObj);//adding a complaint object to the customer complaint list
				returnToCustomerDashboard();
			}else {
				JOptionPane.showMessageDialog(MakeComplaintView.getFrame(), " An occurred while creating your complaint. Please try again later", " Create Complaint Error", JOptionPane.WARNING_MESSAGE);

			}
			/*
			for(Complaint complaint: customerObj.getComplainList())
			{
				System.out.println(complaint.getType());

				
			}*/
		

		}else {
			JOptionPane.showMessageDialog(MakeComplaintView.getFrame(), " Ensure that Details is not empty", " Detail Error", JOptionPane.WARNING_MESSAGE);
		}

	}


}
