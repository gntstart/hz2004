package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁入审批登记子返回信息(随迁人员登记返回信息)
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrspdjzfhxx {

  private Long spsqzid; //审批申请子ID
  private String xm; //姓名
  private String gmsfhm; //公民身份号码

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getSpsqzid() {
    return spsqzid;
  }

  public void setSpsqzid(Long spsqzid) {
    this.spsqzid = spsqzid;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

}