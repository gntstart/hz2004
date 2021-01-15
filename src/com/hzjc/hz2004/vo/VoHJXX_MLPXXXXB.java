package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.wsstruts.vo.DefaultVO;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 门楼牌详细信息表VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHJXX_MLPXXXXB
    extends DefaultVO {

  public VoHJXX_MLPXXXXB() {
  }

  public VoHJXX_MLPXXXXB(PoHJXX_MLPXXXXB po) {
    try {
      if (po != null) {
        BeanUtils.copyProperties(this, po);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  private String ywlx;
  private String cdlb;
  private String jdlb;
  private Long mlpnbid;
  private Long mlpid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private Long cjhjywid;
  private Long cchjywid;
  private String mlpzt;
  private Long lxdbid;
  private String jlbz;
  private String qysj;
  private String jssj;
  private String pxh;

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

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
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

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

}