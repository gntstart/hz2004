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
*用户数据范围表
*/
@Entity
@Table(name="XT_YHSJFWB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHSJFWB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*数据范围ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	sjfwid;
	private Long	yhid;
	private String	xqlx;
	private String	sjfw;
	private String	dqbm;

	public PoXT_YHSJFWB(){}

	public Long getSjfwid(){
		return this.sjfwid;
	}

	public void setSjfwid(Long sjfwid){
		this.sjfwid=sjfwid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getXqlx(){
		return this.xqlx;
	}

	public void setXqlx(String xqlx){
		this.xqlx=xqlx;
	}

	public String getSjfw(){
		return this.sjfw;
	}

	public void setSjfw(String sjfw){
		this.sjfw=sjfw;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}