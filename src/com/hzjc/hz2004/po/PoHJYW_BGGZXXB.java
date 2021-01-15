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
*变更更正信息表
*/
@Entity
@Table(name="HJYW_BGGZXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_BGGZXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*变更更正ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bggzid;		//变更更正ID
	private Long	rynbid;		//人员内部ID
	private String	bggzxm;		//变更更正项目
	private String	bggzlb;		//变更更正类别
	private String	bggzrq;		//变更更正日期
	private String	bggzqnr;	//变更更正前内容
	private String	bggzhnr;	//变更更正后内容
	private String	dybz;		//打印标志
	private Long	hjywid;		//户籍业务ID
	private Long	tbbz;		//同步标志
	private String	bwbh;		//包文编号
	private String	sbsj;		//申报时间
	private String	sbryxm;		//申报人员姓名
	private String	sbrgmsfhm;	//申报人公民身份号码
	private String	slsj;		//受理时间
	private String	sldw;		//受理单位
	private Long	slrid;		//受理人ID
	private Long	ryid;		//人员ID
	private String	bgqxm;		//变更前姓名
	private String	bgqgmsfhm;	//变更前公民身份号码
	private String	xm;			//姓名
	private String	gmsfhm;		//公民身份号码
	private String	mz;			//民族
	private String	xb;			//性别
	private String	csrq;		//出生日期
	private String	cssj;		//出生时间
	private String	csdssxq;	//出生地省市县（区）
	private String	ssxq;		//省市县（区）
	private String	jlx;		//街路巷
	private String	mlph;		//门（楼）牌号
	private String	mlxz;		//门（楼）详址
	private String	pcs;		//派出所
	private String	zrq;		//责任区
	private String	pxh;		//排序号
	private String	xzjd;		//乡镇（街道）
	private String	jcwh;		//居（村）委会
	private Long	hhnbid;		//户号内部ID
	private Long	mlpnbid;	//门（楼）牌内部ID
	private String	bggzqdm;	//变更更正前代码
	private String	bggzhdm;	//变更更正后代码
	private String	ryzt;		//人员状态
	private String	xxlb;		//信息类别
	private String	xxlbmc;		//信息类别名称
	private String	bggzbsf;	//变更更正数据项标识符
	private String	sbrjtgx;	//申报人与变动人家庭关系
	private String	bz;			//备注

	public PoHJYW_BGGZXXB(){}

	public Long getBggzid(){
		return this.bggzid;
	}

	public void setBggzid(Long bggzid){
		this.bggzid=bggzid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public String getBggzxm(){
		return this.bggzxm;
	}

	public void setBggzxm(String bggzxm){
		this.bggzxm=bggzxm;
	}

	public String getBggzlb(){
		return this.bggzlb;
	}

	public void setBggzlb(String bggzlb){
		this.bggzlb=bggzlb;
	}

	public String getBggzrq(){
		return this.bggzrq;
	}

	public void setBggzrq(String bggzrq){
		this.bggzrq=bggzrq;
	}

	public String getBggzqnr(){
		return this.bggzqnr;
	}

	public void setBggzqnr(String bggzqnr){
		this.bggzqnr=bggzqnr;
	}

	public String getBggzhnr(){
		return this.bggzhnr;
	}

	public void setBggzhnr(String bggzhnr){
		this.bggzhnr=bggzhnr;
	}

	public String getDybz(){
		return this.dybz;
	}

	public void setDybz(String dybz){
		this.dybz=dybz;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public Long getTbbz(){
		return this.tbbz;
	}

	public void setTbbz(Long tbbz){
		this.tbbz=tbbz;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(String sbsj){
		this.sbsj=sbsj;
	}

	public String getSbryxm(){
		return this.sbryxm;
	}

	public void setSbryxm(String sbryxm){
		this.sbryxm=sbryxm;
	}

	public String getSbrgmsfhm(){
		return this.sbrgmsfhm;
	}

	public void setSbrgmsfhm(String sbrgmsfhm){
		this.sbrgmsfhm=sbrgmsfhm;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public Long getSlrid(){
		return this.slrid;
	}

	public void setSlrid(Long slrid){
		this.slrid=slrid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getBgqxm(){
		return this.bgqxm;
	}

	public void setBgqxm(String bgqxm){
		this.bgqxm=bgqxm;
	}

	public String getBgqgmsfhm(){
		return this.bgqgmsfhm;
	}

	public void setBgqgmsfhm(String bgqgmsfhm){
		this.bgqgmsfhm=bgqgmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getMlph(){
		return this.mlph;
	}

	public void setMlph(String mlph){
		this.mlph=mlph;
	}

	public String getMlxz(){
		return this.mlxz;
	}

	public void setMlxz(String mlxz){
		this.mlxz=mlxz;
	}

	public String getPcs(){
		return this.pcs;
	}

	public void setPcs(String pcs){
		this.pcs=pcs;
	}

	public String getZrq(){
		return this.zrq;
	}

	public void setZrq(String zrq){
		this.zrq=zrq;
	}

	public String getPxh(){
		return this.pxh;
	}

	public void setPxh(String pxh){
		this.pxh=pxh;
	}

	public String getXzjd(){
		return this.xzjd;
	}

	public void setXzjd(String xzjd){
		this.xzjd=xzjd;
	}

	public String getJcwh(){
		return this.jcwh;
	}

	public void setJcwh(String jcwh){
		this.jcwh=jcwh;
	}

	public Long getHhnbid(){
		return this.hhnbid;
	}

	public void setHhnbid(Long hhnbid){
		this.hhnbid=hhnbid;
	}

	public Long getMlpnbid(){
		return this.mlpnbid;
	}

	public void setMlpnbid(Long mlpnbid){
		this.mlpnbid=mlpnbid;
	}

	public String getBggzqdm(){
		return this.bggzqdm;
	}

	public void setBggzqdm(String bggzqdm){
		this.bggzqdm=bggzqdm;
	}

	public String getBggzhdm(){
		return this.bggzhdm;
	}

	public void setBggzhdm(String bggzhdm){
		this.bggzhdm=bggzhdm;
	}

	public String getRyzt(){
		return this.ryzt;
	}

	public void setRyzt(String ryzt){
		this.ryzt=ryzt;
	}

	public String getXxlb(){
		return this.xxlb;
	}

	public void setXxlb(String xxlb){
		this.xxlb=xxlb;
	}

	public String getXxlbmc(){
		return this.xxlbmc;
	}

	public void setXxlbmc(String xxlbmc){
		this.xxlbmc=xxlbmc;
	}

	public String getBggzbsf(){
		return this.bggzbsf;
	}

	public void setBggzbsf(String bggzbsf){
		this.bggzbsf=bggzbsf;
	}

	public String getSbrjtgx(){
		return this.sbrjtgx;
	}

	public void setSbrjtgx(String sbrjtgx){
		this.sbrjtgx=sbrjtgx;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}