package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户别变更审批登记业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgspdjywfhxx
    extends DefaultVO {
  private VoHbbgspdjfhxx voHbbgspdjfhxx[]; //户别变更审批登记返回信息

  public VoHbbgspdjfhxx[] getVoHbbgspdjfhxx() {
    return voHbbgspdjfhxx;
  }

  public void setVoHbbgspdjfhxx(VoHbbgspdjfhxx[] voHbbgspdjfhxx) {
    this.voHbbgspdjfhxx = voHbbgspdjfhxx;
  }
}