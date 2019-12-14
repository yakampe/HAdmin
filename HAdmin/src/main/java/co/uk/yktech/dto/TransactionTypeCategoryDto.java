package co.uk.yktech.dto;

import java.util.Set;

public class TransactionTypeCategoryDto {
	

	private Long id;
	private String categoryName;
	private Set<String> transactionTypes;

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
	public Set<String> getTransactionTypes() {
		return transactionTypes;
	}
	public void setTransactionTypes(Set<String> transactionTypes) {
		this.transactionTypes = transactionTypes;
	}	
	
}
