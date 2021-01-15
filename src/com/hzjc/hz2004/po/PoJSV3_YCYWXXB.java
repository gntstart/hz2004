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
*接收异常业务数据表
*/
@Entity
@Table(name="JSV3_YCYWXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_YCYWXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接收异常业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jsycywid;
	private Long	pcid;
	private String	pch;
	private Long	jsywid;
	private String	dsdm;
	private String	ssxq;
	private String	gmsfhm;
	private String	xm;
	private String	slsj;
	private String	ywlx;
	private Long	cwbz;
	private String	clqk;
	private String	bwlx;

	public PoJSV3_YCYWXXB(){}

	public Long getJsycywid(){
		return this.jsycywid;
	}

	public void setJsycywid(Long jsycywid){
		this.jsycywid=jsycywid;
	}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public Long getJsywid(){
		return this.jsywid;
	}

	public void setJsywid(Long jsywid){
		this.jsywid=jsywid;
	}

	public String getDsdm(){
		return this.dsdm;
	}

	public void setDsdm(String dsdm){
		this.dsdm=dsdm;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
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

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getCwbz(){
		return this.cwbz;
	}

	public void setCwbz(Long cwbz){
		this.cwbz=cwbz;
	}

	public String getClqk(){
		return this.clqk;
	}

	public void setClqk(String clqk){
		this.clqk=clqk;
	}

	public String getBwlx(){
		return this.bwlx;
	}

	public void setBwlx(String bwlx){
		this.bwlx=bwlx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}