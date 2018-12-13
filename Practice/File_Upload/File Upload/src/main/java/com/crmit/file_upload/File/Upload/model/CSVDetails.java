package com.crmit.file_upload.File.Upload.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonRootName("studentDetails")
public class CSVDetails {

	@JsonProperty("name")
	private String name;
	
	@JsonAlias({ "phoneNumber", "mail" })
	private String ContactOrMail;
	
	@JsonAlias({ "x std", "10th","sslc","xth" })
	private String sslc;
	
	@JsonAlias({ "xii std", "12th","puc","intermediate" })
	private String puc;
	
	@JsonAlias({ "aadhar", "residential","address" })
	private String address;
	
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

	public String getSslc() {
		return sslc;
	}

	public void setSslc(String sslc) {
		this.sslc = sslc;
	}

	public String getPuc() {
		return puc;
	}

	public void setPuc(String puc) {
		this.puc = puc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "CSVDetails [name=" + name + ", ContactOrMail=" + ContactOrMail + ", sslc=" + sslc + ", puc=" + puc
				+ ", address=" + address + ", college=" + college + "]";
	}
	
}
