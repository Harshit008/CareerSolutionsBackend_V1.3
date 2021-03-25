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
public class RecruiterDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	Collection<? extends GrantedAuthority> authorities=null;
	private Recruiter recruiter;
	private boolean AccountNonLocked;
	private boolean AccountNonExpired;
	private boolean CredentialsNonExpired;
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return recruiter.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return recruiter.getUsername();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return recruiter.isEnabled();
	}
	public RecruiterDetails(Collection<? extends GrantedAuthority> authorities, Recruiter recruiter, boolean accountNonLocked,
			boolean accountNonExpired, boolean credentialsNonExpired) {
		super();
		this.authorities = authorities;
		this.recruiter = recruiter;
		AccountNonLocked = accountNonLocked;
		AccountNonExpired = accountNonExpired;
		CredentialsNonExpired = credentialsNonExpired;
	}
//	public RecruiterDetails(Collection<? extends GrantedAuthority> authorities2, Recruiter recruiter2,
//			boolean accountNonLocked2, boolean accountNonExpired2, boolean credentialsNonExpired2) {
//		
//	}
	
	
	

}
