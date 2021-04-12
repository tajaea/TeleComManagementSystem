package microStarCableVision;

import java.io.Serializable;

import com.microstar.cablevision.utility.Utility;

public class Responses implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseID;
	private String custID;
	private String response;
	private String responseDate;
	private String dateOfVisit;
	private String respondedBy;
	
	public Responses(String responseID, String custID, String response, String dateOfVisit,String respondedBy) {
		this.responseID = responseID;
		this.custID=custID;
		this.response = response;
		this.responseDate = Utility.setDate();
		this.dateOfVisit = dateOfVisit;
		this.respondedBy=respondedBy;
	}
	
	public Responses(String responseID, String custID, String response,String date,String dateOfVisit,String respondedBy) {
		this.responseID = responseID;
		this.custID=custID;
		this.response = response;
		this.responseDate = date;
		this.dateOfVisit = dateOfVisit;
		this.respondedBy=respondedBy;
	}
	public Responses() {
		this.responseID = " ";
		this.custID=" ";
		this.response = " ";
		this.responseDate = " ";
		this.dateOfVisit = " ";
		this.respondedBy="";
	}
	
	public Responses(Responses response) {
		this.responseID = response.responseID;
		this.custID=response.custID;
		this.response = response.response;
		this.responseDate = response.responseDate;
		this.dateOfVisit = response.dateOfVisit;
		this.respondedBy=response.respondedBy;
	}

	public String getResponseID() {
		return responseID;
	}

	public void setResponseID(String responseID) {
		this.responseID = responseID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String empID) {
		this.response = empID;
	}
	
	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getRespondedBy() {
		return respondedBy;
	}

	public void setRespondedBy(String respondedBy) {
		this.respondedBy = respondedBy;
	}
	
	
}
