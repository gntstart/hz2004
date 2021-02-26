package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*消息信息表
*/
@Entity
@Table(name="SFXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoSFXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	sfxxbid;//解锁锁定日志表Id
	private String dylb;//打印类别  首页03 准迁证 07 迁移证 06
	private String dwdm;//单位代码
	private String dysj;//打印时间
	private Long    czyid;//操作人
	private Integer je;//金额
	private String sffs;//收费方式
	private String gmsfhm;//公民身份号码
	private String xm;//姓名
	private String bzxjfyy;//不在线缴费原因
	private String jfflag;//是否缴费标志  0 未缴费  1 已缴费
	private String qhdm;//区划代码
	private String jksj;//缴款时间
	private Long    jkrid;//缴款人id
	private String shzt;//审核状态
	private String shsj;//审核时间
	private Long shrid;//审核人ID
	private int xgcs;//add By zjm 20210201 修改次数
	public PoSFXXB(){}
	public Long getCzyid() {
		return czyid;
	}
	public void setCzyid(Long czyid) {
		this.czyid = czyid;
	}
	public Long getSfxxbid() {
		return sfxxbid;
	}
	public void setSfxxbid(Long sfxxbid) {
		this.sfxxbid = sfxxbid;
	}
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
	public String getQhdm() {
		return qhdm;
	}
	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}
	public String getJfflag() {
		return jfflag;
	}
	public void setJfflag(String jfflag) {
		this.jfflag = jfflag;
	}
	
	public String getJksj() {
		return jksj;
	}
	public void setJksj(String jksj) {
		this.jksj = jksj;
	}
	public Long getJkrid() {
		return jkrid;
	}
	public void setJkrid(Long jkrid) {
		this.jkrid = jkrid;
	}
	
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	
	public Long getShrid() {
		return shrid;
	}
	public void setShrid(Long shrid) {
		this.shrid = shrid;
	}
	
	public int getXgcs() {
		return xgcs;
	}
	public void setXgcs(int xgcs) {
		this.xgcs = xgcs;
	}
	public static long getSerialversionuid() {			return serialVersionUID;	}}