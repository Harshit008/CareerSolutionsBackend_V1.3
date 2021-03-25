package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}
