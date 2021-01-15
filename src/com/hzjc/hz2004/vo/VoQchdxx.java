package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁出户地信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQchdxx
    extends DefaultVO {

  /**
   * 户号内部ID
   */
  private Long hhndid;

  public Long getHhndid() {
    return hhndid;
  }

  public void setHhndid(Long hhndid) {
    this.hhndid = hhndid;
  }
}
