package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件统计结果Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author
 * @version 1.0
 */
public class VoFjjgtj
    extends DefaultVO {

  private Long cwh; //槽位号
  private Long count; //统计数量

  public VoFjjgtj() {
  }

  public Long getCwh() {
    return cwh;
  }

  public void setCwh(Long cwh) {
    this.cwh = cwh;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

}
