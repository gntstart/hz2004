package com.hzjc.hz2004.vo;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtywxz {

  private boolean limitflag;
  private String limitinfo;
  private Long spmbid;

  public VoXtywxz() {
  }

  public void setLimitflag(boolean bFlag) {
    this.limitflag = bFlag;
  }

  public boolean getLimitflag() {
    return this.limitflag;
  }

  public void setLimitinfo(String sInfo) {
    this.limitinfo = sInfo;
  }

  public String getLimitinfo() {
    return this.limitinfo;
  }
  public Long getSpmbid() {
    return spmbid;
  }
  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

}