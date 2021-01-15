package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJYW_GMSFHMSXMFPXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 身份号码分配信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSfhmfpxxHqFhxx
    extends DefaultVO {

  private Long fpid;
  private Long ryid;
  private String xm;
  private String xb;
  private String csrq;
  private String gmsfhm;
  private String xzqhdm;
  private String dwdm;
  private String sxh;
  private Long hjywid;

  public VoSfhmfpxxHqFhxx() {

  }

  public VoSfhmfpxxHqFhxx(PoHJYW_GMSFHMSXMFPXXB poHJYW_GMSFHMSXMFPXXB) {
    try {
      BeanUtils.copyProperties(this, poHJYW_GMSFHMSXMFPXXB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public void setFpid(Long fpid) {
    this.fpid = fpid;
  }

  public Long getFpid() {
    return fpid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXzqhdm(String xzqhdm) {
    this.xzqhdm = xzqhdm;
  }

  public String getXzqhdm() {
    return xzqhdm;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setSxh(String sxh) {
    this.sxh = sxh;
  }

  public String getSxh() {
    return sxh;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

}