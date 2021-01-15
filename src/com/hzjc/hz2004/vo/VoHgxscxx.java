package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户关系删除信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHgxscxx
    extends DefaultVO {

  private Long glid; //关联ID
  private Long hhid; //户号ID
  private Long glhhid; //关联户号ID

  public Long getGlhhid() {
    return glhhid;
  }

  public void setGlhhid(Long glhhid) {
    this.glhhid = glhhid;
  }

  public Long getGlid() {
    return glid;
  }

  public void setGlid(Long glid) {
    this.glid = glid;
  }

  public Long getHhid() {
    return hhid;
  }

  public void setHhid(Long hhid) {
    this.hhid = hhid;
  }

}