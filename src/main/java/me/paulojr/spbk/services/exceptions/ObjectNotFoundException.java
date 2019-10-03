package me.paulojr.spbk.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String s) {
		super(s);
	}
	public ObjectNotFoundException(String s, Throwable t) {
		super(s, t);
	}

}
