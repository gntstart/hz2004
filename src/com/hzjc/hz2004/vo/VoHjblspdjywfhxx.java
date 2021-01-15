package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户籍补录审批登记业务返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHjblspdjywfhxx
    extends DefaultVO {

  private VoHjblspdjfhxx voHjblspdjfhxx[]; //籍补录审批登记返回信息

  public VoHjblspdjfhxx[] getVoHjblspdjfhxx() {
    return voHjblspdjfhxx;
  }

  public void setVoHjblspdjfhxx(VoHjblspdjfhxx[] voHjblspdjfhxx) {
    this.voHjblspdjfhxx = voHjblspdjfhxx;
  }

}