package com.hzjc.hz2004.base.bean;

import java.io.Serializable;

/**
*系统代码类别�?
*/
public class SysCodetype implements Serializable{
	/*
	*单位层次�?
	*/
	/*
	*代码类别ID
	*/
	private String	idCodetype;
	/*
	*代码类别
	*/
	private String	codetype;
	/*
	*代码类别名称
	*/
	private String	codetypename;
	/*
	*父类型标�?
	*/
	private String	PCodetype;
	/*
	*是否可以选择
	*/
	private String	isseled;
	/*
	*同等级的排序
	*/
	private Long	sort;
	/*
	*删除标记
	*/
	private String	deleteflag;
	/*
	*是否允许新增
	*/
	private String	isinsert;
	/*
	*是否标准代码
	*/
	private String	isstandard;
	/*
	*字典类型
	*/
	private String	visiontype;
	/*
	*代码选择树中用到的字典类�?
	*/
	private String	vbVisiontype;
	/*
	*拼音�?
	*/
	private String	pyt;
	/*
	*项目编码，为空或�?表示�?��项目，否则表示该字典只�?用于指定项目
	*/
	private String	xmbm;
	/*
	*地市~~DQKHBM
	*/
	private String	ds="gonganbu";
	/*
	*备注
	*/
	private String	bz;
	/*
	*父亲ID
	*/
	private String	pid;
	/*
	*命名空间：空表示系统字典SYS，多个相同字典可用名字空间区�?
	*/
	private String	spacename;

	public SysCodetype(){}


	public SysCodetype(String idCodetype,
			String codetype,
			String codetypename,
			String PCodetype,
			String isseled,
			Long sort,
			String deleteflag,
			String isinsert,
			String isstandard,
			String visiontype,
			String vbVisiontype,
			String pyt,
			String xmbm,
			String ds,
			String bz,
			String pid,
			String spacename)
	{
		this.idCodetype=idCodetype;
		this.codetype=codetype;
		this.codetypename=codetypename;
		this.PCodetype=PCodetype;
		this.isseled=isseled;
		this.sort=sort;
		this.deleteflag=deleteflag;
		this.isinsert=isinsert;
		this.isstandard=isstandard;
		this.visiontype=visiontype;
		this.vbVisiontype=vbVisiontype;
		this.pyt=pyt;
		this.xmbm=xmbm;
		this.ds=ds;
		this.bz=bz;
		this.pid=pid;
		this.spacename=spacename;
	}

	public String getIdCodetype(){
		return this.idCodetype;
	}

	public void setIdCodetype(String idCodetype){
		this.idCodetype=idCodetype;
	}

	public String getCodetype(){
		return this.codetype;
	}

	public void setCodetype(String codetype){
		this.codetype=codetype;
	}

	public String getCodetypename(){
		return this.codetypename;
	}

	public void setCodetypename(String codetypename){
		this.codetypename=codetypename;
	}

	public String getPCodetype(){
		return this.PCodetype;
	}

	public void setPCodetype(String PCodetype){
		this.PCodetype=PCodetype;
	}

	public String getIsseled(){
		return this.isseled;
	}

	public void setIsseled(String isseled){
		this.isseled=isseled;
	}

	public Long getSort(){
		return this.sort;
	}

	public void setSort(Long sort){
		this.sort=sort;
	}

	public String getDeleteflag(){
		return this.deleteflag;
	}

	public void setDeleteflag(String deleteflag){
		this.deleteflag=deleteflag;
	}

	public String getIsinsert(){
		return this.isinsert;
	}

	public void setIsinsert(String isinsert){
		this.isinsert=isinsert;
	}

	public String getIsstandard(){
		return this.isstandard;
	}

	public void setIsstandard(String isstandard){
		this.isstandard=isstandard;
	}

	public String getVisiontype(){
		return this.visiontype;
	}

	public void setVisiontype(String visiontype){
		this.visiontype=visiontype;
	}

	public String getVbVisiontype(){
		return this.vbVisiontype;
	}

	public void setVbVisiontype(String vbVisiontype){
		this.vbVisiontype=vbVisiontype;
	}

	public String getPyt(){
		return this.pyt;
	}

	public void setPyt(String pyt){
		this.pyt=pyt;
	}

	public String getXmbm(){
		return this.xmbm;
	}

	public void setXmbm(String xmbm){
		this.xmbm=xmbm;
	}

	public String getDs(){
		return this.ds;
	}

	public void setDs(String ds){
		this.ds=ds;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getPid(){
		return this.pid;
	}

	public void setPid(String pid){
		this.pid=pid;
	}

	public String getSpacename(){
		return this.spacename;
	}

	public void setSpacename(String spacename){
		this.spacename=spacename;
	}

	public String getLayerOooo(){
		return "";
	}

	public void setLayerOooo(String layerOoooo){
	}
}