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
*门（楼）牌详细信息表
*/
@Entity
@Table(name="HJXX_MLPXXXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_MLPXXXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*门（楼）牌内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	mlpnbid;
	private Long	mlpid;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	jdlb;
	private String	cdlb;
	private String	jdsj;
	private String	cdsj;
	private Long	cjhjywid;
	private Long	cchjywid;
	private String	mlpzt;
	private Long	lxdbid;
	private String	jlbz;
	private String	qysj;
	private String	jssj;
	private String	pxh;
	private String	bzdz;
	private String	bzdzid;
	private String	bzdzx;
	private String	bzdzy;
	private String	bzdzst;

	public PoHJXX_MLPXXXXB(){}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
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

	public String getJdsj(){
		return this.jdsj;
	}

	public void setJdsj(String jdsj){
		this.jdsj=jdsj;
	}

	public String getCdsj(){
		return this.cdsj;
	}

	public void setCdsj(String cdsj){
		this.cdsj=cdsj;
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

	public String getMlpzt(){
		return this.mlpzt;
	}

	public void setMlpzt(String mlpzt){
		this.mlpzt=mlpzt;
	}

	public Long getLxdbid(){
		return this.lxdbid;
	}

	public void setLxdbid(Long lxdbid){
		this.lxdbid=lxdbid;
	}

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
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

	public String getPxh(){
		return this.pxh;
	}

	public void setPxh(String pxh){
		this.pxh=pxh;
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

	public static long getSerialversionuid() {			return serialVersionUID;	}}