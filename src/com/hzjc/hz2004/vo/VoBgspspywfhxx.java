package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更审批审批业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBgspspywfhxx
    extends DefaultVO {

  private VoBgspspfhxx voBgspspfhxx[]; //变更审批审批返回信息

  public VoBgspspfhxx[] getVoBgspspfhxx() {
    return voBgspspfhxx;
  }

  public void setVoBgspspfhxx(VoBgspspfhxx[] voBgspspfhxx) {
    this.voBgspspfhxx = voBgspspfhxx;
  }

}