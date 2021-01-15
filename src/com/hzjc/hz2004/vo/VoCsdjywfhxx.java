package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 出生登记业务返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoCsdjywfhxx
    extends DefaultVO {

  private Long hjywid; //户籍业务ID
  private Long mlpnbid; //门（楼）牌内部ID
  private Long hhnbid; //户号内部ID
  private VoCsdjfhxx voCsdjfhxx[]; //出生登记返回信息
  private VoBggzfhxx voBggzfhxx[]; //变更更正返回信息
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

  public Long getHjywid() {
    return hjywid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public VoCsdjfhxx[] getVoCsdjfhxx() {
    return voCsdjfhxx;
  }

  public void setVoCsdjfhxx(VoCsdjfhxx[] voCsdjfhxx) {
    this.voCsdjfhxx = voCsdjfhxx;
  }

}
