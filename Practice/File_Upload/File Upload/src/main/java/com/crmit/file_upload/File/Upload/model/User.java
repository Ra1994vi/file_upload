package com.crmit.file_upload.File.Upload.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

//@JsonPropertyOrder(value = { "username", "phoneNumber", "address" })
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonRootName("user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonProperty("username")
	private String username;
	
	
	@JsonAlias({ "phoneNumber", "mobile" })
	//@JsonProperty("phoneNumber")
	private String phoneNumber;
	
	@JsonProperty("address")
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}

}