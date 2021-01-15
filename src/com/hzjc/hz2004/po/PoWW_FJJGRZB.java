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
*分检结果日志表
*/
@Entity
@Table(name="WW_FJJGRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_FJJGRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*分检结果ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	fjjgid;
	private String	slh;
	private String	slzt;
	private String	xm;
	private String	gmsfhm;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	czsj;
	private Long	czrid;
	private String	czrip;
	private String	czrdw;
	private String	bz;
	private String	fjpch;
	private Long	cwh;
	private String	cwhdypcs;
	private Long	ryid;
	private Long	rynbid;
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
	private String	jlbz;

	public PoWW_FJJGRZB(){}

	public Long getFjjgid(){
		return this.fjjgid;
	}

	public void setFjjgid(Long fjjgid){
		this.fjjgid=fjjgid;
	}

	public String getSlh(){
		return this.slh;
	}

	public void setSlh(String slh){
		this.slh=slh;
	}

	public String getSlzt(){
		return this.slzt;
	}

	public void setSlzt(String slzt){
		this.slzt=slzt;
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

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getFjpch(){
		return this.fjpch;
	}

	public void setFjpch(String fjpch){
		this.fjpch=fjpch;
	}

	public Long getCwh(){
		return this.cwh;
	}

	public void setCwh(Long cwh){
		this.cwh=cwh;
	}

	public String getCwhdypcs(){
		return this.cwhdypcs;
	}

	public void setCwhdypcs(String cwhdypcs){
		this.cwhdypcs=cwhdypcs;
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

	public String getJlbz(){
		return this.jlbz;
	}

	public void setJlbz(String jlbz){
		this.jlbz=jlbz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}