package com.exception.file_management_system;

public class FileNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileNotExistsException() {
		super();
	}

	public FileNotExistsException(final String message) {
		super(message);
	}
	

}
