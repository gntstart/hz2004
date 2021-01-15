package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 审批附带材料修改业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSpfdclxgywfhxx
    extends DefaultVO {

  private Long spywid; //审批业务ID
  private String splx; //审批类型
  private VoSpfdclxgfhxx voSpfdclxgfhxx[]; //审批附带材料修改返回信息

  public VoSpfdclxgywfhxx() {
  }

  public String getSplx() {
    return splx;
  }

  public void setSplx(String splx) {
    this.splx = splx;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public VoSpfdclxgfhxx[] getVoSpfdclxgfhxx() {
    return voSpfdclxgfhxx;
  }

  public void setVoSpfdclxgfhxx(VoSpfdclxgfhxx[] voSpfdclxgfhxx) {
    this.voSpfdclxgfhxx = voSpfdclxgfhxx;
  }

}