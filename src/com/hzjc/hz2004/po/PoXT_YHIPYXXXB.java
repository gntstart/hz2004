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
*用户IP允许信息表
*/
@Entity
@Table(name="XT_YHIPYXXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHIPYXXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*IP允许ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ipyxid;
	private Long	yhid;
	private String	ipdz;
	private Long	cjrid;
	private String	cjsj;
	private String	dqbm;

	public PoXT_YHIPYXXXB(){}

	public Long getIpyxid(){
		return this.ipyxid;
	}

	public void setIpyxid(Long ipyxid){
		this.ipyxid=ipyxid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getIpdz(){
		return this.ipdz;
	}

	public void setIpdz(String ipdz){
		this.ipdz=ipdz;
	}

	public Long getCjrid(){
		return this.cjrid;
	}

	public void setCjrid(Long cjrid){
		this.cjrid=cjrid;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}