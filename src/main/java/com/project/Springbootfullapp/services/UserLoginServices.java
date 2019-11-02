package com.project.Springbootfullapp.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Springbootfullapp.entities.UserLogin;
import com.project.Springbootfullapp.repositories.UserLoginRepository;

/**
 * @author RAJA
 *
 */
@Service
public class UserLoginServices {

	@Autowired
	private UserLoginRepository userLoginRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * helps to insert user login value in database
	 *
	 * @param userLoginId
	 * @param password
	 * @return
	 */
	public UserLogin createUserLogin(UserLogin userLogin) {
		if (userLoginRepository.existsById(userLogin.getUserLoginId())) {
			return null;
		}
		userLogin.setPassword(bCryptPasswordEncoder.encode(userLogin.getPassword()));
		userLogin.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		return userLoginRepository.save(userLogin);
	}
}
