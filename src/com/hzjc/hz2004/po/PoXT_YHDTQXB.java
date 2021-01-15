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
*用户等同权限表
*/
@Entity
@Table(name="XT_YHDTQXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHDTQXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*等同权限ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dtqxid;
	private Long	yhid;
	private Long	dtyhid;
	private String	dqbm;

	public PoXT_YHDTQXB(){}

	public Long getDtqxid(){
		return this.dtqxid;
	}

	public void setDtqxid(Long dtqxid){
		this.dtqxid=dtqxid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public Long getDtyhid(){
		return this.dtyhid;
	}

	public void setDtyhid(Long dtyhid){
		this.dtyhid=dtyhid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}