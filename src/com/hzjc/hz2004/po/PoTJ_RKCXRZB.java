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

@Entity
@Table(name="TJ_RKCXRZB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoTJ_RKCXRZB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*查询ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	cxid;
	private Long	yhid;
	private String	dlm;
	private String	yhip;
	private String	yhxm;
	private String	yhgmsfhm;
	private String	yhssdwjgdm;
	private String	cxjk;
	private String	cxxm;
	private String	cxgmsfhm;
	private String	cxsql;
	private String	cxsj;

	public PoTJ_RKCXRZB(){}

	public Long getCxid(){
		return this.cxid;
	}

	public void setCxid(Long cxid){
		this.cxid=cxid;
	}

	public Long getYhid(){
		return this.yhid;
	}

	public void setYhid(Long yhid){
		this.yhid=yhid;
	}

	public String getDlm(){
		return this.dlm;
	}

	public void setDlm(String dlm){
		this.dlm=dlm;
	}

	public String getYhip(){
		return this.yhip;
	}

	public void setYhip(String yhip){
		this.yhip=yhip;
	}

	public String getYhxm(){
		return this.yhxm;
	}

	public void setYhxm(String yhxm){
		this.yhxm=yhxm;
	}

	public String getYhgmsfhm(){
		return this.yhgmsfhm;
	}

	public void setYhgmsfhm(String yhgmsfhm){
		this.yhgmsfhm=yhgmsfhm;
	}

	public String getYhssdwjgdm(){
		return this.yhssdwjgdm;
	}

	public void setYhssdwjgdm(String yhssdwjgdm){
		this.yhssdwjgdm=yhssdwjgdm;
	}

	public String getCxjk(){
		return this.cxjk;
	}

	public void setCxjk(String cxjk){
		this.cxjk=cxjk;
	}

	public String getCxxm(){
		return this.cxxm;
	}

	public void setCxxm(String cxxm){
		this.cxxm=cxxm;
	}

	public String getCxgmsfhm(){
		return this.cxgmsfhm;
	}

	public void setCxgmsfhm(String cxgmsfhm){
		this.cxgmsfhm=cxgmsfhm;
	}

	public String getCxsql(){
		return this.cxsql;
	}

	public void setCxsql(String cxsql){
		this.cxsql=cxsql;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}