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
*系统功能菜单表
*/
@Entity
@Table(name="XT_XTGNCDB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_XTGNCDB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*功能菜单ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	gncdid;
	private String	cdcc;
	private String	cdbz;
	private String	cdlx;
	private String	zdlb;
	private String	cdmc;
	private String	dqbm;
	private String 	url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PoXT_XTGNCDB(){}

	public Long getGncdid(){
		return this.gncdid;
	}

	public void setGncdid(Long gncdid){
		this.gncdid=gncdid;
	}

	public String getCdcc(){
		return this.cdcc;
	}

	public void setCdcc(String cdcc){
		this.cdcc=cdcc;
	}

	public String getCdbz(){
		return this.cdbz;
	}

	public void setCdbz(String cdbz){
		this.cdbz=cdbz;
	}

	public String getCdlx(){
		return this.cdlx;
	}

	public void setCdlx(String cdlx){
		this.cdlx=cdlx;
	}

	public String getZdlb(){
		return this.zdlb;
	}

	public void setZdlb(String zdlb){
		this.zdlb=zdlb;
	}

	public String getCdmc(){
		return this.cdmc;
	}

	public void setCdmc(String cdmc){
		this.cdmc=cdmc;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}