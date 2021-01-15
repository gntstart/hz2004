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
@Table(name="XT_QYZDYS" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoXT_QYZDYS implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*KEY
	*/
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	zdid;
	private String	zdlx;
	private String	yzd;
	private String	xzd;

	public PoXT_QYZDYS(){}

	public String getZdid(){
		return this.zdid;
	}

	public void setZdid(String zdid){
		this.zdid=zdid;
	}

	public String getZdlx(){
		return this.zdlx;
	}

	public void setZdlx(String zdlx){
		this.zdlx=zdlx;
	}

	public String getYzd(){
		return this.yzd;
	}

	public void setYzd(String yzd){
		this.yzd=yzd;
	}

	public String getXzd(){
		return this.xzd;
	}

	public void setXzd(String xzd){
		this.xzd=xzd;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}