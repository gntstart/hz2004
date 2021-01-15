package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZjdbzfxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID
  private String zzxxcwlb; //制证信息错误类别
  private String cwms; //错误描述

  public VoZjdbzfxx() {
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
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