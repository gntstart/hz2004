package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 地址冻结删除业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzdjscywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 地址冻结删除返回信息
   */
  private VoDzdjscfhxx voDzdjscfhxx[];

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoDzdjscfhxx[] getVoDzdjscfhxx() {
    return voDzdjscfhxx;
  }

  public void setVoDzdjscfhxx(VoDzdjscfhxx[] voDzdjscfhxx) {
    this.voDzdjscfhxx = voDzdjscfhxx;
  }
}
