package com.cts.exception;

/**
 * 
 * Custom Exception
 *
 */
public class ItemNotFoundException extends RuntimeException{

	public ItemNotFoundException(String mesage) {
        super(mesage);
    }
}
