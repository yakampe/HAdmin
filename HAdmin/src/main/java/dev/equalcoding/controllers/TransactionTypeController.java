package dev.equalcoding.controllers;

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

import dev.equalcoding.dto.TransactionTypeDto;
import dev.equalcoding.services.TransactionTypeService;

@RestController
@RequestMapping("/transactions/transactionType/")
public class TransactionTypeController {

	@Autowired
	TransactionTypeService transactionTypeService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionTypeDto>> getEntities() {
		return new ResponseEntity<>(transactionTypeService.getAllEntities(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<TransactionTypeDto> newEntity (@RequestBody TransactionTypeDto ttDto) {
		return new ResponseEntity<>(transactionTypeService.newEntity(ttDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionTypeDto> getEntityById(@PathVariable("id") Long id){
		return new ResponseEntity<>(transactionTypeService.getEntityById(id), HttpStatus.OK);
	}
	
	@GetMapping("/type/{typeName}")
	public ResponseEntity<TransactionTypeDto> getEntityByCategoryName(@PathVariable("typeName") String typeName){
		return new ResponseEntity<>(transactionTypeService.getEntityByTypeName(typeName), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntityById(@PathVariable("id") Long id){
		transactionTypeService.deleteEntityById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransactionTypeDto> updateEntity(@RequestBody TransactionTypeDto ttDto) {
		return new ResponseEntity<>(transactionTypeService.updateEntity(ttDto),HttpStatus.OK);	
	}
	
	
 	
}
