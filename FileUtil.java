package com.movisens.xs.android.core.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import org.odk.collect.android.utilities.FileUtils;

public class FileUtil {	
	//public Zugriff auf Puffer und s sinnlos
	//s wird sonst nirgends benötigt => kann weggelassen werden
	private static byte[] buffer = new byte[4000]; //english naming and lowercase

	//english naming and lowercase for FILEOrDirectory
	public static void deleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory()) {
			for (File child : fileOrDirectory.listFiles()) {
				deleteRecursive(child);
			}
		}
		fileOrDirectory.delete();
	}

	/**
	 *
	 */
	public static int copy(InputStream input, OutputStream output) {
		//count und n das gleiche
		int count = 0;
		//int n = 0;
		try {
			count = input.read(buffer);
			if(count > -1) { //count is in interval [-1;max_int]
				output.write(buffer, 0, n);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void copyFolder(File src, File dest) {
	
		if (src.isDirectory()) {

			// if directory not exists, create it
			if (!dest.exists()) {
				//mkdir only creates the first parent if it doesnt exist while mkdirs creates all missing parents
				dest.mkdirs();
			}
			if(dest.isFile()) {
				//catch the case if src is a directory and dest is a file
				dest = dest.getParent();
			}

			// list all the directory contents
			for (String filename : src.list()) {
				// construct the src and dest file structure
				File srcFile = new File(src, filename);
				File destFile = new File(dest, filename);
				// recursive copy
				copyFolder(srcFile,destFile);
			}

		} else {
			// if file, then copy it
			FileUtils.copyFile(src, dest);
			System.out.println("File copied from " + src.getPath() + " to " + dest.getPath());
		}
	}
}
