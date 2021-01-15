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
*民警考试情况表-从考试系统中同步
*/
@Entity
@Table(name="XT_YHKSJG" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHKSJG implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*主键
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	id;
	private String	yhm;
	private String	sfzh;
	private String	dwdm;
	private String	jh;
	private Double	fs;
	private String	flag;
	private String	bz;

	public PoXT_YHKSJG(){}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getYhm(){
		return this.yhm;
	}

	public void setYhm(String yhm){
		this.yhm=yhm;
	}

	public String getSfzh(){
		return this.sfzh;
	}

	public void setSfzh(String sfzh){
		this.sfzh=sfzh;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getJh(){
		return this.jh;
	}

	public void setJh(String jh){
		this.jh=jh;
	}

	public Double getFs(){
		return this.fs;
	}

	public void setFs(Double fs){
		this.fs=fs;
	}

	public String getFlag(){
		return this.flag;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}