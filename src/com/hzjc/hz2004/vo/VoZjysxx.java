//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjysxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件验收信息
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class VoZjysxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID
  private String zzxxcwlb; //制证信息错误类别
  private String cwms; //错误描述

  public String getCwms() {
    return cwms;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public String getZzxxcwlb() {
    return zzxxcwlb;
  }

  public void setCwms(String cwms) {
    this.cwms = cwms;
  }

  public void setZzxxcwlb(String zzxxcwlb) {
    this.zzxxcwlb = zzxxcwlb;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

}
