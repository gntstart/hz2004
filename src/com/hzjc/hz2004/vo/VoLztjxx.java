package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoLztjxx  extends DefaultVO{
   private String dwxx;
   private Long wdy;//未打印
   private Long ydy;//已打印
   private Long zf;//作废
   private Long num;//合计

public String getDwxx() {
	return dwxx;
}
public void setDwxx(String dwxx) {
	this.dwxx = dwxx;
}
public Long getWdy() {
	return wdy;
}
public void setWdy(Long wdy) {
	this.wdy = wdy;
}
public Long getYdy() {
	return ydy;
}
public void setYdy(Long ydy) {
	this.ydy = ydy;
}
public Long getZf() {
	return zf;
}
public void setZf(Long zf) {
	this.zf = zf;
}
public Long getNum() {
	return num;
}
public void setNum(Long num) {
	this.num = num;
}
   
}
