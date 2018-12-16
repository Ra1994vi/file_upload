package com.file_upload.File.Upload.Controller;


import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.BasicConfigurator;




@RestController
public class InitController {
	private static final Logger log = Logger.getLogger(InitController.class);
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String index(){		
		log.info("Log Info => Init controller is called");
		//new UploadFile().insertTrainerData();
		return "Ok";
	}
}