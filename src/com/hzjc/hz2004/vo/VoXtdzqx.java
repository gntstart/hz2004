package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YHDZQXB;
import com.hzjc.hz2004.po.PoXT_SPDZB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 动作权限操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtdzqx
    extends DefaultVO {

  //审批动作信息
  private Long dzid;
  private String dzmc;
  private String dzsx;
  private String ms;

  //用户信息
  private Long yhid;
  private String yhdlm;
  private String jyh;
  private String dwdm;
  private String yhxm;
  private String yhxb;
  private String yhzw;
  private String dlkl;
  private String yhmj;
  private String czmj;
  private String yhzt;
  //动作权限
  private Long dzqxid;
  //private Long yhid;
  //private Long dzid;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtdzqx() {
  }

  public VoXtdzqx(PoXT_YHDZQXB PoXT_YHDZQXB, PoXT_YHXXB poXT_YHXXB,
                  PoXT_SPDZB poXT_SPDZB) {

    try {
      BeanUtils.copyProperties(this, poXT_YHXXB);
      BeanUtils.copyProperties(this, PoXT_YHDZQXB);
      BeanUtils.copyProperties(this, poXT_SPDZB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //用户信息
  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setYhdlm(String yhdlm) {
    this.yhdlm = yhdlm;
  }

  public String getYhdlm() {
    return yhdlm;
  }

  public void setJyh(String jyh) {
    this.jyh = jyh;
  }

  public String getJyh() {
    return jyh;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setYhxm(String yhxm) {
    this.yhxm = yhxm;
  }

  public String getYhxm() {
    return yhxm;
  }

  public void setYhxb(String yhxb) {
    this.yhxb = yhxb;
  }

  public String getYhxb() {
    return yhxb;
  }

  public void setYhzw(String yhzw) {
    this.yhzw = yhzw;
  }

  public String getYhzw() {
    return yhzw;
  }

  public void setDlkl(String dlkl) {
    this.dlkl = dlkl;
  }

  public String getDlkl() {
    return dlkl;
  }

  public void setYhmj(String yhmj) {
    this.yhmj = yhmj;
  }

  public String getYhmj() {
    return yhmj;
  }

  public void setCzmj(String czmj) {
    this.czmj = czmj;
  }

  public String getCzmj() {
    return czmj;
  }

  public void setYhzt(String yhzt) {
    this.yhzt = yhzt;
  }

  public String getYhzt() {
    return yhzt;
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

//动作权限
  public void setDzqxid(Long dzqxid) {
    this.dzqxid = dzqxid;
  }

  public Long getDzqxid() {
    return dzqxid;
  }

}
