package com.zensar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entities.JobSeeker;
import com.zensar.entities.JobSeekerVerificationToken;
import com.zensar.entities.RecruiterVerificationToken;

public interface JobSeekerVerificationRepository extends JpaRepository<JobSeekerVerificationToken, Long> {

	Optional<JobSeekerVerificationToken> findByToken(String token);

}
