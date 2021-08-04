package com.zensar.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zensar.entities.RecruiterDetails;
import com.zensar.security.JwtUtil;
import com.zensar.service.RecruiterDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RecruiterDetailsServiceImpl recruitDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtUtil.extractUsername(jwtToken);

			} catch (Exception e) {
				e.printStackTrace();
			}

			RecruiterDetails recruiterDetails = (RecruiterDetails) this.recruitDetailsService
					.loadUserByUsername(username);

			// Validation process
			try {
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							recruiterDetails, null, recruiterDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				}
			} catch (Exception e) {
				System.out.println("Token is not validated");
				e.printStackTrace();
			}

		}
		filterChain.doFilter(request, response);
	}

}
