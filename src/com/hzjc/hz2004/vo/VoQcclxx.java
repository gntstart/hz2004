package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁出处理信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQcclxx
    extends DefaultVO {

  private Long qcclid; //迁出处理ID
  private String clbz; //处理标志(0-未处理/1-已处理/2-已处理+打印存根)

  public Long getQcclid() {
    return qcclid;
  }

  public void setQcclid(Long qcclid) {
    this.qcclid = qcclid;
  }

  public String getClbz() {
    return clbz;
  }

  public void setClbz(String clbz) {
    this.clbz = clbz;
  }

}
