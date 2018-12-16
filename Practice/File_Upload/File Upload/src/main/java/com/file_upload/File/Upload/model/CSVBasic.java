package com.file_upload.File.Upload.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonRootName("studentBasic")
public class CSVBasic {
	
	@JsonProperty("name")
	private String name;
	
	@JsonAlias({ "phoneNumber", "mail" })
	private String ContactOrMail;
	
	@JsonProperty("college")
	private String college;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactOrMail() {
		return ContactOrMail;
	}
	public void setContactOrMail(String contactOrMail) {
		ContactOrMail = contactOrMail;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}

}
