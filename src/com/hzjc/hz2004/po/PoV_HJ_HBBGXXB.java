package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="V_HJ_HBBGXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_HBBGXXB implements com.hzjc.wsstruts.po.PO
{
  @GeneratedValue(generator = "generator") 
  @GenericGenerator(name = "generator", strategy = "assigned") 
  @Id	
  private Long hbbgid;
  private Long rynbid;
  private String bgqhb;
  private String bghhb;
  private String hbbglb;
  private String hbbgrq;
  private String bdfw;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private Long hhnbid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String cssj;
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
  private String ywlx;
  private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private String hzxm;
  private String yhzgx;
  private String hzgmsfhm;

  public void setHbbgid(Long hbbgid) {
    this.hbbgid = hbbgid;
  }

  public Long getHbbgid() {
    return hbbgid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setBgqhb(String bgqhb) {
    this.bgqhb = bgqhb;
  }

  public String getBgqhb() {
    return bgqhb;
  }

  public void setBghhb(String bghhb) {
    this.bghhb = bghhb;
  }

  public String getBghhb() {
    return bghhb;
  }

  public void setHbbglb(String hbbglb) {
    this.hbbglb = hbbglb;
  }

  public String getHbbglb() {
    return hbbglb;
  }

  public void setHbbgrq(String hbbgrq) {
    this.hbbgrq = hbbgrq;
  }

  public String getHbbgrq() {
    return hbbgrq;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdfw() {
    return bdfw;
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
