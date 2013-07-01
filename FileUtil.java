package com.movisens.xs.android.core.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import org.odk.collect.android.utilities.FileUtils;

public class FileUtil {
	public static int s = 4000;
	public static byte[] Puffer = new byte[s];

	public static void deleteRecursive(File FILEOrDirectory) {
		if (FILEOrDirectory.isDirectory())
			for (File child : FILEOrDirectory.listFiles())
				deleteRecursive(child);

		FILEOrDirectory.delete();
	}

	/**
	 *
	 */
	public static int copy(InputStream input, OutputStream output) {
		int count = 0;
		int n = 0;
		try {
			while (-1 != (n = input.read(Puffer))) {
			output.write(Puffer, 0, n);
			count += n;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void copyFolder(File src, File dest) {
	
		if (src.isDirectory()) {

			// if directory not exists, create it
			if (!dest.exists())
				dest.mkdir();

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
			// construct the src and dest file structure
			File srcFile = new File(src, file);
			File destFile = new File(dest, file);
			// recursive copy
			copyFolder(srcFile,destFile);
			}

		} else {
			// if file, then copy it
			FileUtils.copyFile(src, dest);
			System.out.println("File copied from " + src + " to " + dest);
		}
	}
}
