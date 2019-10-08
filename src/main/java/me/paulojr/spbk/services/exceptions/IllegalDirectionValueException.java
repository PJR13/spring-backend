package me.paulojr.spbk.services.exceptions;

public class IllegalDirectionValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalDirectionValueException(String s) {
		super(s);
	}

	public IllegalDirectionValueException(String s, Throwable t) {
		super(s, t);
	}

}
