//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjsjxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件收交信息
 */
public class VoZjsjxx
    extends DefaultVO {

  private String sjyy; //收交原因
  private Long nbsfzid; //内部身份证ID

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public String getSjyy() {
    return sjyy;
  }

  public void setSjyy(String sjyy) {
    this.sjyy = sjyy;
  }
}
