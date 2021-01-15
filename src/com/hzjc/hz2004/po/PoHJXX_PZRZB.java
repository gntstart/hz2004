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
*拍照日志信息表
*/
@Entity
@Table(name="HJXX_PZRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_PZRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*拍照日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	pzrzid;
	private Long	zplsid;
	private Long	nbslid;
	private Long	yhid;
	private String	ipdz;
	private String	yhdlm;
	private String	yhdw;
	private String	yhxm;
	private String	bcsj;
	private String	rksj;
	private String	gmsfhm;
	private String	slh;
	private String	pzxlh;

	public PoHJXX_PZRZB(){}

	public Long getPzrzid(){
		return this.pzrzid;
	}

	public void setPzrzid(Long pzrzid){
		this.pzrzid=pzrzid;
	}

	public Long getZplsid(){
		return this.zplsid;
	}

	public void setZplsid(Long zplsid){
		this.zplsid=zplsid;
	}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getIpdz(){
		return this.ipdz;
	}

	public void setIpdz(String ipdz){
		this.ipdz=ipdz;
	}

	public String getYhdlm(){
		return this.yhdlm;
	}

	public void setYhdlm(String yhdlm){
		this.yhdlm=yhdlm;
	}

	public String getYhdw(){
		return this.yhdw;
	}

	public void setYhdw(String yhdw){
		this.yhdw=yhdw;
	}

	public String getYhxm(){
		return this.yhxm;
	}

	public void setYhxm(String yhxm){
		this.yhxm=yhxm;
	}

	public String getBcsj(){
		return this.bcsj;
	}

	public void setBcsj(String bcsj){
		this.bcsj=bcsj;
	}

	public String getRksj(){
		return this.rksj;
	}

	public void setRksj(String rksj){
		this.rksj=rksj;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getPzxlh(){
		return this.pzxlh;
	}

	public void setPzxlh(String pzxlh){
		this.pzxlh=pzxlh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}