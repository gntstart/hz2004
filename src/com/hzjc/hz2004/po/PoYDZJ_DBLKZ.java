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
*打包量控制
*/
@Entity
@Table(name="YDZJ_DBLKZ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoYDZJ_DBLKZ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*申办点代码
	*/
	@GenericGenerator(name = "generator", strategy = "assigned") 
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	sbddm;
	private String	sbdmc;
	private Long	jbdbl;
	private Long	ydbl;
	private String	sfdbwc;
	private String	cshsj;

	public PoYDZJ_DBLKZ(){}

	public String getSbddm(){
		return this.sbddm;
	}

	public void setSbddm(String sbddm){
		this.sbddm=sbddm;
	}

	public String getSbdmc(){
		return this.sbdmc;
	}

	public void setSbdmc(String sbdmc){
		this.sbdmc=sbdmc;
	}

	public Long getJbdbl(){
		return this.jbdbl;
	}

	public void setJbdbl(Long jbdbl){
		this.jbdbl=jbdbl;
	}

	public Long getYdbl(){
		return this.ydbl;
	}

	public void setYdbl(Long ydbl){
		this.ydbl=ydbl;
	}

	public String getSfdbwc(){
		return this.sfdbwc;
	}

	public void setSfdbwc(String sfdbwc){
		this.sfdbwc=sfdbwc;
	}

	public String getCshsj(){
		return this.cshsj;
	}

	public void setCshsj(String cshsj){
		this.cshsj=cshsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}