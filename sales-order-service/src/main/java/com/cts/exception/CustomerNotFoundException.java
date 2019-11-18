package com.cts.exception;

/**
 * 
 * Custom Exception
 *
 */
public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String mesage) {
        super(mesage);
    }
}
