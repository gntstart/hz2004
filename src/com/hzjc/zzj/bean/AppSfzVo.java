package com.hzjc.zzj.bean;

import java.io.Serializable;
import java.util.Date;

/**
*人像比对信息
*/
public class AppSfzVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	*主键
	*/
	private String	id;
	/*
	*接口信息
	*/
	private String	xm;
	private String	xb;
	private String	mz;
	private String	sfzh;
	private String	csrq;
	private String	xbm;
	private String	mzm;
	private String	ssqh;
	private String	zjlx;
	private String	zjlxm;

	  private String dz;
		private byte[]	zp;
		/*
		*比对时间
		*/
		private Date	sbsj;

		private String	lgbm;
    private byte[]	hzpic;
    private String	flag;
    private String	ywm;
    public byte[] getHzpic() {
		return hzpic;
	}




	public void setHzpic(byte[] hzpic) {
		this.hzpic = hzpic;
	}




	public String getFlag() {
		return flag;
	}




	public void setFlag(String flag) {
		this.flag = flag;
	}




	public String getYwm() {
		return ywm;
	}




	public void setYwm(String ywm) {
		this.ywm = ywm;
	}




	public String getYwx() {
		return ywx;
	}




	public void setYwx(String ywx) {
		this.ywx = ywx;
	}




	public String getYxq() {
		return yxq;
	}




	public void setYxq(String yxq) {
		this.yxq = yxq;
	}




	public String getGj() {
		return gj;
	}




	public void setGj(String gj) {
		this.gj = gj;
	}




	public String getGjm() {
		return gjm;
	}




	public void setGjm(String gjm) {
		this.gjm = gjm;
	}




	public String getQfd() {
		return qfd;
	}




	public void setQfd(String qfd) {
		this.qfd = qfd;
	}




	public String getQfsj() {
		return qfsj;
	}




	public void setQfsj(String qfsj) {
		this.qfsj = qfsj;
	}




	private String	ywx;
    private String	yxq;
    private String	gj;
    private String	gjm;
    private String	qfd;
    private String	qfsj;
	
	public String getXbm() {
		return xbm;
	}




	public void setXbm(String xbm) {
		this.xbm = xbm;
	}




	public String getMzm() {
		return mzm;
	}




	public void setMzm(String mzm) {
		this.mzm = mzm;
	}




	public String getSsqh() {
		return ssqh;
	}




	public void setSsqh(String ssqh) {
		this.ssqh = ssqh;
	}




	public String getZjlx() {
		return zjlx;
	}




	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}




	public String getZjlxm() {
		return zjlxm;
	}




	public void setZjlxm(String zjlxm) {
		this.zjlxm = zjlxm;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getXm() {
		return xm;
	}




	public void setXm(String xm) {
		this.xm = xm;
	}




	public String getXb() {
		return xb;
	}




	public void setXb(String xb) {
		this.xb = xb;
	}




	public String getMz() {
		return mz;
	}




	public void setMz(String mz) {
		this.mz = mz;
	}




	public byte[] getZp() {
		return zp;
	}




	public void setZp(byte[] zp) {
		this.zp = zp;
	}




	public Date getSbsj() {
		return sbsj;
	}




	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}






	
	

	public AppSfzVo(){}


	

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	

	public String getSfzh() {
		return sfzh;
	}




	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}




	public String getCsrq() {
		return csrq;
	}




	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}




	public String getDz() {
		return dz;
	}




	public void setDz(String dz) {
		this.dz = dz;
	}




	public String getLgbm() {
		return lgbm;
	}




	public void setLgbm(String lgbm) {
		this.lgbm = lgbm;
	}




	public void setLayerOooo(String layerOoooo){
	}
}