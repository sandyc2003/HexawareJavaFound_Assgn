package com.hexaware.electronics.exceptions;

public class InvalidDataException extends Exception{
    
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
        super(message);
    }
}
