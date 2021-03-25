package com.zensar.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	@ManyToMany(mappedBy = "jobSeeker")
	private List<Applications> applications;
	
	@OneToOne(mappedBy = "jobSeeker")
	private Resume resume;
}
