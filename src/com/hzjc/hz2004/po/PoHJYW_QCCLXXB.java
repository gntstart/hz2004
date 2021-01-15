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
*迁出处理信息表
*/
@Entity
@Table(name="HJYW_QCCLXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHJYW_QCCLXXB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*迁出处理ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	qcclid;
	private Long	qqrynbid;
	private Long	qhrynbid;
	private String	czlx;
	private String	clrq;
	private String	clbz;
	private Long	clrid;
	private String	cgdyrq;
	private Long	hjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxhjywid;
	private Long	mlpnbid_q;
	private Long	ryid;
	private String	gmsfhm;
	private String	xm;
	private String	xb;
	private String	mz;
	private String	csrq;
	private String	cssj;
	private String	csdssxq;
	private Long	mlpnbid_h;
	private String	ssxq_q;
	private String	jlx_q;
	private String	mlph_q;
	private String	mlxz_q;
	private String	pcs_q;
	private String	zrq_q;
	private String	xzjd_q;
	private String	jcwh_q;
	private String	pxh_q;
	private String	ssxq_h;
	private String	jlx_h;
	private String	mlph_h;
	private String	mlxz_h;
	private String	pcs_h;
	private String	zrq_h;
	private String	xzjd_h;
	private String	jcwh_h;
	private String	pxh_h;

	public PoHJYW_QCCLXXB(){}

	public Long getQcclid(){
		return this.qcclid;
	}

	public void setQcclid(Long qcclid){
		this.qcclid=qcclid;
	}

	public Long getQqrynbid(){
		return this.qqrynbid;
	}

	public void setQqrynbid(Long qqrynbid){
		this.qqrynbid=qqrynbid;
	}

	public Long getQhrynbid(){
		return this.qhrynbid;
	}

	public void setQhrynbid(Long qhrynbid){
		this.qhrynbid=qhrynbid;
	}

	public String getCzlx(){
		return this.czlx;
	}

	public void setCzlx(String czlx){
		this.czlx=czlx;
	}

	public String getClrq(){
		return this.clrq;
	}

	public void setClrq(String clrq){
		this.clrq=clrq;
	}

	public String getClbz(){
		return this.clbz;
	}

	public void setClbz(String clbz){
		this.clbz=clbz;
	}

	public Long getClrid(){
		return this.clrid;
	}

	public void setClrid(Long clrid){
		this.clrid=clrid;
	}

	public String getCgdyrq(){
		return this.cgdyrq;
	}

	public void setCgdyrq(String cgdyrq){
		this.cgdyrq=cgdyrq;
	}

	public Long getHjywid(){
		return this.hjywid;
	}

	public void setHjywid(Long hjywid){
		this.hjywid=hjywid;
	}

	public String getCxbz(){
		return this.cxbz;
	}

	public void setCxbz(String cxbz){
		this.cxbz=cxbz;
	}

	public String getCxsj(){
		return this.cxsj;
	}

	public void setCxsj(String cxsj){
		this.cxsj=cxsj;
	}

	public Long getCxrid(){
		return this.cxrid;
	}

	public void setCxrid(Long cxrid){
		this.cxrid=cxrid;
	}

	public Long getCxhjywid(){
		return this.cxhjywid;
	}

	public void setCxhjywid(Long cxhjywid){
		this.cxhjywid=cxhjywid;
	}

	public Long getMlpnbid_q(){
		return this.mlpnbid_q;
	}

	public void setMlpnbid_q(Long mlpnbid_q){
		this.mlpnbid_q=mlpnbid_q;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public String getGmsfhm(){
		return this.gmsfhm;
	}

	public void setGmsfhm(String gmsfhm){
		this.gmsfhm=gmsfhm;
	}

	public String getXm(){
		return this.xm;
	}

	public void setXm(String xm){
		this.xm=xm;
	}

	public String getXb(){
		return this.xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public String getMz(){
		return this.mz;
	}

	public void setMz(String mz){
		this.mz=mz;
	}

	public String getCsrq(){
		return this.csrq;
	}

	public void setCsrq(String csrq){
		this.csrq=csrq;
	}

	public String getCssj(){
		return this.cssj;
	}

	public void setCssj(String cssj){
		this.cssj=cssj;
	}

	public String getCsdssxq(){
		return this.csdssxq;
	}

	public void setCsdssxq(String csdssxq){
		this.csdssxq=csdssxq;
	}

	public Long getMlpnbid_h(){
		return this.mlpnbid_h;
	}

	public void setMlpnbid_h(Long mlpnbid_h){
		this.mlpnbid_h=mlpnbid_h;
	}

	public String getSsxq_q(){
		return this.ssxq_q;
	}

	public void setSsxq_q(String ssxq_q){
		this.ssxq_q=ssxq_q;
	}

	public String getJlx_q(){
		return this.jlx_q;
	}

	public void setJlx_q(String jlx_q){
		this.jlx_q=jlx_q;
	}

	public String getMlph_q(){
		return this.mlph_q;
	}

	public void setMlph_q(String mlph_q){
		this.mlph_q=mlph_q;
	}

	public String getMlxz_q(){
		return this.mlxz_q;
	}

	public void setMlxz_q(String mlxz_q){
		this.mlxz_q=mlxz_q;
	}

	public String getPcs_q(){
		return this.pcs_q;
	}

	public void setPcs_q(String pcs_q){
		this.pcs_q=pcs_q;
	}

	public String getZrq_q(){
		return this.zrq_q;
	}

	public void setZrq_q(String zrq_q){
		this.zrq_q=zrq_q;
	}

	public String getXzjd_q(){
		return this.xzjd_q;
	}

	public void setXzjd_q(String xzjd_q){
		this.xzjd_q=xzjd_q;
	}

	public String getJcwh_q(){
		return this.jcwh_q;
	}

	public void setJcwh_q(String jcwh_q){
		this.jcwh_q=jcwh_q;
	}

	public String getPxh_q(){
		return this.pxh_q;
	}

	public void setPxh_q(String pxh_q){
		this.pxh_q=pxh_q;
	}

	public String getSsxq_h(){
		return this.ssxq_h;
	}

	public void setSsxq_h(String ssxq_h){
		this.ssxq_h=ssxq_h;
	}

	public String getJlx_h(){
		return this.jlx_h;
	}

	public void setJlx_h(String jlx_h){
		this.jlx_h=jlx_h;
	}

	public String getMlph_h(){
		return this.mlph_h;
	}

	public void setMlph_h(String mlph_h){
		this.mlph_h=mlph_h;
	}

	public String getMlxz_h(){
		return this.mlxz_h;
	}

	public void setMlxz_h(String mlxz_h){
		this.mlxz_h=mlxz_h;
	}

	public String getPcs_h(){
		return this.pcs_h;
	}

	public void setPcs_h(String pcs_h){
		this.pcs_h=pcs_h;
	}

	public String getZrq_h(){
		return this.zrq_h;
	}

	public void setZrq_h(String zrq_h){
		this.zrq_h=zrq_h;
	}

	public String getXzjd_h(){
		return this.xzjd_h;
	}

	public void setXzjd_h(String xzjd_h){
		this.xzjd_h=xzjd_h;
	}

	public String getJcwh_h(){
		return this.jcwh_h;
	}

	public void setJcwh_h(String jcwh_h){
		this.jcwh_h=jcwh_h;
	}

	public String getPxh_h(){
		return this.pxh_h;
	}

	public void setPxh_h(String pxh_h){
		this.pxh_h=pxh_h;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}