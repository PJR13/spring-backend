package me.paulojr.spbk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.paulojr.spbk.services.exceptions.DataIntegrityException;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler   {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalDirectionValueException.class)
	public ResponseEntity<StandardError> operationNotAllowed(IllegalDirectionValueException e,
			HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> operationNotAllowed(HttpRequestMethodNotSupportedException e,
			HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.value(), "Metodo HTTP não suportado.",
				System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> operationNotAllowed(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationError sdr = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação.",
				System.currentTimeMillis());
		e.getBindingResult().getFieldErrors().forEach(x -> sdr.getErros().add(new FieldMessage(x.getField(), x.getDefaultMessage())));
		return new ResponseEntity<StandardError>(sdr, HttpStatus.BAD_REQUEST);
	}
	
	
	

	
}
