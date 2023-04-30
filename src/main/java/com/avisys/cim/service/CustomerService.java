package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.beans.Customer;
import com.avisys.cim.beans.CustomerDTO;

public interface CustomerService {

	public List<CustomerDTO> getCustomers(String firstName, String lastName, String mobileNumber);

	public CustomerDTO findById(Long id);
	
	public boolean addNewCustomer(CustomerDTO customerDto);

	public boolean deleteCustomer(String mobileNumber);

}
