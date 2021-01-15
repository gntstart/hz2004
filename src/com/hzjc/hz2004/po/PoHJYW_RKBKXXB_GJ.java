package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="HJYW_RKBKXXB_GJ" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_RKBKXXB_GJ implements com.hzjc.wsstruts.po.PO, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "uuid") 
	@Id
	private String gjid;
	private String xm;
	private String gmsfhm;
	private String bklx;
	private String bklxmc;
	private String bktx;
	private String bksj;
	private String bkmjxm;
	private String hjywlx;
	private String hjywlxmc;
	private String hjywsj;
	private String hjywmj;
	private String hjywmjxm;
	
	public String getGjid() {
		return gjid;
	}
	public void setGjid(String gjid) {
		this.gjid = gjid;
	}
	public String getHjywlx() {
		return hjywlx;
	}
	public void setHjywlx(String hjywlx) {
		this.hjywlx = hjywlx;
	}
	public String getHjywlxmc() {
		return hjywlxmc;
	}
	public void setHjywlxmc(String hjywlxmc) {
		this.hjywlxmc = hjywlxmc;
	}
	public String getHjywsj() {
		return hjywsj;
	}
	public void setHjywsj(String hjywsj) {
		this.hjywsj = hjywsj;
	}
	public String getHjywmj() {
		return hjywmj;
	}
	public void setHjywmj(String hjywmj) {
		this.hjywmj = hjywmj;
	}
	public String getHjywmjxm() {
		return hjywmjxm;
	}
	public void setHjywmjxm(String hjywmjxm) {
		this.hjywmjxm = hjywmjxm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getGmsfhm() {
		return gmsfhm;
	}
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	public String getBklx() {
		return bklx;
	}
	public void setBklx(String bklx) {
		this.bklx = bklx;
	}
	public String getBklxmc() {
		return bklxmc;
	}
	public void setBklxmc(String bklxmc) {
		this.bklxmc = bklxmc;
	}
	public String getBktx() {
		return bktx;
	}
	public void setBktx(String bktx) {
		this.bktx = bktx;
	}
	public String getBksj() {
		return bksj;
	}
	public void setBksj(String bksj) {
		this.bksj = bksj;
	}
	public String getBkmjxm() {
		return bkmjxm;
	}
	public void setBkmjxm(String bkmjxm) {
		this.bkmjxm = bkmjxm;
	}
}
