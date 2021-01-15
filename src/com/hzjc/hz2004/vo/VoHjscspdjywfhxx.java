package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户籍删除审批登记业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHjscspdjywfhxx
    extends DefaultVO {
  private VoHjscspdjfhxx voHjscspdjfhxx[]; //户籍删除审批登记信息

  public VoHjscspdjfhxx[] getVoHjscspdjfhxx() {
    return voHjscspdjfhxx;
  }

  public void setVoHjscspdjfhxx(VoHjscspdjfhxx[] voHjscspdjfhxx) {
    this.voHjscspdjfhxx = voHjscspdjfhxx;
  }

}