package com.rest.api.advice.exception;

/**
 * 
 * @author lsh
 *
 */
public class PUserNotFoundException extends RuntimeException {
	public PUserNotFoundException() {
		super();
	}

	public PUserNotFoundException(String msg) {
		super(msg);
	}

	public PUserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
