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
*角色业务报表权限表
*/
@Entity
@Table(name="XT_JSYWBBQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JSYWBBQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*业务报表权限ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ywbbqxid;
	private Long	jsid;
	private Long	ywbbid;
	private String	dqbm;

	public PoXT_JSYWBBQXB(){}

	public Long getYwbbqxid(){
		return this.ywbbqxid;
	}

	public void setYwbbqxid(Long ywbbqxid){
		this.ywbbqxid=ywbbqxid;
	}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public Long getYwbbid(){
		return this.ywbbid;
	}

	public void setYwbbid(Long ywbbid){
		this.ywbbid=ywbbid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}