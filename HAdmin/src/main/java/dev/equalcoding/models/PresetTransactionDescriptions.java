package dev.equalcoding.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PresetTransactionDescriptions {

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "FK_TRANSACTION_TYPE")	
	private TransactionType transactionType;
	
	private String presetDescription;
	

	public String getPresetDescription() {
		return presetDescription;
	}

	public void setPresetDescription(String presetDescription) {
		this.presetDescription = presetDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	
	
}
