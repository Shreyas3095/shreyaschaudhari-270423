package com.avisys.cim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	//End-point to fetch data using Get method or any parameter matching to relevant data.
	@GetMapping("/getcustomer")
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String firstName,
														@RequestParam(required = false) String lastName,
														@RequestParam(required = false) String mobileNumber)
	{
		List<Customer> CustomerList = customerService.getCustomers(firstName, lastName, mobileNumber); 
		if(CustomerList != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(CustomerList);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//End-point to fetch data using customer id using get method and path variable.
	@GetMapping("/getcustomer/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id)
	{
		Customer customer = customerService.findById(id);
		if(customer == null)
		{
			return new ResponseEntity("Customer not found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
}










