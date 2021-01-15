package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 准迁证编号回填返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZqzbhhtfhxx
    extends DefaultVO {

  private Long zqid; //准迁ID
  private String zqzjlx; //准迁证件类型
  private String zjbh; //证件编号
  private String sqrgmsfhm; //申请人公民身份号码
  private String sqrxm; //申请人姓名

  public String getSqrgmsfhm() {
    return sqrgmsfhm;
  }

  public void setSqrgmsfhm(String sqrgmsfhm) {
    this.sqrgmsfhm = sqrgmsfhm;
  }

  public String getSqrxm() {
    return sqrxm;
  }

  public void setSqrxm(String sqrxm) {
    this.sqrxm = sqrxm;
  }

  public Long getZqid() {
    return zqid;
  }

  public void setZqid(Long zqid) {
    this.zqid = zqid;
  }

  public String getZjbh() {
    return zjbh;
  }

  public void setZjbh(String zjbh) {
    this.zjbh = zjbh;
  }

  public String getZqzjlx() {
    return zqzjlx;
  }

  public void setZqzjlx(String zqzjlx) {
    this.zqzjlx = zqzjlx;
  }

}