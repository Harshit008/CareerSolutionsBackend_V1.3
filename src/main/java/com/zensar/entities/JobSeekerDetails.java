package com.zensar.entities;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
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
	
	Collection<? extends GrantedAuthority> authorities=null;
	private JobSeeker jobSeeker;
	private boolean AccountNonLocked;
	private boolean AccountNonExpired;
	private boolean CredentialsNonExpired;
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return jobSeeker.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return jobSeeker.getUsername();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return jobSeeker.isEnabled();
	}
	public JobSeekerDetails(Collection<? extends GrantedAuthority> authorities, JobSeeker jobSeeker, boolean accountNonLocked,
			boolean accountNonExpired, boolean credentialsNonExpired) {
		super();
		this.authorities = authorities;
		this.jobSeeker = jobSeeker;
		AccountNonLocked = accountNonLocked;
		AccountNonExpired = accountNonExpired;
		CredentialsNonExpired = credentialsNonExpired;
	}
//	public RecruiterDetails(Collection<? extends GrantedAuthority> authorities2, Recruiter recruiter2,
//			boolean accountNonLocked2, boolean accountNonExpired2, boolean credentialsNonExpired2) {
//		
//	}
	
	
	

}

