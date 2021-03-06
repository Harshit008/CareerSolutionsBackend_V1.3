package com.zensar.entities;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recruiter_token")
public class RecruiterVerificationToken {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String token;
	@OneToOne(fetch = LAZY)
	private Recruiter recruiter;
}