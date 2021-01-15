package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*人员变动信息表
*/
@Entity
@Table(name="HJTJ_RYBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJTJ_RYBDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员变动ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rybdid;
	private Long	ryid;
	private Long	rynbid;
	private Long	mlpnbid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	rylb;
	private String	hb;
	private String	yhzgx;
	private String	hzxm;
	private String	hzgmsfhm;
	private String	ywnr;
	private String	bdqhb;
	private Long	bdbid;
	private String	bdyy;
	private String	bdfw;
	private String	bdrq;
	private Long	bdqhhnbid;
	private String	bdqhh;
	private String	bdqhlx;
	private Long	bdqmlpnbid;
	private String	bdqssxq;
	private String	bdqjlx;
	private String	bdqmlph;
	private String	bdqmlxz;
	private String	bdqpcs;
	private String	bdqzrq;
	private String	bdqxzjd;
	private String	bdqjcwh;
	private Long	bdhhhnbid;
	private String	bdhhh;
	private String	bdhhlx;
	private Long	bdhmlpnbid;
	private String	bdhssxq;
	private String	bdhjlx;
	private String	bdhmlph;
	private String	bdhmlxz;
	private String	bdhpcs;
	private String	bdhzrq;
	private String	bdhxzjd;
	private String	bdhjcwh;
	private Long	hjywid;
	private String	ywbz;
	private String	ywlx;
	private Long	czsm;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	private String	slsj;
	private String	sldw;
	private Long	slrid;
	private Long	rzjs;
	private Long	hzjs;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private Long	mlpid;

	public PoHJTJ_RYBDXXB(){}

	public Long getRybdid(){
		return this.rybdid;
	}

	public void setRybdid(Long rybdid){
		this.rybdid=rybdid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getRylb(){
		return this.rylb;
	}

	public void setRylb(String rylb){
		this.rylb=rylb;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public String getHzxm(){
		return this.hzxm;
	}

	public void setHzxm(String hzxm){
		this.hzxm=hzxm;
	}

	public String getHzgmsfhm(){
		return this.hzgmsfhm;
	}

	public void setHzgmsfhm(String hzgmsfhm){
		this.hzgmsfhm=hzgmsfhm;
	}

	public String getYwnr(){
		return this.ywnr;
	}

	public void setYwnr(String ywnr){
		this.ywnr=ywnr;
	}

	public String getBdqhb(){
		return this.bdqhb;
	}

	public void setBdqhb(String bdqhb){
		this.bdqhb=bdqhb;
	}

	public Long getBdbid(){
		return this.bdbid;
	}

	public void setBdbid(Long bdbid){
		this.bdbid=bdbid;
	}

	public String getBdyy(){
		return this.bdyy;
	}

	public void setBdyy(String bdyy){
		this.bdyy=bdyy;
	}

	public String getBdfw(){
		return this.bdfw;
	}

	public void setBdfw(String bdfw){
		this.bdfw=bdfw;
	}

	public String getBdrq(){
		return this.bdrq;
	}

	public void setBdrq(String bdrq){
		this.bdrq=bdrq;
	}

	public Long getBdqhhnbid(){
		return this.bdqhhnbid;
	}

	public void setBdqhhnbid(Long bdqhhnbid){
		this.bdqhhnbid=bdqhhnbid;
	}

	public String getBdqhh(){
		return this.bdqhh;
	}

	public void setBdqhh(String bdqhh){
		this.bdqhh=bdqhh;
	}

	public String getBdqhlx(){
		return this.bdqhlx;
	}

	public void setBdqhlx(String bdqhlx){
		this.bdqhlx=bdqhlx;
	}

	public Long getBdqmlpnbid(){
		return this.bdqmlpnbid;
	}

	public void setBdqmlpnbid(Long bdqmlpnbid){
		this.bdqmlpnbid=bdqmlpnbid;
	}

	public String getBdqssxq(){
		return this.bdqssxq;
	}

	public void setBdqssxq(String bdqssxq){
		this.bdqssxq=bdqssxq;
	}

	public String getBdqjlx(){
		return this.bdqjlx;
	}

	public void setBdqjlx(String bdqjlx){
		this.bdqjlx=bdqjlx;
	}

	public String getBdqmlph(){
		return this.bdqmlph;
	}

	public void setBdqmlph(String bdqmlph){
		this.bdqmlph=bdqmlph;
	}

	public String getBdqmlxz(){
		return this.bdqmlxz;
	}

	public void setBdqmlxz(String bdqmlxz){
		this.bdqmlxz=bdqmlxz;
	}

	public String getBdqpcs(){
		return this.bdqpcs;
	}

	public void setBdqpcs(String bdqpcs){
		this.bdqpcs=bdqpcs;
	}

	public String getBdqzrq(){
		return this.bdqzrq;
	}

	public void setBdqzrq(String bdqzrq){
		this.bdqzrq=bdqzrq;
	}

	public String getBdqxzjd(){
		return this.bdqxzjd;
	}

	public void setBdqxzjd(String bdqxzjd){
		this.bdqxzjd=bdqxzjd;
	}

	public String getBdqjcwh(){
		return this.bdqjcwh;
	}

	public void setBdqjcwh(String bdqjcwh){
		this.bdqjcwh=bdqjcwh;
	}

	public Long getBdhhhnbid(){
		return this.bdhhhnbid;
	}

	public void setBdhhhnbid(Long bdhhhnbid){
		this.bdhhhnbid=bdhhhnbid;
	}

	public String getBdhhh(){
		return this.bdhhh;
	}

	public void setBdhhh(String bdhhh){
		this.bdhhh=bdhhh;
	}

	public String getBdhhlx(){
		return this.bdhhlx;
	}

	public void setBdhhlx(String bdhhlx){
		this.bdhhlx=bdhhlx;
	}

	public Long getBdhmlpnbid(){
		return this.bdhmlpnbid;
	}

	public void setBdhmlpnbid(Long bdhmlpnbid){
		this.bdhmlpnbid=bdhmlpnbid;
	}

	public String getBdhssxq(){
		return this.bdhssxq;
	}

	public void setBdhssxq(String bdhssxq){
		this.bdhssxq=bdhssxq;
	}

	public String getBdhjlx(){
		return this.bdhjlx;
	}

	public void setBdhjlx(String bdhjlx){
		this.bdhjlx=bdhjlx;
	}

	public String getBdhmlph(){
		return this.bdhmlph;
	}

	public void setBdhmlph(String bdhmlph){
		this.bdhmlph=bdhmlph;
	}

	public String getBdhmlxz(){
		return this.bdhmlxz;
	}

	public void setBdhmlxz(String bdhmlxz){
		this.bdhmlxz=bdhmlxz;
	}

	public String getBdhpcs(){
		return this.bdhpcs;
	}

	public void setBdhpcs(String bdhpcs){
		this.bdhpcs=bdhpcs;
	}

	public String getBdhzrq(){
		return this.bdhzrq;
	}

	public void setBdhzrq(String bdhzrq){
		this.bdhzrq=bdhzrq;
	}

	public String getBdhxzjd(){
		return this.bdhxzjd;
	}

	public void setBdhxzjd(String bdhxzjd){
		this.bdhxzjd=bdhxzjd;
	}

	public String getBdhjcwh(){
		return this.bdhjcwh;
	}

	public void setBdhjcwh(String bdhjcwh){
		this.bdhjcwh=bdhjcwh;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getYwbz(){
		return this.ywbz;
	}

	public void setYwbz(String ywbz){
		this.ywbz=ywbz;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getCzsm(){
		return this.czsm;
	}

	public void setCzsm(Long czsm){
		this.czsm=czsm;
	}

	public String getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(String sbsj){
		this.sbsj=sbsj;
	}

	public String getSbryxm(){
		return this.sbryxm;
	}

	public void setSbryxm(String sbryxm){
		this.sbryxm=sbryxm;
	}

	public String getSbrgmsfhm(){
		return this.sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm){
		this.sbrgmsfhm=sbrgmsfhm;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public Long getSlrid(){
		return this.slrid;
	}

	public void setSlrid(Long slrid){
		this.slrid=slrid;
	}

	public Long getRzjs(){
		return this.rzjs;
	}

	public void setRzjs(Long rzjs){
		this.rzjs=rzjs;
	}

	public Long getHzjs(){
		return this.hzjs;
	}

	public void setHzjs(Long hzjs){
		this.hzjs=hzjs;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getMlph(){
		return this.mlph;
	}

	public void setMlph(String mlph){
		this.mlph=mlph;
	}

	public String getMlxz(){
		return this.mlxz;
	}

	public void setMlxz(String mlxz){
		this.mlxz=mlxz;
	}

	public String getPcs(){
		return this.pcs;
	}

	public void setPcs(String pcs){
		this.pcs=pcs;
	}

	public String getZrq(){
		return this.zrq;
	}

	public void setZrq(String zrq){
		this.zrq=zrq;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public String getJcwh(){
		return this.jcwh;
	}

	public void setJcwh(String jcwh){
		this.jcwh=jcwh;
	}

	public String getPxh(){
		return this.pxh;
	}

	public void setPxh(String pxh){
		this.pxh=pxh;
	}

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}