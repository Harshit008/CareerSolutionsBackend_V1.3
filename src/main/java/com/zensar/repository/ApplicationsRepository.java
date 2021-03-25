package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entities.Applications;

public interface ApplicationsRepository extends JpaRepository<Applications, Integer> {
	
}
