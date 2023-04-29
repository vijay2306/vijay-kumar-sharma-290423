package com.avisys.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.com.Customer;
import com.avisys.com.repositries.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository crepo;
	
	public List<Customer> getAllInfo()
	{
		return crepo.findAll();
	}
	public List<Customer> getCustomersByPartialFirstName(String firstName)
	{
		List<Customer> firstCustomer=crepo.findByPartialFirstName(firstName);
		return firstCustomer;
	}
	 public List<Customer> getCustomersByPartialLastName(String lastName) {
	        return crepo.findByPartialLastName(lastName);
	    }
	 
	 public Customer getCustomerByExactMobileNumber(String mobileNumber) {
	        return crepo.findByExactMobileNumber(mobileNumber);
	    }
}
