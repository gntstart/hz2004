package com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ReplaceJava {
	public static void main(String argc[]) throws Exception{
		String path = "D:/workspace/hz2004/src/com/hzjc/hz2004/service/impl";
		File file = new File(path);
		File[] fiels = file.listFiles();
		if(fiels!=null){
			for(File f:fiels){
				if(f.isFile()){
					make(f);
				}else{
					makedir(f);
				}
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
		
		String str = new String(buff);
		str = str.replaceAll("getPageoffset", "getPageindex");
		System.out.println(str);
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(str.getBytes());
		fout.close();
	}
}
