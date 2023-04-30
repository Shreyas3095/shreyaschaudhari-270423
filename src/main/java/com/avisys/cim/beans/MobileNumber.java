package com.avisys.cim.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOBILENUMBER")
public class MobileNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "mobilenumber_seq")
	@SequenceGenerator(name = "mobilenumber_seq", sequenceName = "mobilenumber_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "MOBILE_NUMBER", unique = true, nullable = false)
	private String mobileNumber;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	
	public MobileNumber() {
	}
	public MobileNumber(String mobileNumber, Customer customer)
	{
		this.mobileNumber = mobileNumber;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
}
