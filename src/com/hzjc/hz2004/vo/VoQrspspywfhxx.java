package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁入审批审批业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrspspywfhxx
    extends DefaultVO {

  private VoQrspspfhxx voQrspspfhxx[];

  public VoQrspspfhxx[] getVoQrspspfhxx() {
    return voQrspspfhxx;
  }

  public void setVoQrspspfhxx(VoQrspspfhxx[] voQrspspfhxx) {
    this.voQrspspfhxx = voQrspspfhxx;
  }

}