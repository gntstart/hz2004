package com.hzjc.zzj.bean;

import java.io.Serializable;

public class FqtkBean implements Serializable{
	 private String    postid;	//--第三方记录ID, 用于返回对应
		private String	lhsfz;	//--落户身份证
		private String	xm;	//--姓名
		private String	gmsfhm;	//--公民身份号码
	    private String	sbrxm;	//--申报人姓名
		private String	sbrsfz;	//--申报人身份证
		private String	sbrlxdh;	//--申报人联系电话
		private String	flag;	//--处理标记0是待处理1是已经处理
		private String	ywlsh;	//--业务流水号
		private String	sbh;	//--业务danw
		private String	pcs;	//--业务danw
		public String getSbrlxdh() {
			return sbrlxdh;
		}
		public void setSbrlxdh(String sbrlxdh) {
			this.sbrlxdh = sbrlxdh;
		}
		public String getSbh() {
			return sbh;
		}
		public void setSbh(String sbh) {
			this.sbh = sbh;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getPcs() {
			return pcs;
		}
		public void setPcs(String pcs) {
			this.pcs = pcs;
		}
		public String getPostid() {
			return postid;
		}
		public String getYwlsh() {
			return ywlsh;
		}
		public void setYwlsh(String ywlsh) {
			this.ywlsh = ywlsh;
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
		public String getGmsfhm() {
			return gmsfhm;
		}
		public void setGmsfhm(String gmsfhm) {
			this.gmsfhm = gmsfhm;
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
}
