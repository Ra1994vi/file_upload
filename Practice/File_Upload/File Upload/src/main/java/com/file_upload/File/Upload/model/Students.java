package com.file_upload.File.Upload.model;

import java.util.ArrayList;
import java.util.List;

public class Students {
	
	//list variables to return status to view to show progress	
	private  List<CSVDetails> validatedStudents = new ArrayList<CSVDetails>();
	private  List<CSVDetails> validationFailStudents = new ArrayList<CSVDetails>();
	private  List<CSVDetails> uploadedStudents = new ArrayList<CSVDetails>();
	
	public  List<CSVDetails> getValidatedStudents() {
		return validatedStudents;
	}
	public  void addValidatedStudents(CSVDetails validatedStudent) {
		this.validatedStudents.add(validatedStudent);
	}
	public  List<CSVDetails> getValidationFailStudents() {
		return validationFailStudents;
	}
	public  void addValidationFailStudents(CSVDetails validationFailStudent) {
		this.validationFailStudents.add(validationFailStudent);
	}
	public  List<CSVDetails> getuploadedStudents() {
		return uploadedStudents;
	}
	public  void addUploadedStudents(CSVDetails validatedStudent) {
		this.uploadedStudents.add(validatedStudent);
	}
	
	

}
