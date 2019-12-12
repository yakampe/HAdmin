package co.uk.yktech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.uk.yktech.models.StatementType;
import co.uk.yktech.services.StatementTypeService;

@Controller
@RequestMapping("/statement")
public class StatementController {

	
	@Autowired
	StatementTypeService statementTypeService;
	
	
	@PostMapping("/newStatementType")
	public ResponseEntity<String> newStatementType(@RequestBody StatementType statementType){
		
		statementTypeService.addStatementType(statementType);
		return new ResponseEntity<>("New Statement type added", HttpStatus.OK);
	}
	
	@GetMapping("/statementTypes")
	public ResponseEntity<Iterable<StatementType>> getStatementTypes(){
		return new ResponseEntity<>(statementTypeService.getStatementTypes(), HttpStatus.OK);
	}
	
	
	@GetMapping("/statementType")
	public ResponseEntity<StatementType> getStatementType(){
		StatementType st = new StatementType();
		st.setDebitPosition(1);
		return new ResponseEntity<>(st, HttpStatus.OK);
	}
	
}
