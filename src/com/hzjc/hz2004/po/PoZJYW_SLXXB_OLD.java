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
@Table(name="ZJYW_SLXXB_OLD" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_SLXXB_OLD implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

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
	private String	gmsfhm;
	private Long	nbsfzid;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdssxq;
	private Long	mlpnbid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private String	ywbz;
	private Long	czyid;
	private String	czsj;
	private String	dwdm;
	private String	sjrxm;
	private String	sjrlxdh;
	private String	sjryb;
	private String	sjrtxdz;
	private String	zzxxcwlb;
	private String	cwms;
	private String	jydw;
	private String	jyrxm;
	private String	jyrq;
	private String	cldw;
	private String	clqk;
	private String	clrq;
	private String	zlhkzt;
	private String	hksj;
	private String	bwbha;
	private String	bwbhb;
	private String	shrq;

	public PoZJYW_SLXXB_OLD(){}

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

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
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

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getMlph(){
		return this.mlph;
	}

	public void setMlph(String mlph){
		this.mlph=mlph;
	}

	public String getMlxz(){
		return this.mlxz;
	}

	public void setMlxz(String mlxz){
		this.mlxz=mlxz;
	}

	public String getPcs(){
		return this.pcs;
	}

	public void setPcs(String pcs){
		this.pcs=pcs;
	}

	public String getZrq(){
		return this.zrq;
	}

	public void setZrq(String zrq){
		this.zrq=zrq;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public String getJcwh(){
		return this.jcwh;
	}

	public void setJcwh(String jcwh){
		this.jcwh=jcwh;
	}

	public String getPxh(){
		return this.pxh;
	}

	public void setPxh(String pxh){
		this.pxh=pxh;
	}

	public String getYwbz(){
		return this.ywbz;
	}

	public void setYwbz(String ywbz){
		this.ywbz=ywbz;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
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

	public String getJyrxm(){
		return this.jyrxm;
	}

	public void setJyrxm(String jyrxm){
		this.jyrxm=jyrxm;
	}

	public String getJyrq(){
		return this.jyrq;
	}

	public void setJyrq(String jyrq){
		this.jyrq=jyrq;
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

	public String getClrq(){
		return this.clrq;
	}

	public void setClrq(String clrq){
		this.clrq=clrq;
	}

	public String getZlhkzt(){
		return this.zlhkzt;
	}

	public void setZlhkzt(String zlhkzt){
		this.zlhkzt=zlhkzt;
	}

	public String getHksj(){
		return this.hksj;
	}

	public void setHksj(String hksj){
		this.hksj=hksj;
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

	public String getShrq(){
		return this.shrq;
	}

	public void setShrq(String shrq){
		this.shrq=shrq;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}