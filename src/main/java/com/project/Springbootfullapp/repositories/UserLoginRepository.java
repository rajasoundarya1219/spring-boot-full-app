package com.project.Springbootfullapp.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springbootfullapp.entities.UserLogin;

/**
 * @author RAJA
 *
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {

	public List<UserLogin> findByIsActive(Boolean isActive);

	public List<UserLogin> findByFirstName(String firstName);

	public List<UserLogin> findByDateOfBirth(Date dateOfBirth);

	public List<UserLogin> findByLastName(String lastName);

	public UserLogin findByMobileNumber(String mobileNumber);

	public List<UserLogin> findByCreatedTimestamp(Timestamp timestamp);

}
