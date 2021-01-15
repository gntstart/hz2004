package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_MBLCXXB;
import com.hzjc.hz2004.po.PoXT_SPDZB;
import com.hzjc.hz2004.po.PoXT_SPMBXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 审批流操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtsplxx
    extends DefaultVO {

  //审批模板流程信息
  private Long mblcid;
  private Long spmbid;
  private Long dzid;
  private String dzz;
  private Long xgdzid;
  private String dzbz;

  //审批动作
//  private Long dzid;
  private String dzmc;
  private String dzsx;
  private String ms;

  //审批模板信息
  //private Long spmbid;
  private String mbmc;
  private String mbdj;
  private Long cjrid;
  private String cjsj;
  private Long xgrid;
  private String xgsj;
  private String qybz;
  private String dqsys;
  //审批动作
//  private Long dzid;
  private String xgdzmc;
  private String xgms;
  private String xgqybz;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtsplxx() {
  }

  public VoXtsplxx(PoXT_MBLCXXB poXT_MBLCXXB, PoXT_SPMBXXB poXT_SPMBXXB,
                   PoXT_SPDZB poXT_SPDZBA, PoXT_SPDZB poXT_SPDZBB) {

    try {
      BeanUtils.copyProperties(this, poXT_MBLCXXB);
      BeanUtils.copyProperties(this, poXT_SPDZBA);
      if (poXT_SPDZBB != null) {
        this.setXgdzmc(poXT_SPDZBB.getDzmc());
        this.setXgqybz(poXT_SPDZBB.getQybz());
        this.setXgms(poXT_SPDZBB.getMs());
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //审批模板流程信息
  public Long getMblcid() {
    return this.mblcid;
  }

  public Long getSpmbid() {
    return this.spmbid;
  }

  public Long getDzid() {
    return this.dzid;
  }

  public String getDzz() {
    return this.dzz;
  }

  public Long getXgdzid() {
    return this.xgdzid;
  }

  public String getDzbz() {
    return this.dzbz;
  }

  public void setMblcid(Long mblcid) {
    this.mblcid = mblcid;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

  public void setDzid(Long dzid) {
    this.dzid = dzid;
  }

  public void setDzz(String dzz) {
    this.dzz = dzz;
  }

  public void setXgdzid(Long xgdzid) {
    this.xgdzid = xgdzid;
  }

  public void setDzbz(String dzbz) {
    this.dzbz = dzbz;
  }

  //审批动作
  public String getDzmc() {
    return this.dzmc;
  }

  public String getDzsx() {
    return this.dzsx;
  }

  public String getMs() {
    return this.ms;
  }

  public void setDzmc(String dzmc) {
    this.dzmc = dzmc;
  }

  public void setDzsx(String dzsx) {
    this.dzsx = dzsx;
  }

  public void setMs(String ms) {
    this.ms = ms;
  }

  //xg审批动作
  public String getXgdzmc() {
    return this.xgdzmc;
  }

  public String getXgqybz() {
    return this.xgqybz;
  }

  public String getXgms() {
    return this.xgms;
  }

  public void setXgdzmc(String dzmc) {
    this.xgdzmc = dzmc;
  }

  public void setXgqybz(String qybz) {
    this.xgqybz = qybz;
  }

  public void setXgms(String ms) {
    this.xgms = ms;
  }

  //审批模板信息
  //private Long spmbid;
  public String getMbmc() {
    return this.mbmc;
  }

  public String getMbdj() {
    return this.mbdj;
  }

  public Long getCjrid() {
    return this.cjrid;
  }

  public String getCjsj() {
    return this.cjsj;
  }

  public Long getXgrid() {
    return this.xgrid;
  }

  public String getXgsj() {
    return this.xgsj;
  }

  public String getQybz() {
    return this.qybz;
  }

  public String getDqsys() {
    return this.dqsys;
  }

  public void setMbmc(String mbmc) {
    this.mbmc = mbmc;
  }

  public void setMbdj(String mbdj) {
    this.mbdj = mbdj;
  }

  public void setCjrid(Long cjrid) {
    this.cjrid = cjrid;
  }

  public void setCjsj(String cjsj) {
    this.cjsj = cjsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public void setDqsys(String dqsys) {
    this.dqsys = dqsys;
  }

}
