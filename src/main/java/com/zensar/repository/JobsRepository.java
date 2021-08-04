package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entities.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Integer> {

}
