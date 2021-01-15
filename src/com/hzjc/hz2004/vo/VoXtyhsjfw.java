package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 用户角色操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtyhsjfw
    extends DefaultVO {
  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtyhsjfw() {
  }

  public VoXtyhsjfw(PoXT_YHSJFWB poXT_YHSJFWB, PoXT_YHXXB poXT_YHXXB,
                    PoXT_XTCSB poXT_XTCSB) {

    try {
      BeanUtils.copyProperties(this, poXT_YHSJFWB);
      BeanUtils.copyProperties(this, poXT_YHXXB);
      this.setXqlxmc(poXT_XTCSB.getMc());
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

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

  //数据范围
  private Long sjfwid;
  private Long yhid;
  private String xqlx;
  private String sjfw;

  private String xqlxmc;
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

  public void setSjfwid(Long sjfwid) {
    this.sjfwid = sjfwid;
  }

  public Long getSjfwid() {
    return sjfwid;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setXqlx(String xqlx) {
    this.xqlx = xqlx;
  }

  public String getXqlx() {
    return xqlx;
  }

  public void setSjfw(String sjfw) {
    this.sjfw = sjfw;
  }

  public String getSjfw() {
    return sjfw;
  }

  public void setXqlxmc(String mc) {
    this.xqlxmc = mc;
  }

  public String getXqlxmc() {
    return xqlxmc;
  }

}
