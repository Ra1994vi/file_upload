package com.file_upload.File.Upload;



import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FileUploadApplication {
	private static final Logger log = Logger.getLogger(FileUploadApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(FileUploadApplication.class, args);
	}
	

	
}
