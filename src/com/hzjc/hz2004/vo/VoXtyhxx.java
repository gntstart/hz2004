package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_DWXXB;
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

public class VoXtyhxx
    extends DefaultVO {
  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtyhxx() {
  }

  public VoXtyhxx(PoXT_YHXXB poXT_YHXXB, PoXT_DWXXB poXT_DWXXB,
                  PoXT_XTCSB poXT_XTCSBA,
                  PoXT_XTCSB poXT_XTCSBB, PoXT_XTCSB poXT_XTCSBC,
                  PoXT_XTCSB poXT_XTCSBD) {

    try {
      BeanUtils.copyProperties(this, poXT_YHXXB);
      this.setDwmc(poXT_DWXXB.getMc());
      if (poXT_XTCSBA != null) {
        this.setYhxbmc(poXT_XTCSBA.getMc());
      }
      if (poXT_XTCSBB != null) {
        this.setYhzwmc(poXT_XTCSBB.getMc());
      }
      if (poXT_XTCSBC != null) {
        this.setYhmjmc(poXT_XTCSBC.getMc());
      }
      if (poXT_XTCSBD != null) {
        this.setYhztmc(poXT_XTCSBD.getMc());
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

  //单位信息
  // private Long jsid;
  private String dwmc;
  //性别名称，用户密级，状态
  private String yhxbmc;
  private String yhmjmc;
  private String yhztmc;
  private String yhzwmc;
  private String gmsfhm;
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

  public void setDlkl(String dlkl) {
    this.dlkl = dlkl;
  }

  public String getDlkl() {
    return dlkl;
  }

  //单位信息
  public void setDwmc(String dwmc) {
    this.dwmc = dwmc;
  }

  public String getDwmc() {
    return dwmc;
  }

  public void setYhxbmc(String mc) {
    this.yhxbmc = mc;
  }

  public String getYhxbmc() {
    return yhxbmc;
  }

  public void setYhmjmc(String mc) {
    this.yhmjmc = mc;
  }

  public String getYhmjmc() {
    return yhmjmc;
  }

  public void setYhztmc(String mc) {
    this.yhztmc = mc;
  }

  public String getYhztmc() {
    return yhztmc;
  }

  public void setYhzwmc(String mc) {
    this.yhzwmc = mc;
  }

  public String getYhzwmc() {
    return yhzwmc;
  }
  public String getGmsfhm() {
    return gmsfhm;
  }
  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

}
