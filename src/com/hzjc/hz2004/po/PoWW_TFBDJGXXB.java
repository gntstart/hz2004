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
*逃犯比对结果信息表
*/
@Entity
@Table(name="WW_TFBDJGXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_TFBDJGXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*比对ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bdid;
	private String	bdywbh;
	private String	pptj;
	private String	bdyj;
	private String	czsj;
	private Long	czrid;
	private String	czrip;
	private String	czrdw;
	private String	czrxm;
	private String	czrdlm;
	private Long	shrid;
	private String	shsj;
	private String	cljg;
	private String	clsm;
	private Long	clrid;
	private String	clsj;
	private String	clrip;
	private String	ssxq;
	private String	jlx;
	private String	mlph;
	private String	mlxz;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private Long	rynbid;
	private String	xm;
	private String	gmsfhm;
	private String	jlbz;

	public PoWW_TFBDJGXXB(){}

	public Long getBdid(){
		return this.bdid;
	}

	public void setBdid(Long bdid){
		this.bdid=bdid;
	}

	public String getBdywbh(){
		return this.bdywbh;
	}

	public void setBdywbh(String bdywbh){
		this.bdywbh=bdywbh;
	}

	public String getPptj(){
		return this.pptj;
	}

	public void setPptj(String pptj){
		this.pptj=pptj;
	}

	public String getBdyj(){
		return this.bdyj;
	}

	public void setBdyj(String bdyj){
		this.bdyj=bdyj;
	}

	public String getCzsj(){
		return this.czsj;
	}

	public void setCzsj(String czsj){
		this.czsj=czsj;
	}

	public Long getCzrid(){
		return this.czrid;
	}

	public void setCzrid(Long czrid){
		this.czrid=czrid;
	}

	public String getCzrip(){
		return this.czrip;
	}

	public void setCzrip(String czrip){
		this.czrip=czrip;
	}

	public String getCzrdw(){
		return this.czrdw;
	}

	public void setCzrdw(String czrdw){
		this.czrdw=czrdw;
	}

	public String getCzrxm(){
		return this.czrxm;
	}

	public void setCzrxm(String czrxm){
		this.czrxm=czrxm;
	}

	public String getCzrdlm(){
		return this.czrdlm;
	}

	public void setCzrdlm(String czrdlm){
		this.czrdlm=czrdlm;
	}

	public Long getShrid(){
		return this.shrid;
	}

	public void setShrid(Long shrid){
		this.shrid=shrid;
	}

	public String getShsj(){
		return this.shsj;
	}

	public void setShsj(String shsj){
		this.shsj=shsj;
	}

	public String getCljg(){
		return this.cljg;
	}

	public void setCljg(String cljg){
		this.cljg=cljg;
	}

	public String getClsm(){
		return this.clsm;
	}

	public void setClsm(String clsm){
		this.clsm=clsm;
	}

	public Long getClrid(){
		return this.clrid;
	}

	public void setClrid(Long clrid){
		this.clrid=clrid;
	}

	public String getClsj(){
		return this.clsj;
	}

	public void setClsj(String clsj){
		this.clsj=clsj;
	}

	public String getClrip(){
		return this.clrip;
	}

	public void setClrip(String clrip){
		this.clrip=clrip;
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

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}