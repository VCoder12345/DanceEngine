package com.danceEngine.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

public class Prefs {
	private static HashMap<String, String> saveMap = new HashMap<>();
	private static final String filePath = "res/preferences.save";
	
	public static void setInt(int x, String name) {
		setString(String.valueOf(x), name);
	}
	
	public static void setFloat(float x, String name) {
		setString(String.valueOf(x), name);
	}
	
	public static void setString(String str, String name) {
		saveMap.put(name, str);
	}
	
	public static String getString(String name) {
		return saveMap.get(name);
	}
	
	public static int getInt(String name) {
		return Integer.parseInt(getString(name));
	}
	
	public static float getFloat(String name) {
		return Float.parseFloat(getString(name));
	}
	
	public static boolean isSet(String name) {
		return saveMap.containsKey(name);
	}
	
	public static void flush() {
		StringBuffer buf = new StringBuffer();
		for(Entry entry : saveMap.entrySet()) {
			buf.append(entry.getKey() + ":"  + entry.getValue() + "\n");
		}
		
		FileHelper.write(filePath, buf.toString());
	}
	
	public static void load() {
		try {
			File file = new File(filePath);
			if(file.exists()) {
				for(String line : Files.readAllLines(Paths.get(filePath))) {
					String[] parts = line.split(":");
					String key = parts[0];
					String value = parts[1];
					
					saveMap.put(key, value);
				}
			}else {
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
