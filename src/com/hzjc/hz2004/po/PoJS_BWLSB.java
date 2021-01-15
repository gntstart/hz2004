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
*接收包文流水表
*/
@Entity
@Table(name="JS_BWLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJS_BWLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包文接受信息ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bwjsid;
	private String	bwbh;
	private String	dwsj;
	private String	zysj;
	private String	bwzt;

	public PoJS_BWLSB(){}

	public Long getBwjsid(){
		return this.bwjsid;
	}

	public void setBwjsid(Long bwjsid){
		this.bwjsid=bwjsid;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getDwsj(){
		return this.dwsj;
	}

	public void setDwsj(String dwsj){
		this.dwsj=dwsj;
	}

	public String getZysj(){
		return this.zysj;
	}

	public void setZysj(String zysj){
		this.zysj=zysj;
	}

	public String getBwzt(){
		return this.bwzt;
	}

	public void setBwzt(String bwzt){
		this.bwzt=bwzt;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}