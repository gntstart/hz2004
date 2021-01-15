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
*角色信息表
*/
@Entity
@Table(name="XT_JSXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JSXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*角色ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jsid;
	private String	jsmc;
	private String	ms;
	private String	dqbm;

	public PoXT_JSXXB(){}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public String getJsmc(){
		return this.jsmc;
	}

	public void setJsmc(String jsmc){
		this.jsmc=jsmc;
	}

	public String getMs(){
		return this.ms;
	}

	public void setMs(String ms){
		this.ms=ms;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}