package com.avisys.cim.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.dao.CustomerDAO;
import com.avisys.cim.exception.ResourceNotFoundException;

@Service
public class CustomerServiceImplementation implements CustomerService{
	@Autowired
	CustomerDAO CustomerDao;

	public List<Customer> getCustomers(String firstName, String lastName, String mobileNumber)
	{
		
		List<Customer> CustomerList = CustomerDao.findAll();		
		if(firstName == null && lastName == null && mobileNumber == null)
		{
			return CustomerList;
		}
		if(firstName != null)
		{
			CustomerList = CustomerList.stream().filter(c -> c.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(lastName != null)
		{
			CustomerList = CustomerList.stream().filter(c -> c.getLastName().toLowerCase().contains(lastName.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(mobileNumber != null)
		{
			CustomerList = CustomerList.stream().filter(c -> c.getMobileNumber().equals(mobileNumber))
					.collect(Collectors.toList());
		}
		return CustomerList;
	}

	//Method to get object matching with given id from the repository.
	@Override
	public Customer findById(Long id) {
		Customer customer = CustomerDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		return customer;
	}
}
