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
*居民身份证信息表
*/
@Entity
@Table(name="ZJXX_JMSFZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJXX_JMSFZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*内部身份证ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	nbsfzid;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	slyy;
	private String	bzyy;
	private String	sjyy;
	private String	zz;
	private String	zz1;
	private String	zz2;
	private String	zz3;
	private String	zz4;
	private String	zjlb;
	private String	zjzt;
	private Long	tbbz;
	private String	bwbh;
	private String	slh;
	private String	ktglh;
	private String	zjsj;
	private String	zjlx;

	public PoZJXX_JMSFZXXB(){}

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
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

	public String getSlyy(){
		return this.slyy;
	}

	public void setSlyy(String slyy){
		this.slyy=slyy;
	}

	public String getBzyy(){
		return this.bzyy;
	}

	public void setBzyy(String bzyy){
		this.bzyy=bzyy;
	}

	public String getSjyy(){
		return this.sjyy;
	}

	public void setSjyy(String sjyy){
		this.sjyy=sjyy;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getZz1(){
		return this.zz1;
	}

	public void setZz1(String zz1){
		this.zz1=zz1;
	}

	public String getZz2(){
		return this.zz2;
	}

	public void setZz2(String zz2){
		this.zz2=zz2;
	}

	public String getZz3(){
		return this.zz3;
	}

	public void setZz3(String zz3){
		this.zz3=zz3;
	}

	public String getZz4(){
		return this.zz4;
	}

	public void setZz4(String zz4){
		this.zz4=zz4;
	}

	public String getZjlb(){
		return this.zjlb;
	}

	public void setZjlb(String zjlb){
		this.zjlb=zjlb;
	}

	public String getZjzt(){
		return this.zjzt;
	}

	public void setZjzt(String zjzt){
		this.zjzt=zjzt;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getKtglh(){
		return this.ktglh;
	}

	public void setKtglh(String ktglh){
		this.ktglh=ktglh;
	}

	public String getZjsj(){
		return this.zjsj;
	}

	public void setZjsj(String zjsj){
		this.zjsj=zjsj;
	}

	public String getZjlx(){
		return this.zjlx;
	}

	public void setZjlx(String zjlx){
		this.zjlx=zjlx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}