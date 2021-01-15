package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POST_SFZSL_LOG" )
public class PostSfzslLog  implements com.hzjc.wsstruts.po.PO{
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	private String loadid;
	private String dqbm;
	private String pcsbm;
	private String pcsmc;
	private String loaddate;
	private Long loadnum;

	public String getLoadid() {
		return loadid;
	}
	public void setLoadid(String loadid) {
		this.loadid = loadid;
	}
	public String getDqbm() {
		return dqbm;
	}
	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}
	public String getPcsbm() {
		return pcsbm;
	}
	public void setPcsbm(String pcsbm) {
		this.pcsbm = pcsbm;
	}
	public String getPcsmc() {
		return pcsmc;
	}
	public void setPcsmc(String pcsmc) {
		this.pcsmc = pcsmc;
	}
	public String getLoaddate() {
		return loaddate;
	}
	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	public Long getLoadnum() {
		return loadnum;
	}
	public void setLoadnum(Long loadnum) {
		this.loadnum = loadnum;
	}
}
