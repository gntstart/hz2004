package com.hzjc.hz2004.vo;

import org.apache.commons.beanutils.BeanUtils;

import com.hzjc.hz2004.base.login.AuthToken;

import java.lang.reflect.*;

/**
 * 用户信息VO
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 * @History 2004-06-16 添加用户IP和最后访问时间
 */

public class VoUserInfo {
  public VoUserInfo() {
  }

  /**
   *
   * @param strAuthInfo
   * @param auth
   */
  public VoUserInfo(String strAuthInfo, AuthToken auth) {
    this.setAuthinfo(strAuthInfo);
    try {
      if (auth != null) {
        BeanUtils.copyProperties(this, auth);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

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
  //add kgb 2004-06-16
  private String ip;
  private String lastaccesstime;
  //
  private String authinfo;
  private String clientversion;

  public String getAuthinfo() {
    return authinfo;
  }

  public String getCzmj() {
    return czmj;
  }

  public String getDlkl() {
    return dlkl;
  }

  public String getDwdm() {
    return dwdm;
  }

  public String getJyh() {
    return jyh;
  }

  public String getYhdlm() {
    return yhdlm;
  }

  public Long getYhid() {
    return yhid;
  }

  public String getYhmj() {
    return yhmj;
  }

  public String getYhxb() {
    return yhxb;
  }

  public String getYhxm() {
    return yhxm;
  }

  public String getYhzt() {
    return yhzt;
  }

  public String getYhzw() {
    return yhzw;
  }

  public void setYhzw(String yhzw) {
    this.yhzw = yhzw;
  }

  public void setYhzt(String yhzt) {
    this.yhzt = yhzt;
  }

  public void setYhxm(String yhxm) {
    this.yhxm = yhxm;
  }

  public void setYhxb(String yhxb) {
    this.yhxb = yhxb;
  }

  public void setYhmj(String yhmj) {
    this.yhmj = yhmj;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public void setYhdlm(String yhdlm) {
    this.yhdlm = yhdlm;
  }

  public void setJyh(String jyh) {
    this.jyh = jyh;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public void setDlkl(String dlkl) {
    this.dlkl = dlkl;
  }

  public void setCzmj(String czmj) {
    this.czmj = czmj;
  }

  public void setAuthinfo(String authinfo) {
    this.authinfo = authinfo;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getLastaccesstime() {
    return lastaccesstime;
  }

  public void setLastaccesstime(String lastaccesstime) {
    this.lastaccesstime = lastaccesstime;
  }

  public String getClientversion() {
    return clientversion;
  }

  public void setClientversion(String clientversion) {
    this.clientversion = clientversion;
  }

}