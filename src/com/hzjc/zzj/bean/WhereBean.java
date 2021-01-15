package com.hzjc.zzj.bean;

/**
 * 查询条件BEAN
 * @author ting_it
 *
 */
public class WhereBean implements  java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String lkbm;
	private String zzflag;
	private String rzsj;
	private String tfsj;
	public String getLkbm() {
		return lkbm;
	}
	public void setLkbm(String lkbm) {
		this.lkbm = lkbm;
	}
	public String getZzflag() {
		return zzflag;
	}
	public void setZzflag(String zzflag) {
		this.zzflag = zzflag;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getTfsj() {
		return tfsj;
	}
	public void setTfsj(String tfsj) {
		this.tfsj = tfsj;
	}
}
