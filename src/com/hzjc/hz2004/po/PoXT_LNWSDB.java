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
*历年尾数段表
*/
@Entity
@Table(name="XT_LNWSDB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_LNWSDB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*尾数段ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	wsdid;
	private String	qhdm;
	private String	dwdm;
	private String	ksd;
	private String	jsd;
	private String	qyrq;
	private String	bz;
	private String	xzjd;

	public PoXT_LNWSDB(){}

	public Long getWsdid(){
		return this.wsdid;
	}

	public void setWsdid(Long wsdid){
		this.wsdid=wsdid;
	}

	public String getQhdm(){
		return this.qhdm;
	}

	public void setQhdm(String qhdm){
		this.qhdm=qhdm;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getKsd(){
		return this.ksd;
	}

	public void setKsd(String ksd){
		this.ksd=ksd;
	}

	public String getJsd(){
		return this.jsd;
	}

	public void setJsd(String jsd){
		this.jsd=jsd;
	}

	public String getQyrq(){
		return this.qyrq;
	}

	public void setQyrq(String qyrq){
		this.qyrq=qyrq;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}