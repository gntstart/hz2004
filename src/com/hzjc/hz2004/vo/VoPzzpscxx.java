package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 拍照照片删除信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzzpscxx
    extends DefaultVO {

  private Long zplsid; //照片流水ID

  public VoPzzpscxx() {
  }

  public Long getZplsid() {
    return zplsid;
  }

  public void setZplsid(Long zplsid) {
    this.zplsid = zplsid;
  }

}