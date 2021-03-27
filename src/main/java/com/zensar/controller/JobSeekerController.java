package com.zensar.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.JobSeekerAuthenticationResponse;
import com.zensar.entities.Jobs;
import com.zensar.entities.Resume;
import com.zensar.exception.GlobalExceptionHandler;
import com.zensar.service.CareerSoltionsJobSeekerService;

@RestController
@RequestMapping(value = "/myapp")
public class JobSeekerController {

	public JobSeekerController() {

	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CareerSoltionsJobSeekerService service;

	@PostMapping(value = "/jobseekerlogin")
	// @ResponseBody
	public ResponseEntity<JobSeekerAuthenticationResponse> getJobSeekerLoginPage(@RequestBody JobSeeker jobSeeker) {

		JobSeekerAuthenticationResponse jobSeekerlogin = service.jobSeekerlogin(jobSeeker);
		return new ResponseEntity<JobSeekerAuthenticationResponse>(jobSeekerlogin, HttpStatus.OK);
	}

	@PostMapping(value = "/registerjobseeker")
	@JsonIgnore
	public ResponseEntity<JobSeeker> registerJobSeeker(@RequestBody JobSeeker jobSeeker) throws GlobalExceptionHandler {
		jobSeeker.setEnabled(false);
		jobSeeker.setCreated(Instant.now());
		jobSeeker.setPassword(passwordEncoder.encode(jobSeeker.getPassword()));
		JobSeeker registerJobSeeker = service.registerJobSeeker(jobSeeker);
		return new ResponseEntity<JobSeeker>(registerJobSeeker, HttpStatus.OK);
	}

	@GetMapping("verifyJobSeeker/{token}")
	public ResponseEntity<String> verifyJobSeeker(@PathVariable("token") String token) throws GlobalExceptionHandler {
		service.verifyJobSeeker(token);
		return new ResponseEntity<String>("Account activated Successfully", HttpStatus.OK);

	}

	@GetMapping(value = "/getjobseekers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobSeeker>> getJobSeeker() {
		List<JobSeeker> jobSeeker = service.getJobSeeker();
		return new ResponseEntity<List<JobSeeker>>(jobSeeker, HttpStatus.OK);
	}
	
	
	// Will be useful for jobseeker job search
		// **** Get all jobs for all recruiters****
		@GetMapping(value = "/jobSeeker/getJobs", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Jobs>> getJobs() {
			List<Jobs> jobs = service.getJobs();
			return new ResponseEntity<List<Jobs>>(jobs, HttpStatus.OK);
		}

	@DeleteMapping(value = "/deletejobseeker/{jobId}")
	public ResponseEntity<String> deleteJobSeeker(@PathVariable("jobId") String jobId) {
		service.deleteJobSeeker(Integer.parseInt(jobId));
		return new ResponseEntity<String>("JobSeeker Deleted", HttpStatus.OK);
	}

	@PutMapping(value = "/updateJobSeeker/{jobSeekerId}")
	public ResponseEntity<String> updateJobSeeker(@PathVariable("jobSeekerId") String jobSeekerId,
			@RequestBody JobSeeker jobSeeker) throws GlobalExceptionHandler {
		int jobseekerid = Integer.parseInt(jobSeekerId);
		service.deleteJobSeeker(jobseekerid);
		jobSeeker.setJobSeekerId(jobseekerid);
		service.registerJobSeeker(jobSeeker);
		return new ResponseEntity<String>("Jobseeker updated", HttpStatus.OK);
	}

	@PostMapping(value = "/insertApplications/{jobId}/{jobSeekerUsername}")
	public ResponseEntity<String> insertApplications(@PathVariable("jobId") String jobId,@PathVariable("jobSeekerUsername")String username,
			@RequestBody Applications application) {
		Jobs job = service.getJobById(Integer.parseInt(jobId));
		JobSeeker jobSeeker = service.getJobSeekerByUsername(username);
		// JobSeeker jobseeker=service.getJobSeekerById(Integer.parseInt(jobSeekerId));
		application.setJobs(job);
		application.setStatus(false);
		application.setJobSeeker(jobSeeker);
		//application.assignJobSeeker(jobSeeker);
		// application.assignJobSeeker(jobseeker);
		service.insertApplications(application);
		return new ResponseEntity<String>("Application Inserted", HttpStatus.OK);
	}
	
	@GetMapping(value="/getApplication/{jobSeekerUsername}")
	public ResponseEntity<List<Applications>> displayApplication(@PathVariable("jobSeekerUsername")String username){
		JobSeeker jobSeeker = service.getJobSeekerByUsername(username);
		List<Applications> applications = jobSeeker.getApplications();
		
		return new ResponseEntity<List<Applications>>(applications, HttpStatus.OK);
		
	}

	@PutMapping(value = "/applications/{applicationId}/{jobSeekerId}")
	@ResponseBody
	@JsonIgnore
	public ResponseEntity<Applications> assignJobSeeker(@PathVariable("applicationId") String applicationId1,
			@PathVariable("jobSeekerId") String jobSeekerId1) {
		int jobSeekerId = Integer.parseInt(jobSeekerId1);
		int applicationId = Integer.parseInt(applicationId1);
		JobSeeker jobSeeker = service.getJobSeekerById(jobSeekerId);
		Applications application = service.getApplicationsByApplicationId(applicationId);
		//application.assignJobSeeker(jobSeeker);
		Applications insertApplication = service.insertApplications(application);
		return new ResponseEntity<Applications>(insertApplication, HttpStatus.OK);

	}

	@GetMapping(value = "/getApplications", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Applications>> getApplications() {
		List<Applications> applications = service.getApplications();
		return new ResponseEntity<List<Applications>>(applications, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteApplication/{applicationId}")
	public ResponseEntity<String> deleteApplication(@PathVariable("applicationId") String applicationId) {
		service.deleteApplication(Integer.parseInt(applicationId));
		return new ResponseEntity<String>("Application Deleted", HttpStatus.OK);
	}

	@PostMapping("/uploadFiles")
	public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file : files) {
			service.saveFile(file);

		}

	}

}
