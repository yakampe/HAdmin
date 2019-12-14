package dev.equalcoding.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import dev.equalcoding.models.MoneyTransaction;
import dev.equalcoding.models.StatementType;

@Service
public class UploadService {
	@Autowired
	MoneyTransactionService moneyTransactionService;

	public String uploadService(MultipartFile file, final StatementType type) {
		
		
		Reader reader;
		try {
			reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allRecords = csvReader.readAll();
			//use process money transaction of statement type to generate money transactions which then will call createTransactions to save data
			return String.format("Added %d new transactions", allRecords.stream()
				.map(record -> convertStatementRowToMoneyTransaction(record, type))
				.filter(r -> r != null)
				.collect(Collectors.collectingAndThen(Collectors.toList(), moneyTransactionService::createTransactionsWithPOJO))
				.size());
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "No transactions added";
		
	}
	
	public MoneyTransaction convertStatementRowToMoneyTransaction(String [] row, StatementType st) {
		MoneyTransaction mt = new MoneyTransaction();
		mt.setType(st.getTypeName());
		mt.setDescription(row[st.getDescriptionPostiion()]);
		mt.setOwner(st.getOwner());
		Double amount = null;
		
		//check first if all of the key elements are blank
		if(Strings.isBlank(
				row[st.getDatePosition()]
				+row[st.getCreditPosition()]
				+row[st.getDebitPosition()])) {
			return null;
		}
		
		//if the row is a pending transaction
		if(row[st.getPendingLine()].toLowerCase().equals(st.getPendingDescription())) {
			if(st.isIgnorePending()) return null;
			mt.setDate(LocalDate.MAX);
		} else {
			//if not a pending transaction set date
			mt.setDate(LocalDate.parse(row[st.getDatePosition()], DateTimeFormatter.ofPattern(st.getDatePattern())));								
		} 
	
		//is the row for debit and credit the same?
		if(st.getCreditPosition() == st.getDebitPosition()) {
			if(row[st.getCreditPosition()].length() > 0) {
				amount = Double.parseDouble(row[st.getCreditPosition()].replace(",",""));
				//if it is a positive number set credit
				if(amount > 0) {
					mt.setCredit(BigDecimal.valueOf(amount));
				} else {
					//if negative number set debit
					mt.setDebit(BigDecimal.valueOf(amount));
				}
			}
		} else {
	    //if the rows arent the same
			if(row[st.getCreditPosition()].length() > 0) {
				//set credit
				amount = Double.parseDouble(row[st.getCreditPosition()]);
				
				//if credit for whatever reason is in negative - reverse
				if(amount < 0) amount = amount * -1;
				
				mt.setCredit(BigDecimal.valueOf(amount));
			} else {
				amount = Double.parseDouble(row[st.getDebitPosition()]);
				
				//if debit for whatever reason is positive - reverse
				if(amount > 0) amount = amount * -1;
				
				mt.setDebit(BigDecimal.valueOf(amount));
			}
			
		}
		
		
		if(st.isReversedForCreditCard()) {
			BigDecimal tempVariableForCredit = mt.getCredit();
			mt.setCredit(mt.getDebit());
			mt.setDebit(tempVariableForCredit);
			
		}	
		
		//if for whatever reason a credit is negative and debit is in positive - negate
		
		
		if(mt.getCredit() != null && mt.getCredit().compareTo(BigDecimal.ZERO) < 0) mt.setCredit(mt.getCredit().negate());
		if(mt.getDebit() != null && mt.getDebit().compareTo(BigDecimal.ZERO) > 0) mt.setDebit(mt.getDebit().negate()); 			

		return mt;
	}

}
