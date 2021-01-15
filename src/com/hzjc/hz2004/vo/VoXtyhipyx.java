package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YHIPYXXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.util.StringUtils;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 动作权限操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtyhipyx
    extends DefaultVO {

  //用户IP允许
  private Long ipyxid;
  private Long yhid;
  private String ipdz;
  private Long cjrid;
  private String cjsj;
  //用户信息
  //private Long yhid;
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

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtyhipyx() {
  }

  public VoXtyhipyx(PoXT_YHIPYXXXB poXT_YHIPYXXXB, PoXT_YHXXB poXT_YHXXB,
                    PoXT_YHXXB poXT_CJRYHXXB) {
    String sDate = "";
    try {
      if (poXT_YHXXB != null) {
        BeanUtils.copyProperties(this, poXT_YHXXB);
      }
      BeanUtils.copyProperties(this, poXT_YHIPYXXXB);
      cjryhdlm = poXT_CJRYHXXB.getYhdlm();
      cjrjyh = poXT_CJRYHXXB.getJyh();
      cjrdwdm = poXT_CJRYHXXB.getDwdm();
      cjryhxm = poXT_CJRYHXXB.getYhxm();
      cjryhxb = poXT_CJRYHXXB.getYhxb();
      cjryhzw = poXT_CJRYHXXB.getYhzw();
      cjrdlkl = poXT_CJRYHXXB.getDlkl();
      cjryhmj = poXT_CJRYHXXB.getYhmj();
      cjrczmj = poXT_CJRYHXXB.getCzmj();
      cjryhzt = poXT_CJRYHXXB.getYhzw();
      /**
       * 将创建时间格式转换
       * YYYYMMDDHHMMSS转换为YYYY-MM-DD HH:MM:SS
       */
      this.cjsj = StringUtils.formatDateBy(StringUtils.strToDate(poXT_YHIPYXXXB.
          getCjsj(), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");

    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //用户信息
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

  //用户IP允许
  public void setIpyxid(Long ipyxid) {
    this.ipyxid = ipyxid;
  }

  public Long getIpyxid() {
    return ipyxid;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setIpdz(String ipdz) {
    this.ipdz = ipdz;
  }

  public String getIpdz() {
    return ipdz;
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

}
