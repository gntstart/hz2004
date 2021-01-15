package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户别变更审批审批业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgspspywfhxx
    extends DefaultVO {

  private VoHbbgspspfhxx voHbbgspspfhxx[]; //户籍删除审批审批返回信息

  public void setVoHbbgspspfhxx(VoHbbgspspfhxx[] voHbbgspspfhxx) {
    this.voHbbgspspfhxx = voHbbgspspfhxx;
  }

  public VoHbbgspspfhxx[] getVoHbbgspspfhxx() {
    return voHbbgspspfhxx;
  }

}
