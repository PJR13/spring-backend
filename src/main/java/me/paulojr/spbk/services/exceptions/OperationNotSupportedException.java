package me.paulojr.spbk.services.exceptions;

public class OperationNotSupportedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public OperationNotSupportedException(String s) {
		super(s);
	}
	public OperationNotSupportedException(String s, Throwable t) {
		super(s, t);
	}

}


