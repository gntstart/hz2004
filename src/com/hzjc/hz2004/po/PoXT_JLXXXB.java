package com.hzjc.hz2004.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*街路巷信息表
*/
@Entity
@Table(name="XT_JLXXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_JLXXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*代码
	*/
	@GenericGenerator(name = "generator", strategy = "assigned") 
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
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

	@Transient
	private List<PoXT_JWHXXB> jwhList;
	@Transient
	private String	qhdm2;
	@Transient
	private String	qhdm2_mc;
	
	public PoXT_JLXXXB(){}

	@Transient
	public List<PoXT_JWHXXB> getJwhList() {
		return jwhList;
	}

	public String getQhdm2_mc() {
		return qhdm2_mc;
	}

	public void setQhdm2_mc(String qhdm2_mc) {
		this.qhdm2_mc = qhdm2_mc;
	}

	public void setJwhList(List<PoXT_JWHXXB> jwhList) {
		this.jwhList = jwhList;
	}

	public String getQhdm2() {
		return qhdm2;
	}

	public void setQhdm2(String qhdm2) {
		this.qhdm2 = qhdm2;
	}

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

	public static long getSerialversionuid() {			return serialVersionUID;	}}