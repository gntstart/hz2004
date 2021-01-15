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
*本市市区表
*/
@Entity
@Table(name="XT_BSSQB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_BSSQB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*市区ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	sqid;
	private String	qhdm;
	private String	sqbz;
	private String	gxbz;
	private String	qybz;
	private String	bdlx;
	private String	bdsj;

	public PoXT_BSSQB(){}

	public Long getSqid(){
		return this.sqid;
	}

	public void setSqid(Long sqid){
		this.sqid=sqid;
	}

	public String getQhdm(){
		return this.qhdm;
	}

	public void setQhdm(String qhdm){
		this.qhdm=qhdm;
	}

	public String getSqbz(){
		return this.sqbz;
	}

	public void setSqbz(String sqbz){
		this.sqbz=sqbz;
	}

	public String getGxbz(){
		return this.gxbz;
	}

	public void setGxbz(String gxbz){
		this.gxbz=gxbz;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}