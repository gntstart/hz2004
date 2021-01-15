package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*旅客编码生成
*/
@Entity
@Table(name="zzj_vbmgr" )
public class Lkbmgr implements com.hzjc.wsstruts.po.PO{
private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	
	/*
	*单位层次
	*/
	/*
	*旅馆编码
	*/
	private String	id;
	private String	lgbm;
	/*
	*日期
	*/
	private String	rq;
	/*
	*序号
	*/
	private Long	xh;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLgbm(){
		return this.lgbm;
	}

	public void setLgbm(String lgbm){
		this.lgbm=lgbm;
	}

	public String getRq(){
		return this.rq;
	}

	public void setRq(String rq){
		this.rq=rq;
	}

	public Long getXh(){
		return this.xh;
	}

	public void setXh(Long xh){
		this.xh=xh;
	}

	public String getLayerOooo(){
		return "";
	}

	public void setLayerOooo(String layerOoooo){
	}
}