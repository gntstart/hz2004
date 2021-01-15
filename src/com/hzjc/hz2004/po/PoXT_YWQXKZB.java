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
*业务权限控制表
*/
@Entity
@Table(name="XT_YWQXKZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YWQXKZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*权限控制ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	qxkzid;
	private String	ywid;
	private String	ywmc;
	private String	xqlx;
	private String	sjfwxz;
	private String	yhdtcx;

	public PoXT_YWQXKZB(){}

	public Long getQxkzid(){
		return this.qxkzid;
	}

	public void setQxkzid(Long qxkzid){
		this.qxkzid=qxkzid;
	}

	public String getYwid(){
		return this.ywid;
	}

	public void setYwid(String ywid){
		this.ywid=ywid;
	}

	public String getYwmc(){
		return this.ywmc;
	}

	public void setYwmc(String ywmc){
		this.ywmc=ywmc;
	}

	public String getXqlx(){
		return this.xqlx;
	}

	public void setXqlx(String xqlx){
		this.xqlx=xqlx;
	}

	public String getSjfwxz(){
		return this.sjfwxz;
	}

	public void setSjfwxz(String sjfwxz){
		this.sjfwxz=sjfwxz;
	}

	public String getYhdtcx(){
		return this.yhdtcx;
	}

	public void setYhdtcx(String yhdtcx){
		this.yhdtcx=yhdtcx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}