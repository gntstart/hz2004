package com.hzjc.hz2004.po;

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
@Table(name="V_ZJ_SHQFXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_ZJ_SHQFXXB
    implements com.hzjc.wsstruts.po.PO {
	/*
	*分检结果ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long shqfid;
  private Long rynbid;
  private Long nbslid;
  private String shqflx;
  private String shrq;
  private Long shrid;
  private String shdw;
  private String shqk;
  private String qfrq;
  private Long qfrid;
  private String qfdw;
  private Long zjywid;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private Long cxzjywid;
  private Long ryid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String csdssxq;
  private Long mlpnbid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String pxh;
  private String ywbz;
  private Long czyid;
  private String czsj;
  private String slh;
  private Long zpid;
  private String qfjg;
  private String yxqxqsrq;
  private String yxqxjzrq;
  private String zz;
  private String slyy;
  private String zzlx;
  private String lqfs;
  private String sflx;
  private Long sfje;
  private String slzt;
  private String jlbz;
  public void setShqfid(Long shqfid) {
    this.shqfid = shqfid;
  }

  public Long getShqfid() {
    return shqfid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setShqflx(String shqflx) {
    this.shqflx = shqflx;
  }

  public String getShqflx() {
    return shqflx;
  }

  public void setShrq(String shrq) {
    this.shrq = shrq;
  }

  public String getShrq() {
    return shrq;
  }

  public void setShdw(String shdw) {
    this.shdw = shdw;
  }

  public String getShdw() {
    return shdw;
  }

  public void setShqk(String shqk) {
    this.shqk = shqk;
  }

  public String getShqk() {
    return shqk;
  }

  public void setQfrq(String qfrq) {
    this.qfrq = qfrq;
  }

  public String getQfrq() {
    return qfrq;
  }

  public void setQfrid(Long qfrid) {
    this.qfrid = qfrid;
  }

  public Long getQfrid() {
    return qfrid;
  }

  public void setQfdw(String qfdw) {
    this.qfdw = qfdw;
  }

  public String getQfdw() {
    return qfdw;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxzjywid(Long cxzjywid) {
    this.cxzjywid = cxzjywid;
  }

  public Long getCxzjywid() {
    return cxzjywid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJlx() {
    return jlx;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPcs() {
    return pcs;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getPxh() {
    return pxh;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public Long getCzyid() {
    return czyid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzsj() {
    return czsj;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getSlh() {
    return slh;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public String getZz() {
    return zz;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public String getSlyy() {
    return slyy;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public String getLqfs() {
    return lqfs;
  }

  public void setSflx(String sflx) {
    this.sflx = sflx;
  }

  public String getSflx() {
    return sflx;
  }

  public void setSfje(Long sfje) {
    this.sfje = sfje;
  }

  public Long getSfje() {
    return sfje;
  }

  public void setSlzt(String slzt) {
    this.slzt = slzt;
  }

  public String getSlzt() {
    return slzt;
  }

  public Long getShrid() {
    return shrid;
  }

  public void setShrid(Long shrid) {
    this.shrid = shrid;
  }

  public String getJlbz() {
 	return jlbz;
  }

  public void setJlbz(String jlbz) {
 	this.jlbz = jlbz;
  }

}
