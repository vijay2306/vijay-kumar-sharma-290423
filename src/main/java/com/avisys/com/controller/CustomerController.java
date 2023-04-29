package com.avisys.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.com.Customer;
import com.avisys.com.services.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService cser;
	@GetMapping("/customer")  // getting all customer
	public List<Customer> getAllnfo() {
		
		List<Customer> allcust=cser.getAllInfo();
		return allcust;
	}
	
	@GetMapping("/customerByPartialFirstName")
	public List<Customer> getCustomersByPartialFirstName(@RequestParam("firstName") String firstname)
	{
		return cser.getCustomersByPartialFirstName(firstname);
	
	}
	
	@GetMapping("/customerByPartialLastName")
    public List<Customer> getCustomersByPartialLastName(@RequestParam("lastName") String lastName) {
        return cser.getCustomersByPartialLastName(lastName);
    }
	
	 @GetMapping("/customerBymobileNumber")
	    public Customer getCustomerByExactMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
	        return cser.getCustomerByExactMobileNumber(mobileNumber);
	    }


}
