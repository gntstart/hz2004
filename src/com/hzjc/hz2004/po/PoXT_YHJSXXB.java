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
*用户角色信息表
*/
@Entity
@Table(name="XT_YHJSXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHJSXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*用户角色ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	yhjsid;
	private Long	yhid;
	private Long	jsid;
	private String	dqbm;

	public PoXT_YHJSXXB(){}

	public Long getYhjsid(){
		return this.yhjsid;
	}

	public void setYhjsid(Long yhjsid){
		this.yhjsid=yhjsid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}