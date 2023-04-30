package com.avisys.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	public List<Customer> getCustomersByPartialLastName(String lastName) 
	{
	        return crepo.findByPartialLastName(lastName);
	}
	 
	public Customer getCustomerByExactMobileNumber(String mobileNumber)
	{
	        return crepo.findByExactMobileNumber(mobileNumber);
	}
	
	public boolean createCustomer(Customer customer) throws DuplicateKeyException 
	{
        Customer existingCustomer = crepo.findByMobileNumber(customer.getMobileNumber());
        if (existingCustomer != null) {
            throw new DuplicateKeyException("Mobile number already present.");
        }
        System.out.println(customer.getId());  //value has come till here
        crepo.save(customer);
        
        return true;
    }
}
