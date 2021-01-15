package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoBgspjgclywfhxx
    extends DefaultVO {

  private Long hjywid; //户籍业务ID
  private VoBggzfhxx voBggzfhxx[]; //变更更正返回信息
  private VoHcygxtzfhxx voHcygxtzfhxx[]; //户成员关系调整返回信息
  private VoBgspjgclfhxx voBgspjgclfhxx[]; //变更审批结果处理返回信息

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoBggzfhxx[] getVoBggzfhxx() {
    return voBggzfhxx;
  }

  public void setVoBggzfhxx(VoBggzfhxx[] voBggzfhxx) {
    this.voBggzfhxx = voBggzfhxx;
  }

  public VoHcygxtzfhxx[] getVoHcygxtzfhxx() {
    return voHcygxtzfhxx;
  }

  public void setVoHcygxtzfhxx(VoHcygxtzfhxx[] voHcygxtzfhxx) {
    this.voHcygxtzfhxx = voHcygxtzfhxx;
  }

  public VoBgspjgclfhxx[] getVoBgspjgclfhxx() {
    return voBgspjgclfhxx;
  }

  public void setVoBgspjgclfhxx(VoBgspjgclfhxx[] voBgspjgclfhxx) {
    this.voBgspjgclfhxx = voBgspjgclfhxx;
  }

}