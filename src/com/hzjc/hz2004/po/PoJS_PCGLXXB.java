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
*接收批次管理表
*/
@Entity
@Table(name="JS_PCGLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJS_PCGLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*批次ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	pcid;
	private String	pch;
	private String	dsdm;
	private String	dwdm;
	private String	dwmc;
	private Long	djrid;
	private String	djrxm;
	private String	djsj;
	private String	ywqssj;
	private String	ywjzsj;
	private String	pczt;
	private String	jdzt;
	private String	dsdbjssj;
	private String	dzbscsj;
	private Long	ywzs;
	private Long	bwzs;
	private String	bz;

	public PoJS_PCGLXXB(){}

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

	public String getDsdm(){
		return this.dsdm;
	}

	public void setDsdm(String dsdm){
		this.dsdm=dsdm;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getDwmc(){
		return this.dwmc;
	}

	public void setDwmc(String dwmc){
		this.dwmc=dwmc;
	}

	public Long getDjrid(){
		return this.djrid;
	}

	public void setDjrid(Long djrid){
		this.djrid=djrid;
	}

	public String getDjrxm(){
		return this.djrxm;
	}

	public void setDjrxm(String djrxm){
		this.djrxm=djrxm;
	}

	public String getDjsj(){
		return this.djsj;
	}

	public void setDjsj(String djsj){
		this.djsj=djsj;
	}

	public String getYwqssj(){
		return this.ywqssj;
	}

	public void setYwqssj(String ywqssj){
		this.ywqssj=ywqssj;
	}

	public String getYwjzsj(){
		return this.ywjzsj;
	}

	public void setYwjzsj(String ywjzsj){
		this.ywjzsj=ywjzsj;
	}

	public String getPczt(){
		return this.pczt;
	}

	public void setPczt(String pczt){
		this.pczt=pczt;
	}

	public String getJdzt(){
		return this.jdzt;
	}

	public void setJdzt(String jdzt){
		this.jdzt=jdzt;
	}

	public String getDsdbjssj(){
		return this.dsdbjssj;
	}

	public void setDsdbjssj(String dsdbjssj){
		this.dsdbjssj=dsdbjssj;
	}

	public String getDzbscsj(){
		return this.dzbscsj;
	}

	public void setDzbscsj(String dzbscsj){
		this.dzbscsj=dzbscsj;
	}

	public Long getYwzs(){
		return this.ywzs;
	}

	public void setYwzs(Long ywzs){
		this.ywzs=ywzs;
	}

	public Long getBwzs(){
		return this.bwzs;
	}

	public void setBwzs(Long bwzs){
		this.bwzs=bwzs;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}