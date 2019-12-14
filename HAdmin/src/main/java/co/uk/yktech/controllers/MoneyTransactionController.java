package co.uk.yktech.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.uk.yktech.dto.MoneyTransactionDto;
import co.uk.yktech.services.MoneyTransactionService;

@RestController
@RequestMapping("transactions")
public class MoneyTransactionController {

	@Autowired
	private MoneyTransactionService moneyTransactionService;
	
	@GetMapping("/")
	public ResponseEntity<List<MoneyTransactionDto>> getTransactions(){
		
		return new ResponseEntity<>(moneyTransactionService.getAllTransactions() ,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoneyTransactionDto> getTransaction(@PathVariable("id") Long id) {
		return new ResponseEntity<>(moneyTransactionService.getTransactionById(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<List<String>> createTransaction(@RequestBody List<MoneyTransactionDto> transactions) {
		return new ResponseEntity<>(moneyTransactionService.createTransactions(transactions), HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Boolean> updateTransaction(@RequestBody MoneyTransactionDto transaction){
		return new ResponseEntity<>(moneyTransactionService.updateTransaction(transaction), HttpStatus.OK);
	}
	
	@GetMapping(path="/", params={"from", "to"})
	public ResponseEntity<Set<MoneyTransactionDto>> getTransactionsBetweenDates(
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam(name="billed", required=false) boolean includeBilled){
		return new ResponseEntity<>(moneyTransactionService.getTransactionsBetweenDates(from, to, includeBilled), HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransactionEntity(@PathVariable("id") Long id){
		moneyTransactionService.deleteEntityById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
