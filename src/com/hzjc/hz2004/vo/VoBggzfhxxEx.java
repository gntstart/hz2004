package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更更正业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBggzfhxxEx
    extends DefaultVO {

  private VoBgryfhxx voBgryfhxx[]; //变更人员返回信息
  private VoBggzfhxx voBggzfhxx[]; //变更更正返回信息
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

  public VoBgryfhxx[] getVoBgryfhxx() {
    return voBgryfhxx;
  }

  public void setVoBgryfhxx(VoBgryfhxx[] voBgryfhxx) {
    this.voBgryfhxx = voBgryfhxx;
  }

}
