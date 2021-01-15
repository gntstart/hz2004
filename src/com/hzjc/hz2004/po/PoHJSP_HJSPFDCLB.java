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
*户籍审批附带材料表
*/
@Entity
@Table(name="HJSP_HJSPFDCLB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJSPFDCLB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批材料ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spclid;
	private String	splx;
	private Long	spywid;
	private String	clbh;

	public PoHJSP_HJSPFDCLB(){}

	public Long getSpclid(){
		return this.spclid;
	}

	public void setSpclid(Long spclid){
		this.spclid=spclid;
	}

	public String getSplx(){
		return this.splx;
	}

	public void setSplx(String splx){
		this.splx=splx;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getClbh(){
		return this.clbh;
	}

	public void setClbh(String clbh){
		this.clbh=clbh;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}