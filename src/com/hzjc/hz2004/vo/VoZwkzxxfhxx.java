package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZwkzxxfhxx  extends DefaultVO {
  public VoZwkzxxfhxx() {
  }

  private Long zwbh;//人员ID
  private String zwtx;//指纹图像
  private float txzlz;//图像质量值


  public void setZwbh(Long zwbh) {
    this.zwbh = zwbh;
  }

  public Long getZwbh() {
    return zwbh;
  }

  public void setZwtx(String zwtx) {
    this.zwtx = zwtx;
  }

  public String getZwtx() {
    return zwtx;
  }

  public void setTxzlz(float txzlz) {
    this.txzlz = txzlz;
  }

  public float getTxzlz() {
    return txzlz;
  }

}
