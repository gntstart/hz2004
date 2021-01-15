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
*逃犯比对接口信息表
*/
@Entity
@Table(name="WW_TFBDJKXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_TFBDJKXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jkid;
	private String	jkjndi;
	private String	cxyj;
	private String	qybz;
	private String	cjsj;
	private String	bz;
	private String	fhts;

	public PoWW_TFBDJKXXB(){}

	public Long getJkid(){
		return this.jkid;
	}

	public void setJkid(Long jkid){
		this.jkid=jkid;
	}

	public String getJkjndi(){
		return this.jkjndi;
	}

	public void setJkjndi(String jkjndi){
		this.jkjndi=jkjndi;
	}

	public String getCxyj(){
		return this.cxyj;
	}

	public void setCxyj(String cxyj){
		this.cxyj=cxyj;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getFhts(){
		return this.fhts;
	}

	public void setFhts(String fhts){
		this.fhts=fhts;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}