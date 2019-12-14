package dev.equalcoding.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.equalcoding.services.StatementTypeService;
import dev.equalcoding.services.UploadService;

@Controller
public class MainController {


	@Autowired
	UploadService uploadService;
	@Autowired
	StatementTypeService statementTypesService;
	
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/importCSV", method = RequestMethod.POST)
	public ResponseEntity<Void> submit(@RequestParam MultipartFile file,
			@RequestParam(name = "id", required = false) String id) {
		
		uploadService.uploadService(file, statementTypesService.getTypeById(id));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping("/viewbill")
	public String viewBill() {
		return "viewbill";
	}

}
