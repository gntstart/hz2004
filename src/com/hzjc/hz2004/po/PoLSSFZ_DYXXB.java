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
*临时身份证打印信息表
*/
@Entity
@Table(name="LSSFZ_DYXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoLSSFZ_DYXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*临时打印ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	lsdyid;
	private Long	lsslid;
	private Long	ryid;
	private String	lsjmsfzkh;
	private Long	czyid;
	private String	czsj;
	private String	czyip;

	public PoLSSFZ_DYXXB(){}

	public Long getLsdyid(){
		return this.lsdyid;
	}

	public void setLsdyid(Long lsdyid){
		this.lsdyid=lsdyid;
	}

	public Long getLsslid(){
		return this.lsslid;
	}

	public void setLsslid(Long lsslid){
		this.lsslid=lsslid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getLsjmsfzkh(){
		return this.lsjmsfzkh;
	}

	public void setLsjmsfzkh(String lsjmsfzkh){
		this.lsjmsfzkh=lsjmsfzkh;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getCzyip(){
		return this.czyip;
	}

	public void setCzyip(String czyip){
		this.czyip=czyip;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}