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
*角色功能权限表
*/
@Entity
@Table(name="XT_JSGNQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JSGNQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*角色功能ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jsgnid;
	private Long	jsid;
	private Long	gnid;
	private String	dqbm;

	public PoXT_JSGNQXB(){}

	public Long getJsgnid(){
		return this.jsgnid;
	}

	public void setJsgnid(Long jsgnid){
		this.jsgnid=jsgnid;
	}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public Long getGnid(){
		return this.gnid;
	}

	public void setGnid(Long gnid){
		this.gnid=gnid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}