package dev.equalcoding.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.equalcoding.models.Person;
import dev.equalcoding.services.PersonService;

@RestController
@RequestMapping("payer")
public class PayerController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/")
	public ResponseEntity<List<Person>> getAllPayers(){
		return personService.getAllPayers();
	}
	
	@PostMapping("/")
	public ResponseEntity<Person> createPayer(
			@RequestParam("name") String name){
		return personService.createPayer(name);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPayerById(
			@PathVariable("id") Long id){
		return personService.getPayerById(id);
	}

}
