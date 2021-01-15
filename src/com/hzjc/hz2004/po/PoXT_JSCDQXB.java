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
*角色菜单权限表
*/
@Entity
@Table(name="XT_JSCDQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JSCDQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*角色菜单ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jscdid;
	private Long	jsid;
	private Long	gncdid;
	private String	dqbm;

	public PoXT_JSCDQXB(){}

	public Long getJscdid(){
		return this.jscdid;
	}

	public void setJscdid(Long jscdid){
		this.jscdid=jscdid;
	}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public Long getGncdid(){
		return this.gncdid;
	}

	public void setGncdid(Long gncdid){
		this.gncdid=gncdid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}