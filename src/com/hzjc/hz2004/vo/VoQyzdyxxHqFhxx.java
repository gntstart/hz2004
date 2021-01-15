package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJLS_HJYWLSB;

/**
 * 迁移证打印信息获取返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQyzdyxxHqFhxx {
  public VoQyzdyxxHqFhxx() {
  }

  private PoHJLS_HJYWLSB poHJLS_HJYWLSB;

  private VoQczxryxx voQczxryxx[];

  private VoZzbdryxx voZzbdryxx[];

  public PoHJLS_HJYWLSB getPoHJLS_HJYWLSB() {
    return poHJLS_HJYWLSB;
  }

  public void setPoHJLS_HJYWLSB(PoHJLS_HJYWLSB poHJLS_HJYWLSB) {
    this.poHJLS_HJYWLSB = poHJLS_HJYWLSB;
  }

  public VoQczxryxx[] getVoQczxryxx() {
    return voQczxryxx;
  }

  public void setVoQczxryxx(VoQczxryxx[] voQczxryxx) {
    this.voQczxryxx = voQczxryxx;
  }

  public VoZzbdryxx[] getVoZzbdryxx() {
    return voZzbdryxx;
  }

  public void setVoZzbdryxx(VoZzbdryxx[] voZzbdryxx) {
    this.voZzbdryxx = voZzbdryxx;
  }

}