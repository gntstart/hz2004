package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件验收返回信息
 */
public class VoZjysfhxx
    extends DefaultVO {

  private Long ysslid; //验收受理ID
  private Long nbslid; //内部受理ID

  public Long getNbslid() {
    return nbslid;
  }

  public Long getYsslid() {
    return ysslid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setYsslid(Long ysslid) {
    this.ysslid = ysslid;
  }

}
