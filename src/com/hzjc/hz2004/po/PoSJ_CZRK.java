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
*上传人口基本信息
*/
@Entity
@Table(name="SJ_CZRK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSJ_CZRK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rynbid;
	private Long	ryid;
	private String	ssxq;
	private String	gmsfhm;
	private String	xm;
	private String	csrq;
	private Long	xpid;
	private Long	tbbz1;
	private String	bwbha;
	private Long	tbbz2;
	private String	bwbhb;
	private String	tbsj;
	private String	cym;
	private String	xb;
	private String	mz;
	private String	csdgjdq;
	private String	csdssxq;
	private String	csdxz;
	private String	jggjdq;
	private String	jgssxq;
	private String	whcd;
	private String	hyzt;
	private String	byzt;
	private String	sg;
	private String	zy;
	private String	fwcs;
	private String	zz;
	private String	xxjb;
	private String	zxbs;
	private String	xjgajgmc;
	private String	xjgajgjgdm;
	private String	pcsmc;
	private String	pcsjgdm;
	private String	hh;
	private String	hlx;
	private String	yhzgx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	jlx;
	private String	xzjd;

	public PoSJ_CZRK(){}

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

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
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

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public Long getXpid(){
		return this.xpid;
	}

	public void setXpid(Long xpid){
		this.xpid=xpid;
	}

	public Long getTbbz1(){
		return this.tbbz1;
	}

	public void setTbbz1(Long tbbz1){
		this.tbbz1=tbbz1;
	}

	public String getBwbha(){
		return this.bwbha;
	}

	public void setBwbha(String bwbha){
		this.bwbha=bwbha;
	}

	public Long getTbbz2(){
		return this.tbbz2;
	}

	public void setTbbz2(Long tbbz2){
		this.tbbz2=tbbz2;
	}

	public String getBwbhb(){
		return this.bwbhb;
	}

	public void setBwbhb(String bwbhb){
		this.bwbhb=bwbhb;
	}

	public String getTbsj(){
		return this.tbsj;
	}

	public void setTbsj(String tbsj){
		this.tbsj=tbsj;
	}

	public String getCym(){
		return this.cym;
	}

	public void setCym(String cym){
		this.cym=cym;
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

	public String getCsdgjdq(){
		return this.csdgjdq;
	}

	public void setCsdgjdq(String csdgjdq){
		this.csdgjdq=csdgjdq;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getCsdxz(){
		return this.csdxz;
	}

	public void setCsdxz(String csdxz){
		this.csdxz=csdxz;
	}

	public String getJggjdq(){
		return this.jggjdq;
	}

	public void setJggjdq(String jggjdq){
		this.jggjdq=jggjdq;
	}

	public String getJgssxq(){
		return this.jgssxq;
	}

	public void setJgssxq(String jgssxq){
		this.jgssxq=jgssxq;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getHyzt(){
		return this.hyzt;
	}

	public void setHyzt(String hyzt){
		this.hyzt=hyzt;
	}

	public String getByzt(){
		return this.byzt;
	}

	public void setByzt(String byzt){
		this.byzt=byzt;
	}

	public String getSg(){
		return this.sg;
	}

	public void setSg(String sg){
		this.sg=sg;
	}

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
	}

	public String getFwcs(){
		return this.fwcs;
	}

	public void setFwcs(String fwcs){
		this.fwcs=fwcs;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getXxjb(){
		return this.xxjb;
	}

	public void setXxjb(String xxjb){
		this.xxjb=xxjb;
	}

	public String getZxbs(){
		return this.zxbs;
	}

	public void setZxbs(String zxbs){
		this.zxbs=zxbs;
	}

	public String getXjgajgmc(){
		return this.xjgajgmc;
	}

	public void setXjgajgmc(String xjgajgmc){
		this.xjgajgmc=xjgajgmc;
	}

	public String getXjgajgjgdm(){
		return this.xjgajgjgdm;
	}

	public void setXjgajgjgdm(String xjgajgjgdm){
		this.xjgajgjgdm=xjgajgjgdm;
	}

	public String getPcsmc(){
		return this.pcsmc;
	}

	public void setPcsmc(String pcsmc){
		this.pcsmc=pcsmc;
	}

	public String getPcsjgdm(){
		return this.pcsjgdm;
	}

	public void setPcsjgdm(String pcsjgdm){
		this.pcsjgdm=pcsjgdm;
	}

	public String getHh(){
		return this.hh;
	}

	public void setHh(String hh){
		this.hh=hh;
	}

	public String getHlx(){
		return this.hlx;
	}

	public void setHlx(String hlx){
		this.hlx=hlx;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
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

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}