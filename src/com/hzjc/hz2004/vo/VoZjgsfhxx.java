//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjgsfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件挂失返回信息
 */
public class VoZjgsfhxx
    extends DefaultVO {

  /**
   *
   */
  public VoZjgsfhxx() {

  }

  /**
   *
   * @param lGsslid
   * @param lNbsfzid
   */
  public VoZjgsfhxx(Long lGsslid, Long lNbsfzid) {
    setGsslid(lGsslid);
    setNbsfzid(lNbsfzid);
  }

  /**
   * 挂失受理ID
   */
  private Long gsslid;

  /**
   * 内部身份证ID
   */
  private Long nbsfzid;

  public Long getGsslid() {
    return gsslid;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setGsslid(Long gsslid) {
    this.gsslid = gsslid;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

}
