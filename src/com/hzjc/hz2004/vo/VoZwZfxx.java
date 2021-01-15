package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZwZfxx  extends DefaultVO {
  public VoZwZfxx() {
  }

  private Long zwbh;//指纹编号
  private Long ryid;//人员ID
  private Long rynbid;//人员ID
  private String zfyy;//作废原因

  public void setZwbh(Long zwbh) {
    this.zwbh = zwbh;
  }

  public Long getZwbh() {
    return zwbh;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setZfyy(String zfyy) {
    this.zfyy = zfyy;
  }

  public String getZfyy() {
    return zfyy;
  }

}
