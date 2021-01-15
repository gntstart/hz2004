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
*重号处理信息表
*/
@Entity
@Table(name="HJYW_CHCLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_CHCLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*重号ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	chid;
	private Long	ryid;
	private String	chsfhm;
	private Long	bchryid;
	private String	bchrxm;
	private String	bchrszpcs;
	private String	bchrzz;
	private String	clfs;
	private Long	hjywid;
	private Long	clhjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxhjywid;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	private String	slsj;
	private String	sldw;
	private Long	slrid;

	public PoHJYW_CHCLXXB(){}

	public Long getChid(){
		return this.chid;
	}

	public void setChid(Long chid){
		this.chid=chid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getChsfhm(){
		return this.chsfhm;
	}

	public void setChsfhm(String chsfhm){
		this.chsfhm=chsfhm;
	}

	public Long getBchryid(){
		return this.bchryid;
	}

	public void setBchryid(Long bchryid){
		this.bchryid=bchryid;
	}

	public String getBchrxm(){
		return this.bchrxm;
	}

	public void setBchrxm(String bchrxm){
		this.bchrxm=bchrxm;
	}

	public String getBchrszpcs(){
		return this.bchrszpcs;
	}

	public void setBchrszpcs(String bchrszpcs){
		this.bchrszpcs=bchrszpcs;
	}

	public String getBchrzz(){
		return this.bchrzz;
	}

	public void setBchrzz(String bchrzz){
		this.bchrzz=bchrzz;
	}

	public String getClfs(){
		return this.clfs;
	}

	public void setClfs(String clfs){
		this.clfs=clfs;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public Long getClhjywid(){
		return this.clhjywid;
	}

	public void setClhjywid(Long clhjywid){
		this.clhjywid=clhjywid;
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

	public String getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(String sbsj){
		this.sbsj=sbsj;
	}

	public String getSbryxm(){
		return this.sbryxm;
	}

	public void setSbryxm(String sbryxm){
		this.sbryxm=sbryxm;
	}

	public String getSbrgmsfhm(){
		return this.sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm){
		this.sbrgmsfhm=sbrgmsfhm;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public Long getSlrid(){
		return this.slrid;
	}

	public void setSlrid(Long slrid){
		this.slrid=slrid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}