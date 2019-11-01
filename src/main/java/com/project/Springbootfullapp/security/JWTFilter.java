package com.project.Springbootfullapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.Springbootfullapp.util.AppConstants;
import com.project.Springbootfullapp.util.JWTUtil;

/**
 * @author RAJA
 *
 */

@Component
public class JWTFilter extends OncePerRequestFilter {
	private static final String module = JWTFilter.class.getName();

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AppUserDetailService appUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String tokenHeader = request.getHeader(AppConstants.authorization);
		String username = null;
		String jwt = null;

		if (tokenHeader != null && tokenHeader.startsWith(AppConstants.jwtPrefix)) {
			jwt = tokenHeader.substring(tokenHeader.lastIndexOf("*") + 1, tokenHeader.length());
			username = jwtUtil.extractUsername(jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = appUserDetailService.loadUserByUsername(username);
			if (jwtUtil.isValidToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
