package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/*
 * 公民是否同一人Vo对象，映射用
 */

public class VoGmsftyr
    extends DefaultVO {
  private String gmsfhm1; 
  private String xm1; 
  private String gmsfhm2; 
  private String xm2; 
  private String istyr;
  private String rynbid;
  
public String getXm1() {
	return xm1;
}
public void setXm1(String xm1) {
	this.xm1 = xm1;
}
public String getGmsfhm1() {
	return gmsfhm1;
}
public void setGmsfhm1(String gmsfhm1) {
	this.gmsfhm1 = gmsfhm1;
}
public String getXm2() {
	return xm2;
}
public void setXm2(String xm2) {
	this.xm2 = xm2;
}
public String getGmsfhm2() {
	return gmsfhm2;
}
public void setGmsfhm2(String gmsfhm2) {
	this.gmsfhm2 = gmsfhm2;
}
public String getIstyr() {
	return istyr;
}
public void setIstyr(String istyr) {
	this.istyr = istyr;
}
public String getRynbid() {
	return rynbid;
}
public void setRynbid(String rynbid) {
	this.rynbid = rynbid;
}

}
