package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 区划调整业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQhtzywfhxx
    extends DefaultVO {

  private Long hjywid; //户籍业务ID
  private VoQhtzfhxx voQhtzfhxx[]; //地址修改返回信息

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoQhtzfhxx[] getVoQhtzfhxx() {
    return voQhtzfhxx;
  }

  public void setVoQhtzfhxx(VoQhtzfhxx[] voQhtzfhxx) {
    this.voQhtzfhxx = voQhtzfhxx;
  }

}