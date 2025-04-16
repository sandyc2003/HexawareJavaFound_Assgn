package com.hexaware.hms.exception;


public class PatientNumberNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	// Default constructor
    public PatientNumberNotFoundException() {
        super("Patient number not found in the database.");
    }

    // Constructor with custom message
    public PatientNumberNotFoundException(String message) {
        super(message);
    }
}
