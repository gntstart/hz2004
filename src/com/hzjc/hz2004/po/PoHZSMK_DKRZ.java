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
*杭州市民卡读卡日志表
*/
@Entity
@Table(name="HZSMK_DKRZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZSMK_DKRZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*读卡日志ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	dkrzid;
	private String	kdsbm;
	private String	kdlb;
	private String	gfbb;
	private String	cshjgdm;
	private String	fkrq;
	private String	kyxq;
	private String	kh;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	csdssxq;
	private String	szcsdm;
	private String	hb;
	private String	czhkszd;
	private String	yzbm;
	private String	txdz;
	private String	lxdh;
	private String	whcd;
	private String	hyzk;
	private Long	czyid;
	private String	dkjqipdz;
	private String	dksj;

	public PoHZSMK_DKRZ(){}

	public Long getDkrzid(){
		return this.dkrzid;
	}

	public void setDkrzid(Long dkrzid){
		this.dkrzid=dkrzid;
	}

	public String getKdsbm(){
		return this.kdsbm;
	}

	public void setKdsbm(String kdsbm){
		this.kdsbm=kdsbm;
	}

	public String getKdlb(){
		return this.kdlb;
	}

	public void setKdlb(String kdlb){
		this.kdlb=kdlb;
	}

	public String getGfbb(){
		return this.gfbb;
	}

	public void setGfbb(String gfbb){
		this.gfbb=gfbb;
	}

	public String getCshjgdm(){
		return this.cshjgdm;
	}

	public void setCshjgdm(String cshjgdm){
		this.cshjgdm=cshjgdm;
	}

	public String getFkrq(){
		return this.fkrq;
	}

	public void setFkrq(String fkrq){
		this.fkrq=fkrq;
	}

	public String getKyxq(){
		return this.kyxq;
	}

	public void setKyxq(String kyxq){
		this.kyxq=kyxq;
	}

	public String getKh(){
		return this.kh;
	}

	public void setKh(String kh){
		this.kh=kh;
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

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public String getSzcsdm(){
		return this.szcsdm;
	}

	public void setSzcsdm(String szcsdm){
		this.szcsdm=szcsdm;
	}

	public String getHb(){
		return this.hb;
	}

	public void setHb(String hb){
		this.hb=hb;
	}

	public String getCzhkszd(){
		return this.czhkszd;
	}

	public void setCzhkszd(String czhkszd){
		this.czhkszd=czhkszd;
	}

	public String getYzbm(){
		return this.yzbm;
	}

	public void setYzbm(String yzbm){
		this.yzbm=yzbm;
	}

	public String getTxdz(){
		return this.txdz;
	}

	public void setTxdz(String txdz){
		this.txdz=txdz;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getWhcd(){
		return this.whcd;
	}

	public void setWhcd(String whcd){
		this.whcd=whcd;
	}

	public String getHyzk(){
		return this.hyzk;
	}

	public void setHyzk(String hyzk){
		this.hyzk=hyzk;
	}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getDkjqipdz(){
		return this.dkjqipdz;
	}

	public void setDkjqipdz(String dkjqipdz){
		this.dkjqipdz=dkjqipdz;
	}

	public String getDksj(){
		return this.dksj;
	}

	public void setDksj(String dksj){
		this.dksj=dksj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}