package com.hzjc.hz2004.po.notable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="OLD_HJTJ_RYBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoOLD_HJTJ_RYBDXXB
    implements com.hzjc.wsstruts.po.PO {
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long rybdid;
  private Long ryid;
  private Long rynbid;
  private Long mlpnbid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String rylb;
  private String hb;
  private String yhzgx;
  private String ywnr;
  private String bdqhb;
  private Long bdbid;
  private String bdyy;
  private String bdfw;
  private String bdrq;
  private Long bdqhhnbid;
  private String bdqhh;
  private String bdqhlx;
  private Long bdqmlpnbid;
  private String bdqssxq;
  private String bdqjlx;
  private String bdqmlph;
  private String bdqmlxz;
  private String bdqpcs;
  private String bdqzrq;
  private String bdqxzjd;
  private String bdqjcwh;
  private Long bdhhhnbid;
  private String bdhhh;
  private String bdhhlx;
  private Long bdhmlpnbid;
  private String bdhssxq;
  private String bdhjlx;
  private String bdhmlph;
  private String bdhmlxz;
  private String bdhpcs;
  private String bdhzrq;
  private String bdhxzjd;
  private String bdhjcwh;
  private Long hjywid;
  private String ywbz;
  private String ywlx;
  private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private Long rzjs;
  private Long hzjs;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private String hzxm; //户主姓名
  private String hzgmsfhm; //户主公民身份号码
  private Long mlpid;
  private String jcwh;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String pxh;

  public void setRybdid(Long rybdid) {
    this.rybdid = rybdid;
  }

  public Long getRybdid() {
    return rybdid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
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

  public void setRylb(String rylb) {
    this.rylb = rylb;
  }

  public String getRylb() {
    return rylb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public String getHb() {
    return hb;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYwnr(String ywnr) {
    this.ywnr = ywnr;
  }

  public String getYwnr() {
    return ywnr;
  }

  public void setBdqhb(String bdqhb) {
    this.bdqhb = bdqhb;
  }

  public String getBdqhb() {
    return bdqhb;
  }

  public void setBdbid(Long bdbid) {
    this.bdbid = bdbid;
  }

  public Long getBdbid() {
    return bdbid;
  }

  public void setBdyy(String bdyy) {
    this.bdyy = bdyy;
  }

  public String getBdyy() {
    return bdyy;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setBdrq(String bdrq) {
    this.bdrq = bdrq;
  }

  public String getBdrq() {
    return bdrq;
  }

  public void setBdqhhnbid(Long bdqhhnbid) {
    this.bdqhhnbid = bdqhhnbid;
  }

  public Long getBdqhhnbid() {
    return bdqhhnbid;
  }

  public void setBdqhh(String bdqhh) {
    this.bdqhh = bdqhh;
  }

  public String getBdqhh() {
    return bdqhh;
  }

  public void setBdqhlx(String bdqhlx) {
    this.bdqhlx = bdqhlx;
  }

  public String getBdqhlx() {
    return bdqhlx;
  }

  public void setBdqmlpnbid(Long bdqmlpnbid) {
    this.bdqmlpnbid = bdqmlpnbid;
  }

  public Long getBdqmlpnbid() {
    return bdqmlpnbid;
  }

  public void setBdqssxq(String bdqssxq) {
    this.bdqssxq = bdqssxq;
  }

  public String getBdqssxq() {
    return bdqssxq;
  }

  public void setBdqjlx(String bdqjlx) {
    this.bdqjlx = bdqjlx;
  }

  public String getBdqjlx() {
    return bdqjlx;
  }

  public void setBdqmlph(String bdqmlph) {
    this.bdqmlph = bdqmlph;
  }

  public String getBdqmlph() {
    return bdqmlph;
  }

  public void setBdqmlxz(String bdqmlxz) {
    this.bdqmlxz = bdqmlxz;
  }

  public String getBdqmlxz() {
    return bdqmlxz;
  }

  public void setBdqpcs(String bdqpcs) {
    this.bdqpcs = bdqpcs;
  }

  public String getBdqpcs() {
    return bdqpcs;
  }

  public void setBdqzrq(String bdqzrq) {
    this.bdqzrq = bdqzrq;
  }

  public String getBdqzrq() {
    return bdqzrq;
  }

  public void setBdqxzjd(String bdqxzjd) {
    this.bdqxzjd = bdqxzjd;
  }

  public String getBdqxzjd() {
    return bdqxzjd;
  }

  public void setBdqjcwh(String bdqjcwh) {
    this.bdqjcwh = bdqjcwh;
  }

  public String getBdqjcwh() {
    return bdqjcwh;
  }

  public void setBdhhhnbid(Long bdhhhnbid) {
    this.bdhhhnbid = bdhhhnbid;
  }

  public Long getBdhhhnbid() {
    return bdhhhnbid;
  }

  public void setBdhhh(String bdhhh) {
    this.bdhhh = bdhhh;
  }

  public String getBdhhh() {
    return bdhhh;
  }

  public void setBdhhlx(String bdhhlx) {
    this.bdhhlx = bdhhlx;
  }

  public String getBdhhlx() {
    return bdhhlx;
  }

  public void setBdhmlpnbid(Long bdhmlpnbid) {
    this.bdhmlpnbid = bdhmlpnbid;
  }

  public Long getBdhmlpnbid() {
    return bdhmlpnbid;
  }

  public void setBdhssxq(String bdhssxq) {
    this.bdhssxq = bdhssxq;
  }

  public String getBdhssxq() {
    return bdhssxq;
  }

  public void setBdhjlx(String bdhjlx) {
    this.bdhjlx = bdhjlx;
  }

  public String getBdhjlx() {
    return bdhjlx;
  }

  public void setBdhmlph(String bdhmlph) {
    this.bdhmlph = bdhmlph;
  }

  public String getBdhmlph() {
    return bdhmlph;
  }

  public void setBdhmlxz(String bdhmlxz) {
    this.bdhmlxz = bdhmlxz;
  }

  public String getBdhmlxz() {
    return bdhmlxz;
  }

  public void setBdhpcs(String bdhpcs) {
    this.bdhpcs = bdhpcs;
  }

  public String getBdhpcs() {
    return bdhpcs;
  }

  public void setBdhzrq(String bdhzrq) {
    this.bdhzrq = bdhzrq;
  }

  public String getBdhzrq() {
    return bdhzrq;
  }

  public void setBdhxzjd(String bdhxzjd) {
    this.bdhxzjd = bdhxzjd;
  }

  public String getBdhxzjd() {
    return bdhxzjd;
  }

  public void setBdhjcwh(String bdhjcwh) {
    this.bdhjcwh = bdhjcwh;
  }

  public String getBdhjcwh() {
    return bdhjcwh;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setCzsm(Long czsm) {
    this.czsm = czsm;
  }

  public Long getCzsm() {
    return czsm;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

  public String getSlsj() {
    return slsj;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setSlrid(Long slrid) {
    this.slrid = slrid;
  }

  public Long getSlrid() {
    return slrid;
  }

  public void setRzjs(Long rzjs) {
    this.rzjs = rzjs;
  }

  public Long getRzjs() {
    return rzjs;
  }

  public void setHzjs(Long hzjs) {
    this.hzjs = hzjs;
  }

  public Long getHzjs() {
    return hzjs;
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

  public String getHzgmsfhm() {
    return hzgmsfhm;
  }

  public void setHzgmsfhm(String hzgmsfhm) {
    this.hzgmsfhm = hzgmsfhm;
  }

  public String getHzxm() {
    return hzxm;
  }

  public void setHzxm(String hzxm) {
    this.hzxm = hzxm;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getPcs() {
    return pcs;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getPxh() {
    return pxh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public Long getMlpid() {
    return mlpid;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

}
