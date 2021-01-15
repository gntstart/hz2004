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
*制证数据对帐表
*/
@Entity
@Table(name="RPT_ZZSJDZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoRPT_ZZSJDZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包文编号
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	bwbh;
	private Long	jlsl;
	private String	fsrq;
	private String	zzlx;

	public PoRPT_ZZSJDZ(){}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public Long getJlsl(){
		return this.jlsl;
	}

	public void setJlsl(Long jlsl){
		this.jlsl=jlsl;
	}

	public String getFsrq(){
		return this.fsrq;
	}

	public void setFsrq(String fsrq){
		this.fsrq=fsrq;
	}

	public String getZzlx(){
		return this.zzlx;
	}

	public void setZzlx(String zzlx){
		this.zzlx=zzlx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}