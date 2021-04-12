package microStarCableVision;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.microstar.cablevision.utility.Utility;

public class Responses implements Serializable{
	private String complaintID;
	private String empID;
	private String messageinfo;
	private String responseDate;
	private String responseTime;
	
	public Responses(String complaintID, String empID, String messageinfo) {
		this.complaintID = complaintID;
		this.empID = empID;
		this.messageinfo = messageinfo;
		this.responseDate = Utility.setDate();
		this.responseTime = Utility.setTime();
	}
	
	public Responses(String complaintID, String empID, String messageinfo,String date,String time) {
		this.complaintID = complaintID;
		this.empID = empID;
		this.messageinfo = messageinfo;
		this.responseDate = date;
		this.responseTime = time;
	}
	public Responses() {
		this.complaintID = " ";
		this.empID = " ";
		this.messageinfo = " ";
		this.responseDate = " ";
		this.responseTime = " ";
	}
	
	public Responses(Responses response) {
		this.complaintID = response.complaintID;
		this.empID = response.empID;
		this.messageinfo = response.messageinfo;
		this.responseDate = response.responseDate;
		this.responseTime = response.responseTime;
	}

	public String getComplaintID() {
		return complaintID;
	}

	public void setComplaintID(String complaintID) {
		this.complaintID = complaintID;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getMessageinfo() {
		return messageinfo;
	}

	public void setMessageinfo(String messageinfo) {
		this.messageinfo = messageinfo;
	}
	
	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
}
