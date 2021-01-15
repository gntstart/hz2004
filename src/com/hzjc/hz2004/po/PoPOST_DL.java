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
*上报日志队列表
*/
@Entity
@Table(name="POST_DL" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPOST_DL implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*日志ID
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	logid;
	private String	ywcode;
	private String	ywid;
	private Timestamp	cjsj;
	private Timestamp	sbsj;
	private String	sbzt;
	private Long	sbcs;
	private String	sbls;
	private String	sbrzid;
	private String	sbjg;
	private String	sbjgms;
	private String	packzt;
	private Timestamp	packztsj;
	private String	packzterr;
	private String	pch;

	public PoPOST_DL(){}

	public String getLogid(){
		return this.logid;
	}

	public void setLogid(String logid){
		this.logid=logid;
	}

	public String getYwcode(){
		return this.ywcode;
	}

	public void setYwcode(String ywcode){
		this.ywcode=ywcode;
	}

	public String getYwid(){
		return this.ywid;
	}

	public void setYwid(String ywid){
		this.ywid=ywid;
	}

	public Timestamp getCjsj(){
		return this.cjsj;
	}

	public void setCjsj(Timestamp cjsj){
		this.cjsj=cjsj;
	}

	public Timestamp getSbsj(){
		return this.sbsj;
	}

	public void setSbsj(Timestamp sbsj){
		this.sbsj=sbsj;
	}

	public String getSbzt(){
		return this.sbzt;
	}

	public void setSbzt(String sbzt){
		this.sbzt=sbzt;
	}

	public Long getSbcs(){
		return this.sbcs;
	}

	public void setSbcs(Long sbcs){
		this.sbcs=sbcs;
	}

	public String getSbls(){
		return this.sbls;
	}

	public void setSbls(String sbls){
		this.sbls=sbls;
	}

	public String getSbrzid(){
		return this.sbrzid;
	}

	public void setSbrzid(String sbrzid){
		this.sbrzid=sbrzid;
	}

	public String getSbjg(){
		return this.sbjg;
	}

	public void setSbjg(String sbjg){
		this.sbjg=sbjg;
	}

	public String getSbjgms(){
		return this.sbjgms;
	}

	public void setSbjgms(String sbjgms){
		this.sbjgms=sbjgms;
	}

	public String getPackzt(){
		return this.packzt;
	}

	public void setPackzt(String packzt){
		this.packzt=packzt;
	}

	public Timestamp getPackztsj(){
		return this.packztsj;
	}

	public void setPackztsj(Timestamp packztsj){
		this.packztsj=packztsj;
	}

	public String getPackzterr(){
		return this.packzterr;
	}

	public void setPackzterr(String packzterr){
		this.packzterr=packzterr;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}