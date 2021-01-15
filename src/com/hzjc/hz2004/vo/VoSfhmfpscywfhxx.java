package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 身份号码分配删除业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSfhmfpscywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 身份号码分配补录返回信息
   */
  private VoSfhmfpscfhxx voSfhmfpscfhxx[];

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoSfhmfpscfhxx[] getVoSfhmfpscfhxx() {
    return voSfhmfpscfhxx;
  }

  public void setVoSfhmfpscfhxx(VoSfhmfpscfhxx[] voSfhmfpscfhxx) {
    this.voSfhmfpscfhxx = voSfhmfpscfhxx;
  }

}