package com.danceEngine.file;

import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	public static void write(String filePath, String text) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
