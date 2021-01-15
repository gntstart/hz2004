package com;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GbkTOUtf8 {
	public static void main(String argc[]) throws Exception{
		String path = "D:/workspace/hz2004qxgl2/src/com/hzjc/hz2004/vo";
		File file = new File(path);
		File[] fiels = file.listFiles();
		for(File f:fiels){
			if(f.isFile()){
				make(f);
			}else{
				makedir(f);
			}
		}
	}
	
	public static void makedir(File file) throws Exception{
		File[] fiels = file.listFiles();
		for(File f:fiels){
			if(f.isFile()){
				make(f);
			}else{
				makedir(f);
			}
		}
	}
	
	public static void make(File file) throws Exception{
		DataInputStream d = new DataInputStream(new FileInputStream(file));
		byte[] buff = new byte[d.available()];
		d.readFully(buff);
		d.close();
		
		String gbk = new String(buff, "GBK");
		//System.out.println(gbk);
		
		String str = new String(gbk.getBytes(), "UTF-8");
		System.out.println(str);
		//com.hzjc.wsstruts.service.security.AuthToken
		str = str.replaceAll("com.hzjc.wsstruts.service.security.AuthToken", "com.hzjc.hz2004.base.login.AuthToken");
		
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(str.getBytes());
		fout.close();
	}
}
