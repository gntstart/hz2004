//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjslywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件受理业务返回信息
 */
public class VoZjslywfhxx
    extends DefaultVO {

  private Long zjywid; //证件业务ID
  private VoZjslfhxx voZjslfhxx[]; //证件受理返回信息

  public VoZjslfhxx[] getVoZjslfhxx() {
    return voZjslfhxx;
  }

  public void setVoZjslfhxx(VoZjslfhxx[] voZjslfhxx) {
    this.voZjslfhxx = voZjslfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

}
