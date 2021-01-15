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
*准迁证信息表
*/
@Entity
@Table(name="HJSP_ZQZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_ZQZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*准迁ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zqid;
	private Long	spywid;
	private String	zqzjlx;
	private String	zjbh;
	private String	sqrgmsfhm;
	private String	sqrxm;
	private String	sqrzzssxq;
	private String	sqrzzxz;
	private String	sqrhkdjjg;
	private String	qyrysqrgx1;
	private String	qyrxm1;
	private String	qyrxb1;
	private String	qyrcsrq1;
	private String	qyrgmsfhm1;
	private String	qyrysqrgx2;
	private String	qyrxm2;
	private String	qyrxb2;
	private String	qyrcsrq2;
	private String	qyrgmsfhm2;
	private String	qyrysqrgx3;
	private String	qyrxm3;
	private String	qyrxb3;
	private String	qyrcsrq3;
	private String	qyrgmsfhm3;
	private String	qyrysqrgx4;
	private String	qyrxm4;
	private String	qyrxb4;
	private String	qyrcsrq4;
	private String	qyrgmsfhm4;
	private String	qyrhkdjjg;
	private String	qyrzzssxq;
	private String	qyrzzxz;
	private String	qrdssxq;
	private String	qrdxz;
	private String	qrdhkdjjg;
	private String	zqyy;
	private String	pzjg;
	private String	cbr;
	private String	qfrq;
	private String	bz;
	private String	qyrs;
	private String	qcbldw;
	private String	qcbldwdm;
	private String	qcblsj;
	private String	qcblr;
	private String	qyfw;
	private String	yxqjzrq;

	public PoHJSP_ZQZXXB(){}

	public Long getZqid(){
		return this.zqid;
	}

	public void setZqid(Long zqid){
		this.zqid=zqid;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getZqzjlx(){
		return this.zqzjlx;
	}

	public void setZqzjlx(String zqzjlx){
		this.zqzjlx=zqzjlx;
	}

	public String getZjbh(){
		return this.zjbh;
	}

	public void setZjbh(String zjbh){
		this.zjbh=zjbh;
	}

	public String getSqrgmsfhm(){
		return this.sqrgmsfhm;
	}

	public void setSqrgmsfhm(String sqrgmsfhm){
		this.sqrgmsfhm=sqrgmsfhm;
	}

	public String getSqrxm(){
		return this.sqrxm;
	}

	public void setSqrxm(String sqrxm){
		this.sqrxm=sqrxm;
	}

	public String getSqrzzssxq(){
		return this.sqrzzssxq;
	}

	public void setSqrzzssxq(String sqrzzssxq){
		this.sqrzzssxq=sqrzzssxq;
	}

	public String getSqrzzxz(){
		return this.sqrzzxz;
	}

	public void setSqrzzxz(String sqrzzxz){
		this.sqrzzxz=sqrzzxz;
	}

	public String getSqrhkdjjg(){
		return this.sqrhkdjjg;
	}

	public void setSqrhkdjjg(String sqrhkdjjg){
		this.sqrhkdjjg=sqrhkdjjg;
	}

	public String getQyrysqrgx1(){
		return this.qyrysqrgx1;
	}

	public void setQyrysqrgx1(String qyrysqrgx1){
		this.qyrysqrgx1=qyrysqrgx1;
	}

	public String getQyrxm1(){
		return this.qyrxm1;
	}

	public void setQyrxm1(String qyrxm1){
		this.qyrxm1=qyrxm1;
	}

	public String getQyrxb1(){
		return this.qyrxb1;
	}

	public void setQyrxb1(String qyrxb1){
		this.qyrxb1=qyrxb1;
	}

	public String getQyrcsrq1(){
		return this.qyrcsrq1;
	}

	public void setQyrcsrq1(String qyrcsrq1){
		this.qyrcsrq1=qyrcsrq1;
	}

	public String getQyrgmsfhm1(){
		return this.qyrgmsfhm1;
	}

	public void setQyrgmsfhm1(String qyrgmsfhm1){
		this.qyrgmsfhm1=qyrgmsfhm1;
	}

	public String getQyrysqrgx2(){
		return this.qyrysqrgx2;
	}

	public void setQyrysqrgx2(String qyrysqrgx2){
		this.qyrysqrgx2=qyrysqrgx2;
	}

	public String getQyrxm2(){
		return this.qyrxm2;
	}

	public void setQyrxm2(String qyrxm2){
		this.qyrxm2=qyrxm2;
	}

	public String getQyrxb2(){
		return this.qyrxb2;
	}

	public void setQyrxb2(String qyrxb2){
		this.qyrxb2=qyrxb2;
	}

	public String getQyrcsrq2(){
		return this.qyrcsrq2;
	}

	public void setQyrcsrq2(String qyrcsrq2){
		this.qyrcsrq2=qyrcsrq2;
	}

	public String getQyrgmsfhm2(){
		return this.qyrgmsfhm2;
	}

	public void setQyrgmsfhm2(String qyrgmsfhm2){
		this.qyrgmsfhm2=qyrgmsfhm2;
	}

	public String getQyrysqrgx3(){
		return this.qyrysqrgx3;
	}

	public void setQyrysqrgx3(String qyrysqrgx3){
		this.qyrysqrgx3=qyrysqrgx3;
	}

	public String getQyrxm3(){
		return this.qyrxm3;
	}

	public void setQyrxm3(String qyrxm3){
		this.qyrxm3=qyrxm3;
	}

	public String getQyrxb3(){
		return this.qyrxb3;
	}

	public void setQyrxb3(String qyrxb3){
		this.qyrxb3=qyrxb3;
	}

	public String getQyrcsrq3(){
		return this.qyrcsrq3;
	}

	public void setQyrcsrq3(String qyrcsrq3){
		this.qyrcsrq3=qyrcsrq3;
	}

	public String getQyrgmsfhm3(){
		return this.qyrgmsfhm3;
	}

	public void setQyrgmsfhm3(String qyrgmsfhm3){
		this.qyrgmsfhm3=qyrgmsfhm3;
	}

	public String getQyrysqrgx4(){
		return this.qyrysqrgx4;
	}

	public void setQyrysqrgx4(String qyrysqrgx4){
		this.qyrysqrgx4=qyrysqrgx4;
	}

	public String getQyrxm4(){
		return this.qyrxm4;
	}

	public void setQyrxm4(String qyrxm4){
		this.qyrxm4=qyrxm4;
	}

	public String getQyrxb4(){
		return this.qyrxb4;
	}

	public void setQyrxb4(String qyrxb4){
		this.qyrxb4=qyrxb4;
	}

	public String getQyrcsrq4(){
		return this.qyrcsrq4;
	}

	public void setQyrcsrq4(String qyrcsrq4){
		this.qyrcsrq4=qyrcsrq4;
	}

	public String getQyrgmsfhm4(){
		return this.qyrgmsfhm4;
	}

	public void setQyrgmsfhm4(String qyrgmsfhm4){
		this.qyrgmsfhm4=qyrgmsfhm4;
	}

	public String getQyrhkdjjg(){
		return this.qyrhkdjjg;
	}

	public void setQyrhkdjjg(String qyrhkdjjg){
		this.qyrhkdjjg=qyrhkdjjg;
	}

	public String getQyrzzssxq(){
		return this.qyrzzssxq;
	}

	public void setQyrzzssxq(String qyrzzssxq){
		this.qyrzzssxq=qyrzzssxq;
	}

	public String getQyrzzxz(){
		return this.qyrzzxz;
	}

	public void setQyrzzxz(String qyrzzxz){
		this.qyrzzxz=qyrzzxz;
	}

	public String getQrdssxq(){
		return this.qrdssxq;
	}

	public void setQrdssxq(String qrdssxq){
		this.qrdssxq=qrdssxq;
	}

	public String getQrdxz(){
		return this.qrdxz;
	}

	public void setQrdxz(String qrdxz){
		this.qrdxz=qrdxz;
	}

	public String getQrdhkdjjg(){
		return this.qrdhkdjjg;
	}

	public void setQrdhkdjjg(String qrdhkdjjg){
		this.qrdhkdjjg=qrdhkdjjg;
	}

	public String getZqyy(){
		return this.zqyy;
	}

	public void setZqyy(String zqyy){
		this.zqyy=zqyy;
	}

	public String getPzjg(){
		return this.pzjg;
	}

	public void setPzjg(String pzjg){
		this.pzjg=pzjg;
	}

	public String getCbr(){
		return this.cbr;
	}

	public void setCbr(String cbr){
		this.cbr=cbr;
	}

	public String getQfrq(){
		return this.qfrq;
	}

	public void setQfrq(String qfrq){
		this.qfrq=qfrq;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getQyrs(){
		return this.qyrs;
	}

	public void setQyrs(String qyrs){
		this.qyrs=qyrs;
	}

	public String getQcbldw(){
		return this.qcbldw;
	}

	public void setQcbldw(String qcbldw){
		this.qcbldw=qcbldw;
	}

	public String getQcbldwdm(){
		return this.qcbldwdm;
	}

	public void setQcbldwdm(String qcbldwdm){
		this.qcbldwdm=qcbldwdm;
	}

	public String getQcblsj(){
		return this.qcblsj;
	}

	public void setQcblsj(String qcblsj){
		this.qcblsj=qcblsj;
	}

	public String getQcblr(){
		return this.qcblr;
	}

	public void setQcblr(String qcblr){
		this.qcblr=qcblr;
	}

	public String getQyfw(){
		return this.qyfw;
	}

	public void setQyfw(String qyfw){
		this.qyfw=qyfw;
	}

	public String getYxqjzrq(){
		return this.yxqjzrq;
	}

	public void setYxqjzrq(String yxqjzrq){
		this.yxqjzrq=yxqjzrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}