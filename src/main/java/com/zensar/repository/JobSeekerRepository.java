package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entities.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
	@Query(value="select * from job_seeker where username=?1 and password=?2", nativeQuery = true)
	JobSeeker jobSeekerByItsUsernameAndPassword(String username, String password);

}
