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
*迁移设置表
*/
@Entity
@Table(name="XT_QYSZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_QYSZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*迁移ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	qyid;
	private String	qhdma;
	private String	qhdmb;
	private String	cjsj;
	private Long	cjrid;
	private String	xgsj;
	private Long	xgrid;
	private String	qybz;

	public PoXT_QYSZB(){}

	public Long getQyid(){
		return this.qyid;
	}

	public void setQyid(Long qyid){
		this.qyid=qyid;
	}

	public String getQhdma(){
		return this.qhdma;
	}

	public void setQhdma(String qhdma){
		this.qhdma=qhdma;
	}

	public String getQhdmb(){
		return this.qhdmb;
	}

	public void setQhdmb(String qhdmb){
		this.qhdmb=qhdmb;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public Long getCjrid(){
		return this.cjrid;
	}

	public void setCjrid(Long cjrid){
		this.cjrid=cjrid;
	}

	public String getXgsj(){
		return this.xgsj;
	}

	public void setXgsj(String xgsj){
		this.xgsj=xgsj;
	}

	public Long getXgrid(){
		return this.xgrid;
	}

	public void setXgrid(Long xgrid){
		this.xgrid=xgrid;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}