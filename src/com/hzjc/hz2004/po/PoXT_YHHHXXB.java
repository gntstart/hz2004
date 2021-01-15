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
*用户登录会话信息表
*/
@Entity
@Table(name="XT_YHHHXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHHHXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*用户会话ID
	*/
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	yhhhid;
	private String	hhid;
	private Long	yhid;
	private String	yhip;
	private String	khdbb;

	public PoXT_YHHHXXB(){}

	public Long getYhhhid(){
		return this.yhhhid;
	}

	public void setYhhhid(Long yhhhid){
		this.yhhhid=yhhhid;
	}

	public String getHhid(){
		return this.hhid;
	}

	public void setHhid(String hhid){
		this.hhid=hhid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getYhip(){
		return this.yhip;
	}

	public void setYhip(String yhip){
		this.yhip=yhip;
	}

	public String getKhdbb(){
		return this.khdbb;
	}

	public void setKhdbb(String khdbb){
		this.khdbb=khdbb;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}