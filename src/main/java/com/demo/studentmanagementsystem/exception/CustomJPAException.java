package com.demo.studentmanagementsystem.exception;

public class CustomJPAException extends Exception {

	public CustomJPAException(String message) {
		super(message);
	}
	public CustomJPAException(Throwable cause) {
		super(cause);
	}

}
