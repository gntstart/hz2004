package com.hzjc.util;

import java.io.*;

public class ImgUtils {

  private ImgUtils() {
  }

  /**
   *
   * @param fileName
   * @return
   * @throws IOException
   */
  public static byte[] imgParseToBytes(String fileName) throws IOException{
    byte[] aByte = null;
    try {
      java.io.File file = new File(fileName);
      FileInputStream fis = new FileInputStream(file);
      if (fis != null) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1000);
        int j;
        while (( j = fis.read()) != -1){
          bytearrayoutputstream.write(j);
        }
        bytearrayoutputstream.flush();
        aByte = bytearrayoutputstream.toByteArray();
        fis.close();
        bytearrayoutputstream.close();
      }else {
        aByte = new byte[0];
      }
    }
    catch (IOException ex) {
      throw new IOException(ex.toString());
    }
    return aByte;
  }

  /**
   *
   * @return
   */
  public static String getValue(){
    for(int i=0;i<10;i++){
      if(i == 3){
        if(i ==3){
          return String.valueOf(i);
        }
        //break;
      }
      //break;
    }
    return "";
  }

  public static void main(String[] args){
    String aStr = "aas";
    int iCount = 1;
    byte[] aByte = null;
    try {
      aByte = imgParseToBytes("c:/noimg.jpg");
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    System.out.println(aByte[1]);
    //System.out.print(aStr.toString().trim());
    System.out.println(0/2);
    System.out.println(aStr.getClass().getName());
    //System.out.println((String) null);
    //System.out.println(iCount.class.getClass().getName());
    switch(iCount){
      case 1:
      case 0:
        System.out.println("0");
        break;
      default:
        System.out.println("1");
        break;
    }
    long i = 0;
    while(i++<iCount){
      System.out.println(i);
    }
    System.out.println("getvalu:=" + getValue());
    System.out.print((byte) 'C' % 65);
  }


}