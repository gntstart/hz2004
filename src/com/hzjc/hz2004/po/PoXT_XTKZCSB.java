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
*系统控制参数表
*/
@Entity
@Table(name="XT_XTKZCSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XTKZCSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*参数ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	csid;
	private String	kzlb;
	private String	kzmc;
	private String	kzz;
	private String	mrz;
	private String	bz;
	private String	xgbz;
	private String	bdlx;
	private String	bdsj;
	private String 	cdkzz;
	
	public PoXT_XTKZCSB(){}

	public Long getCsid(){
		return this.csid;
	}

	public void setCsid(Long csid){
		this.csid=csid;
	}

	public String getCdkzz() {
		return cdkzz;
	}

	public void setCdkzz(String cdkzz) {
		this.cdkzz = cdkzz;
	}

	public String getKzlb(){
		return this.kzlb;
	}

	public void setKzlb(String kzlb){
		this.kzlb=kzlb;
	}

	public String getKzmc(){
		return this.kzmc;
	}

	public void setKzmc(String kzmc){
		this.kzmc=kzmc;
	}

	public String getKzz(){
		return this.kzz;
	}

	public void setKzz(String kzz){
		this.kzz=kzz;
	}

	public String getMrz(){
		return this.mrz;
	}

	public void setMrz(String mrz){
		this.mrz=mrz;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getXgbz(){
		return this.xgbz;
	}

	public void setXgbz(String xgbz){
		this.xgbz=xgbz;
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