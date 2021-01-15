//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjxhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件销毁信息
 */
public class VoZjxhxx
    extends DefaultVO {
  private Long nbsfzid; //内部身份证ID
  private String xhrq; //销毁日期
  private String xhr; //销毁人
  private String xhdw; //销毁单位

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public String getXhdw() {
    return xhdw;
  }

  public String getXhr() {
    return xhr;
  }

  public String getXhrq() {
    return xhrq;
  }

  public void setXhrq(String xhrq) {
    this.xhrq = xhrq;
  }

  public void setXhr(String xhr) {
    this.xhr = xhr;
  }

  public void setXhdw(String xhdw) {
    this.xhdw = xhdw;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

}
