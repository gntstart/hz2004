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
*拼音符号表
*/
@Entity
@Table(name="XT_PYFHB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_PYFHB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*符号ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	fhid;
	private String	hz;
	private String	py;
	private String	nm;

	public PoXT_PYFHB(){}

	public Long getFhid(){
		return this.fhid;
	}

	public void setFhid(Long fhid){
		this.fhid=fhid;
	}

	public String getHz(){
		return this.hz;
	}

	public void setHz(String hz){
		this.hz=hz;
	}

	public String getPy(){
		return this.py;
	}

	public void setPy(String py){
		this.py=py;
	}

	public String getNm(){
		return this.nm;
	}

	public void setNm(String nm){
		this.nm=nm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}