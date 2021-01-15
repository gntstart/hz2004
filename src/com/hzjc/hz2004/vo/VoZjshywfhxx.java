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

public class VoZjshywfhxx
    extends DefaultVO {

  private Long zjywid; //证件业务ID
  private VoZjshfhxx[] voZjshfhxx; //证件审核返回信息

  public Long getZjywid() {
    return zjywid;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public VoZjshfhxx[] getVoZjshfhxx() {
    return voZjshfhxx;
  }

  public void setVoZjshfhxx(VoZjshfhxx[] voZjshfhxx) {
    this.voZjshfhxx = voZjshfhxx;
  }
}