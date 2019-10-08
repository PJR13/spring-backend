package me.paulojr.spbk.resources.exceptions;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.paulojr.spbk.services.exceptions.DataIntegrityException;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;
import me.paulojr.spbk.services.exceptions.OperationNotSupportedException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

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

	@ExceptionHandler(OperationNotSupportedException.class)
	public ResponseEntity<StandardError> operationNotAllowed(OperationNotSupportedException e,
			HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(IllegalDirectionValueException.class)
	public ResponseEntity<StandardError> operationNotAllowed(IllegalDirectionValueException e,
			HttpServletRequest request) {
		StandardError sdr = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<StandardError>(sdr, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		StandardError sdr = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.value(), "Metodo HTTP não suportado.", System.currentTimeMillis());
		pageNotFoundLogger.warn(ex.getMessage());
		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		return new ResponseEntity<Object>(sdr, headers,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}
