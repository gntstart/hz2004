package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="V_HJ_ZZBDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_ZZBDXXB implements com.hzjc.wsstruts.po.PO
{
  @GeneratedValue(generator = "generator") 
  @GenericGenerator(name = "generator", strategy = "assigned") 
  @Id
  private Long zzbdid;
  private Long rynbid;
  private Long yhhnbid;
  private String zzbdlb;
  private String zzbdrq;
  private String qyzbh;
  private String bdfw;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private Long xhhnbid;
  private String gmsfhm;
  private String xm;
  private String xb;
  private String mz;
  private String csrq;
  private String cssj;
  private String csdssxq;
  private String hb;
  private String hb_q;
  private String hh_q;
  private String hlx_q;
  private String hh;
  private String hlx;
  private Long mlpnbid_q;
  private String ssxq_q;
  private String jlx_q;
  private String mlph_q;
  private String mlxz_q;
  private String pcs_q;
  private String zrq_q;
  private String xzjd_q;
  private String jcwh_q;
  private String jdlb_q;
  private String cdlb_q;
  private String pxh_q;
  private Long mlpnbid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String jdlb;
  private String cdlb;
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

  public void setZzbdid(Long zzbdid) {
    this.zzbdid = zzbdid;
  }

  public Long getZzbdid() {
    return zzbdid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setYhhnbid(Long yhhnbid) {
    this.yhhnbid = yhhnbid;
  }

  public Long getYhhnbid() {
    return yhhnbid;
  }

  public void setZzbdlb(String zzbdlb) {
    this.zzbdlb = zzbdlb;
  }

  public String getZzbdlb() {
    return zzbdlb;
  }

  public void setZzbdrq(String zzbdrq) {
    this.zzbdrq = zzbdrq;
  }

  public String getZzbdrq() {
    return zzbdrq;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public String getQyzbh() {
    return qyzbh;
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

  public void setXhhnbid(Long xhhnbid) {
    this.xhhnbid = xhhnbid;
  }

  public Long getXhhnbid() {
    return xhhnbid;
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

  public void setHb(String hb) {
    this.hb = hb;
  }

  public String getHb() {
    return hb;
  }

  public void setHb_q(String hb_q) {
    this.hb_q = hb_q;
  }

  public String getHb_q() {
    return hb_q;
  }

  public void setHh_q(String hh_q) {
    this.hh_q = hh_q;
  }

  public String getHh_q() {
    return hh_q;
  }

  public void setHlx_q(String hlx_q) {
    this.hlx_q = hlx_q;
  }

  public String getHlx_q() {
    return hlx_q;
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

  public void setMlpnbid_q(Long mlpnbid_q) {
    this.mlpnbid_q = mlpnbid_q;
  }

  public Long getMlpnbid_q() {
    return mlpnbid_q;
  }

  public void setSsxq_q(String ssxq_q) {
    this.ssxq_q = ssxq_q;
  }

  public String getSsxq_q() {
    return ssxq_q;
  }

  public void setJlx_q(String jlx_q) {
    this.jlx_q = jlx_q;
  }

  public String getJlx_q() {
    return jlx_q;
  }

  public void setMlph_q(String mlph_q) {
    this.mlph_q = mlph_q;
  }

  public String getMlph_q() {
    return mlph_q;
  }

  public void setMlxz_q(String mlxz_q) {
    this.mlxz_q = mlxz_q;
  }

  public String getMlxz_q() {
    return mlxz_q;
  }

  public void setPcs_q(String pcs_q) {
    this.pcs_q = pcs_q;
  }

  public String getPcs_q() {
    return pcs_q;
  }

  public void setZrq_q(String zrq_q) {
    this.zrq_q = zrq_q;
  }

  public String getZrq_q() {
    return zrq_q;
  }

  public void setXzjd_q(String xzjd_q) {
    this.xzjd_q = xzjd_q;
  }

  public String getXzjd_q() {
    return xzjd_q;
  }

  public void setJcwh_q(String jcwh_q) {
    this.jcwh_q = jcwh_q;
  }

  public String getJcwh_q() {
    return jcwh_q;
  }

  public void setJdlb_q(String jdlb_q) {
    this.jdlb_q = jdlb_q;
  }

  public String getJdlb_q() {
    return jdlb_q;
  }

  public void setCdlb_q(String cdlb_q) {
    this.cdlb_q = cdlb_q;
  }

  public String getCdlb_q() {
    return cdlb_q;
  }

  public void setPxh_q(String pxh_q) {
    this.pxh_q = pxh_q;
  }

  public String getPxh_q() {
    return pxh_q;
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

  public void setJdlb(String jdlb) {
    this.jdlb = jdlb;
  }

  public String getJdlb() {
    return jdlb;
  }

  public void setCdlb(String cdlb) {
    this.cdlb = cdlb;
  }

  public String getCdlb() {
    return cdlb;
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
