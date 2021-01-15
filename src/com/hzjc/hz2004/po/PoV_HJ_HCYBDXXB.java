package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="V_HJ_HCYBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_HCYBDXXB implements com.hzjc.wsstruts.po.PO
{
  /*
  *接口ID
  */
  @GeneratedValue(generator = "generator") 
  @GenericGenerator(name = "generator", strategy = "assigned") 
  @Id
  private Long hcybdid;
  private Long rynbid;
  private String ygx;
  private String xgx;
  private String hcybdlb;
  private String hcybdlx;
  private String hcybdrq;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private Long mlpnbid;
  private Long hhnbid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
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
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private Long	hhid;
  private String hh;
  private String hlx;

  public void setHcybdid(Long hcybdid) {
    this.hcybdid = hcybdid;
  }

  public Long getHcybdid() {
    return hcybdid;
  }

  public Long getHhid() {
	return hhid;
}

public void setHhid(Long hhid) {
	this.hhid = hhid;
}

public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setYgx(String ygx) {
    this.ygx = ygx;
  }

  public String getYgx() {
    return ygx;
  }

  public void setXgx(String xgx) {
    this.xgx = xgx;
  }

  public String getXgx() {
    return xgx;
  }

  public void setHcybdlb(String hcybdlb) {
    this.hcybdlb = hcybdlb;
  }

  public String getHcybdlb() {
    return hcybdlb;
  }

  public void setHcybdlx(String hcybdlx) {
    this.hcybdlx = hcybdlx;
  }

  public String getHcybdlx() {
    return hcybdlx;
  }

  public void setHcybdrq(String hcybdrq) {
    this.hcybdrq = hcybdrq;
  }

  public String getHcybdrq() {
    return hcybdrq;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxhjywid(Long cxhjywid) {
    this.cxhjywid = cxhjywid;
  }

  public Long getCxhjywid() {
    return cxhjywid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getHhnbid() {
    return hhnbid;
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

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public String getCssj() {
    return cssj;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
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

  public void setHh(String hh) {
    this.hh = hh;
  }

  public String getHh() {
    return hh;
  }

  public void setHlx(String hlx) {
    this.hlx = hlx;
  }

  public String getHlx() {
    return hlx;
  }

}
