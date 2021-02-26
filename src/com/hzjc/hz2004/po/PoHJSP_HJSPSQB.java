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
*户籍审批申请表
*/
@Entity
@Table(name="HJSP_HJSPSQB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJSP_HJSPSQB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*审批业务ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	spywid;
	private String	splx;
	private String	slrq;
	private String	sqrgmsfhm;
	private String	sqrxm;
	private String	sqrzzssxq;
	private String	sqrzzxz;
	private String	sqrhkdjjg;
	private String	xm;
	private Long	ryid;
	private String	xb;
	private String	gmsfhm;
	private String	csrq;
	private String	ysqrgx;
	private String	jhny;
	private String	yjrclx;
	private String	yrdwjcyrysj;
	private String	lxdh;
	private String	byxx;
	private String	whcd;
	private String	bysj;
	private String	zyzl;
	private String	byzsh;
	private String	zyjszc;
	private String	jszsh;
	private String	jsfzjg;
	private String	fmzlmc;
	private String	fmzlh;
	private String	zlfzjg;
	private String	hb;
	private String	zzssxq;
	private String	zzxz;
	private String	hkszddjjg;
	private String	qrdqx;
	private String	qrdpcs;
	private String	qrdjwzrq;
	private String	qrdxzjd;
	private String	qrdjwh;
	private String	qrdjlx;
	private String	qrdmlph;
	private String	qrdz;
	private String	qrdhkdjjg;
	private Long	qrdhhid;
	private String	qrdhlx;
	private String	rlhbz;
	private String	sqqrly;
	private String	bz;
	private String	spsm;
	private Long	djrid;
	private String	djsj;
	private Long	xydzid;
	private Long	spmbid;
	private String	spjg;
	private String	lsbz;
	private Long	rynbid;
	private Long	hjywid;
	private String	qrhhb;
	private String	mz;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;
	private String	cxfldm;
	private String	nyzyrklhczyydm;
	private String	kdqqy_qchjywid;
	private String	kdqqy_qcdqbm;
	private String	kdqqy_fksj;
	private String	kdqqy_fkzt;
	private String	kdqqy_fknr;
	private String	kdqqy_czyxm;
	private String	kdqqy_czydw;
	private String	kdqqy_qyzbh;
	private String	kdqqy_lscxfldm;
	private String  qyyy;//add by zjm 20210222  新增迁移原因字段
	private String  sfcsjtb;//add by zjm 20210224  是否长三角通办：0否 1是'
	public PoHJSP_HJSPSQB(){}

	public Long getSpywid(){
		return this.spywid;
	}

	public void setSpywid(Long spywid){
		this.spywid=spywid;
	}

	public String getSplx(){
		return this.splx;
	}

	public void setSplx(String splx){
		this.splx=splx;
	}

	public String getSlrq(){
		return this.slrq;
	}

	public void setSlrq(String slrq){
		this.slrq=slrq;
	}

	public String getSqrgmsfhm(){
		return this.sqrgmsfhm;
	}

	public void setSqrgmsfhm(String sqrgmsfhm){
		this.sqrgmsfhm=sqrgmsfhm;
	}

	public String getSqrxm(){
		return this.sqrxm;
	}

	public void setSqrxm(String sqrxm){
		this.sqrxm=sqrxm;
	}

	public String getSqrzzssxq(){
		return this.sqrzzssxq;
	}

	public void setSqrzzssxq(String sqrzzssxq){
		this.sqrzzssxq=sqrzzssxq;
	}

	public String getSqrzzxz(){
		return this.sqrzzxz;
	}

	public void setSqrzzxz(String sqrzzxz){
		this.sqrzzxz=sqrzzxz;
	}

	public String getSqrhkdjjg(){
		return this.sqrhkdjjg;
	}

	public void setSqrhkdjjg(String sqrhkdjjg){
		this.sqrhkdjjg=sqrhkdjjg;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getYsqrgx(){
		return this.ysqrgx;
	}

	public void setYsqrgx(String ysqrgx){
		this.ysqrgx=ysqrgx;
	}

	public String getJhny(){
		return this.jhny;
	}

	public void setJhny(String jhny){
		this.jhny=jhny;
	}

	public String getYjrclx(){
		return this.yjrclx;
	}

	public void setYjrclx(String yjrclx){
		this.yjrclx=yjrclx;
	}

	public String getYrdwjcyrysj(){
		return this.yrdwjcyrysj;
	}

	public void setYrdwjcyrysj(String yrdwjcyrysj){
		this.yrdwjcyrysj=yrdwjcyrysj;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getByxx(){
		return this.byxx;
	}

	public void setByxx(String byxx){
		this.byxx=byxx;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getBysj(){
		return this.bysj;
	}

	public void setBysj(String bysj){
		this.bysj=bysj;
	}

	public String getZyzl(){
		return this.zyzl;
	}

	public void setZyzl(String zyzl){
		this.zyzl=zyzl;
	}

	public String getByzsh(){
		return this.byzsh;
	}

	public void setByzsh(String byzsh){
		this.byzsh=byzsh;
	}

	public String getZyjszc(){
		return this.zyjszc;
	}

	public void setZyjszc(String zyjszc){
		this.zyjszc=zyjszc;
	}

	public String getJszsh(){
		return this.jszsh;
	}

	public void setJszsh(String jszsh){
		this.jszsh=jszsh;
	}

	public String getJsfzjg(){
		return this.jsfzjg;
	}

	public void setJsfzjg(String jsfzjg){
		this.jsfzjg=jsfzjg;
	}

	public String getFmzlmc(){
		return this.fmzlmc;
	}

	public void setFmzlmc(String fmzlmc){
		this.fmzlmc=fmzlmc;
	}

	public String getFmzlh(){
		return this.fmzlh;
	}

	public void setFmzlh(String fmzlh){
		this.fmzlh=fmzlh;
	}

	public String getZlfzjg(){
		return this.zlfzjg;
	}

	public void setZlfzjg(String zlfzjg){
		this.zlfzjg=zlfzjg;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getZzssxq(){
		return this.zzssxq;
	}

	public void setZzssxq(String zzssxq){
		this.zzssxq=zzssxq;
	}

	public String getZzxz(){
		return this.zzxz;
	}

	public void setZzxz(String zzxz){
		this.zzxz=zzxz;
	}

	public String getHkszddjjg(){
		return this.hkszddjjg;
	}

	public void setHkszddjjg(String hkszddjjg){
		this.hkszddjjg=hkszddjjg;
	}

	public String getQrdqx(){
		return this.qrdqx;
	}

	public void setQrdqx(String qrdqx){
		this.qrdqx=qrdqx;
	}

	public String getQrdpcs(){
		return this.qrdpcs;
	}

	public void setQrdpcs(String qrdpcs){
		this.qrdpcs=qrdpcs;
	}

	public String getQrdjwzrq(){
		return this.qrdjwzrq;
	}

	public void setQrdjwzrq(String qrdjwzrq){
		this.qrdjwzrq=qrdjwzrq;
	}

	public String getQrdxzjd(){
		return this.qrdxzjd;
	}

	public void setQrdxzjd(String qrdxzjd){
		this.qrdxzjd=qrdxzjd;
	}

	public String getQrdjwh(){
		return this.qrdjwh;
	}

	public void setQrdjwh(String qrdjwh){
		this.qrdjwh=qrdjwh;
	}

	public String getQrdjlx(){
		return this.qrdjlx;
	}

	public void setQrdjlx(String qrdjlx){
		this.qrdjlx=qrdjlx;
	}

	public String getQrdmlph(){
		return this.qrdmlph;
	}

	public void setQrdmlph(String qrdmlph){
		this.qrdmlph=qrdmlph;
	}

	public String getQrdz(){
		return this.qrdz;
	}

	public void setQrdz(String qrdz){
		this.qrdz=qrdz;
	}

	public String getQrdhkdjjg(){
		return this.qrdhkdjjg;
	}

	public void setQrdhkdjjg(String qrdhkdjjg){
		this.qrdhkdjjg=qrdhkdjjg;
	}

	public Long getQrdhhid(){
		return this.qrdhhid;
	}

	public void setQrdhhid(Long qrdhhid){
		this.qrdhhid=qrdhhid;
	}

	public String getQrdhlx(){
		return this.qrdhlx;
	}

	public void setQrdhlx(String qrdhlx){
		this.qrdhlx=qrdhlx;
	}

	public String getRlhbz(){
		return this.rlhbz;
	}

	public void setRlhbz(String rlhbz){
		this.rlhbz=rlhbz;
	}

	public String getSqqrly(){
		return this.sqqrly;
	}

	public void setSqqrly(String sqqrly){
		this.sqqrly=sqqrly;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getSpsm(){
		return this.spsm;
	}

	public void setSpsm(String spsm){
		this.spsm=spsm;
	}

	public Long getDjrid(){
		return this.djrid;
	}

	public void setDjrid(Long djrid){
		this.djrid=djrid;
	}

	public String getDjsj(){
		return this.djsj;
	}

	public void setDjsj(String djsj){
		this.djsj=djsj;
	}

	public Long getXydzid(){
		return this.xydzid;
	}

	public void setXydzid(Long xydzid){
		this.xydzid=xydzid;
	}

	public Long getSpmbid(){
		return this.spmbid;
	}

	public void setSpmbid(Long spmbid){
		this.spmbid=spmbid;
	}

	public String getSpjg(){
		return this.spjg;
	}

	public void setSpjg(String spjg){
		this.spjg=spjg;
	}

	public String getLsbz(){
		return this.lsbz;
	}

	public void setLsbz(String lsbz){
		this.lsbz=lsbz;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getQrhhb(){
		return this.qrhhb;
	}

	public void setQrhhb(String qrhhb){
		this.qrhhb=qrhhb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
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

	public String getKdqqy_qchjywid(){
		return this.kdqqy_qchjywid;
	}

	public void setKdqqy_qchjywid(String kdqqy_qchjywid){
		this.kdqqy_qchjywid=kdqqy_qchjywid;
	}

	public String getKdqqy_qcdqbm(){
		return this.kdqqy_qcdqbm;
	}

	public void setKdqqy_qcdqbm(String kdqqy_qcdqbm){
		this.kdqqy_qcdqbm=kdqqy_qcdqbm;
	}

	public String getKdqqy_fksj(){
		return this.kdqqy_fksj;
	}

	public void setKdqqy_fksj(String kdqqy_fksj){
		this.kdqqy_fksj=kdqqy_fksj;
	}

	public String getKdqqy_fkzt(){
		return this.kdqqy_fkzt;
	}

	public void setKdqqy_fkzt(String kdqqy_fkzt){
		this.kdqqy_fkzt=kdqqy_fkzt;
	}

	public String getKdqqy_fknr(){
		return this.kdqqy_fknr;
	}

	public void setKdqqy_fknr(String kdqqy_fknr){
		this.kdqqy_fknr=kdqqy_fknr;
	}

	public String getKdqqy_czyxm(){
		return this.kdqqy_czyxm;
	}

	public void setKdqqy_czyxm(String kdqqy_czyxm){
		this.kdqqy_czyxm=kdqqy_czyxm;
	}

	public String getKdqqy_czydw(){
		return this.kdqqy_czydw;
	}

	public void setKdqqy_czydw(String kdqqy_czydw){
		this.kdqqy_czydw=kdqqy_czydw;
	}

	public String getKdqqy_qyzbh(){
		return this.kdqqy_qyzbh;
	}

	public void setKdqqy_qyzbh(String kdqqy_qyzbh){
		this.kdqqy_qyzbh=kdqqy_qyzbh;
	}

	public String getKdqqy_lscxfldm(){
		return this.kdqqy_lscxfldm;
	}

	public void setKdqqy_lscxfldm(String kdqqy_lscxfldm){
		this.kdqqy_lscxfldm=kdqqy_lscxfldm;
	}

	public String getQyyy() {
		return qyyy;
	}

	public void setQyyy(String qyyy) {
		this.qyyy = qyyy;
	}

	public String getSfcsjtb() {
		return sfcsjtb;
	}

	public void setSfcsjtb(String sfcsjtb) {
		this.sfcsjtb = sfcsjtb;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}