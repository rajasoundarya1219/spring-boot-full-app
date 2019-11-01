package com.project.Springbootfullapp.security;

/**
 * @author RAJA
 *
 */
public class AuthenticationResponse {

	private String token;

	/**
	 * @param token
	 */
	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

}
