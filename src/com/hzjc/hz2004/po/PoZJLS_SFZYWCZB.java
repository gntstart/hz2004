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
*身份证业务操作表
*/
@Entity
@Table(name="ZJLS_SFZYWCZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJLS_SFZYWCZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*证件业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zjywid;
	private String	slh;
	private String	ywbz;
	private String	slzt;
	private Long	czyid;
	private String	czyxm;
	private String	czsj;
	private String	czip;

	public PoZJLS_SFZYWCZB(){}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getYwbz(){
		return this.ywbz;
	}

	public void setYwbz(String ywbz){
		this.ywbz=ywbz;
	}

	public String getSlzt(){
		return this.slzt;
	}

	public void setSlzt(String slzt){
		this.slzt=slzt;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzyxm(){
		return this.czyxm;
	}

	public void setCzyxm(String czyxm){
		this.czyxm=czyxm;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getCzip(){
		return this.czip;
	}

	public void setCzip(String czip){
		this.czip=czip;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}