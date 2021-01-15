package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*户关联关系表
*/
@Entity
@Table(name="XT_JWZRQXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_JWZRQXX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*代码
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	dm;
	private String	mc;
	private String	zwpy;
	private String	wbpy;
	private String	dwdm;
	private String	dwdc;
	private String	bz;
	private String	qybz;
	private String	qybzdm;
	private String	bdlx;
	private String	bdsj;

	public PoV_JWZRQXX(){}

	public String getQybzdm() {
		return qybzdm;
	}

	public void setQybzdm(String qybzdm) {
		this.qybzdm = qybzdm;
	}

	public String getDm(){
		return this.dm;
	}

	public void setDm(String dm){
		this.dm=dm;
	}

	public String getMc(){
		return this.mc;
	}

	public void setMc(String mc){
		this.mc=mc;
	}

	public String getZwpy(){
		return this.zwpy;
	}

	public void setZwpy(String zwpy){
		this.zwpy=zwpy;
	}

	public String getWbpy(){
		return this.wbpy;
	}

	public void setWbpy(String wbpy){
		this.wbpy=wbpy;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getDwdc() {
		return dwdc;
	}

	public void setDwdc(String dwdc) {
		this.dwdc = dwdc;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public String getQybz(){
		return this.qybz;
	}

	public void setQybz(String qybz){
		this.qybz=qybz;
	}

	public String getBdlx(){
		return this.bdlx;
	}

	public void setBdlx(String bdlx){
		this.bdlx=bdlx;
	}

	public String getBdsj(){
		return this.bdsj;
	}

	public void setBdsj(String bdsj){
		this.bdsj=bdsj;
	}

	public PoV_JWZRQXX(String dm, String mc, String zwpy, String wbpy, String dwdm, String dwdc, String bz, String qybz,
			String qybzdm, String bdlx, String bdsj) {
		super();
		this.dm = dm;
		this.mc = mc;
		this.zwpy = zwpy;
		this.wbpy = wbpy;
		this.dwdm = dwdm;
		this.dwdc = dwdc;
		this.bz = bz;
		this.qybz = qybz;
		this.qybzdm = qybzdm;
		this.bdlx = bdlx;
		this.bdsj = bdsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}