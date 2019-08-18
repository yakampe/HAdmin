package co.uk.yktech.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransactionBill {

	private @Id @GeneratedValue Long id;
	private String notes;
	private BigDecimal total;

	@OneToMany(mappedBy = "bill")
	private Set<MoneyTransaction> transactions;
	private LocalDate date;
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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

	
	
}
