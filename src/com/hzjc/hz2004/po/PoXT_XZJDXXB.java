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
*乡镇街道信息表
*/
@Entity
@Table(name="XT_XZJDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XZJDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*代码
	*/
	@GenericGenerator(name = "generator", strategy = "assigned") 
//	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	dm;
	private String	mc;
	private String	zwpy;
	private String	wbpy;
	private String	qhdm;
	private String	bz;
	private String	qybz;
	private String	bdlx;
	private String	bdsj;
	private String	xdm;
	private String	dzys;
	private String	dzysmc;

	public PoXT_XZJDXXB(){}

	public String getDm(){
		return this.dm;
	}

	public void setDm(String dm){
		this.dm=dm;
	}

	public String getMc(){
		return this.mc;
	}

	public void setMc(String mc){
		this.mc=mc;
	}

	public String getZwpy(){
		return this.zwpy;
	}

	public void setZwpy(String zwpy){
		this.zwpy=zwpy;
	}

	public String getWbpy(){
		return this.wbpy;
	}

	public void setWbpy(String wbpy){
		this.wbpy=wbpy;
	}

	public String getQhdm(){
		return this.qhdm;
	}

	public void setQhdm(String qhdm){
		this.qhdm=qhdm;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
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

	public String getXdm(){
		return this.xdm;
	}

	public void setXdm(String xdm){
		this.xdm=xdm;
	}

	public String getDzys(){
		return this.dzys;
	}

	public void setDzys(String dzys){
		this.dzys=dzys;
	}

	public String getDzysmc(){
		return this.dzysmc;
	}

	public void setDzysmc(String dzysmc){
		this.dzysmc=dzysmc;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}