package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更审批登记业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBgspdjywfhxx
    extends DefaultVO {

  private VoBgspdjfhxx voBgspdjfhxx[]; //变更审批登记返回信息VO

  public VoBgspdjfhxx[] getVoBgspdjfhxx() {
    return voBgspdjfhxx;
  }

  public void setVoBgspdjfhxx(VoBgspdjfhxx[] voBgspdjfhxx) {
    this.voBgspdjfhxx = voBgspdjfhxx;
  }

}