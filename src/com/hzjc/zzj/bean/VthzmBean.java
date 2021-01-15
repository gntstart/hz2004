package com.hzjc.zzj.bean;

import java.io.Serializable;

public class VthzmBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String    hhnbid;	//第三方记录ID, 用于返回对应
	private String	    ssxq;	//所属辖区
	private String	pcs;	//所属派出所
		private String	xm1; 	//姓名1，不超过40个字节
		private String	gmsfzh1; 	//身份证号1
		private String	yhzgx1; 	//与户主关系1
		private String	xm2; 	//姓名2，不超过40个字节
		private String	gmsfzh2; 	//身份证号2
		private String	yhzgx2; 	//与户主关系2
		
		
		
		public String getHhnbid() {
			return hhnbid;
		}


		public void setHhnbid(String hhnbid) {
			this.hhnbid = hhnbid;
		}


		public String getSsxq() {
			return ssxq;
		}


		public void setSsxq(String ssxq) {
			this.ssxq = ssxq;
		}


		public String getXm1() {
			return xm1;
		}


		public void setXm1(String xm1) {
			this.xm1 = xm1;
		}


		public String getGmsfzh1() {
			return gmsfzh1;
		}


		public void setGmsfzh1(String gmsfzh1) {
			this.gmsfzh1 = gmsfzh1;
		}


		public String getYhzgx1() {
			return yhzgx1;
		}


		public void setYhzgx1(String yhzgx1) {
			this.yhzgx1 = yhzgx1;
		}


		public String getXm2() {
			return xm2;
		}


		public void setXm2(String xm2) {
			this.xm2 = xm2;
		}


		public String getGmsfzh2() {
			return gmsfzh2;
		}


		public void setGmsfzh2(String gmsfzh2) {
			this.gmsfzh2 = gmsfzh2;
		}


		public String getYhzgx2() {
			return yhzgx2;
		}


		public void setYhzgx2(String yhzgx2) {
			this.yhzgx2 = yhzgx2;
		}


		public String getPcs() {
			return pcs;
		}


		public void setPcs(String pcs) {
			this.pcs = pcs;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
}
