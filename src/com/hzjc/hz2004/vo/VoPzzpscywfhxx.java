package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 拍照照片删除业务返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzzpscywfhxx
    extends DefaultVO {

  private VoPzzpscfhxx voPzzpscfhxx[];

  public VoPzzpscywfhxx() {
  }

  public VoPzzpscfhxx[] getVoPzzpscfhxx() {
    return voPzzpscfhxx;
  }

  public void setVoPzzpscfhxx(VoPzzpscfhxx[] voPzzpscfhxx) {
    this.voPzzpscfhxx = voPzzpscfhxx;
  }

}