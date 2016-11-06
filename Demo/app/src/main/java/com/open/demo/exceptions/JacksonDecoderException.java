package com.open.demo.exceptions;

public class JacksonDecoderException extends Exception {
	private static final long serialVersionUID = -1899319486368694211L;

	public JacksonDecoderException() {
		super();
	}

	public JacksonDecoderException(String message, Throwable cause) {
		super(message, cause);
	}

	public JacksonDecoderException(String message) {
		super(message);
	}

	public JacksonDecoderException(Throwable cause) {
		super(cause);
	}
}