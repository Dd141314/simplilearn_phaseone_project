package com.exception.file_management_system;

public class FileAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileAlreadyExistsException() {
		super();
	}

	public FileAlreadyExistsException(final String message) {
		super(message);
	}
	

}
