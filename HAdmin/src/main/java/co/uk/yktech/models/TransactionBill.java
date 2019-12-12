package co.uk.yktech.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class TransactionBill {

	private @Id @GeneratedValue Long id;
	private String notes;
	private BigDecimal totalCredit;
	private BigDecimal totalDebit;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private Set<Payment> payments;	

	
	@OneToMany
	private Set<MoneyTransaction> transactions;
	
	@DateTimeFormat(pattern ="dd/MM/yyyy",  iso = ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
	private LocalDate date;
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
	public BigDecimal getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}
	public BigDecimal getTotalDebit() {
		return totalDebit;
	}
	public void setTotalDebit(BigDecimal totalDebit) {
		this.totalDebit = totalDebit;
	}
	public Set<MoneyTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<MoneyTransaction> transactions) {
		this.transactions = transactions;
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
	
	
	
	
}
