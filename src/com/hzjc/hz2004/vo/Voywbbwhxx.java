package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class Voywbbwhxx extends DefaultVO{
    private String createName;//创建人
    private String createDate;//创建时间
    private String updateName;//修改人
    private String updateDate;//修改时间
    private String bbName;//报表名称
    private String ywbblb;//业务报表类别
    private String ywbbid;//业务报表id
    private byte[]	bbmb;//模板
    private String ywbbmb;
  
	public String getYwbbmb() {
		return ywbbmb;
	}
	public void setYwbbmb(String ywbbmb) {
		this.ywbbmb = ywbbmb;
	}
	public byte[] getBbmb() {
		return bbmb;
	}
	public void setBbmb(byte[] bbmb) {
		this.bbmb = bbmb;
	}
	public String getYwbbid() {
		return ywbbid;
	}
	public void setYwbbid(String ywbbid) {
		this.ywbbid = ywbbid;
	}
	public String getYwbblb() {
		return ywbblb;
	}
	public void setYwbblb(String ywbblb) {
		this.ywbblb = ywbblb;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getBbName() {
		return bbName;
	}
	public void setBbName(String bbName) {
		this.bbName = bbName;
	}
    
}
