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
*设备信息表
*/
@Entity
@Table(name="DZZJ_SBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoDZZJ_SBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*设备管理号
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	sbglh;
	private String	dwdm;
	private String	zcsj;
	private String	zxsj;
	private String	sbzt;
	private String	sbid;

	public PoDZZJ_SBXXB(){}

	public String getSbglh(){
		return this.sbglh;
	}

	public void setSbglh(String sbglh){
		this.sbglh=sbglh;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getZcsj(){
		return this.zcsj;
	}

	public void setZcsj(String zcsj){
		this.zcsj=zcsj;
	}

	public String getZxsj(){
		return this.zxsj;
	}

	public void setZxsj(String zxsj){
		this.zxsj=zxsj;
	}

	public String getSbzt(){
		return this.sbzt;
	}

	public void setSbzt(String sbzt){
		this.sbzt=sbzt;
	}

	public String getSbid(){
		return this.sbid;
	}

	public void setSbid(String sbid){
		this.sbid=sbid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}