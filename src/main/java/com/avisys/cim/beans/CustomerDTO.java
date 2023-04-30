package com.avisys.cim.beans;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private List<String> mobileNumbers;
	public CustomerDTO() {
	}
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.mobileNumbers = customer.getMobileNumbers()
				.stream()
				.map(MobileNumber::getMobileNumber)
				.collect(Collectors.toList());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(List<String> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	
	
}
