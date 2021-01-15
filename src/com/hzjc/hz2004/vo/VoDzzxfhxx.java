package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 地址注销返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzzxfhxx
    extends DefaultVO {

  /**
   * 门（楼）牌内部ID
   */
  private Long mlpnbid;
  private Long old_mlpnbid; //门楼牌内部ID

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
