package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZwslxx  extends DefaultVO {
  public VoZwslxx() {
  }

  private Long nbslid;//内部受理ID
  private String slh;//受理号
  private String gmsfhm;//公民身份号码
  private Long zwybh;//指纹一编号
  private String zwyzw;//指纹一指位
  private String zwyzcjg;//指纹一注册结果
  private Long zwebh;//指纹二编号
  private String zwezw;//指纹二指位
  private String zwezcjg;//指纹二注册结果

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getSlh() {
    return slh;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setZwybh(Long zwybh) {
    this.zwybh = zwybh;
  }

  public Long getZwybh() {
    return zwybh;
  }

  public void setZwyzw(String zwyzw) {
    this.zwyzw = zwyzw;
  }

  public String getZwyzw() {
    return zwyzw;
  }

  public void setZwyzcjg(String zwyzcjg) {
    this.zwyzcjg = zwyzcjg;
  }

  public String getZwyzcjg() {
    return zwyzcjg;
  }

  public void setZwebh(Long zwebh) {
    this.zwebh = zwebh;
  }

  public Long getZwebh() {
    return zwebh;
  }

  public void setZwezw(String zwezw) {
    this.zwezw = zwezw;
  }

  public String getZwezw() {
    return zwezw;
  }

  public void setZwezcjg(String zwezcjg) {
    this.zwezcjg = zwezcjg;
  }

  public String getZwezcjg() {
    return zwezcjg;
  }

}
