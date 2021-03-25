package com.zensar.service;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zensar.entities.JobSeeker;
import com.zensar.entities.JobSeekerDetails;
import com.zensar.repository.JobSeekerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobSeekerDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private JobSeekerRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Optional<JobSeeker> jobSeekerOptional = repository.findByUsername(username);
		JobSeeker jobSeeker = jobSeekerOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : " + username));
		System.out.println(jobSeeker);

		return new JobSeekerDetails(getAuthorities("USER"), jobSeeker, true, true, true);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}
}
