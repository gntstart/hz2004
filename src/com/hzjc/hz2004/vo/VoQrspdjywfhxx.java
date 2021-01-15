package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁入审批登记业务返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrspdjywfhxx
    extends DefaultVO {

  private Long splsid; //审批流水ID
  private Long spywid; //审批业务ID
  private String xm; //姓名
  private String gmsfhm; //公民身份号码
  private Long zqid; //准迁ID
  private VoQrspdjzfhxx voQrspdjzfhxx[]; //迁入审批登记子返回信息(随迁人员登记返回信息)
  private boolean spFlag;
  private String newHzywid;//add by zjm 20190809 中间表插入新的数据的id
  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public Long getSplsid() {
    return splsid;
  }

  public void setSplsid(Long splsid) {
    this.splsid = splsid;
  }

  public VoQrspdjzfhxx[] getVoQrspdjzfhxx() {
    return voQrspdjzfhxx;
  }

  public void setVoQrspdjzfhxx(VoQrspdjzfhxx[] voQrspdjzfhxx) {
    this.voQrspdjzfhxx = voQrspdjzfhxx;
  }

  public Long getZqid() {
    return zqid;
  }

  public void setZqid(Long zqid) {
    this.zqid = zqid;
  }
  
	public boolean isSpFlag() {
		return spFlag;
	}
	
	public void setSpFlag(boolean spFlag) {
		this.spFlag = spFlag;
	}

	public String getNewHzywid() {
		return newHzywid;
	}

	public void setNewHzywid(String newHzywid) {
		this.newHzywid = newHzywid;
	}

}