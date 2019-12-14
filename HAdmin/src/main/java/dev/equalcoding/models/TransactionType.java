package dev.equalcoding.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TransactionType {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique=true)
	private String typeName;

	@OneToMany(mappedBy="transactionType")
	private List<MoneyTransaction> moneyTransaction;
	
	@ManyToOne
	@JoinColumn(name = "FK_TRANSACTION_TYPE_CAT")
	private TransactionTypeCategory transactionTypeCategory;
	
	@OneToMany
	private List<PresetTransactionDescriptions> defaultTransactionTypes;
	
	public List<PresetTransactionDescriptions> getDefaultTransactionTypes() {
		return defaultTransactionTypes;
	}
	public void setDefaultTransactionTypes(List<PresetTransactionDescriptions> defaultTransactionTypes) {
		this.defaultTransactionTypes = defaultTransactionTypes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}	
	public List<MoneyTransaction> getMoneyTransaction() {
		return moneyTransaction;
	}
	public void setMoneyTransaction(List<MoneyTransaction> moneyTransaction) {
		this.moneyTransaction = moneyTransaction;
	}
	public TransactionTypeCategory getTransactionTypeCategory() {
		return transactionTypeCategory;
	}
	public void setTransactionTypeCategory(TransactionTypeCategory transactionTypeCategory) {
		this.transactionTypeCategory = transactionTypeCategory;
	}
	
	
	
}
