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
*消息信息表
*/
@Entity
@Table(name="MESSAGEXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoMESSAGEXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	messageid;//消息ID
	private Long    fsryhid;//发送人用户ID
	private String	fsryhxm;//发送人用户登录名
	private String	jssj;//接收时间
	private String	message;//信息内容
	private Long 	jsryhid;//接收人Id
	private String 	jsryhxm;//接收人姓名
	private String	readflag;//是否已读  1已读 0未读
	private String	removeflag;//是否删除  1已删除 0未删除
	private String	jsdw;//是否删除  1已删除 0未删除
	public PoMESSAGEXXB(){}

	public Long getMessageid() {
		return messageid;
	}

	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}

	public Long getFsryhid() {
		return fsryhid;
	}

	public void setFsryhid(Long fsryhid) {
		this.fsryhid = fsryhid;
	}

	public String getFsryhxm() {
		return fsryhxm;
	}

	public void setFsryhxm(String fsryhxm) {
		this.fsryhxm = fsryhxm;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getJsryhid() {
		return jsryhid;
	}

	public void setJsryhid(Long jsryhid) {
		this.jsryhid = jsryhid;
	}

	public String getReadflag() {
		return readflag;
	}

	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}

	public String getRemoveflag() {
		return removeflag;
	}

	public void setRemoveflag(String removeflag) {
		this.removeflag = removeflag;
	}

	public String getJsryhxm() {
		return jsryhxm;
	}

	public void setJsryhxm(String jsryhxm) {
		this.jsryhxm = jsryhxm;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}