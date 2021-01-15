package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更审批作废业务返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBgspzfywfhxx
    extends DefaultVO {

  private VoBgspzffhxx voBgspzffhxx[]; //变更审批作废返回信息

  public VoBgspzffhxx[] getVoBgspzffhxx() {
    return voBgspzffhxx;
  }

  public void setVoBgspzffhxx(VoBgspzffhxx[] voBgspzffhxx) {
    this.voBgspzffhxx = voBgspzffhxx;
  }
}