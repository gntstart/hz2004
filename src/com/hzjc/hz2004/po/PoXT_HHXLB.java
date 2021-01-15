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
*户号序列表
*/
@Entity
@Table(name="XT_HHXLB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_HHXLB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*序列ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xlid;
	private String	dwdm;
	private String	hhxlid;

	public PoXT_HHXLB(){}

	public Long getXlid(){
		return this.xlid;
	}

	public void setXlid(Long xlid){
		this.xlid=xlid;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getHhxlid(){
		return this.hhxlid;
	}

	public void setHhxlid(String hhxlid){
		this.hhxlid=hhxlid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}