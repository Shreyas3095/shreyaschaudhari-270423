package com.avisys.cim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.beans.Customer;
import com.avisys.cim.beans.CustomerDTO;
import com.avisys.cim.beans.MobileNumber;
import com.avisys.cim.dao.CustomerDAO;
import com.avisys.cim.dao.MobileNumberDAO;
import com.avisys.cim.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImplementation implements CustomerService{
	@Autowired
	CustomerDAO CustomerDao;
	@Autowired
	MobileNumberDAO MobileNumberDao;

	public List<CustomerDTO> getCustomers(String firstName, String lastName, String mobileNumber)
	{
		List<Customer> CustomerList = CustomerDao.findAll();
		List<CustomerDTO> CustomerDTOList = CustomerList.stream().map(CustomerDTO::new).collect(Collectors.toList());
		if(firstName == null && lastName == null && mobileNumber == null)
		{
			return CustomerDTOList;
		}
		if(firstName != null)
		{
			CustomerDTOList = CustomerDTOList.stream().filter(c -> c.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(lastName != null)
		{
			CustomerDTOList = CustomerDTOList.stream().filter(c -> c.getLastName().toLowerCase().contains(lastName.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(mobileNumber != null)
		{
			CustomerDTO matchedCustomer = null;
			for(CustomerDTO customerDto : CustomerDTOList)
			{
				List<String> IndividualCustomerMobileNumberList = customerDto.getMobileNumbers();
				for(String number : IndividualCustomerMobileNumberList)
				{
					if(number.equals(mobileNumber))
					{
						matchedCustomer = customerDto;
						break;
					}
				}
			}
			CustomerDTOList.clear();
			CustomerDTOList.add(matchedCustomer);
		}
		return CustomerDTOList;
	}

	//Method to get object matching with given id from the repository.
	@Override
	public CustomerDTO findById(Long id) {
		Customer customer = CustomerDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		CustomerDTO customerDto = new CustomerDTO(customer);
		return customerDto;
	}
	
	//Method to save the new customer in h2 database.
	@Override
	public boolean addNewCustomer(CustomerDTO customerDto) {
		//check whether the customer already exists. Check on the basis of duplicate mobile number.
		List<String> newCustomerMobileNumberList = customerDto.getMobileNumbers();
		List<Customer> CustomerList = CustomerDao.findAll();
		List<MobileNumber> existingMobileNumbersList = new ArrayList<>();
		for(Customer existingCustomer : CustomerList)
		{
			existingMobileNumbersList.addAll(existingCustomer.getMobileNumbers()); //List of objects
		}
		//check new mobile numbers with existing mobile numbers in database		
		for(String newNumber : newCustomerMobileNumberList)
		{
			for(MobileNumber existingNumber : existingMobileNumbersList)
			{
				if(newNumber.equals(existingNumber.toString()))
				{
					return false;
				}
			}
		}
		//Creating new customer object with first name, last name
		Customer newCustomer = new Customer(customerDto.getFirstName(), customerDto.getLastName());
		List<MobileNumber> newMobileNumbers = new ArrayList<>();
		//Set each new mobile number to the same customer object
		for(String mobileNumber : customerDto.getMobileNumbers())
		{
			newMobileNumbers.add(new MobileNumber(mobileNumber, newCustomer));
		}
		//set new mobile numbers to the created customer object
		newCustomer.setMobileNumbers(newMobileNumbers);
		CustomerDao.save(newCustomer);
		return true;
	}
}
