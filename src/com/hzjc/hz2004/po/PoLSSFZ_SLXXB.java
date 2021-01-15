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
*临时身份证受理信息表
*/
@Entity
@Table(name="LSSFZ_SLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoLSSFZ_SLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*临时受理ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	lsslid;
	private Long	ryid;
	private Long	rynbid;
	private Long	zpid;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	lsjmsfzkh;
	private String	zz;
	private String	xm;
	private String	gmsfhm;
	private Long	nbsfzid;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdssxq;
	private Long	mlpnbid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private Long	czyid;
	private String	czsj;
	private String	czyip;
	private Long	dyrid;
	private String	dysj;
	private String	dyrip;
	private String	dybz;

	public PoLSSFZ_SLXXB(){}

	public Long getLsslid(){
		return this.lsslid;
	}

	public void setLsslid(Long lsslid){
		this.lsslid=lsslid;
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

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public String getQfjg(){
		return this.qfjg;
	}

	public void setQfjg(String qfjg){
		this.qfjg=qfjg;
	}

	public String getYxqxqsrq(){
		return this.yxqxqsrq;
	}

	public void setYxqxqsrq(String yxqxqsrq){
		this.yxqxqsrq=yxqxqsrq;
	}

	public String getYxqxjzrq(){
		return this.yxqxjzrq;
	}

	public void setYxqxjzrq(String yxqxjzrq){
		this.yxqxjzrq=yxqxjzrq;
	}

	public String getLsjmsfzkh(){
		return this.lsjmsfzkh;
	}

	public void setLsjmsfzkh(String lsjmsfzkh){
		this.lsjmsfzkh=lsjmsfzkh;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
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

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
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

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getCzyip(){
		return this.czyip;
	}

	public void setCzyip(String czyip){
		this.czyip=czyip;
	}

	public Long getDyrid(){
		return this.dyrid;
	}

	public void setDyrid(Long dyrid){
		this.dyrid=dyrid;
	}

	public String getDysj(){
		return this.dysj;
	}

	public void setDysj(String dysj){
		this.dysj=dysj;
	}

	public String getDyrip(){
		return this.dyrip;
	}

	public void setDyrip(String dyrip){
		this.dyrip=dyrip;
	}

	public String getDybz(){
		return this.dybz;
	}

	public void setDybz(String dybz){
		this.dybz=dybz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}