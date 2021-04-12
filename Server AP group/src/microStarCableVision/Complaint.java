package microStarCableVision;

import java.io.Serializable;
import java.util.ArrayList;

import com.microstar.cablevision.utility.Utility;

public class Complaint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int complaintID;
	private String details;
	private String customerId;
	private String type;
	private ArrayList<Responses> responseList;
	private String status;
	private String complaintDate;
	private String complaintTime;

	public Complaint(String customerId,String type,String details,String status) {
		this.details = details;
		this.customerId = customerId;
		this.type = type;
		this.status=status;
		this.complaintDate = Utility.setDate();
		this.complaintTime = Utility.setTime();
	}
	
	public Complaint(String customerId,String type,String details) {
		this.details = details;
		this.customerId = customerId;
		this.type = type;
		this.complaintDate = Utility.setDate();
		this.complaintTime = Utility.setTime();
	}
	
	public Complaint() {
		this.details = " ";
		this.customerId = " ";
		this.type = " ";
		this.complaintDate = " ";
		this.complaintTime = " "; 
	}


	public Complaint(int complaintID,String customerId, String type, String details,ArrayList<Responses> responseList,String status,String date,String time) {
		this.complaintID=complaintID;
		this.details = details;
		this.customerId = customerId;
		this.type = type;
		this.responseList = responseList;
		this.status=status;
		this.complaintDate = date;
		this.complaintTime = time;
	}


	public int getComplaintID() {
		return complaintID;
	}


	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public ArrayList<Responses> getResponseList() {
		return responseList;
	}


	public void setResponseList(ArrayList<Responses> responseList) {
		this.responseList = responseList;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getComplaintDate() {
		return complaintDate;
	}


	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}


	public String getComplaintTime() {
		return complaintTime;
	}


	public void setComplaintTime(String complaintTime) {
		this.complaintTime = complaintTime;
	}


	@Override
	public String toString() {
		return "Complaint [complaintID=" + complaintID + ", details=" + details + ", customerId=" + customerId
				+ ", type=" + type + ", responseList=" + responseList + ", status=" + status + ", complaintDate="
				+ complaintDate + ", complaintTime=" + complaintTime + "]";
	}

	
	
	
}
