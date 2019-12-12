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
	
	@PostMapping("/")
	public ResponseEntity<TransactionType> newEntity (@RequestBody TransactionType tt) {
		return new ResponseEntity<>(transactionTypeService.newEntity(tt), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionType> getEntityById(@PathVariable("id") String id){
		return new ResponseEntity<>(transactionTypeService.getEntityById(Long.parseLong(id)), HttpStatus.OK);
	}
	
	@GetMapping("/type/{typeName}")
	public ResponseEntity<TransactionType> getEntityByCategoryName(@PathVariable("typeName") String typeName){
		return new ResponseEntity<>(transactionTypeService.getEntityByTypeName(typeName), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntityById(@PathVariable("id") Long id){
		transactionTypeService.deleteEntityById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransactionType> updateEntity(@PathVariable("id") Long id,
												@RequestBody TransactionType tt) {
		return new ResponseEntity<>(transactionTypeService.updateEntityById(id, tt),HttpStatus.OK);	
	}
	
	
 	
}
