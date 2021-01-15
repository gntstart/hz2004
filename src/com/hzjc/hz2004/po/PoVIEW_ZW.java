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
*指纹视图
*/
@Entity
@Table(name="VIEW_ZW" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoVIEW_ZW implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String	jmsfzslh;
	private String	xm;
	private String	gmsfhm;

	public PoVIEW_ZW(){}

	public String getJmsfzslh(){
		return this.jmsfzslh;
	}

	public void setJmsfzslh(String jmsfzslh){
		this.jmsfzslh=jmsfzslh;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}