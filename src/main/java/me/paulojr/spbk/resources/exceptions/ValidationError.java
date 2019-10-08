package me.paulojr.spbk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Integer erro, String mensagem, Long timeStamp) {
		super(erro, mensagem, timeStamp);
	}

	public List<FieldMessage> getErros() {
		return list;
	}

	public void setList(List<FieldMessage> list) {
		this.list = list;
	}
	
	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName,message));
	}

	
	
	
}
