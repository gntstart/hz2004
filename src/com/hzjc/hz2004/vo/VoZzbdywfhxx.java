package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.vo.*;

/**
 * 住址变动业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZzbdywfhxx
    extends DefaultVO {

  /**
   * 户籍业务ID
   */
  private Long hjywid;

  /**
   * 门（楼）牌内部ID
   */
  private Long mlpnbid;

  /**
   * 户号内部ID
   */
  private Long hhnbid;

  /**
   * 住址变动返回信息
   */
  private VoZzbdfhxx voZzbdfhxx[];

  /**
   * 变更更正返回信息
   */
  private VoBggzfhxx voBggzfhxx[];

  private VoHcygxtzfhxx voHcygxtzfhxx[]; //户成员关系调整返回信息

  public VoHcygxtzfhxx[] getVoHcygxtzfhxx() {
    return voHcygxtzfhxx;
  }

  public void setVoHcygxtzfhxx(VoHcygxtzfhxx[] voHcygxtzfhxx) {
    this.voHcygxtzfhxx = voHcygxtzfhxx;
  }

  public VoBggzfhxx[] getVoBggzfhxx() {
    return voBggzfhxx;
  }

  public void setVoBggzfhxx(VoBggzfhxx[] voBggzfhxx) {
    this.voBggzfhxx = voBggzfhxx;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public VoZzbdfhxx[] getVoZzbdfhxx() {
    return voZzbdfhxx;
  }

  public void setVoZzbdfhxx(VoZzbdfhxx[] voZzbdfhxx) {
    this.voZzbdfhxx = voZzbdfhxx;
  }
}
