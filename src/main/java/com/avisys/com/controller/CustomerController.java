package com.avisys.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/customerByPartialFirstName") // getting all customer by his partial firstname
	public List<Customer> getCustomersByPartialFirstName(@RequestParam("firstName") String firstname)
	{
		return cser.getCustomersByPartialFirstName(firstname);
	
	}
	
	@GetMapping("/customerByPartialLastName") //getting all customer by his partial Lastname
    public List<Customer> getCustomersByPartialLastName(@RequestParam("lastName") String lastName) {
        return cser.getCustomersByPartialLastName(lastName);
    }
	
	 @GetMapping("/customerBymobileNumber") //getting all customer by his mobileNumber
	    public Customer getCustomerByExactMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
	        return cser.getCustomerByExactMobileNumber(mobileNumber);
	    }
	 
	 @PostMapping("/createcustomersMobilenoNotExist")  //creating the customer whose mobile is not in db
	    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
	        try {
	            boolean created = cser.createCustomer(customer);
	            if (created) {
	                return ResponseEntity.ok("Customer created successfully.");
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create Customer.");
	            }
	        } catch (DuplicateKeyException e) 
	        {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create Customer. Mobile number already present.");
	        }

	 }
}
