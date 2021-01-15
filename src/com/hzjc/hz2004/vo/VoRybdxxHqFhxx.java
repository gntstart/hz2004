package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJTJ_RYBDXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 人员变动信息获取返回信息(四变信息)
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoRybdxxHqFhxx
    extends DefaultVO {

  public VoRybdxxHqFhxx() {
  }

  public VoRybdxxHqFhxx(PoHJTJ_RYBDXXB poRybdxx, PoHJXX_MLPXXXXB poMlpxxxx) {
    try {
      if (poMlpxxxx != null) {
        BeanUtils.copyProperties(this, poMlpxxxx);
      }
      BeanUtils.copyProperties(this, poRybdxx);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  //人员变动信息
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
  private String hzxm;
  private String hzgmsfhm;

  //地信息
  //private Long mlpnbid;
  private Long mlpid;
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
  private String jdsj;
  private String cdsj;
  private Long cjhjywid;
  private Long cchjywid;
  private String mlpzt;
  private Long lxdbid;
  private String jlbz;
  private String qysj;
  private String jssj;
  private String pxh;

  private String bdqxz; //变动前详址
  private String bdhxz; //变动后详址

  public Long getBdbid() {
    return bdbid;
  }

  public void setBdbid(Long bdbid) {
    this.bdbid = bdbid;
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdhhh() {
    return bdhhh;
  }

  public void setBdhhh(String bdhhh) {
    this.bdhhh = bdhhh;
  }

  public Long getBdhhhnbid() {
    return bdhhhnbid;
  }

  public void setBdhhhnbid(Long bdhhhnbid) {
    this.bdhhhnbid = bdhhhnbid;
  }

  public String getBdhhlx() {
    return bdhhlx;
  }

  public void setBdhhlx(String bdhhlx) {
    this.bdhhlx = bdhhlx;
  }

  public String getBdhjcwh() {
    return bdhjcwh;
  }

  public void setBdhjcwh(String bdhjcwh) {
    this.bdhjcwh = bdhjcwh;
  }

  public String getBdhjlx() {
    return bdhjlx;
  }

  public void setBdhjlx(String bdhjlx) {
    this.bdhjlx = bdhjlx;
  }

  public String getBdhmlph() {
    return bdhmlph;
  }

  public void setBdhmlph(String bdhmlph) {
    this.bdhmlph = bdhmlph;
  }

  public Long getBdhmlpnbid() {
    return bdhmlpnbid;
  }

  public void setBdhmlpnbid(Long bdhmlpnbid) {
    this.bdhmlpnbid = bdhmlpnbid;
  }

  public String getBdhmlxz() {
    return bdhmlxz;
  }

  public void setBdhmlxz(String bdhmlxz) {
    this.bdhmlxz = bdhmlxz;
  }

  public String getBdhpcs() {
    return bdhpcs;
  }

  public void setBdhpcs(String bdhpcs) {
    this.bdhpcs = bdhpcs;
  }

  public String getBdhssxq() {
    return bdhssxq;
  }

  public void setBdhssxq(String bdhssxq) {
    this.bdhssxq = bdhssxq;
  }

  public String getBdhxzjd() {
    return bdhxzjd;
  }

  public void setBdhxzjd(String bdhxzjd) {
    this.bdhxzjd = bdhxzjd;
  }

  public String getBdhzrq() {
    return bdhzrq;
  }

  public void setBdhzrq(String bdhzrq) {
    this.bdhzrq = bdhzrq;
  }

  public String getBdqhb() {
    return bdqhb;
  }

  public void setBdqhb(String bdqhb) {
    this.bdqhb = bdqhb;
  }

  public String getBdqhh() {
    return bdqhh;
  }

  public void setBdqhh(String bdqhh) {
    this.bdqhh = bdqhh;
  }

  public Long getBdqhhnbid() {
    return bdqhhnbid;
  }

  public void setBdqhhnbid(Long bdqhhnbid) {
    this.bdqhhnbid = bdqhhnbid;
  }

  public String getBdqhlx() {
    return bdqhlx;
  }

  public void setBdqhlx(String bdqhlx) {
    this.bdqhlx = bdqhlx;
  }

  public String getBdqjcwh() {
    return bdqjcwh;
  }

  public void setBdqjcwh(String bdqjcwh) {
    this.bdqjcwh = bdqjcwh;
  }

  public String getBdqjlx() {
    return bdqjlx;
  }

  public void setBdqjlx(String bdqjlx) {
    this.bdqjlx = bdqjlx;
  }

  public String getBdqmlph() {
    return bdqmlph;
  }

  public void setBdqmlph(String bdqmlph) {
    this.bdqmlph = bdqmlph;
  }

  public Long getBdqmlpnbid() {
    return bdqmlpnbid;
  }

  public void setBdqmlpnbid(Long bdqmlpnbid) {
    this.bdqmlpnbid = bdqmlpnbid;
  }

  public String getBdqmlxz() {
    return bdqmlxz;
  }

  public void setBdqmlxz(String bdqmlxz) {
    this.bdqmlxz = bdqmlxz;
  }

  public String getBdqpcs() {
    return bdqpcs;
  }

  public void setBdqpcs(String bdqpcs) {
    this.bdqpcs = bdqpcs;
  }

  public String getBdqssxq() {
    return bdqssxq;
  }

  public void setBdqssxq(String bdqssxq) {
    this.bdqssxq = bdqssxq;
  }

  public String getBdqxzjd() {
    return bdqxzjd;
  }

  public void setBdqxzjd(String bdqxzjd) {
    this.bdqxzjd = bdqxzjd;
  }

  public String getBdqzrq() {
    return bdqzrq;
  }

  public void setBdqzrq(String bdqzrq) {
    this.bdqzrq = bdqzrq;
  }

  public String getBdrq() {
    return bdrq;
  }

  public void setBdrq(String bdrq) {
    this.bdrq = bdrq;
  }

  public String getBdyy() {
    return bdyy;
  }

  public void setBdyy(String bdyy) {
    this.bdyy = bdyy;
  }

  public Long getCchjywid() {
    return cchjywid;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public String getCdlb() {
    return cdlb;
  }

  public void setCdlb(String cdlb) {
    this.cdlb = cdlb;
  }

  public String getCdsj() {
    return cdsj;
  }

  public void setCdsj(String cdsj) {
    this.cdsj = cdsj;
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public Long getCzsm() {
    return czsm;
  }

  public void setCzsm(Long czsm) {
    this.czsm = czsm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getHb() {
    return hb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHzjs() {
    return hzjs;
  }

  public void setHzjs(Long hzjs) {
    this.hzjs = hzjs;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJdlb() {
    return jdlb;
  }

  public void setJdlb(String jdlb) {
    this.jdlb = jdlb;
  }

  public String getJdsj() {
    return jdsj;
  }

  public void setJdsj(String jdsj) {
    this.jdsj = jdsj;
  }

  public String getJlbz() {
    return jlbz;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJssj() {
    return jssj;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public Long getLxdbid() {
    return lxdbid;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public Long getMlpid() {
    return mlpid;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public String getMlpzt() {
    return mlpzt;
  }

  public void setMlpzt(String mlpzt) {
    this.mlpzt = mlpzt;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMz() {
    return mz;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getPcs() {
    return pcs;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPxh() {
    return pxh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getQysj() {
    return qysj;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public Long getRybdid() {
    return rybdid;
  }

  public void setRybdid(Long rybdid) {
    this.rybdid = rybdid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public String getRylb() {
    return rylb;
  }

  public void setRylb(String rylb) {
    this.rylb = rylb;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRzjs() {
    return rzjs;
  }

  public void setRzjs(Long rzjs) {
    this.rzjs = rzjs;
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

  public String getSbsj() {
    return sbsj;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
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

  public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getXb() {
    return xb;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwnr() {
    return ywnr;
  }

  public void setYwnr(String ywnr) {
    this.ywnr = ywnr;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getSlsj() {
    return slsj;
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

  public String getBdhxz() {
    return bdhxz;
  }

  public void setBdhxz(String bdhxz) {
    this.bdhxz = bdhxz;
  }

  public String getBdqxz() {
    return bdqxz;
  }

  public void setBdqxz(String bdqxz) {
    this.bdqxz = bdqxz;
  }

}