package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YHDTQXB;
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

public class VoXtyhdtqx
    extends DefaultVO {

  //等同权限
  private Long dtqxid;
  private Long yhid;
  private Long dtyhid;

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
  //等同用户信息
  private String dtyhdlm;
  private String dtjyh;
  private String dtdwdm;
  private String dtyhxm;
  private String dtyhxb;
  private String dtyhzw;
  private String dtdlkl;
  private String dtyhmj;
  private String dtczmj;
  private String dtyhzt;
  private String yhzt;

  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtyhdtqx() {
  }

  public VoXtyhdtqx(PoXT_YHDTQXB poXT_YHDTQXB, PoXT_YHXXB poXT_YHXXB,
                    PoXT_YHXXB poXT_DTYHXXB) {

    try {
      BeanUtils.copyProperties(this, poXT_YHXXB);
      BeanUtils.copyProperties(this, poXT_YHDTQXB);
      this.dtczmj = poXT_DTYHXXB.getCzmj();
      this.dtdlkl = poXT_DTYHXXB.getDlkl();
      this.dtdwdm = poXT_DTYHXXB.getDwdm();
      this.dtjyh = poXT_DTYHXXB.getJyh();
      this.dtyhdlm = poXT_DTYHXXB.getYhdlm();
      this.dtyhmj = poXT_DTYHXXB.getYhmj();
      this.dtyhxb = poXT_DTYHXXB.getYhxb();
      this.dtyhxm = poXT_DTYHXXB.getYhxm();
      this.dtyhzt = poXT_DTYHXXB.getYhzt();
      this.dtyhzw = poXT_DTYHXXB.getYhzw();
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

  //等同权限
  public void setDtqxid(Long dtqxid) {
    this.dtqxid = dtqxid;
  }

  public Long getDtqxid() {
    return dtqxid;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setDtyhid(Long dtyhid) {
    this.dtyhid = dtyhid;
  }

  public Long getDtyhid() {
    return dtyhid;
  }

  //等同用户信息
  public void setDtyhdlm(String yhdlm) {
    this.dtyhdlm = yhdlm;
  }

  public String getDtyhdlm() {
    return dtyhdlm;
  }

  public void setDtjyh(String jyh) {
    this.dtjyh = jyh;
  }

  public String getDtjyh() {
    return dtjyh;
  }

  public void setDtdwdm(String dwdm) {
    this.dtdwdm = dwdm;
  }

  public String getDtdwdm() {
    return dtdwdm;
  }

  public void setDtyhxm(String yhxm) {
    this.dtyhxm = yhxm;
  }

  public String getDtyhxm() {
    return dtyhxm;
  }

  public void setDtyhxb(String yhxb) {
    this.dtyhxb = yhxb;
  }

  public String getDtyhxb() {
    return dtyhxb;
  }

  public void setDtyhzw(String yhzw) {
    this.dtyhzw = yhzw;
  }

  public String getDtyhzw() {
    return dtyhzw;
  }

  public void setDtdlkl(String dlkl) {
    this.dtdlkl = dlkl;
  }

  public String getDtdlkl() {
    return dtdlkl;
  }

  public void setDtyhmj(String yhmj) {
    this.dtyhmj = yhmj;
  }

  public String getDtyhmj() {
    return dtyhmj;
  }

  public void setDtczmj(String czmj) {
    this.dtczmj = czmj;
  }

  public String getDtczmj() {
    return dtczmj;
  }

  public void setDtyhzt(String dtyhzt) {
    this.dtyhzt = yhzt;
  }

  public String getDtyhzt() {
    return dtyhzt;
  }

}
