package dev.equalcoding.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import dev.equalcoding.dto.TransactionBillDto;
import dev.equalcoding.services.TransactionBillService;

@RestController
@RequestMapping("bills")
public class BillController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TransactionBillService transactionBillService;
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionBillDto>> getAllBills() {
		return new ResponseEntity<>(transactionBillService.getAllBills(), HttpStatus.OK);
		
	}
		
	@GetMapping("/latest")
	public ResponseEntity<TransactionBillDto> getLatestBill() {
		return new ResponseEntity<>(transactionBillService.getLatestBill(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionBillDto> getBillById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(transactionBillService.getBillById(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<TransactionBillDto> createBill(@RequestParam("from") String startDate, @RequestParam("to") String endDate) {
		return new ResponseEntity<>(transactionBillService.createBill(startDate,endDate), HttpStatus.OK);
	}
	
	
	//TODO delete bill by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBill(@PathVariable("id") Long id) {
		transactionBillService.deleteBill(id);		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Void> updateBill(@RequestBody TransactionBillDto transactionBillDto){
		transactionBillService.updateBill(transactionBillDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
