package com.crmit.file_upload.File.Upload.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String index(){
		
		//new UploadFile().insertTrainerData();
		return "Ok";
	}
}