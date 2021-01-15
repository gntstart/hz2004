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
*常住人口基本信息表
*/
@Entity
@Table(name="HJXX_CZRKJBXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_CZRKJBXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	rynbid;
	private Long	ryid;
	private Long	hhnbid;
	private Long	mlpnbid;
	private Long	zpid;
	private Long	nbsfzid;
	private String	gmsfhm;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	xm;
	private String	cym;
	private String	xmpy;
	private String	cympy;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	cssj;
	private String	csdgjdq;
	private String	csdssxq;
	private String	csdxz;
	private String	dhhm;
	private String	jhryxm;
	private String	jhrygmsfhm;
	private String	jhryjhgx;
	private String	jhrexm;
	private String	jhregmsfhm;
	private String	jhrejhgx;
	private String	fqxm;
	private String	fqgmsfhm;
	private String	mqxm;
	private String	mqgmsfhm;
	private String	poxm;
	private String	pogmsfhm;
	private String	jggjdq;
	private String	jgssxq;
	private String	zjxy;
	private String	whcd;
	private String	hyzk;
	private String	byzk;
	private String	sg;
	private String	xx;
	private String	zy;
	private String	zylb;
	private String	fwcs;
	private String	xxjb;
	private String	hsql;
	private String	hyql;
	private String	hgjdqql;
	private String	hssxqql;
	private String	hxzql;
	private String	hslbz;
	private String	hylbz;
	private String	hgjdqlbz;
	private String	hsssqlbz;
	private String	hxzlbz;
	private String	swrq;
	private String	swzxlb;
	private String	swzxrq;
	private String	qcrq;
	private String	qczxlb;
	private String	qwdgjdq;
	private String	qwdssxq;
	private String	qwdxz;
	private String	cszmbh;
	private String	cszqfrq;
	private String	hylb;
	private String	qtssxq;
	private String	qtzz;
	private String	rylb;
	private String	hb;
	private String	yhzgx;
	private String	ryzt;
	private String	rysdzt;
	private Long	lxdbid;
	private String	bz;
	private String	jlbz;
	private String	ywnr;
	private Long	cjhjywid;
	private Long	cchjywid;
	private String	qysj;
	private String	jssj;
	private String	cxbz;
	private String	zjlb;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private Long	mlpid;
	private String	ssxq;
	private String	hh;
	private String	hlx;
	private Long	hhid;
	private String	bdfw;
	private String	xxqysj;
	private String	dhhm2;
	private String	gxsj;
	private String	zxsj;
	private String	xmx;
	private String	xmm;
	private String	jgxz;
	private String	jhryzjzl;
	private String	jhryzjhm;
	private String	jhrywwx;
	private String	jhrywwm;
	private String	jhrylxdh;
	private String	jhrezjzl;
	private String	jhrezjhm;
	private String	jhrewwx;
	private String	jhrewwm;
	private String	jhrelxdh;
	private String	fqzjzl;
	private String	fqzjhm;
	private String	fqwwx;
	private String	fqwwm;
	private String	mqzjzl;
	private String	mqzjhm;
	private String	mqwwx;
	private String	mqwwm;
	private String	pozjzl;
	private String	pozjhm;
	private String	powwx;
	private String	powwm;
	private String	hjdzbm;
	private String	hjdrhyz;
	private String	cydwbm;
	private String	qyldyy;
	private String	yhkxzmc;
	private String	yhkxzsj;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	cxfldm;
	private String	nyzyrklhczyydm;
	private String	lzd_cxfldm;
	private String	kdqqy_qrdqbm;
	private String	kdqqy_qrywid;
	private String	kdqqy_qrywlx;
	private String	kdqqy_qrfkzt;
	private String	kdqqy_qrfksj;
	private String	jthzl;
	private String dsgxsj;
	private String djzt;
	public PoHJXX_CZRKJBXXB(){}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
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

	public String getXmpy(){
		return this.xmpy;
	}

	public void setXmpy(String xmpy){
		this.xmpy=xmpy;
	}

	public String getCympy(){
		return this.cympy;
	}

	public void setCympy(String cympy){
		this.cympy=cympy;
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

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdgjdq(){
		return this.csdgjdq;
	}

	public void setCsdgjdq(String csdgjdq){
		this.csdgjdq=csdgjdq;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getCsdxz(){
		return this.csdxz;
	}

	public void setCsdxz(String csdxz){
		this.csdxz=csdxz;
	}

	public String getDhhm(){
		return this.dhhm;
	}

	public void setDhhm(String dhhm){
		this.dhhm=dhhm;
	}

	public String getJhryxm(){
		return this.jhryxm;
	}

	public void setJhryxm(String jhryxm){
		this.jhryxm=jhryxm;
	}

	public String getJhrygmsfhm(){
		return this.jhrygmsfhm;
	}

	public void setJhrygmsfhm(String jhrygmsfhm){
		this.jhrygmsfhm=jhrygmsfhm;
	}

	public String getJhryjhgx(){
		return this.jhryjhgx;
	}

	public void setJhryjhgx(String jhryjhgx){
		this.jhryjhgx=jhryjhgx;
	}

	public String getJhrexm(){
		return this.jhrexm;
	}

	public void setJhrexm(String jhrexm){
		this.jhrexm=jhrexm;
	}

	public String getJhregmsfhm(){
		return this.jhregmsfhm;
	}

	public void setJhregmsfhm(String jhregmsfhm){
		this.jhregmsfhm=jhregmsfhm;
	}

	public String getJhrejhgx(){
		return this.jhrejhgx;
	}

	public void setJhrejhgx(String jhrejhgx){
		this.jhrejhgx=jhrejhgx;
	}

	public String getFqxm(){
		return this.fqxm;
	}

	public void setFqxm(String fqxm){
		this.fqxm=fqxm;
	}

	public String getFqgmsfhm(){
		return this.fqgmsfhm;
	}

	public void setFqgmsfhm(String fqgmsfhm){
		this.fqgmsfhm=fqgmsfhm;
	}

	public String getMqxm(){
		return this.mqxm;
	}

	public void setMqxm(String mqxm){
		this.mqxm=mqxm;
	}

	public String getMqgmsfhm(){
		return this.mqgmsfhm;
	}

	public void setMqgmsfhm(String mqgmsfhm){
		this.mqgmsfhm=mqgmsfhm;
	}

	public String getPoxm(){
		return this.poxm;
	}

	public void setPoxm(String poxm){
		this.poxm=poxm;
	}

	public String getPogmsfhm(){
		return this.pogmsfhm;
	}

	public void setPogmsfhm(String pogmsfhm){
		this.pogmsfhm=pogmsfhm;
	}

	public String getJggjdq(){
		return this.jggjdq;
	}

	public void setJggjdq(String jggjdq){
		this.jggjdq=jggjdq;
	}

	public String getJgssxq(){
		return this.jgssxq;
	}

	public void setJgssxq(String jgssxq){
		this.jgssxq=jgssxq;
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

	public String getXxjb(){
		return this.xxjb;
	}

	public void setXxjb(String xxjb){
		this.xxjb=xxjb;
	}

	public String getHsql(){
		return this.hsql;
	}

	public void setHsql(String hsql){
		this.hsql=hsql;
	}

	public String getHyql(){
		return this.hyql;
	}

	public void setHyql(String hyql){
		this.hyql=hyql;
	}

	public String getHgjdqql(){
		return this.hgjdqql;
	}

	public void setHgjdqql(String hgjdqql){
		this.hgjdqql=hgjdqql;
	}

	public String getHssxqql(){
		return this.hssxqql;
	}

	public void setHssxqql(String hssxqql){
		this.hssxqql=hssxqql;
	}

	public String getHxzql(){
		return this.hxzql;
	}

	public void setHxzql(String hxzql){
		this.hxzql=hxzql;
	}

	public String getHslbz(){
		return this.hslbz;
	}

	public void setHslbz(String hslbz){
		this.hslbz=hslbz;
	}

	public String getHylbz(){
		return this.hylbz;
	}

	public void setHylbz(String hylbz){
		this.hylbz=hylbz;
	}

	public String getHgjdqlbz(){
		return this.hgjdqlbz;
	}

	public void setHgjdqlbz(String hgjdqlbz){
		this.hgjdqlbz=hgjdqlbz;
	}

	public String getHsssqlbz(){
		return this.hsssqlbz;
	}

	public void setHsssqlbz(String hsssqlbz){
		this.hsssqlbz=hsssqlbz;
	}

	public String getHxzlbz(){
		return this.hxzlbz;
	}

	public void setHxzlbz(String hxzlbz){
		this.hxzlbz=hxzlbz;
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

	public String getSwzxrq(){
		return this.swzxrq;
	}

	public void setSwzxrq(String swzxrq){
		this.swzxrq=swzxrq;
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

	public String getQwdgjdq(){
		return this.qwdgjdq;
	}

	public void setQwdgjdq(String qwdgjdq){
		this.qwdgjdq=qwdgjdq;
	}

	public String getQwdssxq(){
		return this.qwdssxq;
	}

	public void setQwdssxq(String qwdssxq){
		this.qwdssxq=qwdssxq;
	}

	public String getQwdxz(){
		return this.qwdxz;
	}

	public void setQwdxz(String qwdxz){
		this.qwdxz=qwdxz;
	}

	public String getCszmbh(){
		return this.cszmbh;
	}

	public void setCszmbh(String cszmbh){
		this.cszmbh=cszmbh;
	}

	public String getCszqfrq(){
		return this.cszqfrq;
	}

	public void setCszqfrq(String cszqfrq){
		this.cszqfrq=cszqfrq;
	}

	public String getHylb(){
		return this.hylb;
	}

	public void setHylb(String hylb){
		this.hylb=hylb;
	}

	public String getQtssxq(){
		return this.qtssxq;
	}

	public void setQtssxq(String qtssxq){
		this.qtssxq=qtssxq;
	}

	public String getQtzz(){
		return this.qtzz;
	}

	public void setQtzz(String qtzz){
		this.qtzz=qtzz;
	}

	public String getRylb(){
		return this.rylb;
	}

	public void setRylb(String rylb){
		this.rylb=rylb;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public String getRyzt(){
		return this.ryzt;
	}

	public void setRyzt(String ryzt){
		this.ryzt=ryzt;
	}

	public String getRysdzt(){
		return this.rysdzt;
	}

	public void setRysdzt(String rysdzt){
		this.rysdzt=rysdzt;
	}

	public Long getLxdbid(){
		return this.lxdbid;
	}

	public void setLxdbid(Long lxdbid){
		this.lxdbid=lxdbid;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public String getYwnr(){
		return this.ywnr;
	}

	public void setYwnr(String ywnr){
		this.ywnr=ywnr;
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

	public String getQysj(){
		return this.qysj;
	}

	public void setQysj(String qysj){
		this.qysj=qysj;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getZjlb(){
		return this.zjlb;
	}

	public void setZjlb(String zjlb){
		this.zjlb=zjlb;
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

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
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

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public String getBdfw(){
		return this.bdfw;
	}

	public void setBdfw(String bdfw){
		this.bdfw=bdfw;
	}

	public String getXxqysj(){
		return this.xxqysj;
	}

	public void setXxqysj(String xxqysj){
		this.xxqysj=xxqysj;
	}

	public String getDhhm2(){
		return this.dhhm2;
	}

	public void setDhhm2(String dhhm2){
		this.dhhm2=dhhm2;
	}

	public String getGxsj(){
		return this.gxsj;
	}

	public void setGxsj(String gxsj){
		this.gxsj=gxsj;
	}

	public String getZxsj(){
		return this.zxsj;
	}

	public void setZxsj(String zxsj){
		this.zxsj=zxsj;
	}

	public String getXmx(){
		return this.xmx;
	}

	public void setXmx(String xmx){
		this.xmx=xmx;
	}

	public String getXmm(){
		return this.xmm;
	}

	public void setXmm(String xmm){
		this.xmm=xmm;
	}

	public String getJgxz(){
		return this.jgxz;
	}

	public void setJgxz(String jgxz){
		this.jgxz=jgxz;
	}

	public String getJhryzjzl(){
		return this.jhryzjzl;
	}

	public void setJhryzjzl(String jhryzjzl){
		this.jhryzjzl=jhryzjzl;
	}

	public String getJhryzjhm(){
		return this.jhryzjhm;
	}

	public void setJhryzjhm(String jhryzjhm){
		this.jhryzjhm=jhryzjhm;
	}

	public String getJhrywwx(){
		return this.jhrywwx;
	}

	public void setJhrywwx(String jhrywwx){
		this.jhrywwx=jhrywwx;
	}

	public String getJhrywwm(){
		return this.jhrywwm;
	}

	public void setJhrywwm(String jhrywwm){
		this.jhrywwm=jhrywwm;
	}

	public String getJhrylxdh(){
		return this.jhrylxdh;
	}

	public void setJhrylxdh(String jhrylxdh){
		this.jhrylxdh=jhrylxdh;
	}

	public String getJhrezjzl(){
		return this.jhrezjzl;
	}

	public void setJhrezjzl(String jhrezjzl){
		this.jhrezjzl=jhrezjzl;
	}

	public String getJhrezjhm(){
		return this.jhrezjhm;
	}

	public void setJhrezjhm(String jhrezjhm){
		this.jhrezjhm=jhrezjhm;
	}

	public String getJhrewwx(){
		return this.jhrewwx;
	}

	public void setJhrewwx(String jhrewwx){
		this.jhrewwx=jhrewwx;
	}

	public String getJhrewwm(){
		return this.jhrewwm;
	}

	public void setJhrewwm(String jhrewwm){
		this.jhrewwm=jhrewwm;
	}

	public String getJhrelxdh(){
		return this.jhrelxdh;
	}

	public void setJhrelxdh(String jhrelxdh){
		this.jhrelxdh=jhrelxdh;
	}

	public String getFqzjzl(){
		return this.fqzjzl;
	}

	public void setFqzjzl(String fqzjzl){
		this.fqzjzl=fqzjzl;
	}

	public String getFqzjhm(){
		return this.fqzjhm;
	}

	public void setFqzjhm(String fqzjhm){
		this.fqzjhm=fqzjhm;
	}

	public String getFqwwx(){
		return this.fqwwx;
	}

	public void setFqwwx(String fqwwx){
		this.fqwwx=fqwwx;
	}

	public String getFqwwm(){
		return this.fqwwm;
	}

	public void setFqwwm(String fqwwm){
		this.fqwwm=fqwwm;
	}

	public String getMqzjzl(){
		return this.mqzjzl;
	}

	public void setMqzjzl(String mqzjzl){
		this.mqzjzl=mqzjzl;
	}

	public String getMqzjhm(){
		return this.mqzjhm;
	}

	public void setMqzjhm(String mqzjhm){
		this.mqzjhm=mqzjhm;
	}

	public String getMqwwx(){
		return this.mqwwx;
	}

	public void setMqwwx(String mqwwx){
		this.mqwwx=mqwwx;
	}

	public String getMqwwm(){
		return this.mqwwm;
	}

	public void setMqwwm(String mqwwm){
		this.mqwwm=mqwwm;
	}

	public String getPozjzl(){
		return this.pozjzl;
	}

	public void setPozjzl(String pozjzl){
		this.pozjzl=pozjzl;
	}

	public String getPozjhm(){
		return this.pozjhm;
	}

	public void setPozjhm(String pozjhm){
		this.pozjhm=pozjhm;
	}

	public String getPowwx(){
		return this.powwx;
	}

	public void setPowwx(String powwx){
		this.powwx=powwx;
	}

	public String getPowwm(){
		return this.powwm;
	}

	public void setPowwm(String powwm){
		this.powwm=powwm;
	}

	public String getHjdzbm(){
		return this.hjdzbm;
	}

	public void setHjdzbm(String hjdzbm){
		this.hjdzbm=hjdzbm;
	}

	public String getHjdrhyz(){
		return this.hjdrhyz;
	}

	public void setHjdrhyz(String hjdrhyz){
		this.hjdrhyz=hjdrhyz;
	}

	public String getCydwbm(){
		return this.cydwbm;
	}

	public void setCydwbm(String cydwbm){
		this.cydwbm=cydwbm;
	}

	public String getQyldyy(){
		return this.qyldyy;
	}

	public void setQyldyy(String qyldyy){
		this.qyldyy=qyldyy;
	}

	public String getYhkxzmc(){
		return this.yhkxzmc;
	}

	public void setYhkxzmc(String yhkxzmc){
		this.yhkxzmc=yhkxzmc;
	}

	public String getYhkxzsj(){
		return this.yhkxzsj;
	}

	public void setYhkxzsj(String yhkxzsj){
		this.yhkxzsj=yhkxzsj;
	}

	public String getBzdz(){
		return this.bzdz;
	}

	public void setBzdz(String bzdz){
		this.bzdz=bzdz;
	}

	public String getBzdzid(){
		return this.bzdzid;
	}

	public void setBzdzid(String bzdzid){
		this.bzdzid=bzdzid;
	}

	public String getBzdzx(){
		return this.bzdzx;
	}

	public void setBzdzx(String bzdzx){
		this.bzdzx=bzdzx;
	}

	public String getBzdzy(){
		return this.bzdzy;
	}

	public void setBzdzy(String bzdzy){
		this.bzdzy=bzdzy;
	}

	public String getBzdzst(){
		return this.bzdzst;
	}

	public void setBzdzst(String bzdzst){
		this.bzdzst=bzdzst;
	}

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public String getNyzyrklhczyydm(){
		return this.nyzyrklhczyydm;
	}

	public void setNyzyrklhczyydm(String nyzyrklhczyydm){
		this.nyzyrklhczyydm=nyzyrklhczyydm;
	}

	public String getLzd_cxfldm(){
		return this.lzd_cxfldm;
	}

	public void setLzd_cxfldm(String lzd_cxfldm){
		this.lzd_cxfldm=lzd_cxfldm;
	}

	public String getKdqqy_qrdqbm(){
		return this.kdqqy_qrdqbm;
	}

	public void setKdqqy_qrdqbm(String kdqqy_qrdqbm){
		this.kdqqy_qrdqbm=kdqqy_qrdqbm;
	}

	public String getKdqqy_qrywid(){
		return this.kdqqy_qrywid;
	}

	public void setKdqqy_qrywid(String kdqqy_qrywid){
		this.kdqqy_qrywid=kdqqy_qrywid;
	}

	public String getKdqqy_qrywlx(){
		return this.kdqqy_qrywlx;
	}

	public void setKdqqy_qrywlx(String kdqqy_qrywlx){
		this.kdqqy_qrywlx=kdqqy_qrywlx;
	}

	public String getKdqqy_qrfkzt(){
		return this.kdqqy_qrfkzt;
	}

	public void setKdqqy_qrfkzt(String kdqqy_qrfkzt){
		this.kdqqy_qrfkzt=kdqqy_qrfkzt;
	}

	public String getKdqqy_qrfksj(){
		return this.kdqqy_qrfksj;
	}

	public void setKdqqy_qrfksj(String kdqqy_qrfksj){
		this.kdqqy_qrfksj=kdqqy_qrfksj;
	}

	public String getJthzl(){
		return this.jthzl;
	}

	public void setJthzl(String jthzl){
		this.jthzl=jthzl;
	}

	public String getDsgxsj() {
		return dsgxsj;
	}

	public void setDsgxsj(String dsgxsj) {
		this.dsgxsj = dsgxsj;
	}

	public String getDjzt() {
		return djzt;
	}

	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}