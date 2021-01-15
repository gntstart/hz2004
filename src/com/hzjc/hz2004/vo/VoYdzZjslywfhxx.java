//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoYdzZjslywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 一代证证件受理业务返回信息
 */
public class VoYdzZjslywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 一代证证件受理返回信息
   */
  private VoYdzZjslfhxx voYdzZjslfhxx[];

  public VoYdzZjslfhxx[] getVoYdzZjslfhxx() {
    return voYdzZjslfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setVoYdzZjslfhxx(VoYdzZjslfhxx[] voYdzZjslfhxx) {
    this.voYdzZjslfhxx = voYdzZjslfhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }
}
