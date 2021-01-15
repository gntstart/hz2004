//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjsjfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件收交返回信息
 */
public class VoZjsjfhxx
    extends DefaultVO {

  public VoZjsjfhxx(){

  }

  /**
   *
   * @param lSjslid
   * @param lNbsfzid
   */
  public VoZjsjfhxx(Long lSjslid,Long lNbsfzid){
    setSjslid(lSjslid);
    setNbsfzid(lNbsfzid);
  }

  /**
   * 收交受理ID
   */
  private Long sjslid;

  /**
   * 内部身份证ID
   */
  private Long nbsfzid;

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public Long getSjslid() {
    return sjslid;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public void setSjslid(Long sjslid) {
    this.sjslid = sjslid;
  }

}
