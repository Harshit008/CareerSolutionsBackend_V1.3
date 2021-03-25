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
	@OneToMany(mappedBy = "jobs", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Applications> applications;
}
