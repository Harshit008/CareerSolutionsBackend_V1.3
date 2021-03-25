package com.zensar.repository;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entities.Jobs;
import com.zensar.entities.Recruiter;

public interface JobsRepository extends JpaRepository<Jobs, Integer> {

	
//	@Query(value="select * from jobs where recruiter_recruiterId=?1")
//	List<Jobs> getJobsByItsRecruiterId(int rid);
	/*@NamedNativeQueries(value = {
	@NamedNativeQuery(name = "Product.productByItsName", query = "select * from product where name=?1", resultClass = Product.class),
	@NamedNativeQuery(name = "Product.productByItsCost", query = "select * from product where cost=?1", resultClass = Product.class) })
	*/

}
