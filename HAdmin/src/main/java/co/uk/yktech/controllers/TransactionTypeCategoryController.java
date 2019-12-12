package co.uk.yktech.controllers;

import java.util.List;

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
	
	@PostMapping("/")
	public ResponseEntity<TransactionTypeCategory> newEntity (@RequestBody TransactionTypeCategory ttc) {
		return new ResponseEntity<>(transactionTypeCategoryService.newEntity(ttc), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionTypeCategory> getEntityById(@PathVariable("id") Long id){
		return new ResponseEntity<>(transactionTypeCategoryService.getEntityById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryName}")
	public ResponseEntity<TransactionTypeCategory> getEntityByCategoryName(@PathVariable("categoryName") String categoryName){
		return new ResponseEntity<>(transactionTypeCategoryService.getEntityByCategoryName(categoryName), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntityById(@PathVariable("id") Long id){
		transactionTypeCategoryService.deleteEntityById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransactionTypeCategory> updateEntity(@PathVariable("id") Long id,
												@RequestBody TransactionTypeCategory ttc) {
		return new ResponseEntity<>(transactionTypeCategoryService.updateEntityById(id, ttc),HttpStatus.OK);		
	}
	
	
}
