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

import dev.equalcoding.dto.TransactionTypeCategoryDto;
import dev.equalcoding.services.TransactionTypeCategoryService;

@RestController
@RequestMapping("/transactions/transactionCategory/")
public class TransactionTypeCategoryController {

	@Autowired
	TransactionTypeCategoryService transactionTypeCategoryService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionTypeCategoryDto>> getAllEntities(){
		return new ResponseEntity<>(transactionTypeCategoryService.getAllEntities(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<TransactionTypeCategoryDto> newEntity (@RequestBody TransactionTypeCategoryDto ttcDto) {
		return new ResponseEntity<>(transactionTypeCategoryService.newEntity(ttcDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionTypeCategoryDto> getEntityById(@PathVariable("id") Long id){
		return new ResponseEntity<>(transactionTypeCategoryService.getEntityById(id), HttpStatus.OK);
	}
	
	@GetMapping("/type/{categoryName}")
	public ResponseEntity<TransactionTypeCategoryDto> getEntityByCategoryName(@PathVariable("categoryName") String categoryName){
		return new ResponseEntity<>(transactionTypeCategoryService.getEntityByCategoryName(categoryName), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntityById(@PathVariable("id") Long id){
		transactionTypeCategoryService.deleteEntityById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<TransactionTypeCategoryDto> updateEntity(@RequestBody TransactionTypeCategoryDto ttcDto) {
		return new ResponseEntity<>(transactionTypeCategoryService.updateEntity(ttcDto),HttpStatus.OK);		
	}
	
	
}
