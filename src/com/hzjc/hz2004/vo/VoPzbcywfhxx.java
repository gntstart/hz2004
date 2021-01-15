package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 拍照保存业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzbcywfhxx
    extends DefaultVO {

  private VoPzbcfhxx voPzbcfhxx[]; //拍照保存返回信息VO

  public VoPzbcfhxx[] getVoPzbcfhxx() {
    return voPzbcfhxx;
  }

  public void setVoPzbcfhxx(VoPzbcfhxx[] voPzbcfhxx) {
    this.voPzbcfhxx = voPzbcfhxx;
  }

}