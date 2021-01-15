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
*城乡属性变更表
*/
@Entity
@Table(name="HJXX_CXSXBGB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXX_CXSXBGB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private Long	rynbid;
	private Long	hjywid;
	private String	ywlb;
	private String	cjsj;
	private String	bgqcxsx;
	private String	bghcxsx;
	private String	bgyy;
	private String	sldw;
	private String	bz;
	private String	bgqdw;
	private String	rkbj;
	private String	ssxq;
	private String	jwhdm;
	private Long	rysl;
	private String	nyzyrklhczyydm;

	public PoHJXX_CXSXBGB(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getYwlb(){
		return this.ywlb;
	}

	public void setYwlb(String ywlb){
		this.ywlb=ywlb;
	}

	public String getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(String cjsj){
		this.cjsj=cjsj;
	}

	public String getBgqcxsx(){
		return this.bgqcxsx;
	}

	public void setBgqcxsx(String bgqcxsx){
		this.bgqcxsx=bgqcxsx;
	}

	public String getBghcxsx(){
		return this.bghcxsx;
	}

	public void setBghcxsx(String bghcxsx){
		this.bghcxsx=bghcxsx;
	}

	public String getBgyy(){
		return this.bgyy;
	}

	public void setBgyy(String bgyy){
		this.bgyy=bgyy;
	}

	public String getSldw(){
		return this.sldw;
	}

	public void setSldw(String sldw){
		this.sldw=sldw;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getBgqdw(){
		return this.bgqdw;
	}

	public void setBgqdw(String bgqdw){
		this.bgqdw=bgqdw;
	}

	public String getRkbj(){
		return this.rkbj;
	}

	public void setRkbj(String rkbj){
		this.rkbj=rkbj;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJwhdm(){
		return this.jwhdm;
	}

	public void setJwhdm(String jwhdm){
		this.jwhdm=jwhdm;
	}

	public Long getRysl(){
		return this.rysl;
	}

	public void setRysl(Long rysl){
		this.rysl=rysl;
	}

	public String getNyzyrklhczyydm(){
		return this.nyzyrklhczyydm;
	}

	public void setNyzyrklhczyydm(String nyzyrklhczyydm){
		this.nyzyrklhczyydm=nyzyrklhczyydm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}