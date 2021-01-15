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
*数据包发送统计
*/
@Entity
@Table(name="YC_SJBFSTJ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYC_SJBFSTJ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包发送序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bfsid;
	private String	bwbh;
	private String	bwlx;
	private String	fssj;
	private String	fwjd;
	private Long	jlsl;

	public PoYC_SJBFSTJ(){}

	public Long getBfsid(){
		return this.bfsid;
	}

	public void setBfsid(Long bfsid){
		this.bfsid=bfsid;
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

	public String getFssj(){
		return this.fssj;
	}

	public void setFssj(String fssj){
		this.fssj=fssj;
	}

	public String getFwjd(){
		return this.fwjd;
	}

	public void setFwjd(String fwjd){
		this.fwjd=fwjd;
	}

	public Long getJlsl(){
		return this.jlsl;
	}

	public void setJlsl(Long jlsl){
		this.jlsl=jlsl;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}