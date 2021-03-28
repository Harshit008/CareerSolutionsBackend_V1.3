package com.zensar.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
@ToString
public class JobSeeker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobSeekerId;
	private String name;
	private String password;
	private String username;
	private String email;
	private boolean enabled;
	private Instant created;
	
	
	//Professional Deatils
	private String experience;
	private String previousCompany;
	private String careerLevel;
	private double previousctc;
	private String certifications;
	private String projects;
	
	//Educational Details
	private String levelOfEducation;
	private String fieldOfStudy;
	private String college;
	private int hscMarks;
	private int sscMarks;
	private double graduationMarks;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Applications> applications;
	
	
//	@OneToOne(mappedBy = "jobSeeker")
//	private Resume resume;
}
