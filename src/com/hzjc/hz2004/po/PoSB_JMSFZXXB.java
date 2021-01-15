package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="SB_JMSFZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSB_JMSFZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	nbsfzid;
	private String	ssxq;
	private String	slh;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	csrq;
	private String	mz;
	private String	zz;
	private Long zpid;
	private String	qfjg;
	private String	yxqxqsrq;
	private String	yxqxjzrq;
	private String	slyy;
	private String	slsj;
	private Long ryid;
	private Long rynbid;
	private String	pcs;
	private String	ssxjjgjgdm;
	private String	ssxjjgjgmc;
	private String	sspcsjgdm;
	private String	sspcsmc;
	private Long tbbz;
	private String	bwbh;
	private String	czsj;
	private Long	czrid;
	private String	czrdw;
	public PoSB_JMSFZXXB(){}

	public Long getNbsfzid() {
		return nbsfzid;
	}

	public void setNbsfzid(Long nbsfzid) {
		this.nbsfzid = nbsfzid;
	}

	public String getSsxq() {
		return ssxq;
	}

	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}

	public String getSlh() {
		return slh;
	}

	public void setSlh(String slh) {
		this.slh = slh;
	}

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public Long getZpid() {
		return zpid;
	}

	public void setZpid(Long zpid) {
		this.zpid = zpid;
	}

	public String getQfjg() {
		return qfjg;
	}

	public void setQfjg(String qfjg) {
		this.qfjg = qfjg;
	}

	public String getYxqxqsrq() {
		return yxqxqsrq;
	}

	public void setYxqxqsrq(String yxqxqsrq) {
		this.yxqxqsrq = yxqxqsrq;
	}

	public String getYxqxjzrq() {
		return yxqxjzrq;
	}

	public void setYxqxjzrq(String yxqxjzrq) {
		this.yxqxjzrq = yxqxjzrq;
	}

	public String getSlyy() {
		return slyy;
	}

	public void setSlyy(String slyy) {
		this.slyy = slyy;
	}

	public String getSlsj() {
		return slsj;
	}

	public void setSlsj(String slsj) {
		this.slsj = slsj;
	}

	public Long getRyid() {
		return ryid;
	}

	public void setRyid(Long ryid) {
		this.ryid = ryid;
	}

	public Long getRynbid() {
		return rynbid;
	}

	public void setRynbid(Long rynbid) {
		this.rynbid = rynbid;
	}

	public String getPcs() {
		return pcs;
	}

	public void setPcs(String pcs) {
		this.pcs = pcs;
	}

	public String getSsxjjgjgdm() {
		return ssxjjgjgdm;
	}

	public void setSsxjjgjgdm(String ssxjjgjgdm) {
		this.ssxjjgjgdm = ssxjjgjgdm;
	}

	public String getSsxjjgjgmc() {
		return ssxjjgjgmc;
	}

	public void setSsxjjgjgmc(String ssxjjgjgmc) {
		this.ssxjjgjgmc = ssxjjgjgmc;
	}

	public String getSspcsjgdm() {
		return sspcsjgdm;
	}

	public void setSspcsjgdm(String sspcsjgdm) {
		this.sspcsjgdm = sspcsjgdm;
	}

	public String getSspcsmc() {
		return sspcsmc;
	}

	public void setSspcsmc(String sspcsmc) {
		this.sspcsmc = sspcsmc;
	}

	public Long getTbbz() {
		return tbbz;
	}

	public void setTbbz(Long tbbz) {
		this.tbbz = tbbz;
	}

	public String getBwbh() {
		return bwbh;
	}

	public void setBwbh(String bwbh) {
		this.bwbh = bwbh;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public Long getCzrid() {
		return czrid;
	}

	public void setCzrid(Long czrid) {
		this.czrid = czrid;
	}

	public String getCzrdw() {
		return czrdw;
	}

	public void setCzrdw(String czrdw) {
		this.czrdw = czrdw;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}