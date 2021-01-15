package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POST_SJBBH" )
public class PostSjbbh implements com.hzjc.wsstruts.po.PO{
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String bhid;
	private String bhsj;
	private Integer bh;
	
	public String getBhid() {
		return bhid;
	}
	public void setBhid(String bhid) {
		this.bhid = bhid;
	}
	public String getBhsj() {
		return bhsj;
	}
	public void setBhsj(String bhsj) {
		this.bhsj = bhsj;
	}
	public Integer getBh() {
		return bh;
	}
	public void setBh(Integer bh) {
		this.bh = bh;
	}
}
