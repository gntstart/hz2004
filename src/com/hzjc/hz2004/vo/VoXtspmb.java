package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_SPMBXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 审批流操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtspmb
    extends DefaultVO {

  //审批模板信息
  private Long spmbid;
  private String mbmc;
  private String mbdj;
  private Long cjrid;
  private String cjsj;
  private Long xgrid;
  private String xgsj;
  private String qybz;
  private String dqsys;

  //用户修改人信息
  //private Long yhid;
  private String xgryhdlm;
  private String xgrjyh;
  private String xgrdwdm;
  private String xgryhxm;
  private String xgryhxb;
  private String xgryhzw;
  private String xgrdlkl;
  private String xgryhmj;
  private String xgrczmj;
  private String xgryhzt;

  //用户创建人信息
  //private Long yhid;
  private String cjryhdlm;
  private String cjrjyh;
  private String cjrdwdm;
  private String cjryhxm;
  private String cjryhxb;
  private String cjryhzw;
  private String cjrdlkl;
  private String cjryhmj;
  private String cjrczmj;
  private String cjryhzt;
  private String mbdjmc;
  private String qybzmc;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtspmb() {
  }

  public VoXtspmb(PoXT_SPMBXXB poXT_SPMBXXB, PoXT_YHXXB poXT_CJRYHXXB,
                  PoXT_YHXXB poXT_XGRYHXXB,
                  PoXT_XTCSB poXtcsba, PoXT_XTCSB poXtcsbb) throws
      InvocationTargetException, IllegalAccessException {

    try {
      BeanUtils.copyProperties(this, poXT_SPMBXXB);
      this.setMbdjmc(poXtcsba != null ? poXtcsba.getMc() : null);
      this.setQybzmc(poXtcsbb != null ? poXtcsbb.getMc() : null);
      //创建人信息
      if (poXT_CJRYHXXB != null) {
        cjrjyh = poXT_CJRYHXXB.getJyh();
        cjrdwdm = poXT_CJRYHXXB.getDwdm();
        cjryhxm = poXT_CJRYHXXB.getYhxm();
        cjryhxb = poXT_CJRYHXXB.getYhxb();
        cjryhzw = poXT_CJRYHXXB.getYhzw();
        cjrdlkl = poXT_CJRYHXXB.getDlkl();
        cjryhmj = poXT_CJRYHXXB.getYhmj();
        cjrczmj = poXT_CJRYHXXB.getCzmj();
        cjryhzt = poXT_CJRYHXXB.getYhzw();
      }
      //修改人信息
      if (poXT_XGRYHXXB != null) {
        xgrjyh = poXT_XGRYHXXB.getJyh();
        xgrdwdm = poXT_XGRYHXXB.getDwdm();
        xgryhxm = poXT_XGRYHXXB.getYhxm();
        xgryhxb = poXT_XGRYHXXB.getYhxb();
        xgryhzw = poXT_XGRYHXXB.getYhzw();
        xgrdlkl = poXT_XGRYHXXB.getDlkl();
        xgryhmj = poXT_XGRYHXXB.getYhmj();
        xgrczmj = poXT_XGRYHXXB.getCzmj();
        xgryhzt = poXT_XGRYHXXB.getYhzw();
      }

      /**
       * 将创建时间格式转换
       * YYYYMMDDHHMMSS转换为YYYY-MM-DD HH:MM:SS
       */
      this.cjsj = StringUtils.formatDateBy(StringUtils.strToDate(poXT_SPMBXXB.
          getCjsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
      this.xgsj = StringUtils.formatDateBy(StringUtils.strToDate(poXT_SPMBXXB.
          getXgsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
    }
    catch (InvocationTargetException ex) {
      throw ex;
    }
    catch (IllegalAccessException ex) {
      throw ex;
    }

  }

  //审批模板信息
  //private Long spmbid;
  public Long getSpmbid() {
    return this.spmbid;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

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

  //用户修改信息
  public void setXgryhdlm(String yhdlm) {
    this.xgryhdlm = yhdlm;
  }

  public String getXgryhdlm() {
    return xgryhdlm;
  }

  public void setXgrjyh(String jyh) {
    this.xgrjyh = jyh;
  }

  public String getXgrjyh() {
    return xgrjyh;
  }

  public void setXgrdwdm(String dwdm) {
    this.xgrdwdm = dwdm;
  }

  public String getXgrdwdm() {
    return xgrdwdm;
  }

  public void setXgryhxm(String yhxm) {
    this.xgryhxm = yhxm;
  }

  public String getXgryhxm() {
    return xgryhxm;
  }

  public void setXgryhxb(String yhxb) {
    this.xgryhxb = yhxb;
  }

  public String getXgryhxb() {
    return xgryhxb;
  }

  public void setXgryhzw(String yhzw) {
    this.xgryhzw = yhzw;
  }

  public String getXgryhzw() {
    return xgryhzw;
  }

  public void setXgrdlkl(String dlkl) {
    this.xgrdlkl = dlkl;
  }

  public String getXgrdlkl() {
    return xgrdlkl;
  }

  public void setXgryhmj(String yhmj) {
    this.xgryhmj = yhmj;
  }

  public String getXgryhmj() {
    return xgryhmj;
  }

  public void setXgrzmj(String czmj) {
    this.xgrczmj = czmj;
  }

  public String getXgrczmj() {
    return xgrczmj;
  }

  public void setXgryhzt(String yhzt) {
    this.xgryhzt = yhzt;
  }

  public String getXgryhzt() {
    return xgryhzt;
  }

  //用户创建人信息
  public void setCjryhdlm(String yhdlm) {
    this.cjryhdlm = yhdlm;
  }

  public String getCjryhdlm() {
    return cjryhdlm;
  }

  public void setCjrjyh(String jyh) {
    this.cjrjyh = jyh;
  }

  public String getCjrjyh() {
    return cjrjyh;
  }

  public void setCjrdwdm(String dwdm) {
    this.cjrdwdm = dwdm;
  }

  public String getCjrdwdm() {
    return cjrdwdm;
  }

  public void setCjryhxm(String yhxm) {
    this.cjryhxm = yhxm;
  }

  public String getCjryhxm() {
    return cjryhxm;
  }

  public void setCjryhxb(String yhxb) {
    this.cjryhxb = yhxb;
  }

  public String getCjryhxb() {
    return cjryhxb;
  }

  public void setCjryhzw(String yhzw) {
    this.cjryhzw = yhzw;
  }

  public String getCjryhzw() {
    return cjryhzw;
  }

  public void setCjrdlkl(String dlkl) {
    this.cjrdlkl = dlkl;
  }

  public String getCjrdlkl() {
    return cjrdlkl;
  }

  public void setCjryhmj(String yhmj) {
    this.cjryhmj = yhmj;
  }

  public String getCjryhmj() {
    return cjryhmj;
  }

  public void setCjrzmj(String czmj) {
    this.cjrczmj = czmj;
  }

  public String getCjrczmj() {
    return cjrczmj;
  }

  public void setCjryhzt(String yhzt) {
    this.cjryhzt = yhzt;
  }

  public String getCjryhzt() {
    return cjryhzt;
  }

  public String getMbdjmc() {
    return mbdjmc;
  }

  public void setMbdjmc(String mbdjmc) {
    this.mbdjmc = mbdjmc;
  }

  public String getQybzmc() {
    return qybzmc;
  }

  public void setQybzmc(String qybzmc) {
    this.qybzmc = qybzmc;
  }

}
