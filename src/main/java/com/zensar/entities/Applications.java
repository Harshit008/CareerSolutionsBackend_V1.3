package com.zensar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Applications{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int applicationId;
		private String status;
		
		@ManyToOne
		private JobSeeker jobSeeker;
		@ManyToOne
		private Jobs jobs;
		
//		public void assignJobSeeker(JobSeeker jobseeker) {
//			jobSeeker.add(jobseeker);
//		}
		
		
}
