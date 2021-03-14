package com.oscerba.bsc.exception;

/**
 * Class representing exception thrown, when input does not match requirements.
 */
public class InvalidInputFormatException extends Exception {
	public InvalidInputFormatException() {
		super();
	}

	public InvalidInputFormatException(String message) {
		super(message);
	}

	public InvalidInputFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputFormatException(Throwable cause) {
		super(cause);
	}
}
