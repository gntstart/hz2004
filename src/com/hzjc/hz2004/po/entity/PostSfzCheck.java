package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="POST_SFZ_CHECK" )
public class PostSfzCheck implements com.hzjc.wsstruts.po.PO{
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String logid;
	private String dqbm;
	private String xm;
	private String sfzh;
	private String chyy;
	private String chyy_name;
	private String fkxx;
	private String fkrid;
	private String fkrdlm;
	private String fkrxm;
private Date fksj;
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getDqbm() {
		return dqbm;
	}
	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getChyy() {
		return chyy;
	}
	public void setChyy(String chyy) {
		this.chyy = chyy;
	}
	public String getChyy_name() {
		return chyy_name;
	}
	public void setChyy_name(String chyy_name) {
		this.chyy_name = chyy_name;
	}
	public String getFkxx() {
		return fkxx;
	}
	public void setFkxx(String fkxx) {
		this.fkxx = fkxx;
	}
	public String getFkrid() {
		return fkrid;
	}
	public void setFkrid(String fkrid) {
		this.fkrid = fkrid;
	}
	public String getFkrdlm() {
		return fkrdlm;
	}
	public void setFkrdlm(String fkrdlm) {
		this.fkrdlm = fkrdlm;
	}
	public String getFkrxm() {
		return fkrxm;
	}
	public void setFkrxm(String fkrxm) {
		this.fkrxm = fkrxm;
	}
}
