package com.open.demo.exceptions;

public class JacksonEncoderException extends Exception {
	private static final long serialVersionUID = -1899319486368694211L;

	public JacksonEncoderException() {
		super();
	}

	public JacksonEncoderException(String message, Throwable cause) {
		super(message, cause);
	}

	public JacksonEncoderException(String message) {
		super(message);
	}

	public JacksonEncoderException(Throwable cause) {
		super(cause);
	}
}
