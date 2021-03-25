package com.zensar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer>{
	@Query(value="select * from recruiter where username=?1 and password=?2", nativeQuery = true)
	Recruiter recruiterByItsUsernameAndPassword(String uname, String pass);
	@Query(value="select * from recruiter where username=?1", nativeQuery = true)
	Recruiter recruiterByItsUsername(String username);
	
	Optional<Recruiter> findByUsername(String username);
	
	

	
	
	
	
	
	
	
	
	
	
	
	/* By using HQL */
	//@NamedQuery(name = "Product.productByItsName", query = "FROM Product p where p.productName=?1")
	//@NamedQuery(name = "Product.productByItsCost", query = "FROM Product p where p.productCost=?1")
	 
	/*
	 * @NamedQueries(value = { @NamedQuery(name = "Product.productByItsName", query
	 * = "FROM Product p where p.productName=?1"),
	 * 
	 * @NamedQuery(name = "Product.productByItsCost", query =
	 * "FROM Product p where p.productCost=?1") })
	 */
	 

	/* SQL native queries */
	//@NamedNativeQuery(name = "Product.productByItsName", query = "select * from product where name=?1", resultClass = Product.class)
	 
	 /*@NamedNativeQueries(value = {
	        @NamedNativeQuery(name = "Product.productByItsName", query = "select * from product where name=?1", resultClass = Product.class),
	        @NamedNativeQuery(name = "Product.productByItsCost", query = "select * from product where cost=?1", resultClass = Product.class) })
	*/
	
	
	
}
