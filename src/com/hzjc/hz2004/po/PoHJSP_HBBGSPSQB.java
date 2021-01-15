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
*户别变更审批申请表
*/
@Entity
@Table(name="HJSP_HBBGSPSQB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HBBGSPSQB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spywid;
	private Long	bgryid;
	private String	bgyy;
	private String	bgqhb;
	private String	bghhb;
	private String	spsm;
	private Long	xydzid;
	private Long	spmbid;
	private String	spjg;
	private String	lsbz;
	private Long	bghrynbid;
	private Long	hjywid;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	private Long	djrid;

	public PoHJSP_HBBGSPSQB(){}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public Long getBgryid(){
		return this.bgryid;
	}

	public void setBgryid(Long bgryid){
		this.bgryid=bgryid;
	}

	public String getBgyy(){
		return this.bgyy;
	}

	public void setBgyy(String bgyy){
		this.bgyy=bgyy;
	}

	public String getBgqhb(){
		return this.bgqhb;
	}

	public void setBgqhb(String bgqhb){
		this.bgqhb=bgqhb;
	}

	public String getBghhb(){
		return this.bghhb;
	}

	public void setBghhb(String bghhb){
		this.bghhb=bghhb;
	}

	public String getSpsm(){
		return this.spsm;
	}

	public void setSpsm(String spsm){
		this.spsm=spsm;
	}

	public Long getXydzid(){
		return this.xydzid;
	}

	public void setXydzid(Long xydzid){
		this.xydzid=xydzid;
	}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public String getSpjg(){
		return this.spjg;
	}

	public void setSpjg(String spjg){
		this.spjg=spjg;
	}

	public String getLsbz(){
		return this.lsbz;
	}

	public void setLsbz(String lsbz){
		this.lsbz=lsbz;
	}

	public Long getBghrynbid(){
		return this.bghrynbid;
	}

	public void setBghrynbid(Long bghrynbid){
		this.bghrynbid=bghrynbid;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
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

	public Long getDjrid(){
		return this.djrid;
	}

	public void setDjrid(Long djrid){
		this.djrid=djrid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}