package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
*常住人口基本信息表
*/
@Entity
@Table(name="ECHARTSDATA" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoECHARTSDATA implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*人员内部ID
	*/
	private String	ssqhdm;//所属区划代码
	private String	ssqhmc;//所属区划名称
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private String	qhdm;//区划名称
	private String	qhmc;//区划名称
	private Integer  zrks;//总人口数
	private Integer  zhs;//总户数
	private Integer	nxrk;//男性人口
	private Integer	nvrk;//女性人口
	private Integer  cslr;//出生流入
	private Integer  bllr;//补录流入
	private Integer	qrlr;//迁入流入
	private Integer	swlc;//死亡流出
	private Integer  qclc;//迁出流出
	private Integer  sclc;//删除流出
	private Integer	nl_level1;//年龄分级1 0~20
	private Integer	nl_level2;//年龄分级2 21~40
	private Integer  nl_level3;//年龄分级3 41~60
	private Integer  nl_level4;//年龄分级4 61~80
	private Integer  nl_level5;//年龄分级5 80以上
	private Integer	lrxl_level1;//流入学历1 文盲或半文盲
	private Integer	lrxl_level2;//流入学历2 小学
	private Integer  lrxl_level3;//流入学历3 初中
	private Integer  lrxl_level4;//流入学历4 高中
	private Integer  lrxl_level5;//流入学历5 技校
	private Integer  lrxl_level6;//流入学历6 中专
	private Integer  lrxl_level7;//流入学历7 大专
	private Integer  lrxl_level8;//流入学历8 本科
	private Integer  lrxl_level9;//流入学历9 研究生已以上
	private Integer  lrxl_level10;//流入学历10 其他
	@Transient
	private List<PoSSXQQRSFFBB> xzqhList;
	@Transient
	public List<PoSSXQQRSFFBB> getXzqhList() {
		return xzqhList;
	}

	public void setXzqhList(List<PoSSXQQRSFFBB> xzqhList) {
		this.xzqhList = xzqhList;
	}
	
	public PoECHARTSDATA(){}

	public String getSsqhdm() {
		return ssqhdm;
	}

	public void setSsqhdm(String ssqhdm) {
		this.ssqhdm = ssqhdm;
	}

	public String getSsqhmc() {
		return ssqhmc;
	}

	public void setSsqhmc(String ssqhmc) {
		this.ssqhmc = ssqhmc;
	}

	public String getQhdm() {
		return qhdm;
	}

	public void setQhdm(String qhdm) {
		this.qhdm = qhdm;
	}

	public String getQhmc() {
		return qhmc;
	}

	public void setQhmc(String qhmc) {
		this.qhmc = qhmc;
	}

	public Integer getZrks() {
		return zrks;
	}

	public void setZrks(Integer zrks) {
		this.zrks = zrks;
	}

	public Integer getZhs() {
		return zhs;
	}

	public void setZhs(Integer zhs) {
		this.zhs = zhs;
	}

	public Integer getNxrk() {
		return nxrk;
	}

	public void setNxrk(Integer nxrk) {
		this.nxrk = nxrk;
	}

	public Integer getNvrk() {
		return nvrk;
	}

	public void setNvrk(Integer nvrk) {
		this.nvrk = nvrk;
	}

	public Integer getCslr() {
		return cslr;
	}

	public void setCslr(Integer cslr) {
		this.cslr = cslr;
	}

	public Integer getBllr() {
		return bllr;
	}

	public void setBllr(Integer bllr) {
		this.bllr = bllr;
	}

	public Integer getQrlr() {
		return qrlr;
	}

	public void setQrlr(Integer qrlr) {
		this.qrlr = qrlr;
	}

	public Integer getSwlc() {
		return swlc;
	}

	public void setSwlc(Integer swlc) {
		this.swlc = swlc;
	}

	public Integer getQclc() {
		return qclc;
	}

	public void setQclc(Integer qclc) {
		this.qclc = qclc;
	}

	public Integer getSclc() {
		return sclc;
	}

	public void setSclc(Integer sclc) {
		this.sclc = sclc;
	}

	public Integer getNl_level1() {
		return nl_level1;
	}

	public void setNl_level1(Integer nl_level1) {
		this.nl_level1 = nl_level1;
	}

	public Integer getNl_level2() {
		return nl_level2;
	}

	public void setNl_level2(Integer nl_level2) {
		this.nl_level2 = nl_level2;
	}

	public Integer getNl_level3() {
		return nl_level3;
	}

	public void setNl_level3(Integer nl_level3) {
		this.nl_level3 = nl_level3;
	}

	public Integer getNl_level4() {
		return nl_level4;
	}

	public void setNl_level4(Integer nl_level4) {
		this.nl_level4 = nl_level4;
	}

	public Integer getNl_level5() {
		return nl_level5;
	}

	public void setNl_level5(Integer nl_level5) {
		this.nl_level5 = nl_level5;
	}

	public Integer getLrxl_level1() {
		return lrxl_level1;
	}

	public void setLrxl_level1(Integer lrxl_level1) {
		this.lrxl_level1 = lrxl_level1;
	}

	public Integer getLrxl_level2() {
		return lrxl_level2;
	}

	public void setLrxl_level2(Integer lrxl_level2) {
		this.lrxl_level2 = lrxl_level2;
	}

	public Integer getLrxl_level3() {
		return lrxl_level3;
	}

	public void setLrxl_level3(Integer lrxl_level3) {
		this.lrxl_level3 = lrxl_level3;
	}

	public Integer getLrxl_level4() {
		return lrxl_level4;
	}

	public void setLrxl_level4(Integer lrxl_level4) {
		this.lrxl_level4 = lrxl_level4;
	}

	public Integer getLrxl_level5() {
		return lrxl_level5;
	}

	public void setLrxl_level5(Integer lrxl_level5) {
		this.lrxl_level5 = lrxl_level5;
	}

	public Integer getLrxl_level6() {
		return lrxl_level6;
	}

	public void setLrxl_level6(Integer lrxl_level6) {
		this.lrxl_level6 = lrxl_level6;
	}

	public Integer getLrxl_level7() {
		return lrxl_level7;
	}

	public void setLrxl_level7(Integer lrxl_level7) {
		this.lrxl_level7 = lrxl_level7;
	}

	public Integer getLrxl_level8() {
		return lrxl_level8;
	}

	public void setLrxl_level8(Integer lrxl_level8) {
		this.lrxl_level8 = lrxl_level8;
	}
	public void setLrxl_level9(Integer lrxl_level9) {
		this.lrxl_level9 = lrxl_level9;
	}
	public Integer getLrxl_level9() {
		return lrxl_level9;
	}

	public void setLrxl_level10(Integer lrxl_level10) {
		this.lrxl_level10 = lrxl_level10;
	}

	public Integer getLrxl_level10() {
		return lrxl_level10;
	}
	
	public static long getSerialversionuid() {			return serialVersionUID;	}}