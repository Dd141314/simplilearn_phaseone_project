package com.manager.file_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.constants.file_management_system.FileManagementSystemConstants;
import com.exception.file_management_system.FileAlreadyExistsException;
import com.exception.file_management_system.FileNotExistsException;

public class FileManagementSystemImpl implements FileManagementSystemInterface{

	@Override
	public boolean checkDirectoryPresentIfNotCreate() throws FileNotFoundException {
		String directoryPath = FileManagementSystemConstants.ROOT_DIRECTORY;
		File file = new File(directoryPath);
		if (!file.exists()) {
		    file.mkdir();
		}
		return true;
	}

	@Override
	public Set<String> getFilesFromRootDirectory() throws FileNotFoundException {
		Set<String> fileNames =  new TreeSet<String> ();
	    try {
	    	boolean checkDirectoryPresent = this.checkDirectoryPresentIfNotCreate();
	    	if(checkDirectoryPresent) {
	    		File directoryPath = new File(FileManagementSystemConstants.ROOT_DIRECTORY);
	    		File[] listOfFiles = directoryPath.listFiles();
	    		
	    		for(File file : listOfFiles) {
	    			if(file.isFile()) {
	    				fileNames.add(file.getName());
	    			}
	    		}
	    	}
	    }catch(FileNotFoundException fileNotFoundException) {
	    	throw new FileNotFoundException(fileNotFoundException.getMessage());
	    }
		
	    return fileNames;
	    
	}

	@Override
	public boolean addFileToRootDirectory(String fileName) throws IOException, FileAlreadyExistsException {
		boolean flagCreated = false;
		try {
			Set<String> fileNamesAlreadyPresentInRootDirectory = this.getFilesFromRootDirectory();
			if(fileNamesAlreadyPresentInRootDirectory.size() > 0) {
				if(fileNamesAlreadyPresentInRootDirectory.contains(fileName+FileManagementSystemConstants.FILE_EXTENSION)) {
					throw new FileAlreadyExistsException("*****File already exists! Kindly input different file name*****");
				}else {
			    	boolean checkDirectoryPresent = this.checkDirectoryPresentIfNotCreate();
			    	if(checkDirectoryPresent) {
						File userInputFile = new File(FileManagementSystemConstants.ROOT_DIRECTORY+"/"+fileName+FileManagementSystemConstants.FILE_EXTENSION);
						flagCreated = userInputFile.createNewFile();
			    	}
				}
			}else {
		    	boolean checkDirectoryPresent = this.checkDirectoryPresentIfNotCreate();
		    	if(checkDirectoryPresent) {
		    		File userInputFile = new File(FileManagementSystemConstants.ROOT_DIRECTORY+"/"+fileName+FileManagementSystemConstants.FILE_EXTENSION);
		    		flagCreated = userInputFile.createNewFile();
		    	}
			}
			
		} catch (FileNotFoundException fileNotFoundException) {
	    	throw new FileNotFoundException(fileNotFoundException.getMessage());
		}
		
		return flagCreated;
	}

	@Override
	public boolean userInputFileNameCheckValidation(String fileName) {
		boolean fileNameInputValidation = Pattern.matches(FileManagementSystemConstants.FILE_NAME_REGEX, fileName);
		if(fileNameInputValidation) {
			return fileNameInputValidation;
		}else {
			return fileNameInputValidation;
		}
	}

	@Override
	public boolean removeFileFromRootDirectory(Set<String> fileNames, String fileName) throws FileNotFoundException,FileNotExistsException {
		boolean flagFileRemoved = false;
    	boolean checkDirectoryPresent = this.checkDirectoryPresentIfNotCreate();
    	if(checkDirectoryPresent) {
    		if(fileNames.contains(fileName+FileManagementSystemConstants.FILE_EXTENSION)) {
        		File userInputFile = new File(FileManagementSystemConstants.ROOT_DIRECTORY+"/"+fileName+FileManagementSystemConstants.FILE_EXTENSION);
        		flagFileRemoved = userInputFile.delete();
    		}else {
    			throw new FileNotExistsException("*****Entered file name not found on the root directory! Kindly Check*****");
    		}
    	}
		return flagFileRemoved;
	}

	@Override
	public Set<String> getSearchedFilesNamesFromRootDirectory(Set<String> fileNames, String fileName) throws FileNotFoundException {
		Set<String> searchedfileNames =  new TreeSet<String> ();
	    try {
	    	boolean checkDirectoryPresent = this.checkDirectoryPresentIfNotCreate();
	    	if(checkDirectoryPresent) {
	    		Pattern pattern = Pattern.compile(Pattern.quote(fileName), Pattern.CASE_INSENSITIVE);
	    		for(String file : fileNames) {
		    		Matcher matcher = pattern.matcher(file);
	    			if(matcher.find()) {
	    				searchedfileNames.add(file);
	    			}
	    		}
	    	}
	    }catch(FileNotFoundException fileNotFoundException) {
	    	throw new FileNotFoundException(fileNotFoundException.getMessage());
	    }
		return searchedfileNames;
	}


}
