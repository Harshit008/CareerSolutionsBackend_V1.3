package com.zensar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.JobSeekerAuthenticationResponse;
import com.zensar.entities.JobSeekerDetails;
import com.zensar.entities.JobSeekerVerificationToken;
import com.zensar.entities.Jobs;
import com.zensar.entities.NotificationEmail;
import com.zensar.entities.Recruiter;
import com.zensar.entities.RecruiterAuthenticationResponse;
import com.zensar.entities.RecruiterDetails;
import com.zensar.entities.RecruiterVerificationToken;
import com.zensar.entities.Resume;
import com.zensar.exception.GlobalExceptionHandler;
import com.zensar.repository.ApplicationsRepository;
import com.zensar.repository.JobSeekerRepository;
import com.zensar.repository.JobSeekerVerificationRepository;
import com.zensar.repository.JobsRepository;
import com.zensar.repository.ResumeRepository;
import com.zensar.security.JwtUtil;

import io.jsonwebtoken.security.InvalidKeyException;
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
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private JobSeekerVerificationRepository verificationTokenRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JobSeekerDetailsServiceImpl jobSeekerDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public JobSeeker registerJobSeeker(JobSeeker jobSeeker) throws GlobalExceptionHandler {
		boolean res =jobSeekerRepository.save(jobSeeker)!=null;
			
			String token = generateVerificationToken(jobSeeker);
			mailService.sendMail(new NotificationEmail("Please Activate your account",jobSeeker.getEmail(),"Thank you for signing up to Career Solutions, " +
	                "please click on the below url to activate your account : " +
	                "http://localhost:9999/myapp/verifyJobSeeker/" + token));
			if (res)
				return jobSeeker;
			else
				return null;
	}

	private String generateVerificationToken(JobSeeker jobSeeker) {
		String token = UUID.randomUUID().toString();
		JobSeekerVerificationToken verificationToken= new JobSeekerVerificationToken();
		verificationToken.setToken(token);
		verificationToken.setJobSeeker(jobSeeker);
		verificationTokenRepository.save(verificationToken);
		return token;
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

	  @Override
		public void verifyJobSeeker(String token) throws GlobalExceptionHandler {
				Optional<JobSeekerVerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
				verificationToken.orElseThrow(()-> new GlobalExceptionHandler("Invalid Token"));
				fetchJobSeekerAndEnable(verificationToken.get());
		}
	  
	  @Transactional
		private void fetchJobSeekerAndEnable(JobSeekerVerificationToken jobSeekerVerificationToken) throws GlobalExceptionHandler {
				String username = jobSeekerVerificationToken.getJobSeeker().getUsername();
				JobSeeker jobSeeker = jobSeekerRepository.findByUsername(username).orElseThrow(()-> new GlobalExceptionHandler("User not found with name - "+username));
				jobSeeker.setEnabled(true);
				jobSeekerRepository.save(jobSeeker);
		}

	

	@Override
	public JobSeekerAuthenticationResponse jobSeekerlogin(JobSeeker jobSeeker) {
		System.out.println(jobSeeker);
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jobSeeker.getUsername(), jobSeeker.getPassword()));
		System.out.println(authenticate);
		JobSeekerDetails jobSeekerDetails = (JobSeekerDetails) this.jobSeekerDetailService.loadUserByUsername(jobSeeker.getUsername());
		String token = this.jwtUtil.generateToken(jobSeekerDetails);
		System.out.println(token);
		//Recruiter principal = (Recruiter) authenticate.getPrincipal();
		//System.out.println(principal);
		//SecurityContextHolder.getContext().setAuthentication(authenticate);
		//String token = jwtProvider.generateRecruiterToken(authenticate);
		//System.out.println(token);
		return new  JobSeekerAuthenticationResponse(token,jobSeeker.getUsername());
	}

}
