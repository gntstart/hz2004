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
@Table(name="XT_YHTXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHTXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*xm、jh、sfzh、dw 
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	sfzh;
	private String	xm;
	private String	jh;
	private String	dw;

	public PoXT_YHTXB(){}

	public String getSfzh(){
		return this.sfzh;
	}

	public void setSfzh(String sfzh){
		this.sfzh=sfzh;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getJh(){
		return this.jh;
	}

	public void setJh(String jh){
		this.jh=jh;
	}

	public String getDw(){
		return this.dw;
	}

	public void setDw(String dw){
		this.dw=dw;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}