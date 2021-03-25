package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.JobSeekerAuthenticationResponse;
import com.zensar.entities.Jobs;
import com.zensar.entities.Resume;
import com.zensar.exception.GlobalExceptionHandler;

public interface CareerSoltionsJobSeekerService {

	JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws GlobalExceptionHandler;

	List<JobSeeker> getJobSeeker();

	void deleteJobSeeker(int jobSeekerId);

	Applications insertApplications(Applications application);

	Jobs getJobById(int parseInt);

	JobSeeker getJobSeekerById(int parseInt);

	List<Applications> getApplications();

	Applications getApplicationsByApplicationId(int applicationId);

	void deleteApplication(int applicationId);

	Resume saveFile(MultipartFile file);

	Optional<Resume> getFile(Integer fileId);

	List<Resume> getFiles();

	void verifyJobSeeker(String token) throws GlobalExceptionHandler;

	JobSeekerAuthenticationResponse jobSeekerlogin(JobSeeker jobSeeker);

	

	

}
