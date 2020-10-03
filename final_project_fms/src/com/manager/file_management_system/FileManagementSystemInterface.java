package com.manager.file_management_system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import com.exception.file_management_system.FileAlreadyExistsException;
import com.exception.file_management_system.FileNotExistsException;

public interface FileManagementSystemInterface {

	public boolean checkDirectoryPresentIfNotCreate() throws FileNotFoundException;
	
	public Set<String> getFilesFromRootDirectory() throws FileNotFoundException;
	
	public boolean addFileToRootDirectory(String fileName) throws IOException,FileAlreadyExistsException;
	
	public boolean userInputFileNameCheckValidation(String fileName);
	
	public boolean removeFileFromRootDirectory(Set<String> fileNames , String fileName) throws FileNotFoundException,FileNotExistsException;
	
	public Set<String> getSearchedFilesNamesFromRootDirectory(Set<String> fileNames , String fileName)throws FileNotFoundException;
}
