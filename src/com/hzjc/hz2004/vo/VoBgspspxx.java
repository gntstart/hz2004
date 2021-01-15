package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoBgspspxx
    extends DefaultVO {

  private Long spywid; //审批业务ID
  private Long dzid; //动作ID
  private String czjg; //操作结果
  private String czyj; //操作意见

  public String getCzjg() {
    return czjg;
  }

  public void setCzjg(String czjg) {
    this.czjg = czjg;
  }

  public String getCzyj() {
    return czyj;
  }

  public void setCzyj(String czyj) {
    this.czyj = czyj;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public Long getDzid() {
    return dzid;
  }

  public void setDzid(Long dzid) {
    this.dzid = dzid;
  }

}