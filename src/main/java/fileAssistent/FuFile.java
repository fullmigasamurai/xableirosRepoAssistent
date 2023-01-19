package fileAssistent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FuFile {

	public static boolean isArq (File file){
		return isArq(file.getAbsolutePath());
	}
	public static boolean isArq (String name){
		java.io.BufferedReader reader = null;
		try {
			reader = new java.io.BufferedReader(new java.io.FileReader(name));
			reader.close();

		} catch (FileNotFoundException ex) {
			return false;

		} catch (IOException e) {
			return false;

		} 

		return true;
	}
        
	public static boolean isDir (String dir) {
		return isDir (new File (dir));
	}

	public static boolean isDir (File dir) {
		try {			
			return dir.exists();
		 } catch(Exception e) {
			return false;

		 }
	}

	public static File changeName (File file, String oldName, String newName) {
		if (file.getName().contains(oldName)){
			file = new File(file.getParent()+"/"+file.getName().replace(oldName, newName));
		}
		return file;
	}

	public static File replaceFile (File file, String oldName, String newName){
		if (file.getName().contains(oldName)){
			File replace = new File(file.getParent()+"/"+file.getName().replace(oldName, newName));
			return file.renameTo(replace) ? replace : null;

		} else {
			return file;
		}

	}

	public static File changeName (File file, String newName) {
			file = new File(file.getParent()+"/"+newName);

		return file;
	}

	public static File replaceFile (File file, String newName){
			File replace = new File(file.getParent()+"/"+newName);
			Boolean b = file.renameTo(replace);

			return b ? replace : null;

	}

	public static String readFileToString (File file) {
		return readFileToString(file.getAbsolutePath());
	}

	public static String readFileToString (String file) {
		
		try {

			byte[] bytes = Files.readAllBytes(Paths.get(file));
			return new String (bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	public static Boolean saveFileFromString (String file, String fileContent) {
		try {
			
			Files.write(Paths.get(file), fileContent.getBytes());
			
			return true;			
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static Boolean saveFileFromString (File file, String fileContent) {
		return saveFileFromString(file.getAbsolutePath(), fileContent);
	}

	public static JsonObject readFileJsonObject (File file) {
		if (!isArq(file)) return null;

		String fileString = FuFile.readFileToString(file);
		if (fileString.equalsIgnoreCase("")) return null;
		JsonObject jsonObject = new Gson().fromJson(fileString, JsonObject.class);

		return jsonObject;

	}
	public static JsonObject readFileJsonObject (String file) {
		
		return readFileJsonObject(new File(file));

	}


	
	
}
