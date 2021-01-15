package com.hzjc.hz2004.vo;

import javax.persistence.Id;

import com.hzjc.wsstruts.vo.DefaultVO;

/*
 * 直系亲属关系Vo对象，映射用
 */

public class VoZxqsgx
    extends DefaultVO {
	@Id
  private Long	zxqsgxid;
  private String xm1; 
  private String xb1; 
  private String csrq1; 
  private String gmsfhm1; 
  private String xm2; 
  private String xb2; 
  private String csrq2; 
  private String gmsfhm2; 
  private String zxqsgx; 
  private String pcs;
  private String rynbid;
  
public Long getZxqsgxid() {
	return zxqsgxid;
}
public void setZxqsgxid(Long zxqsgxid) {
	this.zxqsgxid = zxqsgxid;
}
public String getXm1() {
	return xm1;
}
public void setXm1(String xm1) {
	this.xm1 = xm1;
}
public String getXb1() {
	return xb1;
}
public void setXb1(String xb1) {
	this.xb1 = xb1;
}
public String getCsrq1() {
	return csrq1;
}
public void setCsrq1(String csrq1) {
	this.csrq1 = csrq1;
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
public String getXb2() {
	return xb2;
}
public void setXb2(String xb2) {
	this.xb2 = xb2;
}
public String getCsrq2() {
	return csrq2;
}
public void setCsrq2(String csrq2) {
	this.csrq2 = csrq2;
}
public String getGmsfhm2() {
	return gmsfhm2;
}
public void setGmsfhm2(String gmsfhm2) {
	this.gmsfhm2 = gmsfhm2;
}
public String getZxqsgx() {
	return zxqsgx;
}
public void setZxqsgx(String zxqsgx) {
	this.zxqsgx = zxqsgx;
}
public String getPcs() {
	return pcs;
}
public void setPcs(String pcs) {
	this.pcs = pcs;
}
public String getRynbid() {
	return rynbid;
}
public void setRynbid(String rynbid) {
	this.rynbid = rynbid;
} 
 

}
