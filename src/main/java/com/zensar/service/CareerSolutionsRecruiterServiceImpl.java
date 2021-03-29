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

import com.zensar.entities.JobSeeker;
import com.zensar.entities.Jobs;
import com.zensar.entities.NotificationEmail;
import com.zensar.entities.Recruiter;
import com.zensar.entities.RecruiterAuthenticationResponse;
import com.zensar.entities.RecruiterDetails;
import com.zensar.entities.RecruiterVerificationToken;
import com.zensar.entities.Resume;
import com.zensar.entities.Skills;
import com.zensar.exception.GlobalExceptionHandler;
import com.zensar.repository.JobSeekerRepository;
import com.zensar.repository.JobsRepository;
import com.zensar.repository.RecruiterRepository;
import com.zensar.repository.RecruiterVerificationRepository;
import com.zensar.repository.ResumeRepository;
import com.zensar.repository.SkillsRepository;
import com.zensar.security.JwtUtil;

import io.jsonwebtoken.security.InvalidKeyException;
import lombok.AllArgsConstructor;

//UserDetailsService 
@Service
@Transactional
@AllArgsConstructor
public class CareerSolutionsRecruiterServiceImpl implements CareerSolutionsRecruiterService {
	@Autowired
	private RecruiterRepository repository;

	@Autowired
	private JobsRepository jobsRepository;

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private RecruiterVerificationRepository verificationTokenRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private RecruiterDetailsServiceImpl recruiterDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public List<Recruiter> getRecruiter() {
		return repository.findAll();
	}

	@Override
	public Recruiter getRecruiterById(int recruiterId) {
		return repository.findById(recruiterId).get();
	}

	@Override
	public Recruiter registerRecruiter(Recruiter recruiter) throws GlobalExceptionHandler {
		boolean res = repository.save(recruiter) != null;

		String token = generateVerificationToken(recruiter);
		mailService.sendMail(new NotificationEmail("Please Activate your account", recruiter.getEmail(),
				"Thank you for signing up to Career Solutions, "
						+ "please click on the below url to activate your account : "
						+ "http://http://careersolutionsbackendv1-env.eba-umxntpch.ap-south-1.elasticbeanstalk.com/myapp/verifyRecruiter/" + token));
		if (res)
			return recruiter;
		else
			return null;

	}

	private String generateVerificationToken(Recruiter recruiter) {
		String token = UUID.randomUUID().toString();
		RecruiterVerificationToken verificationToken = new RecruiterVerificationToken();
		verificationToken.setToken(token);
		verificationToken.setRecruiter(recruiter);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	@Override
	public Recruiter recruiterLogin(String uname, String pass) {
		Recruiter recruiterByItsUsernameAndPassword = repository.recruiterByItsUsernameAndPassword(uname, pass);
		return recruiterByItsUsernameAndPassword;
	}

	@Override
	public Jobs insertJob(Jobs job) {
		boolean res = jobsRepository.save(job) != null;
		if (res)
			return job;
		else
			return null;
	}

	public List<Jobs> getJobsByRecruiterId(int rid) {
		Recruiter recruiter = repository.findById(rid).get();

		return null;

	}

	@Override
	public Recruiter recruiterByItsUsername(String username) {

		return repository.recruiterByItsUsername(username);
	}

	@Override
	public Skills insertSkills(Skills skill1) {
		boolean res = skillsRepository.save(skill1) != null;
		if (res)
			return skill1;
		else
			return null;

	}

	@Override
	public List<Jobs> getJobs() {

		return jobsRepository.findAll();
	}

	@Override
	public List<Skills> getSkills() {

		return skillsRepository.findAll();
	}

	@Override
	public Jobs getJobsByJobId(int jid) {

		return jobsRepository.findById(jid).get();
	}

	@Override
	public Skills getSkillsBySkillId(int sid) {

		return skillsRepository.findById(sid).get();
	}

	@Override
	public void deleteJob(int jobId) {
		jobsRepository.deleteById(jobId);
	}

	@Override
	public Optional<Resume> getFile(Integer fileId) {
		return resumeRepository.findById(fileId);
	}

	@Override
	public void verifyRecruiter(String token) throws GlobalExceptionHandler {
		Optional<RecruiterVerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new GlobalExceptionHandler("Invalid Token"));
		fetchRecruiterAndEnable(verificationToken.get());
	}

	@Transactional
	private void fetchRecruiterAndEnable(RecruiterVerificationToken recruiterVerificationToken)
			throws GlobalExceptionHandler {
		String username = recruiterVerificationToken.getRecruiter().getUsername();
		Recruiter recruiter = repository.findByUsername(username)
				.orElseThrow(() -> new GlobalExceptionHandler("User not found with name - " + username));
		recruiter.setEnabled(true);
		repository.save(recruiter);
	}

	@Override
	public RecruiterAuthenticationResponse login(Recruiter recruiter)
			throws InvalidKeyException, GlobalExceptionHandler {
		System.out.println(recruiter);
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(recruiter.getUsername(), recruiter.getPassword()));
		System.out.println(authenticate);
		RecruiterDetails recruiterDetails = (RecruiterDetails) this.recruiterDetailsService
				.loadUserByUsername(recruiter.getUsername());
		String token = this.jwtUtil.generateToken(recruiterDetails);
		System.out.println(token);
		return new RecruiterAuthenticationResponse(token, recruiter.getUsername());
	}

	@Override
	public Recruiter getRecruiterByUsername(String username) {
		return repository.findByUsername(username).get();
	}

	@Override
	public JobSeeker getJobSeekerById(int jobSeekerId) {
		return jobSeekerRepository.findById(jobSeekerId).get();
	}

}
