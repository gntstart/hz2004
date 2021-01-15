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
*模板流程信息表
*/
@Entity
@Table(name="XT_MBLCXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_MBLCXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*模板流程ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	mblcid;
	private Long	spmbid;
	private Long	dzid;
	private String	dzz;
	private Long	xgdzid;
	private String	dzbz;

	public PoXT_MBLCXXB(){}

	public Long getMblcid(){
		return this.mblcid;
	}

	public void setMblcid(Long mblcid){
		this.mblcid=mblcid;
	}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public Long getDzid(){
		return this.dzid;
	}

	public void setDzid(Long dzid){
		this.dzid=dzid;
	}

	public String getDzz(){
		return this.dzz;
	}

	public void setDzz(String dzz){
		this.dzz=dzz;
	}

	public Long getXgdzid(){
		return this.xgdzid;
	}

	public void setXgdzid(Long xgdzid){
		this.xgdzid=xgdzid;
	}

	public String getDzbz(){
		return this.dzbz;
	}

	public void setDzbz(String dzbz){
		this.dzbz=dzbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}