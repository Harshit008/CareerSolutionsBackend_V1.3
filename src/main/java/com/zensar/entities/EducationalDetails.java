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
public class EducationalDetails {
	@Id
	private int id;
	
	private String levelOfEducation;
	private String fieldOfStudy;
	private String college;
	private int hscMarks;
	private int sscMarks;
	private int graduationMarks;
	
	@OneToOne
	private JobSeeker jobSeeker;
}
