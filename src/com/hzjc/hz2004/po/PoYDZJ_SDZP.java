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
*异地证件视读照片表
*/
@Entity
@Table(name="YDZJ_SDZP" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYDZJ_SDZP implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*照片ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zpid;
	private String	gmsfhm;
	private String	xm;
	private byte[]	sdzp;
	private String	lrsj;

	public PoYDZJ_SDZP(){}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
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

	public byte[] getSdzp(){
		return this.sdzp;
	}

	public void setSdzp(byte[] sdzp){
		this.sdzp=sdzp;
	}

	public String getLrsj(){
		return this.lrsj;
	}

	public void setLrsj(String lrsj){
		this.lrsj=lrsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}