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
*户籍审批子表
*/
@Entity
@Table(name="HJSP_HJSPZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJSPZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批申请子ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spsqzid;
	private Long	spywid;
	private Long	ryid;
	private String	xm;
	private String	xb;
	private String	csrq;
	private String	gmsfhm;
	private String	ysqrgx;
	private String	yhkqx;
	private String	yhkszd;
	private String	hkxz;
	private String	gzdw;
	private Long	rynbid;
	private String	zqzjlx;
	private String	zjbh;
	private String	qrhhb;
	private String	mz;
	private String	kdqqy_qchjywid;
	private String	kdqqy_qcdqbm;
	private String	kdqqy_fksj;
	private String	kdqqy_fkzt;
	private String	kdqqy_fknr;
	private String	kdqqy_qyzbh;
	private String	kdqqy_lscxfldm;
	private String	kdqqy_qyldyy;
	private String	kdqqy_qclb;

	public PoHJSP_HJSPZB(){}

	public Long getSpsqzid(){
		return this.spsqzid;
	}

	public void setSpsqzid(Long spsqzid){
		this.spsqzid=spsqzid;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
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

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getYsqrgx(){
		return this.ysqrgx;
	}

	public void setYsqrgx(String ysqrgx){
		this.ysqrgx=ysqrgx;
	}

	public String getYhkqx(){
		return this.yhkqx;
	}

	public void setYhkqx(String yhkqx){
		this.yhkqx=yhkqx;
	}

	public String getYhkszd(){
		return this.yhkszd;
	}

	public void setYhkszd(String yhkszd){
		this.yhkszd=yhkszd;
	}

	public String getHkxz(){
		return this.hkxz;
	}

	public void setHkxz(String hkxz){
		this.hkxz=hkxz;
	}

	public String getGzdw(){
		return this.gzdw;
	}

	public void setGzdw(String gzdw){
		this.gzdw=gzdw;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
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

	public String getQrhhb(){
		return this.qrhhb;
	}

	public void setQrhhb(String qrhhb){
		this.qrhhb=qrhhb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getKdqqy_qchjywid(){
		return this.kdqqy_qchjywid;
	}

	public void setKdqqy_qchjywid(String kdqqy_qchjywid){
		this.kdqqy_qchjywid=kdqqy_qchjywid;
	}

	public String getKdqqy_qcdqbm(){
		return this.kdqqy_qcdqbm;
	}

	public void setKdqqy_qcdqbm(String kdqqy_qcdqbm){
		this.kdqqy_qcdqbm=kdqqy_qcdqbm;
	}

	public String getKdqqy_fksj(){
		return this.kdqqy_fksj;
	}

	public void setKdqqy_fksj(String kdqqy_fksj){
		this.kdqqy_fksj=kdqqy_fksj;
	}

	public String getKdqqy_fkzt(){
		return this.kdqqy_fkzt;
	}

	public void setKdqqy_fkzt(String kdqqy_fkzt){
		this.kdqqy_fkzt=kdqqy_fkzt;
	}

	public String getKdqqy_fknr(){
		return this.kdqqy_fknr;
	}

	public void setKdqqy_fknr(String kdqqy_fknr){
		this.kdqqy_fknr=kdqqy_fknr;
	}

	public String getKdqqy_qyzbh(){
		return this.kdqqy_qyzbh;
	}

	public void setKdqqy_qyzbh(String kdqqy_qyzbh){
		this.kdqqy_qyzbh=kdqqy_qyzbh;
	}

	public String getKdqqy_lscxfldm(){
		return this.kdqqy_lscxfldm;
	}

	public void setKdqqy_lscxfldm(String kdqqy_lscxfldm){
		this.kdqqy_lscxfldm=kdqqy_lscxfldm;
	}

	public String getKdqqy_qyldyy(){
		return this.kdqqy_qyldyy;
	}

	public void setKdqqy_qyldyy(String kdqqy_qyldyy){
		this.kdqqy_qyldyy=kdqqy_qyldyy;
	}

	public String getKdqqy_qclb(){
		return this.kdqqy_qclb;
	}

	public void setKdqqy_qclb(String kdqqy_qclb){
		this.kdqqy_qclb=kdqqy_qclb;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}