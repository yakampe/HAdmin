package co.uk.yktech.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.repositories.MoneyTransactionRepo;

@Service
public class MoneyTransactionService {

	private MoneyTransactionRepo moneyTransactionRepo;
	
	public ResponseEntity<MoneyTransaction> getTransactionById(Long id) {
		//TODO Create not found logic?
		
		return ResponseEntity.status(200).body(moneyTransactionRepo.findById(id).get());
	}

	public List<String> createTransactions(List<MoneyTransaction> transactions) {
		List<String> responses = new ArrayList<>();
		transactions.forEach(transaction -> {
			try {
				moneyTransactionRepo.save(transaction);
				responses.add(transaction.getDescription() +  " - OK");
			} catch (Exception e) {
				responses.add(transaction.getDescription() +  " - ERROR " + e.toString());
			} 
		});
		return responses;
	}
}
