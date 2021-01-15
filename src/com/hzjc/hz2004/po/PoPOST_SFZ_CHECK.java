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
*身份证重号信息提交
*/
@Entity
@Table(name="POST_SFZ_CHECK" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_SFZ_CHECK implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private String	dqbm;
	private String	xm;
	private String	sfzh;
	private String	chyy;
	private String	chyy_name;
	private String	fkxx;
	private String	fkrid;
	private String	fkrdlm;
	private String	fkrxm;

	public PoPOST_SFZ_CHECK(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public String getDqbm(){
		return this.dqbm;
	}

	public void setDqbm(String dqbm){
		this.dqbm=dqbm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getSfzh(){
		return this.sfzh;
	}

	public void setSfzh(String sfzh){
		this.sfzh=sfzh;
	}

	public String getChyy(){
		return this.chyy;
	}

	public void setChyy(String chyy){
		this.chyy=chyy;
	}

	public String getChyy_name(){
		return this.chyy_name;
	}

	public void setChyy_name(String chyy_name){
		this.chyy_name=chyy_name;
	}

	public String getFkxx(){
		return this.fkxx;
	}

	public void setFkxx(String fkxx){
		this.fkxx=fkxx;
	}

	public String getFkrid(){
		return this.fkrid;
	}

	public void setFkrid(String fkrid){
		this.fkrid=fkrid;
	}

	public String getFkrdlm(){
		return this.fkrdlm;
	}

	public void setFkrdlm(String fkrdlm){
		this.fkrdlm=fkrdlm;
	}

	public String getFkrxm(){
		return this.fkrxm;
	}

	public void setFkrxm(String fkrxm){
		this.fkrxm=fkrxm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}