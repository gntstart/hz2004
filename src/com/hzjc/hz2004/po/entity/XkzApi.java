package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="zzj_Xkz_Api" )
public class XkzApi implements com.hzjc.wsstruts.po.PO{
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	
	String 	lgbm;
	String 	lgmc;
	Date		kssj;
	Date 	jzsj;
	
	String 	ip;
	String pl;
	String XKZID;
	String sbh;
    String bz;
    String 	xkz;
	String pwd;
	public String getSbh() {
		return sbh;
	}
	public void setSbh(String sbh) {
		this.sbh = sbh;
	}
	Date	qfsj;
	String IPXZ;
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getXKZID() {
		return XKZID;
	}
	public void setXKZID(String xKZID) {
		XKZID = xKZID;
	}
	public String getIPXZ() {
		return IPXZ;
	}
	public void setIPXZ(String iPXZ) {
		IPXZ = iPXZ;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getXkz() {
		return xkz;
	}
	public void setXkz(String xkz) {
		this.xkz = xkz;
	}
	
	public String getLgbm() {
		return lgbm;
	}
	public void setLgbm(String lgbm) {
		this.lgbm = lgbm;
	}
	public String getLgmc() {
		return lgmc;
	}
	public void setLgmc(String lgmc) {
		this.lgmc = lgmc;
	}
	public Date getKssj() {
		return kssj;
	}
	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}
	public Date getJssj() {
		return jzsj;
	}
	public void setJssj(Date jssj) {
		this.jzsj = jssj;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getQfsj() {
		return qfsj;
	}
	public void setQfsj(Date qfsj) {
		this.qfsj = qfsj;
	}
}
