package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 地址冻结修改返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzdjxgfhxx
    extends DefaultVO {

  /**
   * 门（楼）牌冻结ID
   */
  private Long mlpdjid;

  public Long getMlpdjid() {
    return mlpdjid;
  }

  public void setMlpdjid(Long mlpdjid) {
    this.mlpdjid = mlpdjid;
  }
}
