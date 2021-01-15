package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 申报基本信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSbjbxx
    extends DefaultVO {

  //申报时间
  private String sbsj;

  //申报人员姓名
  private String sbryxm;

  //申报人公民身份号码
  private String sbrgmsfhm;

  private String sbrjtgx;

  //户证业务ID，特别处理
  private String hzywid;

  public String getHzywid() {
    return hzywid;
  }

  public void setHzywid(String hzywid) {
    this.hzywid = hzywid;
  }


  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

        public String getSbrjtgx() {
                return sbrjtgx;
        }
        public void setSbrjtgx(String sbrjtgx) {
                this.sbrjtgx = sbrjtgx;
        }


}
