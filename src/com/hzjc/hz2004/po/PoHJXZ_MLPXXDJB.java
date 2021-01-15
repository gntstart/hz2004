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
*门（楼）牌信息冻结表
*/
@Entity
@Table(name="HJXZ_MLPXXDJB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJXZ_MLPXXDJB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*门（楼）牌冻结ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	mlpdjid;
	private String	djfs;
	private String	djlx;
	private String	ssxq;
	private String	jlx;
	private String	jcwh;
	private String	mlph;
	private String	mlxz;
	private String	qyrq;
	private String	jzrq;
	private Long	cjhjywid;
	private Long	xghjywid;

	public PoHJXZ_MLPXXDJB(){}

	public Long getMlpdjid(){
		return this.mlpdjid;
	}

	public void setMlpdjid(Long mlpdjid){
		this.mlpdjid=mlpdjid;
	}

	public String getDjfs(){
		return this.djfs;
	}

	public void setDjfs(String djfs){
		this.djfs=djfs;
	}

	public String getDjlx(){
		return this.djlx;
	}

	public void setDjlx(String djlx){
		this.djlx=djlx;
	}

	public String getSsxq(){
		return this.ssxq;
	}

	public void setSsxq(String ssxq){
		this.ssxq=ssxq;
	}

	public String getJlx(){
		return this.jlx;
	}

	public void setJlx(String jlx){
		this.jlx=jlx;
	}

	public String getJcwh(){
		return this.jcwh;
	}

	public void setJcwh(String jcwh){
		this.jcwh=jcwh;
	}

	public String getMlph(){
		return this.mlph;
	}

	public void setMlph(String mlph){
		this.mlph=mlph;
	}

	public String getMlxz(){
		return this.mlxz;
	}

	public void setMlxz(String mlxz){
		this.mlxz=mlxz;
	}

	public String getQyrq(){
		return this.qyrq;
	}

	public void setQyrq(String qyrq){
		this.qyrq=qyrq;
	}

	public String getJzrq(){
		return this.jzrq;
	}

	public void setJzrq(String jzrq){
		this.jzrq=jzrq;
	}

	public Long getCjhjywid(){
		return this.cjhjywid;
	}

	public void setCjhjywid(Long cjhjywid){
		this.cjhjywid=cjhjywid;
	}

	public Long getXghjywid(){
		return this.xghjywid;
	}

	public void setXghjywid(Long xghjywid){
		this.xghjywid=xghjywid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}