/* Generated by Together */

package com.hzjc.wsstruts.vo;

import java.util.*;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class VoQueryResult
    implements VO {
  private List pagelist = null;
  private long recordcount = 0;
  private Class votype;

  public List getPagelist() {
    return pagelist;
  }

  public void setPagelist(List pagelist) {
    this.pagelist = pagelist;
  }

  public long getRecordcount() {
    return recordcount;
  }

  public void setRecordcount(long recordcount) {
    this.recordcount = recordcount;
  }

  public Class getVotype() {
    return votype;
  }

  public void setVotype(Class votype) {
    this.votype = votype;
  }
}
