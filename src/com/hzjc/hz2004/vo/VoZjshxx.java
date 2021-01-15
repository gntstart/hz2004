package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoZjshxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID
  private String shqk; //审核情况
  private String zzxxcwlb; //制证信息错误类别
  private String cwms; //错误描述

  public Long getNbslid() {
    return nbslid;
  }

  public String getShqk() {
    return shqk;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setShqk(String shqk) {
    this.shqk = shqk;
  }

  public String getCwms() {
    return cwms;
  }

  public void setCwms(String cwms) {
    this.cwms = cwms;
  }

  public String getZzxxcwlb() {
    return zzxxcwlb;
  }

  public void setZzxxcwlb(String zzxxcwlb) {
    this.zzxxcwlb = zzxxcwlb;
  }

}