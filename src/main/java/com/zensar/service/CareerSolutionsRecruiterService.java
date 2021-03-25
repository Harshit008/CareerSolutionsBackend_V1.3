package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.zensar.entities.Jobs;
import com.zensar.entities.Recruiter;
import com.zensar.entities.RecruiterAuthenticationResponse;
import com.zensar.entities.Resume;
import com.zensar.entities.Skills;
import com.zensar.exception.GlobalExceptionHandler;

import io.jsonwebtoken.security.InvalidKeyException;

public interface CareerSolutionsRecruiterService {

	public List<Recruiter> getRecruiter();

	public Recruiter getRecruiterById(int recruiterId);

	public Recruiter registerRecruiter(Recruiter recruiter) throws GlobalExceptionHandler;

	public Recruiter recruiterLogin(String uname, String pass);

	public Jobs insertJob(Jobs job);

	public List<Jobs> getJobsByRecruiterId(int rid);

	public Recruiter recruiterByItsUsername(String username);

	public Skills insertSkills(Skills skill1);

	public List<Jobs> getJobs();

	public List<Skills> getSkills();

	public Jobs getJobsByJobId(int jid);

	public Skills getSkillsBySkillId(int sid);

	public void deleteJob(int jobId);

	public Optional<Resume> getFile(Integer fileId);

	public void verifyRecruiter(String token) throws GlobalExceptionHandler;

	public RecruiterAuthenticationResponse login(Recruiter recruiter)
			throws InvalidKeyException, GlobalExceptionHandler;

	public Recruiter getRecruiterByUsername(String username);

}
