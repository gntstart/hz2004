package com.hzjc.hz2004.base.bean;

import java.io.Serializable;
import java.util.Date;

/**
*系统代码�?
*/
public class SysCode implements Serializable{
	/*
	*单位层次�?
	*/
	/*
	*代码ID
	*/
	private String	id;
	/*
	*代码类别
	*/
	private String	codetype;
	/*
	*代码编号
	*/
	private String	code;
	/*
	*代码名称
	*/
	private String	codename;
	/*
	*拼音�?
	*/
	private String	pyt;
	/*
	*同等级的排序
	*/
	private Long	sort;
	/*
	*字典类型
	*/
	private String	visiontype;
	/*
	*删除标志
	*/
	private String	deleteflag;
	/*
	*是否标准代码
	*/
	private String	isstandard;
	/*
	*创建日期
	*/
	private Date	jlcjsj;
	/*
	*父id
	*/
	private String	parentid;
	/*
	*是否可以选择
	*/
	private String	isseled;
	/*
	*父code
	*/
	private String	parentcode;
	/*
	*项目编码，为空或�?表示�?��项目，否则表示该字典只�?用于指定项目
	*/
	private String	xmbm;
	/*
	*地市~~DQKHBM
	*/
	private String	ds="gonganbu";
	/*
	*命名空间：空表示系统字典SYS，多个相同字典可用名字空间区�?
	*/
	private String	spacename;

	public SysCode(){}


	public SysCode(String id,
			String codetype,
			String code,
			String codename,
			String pyt,
			Long sort,
			String visiontype,
			String deleteflag,
			String isstandard,
			Date jlcjsj,
			String parentid,
			String isseled,
			String parentcode,
			String xmbm,
			String ds,
			String spacename)
	{
		this.id=id;
		this.codetype=codetype;
		this.code=code;
		this.codename=codename;
		this.pyt=pyt;
		this.sort=sort;
		this.visiontype=visiontype;
		this.deleteflag=deleteflag;
		this.isstandard=isstandard;
		this.jlcjsj=jlcjsj;
		this.parentid=parentid;
		this.isseled=isseled;
		this.parentcode=parentcode;
		this.xmbm=xmbm;
		this.ds=ds;
		this.spacename=spacename;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getCodetype(){
		return this.codetype;
	}

	public void setCodetype(String codetype){
		this.codetype=codetype;
	}

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCodename(){
		return this.codename;
	}

	public void setCodename(String codename){
		this.codename=codename;
	}

	public String getPyt(){
		return this.pyt;
	}

	public void setPyt(String pyt){
		this.pyt=pyt;
	}

	public Long getSort(){
		return this.sort;
	}

	public void setSort(Long sort){
		this.sort=sort;
	}

	public String getVisiontype(){
		return this.visiontype;
	}

	public void setVisiontype(String visiontype){
		this.visiontype=visiontype;
	}

	public String getDeleteflag(){
		return this.deleteflag;
	}

	public void setDeleteflag(String deleteflag){
		this.deleteflag=deleteflag;
	}

	public String getIsstandard(){
		return this.isstandard;
	}

	public void setIsstandard(String isstandard){
		this.isstandard=isstandard;
	}

	public Date getJlcjsj(){
		return this.jlcjsj;
	}

	public void setJlcjsj(Date jlcjsj){
		this.jlcjsj=jlcjsj;
	}

	public String getParentid(){
		return this.parentid;
	}

	public void setParentid(String parentid){
		this.parentid=parentid;
	}

	public String getIsseled(){
		return this.isseled;
	}

	public void setIsseled(String isseled){
		this.isseled=isseled;
	}

	public String getParentcode(){
		return this.parentcode;
	}

	public void setParentcode(String parentcode){
		this.parentcode=parentcode;
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