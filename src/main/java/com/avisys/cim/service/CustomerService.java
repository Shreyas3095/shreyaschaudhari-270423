package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(String firstName, String lastName, String mobileNumber);

	public Customer findById(Long id);
	
	public boolean addNewCustomer(Customer customer);

}
