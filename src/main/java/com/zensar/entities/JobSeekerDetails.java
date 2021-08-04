package com.zensar.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobSeekerDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	Collection<? extends GrantedAuthority> authorities = null;
	private JobSeeker jobSeeker;
	private boolean AccountNonLocked;
	private boolean AccountNonExpired;
	private boolean CredentialsNonExpired;

	@Override
	public String getPassword() {
		return jobSeeker.getPassword();
	}

	@Override
	public String getUsername() {
		return jobSeeker.getUsername();
	}

	@Override
	public boolean isEnabled() {
		return jobSeeker.isEnabled();
	}

	public JobSeekerDetails(Collection<? extends GrantedAuthority> authorities, JobSeeker jobSeeker,
			boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired) {
		super();
		this.authorities = authorities;
		this.jobSeeker = jobSeeker;
		AccountNonLocked = accountNonLocked;
		AccountNonExpired = accountNonExpired;
		CredentialsNonExpired = credentialsNonExpired;
	}

}
