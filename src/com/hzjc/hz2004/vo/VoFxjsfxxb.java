package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 非现金收款统计信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoFxjsfxxb
    extends DefaultVO {

  private String dwdm; //单位代码 改为受理机构
  private Integer slzl; //受理总量
  private Integer xjje; //现金金额
  private Integer xjbs; //现金笔数
  private Integer fxjje; //非现金金额
  private Integer fxjbs; //非现金笔数
  private Integer hkbysy; //户口簿已使用
  private Integer hkbzf; //户口簿作废
  private Integer yjkje; //应缴款金额
  private Integer sjkje;	//实缴款金额
  private String sffs ;//收费方式
  private String blje;//20210108新增办理金额
  private String zfje;//20210108新增作废金额
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public Integer getSlzl() {
		return slzl;
	}
	public void setSlzl(Integer slzl) {
		this.slzl = slzl;
	}
	public Integer getXjje() {
		return xjje;
	}
	public void setXjje(Integer xjje) {
		this.xjje = xjje;
	}
	public Integer getXjbs() {
		return xjbs;
	}
	public void setXjbs(Integer xjbs) {
		this.xjbs = xjbs;
	}
	public Integer getFxjje() {
		return fxjje;
	}
	public void setFxjje(Integer fxjje) {
		this.fxjje = fxjje;
	}
	public Integer getFxjbs() {
		return fxjbs;
	}
	public void setFxjbs(Integer fxjbs) {
		this.fxjbs = fxjbs;
	}
	public Integer getHkbysy() {
		return hkbysy;
	}
	public void setHkbysy(Integer hkbysy) {
		this.hkbysy = hkbysy;
	}
	public Integer getHkbzf() {
		return hkbzf;
	}
	public void setHkbzf(Integer hkbzf) {
		this.hkbzf = hkbzf;
	}
	public Integer getYjkje() {
		return yjkje;
	}
	public void setYjkje(Integer yjkje) {
		this.yjkje = yjkje;
	}
	public Integer getSjkje() {
		return sjkje;
	}
	public void setSjkje(Integer sjkje) {
		this.sjkje = sjkje;
	}
	public String getSffs() {
		return sffs;
	}
	public void setSffs(String sffs) {
		this.sffs = sffs;
	}
	public String getBlje() {
		return blje;
	}
	public void setBlje(String blje) {
		this.blje = blje;
	}
	public String getZfje() {
		return zfje;
	}
	public void setZfje(String zfje) {
		this.zfje = zfje;
	}
	
}