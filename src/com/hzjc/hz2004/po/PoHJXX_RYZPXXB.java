package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*人员照片信息表
*/
@Entity
@Table(name="HJXX_RYZPXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_RYZPXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

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
	private byte[]	zp;
	private String	lrrq;
	
	@Transient
	private String zpstr;
	
	public PoHJXX_RYZPXXB(){}

	public String getZpstr() {
		return zpstr;
	}

	public void setZpstr(String zpstr) {
		this.zpstr = zpstr;
	}

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

	public byte[] getZp(){
		return this.zp;
	}

	public void setZp(byte[] zp){
		this.zp=zp;
	}

	public String getLrrq(){
		return this.lrrq;
	}

	public void setLrrq(String lrrq){
		this.lrrq=lrrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}