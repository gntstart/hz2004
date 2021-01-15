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
*常住人口基本信息表
*/
@Entity
@Table(name="JH_LH_VIEW" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJH_LH_VIEW implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/

	private String	mxm;//男性姓名
	private String	mzjhm;//男性证件号码
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private String	mzjyzh;//男性证件编号
	private String	gxm;//女性姓名
	private String	gzjhm;//女性证件号码
	private String	gzjyzh;//女性证件编号
	private String	djrq;//登记日期
	private String	lx;//类型
	
	
	public PoJH_LH_VIEW(){}

	
	public String getMxm() {
		return mxm;
	}


	public void setMxm(String mxm) {
		this.mxm = mxm;
	}


	public String getMzjhm() {
		return mzjhm;
	}


	public void setMzjhm(String mzjhm) {
		this.mzjhm = mzjhm;
	}


	public String getMzjyzh() {
		return mzjyzh;
	}


	public void setMzjyzh(String mzjyzh) {
		this.mzjyzh = mzjyzh;
	}


	public String getGxm() {
		return gxm;
	}


	public void setGxm(String gxm) {
		this.gxm = gxm;
	}


	public String getGzjhm() {
		return gzjhm;
	}


	public void setGzjhm(String gzjhm) {
		this.gzjhm = gzjhm;
	}


	public String getGzjyzh() {
		return gzjyzh;
	}


	public void setGzjyzh(String gzjyzh) {
		this.gzjyzh = gzjyzh;
	}


	public String getDjrq() {
		return djrq;
	}


	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}


	public String getLx() {
		return lx;
	}


	public void setLx(String lx) {
		this.lx = lx;
	}


	public static long getSerialversionuid() {			return serialVersionUID;	}}