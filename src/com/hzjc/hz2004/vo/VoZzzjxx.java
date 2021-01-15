package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoDZZJ_DZZJRZB;
import com.hzjc.hz2004.po.PoDZZJ_CZYXXB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 住址追加日志返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZzzjxx
    extends DefaultVO {

  private Long dzzjid;
  private String xm;
  private String gmsfhm;
  private String xb;
  private String mz;
  private String csrq;
  private String zjdz;
  private String sldw;
  private Long yhid;
  private String czsj;
  private Long czyid;
  private String czip;
  private String hh;
  private String ssxq;
  private String jlx;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private Long nbsfzid;
  private Long rynbid;
  private Long ryid;
  private String ktglh;

  private String czydlm;
  private String czyxm;
  private String czymm;
  private String czydwdm;
  private String czysfhm;
  private String czysfzglh;
  private String sbglh;
  private String czyzw1;
  private String czyzw2;
  private String zcsj;
  private String zxsj;
  private String czyzt;
  private String ktglhdn;
  public VoZzzjxx() {

  }

  public VoZzzjxx(PoDZZJ_DZZJRZB poDZZJ_DZZJRZB, PoDZZJ_CZYXXB poDZZJ_CZYXXB) {
    try {
      BeanUtils.copyProperties(this, poDZZJ_DZZJRZB);
      BeanUtils.copyProperties(this, poDZZJ_CZYXXB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  public void setDzzjid(Long dzzjid) {
    this.dzzjid = dzzjid;
  }

  public Long getDzzjid() {
    return dzzjid;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
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

  public void setZjdz(String zjdz) {
    this.zjdz = zjdz;
  }

  public String getZjdz() {
    return zjdz;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzsj() {
    return czsj;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public Long getCzyid() {
    return czyid;
  }

  public void setCzip(String czip) {
    this.czip = czip;
  }

  public String getCzip() {
    return czip;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }

  public String getHh() {
    return hh;
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

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public String getKtglh() {
    return ktglh;
  }

  public void setKtglh(String ktglh) {
    this.ktglh = ktglh;
  }

  public void setCzydlm(String czydlm) {
    this.czydlm = czydlm;
  }

  public String getCzydlm() {
    return czydlm;
  }

  public void setCzyxm(String czyxm) {
    this.czyxm = czyxm;
  }

  public String getCzyxm() {
    return czyxm;
  }

  public void setCzymm(String czymm) {
    this.czymm = czymm;
  }

  public String getCzymm() {
    return czymm;
  }

  public void setCzydwdm(String czydwdm) {
    this.czydwdm = czydwdm;
  }

  public String getCzydwdm() {
    return czydwdm;
  }

  public void setCzysfhm(String czysfhm) {
    this.czysfhm = czysfhm;
  }

  public String getCzysfhm() {
    return czysfhm;
  }

  public void setCzysfzglh(String czysfzglh) {
    this.czysfzglh = czysfzglh;
  }

  public String getCzysfzglh() {
    return czysfzglh;
  }

  public void setSbglh(String sbglh) {
    this.sbglh = sbglh;
  }

  public String getSbglh() {
    return sbglh;
  }

  public void setCzyzw1(String czyzw1) {
    this.czyzw1 = czyzw1;
  }

  public String getCzyzw1() {
    return czyzw1;
  }

  public void setCzyzw2(String czyzw2) {
    this.czyzw2 = czyzw2;
  }

  public String getCzyzw2() {
    return czyzw2;
  }

  public void setZcsj(String zcsj) {
    this.zcsj = zcsj;
  }

  public String getZcsj() {
    return zcsj;
  }

  public void setZxsj(String zxsj) {
    this.zxsj = zxsj;
  }

  public String getZxsj() {
    return zxsj;
  }

  public void setCzyzt(String czyzt) {
    this.czyzt = czyzt;
  }

  public String getCzyzt() {
    return czyzt;
  }

  public String getKtglhdn() {
    return ktglhdn;
  }

  public void setKtglhdn(String ktglhdn) {
    this.ktglhdn = ktglhdn;
  }

}
