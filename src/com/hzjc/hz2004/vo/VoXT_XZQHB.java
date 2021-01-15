package  com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.po.PoXT_XZQHB;
/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 行政区划的入口信息</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXT_XZQHB extends DefaultVO
{
  /**
  *
  * @param poXt_Xtcsb
  */
 public VoXT_XZQHB(PoXT_XZQHB poXt_xzqhb) {
   super(poXt_xzqhb);
 }

  private String qhdm;
  private String qhmc;
  private String qhzwpy;
  private String qhwbpy;
  private String qybz;
  private String bz;
  private String bdlx;
  private String bdsj;

  public void setQhdm(String qhdm) {
    this.qhdm = qhdm;
  }

  public String getQhdm() {
    return qhdm;
  }

  public void setQhmc(String qhmc) {
    this.qhmc = qhmc;
  }

  public String getQhmc() {
    return qhmc;
  }

  public void setQhzwpy(String qhzwpy) {
    this.qhzwpy = qhzwpy;
  }

  public String getQhzwpy() {
    return qhzwpy;
  }

  public void setQhwbpy(String qhwbpy) {
    this.qhwbpy = qhwbpy;
  }

  public String getQhwbpy() {
    return qhwbpy;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public String getQybz() {
    return qybz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getBz() {
    return bz;
  }

  public void setBdlx(String bdlx) {
    this.bdlx = bdlx;
  }

  public String getBdlx() {
    return bdlx;
  }

  public void setBdsj(String bdsj) {
    this.bdsj = bdsj;
  }

  public String getBdsj() {
    return bdsj;
  }

}
