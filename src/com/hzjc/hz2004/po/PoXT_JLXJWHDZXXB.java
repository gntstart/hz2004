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
*街路巷居委会对照信息表
*/
@Entity
@Table(name="XT_JLXJWHDZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JLXJWHDZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*参照ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czid;
	private String	jlxdm;
	private String	jwhdm;
	private String	jlxhlx;
	private String	jlxh;
	private String	qybz;
	private String	bdlx;
	private String	bdsj;

	public PoXT_JLXJWHDZXXB(){}

	public Long getCzid(){
		return this.czid;
	}

	public void setCzid(Long czid){
		this.czid=czid;
	}

	public String getJlxdm(){
		return this.jlxdm;
	}

	public void setJlxdm(String jlxdm){
		this.jlxdm=jlxdm;
	}

	public String getJwhdm(){
		return this.jwhdm;
	}

	public void setJwhdm(String jwhdm){
		this.jwhdm=jwhdm;
	}

	public String getJlxhlx(){
		return this.jlxhlx;
	}

	public void setJlxhlx(String jlxhlx){
		this.jlxhlx=jlxhlx;
	}

	public String getJlxh(){
		return this.jlxh;
	}

	public void setJlxh(String jlxh){
		this.jlxh=jlxh;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}