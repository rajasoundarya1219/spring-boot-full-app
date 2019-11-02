package com.project.Springbootfullapp.controllers;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Springbootfullapp.security.AppUserDetailService;
import com.project.Springbootfullapp.security.AuthenticationRequest;
import com.project.Springbootfullapp.security.AuthenticationResponse;
import com.project.Springbootfullapp.util.AppConstants;
import com.project.Springbootfullapp.util.JWTUtil;
import com.project.Springbootfullapp.util.ResultBean;

/**
 * @author RAJA
 *
 */
@RestController
public class AuthController {
	private static final String module = AuthController.class.getName();

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUserDetailService appUserDetailService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private MessageSource messageSource;

	@GetMapping({ "/helloWorld" })
	public ResultBean helloWorld(Locale locale) {
		return new ResultBean(AppConstants.error,
				messageSource.getMessage("userIdExist.warningMsg", new Object[] { "rraja@gmail.com" }, locale));
	}

	@PostMapping({ "/authenticate" })
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest, Locale locale)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception(messageSource.getMessage("invalidUserOrPass.errorMsg", null, locale), e);
		}

		final UserDetails userDetails = appUserDetailService.loadUserByUsername(authRequest.getUsername());

		String jwtToken = jwtUtil.generateToken(userDetails, new HashMap<String, Object>());
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
