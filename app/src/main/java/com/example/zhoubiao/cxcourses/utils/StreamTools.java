package com.example.zhoubiao.cxcourses.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * ������
 * @author Administrator
 *
 */
public class StreamTools {
	/**
	 * ����ת�����ַ�
	 */
	public static String readInputStream(InputStream is)
	{
		
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				baos.write(buffer,0,len);
			}
			is.close();
			baos.close();
			byte[] result=baos.toByteArray();
			String temp=new String(result);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "��ȡʧ��";
		}
	}
	

}

