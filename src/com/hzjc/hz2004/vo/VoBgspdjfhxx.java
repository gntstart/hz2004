package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更审批登记返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBgspdjfhxx
    extends DefaultVO {

  private Long splsid; //审批流水ID
  private Long spywid; //审批业务ID
  private Long rynbid; //人员内部ID
  private Long ryid; //人员ID
  private String xm; //姓名
  private String gmsfhm; //公民身份号码

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public Long getRyid() {
    return ryid;
  }

  public Long getSplsid() {
    return splsid;
  }

  public void setSplsid(Long splsid) {
    this.splsid = splsid;
  }

}