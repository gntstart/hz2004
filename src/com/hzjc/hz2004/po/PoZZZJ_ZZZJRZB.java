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
*住址追加日志表
*/
@Entity
@Table(name="ZZZJ_ZZZJRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZZZJ_ZZZJRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*地址追加ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zzzjid;
	private String	xm;
	private String	gmsfhm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	sldw;
	private Long	yhid;
	private String	czsj;
	private String	czip;
	private String	hh;
	private String	ssxq;
	private String	jlx;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private Long	rynbid;
	private Long	ryid;
	private String	czysfhm;
	private String	czjg;
	private String	czyktbh;
	private String	dzjdz;
	private String	sfzzjwz;
	private String	zjsbbh;

	public PoZZZJ_ZZZJRZB(){}

	public Long getZzzjid(){
		return this.zzzjid;
	}

	public void setZzzjid(Long zzzjid){
		this.zzzjid=zzzjid;
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

	public String getCzysfhm(){
		return this.czysfhm;
	}

	public void setCzysfhm(String czysfhm){
		this.czysfhm=czysfhm;
	}

	public String getCzjg(){
		return this.czjg;
	}

	public void setCzjg(String czjg){
		this.czjg=czjg;
	}

	public String getCzyktbh(){
		return this.czyktbh;
	}

	public void setCzyktbh(String czyktbh){
		this.czyktbh=czyktbh;
	}

	public String getDzjdz(){
		return this.dzjdz;
	}

	public void setDzjdz(String dzjdz){
		this.dzjdz=dzjdz;
	}

	public String getSfzzjwz(){
		return this.sfzzjwz;
	}

	public void setSfzzjwz(String sfzzjwz){
		this.sfzzjwz=sfzzjwz;
	}

	public String getZjsbbh(){
		return this.zjsbbh;
	}

	public void setZjsbbh(String zjsbbh){
		this.zjsbbh=zjsbbh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}