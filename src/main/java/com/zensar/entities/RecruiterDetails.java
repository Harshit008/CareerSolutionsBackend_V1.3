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
public class RecruiterDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	Collection<? extends GrantedAuthority> authorities = null;
	private Recruiter recruiter;
	private boolean AccountNonLocked;
	private boolean AccountNonExpired;
	private boolean CredentialsNonExpired;

	@Override
	public String getPassword() {
		return recruiter.getPassword();
	}

	@Override
	public String getUsername() {
		return recruiter.getUsername();
	}

	@Override
	public boolean isEnabled() {
		return recruiter.isEnabled();
	}

	public RecruiterDetails(Collection<? extends GrantedAuthority> authorities, Recruiter recruiter,
			boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired) {
		super();
		this.authorities = authorities;
		this.recruiter = recruiter;
		AccountNonLocked = accountNonLocked;
		AccountNonExpired = accountNonExpired;
		CredentialsNonExpired = credentialsNonExpired;
	}

}
