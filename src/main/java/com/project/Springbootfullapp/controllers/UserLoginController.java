package com.project.Springbootfullapp.controllers;

import java.util.Locale;
import java.util.logging.Logger;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Springbootfullapp.checks.UserCreationChecks;
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

	private static final Logger logger = Logger.getLogger(UserLoginController.class.getName());

	@Autowired
	private UserLoginServices userLoginServices;

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Autowired
	private MessageSource messageSource;

	@PostMapping(path = "/sign-up")
	public ResponseEntity<?> createUserLogin(@Validated(UserCreationChecks.class) @RequestBody UserLogin userLogin,
			Locale locale) {

		if (userLoginRepository.existsById(userLogin.getUserLoginId())) {
			logger.warning("user id already exist");
			return ResponseEntity.ok(new ResultBean(AppConstants.error, messageSource
					.getMessage("userIdExist.warningMsg", new Object[] { userLogin.getUserLoginId() }, locale)));
		}
		logger.info("user id not exist");
		userLogin = userLoginServices.createUserLogin(userLogin);
		return ResponseEntity.ok(ObjectUtils.isNotEmpty(userLogin)
				? new ResultBean(AppConstants.success,
						messageSource.getMessage("accountCreatedSuccessfully.successMsg", null, locale))
				: new ResultBean(AppConstants.error, messageSource.getMessage("server.errorMsg", null, locale)));
	}

}
