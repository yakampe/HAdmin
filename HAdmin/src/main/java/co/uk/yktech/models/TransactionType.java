package co.uk.yktech.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TransactionType {

	@Id
	@GeneratedValue
	private Long id;
	
	private String typeName;

	@OneToOne(mappedBy="transactionType")
	private MoneyTransaction moneyTransaction;
	
	@ManyToOne
	@JoinColumn(name = "FK_TRANSACTION_TYPE_CAT")
	private TransactionTypeCategory transactionTypeCategory;
	
	@OneToMany
	private List<presetTransactionDescriptions> defaultTransactionTypes;
	
	public List<presetTransactionDescriptions> getDefaultTransactionTypes() {
		return defaultTransactionTypes;
	}
	public void setDefaultTransactionTypes(List<presetTransactionDescriptions> defaultTransactionTypes) {
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
	public MoneyTransaction getMoneyTransaction() {
		return moneyTransaction;
	}
	public void setMoneyTransaction(MoneyTransaction moneyTransaction) {
		this.moneyTransaction = moneyTransaction;
	}
	public TransactionTypeCategory getTransactionTypeCategory() {
		return transactionTypeCategory;
	}
	public void setTransactionTypeCategory(TransactionTypeCategory transactionTypeCategory) {
		this.transactionTypeCategory = transactionTypeCategory;
	}
	
	
	
}
