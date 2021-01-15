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
*制式报表信息表
*/
@Entity
@Table(name="XT_ZSBBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_ZSBBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*制式报表ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zsbbid;
	private Long	zsbbmbid;
	private String	bbmc;
	private byte[]	bbsjmb;
	private String	jlsj;
	private Long	scrid;
	private String	xgsj;
	private Long	xgrid;

	public PoXT_ZSBBXXB(){}

	public Long getZsbbid(){
		return this.zsbbid;
	}

	public void setZsbbid(Long zsbbid){
		this.zsbbid=zsbbid;
	}

	public Long getZsbbmbid(){
		return this.zsbbmbid;
	}

	public void setZsbbmbid(Long zsbbmbid){
		this.zsbbmbid=zsbbmbid;
	}

	public String getBbmc(){
		return this.bbmc;
	}

	public void setBbmc(String bbmc){
		this.bbmc=bbmc;
	}

	public byte[] getBbsjmb(){
		return this.bbsjmb;
	}

	public void setBbsjmb(byte[] bbsjmb){
		this.bbsjmb=bbsjmb;
	}

	public String getJlsj(){
		return this.jlsj;
	}

	public void setJlsj(String jlsj){
		this.jlsj=jlsj;
	}

	public Long getScrid(){
		return this.scrid;
	}

	public void setScrid(Long scrid){
		this.scrid=scrid;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setXgsj(String xgsj){
		this.xgsj=xgsj;
	}

	public Long getXgrid(){
		return this.xgrid;
	}

	public void setXgrid(Long xgrid){
		this.xgrid=xgrid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}