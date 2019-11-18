package com.cts.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * global Exception handling
 *
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(new Date(),"Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(new Date(),ex.getMessage(), details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(new Date(),"Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ItemNotFoundException.class)
	public final ResponseEntity<Object> handleiTEMNotFoundException(ItemNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(new Date(),ex.getMessage(), details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}
	
}
