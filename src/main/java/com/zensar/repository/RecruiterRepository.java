package com.zensar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
	@Query(value = "select * from recruiter where username=?1 and password=?2", nativeQuery = true)
	Recruiter recruiterByItsUsernameAndPassword(String uname, String pass);

	@Query(value = "select * from recruiter where username=?1", nativeQuery = true)
	Recruiter recruiterByItsUsername(String username);

	Optional<Recruiter> findByUsername(String username);

}
