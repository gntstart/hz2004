package com.hzjc.zzj.bean;

/**
 * API使用的旅客内宾BEAN对象
 * @author ting_it
 *
 */
public class NbBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String  postid;	//第三方记录ID, 用于返回对应
    private String	lkbm;	//住宿流水号（用于查询，或者用于修改住宿记录）
	private String	xm; 	//姓名，不超过40个字节
	private String	xb;	//性别，字典名称：xb
	private String	mz;	//民族，字典名称：mz
	private String	csrq; //出生日期，格式：yyyyMMdd
	private String	zjlx;	//证件类型，字典名称：zjlx
	private String	zjhm;//证件号码
	private String	ssxq;	//所属县/区：字典：xzqh
	private String	xz;	//详细地址
	private String	rzsj;	//入住时间，格式：yyyyMMddHHmm
	private int 		rzlc;	//入住楼层
	private String	rzfh;	//入住房号
	private String	tfsj;	//退房时间，格式：yyyyMMddHHmm
	private String	xyklx;	//信用卡类型
	private String	xykhm;	//行用卡号码
	private String	zy;		//职业，字典名称：zy
	private String	hcl;		//何处来，字典：xzqh
	private String	hcq;		//何处去，字典：xzqh
	private String	lcsy;		//来此事由，字典：lcsy
	private String	djr;		//登记人
	private String	bz;		//备注
	private String	fzqx;		//发证区县，字典：xzqh
	private String	sfsjdx;	//特殊上报类型，保留
	private String	cphm;	//车牌号码（不含首字母）
	private String	cpsz;		//车牌号码首字，字典：cpsz
	private String	lxdh;		//联系电话
	private String	picture;	//旅客照片，不超过50K，BASE64编码
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
	public String getSsxq() {
		return ssxq;
	}
	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
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
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
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
	public String getFzqx() {
		return fzqx;
	}
	public void setFzqx(String fzqx) {
		this.fzqx = fzqx;
	}
	public String getSfsjdx() {
		return sfsjdx;
	}
	public void setSfsjdx(String sfsjdx) {
		this.sfsjdx = sfsjdx;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
