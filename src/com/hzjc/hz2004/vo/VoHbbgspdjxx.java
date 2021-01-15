package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户别变更审批登记信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgspdjxx
    extends DefaultVO {

  private Long rynbid; //人员内部ID
  private String hbbglb; //户别变更类别
  private String bghhb; //变更后户别
  private String sbsj; //申报时间
  private String sbryxm; //申报人员姓名
  private String sbrgmsfhm; //申报人公民身份号码

  public String getBghhb() {
    return bghhb;
  }

  public void setBghhb(String bghhb) {
    this.bghhb = bghhb;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getHbbglb() {
    return hbbglb;
  }

  public void setHbbglb(String hbbglb) {
    this.hbbglb = hbbglb;
  }

}