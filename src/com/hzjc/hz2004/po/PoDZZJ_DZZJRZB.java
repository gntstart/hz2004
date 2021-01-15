package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*地址追加日志表
*/
@Entity
@Table(name="DZZJ_DZZJRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoDZZJ_DZZJRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*地址追加ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dzzjid;
	private String	xm;
	private String	gmsfhm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	zjdz;
	private String	sldw;
	private Long	yhid;
	private String	czsj;
	private Long	czyid;
	private String	czip;
	private String	hh;
	private String	ssxq;
	private String	jlx;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private Long	nbsfzid;
	private Long	rynbid;
	private Long	ryid;
	private String	ktglh;

	public PoDZZJ_DZZJRZB(){}

	public Long getDzzjid(){
		return this.dzzjid;
	}

	public void setDzzjid(Long dzzjid){
		this.dzzjid=dzzjid;
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

	public String getZjdz(){
		return this.zjdz;
	}

	public void setZjdz(String zjdz){
		this.zjdz=zjdz;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzip(){
		return this.czip;
	}

	public void setCzip(String czip){
		this.czip=czip;
	}

	public String getHh(){
		return this.hh;
	}

	public void setHh(String hh){
		this.hh=hh;
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

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getKtglh(){
		return this.ktglh;
	}

	public void setKtglh(String ktglh){
		this.ktglh=ktglh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}