package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 户地信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHdxxHqFhxx
    extends DefaultVO {

  //户信息
  private Long hhnbid; //户号内部ID
  private Long hhid; //户号ID
  private Long mlpnbid; //门（楼）牌内部ID
  private String hh; //户号
  private String hlx; //户类型
  private Long cjhjywid; //创建户籍业务ID
  private Long cchjywid; //撤除户籍业务ID
  private String hhzt; //户号状态
  private Long lxdbid; //离线DBID
  private String jlbz; //记录标志
  private String qysj; //起用时间
  private String jssj; //结束时间
  private String bdfw; //变动范围
  private String bdyy; //变动原因
  private String chlb; //撤户类别
  private String jhlb; //建户类别
  private String jhsj; //建户时间
  private String chsj; //撤户时间
  private String cxbz; //冲销标志

  //地信息
  private Long mlpid; //门（楼）牌ID
  private String ssxq; //省市县（区）
  private String jlx; //街路巷
  private String mlph; //门（楼）牌号
  private String mlxz; //门（楼）详址
  private String pcs; //派出所
  private String zrq; //责任区
  private String xzjd; //乡镇（街道）
  private String jcwh; //居（村）委会
  private String mlpzt; //门（楼）牌状态
  private String pxh; //排序号
  private String cdlb; //撤地类别
  private String jdlb; //建地类别
  private String jdsj; //建地时间
  private String cdsj; //撤地时间

  public VoHdxxHqFhxx(PoHJXX_HXXB poHJXX_HXXB, PoHJXX_MLPXXXXB poHJXX_MLPXXXXB) {

    try {
      BeanUtils.copyProperties(this, poHJXX_MLPXXXXB);
      BeanUtils.copyProperties(this, poHJXX_HXXB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public Long getCchjywid() {
    return cchjywid;
  }

  public String getHh() {
    return hh;
  }

  public Long getHhid() {
    return hhid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }

  public void setHhid(Long hhid) {
    this.hhid = hhid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public void setHhzt(String hhzt) {
    this.hhzt = hhzt;
  }

  public void setHlx(String hlx) {
    this.hlx = hlx;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJcwh() {
    return jcwh;
  }

  public String getHlx() {
    return hlx;
  }

  public String getHhzt() {
    return hhzt;
  }

  public Long getHhnbid() {
    return hhnbid;
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

  public Long getLxdbid() {
    return lxdbid;
  }

  public String getMlph() {
    return mlph;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
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

  public String getMlpzt() {
    return mlpzt;
  }

  public void setMlpid(Long mlpid) {
    this.mlpid = mlpid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public void setMlpzt(String mlpzt) {
    this.mlpzt = mlpzt;
  }

  public String getMlxz() {
    return mlxz;
  }

  public String getPcs() {
    return pcs;
  }

  public String getPxh() {
    return pxh;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getQysj() {
    return qysj;
  }

  public String getSsxq() {
    return ssxq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public String getCdlb() {
    return cdlb;
  }

  public void setCdlb(String cdlb) {
    this.cdlb = cdlb;
  }

  public String getChlb() {
    return chlb;
  }

  public void setChlb(String chlb) {
    this.chlb = chlb;
  }

  public String getJdlb() {
    return jdlb;
  }

  public void setJdlb(String jdlb) {
    this.jdlb = jdlb;
  }

  public String getJhlb() {
    return jhlb;
  }

  public void setJhlb(String jhlb) {
    this.jhlb = jhlb;
  }

  public String getCdsj() {
    return cdsj;
  }

  public void setCdsj(String cdsj) {
    this.cdsj = cdsj;
  }

  public String getChsj() {
    return chsj;
  }

  public void setChsj(String chsj) {
    this.chsj = chsj;
  }

  public String getJdsj() {
    return jdsj;
  }

  public void setJdsj(String jdsj) {
    this.jdsj = jdsj;
  }

  public String getJhsj() {
    return jhsj;
  }

  public void setJhsj(String jhsj) {
    this.jhsj = jhsj;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdyy() {
    return bdyy;
  }

  public void setBdyy(String bdyy) {
    this.bdyy = bdyy;
  }

}