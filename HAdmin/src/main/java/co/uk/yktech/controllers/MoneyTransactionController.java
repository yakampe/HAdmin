package co.uk.yktech.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.uk.yktech.models.MoneyTransaction;
import co.uk.yktech.services.MoneyTransactionService;

@RestController
@RequestMapping("transactions")
public class MoneyTransactionController {

	@Autowired
	private MoneyTransactionService moneyTransactionService;
	
	@GetMapping("/")
	public ResponseEntity<List<MoneyTransaction>> getTransactions(){
		return moneyTransactionService.getAllTransactions();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoneyTransaction> getTransaction(@PathVariable("id") Long id) {
			return moneyTransactionService.getTransactionById(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<List<String>> createTransaction(
			@RequestBody List<MoneyTransaction> transactions
			) {
		return ResponseEntity.status(200).body(moneyTransactionService.createTransactions(transactions));
	}
	
	@PutMapping("/")
	public ResponseEntity<Boolean> updateTransaction(
			@RequestBody MoneyTransaction transaction){
		return moneyTransactionService.updateTransaction(transaction);
	}
	
	@GetMapping(path="/", params={"from", "to"})
	public ResponseEntity<Set<MoneyTransaction>> getTransactionsBetweenDates(
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam(name="billed", required=false) boolean includeBilled){
		return moneyTransactionService.getTransactionsBetweenDates(from, to, includeBilled);		
	}
}
