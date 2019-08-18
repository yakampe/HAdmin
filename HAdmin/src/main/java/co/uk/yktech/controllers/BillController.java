package co.uk.yktech.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import co.uk.yktech.models.TransactionBill;
import co.uk.yktech.services.TransactionBillService;

@RestController
@RequestMapping("bills")
public class BillController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionBillService transactionBillService;
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionBill>> getAllBills() {
		return transactionBillService.getAllBills();
		
	}
		
	@GetMapping("/latest")
	public ResponseEntity<TransactionBill> getLatestBill() {
		return transactionBillService.getLatestBill();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionBill> getBillById(
			@PathVariable("id") Long id) {
		return transactionBillService.getBillById(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<TransactionBill> createBill(
			@RequestParam("from") String startDate,
			@RequestParam("to") String endDate
			) {
		return transactionBillService.createBill(startDate,endDate);
	}
	
	
	//TODO delete bill by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBill(@PathVariable("id") Long id) {
		return transactionBillService.deleteBill(id);
	}
	
	@PutMapping("/")
	public void updateBill(
			@RequestBody TransactionBill transactionBill
			){
		transactionBillService.updateBill(transactionBill);
	}
	
	
	
}
