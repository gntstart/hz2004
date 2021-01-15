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
*角色制式报表权限表
*/
@Entity
@Table(name="XT_JSZSBBQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JSZSBBQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*制式报表权限ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zsbbqxid;
	private Long	jsid;
	private Long	zsbbmbid;
	private String	dqbm;

	public PoXT_JSZSBBQXB(){}

	public Long getZsbbqxid(){
		return this.zsbbqxid;
	}

	public void setZsbbqxid(Long zsbbqxid){
		this.zsbbqxid=zsbbqxid;
	}

	public Long getJsid(){
		return this.jsid;
	}

	public void setJsid(Long jsid){
		this.jsid=jsid;
	}

	public Long getZsbbmbid(){
		return this.zsbbmbid;
	}

	public void setZsbbmbid(Long zsbbmbid){
		this.zsbbmbid=zsbbmbid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}