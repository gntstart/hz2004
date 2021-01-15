package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZpscywfhxx
    extends DefaultVO {

  private Long hjywid; //户籍业务ID
  private VoZpscfhxx voZpscfhxx[]; //照片删除返回信息Vo

  public VoZpscywfhxx() {
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public VoZpscfhxx[] getVoZpscfhxx() {
    return voZpscfhxx;
  }

  public void setVoZpscfhxx(VoZpscfhxx[] voZpscfhxx) {
    this.voZpscfhxx = voZpscfhxx;
  }

}