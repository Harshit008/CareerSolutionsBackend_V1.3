package com.zensar.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zensar.entities.Jobs;
import com.zensar.entities.Recruiter;
import com.zensar.entities.RecruiterAuthenticationResponse;
import com.zensar.entities.Resume;
import com.zensar.entities.Skills;
import com.zensar.exception.GlobalExceptionHandler;
import com.zensar.service.CareerSolutionsRecruiterService;

import io.jsonwebtoken.security.InvalidKeyException;

@CrossOrigin
@RestController
@RequestMapping(value = "/myapp")
public class RecruiterController {
	@Autowired
	CareerSolutionsRecruiterService service;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public RecruiterController() {

	}

	// ****Get List of recruiters*****
	@GetMapping(value = "/recruiter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recruiter>> getRecruiter() {
		List<Recruiter> recruiter = service.getRecruiter();
		System.out.println(recruiter);
		return new ResponseEntity<List<Recruiter>>(recruiter, HttpStatus.OK);
	}

	// ****Get Recruiter by RecruiterId****
	@GetMapping(value = "/recruiter/{recruiterId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recruiter> getRecruiterById(@PathVariable("recruiterId") int recruiterId) {
		Recruiter recruiter = service.getRecruiterById(recruiterId);
		return new ResponseEntity<Recruiter>(recruiter, HttpStatus.OK);
	}

	// ****Register recruiter****
	@PostMapping(value = "/registerrecruiter")
	public void registerRecruiter(@RequestBody(required = true) Recruiter recruiter) throws GlobalExceptionHandler {
		recruiter.setEnabled(false);
		recruiter.setPassword(passwordEncoder.encode(recruiter.getPassword()));
		recruiter.setCreated(Instant.now());
		service.registerRecruiter(recruiter);

	}

	@GetMapping("verifyRecruiter/{token}")
	public ResponseEntity<String> verifyRecruiter(@PathVariable("token") String token) throws GlobalExceptionHandler {
		service.verifyRecruiter(token);
		return new ResponseEntity<String>("Account activated Successfully", HttpStatus.OK);

	}

	// ****Used for recruiter login****
	@PostMapping(value = "/recruiterlogin")
	public ResponseEntity<RecruiterAuthenticationResponse> recruiterLogin(@RequestBody Recruiter recruiter)
			throws InvalidKeyException, GlobalExceptionHandler {
		RecruiterAuthenticationResponse login = service.login(recruiter);
		System.out.println("Token is: " + login.getAuthenticationToken());
		return new ResponseEntity<RecruiterAuthenticationResponse>(login, HttpStatus.OK);
	}

	// ****Used for Inserting Job for a particular recruiter(username)****
	@PostMapping(value = "/insertJob/{username}")
	public ResponseEntity<List<Jobs>> insertJob(@RequestBody(required = true) Jobs job,
			@PathVariable("username") String username) {
		List<Jobs> jobs = new ArrayList<Jobs>();
		Recruiter recruiter = service.recruiterByItsUsername(username);
		job.setRecruiter(recruiter);
		job.setCreated(Instant.now());
		Jobs job1 = new Jobs();
		job1 = service.insertJob(job);
		jobs.add(job1);
		System.out.println(job1);
		return new ResponseEntity<List<Jobs>>(jobs, HttpStatus.OK);
	}

	// **** Used to get jobs for a particular recruiter(id)****
	@GetMapping(value = "/getJobs/{username}")
	@ResponseBody
	public ResponseEntity<List<Jobs>> getJobsByRecruiterId(@PathVariable("username") String username) {
		Recruiter recruiter = service.getRecruiterByUsername(username);
		List<Jobs> jobs = recruiter.getJobs();
		return new ResponseEntity<List<Jobs>>(jobs, HttpStatus.OK);
	}

	@GetMapping(value = "/getJob/{jobId}")
	public ResponseEntity<Jobs> getJobByJobId(@PathVariable("jobId") String jobId) {
		int jid = Integer.parseInt(jobId);
		Jobs jobs = service.getJobsByJobId(jid);
		return new ResponseEntity<Jobs>(jobs, HttpStatus.OK);
	}

	// Will be useful for jobseeker job search
	// **** Get all jobs for all recruiters****
	@GetMapping(value = "/getJobs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Jobs>> getJobs() {
		List<Jobs> jobs = service.getJobs();
		return new ResponseEntity<List<Jobs>>(jobs, HttpStatus.OK);
	}

	// **** Used for assigning a skill to a job****//not in our record
	@PutMapping(value = "/skills/{skillId}/{jobId}")
	@ResponseBody
	@JsonIgnore
	public ResponseEntity<Skills> assignSkills(@PathVariable("skillId") String skillId,
			@PathVariable("jobId") String jobId) {
		int jid = Integer.parseInt(jobId);
		int sid = Integer.parseInt(skillId);
		Jobs job = service.getJobsByJobId(jid);
		Skills skill = service.getSkillsBySkillId(sid);
		skill.assignJobs(job);
		Skills insertSkills = service.insertSkills(skill);
		return new ResponseEntity<Skills>(insertSkills, HttpStatus.OK);
	}

	// **** Used for inserting skills****//CommandLineRunner
	@PostMapping(value = "/insertSkills")
	@ResponseBody
	public ResponseEntity<Skills> insertSkills(@RequestBody(required = true) Skills skills) {
		Skills skill = service.insertSkills(skills);
		return new ResponseEntity<Skills>(skill, HttpStatus.OK);
	}

	// **** Display all skills in DB****
	@JsonIgnore
	@GetMapping(value = "/getSkills", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Skills>> getSkills() {
		List<Skills> skills = service.getSkills();
		return new ResponseEntity<List<Skills>>(skills, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") String jobId) {
		service.deleteJob(Integer.parseInt(jobId));
		return new ResponseEntity<String>("Deleted Job", HttpStatus.OK);
	}

	@PutMapping(value = "/updateJob/{jobId}/{username}")
	public ResponseEntity<Jobs> updateJob(@PathVariable("jobId") String jobId,
			@PathVariable("username") String username, @RequestBody Jobs job) {
		Recruiter recruiter = service.getRecruiterByUsername(username);
		service.deleteJob(Integer.parseInt(jobId));
		job.setJobId(Integer.parseInt(jobId));
		job.setRecruiter(recruiter);
		Jobs jobs = service.insertJob(job);
		return new ResponseEntity<Jobs>(jobs, HttpStatus.OK);
	}

	@GetMapping("/downloadResume1/{fileId}")

	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int fileId) {
		// Load file as Resource
		Resume resumeFile = service.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resumeFile.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resumeFile.getDocName() + "\"")
				.body(new ByteArrayResource(resumeFile.getData()));
	}
}
