package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户关系增加信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHgxzjxx
    extends DefaultVO {

  private Long hhid; //户号ID
  private Long glhhid; //关联户号ID
  private String glgx; //关联关系

  public String getGlgx() {
    return glgx;
  }

  public void setGlgx(String glgx) {
    this.glgx = glgx;
  }

  public Long getGlhhid() {
    return glhhid;
  }

  public void setGlhhid(Long glhhid) {
    this.glhhid = glhhid;
  }

  public Long getHhid() {
    return hhid;
  }

  public void setHhid(Long hhid) {
    this.hhid = hhid;
  }
}