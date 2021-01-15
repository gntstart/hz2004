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
*接收批次包文信息表
*/
@Entity
@Table(name="JSV3_PCBWXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_PCBWXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包文信息ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bwxxid;
	private Long	pcid;
	private String	pch;
	private String	bwlx;
	private String	bwbh;
	private Long	jls;
	private String	jym;
	private String	pcbwzt;
	private String	sdsj;
	private String	bz;

	public PoJSV3_PCBWXXB(){}

	public Long getBwxxid(){
		return this.bwxxid;
	}

	public void setBwxxid(Long bwxxid){
		this.bwxxid=bwxxid;
	}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getBwlx(){
		return this.bwlx;
	}

	public void setBwlx(String bwlx){
		this.bwlx=bwlx;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public Long getJls(){
		return this.jls;
	}

	public void setJls(Long jls){
		this.jls=jls;
	}

	public String getJym(){
		return this.jym;
	}

	public void setJym(String jym){
		this.jym=jym;
	}

	public String getPcbwzt(){
		return this.pcbwzt;
	}

	public void setPcbwzt(String pcbwzt){
		this.pcbwzt=pcbwzt;
	}

	public String getSdsj(){
		return this.sdsj;
	}

	public void setSdsj(String sdsj){
		this.sdsj=sdsj;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}