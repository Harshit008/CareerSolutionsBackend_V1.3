package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.Jobs;
import com.zensar.entities.Resume;

public interface CareerSoltionsJobSeekerService {

	JobSeeker jobSeekerlogin(String username, String password);

	JobSeeker registerJobSeeker(JobSeeker jobSeeker);

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

	

}
