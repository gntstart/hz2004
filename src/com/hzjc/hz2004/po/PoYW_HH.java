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
*回函
*/
@Entity
@Table(name="YW_HH" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYW_HH implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*回函序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	hhid;
	private String	yfhbh;
	private String	gmsfhm;
	private String	xm;
	private String	cym;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	ckssxq;
	private String	ckxz;
	private String	whcd;
	private String	hyzk;
	private String	zy;
	private String	fhjgdm;
	private String	fhjg;
	private String	hhjgdm;
	private String	hhjg;
	private String	hhrq;
	private String	zkgllb;
	private String	zkglff;
	private String	fzcldw;
	private String	fzajlb;
	private String	fzfasj;
	private String	tjcldw;
	private String	tjajlb;
	private String	tjfasj;
	private String	sdcldw;
	private String	sdlx;
	private String	flgbz;
	private String	flgdjdw;
	private String	xscldw;
	private String	xsajlb;
	private String	xsfasj;
	private String	qtnr;
	private Long	czrkzfbz;

	public PoYW_HH(){}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public String getYfhbh(){
		return this.yfhbh;
	}

	public void setYfhbh(String yfhbh){
		this.yfhbh=yfhbh;
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

	public String getCkssxq(){
		return this.ckssxq;
	}

	public void setCkssxq(String ckssxq){
		this.ckssxq=ckssxq;
	}

	public String getCkxz(){
		return this.ckxz;
	}

	public void setCkxz(String ckxz){
		this.ckxz=ckxz;
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

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
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

	public String getHhjgdm(){
		return this.hhjgdm;
	}

	public void setHhjgdm(String hhjgdm){
		this.hhjgdm=hhjgdm;
	}

	public String getHhjg(){
		return this.hhjg;
	}

	public void setHhjg(String hhjg){
		this.hhjg=hhjg;
	}

	public String getHhrq(){
		return this.hhrq;
	}

	public void setHhrq(String hhrq){
		this.hhrq=hhrq;
	}

	public String getZkgllb(){
		return this.zkgllb;
	}

	public void setZkgllb(String zkgllb){
		this.zkgllb=zkgllb;
	}

	public String getZkglff(){
		return this.zkglff;
	}

	public void setZkglff(String zkglff){
		this.zkglff=zkglff;
	}

	public String getFzcldw(){
		return this.fzcldw;
	}

	public void setFzcldw(String fzcldw){
		this.fzcldw=fzcldw;
	}

	public String getFzajlb(){
		return this.fzajlb;
	}

	public void setFzajlb(String fzajlb){
		this.fzajlb=fzajlb;
	}

	public String getFzfasj(){
		return this.fzfasj;
	}

	public void setFzfasj(String fzfasj){
		this.fzfasj=fzfasj;
	}

	public String getTjcldw(){
		return this.tjcldw;
	}

	public void setTjcldw(String tjcldw){
		this.tjcldw=tjcldw;
	}

	public String getTjajlb(){
		return this.tjajlb;
	}

	public void setTjajlb(String tjajlb){
		this.tjajlb=tjajlb;
	}

	public String getTjfasj(){
		return this.tjfasj;
	}

	public void setTjfasj(String tjfasj){
		this.tjfasj=tjfasj;
	}

	public String getSdcldw(){
		return this.sdcldw;
	}

	public void setSdcldw(String sdcldw){
		this.sdcldw=sdcldw;
	}

	public String getSdlx(){
		return this.sdlx;
	}

	public void setSdlx(String sdlx){
		this.sdlx=sdlx;
	}

	public String getFlgbz(){
		return this.flgbz;
	}

	public void setFlgbz(String flgbz){
		this.flgbz=flgbz;
	}

	public String getFlgdjdw(){
		return this.flgdjdw;
	}

	public void setFlgdjdw(String flgdjdw){
		this.flgdjdw=flgdjdw;
	}

	public String getXscldw(){
		return this.xscldw;
	}

	public void setXscldw(String xscldw){
		this.xscldw=xscldw;
	}

	public String getXsajlb(){
		return this.xsajlb;
	}

	public void setXsajlb(String xsajlb){
		this.xsajlb=xsajlb;
	}

	public String getXsfasj(){
		return this.xsfasj;
	}

	public void setXsfasj(String xsfasj){
		this.xsfasj=xsfasj;
	}

	public String getQtnr(){
		return this.qtnr;
	}

	public void setQtnr(String qtnr){
		this.qtnr=qtnr;
	}

	public Long getCzrkzfbz(){
		return this.czrkzfbz;
	}

	public void setCzrkzfbz(Long czrkzfbz){
		this.czrkzfbz=czrkzfbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}