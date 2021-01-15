package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 全户变更业务返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQhbgywfhxx
    extends DefaultVO {

  private Long hjywid; //户籍业务ID
  private VoQhbgfhxx voQhbgfhxx[]; //全户变更返回信息

  public VoQhbgywfhxx() {
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoQhbgfhxx[] getVoQhbgfhxx() {
    return voQhbgfhxx;
  }

  public void setVoQhbgfhxx(VoQhbgfhxx[] voQhbgfhxx) {
    this.voQhbgfhxx = voQhbgfhxx;
  }

}