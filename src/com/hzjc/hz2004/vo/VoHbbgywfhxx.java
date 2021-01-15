package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户别变更业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 户别变更返回信息
   */
  private VoHbbgfhxx voHbbgfhxx[];

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

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public VoHbbgfhxx[] getVoHbbgfhxx() {
    return voHbbgfhxx;
  }

  public void setVoHbbgfhxx(VoHbbgfhxx[] voHbbgfhxx) {
    this.voHbbgfhxx = voHbbgfhxx;
  }
}
