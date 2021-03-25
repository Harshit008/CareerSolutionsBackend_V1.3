package com.zensar.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skills")
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int skillId;

	private String skill;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "skill_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
	private List<Jobs> jobs;

	public void assignJobs(Jobs job) {
		jobs.add(job);

	}

	/* @JoinColumns(value = @JoinColumn(name = "jobId")) */

}
