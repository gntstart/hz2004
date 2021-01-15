package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.po.PO;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author
 * @version 1.0
 */

public class VoLsSfzDybzfhxx
    implements PO {
  private String tj;
  private String dybz;
  private int count;


  public VoLsSfzDybzfhxx() {
  }

  public VoLsSfzDybzfhxx(String strTj, String strDybz, int iCount) {
    this.tj = strTj;
    this.dybz = strDybz;
    this.count = iCount;
  }

  public String getTj() {
    return tj;
  }

  public void setTj(String tj) {
    this.tj = tj;
  }

  public String getDybz() {
    return dybz;
  }

  public void setDybz(String dybz) {
    this.dybz = dybz;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

}
