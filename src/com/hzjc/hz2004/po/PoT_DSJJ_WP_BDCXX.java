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
@Table(name="T_DSJJ_WP_BDCXX" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoT_DSJJ_WP_BDCXX implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	objectid;//主键	
	private String	bdcqzh;//不动产权证号
	private String	sbzz;//上报组织
	private String	zl;//坐落
	private String	qzhj;//权证附件
	private String	zsxh;//证书序号
	private String	ywh;//业务号
	private String	sfyx;//是否有效
	private String	yt;//用途
	private String	mj;//面积
	private String	syqx;//使用期限
	private String	qlqtzk;//权利其他情况
	private String	sjly;//数据来源
	private String	qlr;//权利人
	private String	bdcdyh;//不动产单元号
	private String	qllx;//权利类型
	private String	qlxz;//权利性质
	private String	zj;//证件
	private String	zjlx;//证件类型
	private String	zjhm;//权利人证件号码
	private String	ysbh;//印刷编号
	private String	gyqk;//共有情况
	private String	createdate;//创建日期
	private String	etldate;//ETLDATE
	private String	djsj;//登记时间
	private String	zxsj;//注销时间
	private String	gxsj;//更新时间
	private String	crsj;//插入时间
	public PoT_DSJJ_WP_BDCXX(){}

	public Long getObjectid() {
		return objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public String getBdcqzh() {
		return bdcqzh;
	}

	public void setBdcqzh(String bdcqzh) {
		this.bdcqzh = bdcqzh;
	}

	public String getSbzz() {
		return sbzz;
	}

	public void setSbzz(String sbzz) {
		this.sbzz = sbzz;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getQzhj() {
		return qzhj;
	}

	public void setQzhj(String qzhj) {
		this.qzhj = qzhj;
	}

	public String getZsxh() {
		return zsxh;
	}

	public void setZsxh(String zsxh) {
		this.zsxh = zsxh;
	}

	public String getYwh() {
		return ywh;
	}

	public void setYwh(String ywh) {
		this.ywh = ywh;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getSyqx() {
		return syqx;
	}

	public void setSyqx(String syqx) {
		this.syqx = syqx;
	}

	public String getQlqtzk() {
		return qlqtzk;
	}

	public void setQlqtzk(String qlqtzk) {
		this.qlqtzk = qlqtzk;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getQlr() {
		return qlr;
	}

	public void setQlr(String qlr) {
		this.qlr = qlr;
	}

	public String getBdcdyh() {
		return bdcdyh;
	}

	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}

	public String getQllx() {
		return qllx;
	}

	public void setQllx(String qllx) {
		this.qllx = qllx;
	}

	public String getQlxz() {
		return qlxz;
	}

	public void setQlxz(String qlxz) {
		this.qlxz = qlxz;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getYsbh() {
		return ysbh;
	}

	public void setYsbh(String ysbh) {
		this.ysbh = ysbh;
	}

	public String getGyqk() {
		return gyqk;
	}

	public void setGyqk(String gyqk) {
		this.gyqk = gyqk;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getEtldate() {
		return etldate;
	}

	public void setEtldate(String etldate) {
		this.etldate = etldate;
	}

	public String getDjsj() {
		return djsj;
	}

	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}

	public String getZxsj() {
		return zxsj;
	}

	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}

	public String getGxsj() {
		return gxsj;
	}

	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}

	public String getCrsj() {
		return crsj;
	}

	public void setCrsj(String crsj) {
		this.crsj = crsj;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}