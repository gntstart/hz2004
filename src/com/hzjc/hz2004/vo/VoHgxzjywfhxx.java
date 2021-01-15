package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户关系增加业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHgxzjywfhxx
    extends DefaultVO {

  private VoHgxzjfhxx voHgxzjfhxx[]; //户关系增加返回信息

  public VoHgxzjfhxx[] getVoHgxzjfhxx() {
    return voHgxzjfhxx;
  }

  public void setVoHgxzjfhxx(VoHgxzjfhxx[] voHgxzjfhxx) {
    this.voHgxzjfhxx = voHgxzjfhxx;
  }

}