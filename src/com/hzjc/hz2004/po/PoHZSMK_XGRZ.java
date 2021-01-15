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
*杭州市民卡修改卡内信息日志表
*/
@Entity
@Table(name="HZSMK_XGRZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZSMK_XGRZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*修改日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	xgrzid;
	private Long	xkrzid;
	private Long	rynbid;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private String	ssxq;
	private String	jlx;
	private String	pcs;
	private String	zrq;
	private String	xzjd;
	private String	jcwh;
	private String	xgzdm;
	private String	xgqnr;
	private String	xghnr;

	public PoHZSMK_XGRZ(){}

	public Long getXgrzid(){
		return this.xgrzid;
	}

	public void setXgrzid(Long xgrzid){
		this.xgrzid=xgrzid;
	}

	public Long getXkrzid(){
		return this.xkrzid;
	}

	public void setXkrzid(Long xkrzid){
		this.xkrzid=xkrzid;
	}

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

	public String getXgzdm(){
		return this.xgzdm;
	}

	public void setXgzdm(String xgzdm){
		this.xgzdm=xgzdm;
	}

	public String getXgqnr(){
		return this.xgqnr;
	}

	public void setXgqnr(String xgqnr){
		this.xgqnr=xgqnr;
	}

	public String getXghnr(){
		return this.xghnr;
	}

	public void setXghnr(String xghnr){
		this.xghnr=xghnr;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}