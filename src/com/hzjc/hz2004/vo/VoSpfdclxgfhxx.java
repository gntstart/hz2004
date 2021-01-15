package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 审批附带材料修改返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSpfdclxgfhxx
    extends DefaultVO {

  private Long spclid; //审批材料ID

  public VoSpfdclxgfhxx() {
  }

  public Long getSpclid() {
    return spclid;
  }

  public void setSpclid(Long spclid) {
    this.spclid = spclid;
  }

}