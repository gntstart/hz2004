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
*评价信息表
*/
@Entity
@Table(name="WW_PJXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoWW_PJXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*评价ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	pjid;
	private String	pjjg;
	private String	pjrlxfs;
	private String	pjkssj;
	private String	pjjssj;
	private Long	bpjrid;
	private String	bpjrdlm;
	private String	bpjrxm;
	private String	bpjdw;
	private Long	ywzs;

	public PoWW_PJXXB(){}

	public Long getPjid(){
		return this.pjid;
	}

	public void setPjid(Long pjid){
		this.pjid=pjid;
	}

	public String getPjjg(){
		return this.pjjg;
	}

	public void setPjjg(String pjjg){
		this.pjjg=pjjg;
	}

	public String getPjrlxfs(){
		return this.pjrlxfs;
	}

	public void setPjrlxfs(String pjrlxfs){
		this.pjrlxfs=pjrlxfs;
	}

	public String getPjkssj(){
		return this.pjkssj;
	}

	public void setPjkssj(String pjkssj){
		this.pjkssj=pjkssj;
	}

	public String getPjjssj(){
		return this.pjjssj;
	}

	public void setPjjssj(String pjjssj){
		this.pjjssj=pjjssj;
	}

	public Long getBpjrid(){
		return this.bpjrid;
	}

	public void setBpjrid(Long bpjrid){
		this.bpjrid=bpjrid;
	}

	public String getBpjrdlm(){
		return this.bpjrdlm;
	}

	public void setBpjrdlm(String bpjrdlm){
		this.bpjrdlm=bpjrdlm;
	}

	public String getBpjrxm(){
		return this.bpjrxm;
	}

	public void setBpjrxm(String bpjrxm){
		this.bpjrxm=bpjrxm;
	}

	public String getBpjdw(){
		return this.bpjdw;
	}

	public void setBpjdw(String bpjdw){
		this.bpjdw=bpjdw;
	}

	public Long getYwzs(){
		return this.ywzs;
	}

	public void setYwzs(Long ywzs){
		this.ywzs=ywzs;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}