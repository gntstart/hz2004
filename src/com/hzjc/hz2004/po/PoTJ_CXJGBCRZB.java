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
*查询结果保存日志表
*/
@Entity
@Table(name="TJ_CXJGBCRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoTJ_CXJGBCRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*查询日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	cxrzid;
	private String	gnbh;
	private String	cxyj;
	private String	bcsj;
	private Long	czrid;
	private String	czrdw;

	public PoTJ_CXJGBCRZB(){}

	public Long getCxrzid(){
		return this.cxrzid;
	}

	public void setCxrzid(Long cxrzid){
		this.cxrzid=cxrzid;
	}

	public String getGnbh(){
		return this.gnbh;
	}

	public void setGnbh(String gnbh){
		this.gnbh=gnbh;
	}

	public String getCxyj(){
		return this.cxyj;
	}

	public void setCxyj(String cxyj){
		this.cxyj=cxyj;
	}

	public String getBcsj(){
		return this.bcsj;
	}

	public void setBcsj(String bcsj){
		this.bcsj=bcsj;
	}

	public Long getCzrid(){
		return this.czrid;
	}

	public void setCzrid(Long czrid){
		this.czrid=czrid;
	}

	public String getCzrdw(){
		return this.czrdw;
	}

	public void setCzrdw(String czrdw){
		this.czrdw=czrdw;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}