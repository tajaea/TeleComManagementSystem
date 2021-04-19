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
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.microstar.cablevision.database.StatusSessionFactoryBuilder;
import com.microstar.cablevision.security.Security;
import microStarCableVision.Customer;
@Entity
@Table(name = "status")
public class AccountStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int ID;

	@Column(name = "cust_ID")
	private String cust_ID;

	@Column(name = "amount_due")
	private double amount_due;

	@Column(name = "payment_due_date")
	private Date due_date;
	
	@Transient
	Customer customer =new Customer();
	
	public AccountStatus() {
		this.cust_ID = "";
		this.amount_due = 0;
		this.due_date = new Date();
	}
	
	public AccountStatus(String cust_ID,float amount_due, Date due_date) {
		this.cust_ID = cust_ID;
		this.amount_due = amount_due;
		this.due_date = due_date;
	}
	
	public AccountStatus(AccountStatus as) {
		as.cust_ID = cust_ID;
		as.amount_due = amount_due;
		as.due_date = due_date;
	}

	public String getCust_ID() {
		return cust_ID;
	}

	public void setCust_ID(String cust_ID) {
		this.cust_ID = cust_ID;
	}

	public double getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(double d) {
		this.amount_due = d;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		return "AccountStatus [cust_ID=" + cust_ID + ", amount_due=" + amount_due
				+ ", due_date=" + due_date + "]";
	}
	public void createStatus() {
		this.setCust_ID(customer.getCustomerID());
		this.setAmount_due(10000.00);
		this.setDue_date(new Date(20/04/2021));
	}
	public boolean create() {
		try {
			Session session = StatusSessionFactoryBuilder.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			System.out.println("Status Record successfully created");
			Security.logger.info("Status Record successfully created");
			session.close();
			return true;
		} catch (Exception e) {
			Security.logger.fatal("Could not create Status using ORM");
			return false;
		}

	}	
	@SuppressWarnings("unchecked")
	public List<AccountStatus> readAll() {
		List<AccountStatus> statusList = new ArrayList<>();
		Session session = StatusSessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		statusList = (List<AccountStatus>) session.createQuery("FROM status").getResultList();
		transaction.commit();
		session.close();
		return statusList;
	}

}
