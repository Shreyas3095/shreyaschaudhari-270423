package com.avisys.cim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.cim.beans.Customer;
import com.avisys.cim.beans.CustomerDTO;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long>{
	
//	public Customer findByMobileNumber(String mobileNumber);
}
