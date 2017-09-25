package com.example.zhoubiao.cxcourses.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public String readJson(String fileName)
	{
		String path="file:///android_asset/";
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		FileReader reader;
		try {
			reader = new FileReader(new File(path+fileName));
			buffer=new BufferedReader(reader);
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					buffer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			return sb.toString();
	

	}
}
