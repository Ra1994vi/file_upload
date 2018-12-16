package com.file_upload.File.Upload.utility;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.file_upload.File.Upload.Controller.UploadFile;
import com.file_upload.File.Upload.model.CSVDetails;
import com.google.gson.Gson;

public class Utility {
	private static final Logger log = Logger.getLogger(Utility.class);
	
	@Value("${osvc.username}")
	private String USERNAME;
	
	@Value("${osvc.password}")
	private String PASSWARD;
	
	@Value("${osvc.url}")
	private String OSVC_URL;
	
	public Boolean validateStudentRecord(CSVDetails student) {		
		if(student.getContactOrMail().isEmpty() || 
		   student.getCollege().isEmpty() || 
		   student.getName().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public Integer uploadToOSVC(List<CSVDetails> studentsToUpload) {
		if(studentsToUpload.size() > 0) {
			List<CSVDetails> tempList = new ArrayList<CSVDetails>(); 
			log.info("Log Info Utility.uploadToOSVC => Uploading started");
			Integer batch = 1;
			for(Integer i=0;i<studentsToUpload.size();i++) {				
				tempList.add(studentsToUpload.get(i));
				Integer size = i + 1;
				if(tempList.size() == 2 || studentsToUpload.size() == size) {
					log.info("**********************************************************************");
					log.info("Log Info Utility.uploadToOSVC => Upload to osvc data batch " + batch);
					log.info("**********************************************************************");
					new Utility().postMe(tempList);
					tempList.clear();
					batch++;
				}
			}
		}		
		return 0;
	}
	
	
	public void postMe(List<CSVDetails> studentsToUpload) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 Gson gson = new Gson();
		    String json = gson.toJson(studentsToUpload);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("body", json);

		HttpEntity<MultiValueMap<String, String>> request = 
												new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost/upload/download.php", request , String.class );
		
		System.out.println("body -> " + response.getBody());
		 log.info("**********************************************************************");
		 log.info("body -> " + response);
		 log.info("**********************************************************************");
	}
	
 	public void insertTrainerData() {
		

		// log.info("LOG=>INFO Inserting Trainer data into OSVC");
		HttpHeaders headers = createHttpHeaders(USERNAME, PASSWARD);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange(
				"https://opn-crmit2-in.rightnowdemo.com/services/rest/connect/latest/accounts", HttpMethod.GET, entity,
				String.class);
		System.out.println("Result - status (" + response.getStatusCode() + ") has body: " + response.getBody());

	}

	private HttpHeaders createHttpHeaders(String user, String password) {
		String notEncoded = user + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
		System.out.println("encoded " + encodedAuth);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("OSvC-CREST-Application-Context", "This is a valid request for account");
		headers.add("Authorization", "Basic " + encodedAuth);
		return headers;
	}

}
