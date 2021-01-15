package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 拍照保存返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzbcfhxx
    extends DefaultVO {

  private Long zplsid; //照片流水ID
  private String slh; //受理号
  private String gmsfhm; //公民身份号码

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getSlh() {
    return slh;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public Long getZplsid() {
    return zplsid;
  }

  public void setZplsid(Long zplsid) {
    this.zplsid = zplsid;
  }

}