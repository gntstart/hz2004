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
*系统参数表
*/
@Entity
@Table(name="XT_XTCSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XTCSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*参数ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	csid;
	private String	cslb;
	private String	dm;
	private String	mc;
	private String	zwpy;
	private String	kzbzb;
	private String	kzbzc;
	private String	kzbzd;
	private String	kzbze;
	private String	kzbzf;
	private String	kzbzg;
	private String	xgbz;
	private String	bdlx;
	private String	bdsj;

	public PoXT_XTCSB(){}

	public Long getCsid(){
		return this.csid;
	}

	public void setCsid(Long csid){
		this.csid=csid;
	}

	public String getCslb(){
		return this.cslb;
	}

	public void setCslb(String cslb){
		this.cslb=cslb;
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

	public String getKzbzb(){
		return this.kzbzb;
	}

	public void setKzbzb(String kzbzb){
		this.kzbzb=kzbzb;
	}

	public String getKzbzc(){
		return this.kzbzc;
	}

	public void setKzbzc(String kzbzc){
		this.kzbzc=kzbzc;
	}

	public String getKzbzd(){
		return this.kzbzd;
	}

	public void setKzbzd(String kzbzd){
		this.kzbzd=kzbzd;
	}

	public String getKzbze(){
		return this.kzbze;
	}

	public void setKzbze(String kzbze){
		this.kzbze=kzbze;
	}

	public String getKzbzf(){
		return this.kzbzf;
	}

	public void setKzbzf(String kzbzf){
		this.kzbzf=kzbzf;
	}

	public String getKzbzg(){
		return this.kzbzg;
	}

	public void setKzbzg(String kzbzg){
		this.kzbzg=kzbzg;
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