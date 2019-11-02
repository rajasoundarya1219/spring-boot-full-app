package com.project.Springbootfullapp.controllers;

import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Springbootfullapp.entities.UserLogin;
import com.project.Springbootfullapp.repositories.UserLoginRepository;
import com.project.Springbootfullapp.services.UserLoginServices;
import com.project.Springbootfullapp.util.AppConstants;
import com.project.Springbootfullapp.util.ResultBean;

/**
 * @author RAJA
 *
 */
@RestController
public class UserLoginController {

	@Autowired
	private UserLoginServices userLoginServices;

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Autowired
	private MessageSource messageSource;

	@PostMapping(path = "/sign-up")
	public ResponseEntity<?> createUserLogin(@RequestBody UserLogin userLogin, Locale locale) {
		if (userLoginRepository.existsById(userLogin.getUserLoginId())) {
			return ResponseEntity.ok(new ResultBean(AppConstants.error, messageSource
					.getMessage("userIdExist.warningMsg", new Object[] { userLogin.getUserLoginId() }, locale)));
		}
		userLogin = userLoginServices.createUserLogin(userLogin);
		return ResponseEntity.ok(ObjectUtils.isNotEmpty(userLogin)
				? new ResultBean(AppConstants.success,
						messageSource.getMessage("accountCreatedSuccessfully.successMsg", null, locale))
				: new ResultBean(AppConstants.error, messageSource.getMessage("server.errorMsg", null, locale)));
	}
}
