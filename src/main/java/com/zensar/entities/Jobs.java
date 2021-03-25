package com.zensar.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	private String jobTitle;
	private String jobDesc;
	private String jobField;
	
	
	private String employementType;
	
	private double ctc;
	
	private float experienceInYears;
	
	private String education;
	
	private Instant created;
	@ManyToOne
	private Recruiter recruiter;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "jobs")
	private List<Skills> skills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobs", orphanRemoval = true, cascade = CascadeType.ALL )
	private List<Applications> applications;
	
	/*
	 * public int getJobId() { return jobId; } public void setJobId(int jobId) {
	 * this.jobId = jobId; } public String getJobTitle() { return jobTitle; } public
	 * void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; } public String
	 * getJobDesc() { return jobDesc; } public void setJobDesc(String jobDesc) {
	 * this.jobDesc = jobDesc; } public String getJobField() { return jobField; }
	 * public void setJobField(String jobField) { this.jobField = jobField; } public
	 * List<Skills> getSkills() { return skills; } public void
	 * setSkills(List<Skills> skills) { this.skills = skills; } public String
	 * getEmployementType() { return employementType; } public void
	 * setEmployementType(String employementType) { this.employementType =
	 * employementType; } public double getCtc() { return ctc; } public void
	 * setCtc(double ctc) { this.ctc = ctc; } public float getExperienceInYears() {
	 * return experienceInYears; } public void setExperienceInYears(float
	 * experienceInYears) { this.experienceInYears = experienceInYears; } public
	 * String getEducation() { return education; } public void setEducation(String
	 * education) { this.education = education; }
	 * 
	 * public int getRecruiterId() { return recruiterId; } public void
	 * setRecruiterId(int recruiterId) { this.recruiterId = recruiterId; }
	 */

	

}
