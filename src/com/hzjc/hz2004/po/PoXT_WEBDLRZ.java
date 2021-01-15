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
*WEB登录日志
*/
@Entity
@Table(name="XT_WEBDLRZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_WEBDLRZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	rzid;
	private Timestamp	dlsj;
	private String	dlm;
	private String	dlsfz;
	private String	dlip;

	public PoXT_WEBDLRZ(){}

	public String getRzid(){
		return this.rzid;
	}

	public void setRzid(String rzid){
		this.rzid=rzid;
	}

	public Timestamp getDlsj(){
		return this.dlsj;
	}

	public void setDlsj(Timestamp dlsj){
		this.dlsj=dlsj;
	}

	public String getDlm(){
		return this.dlm;
	}

	public void setDlm(String dlm){
		this.dlm=dlm;
	}

	public String getDlsfz(){
		return this.dlsfz;
	}

	public void setDlsfz(String dlsfz){
		this.dlsfz=dlsfz;
	}

	public String getDlip(){
		return this.dlip;
	}

	public void setDlip(String dlip){
		this.dlip=dlip;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}