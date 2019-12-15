package dev.equalcoding.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransactionTypeCategory {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String categoryName;
	
	@OneToMany(mappedBy = "transactionTypeCategory")
	private Set<TransactionType> transactionTypes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<TransactionType> getTransactionTypes() {
		return transactionTypes;
	}

	public void setTransactionTypes(Set<TransactionType> transactionTypes) {
		this.transactionTypes = transactionTypes;
	}
	
	
	
}
