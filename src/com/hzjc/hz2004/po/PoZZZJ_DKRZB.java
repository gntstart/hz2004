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
*读卡日志表
*/
@Entity
@Table(name="ZZZJ_DKRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZZZJ_DKRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*读卡日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dkrzid;
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
	private String	yxqx;
	private String	qfjg;
	private String	zz;
	private String	zz1yzjg;
	private String	zz1zjsbbh;
	private String	zz1czydn;
	private String	zz1xrrq;
	private String	zz1zjzz;
	private String	zz2yzjg;
	private String	zz2zjsbbh;
	private String	zz2czydn;
	private String	zz2xrrq;
	private String	zz2zjzz;
	private String	zz3yzjg;
	private String	zz3zjsbbh;
	private String	zz3czydn;
	private String	zz3xrrq;
	private String	zz3zjzz;
	private String	zz4yzjg;
	private String	zz4zjsbbh;
	private String	zz4czydn;
	private String	zz4xrrq;
	private String	zz4zjzz;

	public PoZZZJ_DKRZB(){}

	public Long getDkrzid(){
		return this.dkrzid;
	}

	public void setDkrzid(Long dkrzid){
		this.dkrzid=dkrzid;
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

	public String getYxqx(){
		return this.yxqx;
	}

	public void setYxqx(String yxqx){
		this.yxqx=yxqx;
	}

	public String getQfjg(){
		return this.qfjg;
	}

	public void setQfjg(String qfjg){
		this.qfjg=qfjg;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getZz1yzjg(){
		return this.zz1yzjg;
	}

	public void setZz1yzjg(String zz1yzjg){
		this.zz1yzjg=zz1yzjg;
	}

	public String getZz1zjsbbh(){
		return this.zz1zjsbbh;
	}

	public void setZz1zjsbbh(String zz1zjsbbh){
		this.zz1zjsbbh=zz1zjsbbh;
	}

	public String getZz1czydn(){
		return this.zz1czydn;
	}

	public void setZz1czydn(String zz1czydn){
		this.zz1czydn=zz1czydn;
	}

	public String getZz1xrrq(){
		return this.zz1xrrq;
	}

	public void setZz1xrrq(String zz1xrrq){
		this.zz1xrrq=zz1xrrq;
	}

	public String getZz1zjzz(){
		return this.zz1zjzz;
	}

	public void setZz1zjzz(String zz1zjzz){
		this.zz1zjzz=zz1zjzz;
	}

	public String getZz2yzjg(){
		return this.zz2yzjg;
	}

	public void setZz2yzjg(String zz2yzjg){
		this.zz2yzjg=zz2yzjg;
	}

	public String getZz2zjsbbh(){
		return this.zz2zjsbbh;
	}

	public void setZz2zjsbbh(String zz2zjsbbh){
		this.zz2zjsbbh=zz2zjsbbh;
	}

	public String getZz2czydn(){
		return this.zz2czydn;
	}

	public void setZz2czydn(String zz2czydn){
		this.zz2czydn=zz2czydn;
	}

	public String getZz2xrrq(){
		return this.zz2xrrq;
	}

	public void setZz2xrrq(String zz2xrrq){
		this.zz2xrrq=zz2xrrq;
	}

	public String getZz2zjzz(){
		return this.zz2zjzz;
	}

	public void setZz2zjzz(String zz2zjzz){
		this.zz2zjzz=zz2zjzz;
	}

	public String getZz3yzjg(){
		return this.zz3yzjg;
	}

	public void setZz3yzjg(String zz3yzjg){
		this.zz3yzjg=zz3yzjg;
	}

	public String getZz3zjsbbh(){
		return this.zz3zjsbbh;
	}

	public void setZz3zjsbbh(String zz3zjsbbh){
		this.zz3zjsbbh=zz3zjsbbh;
	}

	public String getZz3czydn(){
		return this.zz3czydn;
	}

	public void setZz3czydn(String zz3czydn){
		this.zz3czydn=zz3czydn;
	}

	public String getZz3xrrq(){
		return this.zz3xrrq;
	}

	public void setZz3xrrq(String zz3xrrq){
		this.zz3xrrq=zz3xrrq;
	}

	public String getZz3zjzz(){
		return this.zz3zjzz;
	}

	public void setZz3zjzz(String zz3zjzz){
		this.zz3zjzz=zz3zjzz;
	}

	public String getZz4yzjg(){
		return this.zz4yzjg;
	}

	public void setZz4yzjg(String zz4yzjg){
		this.zz4yzjg=zz4yzjg;
	}

	public String getZz4zjsbbh(){
		return this.zz4zjsbbh;
	}

	public void setZz4zjsbbh(String zz4zjsbbh){
		this.zz4zjsbbh=zz4zjsbbh;
	}

	public String getZz4czydn(){
		return this.zz4czydn;
	}

	public void setZz4czydn(String zz4czydn){
		this.zz4czydn=zz4czydn;
	}

	public String getZz4xrrq(){
		return this.zz4xrrq;
	}

	public void setZz4xrrq(String zz4xrrq){
		this.zz4xrrq=zz4xrrq;
	}

	public String getZz4zjzz(){
		return this.zz4zjzz;
	}

	public void setZz4zjzz(String zz4zjzz){
		this.zz4zjzz=zz4zjzz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}