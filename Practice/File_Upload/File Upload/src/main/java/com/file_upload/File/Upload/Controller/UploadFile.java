package com.file_upload.File.Upload.Controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file_upload.File.Upload.model.CSVDetails;
import com.file_upload.File.Upload.model.Students;
import com.file_upload.File.Upload.utility.Utility;

@RestController
public class UploadFile {
	private static final Logger log = Logger.getLogger(UploadFile.class);
	
	private String 	FAILED_MSG = "FAILED", 
					OK_MSG = "OK",
					DESCRIPTION = "Description",
					STATUS="STATUS";

	
	@Value("${msg.check_header}")
	private String CHECK_HEADER;
	
	@Value("${msg.file_or_clg_null}")
	private String FILE_CLG_NULL;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/upload3", method = RequestMethod.POST)	
	public List<CSVDetails> testMe(@RequestParam("files") MultipartFile file){
		List<CSVDetails> studentRecords = null;
		try {
			 studentRecords = CsvUtils.read(CSVDetails.class, file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentRecords;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)	
	public HashMap<String, String>  upload(@RequestParam("files") MultipartFile file) {
		System.out.println("coming");
		System.out.println("file size = " + file.getOriginalFilename());
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Path path = Paths.get("files/" + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, String> map = new HashMap<>();
		map.put(STATUS, OK_MSG);
		map.put(DESCRIPTION,"ok");
		return map;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/upload2")
	public  HashMap<String, String>  uploadMultipart(@RequestParam("files") MultipartFile file,
													@RequestParam("clg") String clg) throws IOException {
		HashMap<String, String> map = new HashMap<>();
		
		//object to insert student records in list
		
		if(file != null && clg != null) {
			log.info("**********************************************************************");
			log.info("Log Info UploadFile.uploadMultipart => Request recieved");
			log.info("**********************************************************************");
			/**
			 * Mapping file record with POJO class to convert into object
			 * */
			List<CSVDetails> studentRecords = CsvUtils.read(CSVDetails.class, file.getInputStream());
			log.info("Log Info UploadFile.uploadMultipart => Mapping is done and starting to validate");		
			if(studentRecords != null) {
				
				/**
				 * Mapping is done validate data
				 * */	
				Students studentsObj = new Students();
				Utility utility = new Utility();
				log.info("Log Info UploadFile.uploadMultipart => Validating and storing in lists");
				for (CSVDetails student : studentRecords) {
					log.info("Log Info UploadFile.uploadMultipart => Validating for "+ student);
					if(utility.validateStudentRecord(student)) {
						log.info("Log Info UploadFile.uploadMultipart => adding in ValidatedStudents  for "+ student);
						studentsObj.addValidatedStudents(student);
					}else {
						log.info("Log Info UploadFile.uploadMultipart => Validating in ValidationFailStudents for "+ student);
						studentsObj.addValidationFailStudents(student);
					}					
				}
				/**
				 * Starting to upload student records
				 * */
				utility.uploadToOSVC(studentsObj.getValidatedStudents());
								
				
				map.put(STATUS, OK_MSG);
				map.put(DESCRIPTION, "file is uploaded");
				return map;	
			}
			map.put(STATUS, FAILED_MSG);
			map.put(DESCRIPTION, CHECK_HEADER);
			log.info("Log Info UploadFile.uploadMultipart => " +CHECK_HEADER);
		}else {
			map.put(STATUS, FAILED_MSG);
			map.put(DESCRIPTION, FILE_CLG_NULL);	
			log.info("Log Info UploadFile.uploadMultipart => " +FILE_CLG_NULL);
		}		
		return map;
	}
}