package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * <p>Title: </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class VoCxbcrzxx extends DefaultVO {
  private String gnbh;//功能编号
  private String cxyj;//查询语句

  public void setGnbh(String gnbh) {
    this.gnbh = gnbh;
  }

  public String getGnbh() {
    return gnbh;
  }

  public void setCxyj(String cxyj) {
    this.cxyj = cxyj;
  }

  public String getCxyj() {
    return cxyj;
  }
}
