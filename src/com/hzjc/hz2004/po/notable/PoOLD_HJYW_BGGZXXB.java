package com.hzjc.hz2004.po.notable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="OLD_HJYW_BGGZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoOLD_HJYW_BGGZXXB
    implements com.hzjc.wsstruts.po.PO {
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
  private Long bggzid;
  private Long rynbid;
  private Long ryid;
  private String bggzxm;
  private String bggzlb;
  private String bggzrq;
  private String bggzqnr;
  private String bggzhnr;
  private String dybz;
  private Long hjywid;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private String bgqxm;
  private String bgqgmsfhm;

  private String xm;
  private String gmsfhm;
  private String mz;
  private String xb;
  private String csrq;
  private String cssj;
  private String csdssxq;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String pxh;
  private Long hhnbid;
  private Long mlpnbid;
  private String ryzt;
  private String bggzqdm;
  private String bggzhdm;

  public void setBggzid(Long bggzid) {
    this.bggzid = bggzid;
  }

  public Long getBggzid() {
    return bggzid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setBggzxm(String bggzxm) {
    this.bggzxm = bggzxm;
  }

  public String getBggzxm() {
    return bggzxm;
  }

  public void setBggzlb(String bggzlb) {
    this.bggzlb = bggzlb;
  }

  public String getBggzlb() {
    return bggzlb;
  }

  public void setBggzrq(String bggzrq) {
    this.bggzrq = bggzrq;
  }

  public String getBggzrq() {
    return bggzrq;
  }

  public void setBggzqnr(String bggzqnr) {
    this.bggzqnr = bggzqnr;
  }

  public String getBggzqnr() {
    return bggzqnr;
  }

  public void setBggzhnr(String bggzhnr) {
    this.bggzhnr = bggzhnr;
  }

  public String getBggzhnr() {
    return bggzhnr;
  }

  public void setDybz(String dybz) {
    this.dybz = dybz;
  }

  public String getDybz() {
    return dybz;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSldw() {
    return sldw;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public Long getSlrid() {
    return slrid;
  }

  public void setSlrid(Long slrid) {
    this.slrid = slrid;
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

  public String getBgqgmsfhm() {
    return bgqgmsfhm;
  }

  public void setBgqgmsfhm(String bgqgmsfhm) {
    this.bgqgmsfhm = bgqgmsfhm;
  }

  public String getBgqxm() {
    return bgqxm;
  }

  public void setBgqxm(String bgqxm) {
    this.bgqxm = bgqxm;
  }

  public String getCssj() {
    return cssj;
  }

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJlx() {
    return jlx;
  }

  public String getMlph() {
    return mlph;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public String getMlxz() {
    return mlxz;
  }

  public String getMz() {
    return mz;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getPcs() {
    return pcs;
  }

  public String getPxh() {
    return pxh;
  }
  public String getXm() {
    return xm;
  }
  public void setXm(String xm) {
    this.xm = xm;
  }
  public void setXb(String xb) {
    this.xb = xb;
  }
  public String getXb() {
    return xb;
  }
  public String getXzjd() {
    return xzjd;
  }
  public String getZrq() {
    return zrq;
  }
  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }
  public void setZrq(String zrq) {
    this.zrq = zrq;
  }
  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }
  public String getSsxq() {
    return ssxq;
  }
  public String getCsdssxq() {
    return csdssxq;
  }
  public String getCsrq() {
    return csrq;
  }
  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }
  public void setPcs(String pcs) {
    this.pcs = pcs;
  }
  public void setPxh(String pxh) {
    this.pxh = pxh;
  }
  public String getRyzt() {
    return ryzt;
  }
  public void setRyzt(String ryzt) {
    this.ryzt = ryzt;
  }
  public String getBggzqdm() {
    return bggzqdm;
  }
  public void setBggzqdm(String bggzqdm) {
    this.bggzqdm = bggzqdm;
  }
  public String getBggzhdm() {
    return bggzhdm;
  }
  public void setBggzhdm(String bggzhdm) {
    this.bggzhdm = bggzhdm;
  }

}
