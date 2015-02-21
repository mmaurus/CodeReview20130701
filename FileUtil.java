package com.movisens.xs.android.core.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import org.odk.collect.android.utilities.FileUtils;

public class FileUtil {	
	//public Zugriff auf Puffer und s sinnlos
	//s wird sonst nirgends benötigt => kann weggelassen werden
	private static byte[] Puffer = new byte[4000];

	public static void deleteRecursive(File FILEOrDirectory) {
		if (FILEOrDirectory.isDirectory()) {
			for (File child : FILEOrDirectory.listFiles()) {
				deleteRecursive(child);
			}
		}
		FILEOrDirectory.delete();
	}

	/**
	 *
	 */
	public static int copy(InputStream input, OutputStream output) {
		//count und n das gleiche
		int count = 0;
		//int n = 0;
		try {
			count = input.read(Puffer);
			if(count > 0) {
				output.write(Puffer, 0, n);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void copyFolder(File src, File dest) {
	
		if (src.isDirectory()) {

			if(dest.isFile()) {
				//catch the case if src is a directory and dest is a file
				dest = dest.getParent();
			}
		
			// if directory not exists, create it
			if (!dest.exists()) {
				//mkdir only creates the first parent if it doesnt exist while mkdirs creates all missing parents
				dest.mkdirs();
			}

			// list all the directory contents
			for (String file : src.list()) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
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
