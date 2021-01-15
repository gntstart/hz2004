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
import java.sql.Timestamp;

/**
*用户信息表
*/
@Entity
@Table(name="XT_YHXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_YHXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*用户ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	yhid;
	private String	yhdlm;
	private String	jyh;
	private String	dwdm;
	private String	yhxm;
	private String	yhxb;
	private String	yhzw;
	private String	dlkl;
	private String	yhmj;
	private String	czmj;
	private String	yhzt;
	private String	gmsfhm;
	private String	dqbm;
	private String	khmsg;
	private Timestamp	khgxsj;
	private String	lxdh;
	private String flag;

	public PoXT_YHXXB(){}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getYhdlm(){
		return this.yhdlm;
	}

	public void setYhdlm(String yhdlm){
		this.yhdlm=yhdlm;
	}

	public String getJyh(){
		return this.jyh;
	}

	public void setJyh(String jyh){
		this.jyh=jyh;
	}

	public String getDwdm(){
		return this.dwdm;
	}

	public void setDwdm(String dwdm){
		this.dwdm=dwdm;
	}

	public String getYhxm(){
		return this.yhxm;
	}

	public void setYhxm(String yhxm){
		this.yhxm=yhxm;
	}

	public String getYhxb(){
		return this.yhxb;
	}

	public void setYhxb(String yhxb){
		this.yhxb=yhxb;
	}

	public String getYhzw(){
		return this.yhzw;
	}

	public void setYhzw(String yhzw){
		this.yhzw=yhzw;
	}

	public String getDlkl(){
		return this.dlkl;
	}

	public void setDlkl(String dlkl){
		this.dlkl=dlkl;
	}

	public String getYhmj(){
		return this.yhmj;
	}

	public void setYhmj(String yhmj){
		this.yhmj=yhmj;
	}

	public String getCzmj(){
		return this.czmj;
	}

	public void setCzmj(String czmj){
		this.czmj=czmj;
	}

	public String getYhzt(){
		return this.yhzt;
	}

	public void setYhzt(String yhzt){
		this.yhzt=yhzt;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public String getKhmsg(){
		return this.khmsg;
	}

	public void setKhmsg(String khmsg){
		this.khmsg=khmsg;
	}

	public Timestamp getKhgxsj(){
		return this.khgxsj;
	}

	public void setKhgxsj(Timestamp khgxsj){
		this.khgxsj=khgxsj;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh=lxdh;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}