package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 重号信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSfxxb
    extends DefaultVO {

	private String dylb;//打印类别
	private String dwdm;//单位代码
	private String dysj;//打印时间
	private Long    czyid;//操作人
	private Integer je;//金额
	private String sffs;//收费方式
	private String gmsfhm;//公民身份号码
	private String xm;//姓名
	private String bzxjfyy;//不在线缴费原因
	public String getDylb() {
		return dylb;
	}
	public void setDylb(String dylb) {
		this.dylb = dylb;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getDysj() {
		return dysj;
	}
	public void setDysj(String dysj) {
		this.dysj = dysj;
	}
	public Long getCzyid() {
		return czyid;
	}
	public void setCzyid(Long czyid) {
		this.czyid = czyid;
	}
	public Integer getJe() {
		return je;
	}
	public void setJe(Integer je) {
		this.je = je;
	}
	public String getSffs() {
		return sffs;
	}
	public void setSffs(String sffs) {
		this.sffs = sffs;
	}
	public String getGmsfhm() {
		return gmsfhm;
	}
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBzxjfyy() {
		return bzxjfyy;
	}
	public void setBzxjfyy(String bzxjfyy) {
		this.bzxjfyy = bzxjfyy;
	}
}