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
*变更打印字段控制表
*/
@Entity
@Table(name="HJXZ_BGDYZDKZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXZ_BGDYZDKZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*字段控制ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zdkzid;
	private String	zdmc;
	private String	zdhy;
	private String	kzbz;

	public PoHJXZ_BGDYZDKZB(){}

	public Long getZdkzid(){
		return this.zdkzid;
	}

	public void setZdkzid(Long zdkzid){
		this.zdkzid=zdkzid;
	}

	public String getZdmc(){
		return this.zdmc;
	}

	public void setZdmc(String zdmc){
		this.zdmc=zdmc;
	}

	public String getZdhy(){
		return this.zdhy;
	}

	public void setZdhy(String zdhy){
		this.zdhy=zdhy;
	}

	public String getKzbz(){
		return this.kzbz;
	}

	public void setKzbz(String kzbz){
		this.kzbz=kzbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}