//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoYdzZjslfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 一代证证件受理返回信息
 */
public class VoYdzZjslfhxx
    extends DefaultVO {

  /**
   *
   */
  public VoYdzZjslfhxx() {

  }

  /**
   *
   * @param lBzslid
   * @param lRynbid
   */
  public VoYdzZjslfhxx(Long lBzslid, Long lRynbid) {
    setBzslid(lBzslid);
    setRynbid(lRynbid);
  }

  /**
   * 办证受理ID
   */
  private Long bzslid;

  /**
   * 人员内部ID
   */
  private Long rynbid;

  public Long getBzslid() {
    return bzslid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setBzslid(Long bzslid) {
    this.bzslid = bzslid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }
}
