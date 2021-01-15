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
*区划调整户号控制表
*/
@Entity
@Table(name="QHTZHHKZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoQHTZHHKZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*调整前户号ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	tzqhhid;
	private Long	tzqhhnbid;
	private Long	tzhhhid;
	private Long	tzhhhnbid;
	private String	tzsj;

	public PoQHTZHHKZB(){}

	public Long getTzqhhid(){
		return this.tzqhhid;
	}

	public void setTzqhhid(Long tzqhhid){
		this.tzqhhid=tzqhhid;
	}

	public Long getTzqhhnbid(){
		return this.tzqhhnbid;
	}

	public void setTzqhhnbid(Long tzqhhnbid){
		this.tzqhhnbid=tzqhhnbid;
	}

	public Long getTzhhhid(){
		return this.tzhhhid;
	}

	public void setTzhhhid(Long tzhhhid){
		this.tzhhhid=tzhhhid;
	}

	public Long getTzhhhnbid(){
		return this.tzhhhnbid;
	}

	public void setTzhhhnbid(Long tzhhhnbid){
		this.tzhhhnbid=tzhhhnbid;
	}

	public String getTzsj(){
		return this.tzsj;
	}

	public void setTzsj(String tzsj){
		this.tzsj=tzsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}