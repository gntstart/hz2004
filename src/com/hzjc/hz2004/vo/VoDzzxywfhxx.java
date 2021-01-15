package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 地址注销业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzzxywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 地址注销返回信息
   */
  private VoDzzxfhxx voDzzxfhxx[];

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoDzzxfhxx[] getVoDzzxfhxx() {
    return voDzzxfhxx;
  }

  public void setVoDzzxfhxx(VoDzzxfhxx[] voDzzxfhxx) {
    this.voDzzxfhxx = voDzzxfhxx;
  }

}
