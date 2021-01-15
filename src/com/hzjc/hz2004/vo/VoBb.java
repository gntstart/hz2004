package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更更正返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBb
    extends DefaultVO {
  private String jttdbz; //集体土地标志
  private String djzt; //冻结状态
public String getJttdbz() {
	return jttdbz;
}
public void setJttdbz(String jttdbz) {
	this.jttdbz = jttdbz;
}
public String getDjzt() {
	return djzt;
}
public void setDjzt(String djzt) {
	this.djzt = djzt;
}
  
}
