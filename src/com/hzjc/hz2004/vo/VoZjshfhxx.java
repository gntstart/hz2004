package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoZjshfhxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID
  private Long shqfid; //审核签发ID

  public Long getNbslid() {
    return nbslid;
  }

  public Long getShqfid() {
    return shqfid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setShqfid(Long shqfid) {
    this.shqfid = shqfid;
  }

}