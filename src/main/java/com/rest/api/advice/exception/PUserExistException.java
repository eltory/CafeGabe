package com.rest.api.advice.exception;

public class PUserExistException extends RuntimeException {

	public PUserExistException(String msg, Throwable t) {
		super(msg, t);
	}

	public PUserExistException(String msg) {
		super(msg);
	}

	public PUserExistException() {
		super();
	}
}
