package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户成员关系调整信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHcygxtzxx
    extends DefaultVO {

  /**
   * 人员内部ID
   */
  private Long rynbid;

  /**
   * 与户主关系
   */
  private String yhzgx;

  /*
   * 上笔户籍业务ID
   */
  private Long sbhjywid;

  private String hcybdlb; //户成员变动类别

  public Long getRynbid() {
    return rynbid;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public String getHcybdlb() {
    return hcybdlb;
  }

  public void setHcybdlb(String hcybdlb) {
    this.hcybdlb = hcybdlb;
  }

}
