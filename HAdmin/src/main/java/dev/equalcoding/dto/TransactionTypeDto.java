package dev.equalcoding.dto;

import java.util.List;

import dev.equalcoding.models.MoneyTransaction;
import dev.equalcoding.models.PresetTransactionDescriptions;

public class TransactionTypeDto {

	private Long id;
	private String typeName;
	private List<MoneyTransaction> moneyTransaction;
	private String Category;
	private List<PresetTransactionDescriptions> defaultTransactionTypes;
	
	
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
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public List<PresetTransactionDescriptions> getDefaultTransactionTypes() {
		return defaultTransactionTypes;
	}
	public void setDefaultTransactionTypes(List<PresetTransactionDescriptions> defaultTransactionTypes) {
		this.defaultTransactionTypes = defaultTransactionTypes;
	}
	
	
	
}
