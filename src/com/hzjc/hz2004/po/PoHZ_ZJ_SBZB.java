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
*户政业务子表
*/
@Entity
@Table(name="HZ_ZJ_SBZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZ_ZJ_SBZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*ID，唯一标识
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zbid;
	private Long	id;
	private String	pch;
	private String	sfzh;
	private String	pname;
	private String	pvalue;

	public PoHZ_ZJ_SBZB(){}

	public Long getZbid(){
		return this.zbid;
	}

	public void setZbid(Long zbid){
		this.zbid=zbid;
	}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getSfzh(){
		return this.sfzh;
	}

	public void setSfzh(String sfzh){
		this.sfzh=sfzh;
	}

	public String getPname(){
		return this.pname;
	}

	public void setPname(String pname){
		this.pname=pname;
	}

	public String getPvalue(){
		return this.pvalue;
	}

	public void setPvalue(String pvalue){
		this.pvalue=pvalue;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}