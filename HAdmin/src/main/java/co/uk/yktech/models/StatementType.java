package co.uk.yktech.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StatementType {

	@Id  @GeneratedValue
	private Long id;
	

	private String typeName;
	private String owner;
	private String datePattern;

	private boolean ignorePending;
	private int pendingLine;
	private String pendingDescription;
	private int datePosition;
	private int descriptionPostiion;
	private int debitPosition;
	private int creditPosition;
	private boolean reversedForCreditCard;	
	
	public boolean isReversedForCreditCard() {
		return reversedForCreditCard;
	}






	public void setReversedForCreditCard(boolean reversedForCreditCard) {
		this.reversedForCreditCard = reversedForCreditCard;
	}






	public String getOwner() {
		return owner;
	}






	public void setOwner(String owner) {
		this.owner = owner;
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





	public String getDatePattern() {
		return datePattern;
	}





	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}




	public String getPendingDescription() {
		return pendingDescription;
	}





	public void setPendingDescription(String pendingDescription) {
		this.pendingDescription = pendingDescription;
	}


	public int getPendingLine() {
		return pendingLine;
	}





	public void setPendingLine(int pendingLine) {
		this.pendingLine = pendingLine;
	}





	public int getDatePosition() {
		return datePosition;
	}





	public void setDatePosition(int datePosition) {
		this.datePosition = datePosition;
	}





	public int getDescriptionPostiion() {
		return descriptionPostiion;
	}
	
	





	public int getCreditPosition() {
		return creditPosition;
	}





	public void setDescriptionPostiion(int descriptionPostiion) {
		this.descriptionPostiion = descriptionPostiion;
	}





	public int getDebitPosition() {
		return debitPosition;
	}





	public void setDebitPosition(int debitPosition) {
		this.debitPosition = debitPosition;
	}





	public void setCreditPosition(int creditPosition) {
		this.creditPosition = creditPosition;
	}
	
	

	public boolean isIgnorePending() {
		return ignorePending;
	}


	public void setIgnorePending(boolean ignorePending) {
		this.ignorePending = ignorePending;
	}

	
}
