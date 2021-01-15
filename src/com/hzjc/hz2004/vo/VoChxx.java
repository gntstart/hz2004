package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 重号信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoChxx
    extends DefaultVO {

  private String chsfhm; //重号身份号码
  private String chxm; //重号姓名
  private Long bchryid; //被重号人员ID

  public Long getBchryid() {
    return bchryid;
  }

  public void setBchryid(Long bchryid) {
    this.bchryid = bchryid;
  }

  public String getChsfhm() {
    return chsfhm;
  }

  public void setChsfhm(String chsfhm) {
    this.chsfhm = chsfhm;
  }

  public String getChxm() {
    return chxm;
  }

  public void setChxm(String chxm) {
    this.chxm = chxm;
  }

}