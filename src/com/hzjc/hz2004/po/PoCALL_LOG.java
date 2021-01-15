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
*第三方调用日志
*/
@Entity
@Table(name="CALL_LOG" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoCALL_LOG implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private String	sid;
	private Timestamp	cjsj;
	private Long	hs;
	private String	xkz;
	private String	info;
	private String	sfcg;
	private String	returnmsg;
	private String	errmsg;
	private String	orgcode;
	private String	orgname;
	private String	accountid;
	private String	ip;
	private String	exesql;

	public PoCALL_LOG(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public String getSid(){
		return this.sid;
	}

	public void setSid(String sid){
		this.sid=sid;
	}

	public Timestamp getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(Timestamp cjsj){
		this.cjsj=cjsj;
	}

	public Long getHs(){
		return this.hs;
	}

	public void setHs(Long hs){
		this.hs=hs;
	}

	public String getXkz(){
		return this.xkz;
	}

	public void setXkz(String xkz){
		this.xkz=xkz;
	}

	public String getInfo(){
		return this.info;
	}

	public void setInfo(String info){
		this.info=info;
	}

	public String getSfcg(){
		return this.sfcg;
	}

	public void setSfcg(String sfcg){
		this.sfcg=sfcg;
	}

	public String getReturnmsg(){
		return this.returnmsg;
	}

	public void setReturnmsg(String returnmsg){
		this.returnmsg=returnmsg;
	}

	public String getErrmsg(){
		return this.errmsg;
	}

	public void setErrmsg(String errmsg){
		this.errmsg=errmsg;
	}

	public String getOrgcode(){
		return this.orgcode;
	}

	public void setOrgcode(String orgcode){
		this.orgcode=orgcode;
	}

	public String getOrgname(){
		return this.orgname;
	}

	public void setOrgname(String orgname){
		this.orgname=orgname;
	}

	public String getAccountid(){
		return this.accountid;
	}

	public void setAccountid(String accountid){
		this.accountid=accountid;
	}

	public String getIp(){
		return this.ip;
	}

	public void setIp(String ip){
		this.ip=ip;
	}

	public String getExesql(){
		return this.exesql;
	}

	public void setExesql(String exesql){
		this.exesql=exesql;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}