/* Generated by Together */

package com.hzjc.wsstruts.vo;

import java.util.*;

/**
 * Delphi
 * <p>Title: Hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */
public class CdsVO
    implements VO {

  private Map oldmap = null; //
  private Map curMap = null; //
  private Map newmap = null; //
  private Map map = null; //

  //(1 1 1 1)
  public final static int ROW_STATE_ADD = 4;
  public final static int ROW_STATE_DELETE = 2;
  public final static int ROW_STATE_UPDATE = 8;
  public final static int ROW_STATE_RETRIEVE = 1;
  private int rowstate = 1; //̬

  ////////////////////////////////////////////////////////////////////////////
  //Delphiclientdataset
  ////////////////////////////////////////////////////////////////////////////
  public Map getOldmap() {
    return oldmap;
  }

  public void setOldmap(Map oldmap) {
    this.oldmap = oldmap;
  }

  protected Map getCurMap() {
    return curMap;
  }

  protected void setCurMap(Map curMap) {
    this.curMap = curMap;
  }

  public Map getNewmap() {
    return newmap;
  }

  public void setNewmap(Map newmap) {
    this.newmap = newmap;
  }

  public int getRowstate() {
    return rowstate;
  }

  public void setRowstate(int rowstate) {
    this.rowstate = rowstate;
  }

  /**
   *map = oldmap + newmap
   * @return
   */
  public Map getMap() {
    //ֵ
    map = new HashMap();
    map = getNewmap();
    //
    if (getRowstate() == ROW_STATE_UPDATE) {
      map = getOldmap();
      //ֵ
      for (Iterator iter = getNewmap().entrySet().iterator(); iter.hasNext(); ) {
        Map.Entry entry = (Map.Entry) iter.next();
        //֮
        map.put(entry.getKey(), entry.getValue());
      }
    }
    return map;
  }

  /**
   *
   * @param map
   */
  protected void setMap(Map map) {
    this.map = map;
  }
  /////////////////////////////////////////////////////////////////////////////
}
