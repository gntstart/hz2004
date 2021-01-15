package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*户信息表
*/
@Entity
@Table(name="LODOP" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoLODOP implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*户号内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private String	id;//主键ID
	private String	lodopId;//模板ID
	private String	nr;//内容
	private String	zxbz;//注销标志
	private String	cjr;//创建人
	private String cjsj;	//创建时间
	private String	xgr;//修改人
	private String	xgsj;//修改时间	
	private String	lodopName;//模板名称

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLodopId() {
		return lodopId;
	}


	public void setLodopId(String lodopId) {
		this.lodopId = lodopId;
	}


	public String getNr() {
		return nr;
	}


	public void setNr(String nr) {
		this.nr = nr;
	}


	public String getZxbz() {
		return zxbz;
	}


	public void setZxbz(String zxbz) {
		this.zxbz = zxbz;
	}


	public String getCjr() {
		return cjr;
	}


	public void setCjr(String cjr) {
		this.cjr = cjr;
	}


	public String getCjsj() {
		return cjsj;
	}


	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}


	public String getXgr() {
		return xgr;
	}


	public void setXgr(String xgr) {
		this.xgr = xgr;
	}


	public String getXgsj() {
		return xgsj;
	}


	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}


	public PoLODOP(){}
	

	public String getLodopName() {
		return lodopName;
	}


	public void setLodopName(String lodopName) {
		this.lodopName = lodopName;
	}


	public static long getSerialversionuid() {			return serialVersionUID;	}}