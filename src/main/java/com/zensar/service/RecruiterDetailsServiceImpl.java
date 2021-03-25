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

import com.zensar.entities.Recruiter;
import com.zensar.entities.RecruiterDetails;
import com.zensar.repository.RecruiterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecruiterDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private RecruiterRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Optional<Recruiter> recruiterOptional = repository.findByUsername(username);
		Recruiter recruiter = recruiterOptional
				.orElseThrow(() -> new UsernameNotFoundException("No user " + "Found with username : " + username));
		System.out.println(recruiter);
		return new RecruiterDetails(getAuthorities("USER"), recruiter, true, true, true);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}
}
