package com.action.file_management_system;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import com.constants.file_management_system.FileManagementSystemConstants;
import com.exception.file_management_system.FileAlreadyExistsException;
import com.exception.file_management_system.FileNotExistsException;
import com.manager.file_management_system.CurrentDateTimeImpl;
import com.manager.file_management_system.CurrentDateTimeInterface;
import com.manager.file_management_system.FileManagementSystemImpl;
import com.manager.file_management_system.FileManagementSystemInterface;

/**
 * @author dharanidharan
 * @date   23-09-2020
 *
 */
public class FileManagementSystemMain {

    public static void main(String[] args) {

        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        CurrentDateTimeInterface currentDateTimeInterface = new CurrentDateTimeImpl();
        FileManagementSystemInterface fileManagementSystemInterface = new FileManagementSystemImpl();

        System.out.println("*****Welcome to " + FileManagementSystemConstants.PROJECT_NAME + " Menu driven code*****");
        System.out.println("Author :- " + FileManagementSystemConstants.AUTHOR_NAME);
        System.out.println("Version:- " + FileManagementSystemConstants.VERSION_NO);
        System.out.println("Logged In Date & Time:- " + currentDateTimeInterface.currentDateTime());

        System.out.println("");
        System.out.println("\n*****Kindly look the options and press valid key*****");
        System.out.println("Press 1 to perform Menu 1 :- " + FileManagementSystemConstants.MENU1);
        System.out.println("Press 2 to perform Menu 2 :- " + FileManagementSystemConstants.MENU2);
        System.out.println("Press 3 to perform Menu 3 :- " + FileManagementSystemConstants.MENU3);

        do {
            System.out.println("");
            System.out.println("Enter the value to play with FMS:- ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException numberFormatException) {

            }
            switch (choice) {
                case 1:
                    System.out.println("");
                    System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU1);
                    System.out.println("");
                    try {
                        Set < String > fileFromRootDirectory = fileManagementSystemInterface.getFilesFromRootDirectory();
                        if (fileFromRootDirectory.size() == 0) {
                            System.out.println("*****No files found in the Root Directory! Kindly add the files*****");
                        } else {
                            System.out.println("*****Files are arranged in Alphabetical Ascending Order*****");
                            for (String fileNames: fileFromRootDirectory) {
                                System.out.println(fileNames);
                            }
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        System.out.println(fileNotFoundException.getMessage());
                    }
                    choice = 0;
                    break;

                case 2:
                    int internalChoice = 0;
                    String fileName;
                    System.out.println("");
                    System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU2);
                    System.out.println("");
                    System.out.println("\n*****Kindly look the options and press valid key*****");
                    System.out.println("Press 4 to perform Menu 4 :- " + FileManagementSystemConstants.MENU4);
                    System.out.println("Press 5 to perform Menu 5 :- " + FileManagementSystemConstants.MENU5);
                    System.out.println("Press 6 to perform Menu 6 :- " + FileManagementSystemConstants.MENU6);
                    System.out.println("Press 7 to perform Menu 7 :- " + FileManagementSystemConstants.MENU7);
                    do {
                        System.out.println("");
                        System.out.println("Enter the value to perform " + FileManagementSystemConstants.MENU2);
                        try {
                            internalChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException numberFormatException) {

                        }

                        switch (internalChoice) {
                            case 4:
                                System.out.println("");
                                System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU4);
                                System.out.println("Enter the file name alone, Application automatically creates a new file in TXT format :- ");
                                try {
                                    fileName = scanner.nextLine();
                                    boolean fileNameCheck = fileManagementSystemInterface.userInputFileNameCheckValidation(fileName);
                                    if (fileNameCheck) {
                                        try {
                                            boolean fileCreatedFlag = fileManagementSystemInterface.addFileToRootDirectory(fileName);
                                            if (fileCreatedFlag) {
                                                System.out.println("File Created Successfully!");
                                            }
                                        } catch (FileAlreadyExistsException fileAlreadyExistsException) {
                                            System.out.println(fileAlreadyExistsException.getMessage());
                                        }
                                    } else {
                                        System.out.println("*****Invalid Filename! Kindly enter the AlphaNumeric Characters for file name*****");
                                    }

                                } catch (Exception exception) {
                                    System.out.println("");
                                    System.out.println(exception.getMessage());
                                }
                                internalChoice = 0;
                                break;

                            case 5:
                                System.out.println("");
                                System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU5);
                                System.out.println("Enter the file name alone, Application automatically deletes the file from the root directory :- ");
                                try {
                                    fileName = scanner.nextLine();
                                    Set < String > fileFromRootDirectory = fileManagementSystemInterface.getFilesFromRootDirectory();
                                    if (fileFromRootDirectory.size() == 0) {
                                        System.out.println("*****No files found in the Root Directory! Kindly add the files*****");
                                    } else {
                                        try {
                                            boolean fileRemoveFlag = fileManagementSystemInterface.removeFileFromRootDirectory(fileFromRootDirectory, fileName);
                                            if (fileRemoveFlag) {
                                                System.out.println("File removed Successfully!");
                                            }
                                        } catch (FileNotExistsException fileNotExistsException) {
                                            System.out.println(fileNotExistsException.getMessage());
                                        }
                                    }
                                } catch (Exception exception) {
                                    System.out.println(exception.getMessage());
                                }
                                internalChoice = 0;
                                break;
                            case 6:
                                System.out.println("");
                                System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU6);
                                System.out.println("Enter the file name pattern to search from the root directory :- ");
                                try {
                                    fileName = scanner.nextLine();
                                    Set < String > filesFromRootDirectory = fileManagementSystemInterface.getFilesFromRootDirectory();
                                    if (filesFromRootDirectory.size() == 0) {
                                        System.out.println("*****No files found in the Root Directory! Kindly add the files*****");
                                    } else {
                                    	Set<String> searchedFileNames = fileManagementSystemInterface.getSearchedFilesNamesFromRootDirectory(filesFromRootDirectory, fileName);
                                        if (searchedFileNames.size() == 0) {
                                        	System.out.println("*****No files found for the search pattern from Root Directory! Kindly add the files and try again*****");
                                        }else {
                                        	for(String s : searchedFileNames) {
                                        		System.out.println(s.toString());
                                        	}
                                        }
                                    }
                                }catch(Exception exception) {
                                    System.out.println(exception.getMessage());
                                }
                                internalChoice = 0;
                                break;
                            case 7:
                                System.out.println("You Selected to perform " +FileManagementSystemConstants.MENU7);
                                break;
                            default:
                                System.out.println("**** Enter input value is Wrong! Kindly check *****");
                                break;
                        }


                    } while (internalChoice != 7);

                    choice = 0;
                    break;

                case 3:
                    System.out.println("");
                    System.out.println("You Selected to perform " + FileManagementSystemConstants.MENU3);
                    System.out.println("bye bye! Thankyou for using our FMS");
                    System.out.println("Logged out Date & Time:- " + currentDateTimeInterface.currentDateTime());
                    break;

                default:
                    System.out.println("");
                    System.out.println("**** Enter input value is Wrong! Kindly check *****");
                    break;

            }

        } while (choice != 3);

    }

}