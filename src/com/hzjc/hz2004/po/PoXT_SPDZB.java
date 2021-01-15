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
*审批动作表
*/
@Entity
@Table(name="XT_SPDZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_SPDZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*动作ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dzid;
	private String	dzmc;
	private String	ms;
	private String	fwjb;
	private String	qybz;
	private String	dqbm;

	public PoXT_SPDZB(){}

	public Long getDzid(){
		return this.dzid;
	}

	public void setDzid(Long dzid){
		this.dzid=dzid;
	}

	public String getDzmc(){
		return this.dzmc;
	}

	public void setDzmc(String dzmc){
		this.dzmc=dzmc;
	}

	public String getMs(){
		return this.ms;
	}

	public void setMs(String ms){
		this.ms=ms;
	}

	public String getFwjb(){
		return this.fwjb;
	}

	public void setFwjb(String fwjb){
		this.fwjb=fwjb;
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