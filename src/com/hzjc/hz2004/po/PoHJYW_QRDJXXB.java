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
*迁入登记信息表
*/
@Entity
@Table(name="HJYW_QRDJXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_QRDJXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*迁入登记ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	qrdjid;
	private Long	rynbid;
	private String	qrqhb;
	private String	qrlb;
	private String	qrrq;
	private String	qcdgjdq;
	private String	qcdssxq;
	private String	qcdpcs;
	private String	qcdxzjd;
	private String	qcdjwh;
	private String	qcdjlx;
	private String	qcdjwzrq;
	private String	qcdmlph;
	private String	qcdxz;
	private String	qyzbh;
	private String	zqzbh;
	private Long	nbsfzid;
	private String	bdfw;
	private Long	hjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxhjywid;
	private Long	tbbz;
	private String	bwbh;
	private String	sbsj;
	private String	sbryxm;
	private String	sbrgmsfhm;
	private String	slsj;
	private String	sldw;
	private Long	slrid;
	private String	ywlx;
	private Long	czsm;
	private String	hb;
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
	private String	zjlb;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	dhhm;
	private String	qtssxq;
	private String	bz;
	private String	qtzz;
	private String	csdxz;
	private String	csdgjdq;
	private String	xm;
	private String	cym;
	private String	gmsfhm;
	private String	mz;
	private String	xb;
	private String	csrq;
	private String	cssj;
	private String	csdssxq;
	private Long	ryid;
	private Long	hhnbid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private Long	mlpnbid;
	private String	yhzgx;
	private Long	hhid;
	private Long	mlpid;
	private String	hzxm;
	private String	hzgmsfhm;
	private String	ydblbs;
	private String	qhtzbs;
	private String	sbrjtgx;
	private String	ycyzjzl;
	private String	ycyzjhm;
	private String	qyzhyxx;
	private String	cxfldm;
	private String	hjdz_cxfldm;
	private String	nyzyrklhczyydm;
	private String	zyjszc_pdbz;
	private String	jndj_pdbz;
	private String	ncjdzzyxbys_pdbz;
	private String	jjqx_pdbz;
	private String	zczjyhjzwnys_pdbz;
	private String	ncjsbtcxy_pdbz;
	private String	jthzl;

	public PoHJYW_QRDJXXB(){}

	public Long getQrdjid(){
		return this.qrdjid;
	}

	public void setQrdjid(Long qrdjid){
		this.qrdjid=qrdjid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getQrqhb(){
		return this.qrqhb;
	}

	public void setQrqhb(String qrqhb){
		this.qrqhb=qrqhb;
	}

	public String getQrlb(){
		return this.qrlb;
	}

	public void setQrlb(String qrlb){
		this.qrlb=qrlb;
	}

	public String getQrrq(){
		return this.qrrq;
	}

	public void setQrrq(String qrrq){
		this.qrrq=qrrq;
	}

	public String getQcdgjdq(){
		return this.qcdgjdq;
	}

	public void setQcdgjdq(String qcdgjdq){
		this.qcdgjdq=qcdgjdq;
	}

	public String getQcdssxq(){
		return this.qcdssxq;
	}

	public void setQcdssxq(String qcdssxq){
		this.qcdssxq=qcdssxq;
	}

	public String getQcdpcs(){
		return this.qcdpcs;
	}

	public void setQcdpcs(String qcdpcs){
		this.qcdpcs=qcdpcs;
	}

	public String getQcdxzjd(){
		return this.qcdxzjd;
	}

	public void setQcdxzjd(String qcdxzjd){
		this.qcdxzjd=qcdxzjd;
	}

	public String getQcdjwh(){
		return this.qcdjwh;
	}

	public void setQcdjwh(String qcdjwh){
		this.qcdjwh=qcdjwh;
	}

	public String getQcdjlx(){
		return this.qcdjlx;
	}

	public void setQcdjlx(String qcdjlx){
		this.qcdjlx=qcdjlx;
	}

	public String getQcdjwzrq(){
		return this.qcdjwzrq;
	}

	public void setQcdjwzrq(String qcdjwzrq){
		this.qcdjwzrq=qcdjwzrq;
	}

	public String getQcdmlph(){
		return this.qcdmlph;
	}

	public void setQcdmlph(String qcdmlph){
		this.qcdmlph=qcdmlph;
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

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
	}

	public String getBdfw(){
		return this.bdfw;
	}

	public void setBdfw(String bdfw){
		this.bdfw=bdfw;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
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

	public Long getCxhjywid(){
		return this.cxhjywid;
	}

	public void setCxhjywid(Long cxhjywid){
		this.cxhjywid=cxhjywid;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(String sbsj){
		this.sbsj=sbsj;
	}

	public String getSbryxm(){
		return this.sbryxm;
	}

	public void setSbryxm(String sbryxm){
		this.sbryxm=sbryxm;
	}

	public String getSbrgmsfhm(){
		return this.sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm){
		this.sbrgmsfhm=sbrgmsfhm;
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

	public String getYwlx(){
		return this.ywlx;
	}

	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}

	public Long getCzsm(){
		return this.czsm;
	}

	public void setCzsm(Long czsm){
		this.czsm=czsm;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
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

	public String getZjlb(){
		return this.zjlb;
	}

	public void setZjlb(String zjlb){
		this.zjlb=zjlb;
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

	public String getDhhm(){
		return this.dhhm;
	}

	public void setDhhm(String dhhm){
		this.dhhm=dhhm;
	}

	public String getQtssxq(){
		return this.qtssxq;
	}

	public void setQtssxq(String qtssxq){
		this.qtssxq=qtssxq;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getQtzz(){
		return this.qtzz;
	}

	public void setQtzz(String qtzz){
		this.qtzz=qtzz;
	}

	public String getCsdxz(){
		return this.csdxz;
	}

	public void setCsdxz(String csdxz){
		this.csdxz=csdxz;
	}

	public String getCsdgjdq(){
		return this.csdgjdq;
	}

	public void setCsdgjdq(String csdgjdq){
		this.csdgjdq=csdgjdq;
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

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
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

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
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

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
	}

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
	}

	public String getHzxm(){
		return this.hzxm;
	}

	public void setHzxm(String hzxm){
		this.hzxm=hzxm;
	}

	public String getHzgmsfhm(){
		return this.hzgmsfhm;
	}

	public void setHzgmsfhm(String hzgmsfhm){
		this.hzgmsfhm=hzgmsfhm;
	}

	public String getYdblbs(){
		return this.ydblbs;
	}

	public void setYdblbs(String ydblbs){
		this.ydblbs=ydblbs;
	}

	public String getQhtzbs(){
		return this.qhtzbs;
	}

	public void setQhtzbs(String qhtzbs){
		this.qhtzbs=qhtzbs;
	}

	public String getSbrjtgx(){
		return this.sbrjtgx;
	}

	public void setSbrjtgx(String sbrjtgx){
		this.sbrjtgx=sbrjtgx;
	}

	public String getYcyzjzl(){
		return this.ycyzjzl;
	}

	public void setYcyzjzl(String ycyzjzl){
		this.ycyzjzl=ycyzjzl;
	}

	public String getYcyzjhm(){
		return this.ycyzjhm;
	}

	public void setYcyzjhm(String ycyzjhm){
		this.ycyzjhm=ycyzjhm;
	}

	public String getQyzhyxx(){
		return this.qyzhyxx;
	}

	public void setQyzhyxx(String qyzhyxx){
		this.qyzhyxx=qyzhyxx;
	}

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public String getHjdz_cxfldm(){
		return this.hjdz_cxfldm;
	}

	public void setHjdz_cxfldm(String hjdz_cxfldm){
		this.hjdz_cxfldm=hjdz_cxfldm;
	}

	public String getNyzyrklhczyydm(){
		return this.nyzyrklhczyydm;
	}

	public void setNyzyrklhczyydm(String nyzyrklhczyydm){
		this.nyzyrklhczyydm=nyzyrklhczyydm;
	}

	public String getZyjszc_pdbz(){
		return this.zyjszc_pdbz;
	}

	public void setZyjszc_pdbz(String zyjszc_pdbz){
		this.zyjszc_pdbz=zyjszc_pdbz;
	}

	public String getJndj_pdbz(){
		return this.jndj_pdbz;
	}

	public void setJndj_pdbz(String jndj_pdbz){
		this.jndj_pdbz=jndj_pdbz;
	}

	public String getNcjdzzyxbys_pdbz(){
		return this.ncjdzzyxbys_pdbz;
	}

	public void setNcjdzzyxbys_pdbz(String ncjdzzyxbys_pdbz){
		this.ncjdzzyxbys_pdbz=ncjdzzyxbys_pdbz;
	}

	public String getJjqx_pdbz(){
		return this.jjqx_pdbz;
	}

	public void setJjqx_pdbz(String jjqx_pdbz){
		this.jjqx_pdbz=jjqx_pdbz;
	}

	public String getZczjyhjzwnys_pdbz(){
		return this.zczjyhjzwnys_pdbz;
	}

	public void setZczjyhjzwnys_pdbz(String zczjyhjzwnys_pdbz){
		this.zczjyhjzwnys_pdbz=zczjyhjzwnys_pdbz;
	}

	public String getNcjsbtcxy_pdbz(){
		return this.ncjsbtcxy_pdbz;
	}

	public void setNcjsbtcxy_pdbz(String ncjsbtcxy_pdbz){
		this.ncjsbtcxy_pdbz=ncjsbtcxy_pdbz;
	}

	public String getJthzl(){
		return this.jthzl;
	}

	public void setJthzl(String jthzl){
		this.jthzl=jthzl;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}