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
*数据质量检测进度表
*/
@Entity
@Table(name="TJ_JD_JC" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoTJ_JD_JC implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*考核代码
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	khdm;
	private String	khmc;
	private String	khzt;
	private String	khqssj;
	private String	khjssj;
	private Long	cwsl;
	private String	cwsm;

	public PoTJ_JD_JC(){}

	public String getKhdm(){
		return this.khdm;
	}

	public void setKhdm(String khdm){
		this.khdm=khdm;
	}

	public String getKhmc(){
		return this.khmc;
	}

	public void setKhmc(String khmc){
		this.khmc=khmc;
	}

	public String getKhzt(){
		return this.khzt;
	}

	public void setKhzt(String khzt){
		this.khzt=khzt;
	}

	public String getKhqssj(){
		return this.khqssj;
	}

	public void setKhqssj(String khqssj){
		this.khqssj=khqssj;
	}

	public String getKhjssj(){
		return this.khjssj;
	}

	public void setKhjssj(String khjssj){
		this.khjssj=khjssj;
	}

	public Long getCwsl(){
		return this.cwsl;
	}

	public void setCwsl(Long cwsl){
		this.cwsl=cwsl;
	}

	public String getCwsm(){
		return this.cwsm;
	}

	public void setCwsm(String cwsm){
		this.cwsm=cwsm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}