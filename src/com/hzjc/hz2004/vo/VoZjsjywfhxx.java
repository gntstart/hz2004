//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjsjywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件收交业务返回信息
 */
public class VoZjsjywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 证件收交返回信息
   */
  private VoZjsjfhxx voZjsjfhxx[];

  public VoZjsjfhxx[] getVoZjsjfhxx() {
    return voZjsjfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setVoZjsjfhxx(VoZjsjfhxx[] voZjsjfhxx) {
    this.voZjsjfhxx = voZjsjfhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

}
