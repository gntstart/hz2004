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
@Table(name="PERSON_DY_SET" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoPERSON_DY_SET implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	yhid;//用户id
	private String	dyyl_dysz;//需要进行打印预览--打印设置
	private String	tcdysz_dysz;//打印时弹出打印设置--打印设置
	private String	dyzp_cbsz;//打印照片--常表设置
	private String	jth_syksz;//集体户--索引卡设置
	private String	hkbsy_hkbsz;//打印户口簿首页--户口簿设置
	private String	hkbbm_hkbsz;//打印户口簿背面--户口簿设置
	private String	jthfshksy_hkbsz;//集体户方式户口首页--户口簿设置
	private String	jthfshky_hkbsz;//集体户方式户口页--户口簿设置
	private String	csyy_hkbsz;//打印出生原因--户口簿设置
	private String	dyzp_hjzmsz;//打印照片--户籍证明设置
	private String	hcyxx_hjzmsz;//户成员信息--户籍证明设置
	private String	bdyy_hjzmsz;//变动原因--户籍证明设置
	private String	bdxx_hjzmsz;//变动信息--户籍证明设置
	private String	zxryxx_hjzmsz;//注销人员信息--户籍证明设置
	private String	dydw_hjzmsz;//打印单位--户籍证明设置
	private String	dyhh_hjzmsz;//打印户号--户籍证明设置
	private String	dyhyzk_hjzmsz;//打印婚姻状况--户籍证明设置
	private String	dybyqk_hjzmsz;//打印兵役情况--户籍证明设置
	private String	dywhcd_hjzmsz;//打印文化程度--户籍证明设置
	private String  yxts;//有效天数
	private String  dyjmsfzsqb_lsbz;//打印居民身份证申请表--零散办证
	private String  sfzlqdxgnr_lsbz;//身份证领取单相关内容--零散办证
	private String  qyzbh;//迁移证编号
	private String  zqzbh;//准迁证编号
	private String  dkqlx;//读卡器类型
	private String  yznf;//印制年份
	private int hjsysf;//户籍首页收费
	private int zqzsf;//准迁证收费
	private int qyzsf;//迁移证收费
	public PoPERSON_DY_SET(){}
	
	public Long getYhid() {
		return yhid;
	}

	public void setYhid(Long yhid) {
		this.yhid = yhid;
	}

	public String getDyyl_dysz() {
		return dyyl_dysz;
	}

	public void setDyyl_dysz(String dyyl_dysz) {
		this.dyyl_dysz = dyyl_dysz;
	}

	public String getTcdysz_dysz() {
		return tcdysz_dysz;
	}

	public void setTcdysz_dysz(String tcdysz_dysz) {
		this.tcdysz_dysz = tcdysz_dysz;
	}

	public String getDyzp_cbsz() {
		return dyzp_cbsz;
	}

	public void setDyzp_cbsz(String dyzp_cbsz) {
		this.dyzp_cbsz = dyzp_cbsz;
	}

	public String getJth_syksz() {
		return jth_syksz;
	}

	public void setJth_syksz(String jth_syksz) {
		this.jth_syksz = jth_syksz;
	}

	public String getHkbsy_hkbsz() {
		return hkbsy_hkbsz;
	}

	public void setHkbsy_hkbsz(String hkbsy_hkbsz) {
		this.hkbsy_hkbsz = hkbsy_hkbsz;
	}

	public String getHkbbm_hkbsz() {
		return hkbbm_hkbsz;
	}

	public void setHkbbm_hkbsz(String hkbbm_hkbsz) {
		this.hkbbm_hkbsz = hkbbm_hkbsz;
	}

	public String getJthfshksy_hkbsz() {
		return jthfshksy_hkbsz;
	}

	public void setJthfshksy_hkbsz(String jthfshksy_hkbsz) {
		this.jthfshksy_hkbsz = jthfshksy_hkbsz;
	}

	public String getJthfshky_hkbsz() {
		return jthfshky_hkbsz;
	}

	public void setJthfshky_hkbsz(String jthfshky_hkbsz) {
		this.jthfshky_hkbsz = jthfshky_hkbsz;
	}

	public String getCsyy_hkbsz() {
		return csyy_hkbsz;
	}

	public void setCsyy_hkbsz(String csyy_hkbsz) {
		this.csyy_hkbsz = csyy_hkbsz;
	}

	public String getDyzp_hjzmsz() {
		return dyzp_hjzmsz;
	}

	public void setDyzp_hjzmsz(String dyzp_hjzmsz) {
		this.dyzp_hjzmsz = dyzp_hjzmsz;
	}

	public String getHcyxx_hjzmsz() {
		return hcyxx_hjzmsz;
	}

	public void setHcyxx_hjzmsz(String hcyxx_hjzmsz) {
		this.hcyxx_hjzmsz = hcyxx_hjzmsz;
	}

	public String getBdyy_hjzmsz() {
		return bdyy_hjzmsz;
	}

	public void setBdyy_hjzmsz(String bdyy_hjzmsz) {
		this.bdyy_hjzmsz = bdyy_hjzmsz;
	}

	public String getBdxx_hjzmsz() {
		return bdxx_hjzmsz;
	}

	public void setBdxx_hjzmsz(String bdxx_hjzmsz) {
		this.bdxx_hjzmsz = bdxx_hjzmsz;
	}

	public String getZxryxx_hjzmsz() {
		return zxryxx_hjzmsz;
	}

	public void setZxryxx_hjzmsz(String zxryxx_hjzmsz) {
		this.zxryxx_hjzmsz = zxryxx_hjzmsz;
	}

	public String getDydw_hjzmsz() {
		return dydw_hjzmsz;
	}

	public void setDydw_hjzmsz(String dydw_hjzmsz) {
		this.dydw_hjzmsz = dydw_hjzmsz;
	}

	public String getDyhh_hjzmsz() {
		return dyhh_hjzmsz;
	}

	public void setDyhh_hjzmsz(String dyhh_hjzmsz) {
		this.dyhh_hjzmsz = dyhh_hjzmsz;
	}

	public String getDyhyzk_hjzmsz() {
		return dyhyzk_hjzmsz;
	}

	public void setDyhyzk_hjzmsz(String dyhyzk_hjzmsz) {
		this.dyhyzk_hjzmsz = dyhyzk_hjzmsz;
	}

	public String getDybyqk_hjzmsz() {
		return dybyqk_hjzmsz;
	}

	public void setDybyqk_hjzmsz(String dybyqk_hjzmsz) {
		this.dybyqk_hjzmsz = dybyqk_hjzmsz;
	}

	public String getDywhcd_hjzmsz() {
		return dywhcd_hjzmsz;
	}

	public void setDywhcd_hjzmsz(String dywhcd_hjzmsz) {
		this.dywhcd_hjzmsz = dywhcd_hjzmsz;
	}

	public String getYxts() {
		return yxts;
	}

	public void setYxts(String yxts) {
		this.yxts = yxts;
	}

	public String getDyjmsfzsqb_lsbz() {
		return dyjmsfzsqb_lsbz;
	}

	public void setDyjmsfzsqb_lsbz(String dyjmsfzsqb_lsbz) {
		this.dyjmsfzsqb_lsbz = dyjmsfzsqb_lsbz;
	}

	public String getSfzlqdxgnr_lsbz() {
		return sfzlqdxgnr_lsbz;
	}

	public void setSfzlqdxgnr_lsbz(String sfzlqdxgnr_lsbz) {
		this.sfzlqdxgnr_lsbz = sfzlqdxgnr_lsbz;
	}

	public String getQyzbh() {
		return qyzbh;
	}

	public void setQyzbh(String qyzbh) {
		this.qyzbh = qyzbh;
	}

	public String getZqzbh() {
		return zqzbh;
	}

	public void setZqzbh(String zqzbh) {
		this.zqzbh = zqzbh;
	}

	public String getDkqlx() {
		return dkqlx;
	}

	public void setDkqlx(String dkqlx) {
		this.dkqlx = dkqlx;
	}

	public String getYznf() {
		return yznf;
	}

	public void setYznf(String yznf) {
		this.yznf = yznf;
	}

	public int getHjsysf() {
		return hjsysf;
	}

	public void setHjsysf(int hjsysf) {
		this.hjsysf = hjsysf;
	}

	public int getZqzsf() {
		return zqzsf;
	}

	public void setZqzsf(int zqzsf) {
		this.zqzsf = zqzsf;
	}

	public int getQyzsf() {
		return qyzsf;
	}

	public void setQyzsf(int qyzsf) {
		this.qyzsf = qyzsf;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}