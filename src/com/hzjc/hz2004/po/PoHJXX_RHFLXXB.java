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
*人户分离信息表
*/
@Entity
@Table(name="HJXX_RHFLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_RHFLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人户分离ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rhflid;
	private Long	ryid;
	private Long	rynbid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	csrq;
	private String	rhfl_ssxq;
	private String	rhfl_jlx;
	private String	rhfl_mlph;
	private String	rhfl_mlxz;
	private String	rhfl_pcs;
	private String	rhfl_zrq;
	private String	rhfl_xzjd;
	private String	rhfl_jcwh;
	private String	slsj;
	private String	sldw;
	private Long	slrid;
	private String	zxsj;
	private String	zxdw;
	private Long	zxrid;
	private String	bz;
	private Long	cjhjywid;
	private Long	cchjywid;
	private String	rhflzt;
	private String	xzdfw;
	private String	zxyy;

	public PoHJXX_RHFLXXB(){}

	public Long getRhflid(){
		return this.rhflid;
	}

	public void setRhflid(Long rhflid){
		this.rhflid=rhflid;
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

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getRhfl_ssxq(){
		return this.rhfl_ssxq;
	}

	public void setRhfl_ssxq(String rhfl_ssxq){
		this.rhfl_ssxq=rhfl_ssxq;
	}

	public String getRhfl_jlx(){
		return this.rhfl_jlx;
	}

	public void setRhfl_jlx(String rhfl_jlx){
		this.rhfl_jlx=rhfl_jlx;
	}

	public String getRhfl_mlph(){
		return this.rhfl_mlph;
	}

	public void setRhfl_mlph(String rhfl_mlph){
		this.rhfl_mlph=rhfl_mlph;
	}

	public String getRhfl_mlxz(){
		return this.rhfl_mlxz;
	}

	public void setRhfl_mlxz(String rhfl_mlxz){
		this.rhfl_mlxz=rhfl_mlxz;
	}

	public String getRhfl_pcs(){
		return this.rhfl_pcs;
	}

	public void setRhfl_pcs(String rhfl_pcs){
		this.rhfl_pcs=rhfl_pcs;
	}

	public String getRhfl_zrq(){
		return this.rhfl_zrq;
	}

	public void setRhfl_zrq(String rhfl_zrq){
		this.rhfl_zrq=rhfl_zrq;
	}

	public String getRhfl_xzjd(){
		return this.rhfl_xzjd;
	}

	public void setRhfl_xzjd(String rhfl_xzjd){
		this.rhfl_xzjd=rhfl_xzjd;
	}

	public String getRhfl_jcwh(){
		return this.rhfl_jcwh;
	}

	public void setRhfl_jcwh(String rhfl_jcwh){
		this.rhfl_jcwh=rhfl_jcwh;
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

	public String getZxsj(){
		return this.zxsj;
	}

	public void setZxsj(String zxsj){
		this.zxsj=zxsj;
	}

	public String getZxdw(){
		return this.zxdw;
	}

	public void setZxdw(String zxdw){
		this.zxdw=zxdw;
	}

	public Long getZxrid(){
		return this.zxrid;
	}

	public void setZxrid(Long zxrid){
		this.zxrid=zxrid;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public Long getCjhjywid(){
		return this.cjhjywid;
	}

	public void setCjhjywid(Long cjhjywid){
		this.cjhjywid=cjhjywid;
	}

	public Long getCchjywid(){
		return this.cchjywid;
	}

	public void setCchjywid(Long cchjywid){
		this.cchjywid=cchjywid;
	}

	public String getRhflzt(){
		return this.rhflzt;
	}

	public void setRhflzt(String rhflzt){
		this.rhflzt=rhflzt;
	}

	public String getXzdfw(){
		return this.xzdfw;
	}

	public void setXzdfw(String xzdfw){
		this.xzdfw=xzdfw;
	}

	public String getZxyy(){
		return this.zxyy;
	}

	public void setZxyy(String zxyy){
		this.zxyy=zxyy;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}