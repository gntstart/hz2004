package com.hzjc.zzj.bean;

/**
 * 退房Bean，退房时间为当前时间
 */
public class TfBean  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String postid;
	private String	lkbm;	//住宿流水号
	
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getLkbm() {
		return lkbm;
	}
	public void setLkbm(String lkbm) {
		this.lkbm = lkbm;
	}
}
