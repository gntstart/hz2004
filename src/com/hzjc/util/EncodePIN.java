package com.hzjc.util;

import java.lang.*;
import java.util.*;
import java.io.*;
import com.hzjc.util.I18nParse;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author KGB
 * @version 1.0
 */
public class EncodePIN {

  private String strID;
  private String strPIN;
  private int iPINLen;

  public EncodePIN(){
  }

  public EncodePIN(String strID,String strPIN,int iPINLen){
    this.setID(strID);
    this.setPIN(strPIN);
    this.setPINLength(iPINLen);
  }

  public void setPINLength(int iLen){
    this.iPINLen = iLen;
  }

  public int getPINLength(){
    return this.iPINLen;
  }

  public void setID(String strID){
    this.strID = strID;
  }

  private String getID(){
    return strID;
  }

  public void setPIN(String strPIN){
    this.strPIN = strPIN;
  }

  private String getPIN(){
    return strPIN;
  }

  //**************根据加密的密文得到明文**************
  public String getPWD(){
    int m;
    int n;
    String strCode = this.strID;
    String strPWD = this.strPIN;
    String strGetPIN = "";
    if(strID.length() < this.iPINLen){
      for(int i=0;i<(this.iPINLen-strID.length());i++){
        strCode += " ";
      }
    }
    if(strPIN.length() < this.iPINLen){
      for(int i=0;i<(this.iPINLen-strPIN.length());i++){
        strPWD += " ";
      }
    }
    for(int i=0; i<this.iPINLen; i++){
      m = (strCode.charAt(i) + i) % 7;
      n = (strPWD.charAt(i) + m) % 122;
      if(n<128){
        n += 128;
      }
      strGetPIN += String.valueOf((char) n);
    }
    return I18nParse.parseGBK(strGetPIN);

  }

  public static void main(String[] args)
  {
    EncodePIN sinoPIN = new EncodePIN();
    sinoPIN.setID("02aes");
    sinoPIN.setPIN("02325");
    sinoPIN.setPINLength(20);
    System.out.println(sinoPIN.getID() + "-" + sinoPIN.getPIN());
    System.out.print(sinoPIN.getPWD());
  }
}