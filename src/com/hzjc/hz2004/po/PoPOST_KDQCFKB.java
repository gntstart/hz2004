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
*一站式跨地市迁出反馈表
*/
@Entity
@Table(name="POST_KDQCFKB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_KDQCFKB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	kdsid;
	private String	qrdq;
	private String	spywid;
	private String	fkbz;
	private String	qyzbh;
	private String	cxfldm;

	public PoPOST_KDQCFKB(){}

	public String getKdsid(){
		return this.kdsid;
	}

	public void setKdsid(String kdsid){
		this.kdsid=kdsid;
	}

	public String getQrdq(){
		return this.qrdq;
	}

	public void setQrdq(String qrdq){
		this.qrdq=qrdq;
	}

	public String getSpywid(){
		return this.spywid;
	}

	public void setSpywid(String spywid){
		this.spywid=spywid;
	}

	public String getFkbz(){
		return this.fkbz;
	}

	public void setFkbz(String fkbz){
		this.fkbz=fkbz;
	}

	public String getQyzbh(){
		return this.qyzbh;
	}

	public void setQyzbh(String qyzbh){
		this.qyzbh=qyzbh;
	}

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}