package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户别变更审批审批信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgspspxx
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

  public Long getDzid() {
    return dzid;
  }

  public void setDzid(Long dzid) {
    this.dzid = dzid;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

}
