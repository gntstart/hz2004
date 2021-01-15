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
*数据包编号表
*/
@Entity
@Table(name="XT_SJBBHB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_SJBBHB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*序列ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xlid;
	private String	rq;
	private String	bhid;

	public PoXT_SJBBHB(){}

	public Long getXlid(){
		return this.xlid;
	}

	public void setXlid(Long xlid){
		this.xlid=xlid;
	}

	public String getRq(){
		return this.rq;
	}

	public void setRq(String rq){
		this.rq=rq;
	}

	public String getBhid(){
		return this.bhid;
	}

	public void setBhid(String bhid){
		this.bhid=bhid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}