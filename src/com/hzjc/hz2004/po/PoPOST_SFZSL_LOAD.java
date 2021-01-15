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
*居民身份证异地受理信息
*/
@Entity
@Table(name="POST_SFZSL_LOAD" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_SFZSL_LOAD implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*居民身份证受理号
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	jmsfzslh;
	private String	gmsfhm;
	private String	xm;
	private String	xbdm;
	private String	mzdm;
	private String	csrq;
	private String	qfjg_gajgmc;
	private String	yxqqsrq;
	private String	yxqjzrq;
	private String	sdxp;
	private String	dzmc;
	private String	hjdz_ssxqdm;
	private String	hjdz_qhnxxdz;
	private String	zjzdz_ssxqdm;
	private String	zjzdz_qhnxxdz;
	private String	jmsfzslyydm;
	private String	jmsfzzzlxdm;
	private String	jmsfzlzfsdm;
	private String	lxdh;
	private String	sldw_gajgjgdm;
	private String	sldw_gajgmc;
	private String	slr_xm;
	private String	slsj;
	private String	zwcjjgdm;
	private String	zwy_zwzcjgdm;
	private String	zwy_zwdm;
	private String	zwy_zwtxsj;
	private String	zwy_zwtxzlz;
	private String	zwy_zwtzsj;
	private String	zwe_zwzcjgdm;
	private String	zwe_zwdm;
	private String	zwe_zwtxsj;
	private String	zwe_zwtxzlz;
	private String	zwe_zwtzsj;
	private String	szyczkdm;
	private String	zwqdxtzch;
	private String	zwcjqbsh;
	private String	load_date;

	public PoPOST_SFZSL_LOAD(){}

	public String getJmsfzslh(){
		return this.jmsfzslh;
	}

	public void setJmsfzslh(String jmsfzslh){
		this.jmsfzslh=jmsfzslh;
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

	public String getXbdm(){
		return this.xbdm;
	}

	public void setXbdm(String xbdm){
		this.xbdm=xbdm;
	}

	public String getMzdm(){
		return this.mzdm;
	}

	public void setMzdm(String mzdm){
		this.mzdm=mzdm;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getQfjg_gajgmc(){
		return this.qfjg_gajgmc;
	}

	public void setQfjg_gajgmc(String qfjg_gajgmc){
		this.qfjg_gajgmc=qfjg_gajgmc;
	}

	public String getYxqqsrq(){
		return this.yxqqsrq;
	}

	public void setYxqqsrq(String yxqqsrq){
		this.yxqqsrq=yxqqsrq;
	}

	public String getYxqjzrq(){
		return this.yxqjzrq;
	}

	public void setYxqjzrq(String yxqjzrq){
		this.yxqjzrq=yxqjzrq;
	}

	public String getSdxp(){
		return this.sdxp;
	}

	public void setSdxp(String sdxp){
		this.sdxp=sdxp;
	}

	public String getDzmc(){
		return this.dzmc;
	}

	public void setDzmc(String dzmc){
		this.dzmc=dzmc;
	}

	public String getHjdz_ssxqdm(){
		return this.hjdz_ssxqdm;
	}

	public void setHjdz_ssxqdm(String hjdz_ssxqdm){
		this.hjdz_ssxqdm=hjdz_ssxqdm;
	}

	public String getHjdz_qhnxxdz(){
		return this.hjdz_qhnxxdz;
	}

	public void setHjdz_qhnxxdz(String hjdz_qhnxxdz){
		this.hjdz_qhnxxdz=hjdz_qhnxxdz;
	}

	public String getZjzdz_ssxqdm(){
		return this.zjzdz_ssxqdm;
	}

	public void setZjzdz_ssxqdm(String zjzdz_ssxqdm){
		this.zjzdz_ssxqdm=zjzdz_ssxqdm;
	}

	public String getZjzdz_qhnxxdz(){
		return this.zjzdz_qhnxxdz;
	}

	public void setZjzdz_qhnxxdz(String zjzdz_qhnxxdz){
		this.zjzdz_qhnxxdz=zjzdz_qhnxxdz;
	}

	public String getJmsfzslyydm(){
		return this.jmsfzslyydm;
	}

	public void setJmsfzslyydm(String jmsfzslyydm){
		this.jmsfzslyydm=jmsfzslyydm;
	}

	public String getJmsfzzzlxdm(){
		return this.jmsfzzzlxdm;
	}

	public void setJmsfzzzlxdm(String jmsfzzzlxdm){
		this.jmsfzzzlxdm=jmsfzzzlxdm;
	}

	public String getJmsfzlzfsdm(){
		return this.jmsfzlzfsdm;
	}

	public void setJmsfzlzfsdm(String jmsfzlzfsdm){
		this.jmsfzlzfsdm=jmsfzlzfsdm;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getSldw_gajgjgdm(){
		return this.sldw_gajgjgdm;
	}

	public void setSldw_gajgjgdm(String sldw_gajgjgdm){
		this.sldw_gajgjgdm=sldw_gajgjgdm;
	}

	public String getSldw_gajgmc(){
		return this.sldw_gajgmc;
	}

	public void setSldw_gajgmc(String sldw_gajgmc){
		this.sldw_gajgmc=sldw_gajgmc;
	}

	public String getSlr_xm(){
		return this.slr_xm;
	}

	public void setSlr_xm(String slr_xm){
		this.slr_xm=slr_xm;
	}

	public String getSlsj(){
		return this.slsj;
	}

	public void setSlsj(String slsj){
		this.slsj=slsj;
	}

	public String getZwcjjgdm(){
		return this.zwcjjgdm;
	}

	public void setZwcjjgdm(String zwcjjgdm){
		this.zwcjjgdm=zwcjjgdm;
	}

	public String getZwy_zwzcjgdm(){
		return this.zwy_zwzcjgdm;
	}

	public void setZwy_zwzcjgdm(String zwy_zwzcjgdm){
		this.zwy_zwzcjgdm=zwy_zwzcjgdm;
	}

	public String getZwy_zwdm(){
		return this.zwy_zwdm;
	}

	public void setZwy_zwdm(String zwy_zwdm){
		this.zwy_zwdm=zwy_zwdm;
	}

	public String getZwy_zwtxsj(){
		return this.zwy_zwtxsj;
	}

	public void setZwy_zwtxsj(String zwy_zwtxsj){
		this.zwy_zwtxsj=zwy_zwtxsj;
	}

	public String getZwy_zwtxzlz(){
		return this.zwy_zwtxzlz;
	}

	public void setZwy_zwtxzlz(String zwy_zwtxzlz){
		this.zwy_zwtxzlz=zwy_zwtxzlz;
	}

	public String getZwy_zwtzsj(){
		return this.zwy_zwtzsj;
	}

	public void setZwy_zwtzsj(String zwy_zwtzsj){
		this.zwy_zwtzsj=zwy_zwtzsj;
	}

	public String getZwe_zwzcjgdm(){
		return this.zwe_zwzcjgdm;
	}

	public void setZwe_zwzcjgdm(String zwe_zwzcjgdm){
		this.zwe_zwzcjgdm=zwe_zwzcjgdm;
	}

	public String getZwe_zwdm(){
		return this.zwe_zwdm;
	}

	public void setZwe_zwdm(String zwe_zwdm){
		this.zwe_zwdm=zwe_zwdm;
	}

	public String getZwe_zwtxsj(){
		return this.zwe_zwtxsj;
	}

	public void setZwe_zwtxsj(String zwe_zwtxsj){
		this.zwe_zwtxsj=zwe_zwtxsj;
	}

	public String getZwe_zwtxzlz(){
		return this.zwe_zwtxzlz;
	}

	public void setZwe_zwtxzlz(String zwe_zwtxzlz){
		this.zwe_zwtxzlz=zwe_zwtxzlz;
	}

	public String getZwe_zwtzsj(){
		return this.zwe_zwtzsj;
	}

	public void setZwe_zwtzsj(String zwe_zwtzsj){
		this.zwe_zwtzsj=zwe_zwtzsj;
	}

	public String getSzyczkdm(){
		return this.szyczkdm;
	}

	public void setSzyczkdm(String szyczkdm){
		this.szyczkdm=szyczkdm;
	}

	public String getZwqdxtzch(){
		return this.zwqdxtzch;
	}

	public void setZwqdxtzch(String zwqdxtzch){
		this.zwqdxtzch=zwqdxtzch;
	}

	public String getZwcjqbsh(){
		return this.zwcjqbsh;
	}

	public void setZwcjqbsh(String zwcjqbsh){
		this.zwcjqbsh=zwcjqbsh;
	}

	public String getLoad_date(){
		return this.load_date;
	}

	public void setLoad_date(String load_date){
		this.load_date=load_date;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}