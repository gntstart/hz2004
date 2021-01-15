//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjgsywfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件挂失业务返回信息
 */
public class VoZjgsywfhxx
    extends DefaultVO {

  /**
   * 证件业务ID
   */
  private Long zjywid;

  /**
   * 证件挂失返回信息
   */
  private VoZjgsfhxx voZjgsfhxx[];

  public VoZjgsfhxx[] getVoZjgsfhxx() {
    return voZjgsfhxx;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setVoZjgsfhxx(VoZjgsfhxx[] voZjgsfhxx) {
    this.voZjgsfhxx = voZjgsfhxx;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

}
