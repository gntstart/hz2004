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
*办证绿色通道人员信息
*/
@Entity
@Table(name="WW_BZLSTDRYXX" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_BZLSTDRYXX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接口ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	id;
	private String	gmsfhm;
	private String	xm;
	private String	rkrq;

	public PoWW_BZLSTDRYXX(){}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getRkrq(){
		return this.rkrq;
	}

	public void setRkrq(String rkrq){
		this.rkrq=rkrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}