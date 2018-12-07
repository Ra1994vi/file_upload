package com.crmit.file_upload.File.Upload.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class UploadFile {

//	@RequestMapping(method= RequestMethod.GET)
//	public String index(){
//		return "index";
//	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		return "index";
	}
}
