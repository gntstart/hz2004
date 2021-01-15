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
*审批模板信息表
*/
@Entity
@Table(name="XT_SPMBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_SPMBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批模板ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spmbid;
	private String	mbmc;
	private String	mbdj;
	private Long	cjrid;
	private String	cjsj;
	private Long	xgrid;
	private String	xgsj;
	private String	qybz;
	private String	dqsys;

	public PoXT_SPMBXXB(){}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public String getMbmc(){
		return this.mbmc;
	}

	public void setMbmc(String mbmc){
		this.mbmc=mbmc;
	}

	public String getMbdj(){
		return this.mbdj;
	}

	public void setMbdj(String mbdj){
		this.mbdj=mbdj;
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

	public Long getXgrid(){
		return this.xgrid;
	}

	public void setXgrid(Long xgrid){
		this.xgrid=xgrid;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setXgsj(String xgsj){
		this.xgsj=xgsj;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getDqsys(){
		return this.dqsys;
	}

	public void setDqsys(String dqsys){
		this.dqsys=dqsys;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}