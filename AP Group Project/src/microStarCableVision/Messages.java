package microStarCableVision;

import java.io.Serializable;

import com.microstar.cablevision.utility.Utility;

public class Messages implements Serializable{
private String sender;
private String receiver;
private String messageBody;
private String time;
private String date;
private boolean messageState;

 public Messages(String sender,String receiver,String messageBody) {
	this.sender = sender;
	this.receiver = receiver;
	this.messageBody = messageBody;
	this.messageState = false;
	this.time = Utility.setTime();
	this.date = Utility.setDate();
}


public boolean isMessageState() {
	return messageState;
}


public void setMessageState(boolean messageState) {
	this.messageState = messageState;
}


public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getReceiver() {
	return receiver;
}
public void setReceiver(String receiver) {
	this.receiver = receiver;
}
public String getMessageBody() {
	return messageBody;
}
public void setMessageBody(String messageBody) {
	this.messageBody = messageBody;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}


@Override
public String toString() {
	return "Messages [sender=" + sender + ", receiver=" + receiver + ", messageBody=" + messageBody + ", time=" + time
			+ ", date=" + date + ", messageState=" + messageState + "]";
}


}
