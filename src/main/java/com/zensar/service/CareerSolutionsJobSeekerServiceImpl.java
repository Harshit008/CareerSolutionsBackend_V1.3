package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.Jobs;
import com.zensar.entities.Resume;
import com.zensar.repository.ApplicationsRepository;
import com.zensar.repository.JobSeekerRepository;
import com.zensar.repository.JobsRepository;
import com.zensar.repository.ResumeRepository;
@Service
public class CareerSolutionsJobSeekerServiceImpl implements CareerSoltionsJobSeekerService {
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private ResumeRepository resumeRepository;
	
	@Override
	public JobSeeker jobSeekerlogin(String username, String password) {
		
		return jobSeekerRepository.jobSeekerByItsUsernameAndPassword(username,password);
	}

	@Override
	public JobSeeker registerJobSeeker(JobSeeker jobSeeker) {
			return jobSeekerRepository.save(jobSeeker);
	}

	@Override
	public List<JobSeeker> getJobSeeker() {
		return jobSeekerRepository.findAll();
	}

	@Override
	public void deleteJobSeeker(int jobSeekerId) {
		jobSeekerRepository.deleteById(jobSeekerId);
	}

	@Override
	public Applications insertApplications(Applications application) {
		return applicationsRepository.save(application);
	}

	@Override
	public Jobs getJobById(int jobId) {
		return jobsRepository.findById(jobId).get();
	}

	@Override
	public JobSeeker getJobSeekerById(int jobseekerId) {
		return jobSeekerRepository.findById(jobseekerId).get();
	}

	@Override
	public List<Applications> getApplications() {
		
		return applicationsRepository.findAll();
	}

	@Override
	public Applications getApplicationsByApplicationId(int applicationId) {
		
		return applicationsRepository.findById(applicationId).get();
	}

	@Override
	public void deleteApplication(int applicationId) {
			applicationsRepository.deleteById(applicationId);
	}
	
	@Override
	public Resume saveFile(MultipartFile file) {
		  String docname = file.getOriginalFilename();
		  try {
			  Resume resume = new Resume(docname,file.getContentType(),file.getBytes());
			  return resumeRepository.save(resume);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
	  
	  @Override
	  public Optional<Resume> getFile(Integer fileId) {
		  return resumeRepository.findById(fileId);
	  }
	  
	  @Override
	  public List<Resume> getFiles(){
		  return resumeRepository.findAll();
	  }

}
