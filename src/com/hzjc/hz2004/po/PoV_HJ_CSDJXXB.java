package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="V_HJ_CSDJXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_CSDJXXB implements com.hzjc.wsstruts.po.PO
{
  @GeneratedValue(generator = "generator") 
  @GenericGenerator(name = "generator", strategy = "assigned") 
  @Id
  private Long csdjid;
  private Long rynbid;
  private String cszmbh;
  private String csdjlb;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String cssj;
  private String csdgjdq;
  private String csdssxq;
  private String csdxz;
  private String jhrygmsfhm;
  private String jhryxm;
  private String jhryjhgx;
  private String jhregmsfhm;
  private String jhrexm;
  private String jhrejhgx;
  private String fqgmsfhm;
  private String fqxm;
  private String mqgmsfhm;
  private String mqxm;
  private String jggjdq;
  private String jgssxq;
  private String xx;
  private String hb;
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
  private String ywlx;
  private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private Long hhnbid;
  private String hh;
  private String hlx;
  private String hzxm;
  private String yhzgx;
  private String hzgmsfhm;

  public void setCsdjid(Long csdjid) {
    this.csdjid = csdjid;
  }

  public Long getCsdjid() {
    return csdjid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setCszmbh(String cszmbh) {
    this.cszmbh = cszmbh;
  }

  public String getCszmbh() {
    return cszmbh;
  }

  public void setCsdjlb(String csdjlb) {
    this.csdjlb = csdjlb;
  }

  public String getCsdjlb() {
    return csdjlb;
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

  public void setCsdgjdq(String csdgjdq) {
    this.csdgjdq = csdgjdq;
  }

  public String getCsdgjdq() {
    return csdgjdq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setCsdxz(String csdxz) {
    this.csdxz = csdxz;
  }

  public String getCsdxz() {
    return csdxz;
  }

  public void setJhrygmsfhm(String jhrygmsfhm) {
    this.jhrygmsfhm = jhrygmsfhm;
  }

  public String getJhrygmsfhm() {
    return jhrygmsfhm;
  }

  public void setJhryxm(String jhryxm) {
    this.jhryxm = jhryxm;
  }

  public String getJhryxm() {
    return jhryxm;
  }

  public void setJhryjhgx(String jhryjhgx) {
    this.jhryjhgx = jhryjhgx;
  }

  public String getJhryjhgx() {
    return jhryjhgx;
  }

  public void setJhregmsfhm(String jhregmsfhm) {
    this.jhregmsfhm = jhregmsfhm;
  }

  public String getJhregmsfhm() {
    return jhregmsfhm;
  }

  public void setJhrexm(String jhrexm) {
    this.jhrexm = jhrexm;
  }

  public String getJhrexm() {
    return jhrexm;
  }

  public void setJhrejhgx(String jhrejhgx) {
    this.jhrejhgx = jhrejhgx;
  }

  public String getJhrejhgx() {
    return jhrejhgx;
  }

  public void setFqgmsfhm(String fqgmsfhm) {
    this.fqgmsfhm = fqgmsfhm;
  }

  public String getFqgmsfhm() {
    return fqgmsfhm;
  }

  public void setFqxm(String fqxm) {
    this.fqxm = fqxm;
  }

  public String getFqxm() {
    return fqxm;
  }

  public void setMqgmsfhm(String mqgmsfhm) {
    this.mqgmsfhm = mqgmsfhm;
  }

  public String getMqgmsfhm() {
    return mqgmsfhm;
  }

  public void setMqxm(String mqxm) {
    this.mqxm = mqxm;
  }

  public String getMqxm() {
    return mqxm;
  }

  public void setJggjdq(String jggjdq) {
    this.jggjdq = jggjdq;
  }

  public String getJggjdq() {
    return jggjdq;
  }

  public void setJgssxq(String jgssxq) {
    this.jgssxq = jgssxq;
  }

  public String getJgssxq() {
    return jgssxq;
  }

  public void setXx(String xx) {
    this.xx = xx;
  }

  public String getXx() {
    return xx;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public String getHb() {
    return hb;
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

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getHhnbid() {
    return hhnbid;
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

  public void setHzxm(String hzxm) {
    this.hzxm = hzxm;
  }

  public String getHzxm() {
    return hzxm;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setHzgmsfhm(String hzgmsfhm) {
    this.hzgmsfhm = hzgmsfhm;
  }

  public String getHzgmsfhm() {
    return hzgmsfhm;
  }

}
