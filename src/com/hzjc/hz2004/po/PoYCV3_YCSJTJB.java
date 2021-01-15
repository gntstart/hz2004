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
*异常数据统计表
*/
@Entity
@Table(name="YCV3_YCSJTJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYCV3_YCSJTJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*异常数据统计ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ycsjtjid;
	private Long	ryid;
	private Long	rynbid;
	private String	gmsfhm;
	private String	xm;
	private String	ssxq;
	private String	pcsdm;
	private String	ywlx;
	private Long	hjywid;
	private String	nbbm;
	private Long	cwlx;
	private String	cwms;
	private Long	cllx;
	private String	clsj;
	private Long	yclx;
	private String	slsj;
	private Long	cxbz;
	private String	pch;

	public PoYCV3_YCSJTJB(){}

	public Long getYcsjtjid(){
		return this.ycsjtjid;
	}

	public void setYcsjtjid(Long ycsjtjid){
		this.ycsjtjid=ycsjtjid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getPcsdm(){
		return this.pcsdm;
	}

	public void setPcsdm(String pcsdm){
		this.pcsdm=pcsdm;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getNbbm(){
		return this.nbbm;
	}

	public void setNbbm(String nbbm){
		this.nbbm=nbbm;
	}

	public Long getCwlx(){
		return this.cwlx;
	}

	public void setCwlx(Long cwlx){
		this.cwlx=cwlx;
	}

	public String getCwms(){
		return this.cwms;
	}

	public void setCwms(String cwms){
		this.cwms=cwms;
	}

	public Long getCllx(){
		return this.cllx;
	}

	public void setCllx(Long cllx){
		this.cllx=cllx;
	}

	public String getClsj(){
		return this.clsj;
	}

	public void setClsj(String clsj){
		this.clsj=clsj;
	}

	public Long getYclx(){
		return this.yclx;
	}

	public void setYclx(Long yclx){
		this.yclx=yclx;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public Long getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(Long cxbz){
		this.cxbz=cxbz;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}