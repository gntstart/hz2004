package com.hzjc.hz2004.po.hzpt;

import java.util.Date;

public class ReceiveState {
	private String WX_CODE;
	private int bjzt;//标记状态
	private Date bjrq;//标记日期
	private String cktable;//备注

	public ReceiveState() {
	}

	public String getWX_CODE() {
		return WX_CODE;
	}

	public void setWX_CODE(String WX_CODE) {
		this.WX_CODE = WX_CODE;
	}

	public int getBjzt() {
		return bjzt;
	}

	public void setBjzt(int bjzt) {
		this.bjzt = bjzt;
	}

	public Date getBjrq() {
		return bjrq;
	}

	public void setBjrq(Date bjrq) {
		this.bjrq = bjrq;
	}

	public String getCktable() {
		return cktable;
	}

	public void setCktable(String cktable) {
		this.cktable = cktable;
	}
}