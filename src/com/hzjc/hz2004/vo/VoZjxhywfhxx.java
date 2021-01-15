//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjxhywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件销毁业务返回信息
 */
public class VoZjxhywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 证件销毁返回信息
   */
  private VoZjxhfhxx voZjxhfhxx[];

  public VoZjxhfhxx[] getVoZjxhfhxx() {
    return voZjxhfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setVoZjxhfhxx(VoZjxhfhxx[] voZjxhfhxx) {
    this.voZjxhfhxx = voZjxhfhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

}
