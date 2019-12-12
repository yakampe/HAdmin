package co.uk.yktech.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.repositories.MoneyTransactionRepo;

@Service
public class MoneyTransactionService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MoneyTransactionRepo moneyTransactionRepo;
	
	public ResponseEntity<MoneyTransaction> getTransactionById(Long id) {
		//TODO Create not found logic?
		
		return ResponseEntity.status(200).body(moneyTransactionRepo.findById(id).get());
	}

	public List<String> createTransactions(List<MoneyTransaction> transactions) {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.info(transactions.toString());
		List<String> responses = new ArrayList<>();
		transactions.forEach(transaction -> {
			try {
				moneyTransactionRepo.save(transaction);
				responses.add(transaction.getDescription() +  " - OK");
			} catch (Exception e) {
				responses.add(transaction.getDescription() +  " - ERROR " + e.toString() + " @ " + transaction.toString());
			} 
		});
		return responses;
	}

	public ResponseEntity<List<MoneyTransaction>> getAllTransactions() {
		return ResponseEntity.status(200).body((List<MoneyTransaction>) moneyTransactionRepo.findAll());
	}

	public ResponseEntity<Boolean> updateTransaction(MoneyTransaction transaction) {
		moneyTransactionRepo.save(transaction);
		return ResponseEntity.status(200).body(true);
	}
	
	public ResponseEntity<Set<MoneyTransaction>> getTransactionsBetweenDates(String startDateString, String endDateString, boolean includeBilled){
		LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Set<MoneyTransaction> mt = new HashSet<>();
		

		if(includeBilled) {
			mt = moneyTransactionRepo.getAllBetweenDates(startDate, endDate);		
		} else {
			mt = moneyTransactionRepo.getNotBilledBetweenDates(startDate, endDate);			
		}
		
		return ResponseEntity.status(200).body(mt);
		
	}
}
