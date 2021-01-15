//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjlqffywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件领取发放业务返回信息
 */
public class VoZjlqffywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 证件领取发放信息数组
   */
  private VoZjlqfffhxx voZjlqfffhxx[];

  public VoZjlqfffhxx[] getVoZjlqfffhxx() {
    return voZjlqfffhxx;
  }

  public void setVoZjlqfffhxx(VoZjlqfffhxx[] voZjlqfffhxx) {
    this.voZjlqfffhxx = voZjlqfffhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public Long getZjywid() {
    return zjywid;
  }
}
