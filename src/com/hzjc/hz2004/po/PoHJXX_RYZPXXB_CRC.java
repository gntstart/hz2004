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

@Entity
@Table(name="HJXX_RYZPXXB_CRC" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_RYZPXXB_CRC implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*照片ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zpid;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private Long	len;
	private Long	crc;
	private String	md5;

	public PoHJXX_RYZPXXB_CRC(){}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
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

	public Long getLen(){
		return this.len;
	}

	public void setLen(Long len){
		this.len=len;
	}

	public Long getCrc(){
		return this.crc;
	}

	public void setCrc(Long crc){
		this.crc=crc;
	}

	public String getMd5(){
		return this.md5;
	}

	public void setMd5(String md5){
		this.md5=md5;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}