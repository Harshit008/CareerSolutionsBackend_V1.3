package com.zensar.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Applications> applications;
	
	
//	@OneToOne(mappedBy = "jobSeeker")
//	private Resume resume;
}
