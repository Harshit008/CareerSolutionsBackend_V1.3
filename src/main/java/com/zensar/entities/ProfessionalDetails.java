package com.zensar.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDetails {
	@Id
	private int id;
	
	private String experience;
	private String previousCompany;
	private String careerLevel;
	private double previousctc;
	private String certifications;
	private String projects;
	
	@OneToOne
	private JobSeeker jobSeeker;
}
