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
*数据包异常
*/
@Entity
@Table(name="YC_SJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYC_SJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*包异常序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czrkbycid;
	private String	czrksjbscdw;
	private String	czrkysjbwjm;
	private String	czrksjbyclx;
	private String	czrkyclxms;
	private String	czrkjcdw;
	private String	czrkxxtqsj;
	private Long	zfbz;

	public PoYC_SJB(){}

	public Long getCzrkbycid(){
		return this.czrkbycid;
	}

	public void setCzrkbycid(Long czrkbycid){
		this.czrkbycid=czrkbycid;
	}

	public String getCzrksjbscdw(){
		return this.czrksjbscdw;
	}

	public void setCzrksjbscdw(String czrksjbscdw){
		this.czrksjbscdw=czrksjbscdw;
	}

	public String getCzrkysjbwjm(){
		return this.czrkysjbwjm;
	}

	public void setCzrkysjbwjm(String czrkysjbwjm){
		this.czrkysjbwjm=czrkysjbwjm;
	}

	public String getCzrksjbyclx(){
		return this.czrksjbyclx;
	}

	public void setCzrksjbyclx(String czrksjbyclx){
		this.czrksjbyclx=czrksjbyclx;
	}

	public String getCzrkyclxms(){
		return this.czrkyclxms;
	}

	public void setCzrkyclxms(String czrkyclxms){
		this.czrkyclxms=czrkyclxms;
	}

	public String getCzrkjcdw(){
		return this.czrkjcdw;
	}

	public void setCzrkjcdw(String czrkjcdw){
		this.czrkjcdw=czrkjcdw;
	}

	public String getCzrkxxtqsj(){
		return this.czrkxxtqsj;
	}

	public void setCzrkxxtqsj(String czrkxxtqsj){
		this.czrkxxtqsj=czrkxxtqsj;
	}

	public Long getZfbz(){
		return this.zfbz;
	}

	public void setZfbz(Long zfbz){
		this.zfbz=zfbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}