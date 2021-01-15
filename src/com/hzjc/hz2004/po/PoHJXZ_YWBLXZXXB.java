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
*业务办理限制信息表
*/
@Entity
@Table(name="HJXZ_YWBLXZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXZ_YWBLXZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*限制信息ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xzxxid;
	private String	xzmc;
	private String	xzywlx;
	private String	xzbds;
	private Long	cjrid;
	private String	cjsj;
	private Long	xgrid;
	private String	xgsj;
	private String	xzzt;
	private String	qybz;
	private Long	spmbid;

	public PoHJXZ_YWBLXZXXB(){}

	public Long getXzxxid(){
		return this.xzxxid;
	}

	public void setXzxxid(Long xzxxid){
		this.xzxxid=xzxxid;
	}

	public String getXzmc(){
		return this.xzmc;
	}

	public void setXzmc(String xzmc){
		this.xzmc=xzmc;
	}

	public String getXzywlx(){
		return this.xzywlx;
	}

	public void setXzywlx(String xzywlx){
		this.xzywlx=xzywlx;
	}

	public String getXzbds(){
		return this.xzbds;
	}

	public void setXzbds(String xzbds){
		this.xzbds=xzbds;
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

	public String getXzzt(){
		return this.xzzt;
	}

	public void setXzzt(String xzzt){
		this.xzzt=xzzt;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}