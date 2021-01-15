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
*审核签发信息表
*/
@Entity
@Table(name="ZJSH_SHQFXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJSH_SHQFXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审核签发ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	shqfid;
	private Long	rynbid;
	private Long	nbslid;
	private String	shqflx;
	private String	shrq;
	private String	shrxm;
	private String	shdw;
	private String	shqk;
	private String	qfrq;
	private Long	qfrid;
	private String	qfdw;
	private String	qfdwjgdm;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;
	private String	jlbz;
	private Long	shrid;
	private Long	ryid;
	private Long	hhid;
	private Long	hhnbid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdssxq;
	private Long	mlpid;
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
	private String	slh;
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
	private String	slzt;

	public PoZJSH_SHQFXXB(){}

	public Long getShqfid(){
		return this.shqfid;
	}

	public void setShqfid(Long shqfid){
		this.shqfid=shqfid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getNbslid(){
		return this.nbslid;
	}

	public void setNbslid(Long nbslid){
		this.nbslid=nbslid;
	}

	public String getShqflx(){
		return this.shqflx;
	}

	public void setShqflx(String shqflx){
		this.shqflx=shqflx;
	}

	public String getShrq(){
		return this.shrq;
	}

	public void setShrq(String shrq){
		this.shrq=shrq;
	}

	public String getShrxm(){
		return this.shrxm;
	}

	public void setShrxm(String shrxm){
		this.shrxm=shrxm;
	}

	public String getShdw(){
		return this.shdw;
	}

	public void setShdw(String shdw){
		this.shdw=shdw;
	}

	public String getShqk(){
		return this.shqk;
	}

	public void setShqk(String shqk){
		this.shqk=shqk;
	}

	public String getQfrq(){
		return this.qfrq;
	}

	public void setQfrq(String qfrq){
		this.qfrq=qfrq;
	}

	public Long getQfrid(){
		return this.qfrid;
	}

	public void setQfrid(Long qfrid){
		this.qfrid=qfrid;
	}

	public String getQfdw(){
		return this.qfdw;
	}

	public void setQfdw(String qfdw){
		this.qfdw=qfdw;
	}

	public String getQfdwjgdm(){
		return this.qfdwjgdm;
	}

	public void setQfdwjgdm(String qfdwjgdm){
		this.qfdwjgdm=qfdwjgdm;
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

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public Long getShrid(){
		return this.shrid;
	}

	public void setShrid(Long shrid){
		this.shrid=shrid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
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

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
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

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
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

	public String getSlzt(){
		return this.slzt;
	}

	public void setSlzt(String slzt){
		this.slzt=slzt;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}