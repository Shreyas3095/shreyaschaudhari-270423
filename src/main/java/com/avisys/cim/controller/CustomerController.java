package com.avisys.cim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.avisys.cim.beans.Customer;
import com.avisys.cim.beans.CustomerDTO;
import com.avisys.cim.beans.MobileNumber;
import com.avisys.cim.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	//End-point to fetch data using Get method or any parameter matching to relevant data.
	@GetMapping("/getcustomer")
	public ResponseEntity<List<CustomerDTO>> getCustomers(@RequestParam(required = false) String firstName,
														@RequestParam(required = false) String lastName,
														@RequestParam(required = false) String mobileNumber)
	{
		List<CustomerDTO> CustomerList = customerService.getCustomers(firstName, lastName, mobileNumber); 
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
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id)
	{
		CustomerDTO customerDto = customerService.findById(id);
		if(customerDto == null)
		{
			return new ResponseEntity("Customer not found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	//End-point to create a new customer using post method
	@PostMapping("/addnewcustomer")
	public ResponseEntity addNewCustomer(@RequestBody CustomerDTO customerDto)
	{
		
		boolean status = customerService.addNewCustomer(customerDto);
		if(status == false)
		{
			return new ResponseEntity("Unable to create Customer. Mobile number already present.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity("Customer added successfully", HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/deletecustomer")
	public ResponseEntity deleteCustomer(@RequestParam String mobileNumber)
	{
		boolean status = customerService.deleteCustomer(mobileNumber);
		if(status == false)
		{
			return new ResponseEntity("Unable to delete Customer.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity("Customer deleted successfully", HttpStatus.OK);
		}
	}
	
	@PutMapping("/deletemobilenumber/{id}")
	public ResponseEntity deleteCustomerMobileNumber(@RequestBody MobileNumber oldNumber, @PathVariable Long id)
	{
		boolean status = customerService.deleteCustomerMobileNumber(oldNumber, id);
		if(status == false)
		{
			return new ResponseEntity("Unable to update Customer mobile number.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity("Customer mobile number updated successfully", HttpStatus.OK);
		}
	}
	
	@PutMapping("/addmobilenumber/{id}")
	public ResponseEntity addCustomerMobileNumber(@RequestBody MobileNumber newNumber, @PathVariable Long id)
	{
		boolean status = customerService.addCustomerMobileNumber(newNumber, id);
		if(status == false)
		{
			return new ResponseEntity("Unable to update Customer mobile number.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity("Customer mobile number updated successfully", HttpStatus.OK);
		}
	}
}










