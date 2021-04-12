package microStarCableVision;

import java.util.Date;

public class AccountStatus {
	private int cust_ID;
	private String payment_status;
	private float amount_due;
	private Date due_date;
	
	public AccountStatus() {
		this.cust_ID = 0;
		this.payment_status = "";
		this.amount_due = 0;
		this.due_date = new Date();
	}
	
	public AccountStatus(int cust_ID, String payment_status, float amount_due, Date due_date) {
		this.cust_ID = cust_ID;
		this.payment_status = payment_status;
		this.amount_due = amount_due;
		this.due_date = due_date;
	}
	
	public AccountStatus(AccountStatus as) {
		as.cust_ID = cust_ID;
		as.payment_status = payment_status;
		as.amount_due = amount_due;
		as.due_date = due_date;
	}

	public int getCust_ID() {
		return cust_ID;
	}

	public void setCust_ID(int cust_ID) {
		this.cust_ID = cust_ID;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public float getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(float amount_due) {
		this.amount_due = amount_due;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		return "AccountStatus [cust_ID=" + cust_ID + ", payment_status=" + payment_status + ", amount_due=" + amount_due
				+ ", due_date=" + due_date + "]";
	}
	
	
	
	
	
	

}
