package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.txw2.Document;
import com.zensar.entities.Applications;
import com.zensar.entities.JobSeeker;
import com.zensar.entities.Jobs;
import com.zensar.service.CareerSoltionsJobSeekerService;

@RestController
//@RequestMapping(value="jobseeker")
public class JobSeekerController {
	
	public JobSeekerController() {

	}
	
	@Autowired
	private CareerSoltionsJobSeekerService service;

	@PostMapping(value="/jobseekerlogin")
	@ResponseBody
	public JobSeeker getJobSeekerLoginPage(@RequestBody JobSeeker jobSeeker) {
			
			return service.jobSeekerlogin(jobSeeker.getUsername(),jobSeeker.getPassword());
	}
	
	@PostMapping(value="/registerjobseeker")
	public JobSeeker registerJobSeeker(@RequestBody JobSeeker jobSeeker, @RequestParam("files") MultipartFile[] files) {
		
			return service.registerJobSeeker(jobSeeker);
	}
	
	@GetMapping(value="/getjobseekers", produces =MediaType.APPLICATION_JSON_VALUE)
	public List<JobSeeker> getJobSeeker(){
		return service.getJobSeeker();
	}
	
	@DeleteMapping(value="/deletejobseeker/{jobId}")
	public void deleteJobSeeker(@PathVariable("jobId") String jobId) {
		service.deleteJobSeeker(Integer.parseInt(jobId));
	}
	
	@PutMapping(value="/updateJobSeeker/{jobSeekerId}")
	public void updateJobSeeker(@PathVariable("jobSeekerId") String jobSeekerId, @RequestBody JobSeeker jobSeeker) {
		 int jobseekerid=Integer.parseInt(jobSeekerId);
		 service.deleteJobSeeker(jobseekerid);
		 jobSeeker.setJobSeekerId(jobseekerid);
		 service.registerJobSeeker(jobSeeker);
		
	}
	
	@PostMapping(value="/insertApplications/{jobId}")
	public void insertApplications(@PathVariable("jobId")String jobId,@RequestBody Applications application) {
		Jobs job=service.getJobById(Integer.parseInt(jobId));
		//JobSeeker jobseeker=service.getJobSeekerById(Integer.parseInt(jobSeekerId));
		application.setJobs(job);	
		//application.assignJobSeeker(jobseeker);
		service.insertApplications(application);
	}
	
	@PutMapping(value = "/applications/{applicationId}/{jobSeekerId}")
	@ResponseBody
	@JsonIgnore
	public Applications assignJobSeeker(@PathVariable("applicationId")String applicationId1,@PathVariable("jobSeekerId")String jobSeekerId1){
		int jobSeekerId=Integer.parseInt(jobSeekerId1);
		int applicationId=Integer.parseInt(applicationId1);
		JobSeeker jobSeeker=service.getJobSeekerById(jobSeekerId);
		Applications application=service.getApplicationsByApplicationId(applicationId);
		application.assignJobSeeker(jobSeeker);
		Applications insertApplication = service.insertApplications(application);
		return insertApplication;
		
	}
	
	
	@GetMapping(value="/getApplications",  produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Applications> getApplications(){
		return service.getApplications();
	}
	
	@DeleteMapping(value="/deleteApplication/{applicationId}")
	public void deleteApplication(@PathVariable("applicationId") String applicationId) {
		service.deleteApplication(Integer.parseInt(applicationId));
	}
	
	
	@PostMapping("/uploadFiles")
	public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file: files) {
			service.saveFile(file);
			
		}
		
	} 
	
	
	
	
	
	
	
	
	
//	@PostMapping("/uploadresume")
//	public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
//		Document doc = new Document();
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		doc.setDocName(fileName);
//		try {
//			doc.setFile(file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		documentDao.save(doc);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/files/download/")
//				.path(fileName).path("/db")
//				.toUriString();
//		return ResponseEntity.ok(fileDownloadUri);
//	}
	
	
}
