package microStarCableVision;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.microstar.cablevision.database.CustomerSessionFactoryBuilder;
import com.microstar.cablevision.database.PaymentSessionFactoryBuilder;
import com.microstar.cablevision.security.Security;



@Entity
@Table(name = "payment")
public class PaymentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int ID;

	@Column(name = "cust_ID")
	private String customerID;

	@Column(name = "amount_paid")
	private double amountPaid;
	
	@Column(name = "balance_remaining")
	private double balanceRemaining;
	
	@Column(name = "date_paid")
	private Date datePaid;
	
	@Column(name = "payment_method")
	private String paymentMethod;

	public PaymentHistory() {
		this.customerID = "";
		this.amountPaid = 0;
		this.balanceRemaining = 0;
		this.datePaid = new Date();
		this.paymentMethod = "";
	}
	public PaymentHistory(String customerID, double amountPaid, double balanceRemaining, Date datePaid, String paymentMethod) {
		this.customerID = customerID;
		this.amountPaid = amountPaid;
		this.balanceRemaining = balanceRemaining;
		this.datePaid = datePaid;
		this.paymentMethod = paymentMethod;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getBalanceRemaining() {
		return balanceRemaining;
	}

	public void setBalanceRemaining(double balanceRemaining) {
		this.balanceRemaining = balanceRemaining;
	}

	public Date getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	@Override
	public String toString() {
		return "PaymentHistory [customerID=" + customerID + ", amountPaid=" + amountPaid
				+ ", balanceRemaining=" + balanceRemaining + ", datePaid=" + datePaid + ", paymentMethod="
				+ paymentMethod + "]";
	}
	public void createPayment1() {
		this.setCustomerID("CUSc99a95");
		this.setAmountPaid(10000.00);
		this.setBalanceRemaining(0.00);
		this.setDatePaid(new Date(17/04/2021));
		this.setPaymentMethod("Paid with card ending in xxxxxx9897");
	}
	public void createPayment2() {
		this.setCustomerID("CUSc99a95");
		this.setAmountPaid(5000.00);
		this.setBalanceRemaining(1500.00);
		this.setDatePaid(new Date(10/04/2021));
		this.setPaymentMethod("Paid with card ending in xxxxxx9897");
	}
	
	
	public boolean create() {
		try {
			Session session = PaymentSessionFactoryBuilder.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			System.out.println("Payment Record successfully created");
			Security.logger.info("Payment Record successfully created");
			session.close();
			return true;
		} catch (Exception e) {
			Security.logger.fatal("Could not create Payment using ORM");
			return false;
		}

	}
	@SuppressWarnings("unchecked")
	public List<PaymentHistory> readAll() {
		List<PaymentHistory> paymentList = new ArrayList<>();
		Session session = PaymentSessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		paymentList = (List<PaymentHistory>) session.createQuery("FROM payment").getResultList();
		transaction.commit();
		session.close();
		return paymentList;
	}

	
	

}
