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
*住址变动信息表
*/
@Entity
@Table(name="HJYW_ZZBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_ZZBDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*住址变动ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	zzbdid;
	private Long	rynbid;
	private Long	yhhnbid;
	private String	zzbdlb;
	private String	zzbdrq;
	private String	qyzbh;
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
	private String	xm;
	private String	mz;
	private String	xb;
	private String	csrq;
	private String	cssj;
	private String	csdssxq;
	private String	gmsfhm;
	private String	hh;
	private String	hlx;
	private String	hb;
	private String	hh_q;
	private String	hlx_q;
	private String	hb_q;
	private String	ssxq_q;
	private String	jlx_q;
	private String	mlph_q;
	private String	mlxz_q;
	private String	pcs_q;
	private String	zrq_q;
	private String	xzjd_q;
	private String	jcwh_q;
	private String	pxh_q;
	private String	jdlb_q;
	private String	cdlb_q;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	pxh;
	private String	jdlb;
	private String	cdlb;
	private Long	ryid;
	private Long	hhnbid;
	private Long	mlpnbid;
	private Long	nbsfzid;
	private Long	mlpnbid_q;
	private String	yhzgx;
	private String	hzxm;
	private String	hzgmsfhm;
	private Long	hhid;
	private Long	yhhid;
	private Long	mlpid;
	private Long	mlpid_q;
	private String	sbrjtgx;
	private String	qhtzbs;
	private String	ydzbm;
	private String	nyzyrklhczyydm;
	private String	cxsxtz_pdbz;
	private String	jjqx_pdbz;
	private String	zczjyhjzwnys_pdbz;
	private String	cxfldm;
	private String	cxfldm_q;
	private String	jthzl;

	public PoHJYW_ZZBDXXB(){}

	public Long getZzbdid(){
		return this.zzbdid;
	}

	public void setZzbdid(Long zzbdid){
		this.zzbdid=zzbdid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getYhhnbid(){
		return this.yhhnbid;
	}

	public void setYhhnbid(Long yhhnbid){
		this.yhhnbid=yhhnbid;
	}

	public String getZzbdlb(){
		return this.zzbdlb;
	}

	public void setZzbdlb(String zzbdlb){
		this.zzbdlb=zzbdlb;
	}

	public String getZzbdrq(){
		return this.zzbdrq;
	}

	public void setZzbdrq(String zzbdrq){
		this.zzbdrq=zzbdrq;
	}

	public String getQyzbh(){
		return this.qyzbh;
	}

	public void setQyzbh(String qyzbh){
		this.qyzbh=qyzbh;
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

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
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

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
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

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getHh_q(){
		return this.hh_q;
	}

	public void setHh_q(String hh_q){
		this.hh_q=hh_q;
	}

	public String getHlx_q(){
		return this.hlx_q;
	}

	public void setHlx_q(String hlx_q){
		this.hlx_q=hlx_q;
	}

	public String getHb_q(){
		return this.hb_q;
	}

	public void setHb_q(String hb_q){
		this.hb_q=hb_q;
	}

	public String getSsxq_q(){
		return this.ssxq_q;
	}

	public void setSsxq_q(String ssxq_q){
		this.ssxq_q=ssxq_q;
	}

	public String getJlx_q(){
		return this.jlx_q;
	}

	public void setJlx_q(String jlx_q){
		this.jlx_q=jlx_q;
	}

	public String getMlph_q(){
		return this.mlph_q;
	}

	public void setMlph_q(String mlph_q){
		this.mlph_q=mlph_q;
	}

	public String getMlxz_q(){
		return this.mlxz_q;
	}

	public void setMlxz_q(String mlxz_q){
		this.mlxz_q=mlxz_q;
	}

	public String getPcs_q(){
		return this.pcs_q;
	}

	public void setPcs_q(String pcs_q){
		this.pcs_q=pcs_q;
	}

	public String getZrq_q(){
		return this.zrq_q;
	}

	public void setZrq_q(String zrq_q){
		this.zrq_q=zrq_q;
	}

	public String getXzjd_q(){
		return this.xzjd_q;
	}

	public void setXzjd_q(String xzjd_q){
		this.xzjd_q=xzjd_q;
	}

	public String getJcwh_q(){
		return this.jcwh_q;
	}

	public void setJcwh_q(String jcwh_q){
		this.jcwh_q=jcwh_q;
	}

	public String getPxh_q(){
		return this.pxh_q;
	}

	public void setPxh_q(String pxh_q){
		this.pxh_q=pxh_q;
	}

	public String getJdlb_q(){
		return this.jdlb_q;
	}

	public void setJdlb_q(String jdlb_q){
		this.jdlb_q=jdlb_q;
	}

	public String getCdlb_q(){
		return this.cdlb_q;
	}

	public void setCdlb_q(String cdlb_q){
		this.cdlb_q=cdlb_q;
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

	public String getJdlb(){
		return this.jdlb;
	}

	public void setJdlb(String jdlb){
		this.jdlb=jdlb;
	}

	public String getCdlb(){
		return this.cdlb;
	}

	public void setCdlb(String cdlb){
		this.cdlb=cdlb;
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

	public Long getNbsfzid(){
		return this.nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid){
		this.nbsfzid=nbsfzid;
	}

	public Long getMlpnbid_q(){
		return this.mlpnbid_q;
	}

	public void setMlpnbid_q(Long mlpnbid_q){
		this.mlpnbid_q=mlpnbid_q;
	}

	public String getYhzgx(){
		return this.yhzgx;
	}

	public void setYhzgx(String yhzgx){
		this.yhzgx=yhzgx;
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

	public Long getHhid(){
		return this.hhid;
	}

	public void setHhid(Long hhid){
		this.hhid=hhid;
	}

	public Long getYhhid(){
		return this.yhhid;
	}

	public void setYhhid(Long yhhid){
		this.yhhid=yhhid;
	}

	public Long getMlpid(){
		return this.mlpid;
	}

	public void setMlpid(Long mlpid){
		this.mlpid=mlpid;
	}

	public Long getMlpid_q(){
		return this.mlpid_q;
	}

	public void setMlpid_q(Long mlpid_q){
		this.mlpid_q=mlpid_q;
	}

	public String getSbrjtgx(){
		return this.sbrjtgx;
	}

	public void setSbrjtgx(String sbrjtgx){
		this.sbrjtgx=sbrjtgx;
	}

	public String getQhtzbs(){
		return this.qhtzbs;
	}

	public void setQhtzbs(String qhtzbs){
		this.qhtzbs=qhtzbs;
	}

	public String getYdzbm(){
		return this.ydzbm;
	}

	public void setYdzbm(String ydzbm){
		this.ydzbm=ydzbm;
	}

	public String getNyzyrklhczyydm(){
		return this.nyzyrklhczyydm;
	}

	public void setNyzyrklhczyydm(String nyzyrklhczyydm){
		this.nyzyrklhczyydm=nyzyrklhczyydm;
	}

	public String getCxsxtz_pdbz(){
		return this.cxsxtz_pdbz;
	}

	public void setCxsxtz_pdbz(String cxsxtz_pdbz){
		this.cxsxtz_pdbz=cxsxtz_pdbz;
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

	public String getCxfldm(){
		return this.cxfldm;
	}

	public void setCxfldm(String cxfldm){
		this.cxfldm=cxfldm;
	}

	public String getCxfldm_q() {
		return cxfldm_q;
	}

	public void setCxfldm_q(String cxfldm_q) {
		this.cxfldm_q = cxfldm_q;
	}

	public String getJthzl(){
		return this.jthzl;
	}

	public void setJthzl(String jthzl){
		this.jthzl=jthzl;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}