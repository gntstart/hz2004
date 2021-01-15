package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zzj_Vfqtk" )
public class Fqtk implements com.hzjc.wsstruts.po.PO{
private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	
	 private String    postid;	//--第三方记录ID, 用于返回对应
		private String	lhsfz;	//--落户身份证
		private String	xm;	//--姓名
		private String	gmsfhm;	//--公民身份号码
	    private String	sbrxm;	//--申报人姓名
		private String	sbrsfz;	//--申报人身份证
		private String	sbrlxdh;	//--申报人联系电话
		private String	flag;	//--处理标记0是待处理1是已经处理
		private String	cjsj;	//--创建时间
		private String	gxsj;	//--创建时间
		private String	ywlsh;	//--业务流水号
		private String	pcs;	//--业务流水号
		public String getSbrlxdh() {
			return sbrlxdh;
		}
		public void setSbrlxdh(String sbrlxdh) {
			this.sbrlxdh = sbrlxdh;
		}
		public String getGxsj() {
			return gxsj;
		}
		public void setGxsj(String gxsj) {
			this.gxsj = gxsj;
		}
		public String getSbh() {
			return sbh;
		}
		public void setSbh(String sbh) {
			this.sbh = sbh;
		}
		public String getQrbz() {
			return qrbz;
		}
		public void setQrbz(String qrbz) {
			this.qrbz = qrbz;
		}
		public void setCjsj(String cjsj) {
			this.cjsj = cjsj;
		}

		private String	sbh;	//--设备号
		private String	qrbz;	//--申报人身份证
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
		
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
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
