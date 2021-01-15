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
*制证回馈信息表
*/
@Entity
@Table(name="ZJYW_ZZHKXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_ZZHKXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*制证回馈ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zzhkid;
	private Long	rynbid;
	private Long	nbslid;
	private String	ktglh;
	private String	zzdwffrq;
	private byte[]	jdzp;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;
	private String	zzhksj;

	public PoZJYW_ZZHKXXB(){}

	public Long getZzhkid(){
		return this.zzhkid;
	}

	public void setZzhkid(Long zzhkid){
		this.zzhkid=zzhkid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getKtglh(){
		return this.ktglh;
	}

	public void setKtglh(String ktglh){
		this.ktglh=ktglh;
	}

	public String getZzdwffrq(){
		return this.zzdwffrq;
	}

	public void setZzdwffrq(String zzdwffrq){
		this.zzdwffrq=zzdwffrq;
	}

	public byte[] getJdzp(){
		return this.jdzp;
	}

	public void setJdzp(byte[] jdzp){
		this.jdzp=jdzp;
	}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public Long getCxzjywid(){
		return this.cxzjywid;
	}

	public void setCxzjywid(Long cxzjywid){
		this.cxzjywid=cxzjywid;
	}

	public String getZzhksj(){
		return this.zzhksj;
	}

	public void setZzhksj(String zzhksj){
		this.zzhksj=zzhksj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}