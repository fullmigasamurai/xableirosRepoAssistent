/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fileAssistent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


/**
 *
 * @author Toph
 */
public class FileManipulator {

	public FileManipulator (){

	}

	/**
	 * 
	 * @param dir
	 * @return
	 */
	public static File[] listFolder (String dir) {
		return listFolder(new java.io.File(dir));
	}
	/**
	 * 
	 * @param dir
	 * @return
	 */
	public static File[] listFolder (File dir) {
		File[] folderList = dir.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});

		return folderList;

	}

	/**
	 * 
	 * @param dir
	 * @return
	 */
	public static File[] listFiles (File dir){
		return dir.listFiles();
	}

	/**
	 * 
	 * @param originPath
	 * @param destinyPath
	 * @return
	 */
	public static Boolean copyFile (String originPath, String destinyPath) {
		return copyFile(new java.io.File(originPath), new java.io.File(destinyPath));
	}

	/**
	 * 
	 * @param originFile
	 * @param destinyFile
	 * @return
	 */
	public static Boolean copyFile (File originFile, File destinyFile) {

		try {
			if (!originFile.exists()){
                throw new IOException("Arquivo: "+ originFile.getName() +" não encontrado");
            }
			Files.copy(originFile.toPath(), destinyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static ArrayList<String> buffReadFileToArrayList (File file) {
		return buffReadFileToArrayList(file);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static ArrayList<String> buffReadFileToArrayList (String file) {

		if (!FuFile.isArq(file)) {
			return null;
		}
		String nextLine = null;
		ArrayList<String> lines = new ArrayList<String>();
		System.out.println("Readind: " + file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			try {
				while ((nextLine = br.readLine()) != null) {
					lines.add(nextLine);
				}
			}
			catch(IOException e) {
				System.out.print("Error reading file. Try another).\n: ");
				return null;
			}
		}
		catch(FileNotFoundException e) {
			System.out.print("File doesn't exist. Try again).\n: ");
			return null;
		}
		catch(IOException e) {
			System.out.println("Error closing the file.");
			return null;
		}


		return lines;

	}

	/**
	 * 
	 * @param file
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static ArrayList<String> buffReadAndReplaceFileToArrayList (String file, String oldName, String newName) {

		if (!FuFile.isArq(file)) {
			return null;
		}
		String nextLine = null;
		ArrayList<String> lines = new ArrayList<String>();
		System.out.println("Readind: " + file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			try {
				while ((nextLine = br.readLine()) != null) {
					nextLine = nextLine.replaceAll(oldName, newName);
					lines.add(nextLine);
				}
			}
			catch(IOException e) {
				System.out.print("Error reading file. Try another).\n: ");
				return null;
			}
		}
		catch(FileNotFoundException e) {
			System.out.print("File doesn't exist. Try again).\n: ");
			return null;
		}
		catch(IOException e) {
			System.out.println("Error closing the file.");
			return null;
		}


		return lines;

	}

	/**
	 * 
	 * @param file
	 * @param lines
	 */
	public static void buffWriteArrayList (File file , ArrayList<String> lines) {
		buffWriteArrayList(file.getPath(), lines);
	}
	/**
	 * 
	 * @param file
	 * @param lines
	 */
	public static void buffWriteArrayList (String file , ArrayList<String> lines) {

		System.out.println("Writing: " + file + "\n____");
		try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file))) {
			for (String s : lines) {
				buffWriter.write(s);
				buffWriter.newLine();
			}

		} catch (IOException io) {
			System.out.println("Error writing the file.");
		}
	}

	/**
	 * 
	 * @param dir
	 */
	public void delete (File dir){

		File myObj = new File("filename.txt");
		if (myObj.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
	}



}
