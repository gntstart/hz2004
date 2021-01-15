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
*接收异常数据项
*/
@Entity
@Table(name="JSV3_YCSJX" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_YCSJX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接收数据异常序列号
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	ycxlh;
	private Long	pcid;
	private String	pch;
	private String	ssxq;
	private String	gmsfhm;
	private String	xm;
	private String	bwbh;
	private String	yclx;
	private String	jydw;
	private String	jyrq;
	private String	xjgajgmc;
	private String	xjgajgdm;
	private String	pcsmc;
	private String	pcsdm;
	private String	xxtqsj;
	private Long	ryid;
	private String	cwms;
	private String	clqk;
	private String	clsj;
	private String	bwlx;

	public PoJSV3_YCSJX(){}

	public Long getYcxlh(){
		return this.ycxlh;
	}

	public void setYcxlh(Long ycxlh){
		this.ycxlh=ycxlh;
	}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getBwbh(){
		return this.bwbh;
	}

	public void setBwbh(String bwbh){
		this.bwbh=bwbh;
	}

	public String getYclx(){
		return this.yclx;
	}

	public void setYclx(String yclx){
		this.yclx=yclx;
	}

	public String getJydw(){
		return this.jydw;
	}

	public void setJydw(String jydw){
		this.jydw=jydw;
	}

	public String getJyrq(){
		return this.jyrq;
	}

	public void setJyrq(String jyrq){
		this.jyrq=jyrq;
	}

	public String getXjgajgmc(){
		return this.xjgajgmc;
	}

	public void setXjgajgmc(String xjgajgmc){
		this.xjgajgmc=xjgajgmc;
	}

	public String getXjgajgdm(){
		return this.xjgajgdm;
	}

	public void setXjgajgdm(String xjgajgdm){
		this.xjgajgdm=xjgajgdm;
	}

	public String getPcsmc(){
		return this.pcsmc;
	}

	public void setPcsmc(String pcsmc){
		this.pcsmc=pcsmc;
	}

	public String getPcsdm(){
		return this.pcsdm;
	}

	public void setPcsdm(String pcsdm){
		this.pcsdm=pcsdm;
	}

	public String getXxtqsj(){
		return this.xxtqsj;
	}

	public void setXxtqsj(String xxtqsj){
		this.xxtqsj=xxtqsj;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getCwms(){
		return this.cwms;
	}

	public void setCwms(String cwms){
		this.cwms=cwms;
	}

	public String getClqk(){
		return this.clqk;
	}

	public void setClqk(String clqk){
		this.clqk=clqk;
	}

	public String getClsj(){
		return this.clsj;
	}

	public void setClsj(String clsj){
		this.clsj=clsj;
	}

	public String getBwlx(){
		return this.bwlx;
	}

	public void setBwlx(String bwlx){
		this.bwlx=bwlx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}