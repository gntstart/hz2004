package com.hzjc.zzj.bean;

import java.io.Serializable;

public class VcsdjBean implements Serializable {
	private static final long serialVersionUID = 1L;
		private String    postid;	//第三方记录ID, 用于返回对应
		private String	    lhsfz;	//落户身份证
		private String	xm; 	//姓名，不超过40个字节
		private String	xb;	//性别
		private String	mz;	//民族
		private String	csrq; //出生日期，格式：yyyyMMdd
		private String	csdssxq;	//出生地省市县/区
		private String	cssj;	//出生时间
		private String	csdxz;	//出生地省市县/区
		private String	jgssxq;	//籍贯省市县/区
		private String	hb;	//户别默认和户主一致
		private String	jthzl;	//集体户种类（户别为集体户时必填）
		private String	csdjlb;//出生登记类别
		private String	cszmbh;//出生证明编号
		private String	jhryxm;	//监护人一姓名
		private String	jhrygmsfhm;//监护人一身份号码
		private String	jhrylxdh;		//监护人一联系电话
		private String	jhryjhgx;		//监护人一关系
		private String	jhrexm;		//监护人二姓名
		private String	jhregmsfhm;//监护人二身份号码
		private String	jhrelxdh;		//监护人二联系电话
		private String	jhrejhgx;		//监护人二关系
		private String	fqxm;	//父亲姓名（父亲母亲信息两组必须填一组）
		private String	fqgmsfhm;	//父亲身份号码（父亲母亲信息两组必须填一组）
		private String	mqxm;		//母亲姓名（父亲母亲信息两组必须填一组）
		private String	mqgmsfhm;	//母亲身份号码（父亲母亲信息两组必须填一组）
	private String	    sbrxm;	//申报人姓名 
		private String	sbrsfz;	//申报人身份证
		private String	sbrlxdh;	//申报人身份证
		private String  ywlsh;	//业务流水号
		private String  sbh;	//设备号
		private String  hhnbid; //户号内部ID
	private String yhzgx; //与户主关系

	public String getYhzgx() {
		return yhzgx;
	}

	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
	}

	public String getSbh() {
			return sbh;
		}
		public void setSbh(String sbh) {
			this.sbh = sbh;
		}
		public String getCsdxz() {
			return csdxz;
		}
		public void setCsdxz(String csdxz) {
			this.csdxz = csdxz;
		}
		private String  	pcs;	//业务流水号
		public String getPcs() {
			return pcs;
		}
		public void setPcs(String pcs) {
			this.pcs = pcs;
		}
		public String getYwlsh() {
			return ywlsh;
		}
		public void setYwlsh(String ywlsh) {
			this.ywlsh = ywlsh;
		}
		public String getPostid() {
			return postid;
		}
		public void setPostid(String postid) {
			this.postid = postid;
		}
		public String getLhsfz() {
			return lhsfz;
		}
		public void setLhsfz(String lhsfz) {
			this.lhsfz = lhsfz;
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
		public String getCsrq() {
			return csrq;
		}
		public void setCsrq(String csrq) {
			this.csrq = csrq;
		}
		public String getCsdssxq() {
			return csdssxq;
		}
		public void setCsdssxq(String csdssxq) {
			this.csdssxq = csdssxq;
		}
		public String getCssj() {
			return cssj;
		}
		public void setCssj(String cssj) {
			this.cssj = cssj;
		}
		public String getJgssxq() {
			return jgssxq;
		}
		public void setJgssxq(String jgssxq) {
			this.jgssxq = jgssxq;
		}
		public String getHb() {
			return hb;
		}
		public void setHb(String hb) {
			this.hb = hb;
		}
		public String getJthzl() {
			return jthzl;
		}
		public void setJthzl(String jthzl) {
			this.jthzl = jthzl;
		}
		public String getCsdjlb() {
			return csdjlb;
		}
		public void setCsdjlb(String csdjlb) {
			this.csdjlb = csdjlb;
		}
		public String getCszmbh() {
			return cszmbh;
		}
		public void setCszmbh(String cszmbh) {
			this.cszmbh = cszmbh;
		}
		public String getJhryxm() {
			return jhryxm;
		}
		public void setJhryxm(String jhryxm) {
			this.jhryxm = jhryxm;
		}
		public String getJhrygmsfhm() {
			return jhrygmsfhm;
		}
		public void setJhrygmsfhm(String jhrygmsfhm) {
			this.jhrygmsfhm = jhrygmsfhm;
		}
		public String getJhrylxdh() {
			return jhrylxdh;
		}
		public void setJhrylxdh(String jhrylxdh) {
			this.jhrylxdh = jhrylxdh;
		}
		public String getJhryjhgx() {
			return jhryjhgx;
		}
		public void setJhryjhgx(String jhryjhgx) {
			this.jhryjhgx = jhryjhgx;
		}
		public String getJhrexm() {
			return jhrexm;
		}
		public void setJhrexm(String jhrexm) {
			this.jhrexm = jhrexm;
		}
		public String getJhregmsfhm() {
			return jhregmsfhm;
		}
		public void setJhregmsfhm(String jhregmsfhm) {
			this.jhregmsfhm = jhregmsfhm;
		}
		public String getJhrelxdh() {
			return jhrelxdh;
		}
		public void setJhrelxdh(String jhrelxdh) {
			this.jhrelxdh = jhrelxdh;
		}
		public String getJhrejhgx() {
			return jhrejhgx;
		}
		public void setJhrejhgx(String jhrejhgx) {
			this.jhrejhgx = jhrejhgx;
		}
		public String getFqxm() {
			return fqxm;
		}
		public void setFqxm(String fqxm) {
			this.fqxm = fqxm;
		}
		public String getFqgmsfhm() {
			return fqgmsfhm;
		}
		public void setFqgmsfhm(String fqgmsfhm) {
			this.fqgmsfhm = fqgmsfhm;
		}
		public String getMqxm() {
			return mqxm;
		}
		public void setMqxm(String mqxm) {
			this.mqxm = mqxm;
		}
		public String getMqgmsfhm() {
			return mqgmsfhm;
		}
		public void setMqgmsfhm(String mqgmsfhm) {
			this.mqgmsfhm = mqgmsfhm;
		}
		public String getSbrxm() {
			return sbrxm;
		}
		public void setSbrxm(String sbrxm) {
			this.sbrxm = sbrxm;
		}
		public String getSbrsfz() {
			return sbrsfz;
		}
		public void setSbrsfz(String sbrsfz) {
			this.sbrsfz = sbrsfz;
		}
	public String getHhnbid() {
		return hhnbid;
	}

	public void setHhnbid(String hhnbid) {
		this.hhnbid = hhnbid;
	}
		public String getSbrlxdh() {
			return sbrlxdh;
		}
		public void setSbrlxdh(String sbrlxdh) {
			this.sbrlxdh = sbrlxdh;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
}
