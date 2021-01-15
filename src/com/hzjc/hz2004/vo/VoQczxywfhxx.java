package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁出注销业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQczxywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 迁出注销返回信息
   */
  private VoQczxfhxx voQczxfhxx[];

  /**
   * 变更更正返回信息
   */
  private VoBggzfhxx voBggzfhxx[];

  private VoHcygxtzfhxx voHcygxtzfhxx[]; //户成员关系调整返回信息

  public VoHcygxtzfhxx[] getVoHcygxtzfhxx() {
    return voHcygxtzfhxx;
  }

  public void setVoHcygxtzfhxx(VoHcygxtzfhxx[] voHcygxtzfhxx) {
    this.voHcygxtzfhxx = voHcygxtzfhxx;
  }

  public VoBggzfhxx[] getVoBggzfhxx() {
    return voBggzfhxx;
  }

  public void setVoBggzfhxx(VoBggzfhxx[] voBggzfhxx) {
    this.voBggzfhxx = voBggzfhxx;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoQczxfhxx[] getVoQczxfhxx() {
    return voQczxfhxx;
  }

  public void setVoQczxfhxx(VoQczxfhxx[] voQczxfhxx) {
    this.voQczxfhxx = voQczxfhxx;
  }

}
