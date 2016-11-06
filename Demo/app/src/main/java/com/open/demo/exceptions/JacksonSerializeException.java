package com.open.demo.exceptions;

public class JacksonSerializeException extends RuntimeException {
	private static final long serialVersionUID = 3130986529358944746L;

	public JacksonSerializeException() {
		super();
	}

	public JacksonSerializeException(String message, Throwable cause) {
		super(message, cause);
	}

	public JacksonSerializeException(String message) {
		super(message);
	}

	public JacksonSerializeException(Throwable cause) {
		super(cause);
	}
}
