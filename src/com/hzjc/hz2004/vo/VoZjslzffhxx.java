package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件受理作废返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZjslzffhxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID

  public VoZjslzffhxx() {
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

}