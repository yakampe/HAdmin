package co.uk.yktech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.uk.yktech.models.TransactionTypeCategory;
import co.uk.yktech.services.TransactionTypeCategoryService;

@RestController
@RequestMapping("/transactions/transactionCategory/")
public class TransactionTypeCategoryController {

	@Autowired
	TransactionTypeCategoryService transactionTypeCategoryService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionTypeCategory>> getAllEntities(){
		return new ResponseEntity<>(transactionTypeCategoryService.getAllEntities(), HttpStatus.OK);
	}
	
	
}
