package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 消息存储对象
 * @version 1.0
 */
public class VoMessagexx
    extends DefaultVO {
  private Long fsryhid;//发送人用户ID
  private String fsryhxm;//发送人用户登录名
  private String jssj;//接收时间
  private String message;//信息内容
  private Long jsryhid;//接收人Id
  private String 	jsryhxm;//接收人姓名
  private String readflag;//是否已读  1已读 0未读
  private String removeflag;//是否删除  1已删除 0未删除
public Long getFsryhid() {
	return fsryhid;
}
public void setFsryhid(Long fsryhid) {
	this.fsryhid = fsryhid;
}
public String getFsryhxm() {
	return fsryhxm;
}
public void setFsryhxm(String fsryhxm) {
	this.fsryhxm = fsryhxm;
}
public String getJssj() {
	return jssj;
}
public void setJssj(String jssj) {
	this.jssj = jssj;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Long getJsryhid() {
	return jsryhid;
}
public void setJsryhid(Long jsryhid) {
	this.jsryhid = jsryhid;
}
public String getReadflag() {
	return readflag;
}
public void setReadflag(String readflag) {
	this.readflag = readflag;
}
public String getRemoveflag() {
	return removeflag;
}
public void setRemoveflag(String removeflag) {
	this.removeflag = removeflag;
}
public String getJsryhxm() {
	return jsryhxm;
}
public void setJsryhxm(String jsryhxm) {
	this.jsryhxm = jsryhxm;
}
  
}
