package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 异地证件打包量控制返回Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author lzh54321@hotmail.com
 * @version 1.0
 */
public class VoYdslDblkzfhxx
    extends DefaultVO {

  private String sbddm;

  public VoYdslDblkzfhxx() {
  }

  public void setSbddm(String sbddm) {
    this.sbddm = sbddm;
  }

  public String getSbddm() {
    return sbddm;
  }
}