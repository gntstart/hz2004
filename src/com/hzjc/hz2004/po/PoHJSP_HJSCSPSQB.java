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
*户籍删除审批申请表
*/
@Entity
@Table(name="HJSP_HJSCSPSQB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJSCSPSQB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spywid;
	private Long	bscryid;
	private String	csyy;
	private String	spsm;
	private Long	xydzid;
	private Long	spmbid;
	private String	spjg;
	private String	lsbz;
	private Long	hjywid;
	private String	sqsj;
	private Long	sqyhid;
	private String	hjscsm;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	
	public PoHJSP_HJSCSPSQB(){}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getSbryxm() {
		return sbryxm;
	}

	public void setSbryxm(String sbryxm) {
		this.sbryxm = sbryxm;
	}

	public String getSbrgmsfhm() {
		return sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm) {
		this.sbrgmsfhm = sbrgmsfhm;
	}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public Long getBscryid(){
		return this.bscryid;
	}

	public void setBscryid(Long bscryid){
		this.bscryid=bscryid;
	}

	public String getCsyy(){
		return this.csyy;
	}

	public void setCsyy(String csyy){
		this.csyy=csyy;
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

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getSqsj(){
		return this.sqsj;
	}

	public void setSqsj(String sqsj){
		this.sqsj=sqsj;
	}

	public Long getSqyhid(){
		return this.sqyhid;
	}

	public void setSqyhid(Long sqyhid){
		this.sqyhid=sqyhid;
	}

	public String getHjscsm(){
		return this.hjscsm;
	}

	public void setHjscsm(String hjscsm){
		this.hjscsm=hjscsm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}