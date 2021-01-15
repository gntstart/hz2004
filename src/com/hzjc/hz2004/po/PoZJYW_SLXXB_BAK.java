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
*受理信息表
*/
@Entity
@Table(name="ZJYW_SLXXB_BAK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_SLXXB_BAK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*内部受理ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	nbslid;
	private String	slh;
	private Long	ryid;
	private Long	rynbid;
	private Long	zpid;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	zz;
	private String	slyy;
	private String	zzlx;
	private String	lqfs;
	private String	sflx;
	private Double	sfje;
	private String	sjblsh;
	private String	slzt;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;
	private Long	tbbz;
	private String	bwbha;
	private String	bwbhb;

	public PoZJYW_SLXXB_BAK(){}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
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

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public String getQfjg(){
		return this.qfjg;
	}

	public void setQfjg(String qfjg){
		this.qfjg=qfjg;
	}

	public String getYxqxqsrq(){
		return this.yxqxqsrq;
	}

	public void setYxqxqsrq(String yxqxqsrq){
		this.yxqxqsrq=yxqxqsrq;
	}

	public String getYxqxjzrq(){
		return this.yxqxjzrq;
	}

	public void setYxqxjzrq(String yxqxjzrq){
		this.yxqxjzrq=yxqxjzrq;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getSlyy(){
		return this.slyy;
	}

	public void setSlyy(String slyy){
		this.slyy=slyy;
	}

	public String getZzlx(){
		return this.zzlx;
	}

	public void setZzlx(String zzlx){
		this.zzlx=zzlx;
	}

	public String getLqfs(){
		return this.lqfs;
	}

	public void setLqfs(String lqfs){
		this.lqfs=lqfs;
	}

	public String getSflx(){
		return this.sflx;
	}

	public void setSflx(String sflx){
		this.sflx=sflx;
	}

	public Double getSfje(){
		return this.sfje;
	}

	public void setSfje(Double sfje){
		this.sfje=sfje;
	}

	public String getSjblsh(){
		return this.sjblsh;
	}

	public void setSjblsh(String sjblsh){
		this.sjblsh=sjblsh;
	}

	public String getSlzt(){
		return this.slzt;
	}

	public void setSlzt(String slzt){
		this.slzt=slzt;
	}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
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

	public Long getCxzjywid(){
		return this.cxzjywid;
	}

	public void setCxzjywid(Long cxzjywid){
		this.cxzjywid=cxzjywid;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getBwbha(){
		return this.bwbha;
	}

	public void setBwbha(String bwbha){
		this.bwbha=bwbha;
	}

	public String getBwbhb(){
		return this.bwbhb;
	}

	public void setBwbhb(String bwbhb){
		this.bwbhb=bwbhb;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}