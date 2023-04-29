/**
 * 
 */
package com.avisys.com.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.com.Customer;

/**
 * @author vijay
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
	public List<Customer> findByPartialFirstName(@Param("firstName") String firstName);
	
	 @Query("SELECT c FROM Customer c WHERE LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
	 public List<Customer> findByPartialLastName(@Param("lastName") String lastName);
	 
	 @Query("SELECT c FROM Customer c WHERE mobileNumber=:mobileNumber")
	 public Customer findByExactMobileNumber(@Param("mobileNumber") String mobileNumber);
	
}
