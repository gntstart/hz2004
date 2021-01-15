package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 地址冻结增加业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzdjzjywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;
  /**
   * 地址冻结返回信息
   */
  private VoDzdjzjfhxx voDzdjzjfhxx[];

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoDzdjzjfhxx[] getVoDzdjzjfhxx() {
    return voDzdjzjfhxx;
  }

  public void setVoDzdjzjfhxx(VoDzdjzjfhxx[] voDzdjzjfhxx) {
    this.voDzdjzjfhxx = voDzdjzjfhxx;
  }

}
