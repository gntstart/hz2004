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
*户籍业务上报临时表
*/
@Entity
@Table(name="HJYW_HJYWSBLSB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_HJYWSBLSB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*业务上报ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ywsbid;
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
	private String	fwcs;
	private String	jlxdm;
	private String	jlxmc;
	private String	mlph;
	private String	mlxz;
	private String	jwhdm;
	private String	jwhmc;
	private String	zrqdm;
	private String	zrqmc;
	private String	jmzdm;
	private String	jmzmc;
	private String	qtzzssxq;
	private String	qtzzjlxdm;
	private String	qtzzjlxmc;
	private String	qtzzmlph;
	private String	qtzzmlxz;
	private String	xxjb;
	private String	zxbs;
	private String	hh;
	private String	hlx;
	private String	yhzgx;
	private String	xzjdmc;
	private String	xjgajgmc;
	private String	xjgajgjgdm;
	private String	pcsmc;
	private String	pcsjgdm;
	private String	xxtqsj;
	private Long	ryid;
	private String	swrq;
	private String	swzxlb;
	private String	swzmbh;
	private String	cgqxzqh;
	private String	lzgj;
	private String	qwgj;
	private String	cjqxzqh;
	private String	cjxzqh;
	private String	szqxzqh;
	private String	szrq;
	private String	dbqxzqh;
	private String	qrrq;
	private String	qrlb;
	private String	qcdssxq;
	private String	qcdxz;
	private String	qcrq;
	private String	qczxlb;
	private String	qwdssx;
	private String	qwdxz;
	private String	qyzbh;
	private String	zqzbh;
	private String	qyfw;
	private String	zzbdrq;
	private String	zzbdlb;
	private String	yzz;
	private String	yzzjlxdm;
	private String	yzzjlxmc;
	private String	yzzmlph;
	private String	yzzmlxz;
	private String	bggzrq;
	private String	bggzlb;
	private String	bggzxm;
	private String	bggzxmbh;
	private String	bggzqnr;
	private String	bggzhnr;
	private String	ygmsfhm;
	private String	xgmsfhm;
	private String	ywlx;
	private String	slsj;
	private String	pcsdm;
	private Long	nbid;
	private String	nbbm;
	private Long	yclx;
	private Long	qrbz;
	private Long	cxbz;
	private String	csdjlb;
	private String	cszmbh;
	private Long	zpid;
	private Long	tbbz;
	private String	ywmc;
	private String	xzz;
	private String	yzzssxq;
	private String	cssj;
	private String	shgxycw;
	private String	shgxyxm;
	private String	shgxyzz;
	private String	shgxecw;
	private String	shgxexm;
	private String	shgxezz;
	private String	xzjd;
	private String	dhhm;
	private String	hcybdlx;
	private Long	hjywid;
	private Long	ywclsx;

	public PoHJYW_HJYWSBLSB(){}

	public Long getYwsbid(){
		return this.ywsbid;
	}

	public void setYwsbid(Long ywsbid){
		this.ywsbid=ywsbid;
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

	public String getFwcs(){
		return this.fwcs;
	}

	public void setFwcs(String fwcs){
		this.fwcs=fwcs;
	}

	public String getJlxdm(){
		return this.jlxdm;
	}

	public void setJlxdm(String jlxdm){
		this.jlxdm=jlxdm;
	}

	public String getJlxmc(){
		return this.jlxmc;
	}

	public void setJlxmc(String jlxmc){
		this.jlxmc=jlxmc;
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

	public String getJwhdm(){
		return this.jwhdm;
	}

	public void setJwhdm(String jwhdm){
		this.jwhdm=jwhdm;
	}

	public String getJwhmc(){
		return this.jwhmc;
	}

	public void setJwhmc(String jwhmc){
		this.jwhmc=jwhmc;
	}

	public String getZrqdm(){
		return this.zrqdm;
	}

	public void setZrqdm(String zrqdm){
		this.zrqdm=zrqdm;
	}

	public String getZrqmc(){
		return this.zrqmc;
	}

	public void setZrqmc(String zrqmc){
		this.zrqmc=zrqmc;
	}

	public String getJmzdm(){
		return this.jmzdm;
	}

	public void setJmzdm(String jmzdm){
		this.jmzdm=jmzdm;
	}

	public String getJmzmc(){
		return this.jmzmc;
	}

	public void setJmzmc(String jmzmc){
		this.jmzmc=jmzmc;
	}

	public String getQtzzssxq(){
		return this.qtzzssxq;
	}

	public void setQtzzssxq(String qtzzssxq){
		this.qtzzssxq=qtzzssxq;
	}

	public String getQtzzjlxdm(){
		return this.qtzzjlxdm;
	}

	public void setQtzzjlxdm(String qtzzjlxdm){
		this.qtzzjlxdm=qtzzjlxdm;
	}

	public String getQtzzjlxmc(){
		return this.qtzzjlxmc;
	}

	public void setQtzzjlxmc(String qtzzjlxmc){
		this.qtzzjlxmc=qtzzjlxmc;
	}

	public String getQtzzmlph(){
		return this.qtzzmlph;
	}

	public void setQtzzmlph(String qtzzmlph){
		this.qtzzmlph=qtzzmlph;
	}

	public String getQtzzmlxz(){
		return this.qtzzmlxz;
	}

	public void setQtzzmlxz(String qtzzmlxz){
		this.qtzzmlxz=qtzzmlxz;
	}

	public String getXxjb(){
		return this.xxjb;
	}

	public void setXxjb(String xxjb){
		this.xxjb=xxjb;
	}

	public String getZxbs(){
		return this.zxbs;
	}

	public void setZxbs(String zxbs){
		this.zxbs=zxbs;
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

	public String getQwgj(){
		return this.qwgj;
	}

	public void setQwgj(String qwgj){
		this.qwgj=qwgj;
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

	public String getYzzjlxdm(){
		return this.yzzjlxdm;
	}

	public void setYzzjlxdm(String yzzjlxdm){
		this.yzzjlxdm=yzzjlxdm;
	}

	public String getYzzjlxmc(){
		return this.yzzjlxmc;
	}

	public void setYzzjlxmc(String yzzjlxmc){
		this.yzzjlxmc=yzzjlxmc;
	}

	public String getYzzmlph(){
		return this.yzzmlph;
	}

	public void setYzzmlph(String yzzmlph){
		this.yzzmlph=yzzmlph;
	}

	public String getYzzmlxz(){
		return this.yzzmlxz;
	}

	public void setYzzmlxz(String yzzmlxz){
		this.yzzmlxz=yzzmlxz;
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

	public String getPcsdm(){
		return this.pcsdm;
	}

	public void setPcsdm(String pcsdm){
		this.pcsdm=pcsdm;
	}

	public Long getNbid(){
		return this.nbid;
	}

	public void setNbid(Long nbid){
		this.nbid=nbid;
	}

	public String getNbbm(){
		return this.nbbm;
	}

	public void setNbbm(String nbbm){
		this.nbbm=nbbm;
	}

	public Long getYclx(){
		return this.yclx;
	}

	public void setYclx(Long yclx){
		this.yclx=yclx;
	}

	public Long getQrbz(){
		return this.qrbz;
	}

	public void setQrbz(Long qrbz){
		this.qrbz=qrbz;
	}

	public Long getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(Long cxbz){
		this.cxbz=cxbz;
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

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getYwmc(){
		return this.ywmc;
	}

	public void setYwmc(String ywmc){
		this.ywmc=ywmc;
	}

	public String getXzz(){
		return this.xzz;
	}

	public void setXzz(String xzz){
		this.xzz=xzz;
	}

	public String getYzzssxq(){
		return this.yzzssxq;
	}

	public void setYzzssxq(String yzzssxq){
		this.yzzssxq=yzzssxq;
	}

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getShgxycw(){
		return this.shgxycw;
	}

	public void setShgxycw(String shgxycw){
		this.shgxycw=shgxycw;
	}

	public String getShgxyxm(){
		return this.shgxyxm;
	}

	public void setShgxyxm(String shgxyxm){
		this.shgxyxm=shgxyxm;
	}

	public String getShgxyzz(){
		return this.shgxyzz;
	}

	public void setShgxyzz(String shgxyzz){
		this.shgxyzz=shgxyzz;
	}

	public String getShgxecw(){
		return this.shgxecw;
	}

	public void setShgxecw(String shgxecw){
		this.shgxecw=shgxecw;
	}

	public String getShgxexm(){
		return this.shgxexm;
	}

	public void setShgxexm(String shgxexm){
		this.shgxexm=shgxexm;
	}

	public String getShgxezz(){
		return this.shgxezz;
	}

	public void setShgxezz(String shgxezz){
		this.shgxezz=shgxezz;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public String getDhhm(){
		return this.dhhm;
	}

	public void setDhhm(String dhhm){
		this.dhhm=dhhm;
	}

	public String getHcybdlx(){
		return this.hcybdlx;
	}

	public void setHcybdlx(String hcybdlx){
		this.hcybdlx=hcybdlx;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public Long getYwclsx(){
		return this.ywclsx;
	}

	public void setYwclsx(Long ywclsx){
		this.ywclsx=ywclsx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}