package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 区划调整返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQhtzfhxx
    extends DefaultVO {

  private Long mlpnbid; //门（楼）牌内部ID
  private Long old_mlpnbid; //老的门楼牌内部ID

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getOld_mlpnbid() {
    return old_mlpnbid;
  }

  public void setOld_mlpnbid(Long old_mlpnbid) {
    this.old_mlpnbid = old_mlpnbid;
  }

}