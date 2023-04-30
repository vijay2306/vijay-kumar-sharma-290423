package com.avisys.com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	public void deleteCustomerByMobileNumber(String mobileNumber)
	{
		Customer c=crepo.findByMobileNumber(mobileNumber);
		 if (c!=null) {
			 System.out.println(c.getFirstName());
         	crepo.delete(c);
         	
         	
         } else {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
         }
	}
	public Customer updateCustomerMobileNumber(Long id,String mobileNumber)
	{
		Optional<Customer> optionalCustomer = crepo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
        String mobile = customer.getMobileNumber();
        
        
            if (mobile.equals(mobileNumber)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mobile number already exists");
            }
            customer.setMobileNumber(mobileNumber);
            return crepo.save(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
       
	}
	
	public Customer deleteCustomerMobileNumber(Long id,String mobileNumber)
	{
		 Optional<Customer> optionalCustomer = crepo.findById(id);
		 if (optionalCustomer.isPresent()) {
	           Customer customer = optionalCustomer.get();
	           String mobile = customer.getMobileNumber();
	           List<String> mobileNumbers=new ArrayList<>();
	           mobileNumbers.add(mobile);
	           if (mobileNumbers.contains(mobileNumber)) {
	               mobileNumbers.remove(mobileNumber);
	               String newmobile=mobileNumbers.toString();
	               customer.setMobileNumber(newmobile);
	               return crepo.save(customer);
	           } else {
	               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile number not found for customer");
	           }
	       } else {
	           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
	       }
	}
}
