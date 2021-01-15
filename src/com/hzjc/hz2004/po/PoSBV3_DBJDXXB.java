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

@Entity
@Table(name="SBV3_DBJDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSBV3_DBJDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*打包进度ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dbjdid;
	private String	pch;
	private String	ywlx;
	private Long	ywjls;
	private Long	ydbjls;
	private Long	ycjls;
	private Long	sfdbjs;
	private String	bz;
	private Long	bsbjls;
	private String	qssj;
	private String	jssj;
	private String	zfzt;
	private String	sfdyc;

	public PoSBV3_DBJDXXB(){}

	public Long getDbjdid(){
		return this.dbjdid;
	}

	public void setDbjdid(Long dbjdid){
		this.dbjdid=dbjdid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getYwjls(){
		return this.ywjls;
	}

	public void setYwjls(Long ywjls){
		this.ywjls=ywjls;
	}

	public Long getYdbjls(){
		return this.ydbjls;
	}

	public void setYdbjls(Long ydbjls){
		this.ydbjls=ydbjls;
	}

	public Long getYcjls(){
		return this.ycjls;
	}

	public void setYcjls(Long ycjls){
		this.ycjls=ycjls;
	}

	public Long getSfdbjs(){
		return this.sfdbjs;
	}

	public void setSfdbjs(Long sfdbjs){
		this.sfdbjs=sfdbjs;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public Long getBsbjls(){
		return this.bsbjls;
	}

	public void setBsbjls(Long bsbjls){
		this.bsbjls=bsbjls;
	}

	public String getQssj(){
		return this.qssj;
	}

	public void setQssj(String qssj){
		this.qssj=qssj;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public String getZfzt(){
		return this.zfzt;
	}

	public void setZfzt(String zfzt){
		this.zfzt=zfzt;
	}

	public String getSfdyc(){
		return this.sfdyc;
	}

	public void setSfdyc(String sfdyc){
		this.sfdyc=sfdyc;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}