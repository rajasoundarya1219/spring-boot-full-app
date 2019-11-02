package com.project.Springbootfullapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RAJA
 *
 */

public class ResultBean {

	private String result;
	private String message;
	private Map<String, String> errorMessages = new HashMap<String, String>();

	/**
	 * @param result
	 */
	public ResultBean(String result) {
		super();
		this.result = result;
	}

	/**
	 * @param result
	 * @param message
	 */
	public ResultBean(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	/**
	 * @param result
	 * @param message
	 * @param errorMessages
	 */
	public ResultBean(String result, String message, Map<String, String> errorMessages) {
		super();
		this.result = result;
		this.message = message;
		this.errorMessages = errorMessages;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the errorMessages
	 */
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
