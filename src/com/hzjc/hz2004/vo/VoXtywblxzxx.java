package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXZ_YWBLXZXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_SPMBXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 业务办理限制操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtywblxzxx
    extends DefaultVO {

  //业务办理限制信息
  private Long xzxxid;
  private String xzmc;
  private String xzywlx;
  private Long cjrid;
  private String cjsj;
  private Long xgrid;
  private String xgsj;
  private String xzzt;
  private String qybz;
  private Long spmbid;
  private String xzbds;

  //增加人用户信息
  private String cjryhdlm;
  private String cjryhxm;
  //修改人信息
  private String xgryhdlm;
  private String xgryhxm;
  //审批模板信息
  //private Long spmbid;
  private String mbmc;
  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtywblxzxx() {
  }

  public VoXtywblxzxx(PoHJXZ_YWBLXZXXB poHJXZ_YWBLXZXXB, PoXT_YHXXB poXT_YHXXBA,
                      PoXT_YHXXB poXT_YHXXBB, PoXT_SPMBXXB poXT_SPMBXXB) {

    try {
      BeanUtils.copyProperties(this, poHJXZ_YWBLXZXXB);
      this.cjsj = StringUtils.formatDateBy(StringUtils.strToDate(
          poHJXZ_YWBLXZXXB.getCjsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.xgsj = StringUtils.formatDateBy(StringUtils.strToDate(
          poHJXZ_YWBLXZXXB.getXgsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      if (poXT_SPMBXXB != null) {
        this.mbmc = poXT_SPMBXXB.getMbmc();
      }
      this.setCjryhdlm(poXT_YHXXBA.getYhdlm());
      this.setCjryhxm(poXT_YHXXBA.getYhxm());
      if (poXT_YHXXBB != null) {
        this.setXgryhdlm(poXT_YHXXBB.getYhdlm());
        this.setXgryhxm(poXT_YHXXBB.getYhxm());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //业务办理限制信息
  public void setXzxxid(Long xzxxid) {
    this.xzxxid = xzxxid;
  }

  public Long getXzxxid() {
    return xzxxid;
  }

  public void setXzmc(String xzmc) {
    this.xzmc = xzmc;
  }

  public String getXzmc() {
    return xzmc;
  }

  public void setXzywlx(String xzywlx) {
    this.xzywlx = xzywlx;
  }

  public String getXzywlx() {
    return xzywlx;
  }

  public void setCjrid(Long cjrid) {
    this.cjrid = cjrid;
  }

  public Long getCjrid() {
    return cjrid;
  }

  public void setCjsj(String cjsj) {
    this.cjsj = cjsj;
  }

  public String getCjsj() {
    return cjsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public Long getXgrid() {
    return xgrid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public String getXgsj() {
    return xgsj;
  }

  public void setXzzt(String xzzt) {
    this.xzzt = xzzt;
  }

  public String getXzzt() {
    return xzzt;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public String getQybz() {
    return qybz;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

  public Long getSpmbid() {
    return spmbid;
  }

  public String getXzbds() {
    return xzbds;
  }

  public void setXzbds(String xzbds) {
    this.xzbds = xzbds;
  }

  //增加人信息
  public void setCjryhdlm(String yhdlm) {
    this.cjryhdlm = yhdlm;
  }

  public String getCjryhdlm() {
    return cjryhdlm;
  }

  public void setCjryhxm(String yhxm) {
    this.cjryhxm = yhxm;
  }

  public String getCjryhxm() {
    return cjryhxm;
  }

  //修改人信息
  public void setXgryhdlm(String yhdlm) {
    this.xgryhdlm = yhdlm;
  }

  public String getXgryhdlm() {
    return xgryhdlm;
  }

  public void setXgryhxm(String yhxm) {
    this.xgryhxm = yhxm;
  }

  public String getXgryhxm() {
    return xgryhxm;
  }

  public void setMbmc(String mbmc) {
    this.mbmc = mbmc;
  }

  public String getMbmc() {
    return mbmc;
  }

}
