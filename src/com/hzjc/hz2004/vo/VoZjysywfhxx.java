//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjysywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件验收业务返回信息
 */
public class VoZjysywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 证件验收返回信息
   */
  private VoZjysfhxx voZjysfhxx[];

  public VoZjysfhxx[] getVoZjysfhxx() {
    return voZjysfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setVoZjysfhxx(VoZjysfhxx[] voZjysfhxx) {
    this.voZjysfhxx = voZjysfhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

}
