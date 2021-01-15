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
*用户动作权限表
*/
@Entity
@Table(name="XT_YHDZQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHDZQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*动作权限ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dzqxid;
	private Long	yhid;
	private Long	dzid;
	private String	dqbm;

	public PoXT_YHDZQXB(){}

	public Long getDzqxid(){
		return this.dzqxid;
	}

	public void setDzqxid(Long dzqxid){
		this.dzqxid=dzqxid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public Long getDzid(){
		return this.dzid;
	}

	public void setDzid(Long dzid){
		this.dzid=dzid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}