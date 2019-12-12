package co.uk.yktech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.uk.yktech.models.TransactionType;
import co.uk.yktech.services.TransactionTypeService;

@RestController
@RequestMapping("/transactions/transactionType/")
public class TransactionTypeController {

	@Autowired
	TransactionTypeService transactionTypeService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionType>> getEntities() {
		return new ResponseEntity<>(transactionTypeService.getAllEntities(), HttpStatus.OK);
	}
 	
}
