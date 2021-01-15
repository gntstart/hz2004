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
*接收业务临时表
*/
@Entity
@Table(name="JSV3_TMP_YW" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_TMP_YW implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接收业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jsywid;
	private Long	pcid;
	private String	pch;
	private String	qcbz;
	private Long	tbbz;
	private Long	ywclsx;
	private String	dsdm;
	private String	ssxq;
	private String	gmsfhm;
	private String	xm;
	private String	cym;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdgj;
	private String	csdssx;
	private String	csdxz;
	private String	jggj;
	private String	jgssx;
	private String	jhryhm;
	private String	jhryxm;
	private String	jhrygx;
	private String	jhrehm;
	private String	jhrexm;
	private String	jhregx;
	private String	fqhm;
	private String	fqxm;
	private String	mqhm;
	private String	mqxm;
	private String	pohm;
	private String	poxm;
	private String	zjxy;
	private String	whcd;
	private String	hyzk;
	private String	byzk;
	private String	sg;
	private String	xx;
	private String	zy;
	private String	zylb;
	private String	lxdh;
	private String	fwcs;
	private String	xxjb;
	private String	zz;
	private String	hsqlbs;
	private String	hyqlbs;
	private String	hdqlgj;
	private String	hdqlssx;
	private String	hdqlxz;
	private String	swrq;
	private String	swzxlb;
	private String	qcrq;
	private String	qczxlb;
	private String	qwdgj;
	private String	qwdssx;
	private String	qwdxz;
	private String	csdjlb;
	private String	cszmbh;
	private String	swzmbh;
	private String	cgqxzqh;
	private String	lzgj;
	private String	cjqxzqh;
	private String	cjxzqh;
	private String	szqxzqh;
	private String	szrq;
	private String	dbqxzqh;
	private String	qrrq;
	private String	qrlb;
	private String	qcdssxq;
	private String	qcdxz;
	private String	qyzbh;
	private String	zqzbh;
	private String	qyfw;
	private String	zzbdrq;
	private String	zzbdlb;
	private String	yzz;
	private String	bggzrq;
	private String	bggzlb;
	private String	bggzxm;
	private String	bggzxmbh;
	private String	bggzqnr;
	private String	bggzhnr;
	private String	hh;
	private String	hlx;
	private String	yhzgx;
	private String	hcybdlx;
	private String	slh;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	slyy;
	private String	zjlqrq;
	private String	zjlqrxm;
	private String	zjlqrhm;
	private String	zjffrxm;
	private String	zjffrq;
	private String	zjddrq;
	private String	ygmsfhm;
	private String	xgmsfhm;
	private String	zxbs;
	private Long	xpid;
	private String	ywlx;
	private String	slsj;
	private String	xzjdmc;
	private String	xjgajgmc;
	private String	xjgajgjgdm;
	private String	pcsmc;
	private String	pcsjgdm;
	private String	xxtqsj;
	private Long	ryid;
	private Long	bwxxid;
	private String	bwbh;
	private Long	ywid;
	private String	bwlx;
	private String	whsj;
	private Long	rkid;
	private String	jlx;
	private String	jwh;
	private String	jlxmc;
	private String	jwhmc;
	private String	xzjd;
	private String	mlph;
	private String	mlxz;

	public PoJSV3_TMP_YW(){}

	public Long getJsywid(){
		return this.jsywid;
	}

	public void setJsywid(Long jsywid){
		this.jsywid=jsywid;
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

	public String getQcbz(){
		return this.qcbz;
	}

	public void setQcbz(String qcbz){
		this.qcbz=qcbz;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public Long getYwclsx(){
		return this.ywclsx;
	}

	public void setYwclsx(Long ywclsx){
		this.ywclsx=ywclsx;
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

	public String getCsdgj(){
		return this.csdgj;
	}

	public void setCsdgj(String csdgj){
		this.csdgj=csdgj;
	}

	public String getCsdssx(){
		return this.csdssx;
	}

	public void setCsdssx(String csdssx){
		this.csdssx=csdssx;
	}

	public String getCsdxz(){
		return this.csdxz;
	}

	public void setCsdxz(String csdxz){
		this.csdxz=csdxz;
	}

	public String getJggj(){
		return this.jggj;
	}

	public void setJggj(String jggj){
		this.jggj=jggj;
	}

	public String getJgssx(){
		return this.jgssx;
	}

	public void setJgssx(String jgssx){
		this.jgssx=jgssx;
	}

	public String getJhryhm(){
		return this.jhryhm;
	}

	public void setJhryhm(String jhryhm){
		this.jhryhm=jhryhm;
	}

	public String getJhryxm(){
		return this.jhryxm;
	}

	public void setJhryxm(String jhryxm){
		this.jhryxm=jhryxm;
	}

	public String getJhrygx(){
		return this.jhrygx;
	}

	public void setJhrygx(String jhrygx){
		this.jhrygx=jhrygx;
	}

	public String getJhrehm(){
		return this.jhrehm;
	}

	public void setJhrehm(String jhrehm){
		this.jhrehm=jhrehm;
	}

	public String getJhrexm(){
		return this.jhrexm;
	}

	public void setJhrexm(String jhrexm){
		this.jhrexm=jhrexm;
	}

	public String getJhregx(){
		return this.jhregx;
	}

	public void setJhregx(String jhregx){
		this.jhregx=jhregx;
	}

	public String getFqhm(){
		return this.fqhm;
	}

	public void setFqhm(String fqhm){
		this.fqhm=fqhm;
	}

	public String getFqxm(){
		return this.fqxm;
	}

	public void setFqxm(String fqxm){
		this.fqxm=fqxm;
	}

	public String getMqhm(){
		return this.mqhm;
	}

	public void setMqhm(String mqhm){
		this.mqhm=mqhm;
	}

	public String getMqxm(){
		return this.mqxm;
	}

	public void setMqxm(String mqxm){
		this.mqxm=mqxm;
	}

	public String getPohm(){
		return this.pohm;
	}

	public void setPohm(String pohm){
		this.pohm=pohm;
	}

	public String getPoxm(){
		return this.poxm;
	}

	public void setPoxm(String poxm){
		this.poxm=poxm;
	}

	public String getZjxy(){
		return this.zjxy;
	}

	public void setZjxy(String zjxy){
		this.zjxy=zjxy;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getHyzk(){
		return this.hyzk;
	}

	public void setHyzk(String hyzk){
		this.hyzk=hyzk;
	}

	public String getByzk(){
		return this.byzk;
	}

	public void setByzk(String byzk){
		this.byzk=byzk;
	}

	public String getSg(){
		return this.sg;
	}

	public void setSg(String sg){
		this.sg=sg;
	}

	public String getXx(){
		return this.xx;
	}

	public void setXx(String xx){
		this.xx=xx;
	}

	public String getZy(){
		return this.zy;
	}

	public void setZy(String zy){
		this.zy=zy;
	}

	public String getZylb(){
		return this.zylb;
	}

	public void setZylb(String zylb){
		this.zylb=zylb;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getFwcs(){
		return this.fwcs;
	}

	public void setFwcs(String fwcs){
		this.fwcs=fwcs;
	}

	public String getXxjb(){
		return this.xxjb;
	}

	public void setXxjb(String xxjb){
		this.xxjb=xxjb;
	}

	public String getZz(){
		return this.zz;
	}

	public void setZz(String zz){
		this.zz=zz;
	}

	public String getHsqlbs(){
		return this.hsqlbs;
	}

	public void setHsqlbs(String hsqlbs){
		this.hsqlbs=hsqlbs;
	}

	public String getHyqlbs(){
		return this.hyqlbs;
	}

	public void setHyqlbs(String hyqlbs){
		this.hyqlbs=hyqlbs;
	}

	public String getHdqlgj(){
		return this.hdqlgj;
	}

	public void setHdqlgj(String hdqlgj){
		this.hdqlgj=hdqlgj;
	}

	public String getHdqlssx(){
		return this.hdqlssx;
	}

	public void setHdqlssx(String hdqlssx){
		this.hdqlssx=hdqlssx;
	}

	public String getHdqlxz(){
		return this.hdqlxz;
	}

	public void setHdqlxz(String hdqlxz){
		this.hdqlxz=hdqlxz;
	}

	public String getSwrq(){
		return this.swrq;
	}

	public void setSwrq(String swrq){
		this.swrq=swrq;
	}

	public String getSwzxlb(){
		return this.swzxlb;
	}

	public void setSwzxlb(String swzxlb){
		this.swzxlb=swzxlb;
	}

	public String getQcrq(){
		return this.qcrq;
	}

	public void setQcrq(String qcrq){
		this.qcrq=qcrq;
	}

	public String getQczxlb(){
		return this.qczxlb;
	}

	public void setQczxlb(String qczxlb){
		this.qczxlb=qczxlb;
	}

	public String getQwdgj(){
		return this.qwdgj;
	}

	public void setQwdgj(String qwdgj){
		this.qwdgj=qwdgj;
	}

	public String getQwdssx(){
		return this.qwdssx;
	}

	public void setQwdssx(String qwdssx){
		this.qwdssx=qwdssx;
	}

	public String getQwdxz(){
		return this.qwdxz;
	}

	public void setQwdxz(String qwdxz){
		this.qwdxz=qwdxz;
	}

	public String getCsdjlb(){
		return this.csdjlb;
	}

	public void setCsdjlb(String csdjlb){
		this.csdjlb=csdjlb;
	}

	public String getCszmbh(){
		return this.cszmbh;
	}

	public void setCszmbh(String cszmbh){
		this.cszmbh=cszmbh;
	}

	public String getSwzmbh(){
		return this.swzmbh;
	}

	public void setSwzmbh(String swzmbh){
		this.swzmbh=swzmbh;
	}

	public String getCgqxzqh(){
		return this.cgqxzqh;
	}

	public void setCgqxzqh(String cgqxzqh){
		this.cgqxzqh=cgqxzqh;
	}

	public String getLzgj(){
		return this.lzgj;
	}

	public void setLzgj(String lzgj){
		this.lzgj=lzgj;
	}

	public String getCjqxzqh(){
		return this.cjqxzqh;
	}

	public void setCjqxzqh(String cjqxzqh){
		this.cjqxzqh=cjqxzqh;
	}

	public String getCjxzqh(){
		return this.cjxzqh;
	}

	public void setCjxzqh(String cjxzqh){
		this.cjxzqh=cjxzqh;
	}

	public String getSzqxzqh(){
		return this.szqxzqh;
	}

	public void setSzqxzqh(String szqxzqh){
		this.szqxzqh=szqxzqh;
	}

	public String getSzrq(){
		return this.szrq;
	}

	public void setSzrq(String szrq){
		this.szrq=szrq;
	}

	public String getDbqxzqh(){
		return this.dbqxzqh;
	}

	public void setDbqxzqh(String dbqxzqh){
		this.dbqxzqh=dbqxzqh;
	}

	public String getQrrq(){
		return this.qrrq;
	}

	public void setQrrq(String qrrq){
		this.qrrq=qrrq;
	}

	public String getQrlb(){
		return this.qrlb;
	}

	public void setQrlb(String qrlb){
		this.qrlb=qrlb;
	}

	public String getQcdssxq(){
		return this.qcdssxq;
	}

	public void setQcdssxq(String qcdssxq){
		this.qcdssxq=qcdssxq;
	}

	public String getQcdxz(){
		return this.qcdxz;
	}

	public void setQcdxz(String qcdxz){
		this.qcdxz=qcdxz;
	}

	public String getQyzbh(){
		return this.qyzbh;
	}

	public void setQyzbh(String qyzbh){
		this.qyzbh=qyzbh;
	}

	public String getZqzbh(){
		return this.zqzbh;
	}

	public void setZqzbh(String zqzbh){
		this.zqzbh=zqzbh;
	}

	public String getQyfw(){
		return this.qyfw;
	}

	public void setQyfw(String qyfw){
		this.qyfw=qyfw;
	}

	public String getZzbdrq(){
		return this.zzbdrq;
	}

	public void setZzbdrq(String zzbdrq){
		this.zzbdrq=zzbdrq;
	}

	public String getZzbdlb(){
		return this.zzbdlb;
	}

	public void setZzbdlb(String zzbdlb){
		this.zzbdlb=zzbdlb;
	}

	public String getYzz(){
		return this.yzz;
	}

	public void setYzz(String yzz){
		this.yzz=yzz;
	}

	public String getBggzrq(){
		return this.bggzrq;
	}

	public void setBggzrq(String bggzrq){
		this.bggzrq=bggzrq;
	}

	public String getBggzlb(){
		return this.bggzlb;
	}

	public void setBggzlb(String bggzlb){
		this.bggzlb=bggzlb;
	}

	public String getBggzxm(){
		return this.bggzxm;
	}

	public void setBggzxm(String bggzxm){
		this.bggzxm=bggzxm;
	}

	public String getBggzxmbh(){
		return this.bggzxmbh;
	}

	public void setBggzxmbh(String bggzxmbh){
		this.bggzxmbh=bggzxmbh;
	}

	public String getBggzqnr(){
		return this.bggzqnr;
	}

	public void setBggzqnr(String bggzqnr){
		this.bggzqnr=bggzqnr;
	}

	public String getBggzhnr(){
		return this.bggzhnr;
	}

	public void setBggzhnr(String bggzhnr){
		this.bggzhnr=bggzhnr;
	}

	public String getHh(){
		return this.hh;
	}

	public void setHh(String hh){
		this.hh=hh;
	}

	public String getHlx(){
		return this.hlx;
	}

	public void setHlx(String hlx){
		this.hlx=hlx;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public String getHcybdlx(){
		return this.hcybdlx;
	}

	public void setHcybdlx(String hcybdlx){
		this.hcybdlx=hcybdlx;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
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

	public String getSlyy(){
		return this.slyy;
	}

	public void setSlyy(String slyy){
		this.slyy=slyy;
	}

	public String getZjlqrq(){
		return this.zjlqrq;
	}

	public void setZjlqrq(String zjlqrq){
		this.zjlqrq=zjlqrq;
	}

	public String getZjlqrxm(){
		return this.zjlqrxm;
	}

	public void setZjlqrxm(String zjlqrxm){
		this.zjlqrxm=zjlqrxm;
	}

	public String getZjlqrhm(){
		return this.zjlqrhm;
	}

	public void setZjlqrhm(String zjlqrhm){
		this.zjlqrhm=zjlqrhm;
	}

	public String getZjffrxm(){
		return this.zjffrxm;
	}

	public void setZjffrxm(String zjffrxm){
		this.zjffrxm=zjffrxm;
	}

	public String getZjffrq(){
		return this.zjffrq;
	}

	public void setZjffrq(String zjffrq){
		this.zjffrq=zjffrq;
	}

	public String getZjddrq(){
		return this.zjddrq;
	}

	public void setZjddrq(String zjddrq){
		this.zjddrq=zjddrq;
	}

	public String getYgmsfhm(){
		return this.ygmsfhm;
	}

	public void setYgmsfhm(String ygmsfhm){
		this.ygmsfhm=ygmsfhm;
	}

	public String getXgmsfhm(){
		return this.xgmsfhm;
	}

	public void setXgmsfhm(String xgmsfhm){
		this.xgmsfhm=xgmsfhm;
	}

	public String getZxbs(){
		return this.zxbs;
	}

	public void setZxbs(String zxbs){
		this.zxbs=zxbs;
	}

	public Long getXpid(){
		return this.xpid;
	}

	public void setXpid(Long xpid){
		this.xpid=xpid;
	}

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getXzjdmc(){
		return this.xzjdmc;
	}

	public void setXzjdmc(String xzjdmc){
		this.xzjdmc=xzjdmc;
	}

	public String getXjgajgmc(){
		return this.xjgajgmc;
	}

	public void setXjgajgmc(String xjgajgmc){
		this.xjgajgmc=xjgajgmc;
	}

	public String getXjgajgjgdm(){
		return this.xjgajgjgdm;
	}

	public void setXjgajgjgdm(String xjgajgjgdm){
		this.xjgajgjgdm=xjgajgjgdm;
	}

	public String getPcsmc(){
		return this.pcsmc;
	}

	public void setPcsmc(String pcsmc){
		this.pcsmc=pcsmc;
	}

	public String getPcsjgdm(){
		return this.pcsjgdm;
	}

	public void setPcsjgdm(String pcsjgdm){
		this.pcsjgdm=pcsjgdm;
	}

	public String getXxtqsj(){
		return this.xxtqsj;
	}

	public void setXxtqsj(String xxtqsj){
		this.xxtqsj=xxtqsj;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getBwxxid(){
		return this.bwxxid;
	}

	public void setBwxxid(Long bwxxid){
		this.bwxxid=bwxxid;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public Long getYwid(){
		return this.ywid;
	}

	public void setYwid(Long ywid){
		this.ywid=ywid;
	}

	public String getBwlx(){
		return this.bwlx;
	}

	public void setBwlx(String bwlx){
		this.bwlx=bwlx;
	}

	public String getWhsj(){
		return this.whsj;
	}

	public void setWhsj(String whsj){
		this.whsj=whsj;
	}

	public Long getRkid(){
		return this.rkid;
	}

	public void setRkid(Long rkid){
		this.rkid=rkid;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getJwh(){
		return this.jwh;
	}

	public void setJwh(String jwh){
		this.jwh=jwh;
	}

	public String getJlxmc(){
		return this.jlxmc;
	}

	public void setJlxmc(String jlxmc){
		this.jlxmc=jlxmc;
	}

	public String getJwhmc(){
		return this.jwhmc;
	}

	public void setJwhmc(String jwhmc){
		this.jwhmc=jwhmc;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
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

	public static long getSerialversionuid() {			return serialVersionUID;	}}