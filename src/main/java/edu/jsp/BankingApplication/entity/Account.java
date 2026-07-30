package edu.jsp.BankingApplication.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long accountNo;
	private double balance;
	
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
	private List<Transaction> transaction;
	
	private void addTransaction(Transaction t)
	{
		transaction.add(t);
		t.setAccount(this);
	}
	
	
	private void removeTransaction(Transaction t)
	{
		transaction.remove(t);
		t.setAccount(this);
	}
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	//generate getter and setter
	
	
	
	
	
	
	
	

}
