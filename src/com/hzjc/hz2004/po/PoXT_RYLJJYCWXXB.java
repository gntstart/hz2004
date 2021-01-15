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
*人员逻辑校验错误信息表
*/
@Entity
@Table(name="XT_RYLJJYCWXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_RYLJJYCWXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*校验ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jyid;
	private Long	ryid;
	private Long	rynbid;
	private String	xm;
	private String	gmsfhm;
	private String	pcs;
	private String	sm;
	private String	jysj;
	private Long	jyrid;

	public PoXT_RYLJJYCWXXB(){}

	public Long getJyid(){
		return this.jyid;
	}

	public void setJyid(Long jyid){
		this.jyid=jyid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getPcs(){
		return this.pcs;
	}

	public void setPcs(String pcs){
		this.pcs=pcs;
	}

	public String getSm(){
		return this.sm;
	}

	public void setSm(String sm){
		this.sm=sm;
	}

	public String getJysj(){
		return this.jysj;
	}

	public void setJysj(String jysj){
		this.jysj=jysj;
	}

	public Long getJyrid(){
		return this.jyrid;
	}

	public void setJyrid(Long jyrid){
		this.jyrid=jyrid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}