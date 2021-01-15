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
*操作员信息表
*/
@Entity
@Table(name="DZZJ_CZYXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoDZZJ_CZYXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*操作员ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czyid;
	private String	czydlm;
	private String	czyxm;
	private String	czymm;
	private String	czydwdm;
	private String	czysfhm;
	private String	czysfzglh;
	private String	sbglh;
	private String	czyzw1;
	private String	czyzw2;
	private String	zcsj;
	private String	zxsj;
	private String	czyzt;
	private String	ktglhdn;

	public PoDZZJ_CZYXXB(){}

	public Long getCzyid(){
		return this.czyid;
	}

	public void setCzyid(Long czyid){
		this.czyid=czyid;
	}

	public String getCzydlm(){
		return this.czydlm;
	}

	public void setCzydlm(String czydlm){
		this.czydlm=czydlm;
	}

	public String getCzyxm(){
		return this.czyxm;
	}

	public void setCzyxm(String czyxm){
		this.czyxm=czyxm;
	}

	public String getCzymm(){
		return this.czymm;
	}

	public void setCzymm(String czymm){
		this.czymm=czymm;
	}

	public String getCzydwdm(){
		return this.czydwdm;
	}

	public void setCzydwdm(String czydwdm){
		this.czydwdm=czydwdm;
	}

	public String getCzysfhm(){
		return this.czysfhm;
	}

	public void setCzysfhm(String czysfhm){
		this.czysfhm=czysfhm;
	}

	public String getCzysfzglh(){
		return this.czysfzglh;
	}

	public void setCzysfzglh(String czysfzglh){
		this.czysfzglh=czysfzglh;
	}

	public String getSbglh(){
		return this.sbglh;
	}

	public void setSbglh(String sbglh){
		this.sbglh=sbglh;
	}

	public String getCzyzw1(){
		return this.czyzw1;
	}

	public void setCzyzw1(String czyzw1){
		this.czyzw1=czyzw1;
	}

	public String getCzyzw2(){
		return this.czyzw2;
	}

	public void setCzyzw2(String czyzw2){
		this.czyzw2=czyzw2;
	}

	public String getZcsj(){
		return this.zcsj;
	}

	public void setZcsj(String zcsj){
		this.zcsj=zcsj;
	}

	public String getZxsj(){
		return this.zxsj;
	}

	public void setZxsj(String zxsj){
		this.zxsj=zxsj;
	}

	public String getCzyzt(){
		return this.czyzt;
	}

	public void setCzyzt(String czyzt){
		this.czyzt=czyzt;
	}

	public String getKtglhdn(){
		return this.ktglhdn;
	}

	public void setKtglhdn(String ktglhdn){
		this.ktglhdn=ktglhdn;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}