package com.rest.api.advice.exception;

public class CommunicationException extends RuntimeException {

	public CommunicationException() {
		super();
	}

	public CommunicationException(String msg) {
		super(msg);
	}

	public CommunicationException(String msg, Throwable t) {
		super(msg, t);
	}
}
