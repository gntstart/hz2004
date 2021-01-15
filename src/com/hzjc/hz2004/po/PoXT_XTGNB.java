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
*系统功能表
*/
@Entity
@Table(name="XT_XTGNB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XTGNB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*功能ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	gnid;
	private String	gnbh;
	private String	gnmc;
	private String	gnlx;
	private String	qybz;
	private String	dqbm;

	public PoXT_XTGNB(){}

	public Long getGnid(){
		return this.gnid;
	}

	public void setGnid(Long gnid){
		this.gnid=gnid;
	}

	public String getGnbh(){
		return this.gnbh;
	}

	public void setGnbh(String gnbh){
		this.gnbh=gnbh;
	}

	public String getGnmc(){
		return this.gnmc;
	}

	public void setGnmc(String gnmc){
		this.gnmc=gnmc;
	}

	public String getGnlx(){
		return this.gnlx;
	}

	public void setGnlx(String gnlx){
		this.gnlx=gnlx;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}