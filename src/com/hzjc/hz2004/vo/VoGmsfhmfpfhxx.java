package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 公民身份号码分配返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoGmsfhmfpfhxx
    extends DefaultVO {

  //分配ID
  private Long fpid;

  //公民身份号码
  private String gmsfhm;

  public Long getFpid() {
    return fpid;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public void setFpid(Long fpid) {
    this.fpid = fpid;
  }

}