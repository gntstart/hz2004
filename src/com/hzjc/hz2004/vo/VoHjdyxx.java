package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;


/**
 * 户籍打印信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT Corp. 2004</p>
 * <p>Company: </p>
 * @author hb
 * @version 1.0
 */

public class VoHjdyxx extends DefaultVO{

//  public VoHjdyxx(Object poView) {
//    try {
//      BeanUtils.copyProperties(this, poView);
//    }
//    catch (InvocationTargetException ex) {
//    }
//    catch (IllegalAccessException ex) {
//    }
//  }

  private Long dyid;//打印ID
  private Long ryid;//人员ID
  private Long rynbid;//人员内部ID
  private String gmsfhm;//公民身份号码
  private String xm;//姓名
  private String pcs;//派出所
  private String ssxq;//省市县区
  private String dylb;//打印类别
  private String zjbh;//证件编号
  private String yznf;//印制年份
  private String slsj;//受理时间
  private String sldw;//受理单位
  private Long slrid;//受理人ID
  private String czip;//操作IP
  private Long mlpnbid;//门楼牌内部ID
  private String jlx;//街路巷
  private String mlph;//门楼牌号
  private String mlxz;//门楼详址
  private String zrq;//责任区
  private String xzjd;//乡镇街道
  private String jcwh;//居村委会

  public void setDyid(Long dyid) {
    this.dyid = dyid;
  }

  public Long getDyid() {
    return dyid;
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

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPcs() {
    return pcs;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setDylb(String dylb) {
    this.dylb = dylb;
  }

  public String getDylb() {
    return dylb;
  }

  public void setZjbh(String zjbh) {
    this.zjbh = zjbh;
  }

  public String getZjbh() {
    return zjbh;
  }

  public void setYznf(String yznf) {
    this.yznf = yznf;
  }

  public String getYznf() {
    return yznf;
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

  public void setCzip(String czip) {
    this.czip = czip;
  }

  public String getCzip() {
    return czip;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
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


}
