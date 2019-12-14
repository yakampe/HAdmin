package dev.equalcoding.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment {
	
	private @Id @GeneratedValue Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_TRANSACTION_PAYMENT")
	private MoneyTransaction transaction;	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_BILL_PAYMENT")
	private TransactionBill bill;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FK_PAYEE")
	private Person payee;
	
	private BigDecimal totalToPay;
	
	private String notes;
	
	
	
	
	public Payment(TransactionBill bill, Person payee, BigDecimal totalToPay, String notes) {
		super();
		this.bill = bill;
		this.payee = payee;
		this.totalToPay = totalToPay;
		this.notes = notes;
	}


	public Payment() {
	}


	public String getPayeeName() {
		return payee.getName();
	}
	
	
	public TransactionBill getBill() {
		return bill;
	}

	public void setBill(TransactionBill bill) {
		this.bill = bill;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MoneyTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(MoneyTransaction transaction) {
		this.transaction = transaction;
	}

	public Person getPayee() {
		return payee;
	}

	public void setPayee(Person payee) {
		this.payee = payee;
	}

	public BigDecimal getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(BigDecimal totalToPay) {
		this.totalToPay = totalToPay;
	}


	
	
}
