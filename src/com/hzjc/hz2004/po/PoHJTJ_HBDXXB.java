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
*户变动信息表
*/
@Entity
@Table(name="HJTJ_HBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJTJ_HBDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*户变动ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	hbdid;
	private Long	hhnbid;
	private String	bdfw;
	private String	bdyy;
	private String	bdsj;
	private String	ywnr;
	private Long	hzjs;
	private Long	hjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxhjywid;

	public PoHJTJ_HBDXXB(){}

	public Long getHbdid(){
		return this.hbdid;
	}

	public void setHbdid(Long hbdid){
		this.hbdid=hbdid;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public String getBdfw(){
		return this.bdfw;
	}

	public void setBdfw(String bdfw){
		this.bdfw=bdfw;
	}

	public String getBdyy(){
		return this.bdyy;
	}

	public void setBdyy(String bdyy){
		this.bdyy=bdyy;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public String getYwnr(){
		return this.ywnr;
	}

	public void setYwnr(String ywnr){
		this.ywnr=ywnr;
	}

	public Long getHzjs(){
		return this.hzjs;
	}

	public void setHzjs(Long hzjs){
		this.hzjs=hzjs;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public Long getCxhjywid(){
		return this.cxhjywid;
	}

	public void setCxhjywid(Long cxhjywid){
		this.cxhjywid=cxhjywid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}