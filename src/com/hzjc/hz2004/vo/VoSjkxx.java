package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 非现金收款统计信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSjkxx
    extends DefaultVO {

  private String dwdm; //单位代码 改为受理机构
  private String yingjkje;//应缴款总金额
  private String yijkje;//已缴款总金额
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getYingjkje() {
		return yingjkje;
	}
	public void setYingjkje(String yingjkje) {
		this.yingjkje = yingjkje;
	}
	public String getYijkje() {
		return yijkje;
	}
	public void setYijkje(String yijkje) {
		this.yijkje = yijkje;
	}

	
}