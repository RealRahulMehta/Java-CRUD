package com.crud.demo.exception;

public class ErrorDetails {
	private int status;
	private String message;
	private String code;

	public ErrorDetails(int status , String message, String code) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
}