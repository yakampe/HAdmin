package dev.equalcoding.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MoneyTransaction {

	private @Id @GeneratedValue Long id;
	@DateTimeFormat(pattern ="dd/MM/yyyy",  iso = ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
	private LocalDate date;
	private String description;
	private BigDecimal debit;
	private BigDecimal credit;
	
	@OneToMany
	private Set<Payment> payments;
	
	@ManyToOne
	@JoinColumn(name = "FK_BILL")
	@JsonIgnore
	private TransactionBill bill;
	private String type = "Custom";
	private String owner;
	
	@OneToOne
	private TransactionType transactionType;
		
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Boolean getProcessed() {
		if(payments.size() > 0) 
			return true;
		else
			return false;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public TransactionBill getBill() {
		return bill;
	}
	public void setBill(TransactionBill bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "MoneyTransaction [id=" + id + ", debit=" + debit + ", credit=" + credit + ", description=" + description
				+ ", date=" + date + "]";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
