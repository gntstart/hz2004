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
*异地证件申办信息表
*/
@Entity
@Table(name="YDZJ_SBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYDZJ_SBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*申办信息ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	sbxxid;
	private String	ssxq;
	private String	slh;
	private String	gmsfhm;
	private String	xm;
	private String	cym;
	private String	xb;
	private String	csrq;
	private String	mz;
	private String	zz;
	private String	slyy;
	private String	zzlx;
	private String	lzfs;
	private String	sjrxm;
	private String	sjrlxdh;
	private String	sjryb;
	private String	sjrtxdz;
	private Long	zpid;
	private Double	zzfy;
	private String	sbddm;
	private String	sbdmc;
	private String	czyip;
	private Long	czyid;
	private String	czyxm;
	private String	czsj;
	private String	slzt;
	private Long	tbbz;
	private String	bwbha;
	private String	tbsj;
	private String	bwbhb;
	private String	hksj;
	private String	zlhkzt;
	private String	zzxxcwlb;
	private String	cwms;
	private String	jydw;
	private String	jyr;
	private String	jysj;
	private String	fkxxbwbh;
	private String	clr;
	private String	cldw;
	private String	clqk;
	private String	clsj;
	private String	lzsj;
	private Long	lzslr;
	private String	lzrxm;
	private String	lzrgmsfhm;
	private String	lzrdh;
	private Long	lztbbz;
	private String	lzxxbwbh;
	private Long	sbh;
	private String	lzdbsj;
	private String	lzqrms;
	private String	bz;
	private String	sblx;

	public PoYDZJ_SBXXB(){}

	public Long getSbxxid(){
		return this.sbxxid;
	}

	public void setSbxxid(Long sbxxid){
		this.sbxxid=sbxxid;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
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

	public String getCym(){
		return this.cym;
	}

	public void setCym(String cym){
		this.cym=cym;
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

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
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

	public String getLzfs(){
		return this.lzfs;
	}

	public void setLzfs(String lzfs){
		this.lzfs=lzfs;
	}

	public String getSjrxm(){
		return this.sjrxm;
	}

	public void setSjrxm(String sjrxm){
		this.sjrxm=sjrxm;
	}

	public String getSjrlxdh(){
		return this.sjrlxdh;
	}

	public void setSjrlxdh(String sjrlxdh){
		this.sjrlxdh=sjrlxdh;
	}

	public String getSjryb(){
		return this.sjryb;
	}

	public void setSjryb(String sjryb){
		this.sjryb=sjryb;
	}

	public String getSjrtxdz(){
		return this.sjrtxdz;
	}

	public void setSjrtxdz(String sjrtxdz){
		this.sjrtxdz=sjrtxdz;
	}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public Double getZzfy(){
		return this.zzfy;
	}

	public void setZzfy(Double zzfy){
		this.zzfy=zzfy;
	}

	public String getSbddm(){
		return this.sbddm;
	}

	public void setSbddm(String sbddm){
		this.sbddm=sbddm;
	}

	public String getSbdmc(){
		return this.sbdmc;
	}

	public void setSbdmc(String sbdmc){
		this.sbdmc=sbdmc;
	}

	public String getCzyip(){
		return this.czyip;
	}

	public void setCzyip(String czyip){
		this.czyip=czyip;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzyxm(){
		return this.czyxm;
	}

	public void setCzyxm(String czyxm){
		this.czyxm=czyxm;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getSlzt(){
		return this.slzt;
	}

	public void setSlzt(String slzt){
		this.slzt=slzt;
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

	public String getTbsj(){
		return this.tbsj;
	}

	public void setTbsj(String tbsj){
		this.tbsj=tbsj;
	}

	public String getBwbhb(){
		return this.bwbhb;
	}

	public void setBwbhb(String bwbhb){
		this.bwbhb=bwbhb;
	}

	public String getHksj(){
		return this.hksj;
	}

	public void setHksj(String hksj){
		this.hksj=hksj;
	}

	public String getZlhkzt(){
		return this.zlhkzt;
	}

	public void setZlhkzt(String zlhkzt){
		this.zlhkzt=zlhkzt;
	}

	public String getZzxxcwlb(){
		return this.zzxxcwlb;
	}

	public void setZzxxcwlb(String zzxxcwlb){
		this.zzxxcwlb=zzxxcwlb;
	}

	public String getCwms(){
		return this.cwms;
	}

	public void setCwms(String cwms){
		this.cwms=cwms;
	}

	public String getJydw(){
		return this.jydw;
	}

	public void setJydw(String jydw){
		this.jydw=jydw;
	}

	public String getJyr(){
		return this.jyr;
	}

	public void setJyr(String jyr){
		this.jyr=jyr;
	}

	public String getJysj(){
		return this.jysj;
	}

	public void setJysj(String jysj){
		this.jysj=jysj;
	}

	public String getFkxxbwbh(){
		return this.fkxxbwbh;
	}

	public void setFkxxbwbh(String fkxxbwbh){
		this.fkxxbwbh=fkxxbwbh;
	}

	public String getClr(){
		return this.clr;
	}

	public void setClr(String clr){
		this.clr=clr;
	}

	public String getCldw(){
		return this.cldw;
	}

	public void setCldw(String cldw){
		this.cldw=cldw;
	}

	public String getClqk(){
		return this.clqk;
	}

	public void setClqk(String clqk){
		this.clqk=clqk;
	}

	public String getClsj(){
		return this.clsj;
	}

	public void setClsj(String clsj){
		this.clsj=clsj;
	}

	public String getLzsj(){
		return this.lzsj;
	}

	public void setLzsj(String lzsj){
		this.lzsj=lzsj;
	}

	public Long getLzslr(){
		return this.lzslr;
	}

	public void setLzslr(Long lzslr){
		this.lzslr=lzslr;
	}

	public String getLzrxm(){
		return this.lzrxm;
	}

	public void setLzrxm(String lzrxm){
		this.lzrxm=lzrxm;
	}

	public String getLzrgmsfhm(){
		return this.lzrgmsfhm;
	}

	public void setLzrgmsfhm(String lzrgmsfhm){
		this.lzrgmsfhm=lzrgmsfhm;
	}

	public String getLzrdh(){
		return this.lzrdh;
	}

	public void setLzrdh(String lzrdh){
		this.lzrdh=lzrdh;
	}

	public Long getLztbbz(){
		return this.lztbbz;
	}

	public void setLztbbz(Long lztbbz){
		this.lztbbz=lztbbz;
	}

	public String getLzxxbwbh(){
		return this.lzxxbwbh;
	}

	public void setLzxxbwbh(String lzxxbwbh){
		this.lzxxbwbh=lzxxbwbh;
	}

	public Long getSbh(){
		return this.sbh;
	}

	public void setSbh(Long sbh){
		this.sbh=sbh;
	}

	public String getLzdbsj(){
		return this.lzdbsj;
	}

	public void setLzdbsj(String lzdbsj){
		this.lzdbsj=lzdbsj;
	}

	public String getLzqrms(){
		return this.lzqrms;
	}

	public void setLzqrms(String lzqrms){
		this.lzqrms=lzqrms;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getSblx(){
		return this.sblx;
	}

	public void setSblx(String sblx){
		this.sblx=sblx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}