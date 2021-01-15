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
*杭州市市民卡写卡日志表
*/
@Entity
@Table(name="HZSMK_XKRZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZSMK_XKRZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*写卡日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xkrzid;
	private Long	rynbid;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdssxq;
	private String	dhhm;
	private String	whcd;
	private String	hyzk;
	private String	sg;
	private String	xx;
	private String	zy;
	private String	hb;
	private String	yhzgx;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	hh;
	private String	szcsdm;
	private String	czhkszd;
	private String	xgqhb;
	private String	xgqczhkszd;
	private String	xgqmz;
	private String	xgqcsd;
	private String	xgqcsdm;
	private String	kntxdz;
	private String	knyzbm;
	private String	knlxdh;
	private String	knhyzk;
	private String	knwhcd;
	private String	kdsbm;
	private String	fkrq;
	private String	kyxq;
	private Long	xgrid;
	private String	xgjqip;
	private String	xgsj;

	public PoHZSMK_XKRZ(){}

	public Long getXkrzid(){
		return this.xkrzid;
	}

	public void setXkrzid(Long xkrzid){
		this.xkrzid=xkrzid;
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

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getDhhm(){
		return this.dhhm;
	}

	public void setDhhm(String dhhm){
		this.dhhm=dhhm;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getHyzk(){
		return this.hyzk;
	}

	public void setHyzk(String hyzk){
		this.hyzk=hyzk;
	}

	public String getSg(){
		return this.sg;
	}

	public void setSg(String sg){
		this.sg=sg;
	}

	public String getXx(){
		return this.xx;
	}

	public void setXx(String xx){
		this.xx=xx;
	}

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
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

	public String getHh(){
		return this.hh;
	}

	public void setHh(String hh){
		this.hh=hh;
	}

	public String getSzcsdm(){
		return this.szcsdm;
	}

	public void setSzcsdm(String szcsdm){
		this.szcsdm=szcsdm;
	}

	public String getCzhkszd(){
		return this.czhkszd;
	}

	public void setCzhkszd(String czhkszd){
		this.czhkszd=czhkszd;
	}

	public String getXgqhb(){
		return this.xgqhb;
	}

	public void setXgqhb(String xgqhb){
		this.xgqhb=xgqhb;
	}

	public String getXgqczhkszd(){
		return this.xgqczhkszd;
	}

	public void setXgqczhkszd(String xgqczhkszd){
		this.xgqczhkszd=xgqczhkszd;
	}

	public String getXgqmz(){
		return this.xgqmz;
	}

	public void setXgqmz(String xgqmz){
		this.xgqmz=xgqmz;
	}

	public String getXgqcsd(){
		return this.xgqcsd;
	}

	public void setXgqcsd(String xgqcsd){
		this.xgqcsd=xgqcsd;
	}

	public String getXgqcsdm(){
		return this.xgqcsdm;
	}

	public void setXgqcsdm(String xgqcsdm){
		this.xgqcsdm=xgqcsdm;
	}

	public String getKntxdz(){
		return this.kntxdz;
	}

	public void setKntxdz(String kntxdz){
		this.kntxdz=kntxdz;
	}

	public String getKnyzbm(){
		return this.knyzbm;
	}

	public void setKnyzbm(String knyzbm){
		this.knyzbm=knyzbm;
	}

	public String getKnlxdh(){
		return this.knlxdh;
	}

	public void setKnlxdh(String knlxdh){
		this.knlxdh=knlxdh;
	}

	public String getKnhyzk(){
		return this.knhyzk;
	}

	public void setKnhyzk(String knhyzk){
		this.knhyzk=knhyzk;
	}

	public String getKnwhcd(){
		return this.knwhcd;
	}

	public void setKnwhcd(String knwhcd){
		this.knwhcd=knwhcd;
	}

	public String getKdsbm(){
		return this.kdsbm;
	}

	public void setKdsbm(String kdsbm){
		this.kdsbm=kdsbm;
	}

	public String getFkrq(){
		return this.fkrq;
	}

	public void setFkrq(String fkrq){
		this.fkrq=fkrq;
	}

	public String getKyxq(){
		return this.kyxq;
	}

	public void setKyxq(String kyxq){
		this.kyxq=kyxq;
	}

	public Long getXgrid(){
		return this.xgrid;
	}

	public void setXgrid(Long xgrid){
		this.xgrid=xgrid;
	}

	public String getXgjqip(){
		return this.xgjqip;
	}

	public void setXgjqip(String xgjqip){
		this.xgjqip=xgjqip;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setXgsj(String xgsj){
		this.xgsj=xgsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}