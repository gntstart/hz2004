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
import java.sql.Timestamp;

/**
*制证视图模拟
*/
@Entity
@Table(name="VIEW_ZZST" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoVIEW_ZZST implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	jmsfzslh;
	private String	xm;
	private String	gmsfhm;
	private String	mz;
	private String	sfzzz;
	private Timestamp	yxqqsrq;
	private String	yxqjzrq;
	private String	qfjgmc;
	private String	zjztdm;
	private Timestamp	slrq;
	private String	slyy;
	private String	sdzpnr;

	public PoVIEW_ZZST(){}

	public String getJmsfzslh(){
		return this.jmsfzslh;
	}

	public void setJmsfzslh(String jmsfzslh){
		this.jmsfzslh=jmsfzslh;
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

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getSfzzz(){
		return this.sfzzz;
	}

	public void setSfzzz(String sfzzz){
		this.sfzzz=sfzzz;
	}

	public Timestamp getYxqqsrq(){
		return this.yxqqsrq;
	}

	public void setYxqqsrq(Timestamp yxqqsrq){
		this.yxqqsrq=yxqqsrq;
	}

	public String getYxqjzrq(){
		return this.yxqjzrq;
	}

	public void setYxqjzrq(String yxqjzrq){
		this.yxqjzrq=yxqjzrq;
	}

	public String getQfjgmc(){
		return this.qfjgmc;
	}

	public void setQfjgmc(String qfjgmc){
		this.qfjgmc=qfjgmc;
	}

	public String getZjztdm(){
		return this.zjztdm;
	}

	public void setZjztdm(String zjztdm){
		this.zjztdm=zjztdm;
	}

	public Timestamp getSlrq(){
		return this.slrq;
	}

	public void setSlrq(Timestamp slrq){
		this.slrq=slrq;
	}

	public String getSlyy(){
		return this.slyy;
	}

	public void setSlyy(String slyy){
		this.slyy=slyy;
	}

	public String getSdzpnr(){
		return this.sdzpnr;
	}

	public void setSdzpnr(String sdzpnr){
		this.sdzpnr=sdzpnr;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}