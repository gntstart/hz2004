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
*接收进度信息表
*/
@Entity
@Table(name="JS_JDXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJS_JDXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*接收进度ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	jsjdid;
	private Long	pcid;
	private String	pch;
	private String	pczt;
	private String	qssj;
	private String	jssj;
	private Long	yclbz;
	private Long	yjclbz;
	private String	zhgxsj;
	private String	xtycxx;
	private String	bz;

	public PoJS_JDXXB(){}

	public Long getJsjdid(){
		return this.jsjdid;
	}

	public void setJsjdid(Long jsjdid){
		this.jsjdid=jsjdid;
	}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getPczt(){
		return this.pczt;
	}

	public void setPczt(String pczt){
		this.pczt=pczt;
	}

	public String getQssj(){
		return this.qssj;
	}

	public void setQssj(String qssj){
		this.qssj=qssj;
	}

	public String getJssj(){
		return this.jssj;
	}

	public void setJssj(String jssj){
		this.jssj=jssj;
	}

	public Long getYclbz(){
		return this.yclbz;
	}

	public void setYclbz(Long yclbz){
		this.yclbz=yclbz;
	}

	public Long getYjclbz(){
		return this.yjclbz;
	}

	public void setYjclbz(Long yjclbz){
		this.yjclbz=yjclbz;
	}

	public String getZhgxsj(){
		return this.zhgxsj;
	}

	public void setZhgxsj(String zhgxsj){
		this.zhgxsj=zhgxsj;
	}

	public String getXtycxx(){
		return this.xtycxx;
	}

	public void setXtycxx(String xtycxx){
		this.xtycxx=xtycxx;
	}

	public String getBz(){
		return this.bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}