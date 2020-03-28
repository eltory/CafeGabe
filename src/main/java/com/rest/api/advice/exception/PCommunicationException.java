package com.rest.api.advice.exception;

public class PCommunicationException extends RuntimeException {

	public PCommunicationException() {
		super();
	}

	public PCommunicationException(String msg) {
		super(msg);
	}

	public PCommunicationException(String msg, Throwable t) {
		super(msg, t);
	}
}
