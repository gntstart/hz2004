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
*迁移证信息表
*/
@Entity
@Table(name="HJSP_QYZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_QYZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*迁移ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	qyid;
	private String	qyzbh;
	private String	czrgmsfhm;
	private String	xm;
	private String	cym;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdgjdq;
	private String	csdssxq;
	private String	csdxz;
	private String	jggjdq;
	private String	jgssxq;
	private String	whcd;
	private String	zy;
	private String	hyzk;
	private String	qyyy;
	private String	sqryczrgx1;
	private String	gmsfhm1;
	private String	xm1;
	private String	cym1;
	private String	xb1;
	private String	mz1;
	private String	csrq1;
	private String	csdgjdq1;
	private String	csdssxq1;
	private String	csdxz1;
	private String	jggjdq1;
	private String	jgssxq1;
	private String	whcd1;
	private String	zy1;
	private String	hyzk1;
	private String	qyyy1;
	private String	sqryczrgx2;
	private String	gmsfhm2;
	private String	xm2;
	private String	cym2;
	private String	xb2;
	private String	mz2;
	private String	csrq2;
	private String	csdgjdq2;
	private String	csdssxq2;
	private String	csdxz2;
	private String	jggjdq2;
	private String	jgssxq2;
	private String	whcd2;
	private String	zy2;
	private String	hyzk2;
	private String	qyyy2;
	private String	sqryczrgx3;
	private String	gmsfhm3;
	private String	xm3;
	private String	cym3;
	private String	xb3;
	private String	mz3;
	private String	csrq3;
	private String	csdgjdq3;
	private String	csdssxq3;
	private String	csdxz3;
	private String	jggjdq3;
	private String	jgssxq3;
	private String	whcd3;
	private String	zy3;
	private String	hyzk3;
	private String	qyyy3;
	private String	yzzssxq;
	private String	yzzxz;
	private String	qwdssxq;
	private String	qwdxz;
	private String	qfrq;
	private String	yxqxjzrq;
	private String	qfdwdm;
	private String	cbr;
	private String	qrbldw;
	private String	qrbldwdm;
	private String	qrblsj;
	private String	qrblr;
	private String	zqzbh;
	private String	bz;
	private String	yznf;
	private String	qyfw;

	public PoHJSP_QYZXXB(){}

	public Long getQyid(){
		return this.qyid;
	}

	public void setQyid(Long qyid){
		this.qyid=qyid;
	}

	public String getQyzbh(){
		return this.qyzbh;
	}

	public void setQyzbh(String qyzbh){
		this.qyzbh=qyzbh;
	}

	public String getCzrgmsfhm(){
		return this.czrgmsfhm;
	}

	public void setCzrgmsfhm(String czrgmsfhm){
		this.czrgmsfhm=czrgmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
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

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
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

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
	}

	public String getHyzk(){
		return this.hyzk;
	}

	public void setHyzk(String hyzk){
		this.hyzk=hyzk;
	}

	public String getQyyy(){
		return this.qyyy;
	}

	public void setQyyy(String qyyy){
		this.qyyy=qyyy;
	}

	public String getSqryczrgx1(){
		return this.sqryczrgx1;
	}

	public void setSqryczrgx1(String sqryczrgx1){
		this.sqryczrgx1=sqryczrgx1;
	}

	public String getGmsfhm1(){
		return this.gmsfhm1;
	}

	public void setGmsfhm1(String gmsfhm1){
		this.gmsfhm1=gmsfhm1;
	}

	public String getXm1(){
		return this.xm1;
	}

	public void setXm1(String xm1){
		this.xm1=xm1;
	}

	public String getCym1(){
		return this.cym1;
	}

	public void setCym1(String cym1){
		this.cym1=cym1;
	}

	public String getXb1(){
		return this.xb1;
	}

	public void setXb1(String xb1){
		this.xb1=xb1;
	}

	public String getMz1(){
		return this.mz1;
	}

	public void setMz1(String mz1){
		this.mz1=mz1;
	}

	public String getCsrq1(){
		return this.csrq1;
	}

	public void setCsrq1(String csrq1){
		this.csrq1=csrq1;
	}

	public String getCsdgjdq1(){
		return this.csdgjdq1;
	}

	public void setCsdgjdq1(String csdgjdq1){
		this.csdgjdq1=csdgjdq1;
	}

	public String getCsdssxq1(){
		return this.csdssxq1;
	}

	public void setCsdssxq1(String csdssxq1){
		this.csdssxq1=csdssxq1;
	}

	public String getCsdxz1(){
		return this.csdxz1;
	}

	public void setCsdxz1(String csdxz1){
		this.csdxz1=csdxz1;
	}

	public String getJggjdq1(){
		return this.jggjdq1;
	}

	public void setJggjdq1(String jggjdq1){
		this.jggjdq1=jggjdq1;
	}

	public String getJgssxq1(){
		return this.jgssxq1;
	}

	public void setJgssxq1(String jgssxq1){
		this.jgssxq1=jgssxq1;
	}

	public String getWhcd1(){
		return this.whcd1;
	}

	public void setWhcd1(String whcd1){
		this.whcd1=whcd1;
	}

	public String getZy1(){
		return this.zy1;
	}

	public void setZy1(String zy1){
		this.zy1=zy1;
	}

	public String getHyzk1(){
		return this.hyzk1;
	}

	public void setHyzk1(String hyzk1){
		this.hyzk1=hyzk1;
	}

	public String getQyyy1(){
		return this.qyyy1;
	}

	public void setQyyy1(String qyyy1){
		this.qyyy1=qyyy1;
	}

	public String getSqryczrgx2(){
		return this.sqryczrgx2;
	}

	public void setSqryczrgx2(String sqryczrgx2){
		this.sqryczrgx2=sqryczrgx2;
	}

	public String getGmsfhm2(){
		return this.gmsfhm2;
	}

	public void setGmsfhm2(String gmsfhm2){
		this.gmsfhm2=gmsfhm2;
	}

	public String getXm2(){
		return this.xm2;
	}

	public void setXm2(String xm2){
		this.xm2=xm2;
	}

	public String getCym2(){
		return this.cym2;
	}

	public void setCym2(String cym2){
		this.cym2=cym2;
	}

	public String getXb2(){
		return this.xb2;
	}

	public void setXb2(String xb2){
		this.xb2=xb2;
	}

	public String getMz2(){
		return this.mz2;
	}

	public void setMz2(String mz2){
		this.mz2=mz2;
	}

	public String getCsrq2(){
		return this.csrq2;
	}

	public void setCsrq2(String csrq2){
		this.csrq2=csrq2;
	}

	public String getCsdgjdq2(){
		return this.csdgjdq2;
	}

	public void setCsdgjdq2(String csdgjdq2){
		this.csdgjdq2=csdgjdq2;
	}

	public String getCsdssxq2(){
		return this.csdssxq2;
	}

	public void setCsdssxq2(String csdssxq2){
		this.csdssxq2=csdssxq2;
	}

	public String getCsdxz2(){
		return this.csdxz2;
	}

	public void setCsdxz2(String csdxz2){
		this.csdxz2=csdxz2;
	}

	public String getJggjdq2(){
		return this.jggjdq2;
	}

	public void setJggjdq2(String jggjdq2){
		this.jggjdq2=jggjdq2;
	}

	public String getJgssxq2(){
		return this.jgssxq2;
	}

	public void setJgssxq2(String jgssxq2){
		this.jgssxq2=jgssxq2;
	}

	public String getWhcd2(){
		return this.whcd2;
	}

	public void setWhcd2(String whcd2){
		this.whcd2=whcd2;
	}

	public String getZy2(){
		return this.zy2;
	}

	public void setZy2(String zy2){
		this.zy2=zy2;
	}

	public String getHyzk2(){
		return this.hyzk2;
	}

	public void setHyzk2(String hyzk2){
		this.hyzk2=hyzk2;
	}

	public String getQyyy2(){
		return this.qyyy2;
	}

	public void setQyyy2(String qyyy2){
		this.qyyy2=qyyy2;
	}

	public String getSqryczrgx3(){
		return this.sqryczrgx3;
	}

	public void setSqryczrgx3(String sqryczrgx3){
		this.sqryczrgx3=sqryczrgx3;
	}

	public String getGmsfhm3(){
		return this.gmsfhm3;
	}

	public void setGmsfhm3(String gmsfhm3){
		this.gmsfhm3=gmsfhm3;
	}

	public String getXm3(){
		return this.xm3;
	}

	public void setXm3(String xm3){
		this.xm3=xm3;
	}

	public String getCym3(){
		return this.cym3;
	}

	public void setCym3(String cym3){
		this.cym3=cym3;
	}

	public String getXb3(){
		return this.xb3;
	}

	public void setXb3(String xb3){
		this.xb3=xb3;
	}

	public String getMz3(){
		return this.mz3;
	}

	public void setMz3(String mz3){
		this.mz3=mz3;
	}

	public String getCsrq3(){
		return this.csrq3;
	}

	public void setCsrq3(String csrq3){
		this.csrq3=csrq3;
	}

	public String getCsdgjdq3(){
		return this.csdgjdq3;
	}

	public void setCsdgjdq3(String csdgjdq3){
		this.csdgjdq3=csdgjdq3;
	}

	public String getCsdssxq3(){
		return this.csdssxq3;
	}

	public void setCsdssxq3(String csdssxq3){
		this.csdssxq3=csdssxq3;
	}

	public String getCsdxz3(){
		return this.csdxz3;
	}

	public void setCsdxz3(String csdxz3){
		this.csdxz3=csdxz3;
	}

	public String getJggjdq3(){
		return this.jggjdq3;
	}

	public void setJggjdq3(String jggjdq3){
		this.jggjdq3=jggjdq3;
	}

	public String getJgssxq3(){
		return this.jgssxq3;
	}

	public void setJgssxq3(String jgssxq3){
		this.jgssxq3=jgssxq3;
	}

	public String getWhcd3(){
		return this.whcd3;
	}

	public void setWhcd3(String whcd3){
		this.whcd3=whcd3;
	}

	public String getZy3(){
		return this.zy3;
	}

	public void setZy3(String zy3){
		this.zy3=zy3;
	}

	public String getHyzk3(){
		return this.hyzk3;
	}

	public void setHyzk3(String hyzk3){
		this.hyzk3=hyzk3;
	}

	public String getQyyy3(){
		return this.qyyy3;
	}

	public void setQyyy3(String qyyy3){
		this.qyyy3=qyyy3;
	}

	public String getYzzssxq(){
		return this.yzzssxq;
	}

	public void setYzzssxq(String yzzssxq){
		this.yzzssxq=yzzssxq;
	}

	public String getYzzxz(){
		return this.yzzxz;
	}

	public void setYzzxz(String yzzxz){
		this.yzzxz=yzzxz;
	}

	public String getQwdssxq(){
		return this.qwdssxq;
	}

	public void setQwdssxq(String qwdssxq){
		this.qwdssxq=qwdssxq;
	}

	public String getQwdxz(){
		return this.qwdxz;
	}

	public void setQwdxz(String qwdxz){
		this.qwdxz=qwdxz;
	}

	public String getQfrq(){
		return this.qfrq;
	}

	public void setQfrq(String qfrq){
		this.qfrq=qfrq;
	}

	public String getYxqxjzrq(){
		return this.yxqxjzrq;
	}

	public void setYxqxjzrq(String yxqxjzrq){
		this.yxqxjzrq=yxqxjzrq;
	}

	public String getQfdwdm(){
		return this.qfdwdm;
	}

	public void setQfdwdm(String qfdwdm){
		this.qfdwdm=qfdwdm;
	}

	public String getCbr(){
		return this.cbr;
	}

	public void setCbr(String cbr){
		this.cbr=cbr;
	}

	public String getQrbldw(){
		return this.qrbldw;
	}

	public void setQrbldw(String qrbldw){
		this.qrbldw=qrbldw;
	}

	public String getQrbldwdm(){
		return this.qrbldwdm;
	}

	public void setQrbldwdm(String qrbldwdm){
		this.qrbldwdm=qrbldwdm;
	}

	public String getQrblsj(){
		return this.qrblsj;
	}

	public void setQrblsj(String qrblsj){
		this.qrblsj=qrblsj;
	}

	public String getQrblr(){
		return this.qrblr;
	}

	public void setQrblr(String qrblr){
		this.qrblr=qrblr;
	}

	public String getZqzbh(){
		return this.zqzbh;
	}

	public void setZqzbh(String zqzbh){
		this.zqzbh=zqzbh;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getYznf(){
		return this.yznf;
	}

	public void setYznf(String yznf){
		this.yznf=yznf;
	}

	public String getQyfw(){
		return this.qyfw;
	}

	public void setQyfw(String qyfw){
		this.qyfw=qyfw;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}