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
*数据包接收统计
*/
@Entity
@Table(name="YC_SJBJSTJ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYC_SJBJSTJ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包接收序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bjsid;
	private String	bwbh;
	private String	bwlx;
	private String	jssj;
	private String	lzjd;
	private Long	jlsl;
	private String	clbz;

	public PoYC_SJBJSTJ(){}

	public Long getBjsid(){
		return this.bjsid;
	}

	public void setBjsid(Long bjsid){
		this.bjsid=bjsid;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getBwlx(){
		return this.bwlx;
	}

	public void setBwlx(String bwlx){
		this.bwlx=bwlx;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public String getLzjd(){
		return this.lzjd;
	}

	public void setLzjd(String lzjd){
		this.lzjd=lzjd;
	}

	public Long getJlsl(){
		return this.jlsl;
	}

	public void setJlsl(Long jlsl){
		this.jlsl=jlsl;
	}

	public String getClbz(){
		return this.clbz;
	}

	public void setClbz(String clbz){
		this.clbz=clbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}