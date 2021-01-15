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
*一代证受理表
*/
@Entity
@Table(name="ZJYW_YDZSLB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoZJYW_YDZSLB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*办证受理ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	bzslid;
	private Long	ryid;
	private Long	rynbid;
	private Long	zpid;
	private String	zz1;
	private String	zz2;
	private String	zz3;
	private String	qfrq;
	private String	yxqx;
	private String	bzlb;
	private String	bzyy;
	private String	blnf;
	private String	blbz;
	private String	dkdy;
	private String	sffs;
	private String	fssj;
	private String	pjbh;
	private String	tkzd;
	private String	pjbh2;
	private String	dbpjbh;
	private Long	zjywid;
	private String	cxbz;
	private String	cxsj;
	private Long	cxrid;
	private Long	cxzjywid;

	public PoZJYW_YDZSLB(){}

	public Long getBzslid(){
		return this.bzslid;
	}

	public void setBzslid(Long bzslid){
		this.bzslid=bzslid;
	}

	public Long getRyid(){
		return this.ryid;
	}

	public void setRyid(Long ryid){
		this.ryid=ryid;
	}

	public Long getRynbid(){
		return this.rynbid;
	}

	public void setRynbid(Long rynbid){
		this.rynbid=rynbid;
	}

	public Long getZpid(){
		return this.zpid;
	}

	public void setZpid(Long zpid){
		this.zpid=zpid;
	}

	public String getZz1(){
		return this.zz1;
	}

	public void setZz1(String zz1){
		this.zz1=zz1;
	}

	public String getZz2(){
		return this.zz2;
	}

	public void setZz2(String zz2){
		this.zz2=zz2;
	}

	public String getZz3(){
		return this.zz3;
	}

	public void setZz3(String zz3){
		this.zz3=zz3;
	}

	public String getQfrq(){
		return this.qfrq;
	}

	public void setQfrq(String qfrq){
		this.qfrq=qfrq;
	}

	public String getYxqx(){
		return this.yxqx;
	}

	public void setYxqx(String yxqx){
		this.yxqx=yxqx;
	}

	public String getBzlb(){
		return this.bzlb;
	}

	public void setBzlb(String bzlb){
		this.bzlb=bzlb;
	}

	public String getBzyy(){
		return this.bzyy;
	}

	public void setBzyy(String bzyy){
		this.bzyy=bzyy;
	}

	public String getBlnf(){
		return this.blnf;
	}

	public void setBlnf(String blnf){
		this.blnf=blnf;
	}

	public String getBlbz(){
		return this.blbz;
	}

	public void setBlbz(String blbz){
		this.blbz=blbz;
	}

	public String getDkdy(){
		return this.dkdy;
	}

	public void setDkdy(String dkdy){
		this.dkdy=dkdy;
	}

	public String getSffs(){
		return this.sffs;
	}

	public void setSffs(String sffs){
		this.sffs=sffs;
	}

	public String getFssj(){
		return this.fssj;
	}

	public void setFssj(String fssj){
		this.fssj=fssj;
	}

	public String getPjbh(){
		return this.pjbh;
	}

	public void setPjbh(String pjbh){
		this.pjbh=pjbh;
	}

	public String getTkzd(){
		return this.tkzd;
	}

	public void setTkzd(String tkzd){
		this.tkzd=tkzd;
	}

	public String getPjbh2(){
		return this.pjbh2;
	}

	public void setPjbh2(String pjbh2){
		this.pjbh2=pjbh2;
	}

	public String getDbpjbh(){
		return this.dbpjbh;
	}

	public void setDbpjbh(String dbpjbh){
		this.dbpjbh=dbpjbh;
	}

	public Long getZjywid(){
		return this.zjywid;
	}

	public void setZjywid(Long zjywid){
		this.zjywid=zjywid;
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

	public Long getCxzjywid(){
		return this.cxzjywid;
	}

	public void setCxzjywid(Long cxzjywid){
		this.cxzjywid=cxzjywid;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}