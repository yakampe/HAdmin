package co.uk.yktech.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MoneyTransaction {

	private @Id @GeneratedValue Long id;
	private BigDecimal debit;
	private BigDecimal credit;
	private String description;
	@DateTimeFormat(pattern ="dd/MM/yyyy",  iso = ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
	private LocalDate date;
	private String owner;
	
	@JsonIgnore
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

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "MoneyTransaction [id=" + id + ", debit=" + debit + ", credit=" + credit + ", description=" + description
				+ ", date=" + date + "]";
	}
		
	
}
