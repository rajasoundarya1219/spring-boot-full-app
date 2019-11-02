package com.project.Springbootfullapp.util;

/**
 * @author RAJA
 *
 */

public class ResultBean {

	private String result;
	private String message;

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

}
