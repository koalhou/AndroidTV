package com.open.demo.exceptions;

public class JacksonDeserializeException extends RuntimeException {
	private static final long serialVersionUID = -5724732488155350959L;

	public JacksonDeserializeException() {
		super();
	}

	public JacksonDeserializeException(String message, Throwable cause) {
		super(message, cause);
	}

	public JacksonDeserializeException(String message) {
		super(message);
	}

	public JacksonDeserializeException(Throwable cause) {
		super(cause);
	}
}
