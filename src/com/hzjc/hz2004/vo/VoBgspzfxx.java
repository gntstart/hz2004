package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更批审作废信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBgspzfxx
    extends DefaultVO {

  private Long spywid; //审批业务ID

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

}