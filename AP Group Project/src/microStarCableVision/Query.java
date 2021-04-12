package microStarCableVision;

public class Query extends Customer
{
	private String payment_status;
	private float amount_due;
	private int due_date;
	
	public Query() 
	{
		
		payment_status = "";
		amount_due = 0;
		due_date = 0;
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
	public int getDue_date() {
		return due_date;
	}
	public void setDue_date(int due_date) {
		this.due_date = due_date;
	}
	public Query(String cust_ID, String payment_status, float amount_due, int due_date) 
	{
		super(cust_ID);
		this.payment_status = payment_status;
		this.amount_due = amount_due;
		this.due_date = due_date;
	}
	public Query(Query Q) 
	{
		
		payment_status = Q.payment_status;
		amount_due = Q.amount_due;
		due_date = Q.due_date;
	}
	public String toString() {
		return "Query [payment_status=" + payment_status + ", amount_due=" + amount_due + ", due_date=" + due_date
				+ ", Cust_ID=" + customerID + "]";
	}
	public void print()
	{}
	
	
}
