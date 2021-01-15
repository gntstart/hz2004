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
*下载信息表
*/
@Entity
@Table(name="HJXX_XZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_XZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*下载ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xzid;
	private String	sczt;
	private String	ztsm;
	private Long	scsl;
	private String	xzzt;
	private String	cxjk;
	private Long	slrid;
	private String	slsj;
	private String	czip;
	private String	sldw;
	private String	cxyj;

	public PoHJXX_XZXXB(){}

	public Long getXzid(){
		return this.xzid;
	}

	public void setXzid(Long xzid){
		this.xzid=xzid;
	}

	public String getSczt(){
		return this.sczt;
	}

	public void setSczt(String sczt){
		this.sczt=sczt;
	}

	public String getZtsm(){
		return this.ztsm;
	}

	public void setZtsm(String ztsm){
		this.ztsm=ztsm;
	}

	public Long getScsl(){
		return this.scsl;
	}

	public void setScsl(Long scsl){
		this.scsl=scsl;
	}

	public String getXzzt(){
		return this.xzzt;
	}

	public void setXzzt(String xzzt){
		this.xzzt=xzzt;
	}

	public String getCxjk(){
		return this.cxjk;
	}

	public void setCxjk(String cxjk){
		this.cxjk=cxjk;
	}

	public Long getSlrid(){
		return this.slrid;
	}

	public void setSlrid(Long slrid){
		this.slrid=slrid;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getCzip(){
		return this.czip;
	}

	public void setCzip(String czip){
		this.czip=czip;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public String getCxyj(){
		return this.cxyj;
	}

	public void setCxyj(String cxyj){
		this.cxyj=cxyj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}