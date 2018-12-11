package com.crmit.file_upload.File.Upload.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class UploadFile {

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public HashMap upload(@RequestParam("file") MultipartFile[] file){
		System.out.println("coming");
		System.out.println("file size = " + file.length);
		byte[] bytes;
		try {
			for(MultipartFile uploadedFile : file) {
				System.out.println("file name = " + uploadedFile.getOriginalFilename());
	           // File tempFile = new File("files/" + uploadedFile.getOriginalFilename());
	            //uploadedFile.transferTo(tempFile);
	            Path path = Paths.get("files/" +uploadedFile.getOriginalFilename());
	            bytes = uploadedFile.getBytes();
		        Files.write(path, bytes);
	        }			
			//Path path = Paths.get("files/" +files.getOriginalFilename());
	        //Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HashMap<String, String> map = new HashMap<>();
		    map.put("Status", "OK");
		    
		return map;
	}
	public void insertTrainerData() {

		//log.info("LOG=>INFO Inserting Trainer data into OSVC");
		HttpHeaders headers = createHttpHeaders("ravindranath","R@vi1994");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate =  new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("https://opn-crmit2-in.rightnowdemo.com/services/rest/connect/latest/accounts", 
											HttpMethod.GET,
											entity,
											String.class
											);
		System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.getBody());
			
	}
	private HttpHeaders createHttpHeaders(String user, String password){
	    String notEncoded = user + ":" + password;
	    String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
	    System.out.println("encoded " + encodedAuth);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.add("OSvC-CREST-Application-Context",  "This is a valid request for account");
	    headers.add("Authorization", "Basic " + encodedAuth);
	    return headers;
	}
}
