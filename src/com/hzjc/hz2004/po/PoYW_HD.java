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
*函调
*/
@Entity
@Table(name="YW_HD" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYW_HD implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*函调序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	hdid;
	private String	fhbh;
	private String	gmsfhm;
	private String	xm;
	private String	fwcs;
	private String	zzsy;
	private String	zzdssxq;
	private String	zzdxz;
	private String	lbdrq;
	private String	hssf;
	private String	hszk;
	private String	hswffz;
	private String	hstjzt;
	private String	hssd;
	private String	hsflg;
	private String	hsxsjj;
	private String	qt;
	private String	fhjgdm;
	private String	fhjg;
	private String	fhrq;
	private String	bhhsj;
	private String	bhhnr;
	private String	zfsj;
	private String	zfjg;
	private String	zfjgdm;
	private String	zfnr;

	public PoYW_HD(){}

	public Long getHdid(){
		return this.hdid;
	}

	public void setHdid(Long hdid){
		this.hdid=hdid;
	}

	public String getFhbh(){
		return this.fhbh;
	}

	public void setFhbh(String fhbh){
		this.fhbh=fhbh;
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

	public String getFwcs(){
		return this.fwcs;
	}

	public void setFwcs(String fwcs){
		this.fwcs=fwcs;
	}

	public String getZzsy(){
		return this.zzsy;
	}

	public void setZzsy(String zzsy){
		this.zzsy=zzsy;
	}

	public String getZzdssxq(){
		return this.zzdssxq;
	}

	public void setZzdssxq(String zzdssxq){
		this.zzdssxq=zzdssxq;
	}

	public String getZzdxz(){
		return this.zzdxz;
	}

	public void setZzdxz(String zzdxz){
		this.zzdxz=zzdxz;
	}

	public String getLbdrq(){
		return this.lbdrq;
	}

	public void setLbdrq(String lbdrq){
		this.lbdrq=lbdrq;
	}

	public String getHssf(){
		return this.hssf;
	}

	public void setHssf(String hssf){
		this.hssf=hssf;
	}

	public String getHszk(){
		return this.hszk;
	}

	public void setHszk(String hszk){
		this.hszk=hszk;
	}

	public String getHswffz(){
		return this.hswffz;
	}

	public void setHswffz(String hswffz){
		this.hswffz=hswffz;
	}

	public String getHstjzt(){
		return this.hstjzt;
	}

	public void setHstjzt(String hstjzt){
		this.hstjzt=hstjzt;
	}

	public String getHssd(){
		return this.hssd;
	}

	public void setHssd(String hssd){
		this.hssd=hssd;
	}

	public String getHsflg(){
		return this.hsflg;
	}

	public void setHsflg(String hsflg){
		this.hsflg=hsflg;
	}

	public String getHsxsjj(){
		return this.hsxsjj;
	}

	public void setHsxsjj(String hsxsjj){
		this.hsxsjj=hsxsjj;
	}

	public String getQt(){
		return this.qt;
	}

	public void setQt(String qt){
		this.qt=qt;
	}

	public String getFhjgdm(){
		return this.fhjgdm;
	}

	public void setFhjgdm(String fhjgdm){
		this.fhjgdm=fhjgdm;
	}

	public String getFhjg(){
		return this.fhjg;
	}

	public void setFhjg(String fhjg){
		this.fhjg=fhjg;
	}

	public String getFhrq(){
		return this.fhrq;
	}

	public void setFhrq(String fhrq){
		this.fhrq=fhrq;
	}

	public String getBhhsj(){
		return this.bhhsj;
	}

	public void setBhhsj(String bhhsj){
		this.bhhsj=bhhsj;
	}

	public String getBhhnr(){
		return this.bhhnr;
	}

	public void setBhhnr(String bhhnr){
		this.bhhnr=bhhnr;
	}

	public String getZfsj(){
		return this.zfsj;
	}

	public void setZfsj(String zfsj){
		this.zfsj=zfsj;
	}

	public String getZfjg(){
		return this.zfjg;
	}

	public void setZfjg(String zfjg){
		this.zfjg=zfjg;
	}

	public String getZfjgdm(){
		return this.zfjgdm;
	}

	public void setZfjgdm(String zfjgdm){
		this.zfjgdm=zfjgdm;
	}

	public String getZfnr(){
		return this.zfnr;
	}

	public void setZfnr(String zfnr){
		this.zfnr=zfnr;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}