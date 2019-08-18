package co.uk.yktech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.uk.yktech.services.UploadService;

@Controller
public class MainController {


	@Autowired
	UploadService uploadService;
	
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/importCSV", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file,
			@RequestParam("type") String type) {
		uploadService.upload(file, type);
		return "home";
	}
	
	
	@RequestMapping("/viewbill")
	public String viewBill() {
		return "viewbill";
	}

}
