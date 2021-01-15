package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/*
 * 直系亲属关系Vo对象，映射用
 */

public class VoFh
    extends DefaultVO {
	private String xm; 
  	private String gmsfhm; 
  	private String isfh;
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getGmsfhm() {
		return gmsfhm;
	}
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	public String getIsfh() {
		return isfh;
	}
	public void setIsfh(String isfh) {
		this.isfh = isfh;
	} 
	
}
