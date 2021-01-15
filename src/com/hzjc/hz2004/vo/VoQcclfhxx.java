package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁出处理返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQcclfhxx
    extends DefaultVO {

  /**
   * 迁出处理ID
   */
  private Long qcclid;

  public Long getQcclid() {
    return qcclid;
  }

  public void setQcclid(Long qcclid) {
    this.qcclid = qcclid;
  }

}
