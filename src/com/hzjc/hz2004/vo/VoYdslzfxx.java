package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 异地证件受理作废信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoYdslzfxx
    extends DefaultVO {

  private Long sbxxid; //内部受理ID

  public VoYdslzfxx() {
  }

  public Long getSbxxid() {
    return sbxxid;
  }

  public void setSbxxid(Long sbxxid) {
    this.sbxxid = sbxxid;
  }

}