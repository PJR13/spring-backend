package me.paulojr.spbk.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String s) {
		super(s);
	}

	public DataIntegrityException(String s, Throwable t) {
		super(s, t);
	}

}
