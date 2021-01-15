package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 注销户信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZxhxx
    extends DefaultVO {

  private Long old_hhnbid;
  private Long new_hhnbid;

  public void LogoutHXX() {
  }

  public Long getNew_hhnbid() {
    return new_hhnbid;
  }

  public void setNew_hhnbid(Long new_hhnbid) {
    this.new_hhnbid = new_hhnbid;
  }

  public Long getOld_hhnbid() {
    return old_hhnbid;
  }

  public void setOld_hhnbid(Long old_hhnbid) {
    this.old_hhnbid = old_hhnbid;
  }

}