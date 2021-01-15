package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 异地证件受理作废业务返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoYdslzfywfhxx
    extends DefaultVO {

  private VoYdslzffhxx voYdslzffhxx[]; //证件受理作废返回信息

  public VoYdslzfywfhxx() {
  }

  public VoYdslzffhxx[] getVoYdslzffhxx() {
    return voYdslzffhxx;
  }

  public void setVoYdslzffhxx(VoYdslzffhxx[] voYdslzffhxx) {
    this.voYdslzffhxx = voYdslzffhxx;
  }

}