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
*户关联关系表
*/
@Entity
@Table(name="HJXX_HGLGXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_HGLGXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*关联ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	glid;
	private Long	hhid;
	private Long	glhhid;
	private String	glgx;
	private String	jljlsj;
	private Long	jljlrid;
	private String	zt;

	public PoHJXX_HGLGXB(){}

	public Long getGlid(){
		return this.glid;
	}

	public void setGlid(Long glid){
		this.glid=glid;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getGlhhid(){
		return this.glhhid;
	}

	public void setGlhhid(Long glhhid){
		this.glhhid=glhhid;
	}

	public String getGlgx(){
		return this.glgx;
	}

	public void setGlgx(String glgx){
		this.glgx=glgx;
	}

	public String getJljlsj(){
		return this.jljlsj;
	}

	public void setJljlsj(String jljlsj){
		this.jljlsj=jljlsj;
	}

	public Long getJljlrid(){
		return this.jljlrid;
	}

	public void setJljlrid(Long jljlrid){
		this.jljlrid=jljlrid;
	}

	public String getZt(){
		return this.zt;
	}

	public void setZt(String zt){
		this.zt=zt;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}