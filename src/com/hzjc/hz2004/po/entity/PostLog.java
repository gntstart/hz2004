package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="POST_LOG" )
public class PostLog implements com.hzjc.wsstruts.po.PO{
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String logid;
	
	private Date logsj;
	private Integer postnum;
	private String xkz;
	private String info;
	private String remsg;
	private String sfcg;
	private String errmsg;
	private Long hs;
	
	public Long getHs() {
		return hs;
	}
	public void setHs(Long hs) {
		this.hs = hs;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}

	public Date getLogsj() {
		return logsj;
	}
	public void setLogsj(Date logsj) {
		this.logsj = logsj;
	}
	public Integer getPostnum() {
		return postnum;
	}
	public void setPostnum(Integer postnum) {
		this.postnum = postnum;
	}
	public String getXkz() {
		return xkz;
	}
	public void setXkz(String xkz) {
		this.xkz = xkz;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRemsg() {
		return remsg;
	}
	public void setRemsg(String remsg) {
		this.remsg = remsg;
	}
	public String getSfcg() {
		return sfcg;
	}
	public void setSfcg(String sfcg) {
		this.sfcg = sfcg;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
