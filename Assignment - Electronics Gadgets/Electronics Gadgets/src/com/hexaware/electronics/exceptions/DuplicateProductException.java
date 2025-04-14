package com.hexaware.electronics.exceptions; // Adjust the package name as needed

public class DuplicateProductException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateProductException() {
        super();
    }

    public DuplicateProductException(String message) {
        super(message);
    }

    public DuplicateProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateProductException(Throwable cause) {
        super(cause);
    }
}