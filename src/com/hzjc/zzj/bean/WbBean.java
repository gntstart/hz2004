package com.hzjc.zzj.bean;

public class WbBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
    private String    postid;	//第三方记录ID, 用于返回对应
    private String	lkbm;	//住宿流水号（仅用于查询，提交为空）
	private String	zwm;	//中文名
	private String	ywx;		//英文姓
	private String	ywm;	//英文名
	private String	xb;		//性别，字典名称：xb
	private String	gj;			//国籍，字典名称：gj
	private String	csrq;		//出生日期，格式：yyyyMMdd
	private String	zjlx;		//证据类型，字典名称：zjlx1
	private String	zjhm;	//证件号码
	private String	qzzl;		//签证种类，字典名称：qzzl
	private String	qzhm;   //签证号码
	private String	zhtlqz;	//停留期至，格式：yyyyMMdd
	private String	qfjg;		//签证签发机关，字典名称：qfjg
	private String	rjrq;		//入籍日期，格式：yyyyMMdd
	private String	rjka;		//入境口岸，字典名称：rjka
	private String	jddw;	//接待单位
	private String	rzsj;		//入住时间，格式：yyyyMMddHHmm
	private int 		rzlc;		//入住楼层
	private String	rzfh;		//入住房号
	private String	tfsj;		//退房时间，格式：yyyyMMddHHmm
	private String	xyklx;	//信用卡类型
	private String	xykhm;	//信用卡号码
	private String	hcl;		//何处来，字典：gj/xzqh之一
	private String	hcq;		//何处去，字典：gj/xzqh之一
	private String	lcsy;		//来此事由，字典：lcsy1
	private String	jdr;		//接待人
	private String	djr;		//登记人
	private String	bz;		//备注
	private String	cphm;	//车牌号码（不含首字母）
	private String	cpsz;		//车牌号码首字，字典：cpsz
	private String	lxdh;		//联系电话
	private String	picture1;	//旅客照片，不超过50K，BASE64编码
	private String	picture2;	//旅客护照，不超过50K，BASE64编码
    private String	lg;	//旅客照片，不超过50K，BASE64编码
	
	public String getLg() {
		return lg;
	}
	public void setLg(String lg) {
		this.lg = lg;
	}
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
	public String getZwm() {
		return zwm;
	}
	public void setZwm(String zwm) {
		this.zwm = zwm;
	}
	public String getYwx() {
		return ywx;
	}
	public void setYwx(String ywx) {
		this.ywx = ywx;
	}
	public String getYwm() {
		return ywm;
	}
	public void setYwm(String ywm) {
		this.ywm = ywm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getQzzl() {
		return qzzl;
	}
	public void setQzzl(String qzzl) {
		this.qzzl = qzzl;
	}
	public String getQzhm() {
		return qzhm;
	}
	public void setQzhm(String qzhm) {
		this.qzhm = qzhm;
	}
	public String getZhtlqz() {
		return zhtlqz;
	}
	public void setZhtlqz(String zhtlqz) {
		this.zhtlqz = zhtlqz;
	}
	public String getQfjg() {
		return qfjg;
	}
	public void setQfjg(String qfjg) {
		this.qfjg = qfjg;
	}
	public String getRjrq() {
		return rjrq;
	}
	public void setRjrq(String rjrq) {
		this.rjrq = rjrq;
	}
	public String getRjka() {
		return rjka;
	}
	public void setRjka(String rjka) {
		this.rjka = rjka;
	}
	public String getJddw() {
		return jddw;
	}
	public void setJddw(String jddw) {
		this.jddw = jddw;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public int getRzlc() {
		return rzlc;
	}
	public void setRzlc(int rzlc) {
		this.rzlc = rzlc;
	}
	public String getRzfh() {
		return rzfh;
	}
	public void setRzfh(String rzfh) {
		this.rzfh = rzfh;
	}
	public String getTfsj() {
		return tfsj;
	}
	public void setTfsj(String tfsj) {
		this.tfsj = tfsj;
	}
	public String getXyklx() {
		return xyklx;
	}
	public void setXyklx(String xyklx) {
		this.xyklx = xyklx;
	}
	public String getXykhm() {
		return xykhm;
	}
	public void setXykhm(String xykhm) {
		this.xykhm = xykhm;
	}
	public String getHcl() {
		return hcl;
	}
	public void setHcl(String hcl) {
		this.hcl = hcl;
	}
	public String getHcq() {
		return hcq;
	}
	public void setHcq(String hcq) {
		this.hcq = hcq;
	}
	public String getLcsy() {
		return lcsy;
	}
	public void setLcsy(String lcsy) {
		this.lcsy = lcsy;
	}
	public String getJdr() {
		return jdr;
	}
	public void setJdr(String jdr) {
		this.jdr = jdr;
	}
	public String getDjr() {
		return djr;
	}
	public void setDjr(String djr) {
		this.djr = djr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCphm() {
		return cphm;
	}
	public void setCphm(String cphm) {
		this.cphm = cphm;
	}
	public String getCpsz() {
		return cpsz;
	}
	public void setCpsz(String cpsz) {
		this.cpsz = cpsz;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
}
