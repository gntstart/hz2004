package com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import sun.misc.BASE64Encoder;

import java.sql.Timestamp;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="HZ_ZJ_BS" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoHZ_ZJ_SB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/*
	*ID，唯一标识
	*/
	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	id;
	private String	sqrxm;
	private String	sqrsfz;
	private String	sqrlxdh;
	private String	sqrlxdz;
	private String	ywlb;
	private String	bsqrxm;
	private String	bsqrsfz;
	private String	czdw;
	private String	czyh;
	private Timestamp	czsj;
	private String	lhbs;
	private String	lhsfz;
	private String	lhdz;
	private String	sbzt;
	private String	clbs;
	private Timestamp	clsj;
	private String	pch;
	private String	ywlbm;
	private String	nbgxm;
	private String	cym;
	private String	csyxzmbh;
	private String	bsqrxb;
	private String	bsqrmz;
	private String	bsqrcsrq;
	private String	btkrgx;
	private String	bsqrsjhm;
	private String	zxyy;
	private String	qcdbm;
	private String	kdqqy_qrdqbm;
	private String	kdqqy_qrywid;
	private String	kdqqy_qrywlx;
	private String	kdqqy_qrfkzt;
	private String	kdqqy_qrfksj;
	private String	kdqqy_zqzbh;
	private String	kdqqy_qrdqh;
	private String	kdqqy_qyzbh;
	private String	kdqqy_lscxfldm;
	private String	kdqqy_qyldyy;
	private String	kdqqy_qclb;
	private String	sfzqr;
	private String	sbid;
	@Transient
	private String 	czdw_mc;
	@Transient
	private Map<String,String> zbmap = null;
	
	/**
		2018.07.30
		新增	刁杰
		户政业务处理对接新增字段
	 */
	
	private String   mz;
	private String   hjbllb;
	private String   swzxlb;
	private String   xb;
	private String   yhzgx;
	private String   qcdssxq;
	private String   bdfw;
	private String   csrq;
	private String   csdssxq;
	private String   cxfldm;
	private String   hb;
	private String   csdjlb;
	private String   jgssxq;
	private String   hjsclb;
	private String   zzbdlb;
	private String   jhryxm;
	private String   fqxm;
	private String   ywdm;
	private String   zqzbh;
	private String   qyldyy;
	private String   jhryjhgx;
	private String   zylb;
	private String   sg;
	private String   sbrjtgx;
	private String   bdhhb;
	private String   whcd;
	private String   jndj_pdbz;
	private String   ncjdzzyxbys_pdbz;
	private String   ncjsbtcxy_pdbz;
	private String   hlx;
	private String   cszmbh;
	private String   zczjyhjzwnys_pdbz;
	private String   zy;
	private String   jjqx_pdbz;
	private String   fwcs;
	private String   qyzbh;
	private String   nyzyrklhczyydm;
	private String   zyjszc_pdbz;
	private String   bdhjth;
	private String   qwdssxq;
	private String   qrqhb;
	private String   qrlb;
	private String   dhhm;
	private String   jhrygmsfhm;
	private String   jthzl;
	private String   qclb;
	//add by zjm  20190226  中间表新增字段   迁入登记业务需要带值的字段
	private String   splx;
	private String  hkszqx;
	private String  hkdjjg;
	private String  zzxz;
	private String  ysqrgx;
	private String  sqrzzssxq;
	private String  sqrhkdjjg;
	//add by zjm  20190226  中间表新增字段   迁入登记业务需要带值的字段
	private String  fqgmsfhm;
	private String  mqxm;
	private String  mqgmsfhm;
	private String  jhrejhgx;
	private String  jhrexm;
	private String  jhregmsfhm;
	
	private String cszqfrq;//add by zjm  20190712  中间表新增字段   出生证签发时间字段
	private String cssj;//add by zjm  20190712  中间表新增字段   出生时间字段
	private String swrq;//add by zjm  20190712  中间表新增字段   死亡日期字段
	private String swzmbh;//add by zjm  20190712  中间表新增字段   死亡证明编号字段
	/*
	 * add by zjm 20190719 中间表增加字段
	 */
	private String zjxy;
	private String hyzk;
	private String byzk;
	private String xx;
	private String qfjg;
	private String zjlb;
	private String yxqxqsrq;
	private String yxqxjzrq;
	private String jlx;
	private String mlph;
	private String jcwh;
	private Long   hjywid;// add by zjm 20190830 中间表新增字段 户籍业务ID
	private Long   spywid;// add by zjm 20190902 中间表新增字段 审批户籍业务ID  
	private String hjdpcs;// add by zjm 20190902 中间表新增字段 户籍地派出所 
	private String	blrsfz;// add by zjm 20190904 中间表新增字段 办理人身份证 
	private String	nbggmsfhm;// add by zjm 20190916 中间表新增字段 拟变更身份证 
	private String	csdxz;// add by zjm 20190916 中间表新增字段 出生地详址 
	private String	xxjb;// add by zjm 20190916 中间表新增字段  信息级别 
	private String	rylb;// add by zjm 20190916 中间表新增字段  人员类别
	private String	bz;// add by zjm 20190916 中间表新增字段  备注  
	private String	qwdxz;// add by zjm 20190916 中间表新增字段  迁往地详址  
	private String	qcdxz;// add by zjm 20190916 中间表新增字段  迁出地详址  
	private String 	wx_code;//add by zjm 20191030 微信唯一标识（航信）
	private byte[]	rkzp;//201911新增字段，人员照片采集
	@Transient
	private String	rkzpBase64;

	private String hsql;//何时迁来
	private String hyql;//何因迁来
	private String hssxqql;//何省市县迁来
	private String hxzql;//何祥址迁来
	private String pj;//1非常满意2满意3基本满意4不满意
	private String fqzjlb;//202007新增父亲证件种类
	private String fqzjhm;//202007新增父亲证件号码
	private String mqzjlb;//202007新增母亲证件种类
	private String mqzjhm;//202007新增母亲证件号码
	private String jhryzjlb;//202007新增监护人一证件种类
	private String jhryzjhm;//202007新增监护人一证件号码
	private String jhrezjlb;//202007新增监护人二证件种类
	private String jhrezjhm;//202007新增监护人二证件号码
	private String fqwwx;//202007新增父亲外文姓
	private String fqwwm;//202007新增父亲外文名
	private String mqwwx;//202007新增母亲外文姓
	private String mqwwm;//202007新增母亲外文名
	private String jhrywwx;//202007新增监护人一外文姓
	private String jhrywwm;//202007新增监护人一外文名
	private String jhrewwx;//202007新增监护人二外文姓
	private String jhrewwm;//202007新增监护人二外文名
	private String fjid;//202007辅警ID
	private String fjdy;//202007辅警打印
	public String getHsql() {
		return hsql;
	}

	public void setHsql(String hsql) {
		this.hsql = hsql;
	}

	public String getHyql() {
		return hyql;
	}

	public void setHyql(String hyql) {
		this.hyql = hyql;
	}

	public String getHssxqql() {
		return hssxqql;
	}

	public void setHssxqql(String hssxqql) {
		this.hssxqql = hssxqql;
	}

	public String getHxzql() {
		return hxzql;
	}

	public void setHxzql(String hxzql) {
		this.hxzql = hxzql;
	}

	public Map<String, String> getZbmap() {
		return zbmap;
	}

	public void setZbmap(Map<String, String> zbmap) {
		this.zbmap = zbmap;
	}

	public PoHZ_ZJ_SB(){}

	public String getCzdw_mc() {
		return czdw_mc;
	}

	public void setCzdw_mc(String czdw_mc) {
		this.czdw_mc = czdw_mc;
	}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id=id;
	}

	public String getSqrxm(){
		return this.sqrxm;
	}

	public void setSqrxm(String sqrxm){
		this.sqrxm=sqrxm;
	}

	public String getSqrsfz(){
		return this.sqrsfz;
	}

	public void setSqrsfz(String sqrsfz){
		this.sqrsfz=sqrsfz;
	}

	public String getSqrlxdh(){
		return this.sqrlxdh;
	}

	public void setSqrlxdh(String sqrlxdh){
		this.sqrlxdh=sqrlxdh;
	}

	public String getSqrlxdz(){
		return this.sqrlxdz;
	}

	public void setSqrlxdz(String sqrlxdz){
		this.sqrlxdz=sqrlxdz;
	}

	public String getYwlb(){
		return this.ywlb;
	}

	public void setYwlb(String ywlb){
		this.ywlb=ywlb;
	}

	public String getBsqrxm(){
		return this.bsqrxm;
	}

	public void setBsqrxm(String bsqrxm){
		this.bsqrxm=bsqrxm;
	}

	public String getBsqrsfz(){
		return this.bsqrsfz;
	}

	public void setBsqrsfz(String bsqrsfz){
		this.bsqrsfz=bsqrsfz;
	}

	public String getCzdw(){
		return this.czdw;
	}

	public void setCzdw(String czdw){
		this.czdw=czdw;
	}

	public String getCzyh(){
		return this.czyh;
	}

	public void setCzyh(String czyh){
		this.czyh=czyh;
	}

	public Timestamp getCzsj(){
		return this.czsj;
	}

	public void setCzsj(Timestamp czsj){
		this.czsj=czsj;
	}

	public String getLhbs(){
		return this.lhbs;
	}

	public void setLhbs(String lhbs){
		this.lhbs=lhbs;
	}

	public String getLhsfz(){
		return this.lhsfz;
	}

	public void setLhsfz(String lhsfz){
		this.lhsfz=lhsfz;
	}

	public String getLhdz(){
		return this.lhdz;
	}

	public void setLhdz(String lhdz){
		this.lhdz=lhdz;
	}

	public String getSbzt(){
		return this.sbzt;
	}

	public void setSbzt(String sbzt){
		this.sbzt=sbzt;
	}

	public String getClbs(){
		return this.clbs;
	}

	public void setClbs(String clbs){
		this.clbs=clbs;
	}

	public Timestamp getClsj(){
		return this.clsj;
	}

	public void setClsj(Timestamp clsj){
		this.clsj=clsj;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getYwlbm(){
		return this.ywlbm;
	}

	public void setYwlbm(String ywlbm){
		this.ywlbm=ywlbm;
	}

	public String getNbgxm(){
		return this.nbgxm;
	}

	public void setNbgxm(String nbgxm){
		this.nbgxm=nbgxm;
	}

	public String getCym(){
		return this.cym;
	}

	public void setCym(String cym){
		this.cym=cym;
	}

	public String getCsyxzmbh(){
		return this.csyxzmbh;
	}

	public void setCsyxzmbh(String csyxzmbh){
		this.csyxzmbh=csyxzmbh;
	}

	public String getBsqrxb(){
		return this.bsqrxb;
	}

	public void setBsqrxb(String bsqrxb){
		this.bsqrxb=bsqrxb;
	}

	public String getBsqrmz(){
		return this.bsqrmz;
	}

	public void setBsqrmz(String bsqrmz){
		this.bsqrmz=bsqrmz;
	}

	public String getBsqrcsrq(){
		return this.bsqrcsrq;
	}

	public void setBsqrcsrq(String bsqrcsrq){
		this.bsqrcsrq=bsqrcsrq;
	}

	public String getBtkrgx(){
		return this.btkrgx;
	}

	public void setBtkrgx(String btkrgx){
		this.btkrgx=btkrgx;
	}

	public String getBsqrsjhm(){
		return this.bsqrsjhm;
	}

	public void setBsqrsjhm(String bsqrsjhm){
		this.bsqrsjhm=bsqrsjhm;
	}

	public String getZxyy(){
		return this.zxyy;
	}

	public void setZxyy(String zxyy){
		this.zxyy=zxyy;
	}

	public String getQcdbm(){
		return this.qcdbm;
	}

	public void setQcdbm(String qcdbm){
		this.qcdbm=qcdbm;
	}

	public String getKdqqy_qrdqbm(){
		return this.kdqqy_qrdqbm;
	}

	public void setKdqqy_qrdqbm(String kdqqy_qrdqbm){
		this.kdqqy_qrdqbm=kdqqy_qrdqbm;
	}

	public String getKdqqy_qrywid(){
		return this.kdqqy_qrywid;
	}

	public void setKdqqy_qrywid(String kdqqy_qrywid){
		this.kdqqy_qrywid=kdqqy_qrywid;
	}

	public String getKdqqy_qrywlx(){
		return this.kdqqy_qrywlx;
	}

	public void setKdqqy_qrywlx(String kdqqy_qrywlx){
		this.kdqqy_qrywlx=kdqqy_qrywlx;
	}

	public String getKdqqy_qrfkzt(){
		return this.kdqqy_qrfkzt;
	}

	public void setKdqqy_qrfkzt(String kdqqy_qrfkzt){
		this.kdqqy_qrfkzt=kdqqy_qrfkzt;
	}

	public String getKdqqy_qrfksj(){
		return this.kdqqy_qrfksj;
	}

	public void setKdqqy_qrfksj(String kdqqy_qrfksj){
		this.kdqqy_qrfksj=kdqqy_qrfksj;
	}

	public String getKdqqy_zqzbh(){
		return this.kdqqy_zqzbh;
	}

	public void setKdqqy_zqzbh(String kdqqy_zqzbh){
		this.kdqqy_zqzbh=kdqqy_zqzbh;
	}

	public String getKdqqy_qrdqh(){
		return this.kdqqy_qrdqh;
	}

	public void setKdqqy_qrdqh(String kdqqy_qrdqh){
		this.kdqqy_qrdqh=kdqqy_qrdqh;
	}

	public String getKdqqy_qyzbh(){
		return this.kdqqy_qyzbh;
	}

	public void setKdqqy_qyzbh(String kdqqy_qyzbh){
		this.kdqqy_qyzbh=kdqqy_qyzbh;
	}

	public String getKdqqy_lscxfldm(){
		return this.kdqqy_lscxfldm;
	}

	public void setKdqqy_lscxfldm(String kdqqy_lscxfldm){
		this.kdqqy_lscxfldm=kdqqy_lscxfldm;
	}

	public String getKdqqy_qyldyy(){
		return this.kdqqy_qyldyy;
	}

	public void setKdqqy_qyldyy(String kdqqy_qyldyy){
		this.kdqqy_qyldyy=kdqqy_qyldyy;
	}

	public String getKdqqy_qclb(){
		return this.kdqqy_qclb;
	}

	public void setKdqqy_qclb(String kdqqy_qclb){
		this.kdqqy_qclb=kdqqy_qclb;
	}

	public String getSfzqr(){
		return this.sfzqr;
	}

	public void setSfzqr(String sfzqr){
		this.sfzqr=sfzqr;
	}

	public String getSbid(){
		return this.sbid;
	}

	public void setSbid(String sbid){
		this.sbid=sbid;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getHjbllb() {
		return hjbllb;
	}

	public void setHjbllb(String hjbllb) {
		this.hjbllb = hjbllb;
	}

	public String getSwzxlb() {
		return swzxlb;
	}

	public void setSwzxlb(String swzxlb) {
		this.swzxlb = swzxlb;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getYhzgx() {
		return yhzgx;
	}

	public void setYhzgx(String yhzgx) {
		this.yhzgx = yhzgx;
	}

	public String getQcdssxq() {
		return qcdssxq;
	}

	public void setQcdssxq(String qcdssxq) {
		this.qcdssxq = qcdssxq;
	}

	public String getBdfw() {
		return bdfw;
	}

	public void setBdfw(String bdfw) {
		this.bdfw = bdfw;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getCsdssxq() {
		return csdssxq;
	}

	public void setCsdssxq(String csdssxq) {
		this.csdssxq = csdssxq;
	}

	public String getCxfldm() {
		return cxfldm;
	}

	public void setCxfldm(String cxfldm) {
		this.cxfldm = cxfldm;
	}

	public String getHb() {
		return hb;
	}

	public void setHb(String hb) {
		this.hb = hb;
	}

	public String getCsdjlb() {
		return csdjlb;
	}

	public void setCsdjlb(String csdjlb) {
		this.csdjlb = csdjlb;
	}

	public String getJgssxq() {
		return jgssxq;
	}

	public void setJgssxq(String jgssxq) {
		this.jgssxq = jgssxq;
	}

	public String getHjsclb() {
		return hjsclb;
	}

	public void setHjsclb(String hjsclb) {
		this.hjsclb = hjsclb;
	}

	public String getZzbdlb() {
		return zzbdlb;
	}

	public void setZzbdlb(String zzbdlb) {
		this.zzbdlb = zzbdlb;
	}

	public String getJhryxm() {
		return jhryxm;
	}

	public void setJhryxm(String jhryxm) {
		this.jhryxm = jhryxm;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getYwdm() {
		return ywdm;
	}

	public void setYwdm(String ywdm) {
		this.ywdm = ywdm;
	}

	public String getZqzbh() {
		return zqzbh;
	}

	public void setZqzbh(String zqzbh) {
		this.zqzbh = zqzbh;
	}

	public String getQyldyy() {
		return qyldyy;
	}

	public void setQyldyy(String qyldyy) {
		this.qyldyy = qyldyy;
	}

	public String getJhryjhgx() {
		return jhryjhgx;
	}

	public void setJhryjhgx(String jhryjhgx) {
		this.jhryjhgx = jhryjhgx;
	}

	public String getZylb() {
		return zylb;
	}

	public void setZylb(String zylb) {
		this.zylb = zylb;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getSbrjtgx() {
		return sbrjtgx;
	}

	public void setSbrjtgx(String sbrjtgx) {
		this.sbrjtgx = sbrjtgx;
	}

	public String getBdhhb() {
		return bdhhb;
	}

	public void setBdhhb(String bdhhb) {
		this.bdhhb = bdhhb;
	}

	public String getWhcd() {
		return whcd;
	}

	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}

	public String getJndj_pdbz() {
		return jndj_pdbz;
	}

	public void setJndj_pdbz(String jndj_pdbz) {
		this.jndj_pdbz = jndj_pdbz;
	}

	public String getNcjdzzyxbys_pdbz() {
		return ncjdzzyxbys_pdbz;
	}

	public void setNcjdzzyxbys_pdbz(String ncjdzzyxbys_pdbz) {
		this.ncjdzzyxbys_pdbz = ncjdzzyxbys_pdbz;
	}

	public String getNcjsbtcxy_pdbz() {
		return ncjsbtcxy_pdbz;
	}

	public void setNcjsbtcxy_pdbz(String ncjsbtcxy_pdbz) {
		this.ncjsbtcxy_pdbz = ncjsbtcxy_pdbz;
	}

	public String getHlx() {
		return hlx;
	}

	public void setHlx(String hlx) {
		this.hlx = hlx;
	}

	public String getCszmbh() {
		return cszmbh;
	}

	public void setCszmbh(String cszmbh) {
		this.cszmbh = cszmbh;
	}

	public String getZczjyhjzwnys_pdbz() {
		return zczjyhjzwnys_pdbz;
	}

	public void setZczjyhjzwnys_pdbz(String zczjyhjzwnys_pdbz) {
		this.zczjyhjzwnys_pdbz = zczjyhjzwnys_pdbz;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getJjqx_pdbz() {
		return jjqx_pdbz;
	}

	public void setJjqx_pdbz(String jjqx_pdbz) {
		this.jjqx_pdbz = jjqx_pdbz;
	}

	public String getFwcs() {
		return fwcs;
	}

	public void setFwcs(String fwcs) {
		this.fwcs = fwcs;
	}

	public String getQyzbh() {
		return qyzbh;
	}

	public void setQyzbh(String qyzbh) {
		this.qyzbh = qyzbh;
	}

	public String getNyzyrklhczyydm() {
		return nyzyrklhczyydm;
	}

	public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
		this.nyzyrklhczyydm = nyzyrklhczyydm;
	}

	public String getZyjszc_pdbz() {
		return zyjszc_pdbz;
	}

	public void setZyjszc_pdbz(String zyjszc_pdbz) {
		this.zyjszc_pdbz = zyjszc_pdbz;
	}

	public String getBdhjth() {
		return bdhjth;
	}

	public void setBdhjth(String bdhjth) {
		this.bdhjth = bdhjth;
	}

	public String getQwdssxq() {
		return qwdssxq;
	}

	public void setQwdssxq(String qwdssxq) {
		this.qwdssxq = qwdssxq;
	}

	public String getQrqhb() {
		return qrqhb;
	}

	public void setQrqhb(String qrqhb) {
		this.qrqhb = qrqhb;
	}

	public String getQrlb() {
		return qrlb;
	}

	public void setQrlb(String qrlb) {
		this.qrlb = qrlb;
	}

	public String getDhhm() {
		return dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	public String getJhrygmsfhm() {
		return jhrygmsfhm;
	}

	public void setJhrygmsfhm(String jhrygmsfhm) {
		this.jhrygmsfhm = jhrygmsfhm;
	}

	public String getJthzl() {
		return jthzl;
	}

	public void setJthzl(String jthzl) {
		this.jthzl = jthzl;
	}

	public String getQclb() {
		return qclb;
	}

	public void setQclb(String qclb) {
		this.qclb = qclb;
	}

	public String getSplx() {
		return splx;
	}

	public void setSplx(String splx) {
		this.splx = splx;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}

	public String getHkszqx() {
		return hkszqx;
	}

	public void setHkszqx(String hkszqx) {
		this.hkszqx = hkszqx;
	}

	public String getHkdjjg() {
		return hkdjjg;
	}

	public void setHkdjjg(String hkdjjg) {
		this.hkdjjg = hkdjjg;
	}

	public String getZzxz() {
		return zzxz;
	}

	public void setZzxz(String zzxz) {
		this.zzxz = zzxz;
	}

	public String getYsqrgx() {
		return ysqrgx;
	}

	public void setYsqrgx(String ysqrgx) {
		this.ysqrgx = ysqrgx;
	}

	public String getSqrzzssxq() {
		return sqrzzssxq;
	}

	public void setSqrzzssxq(String sqrzzssxq) {
		this.sqrzzssxq = sqrzzssxq;
	}

	public String getSqrhkdjjg() {
		return sqrhkdjjg;
	}

	public void setSqrhkdjjg(String sqrhkdjjg) {
		this.sqrhkdjjg = sqrhkdjjg;
	}

	public String getFqgmsfhm() {
		return fqgmsfhm;
	}

	public void setFqgmsfhm(String fqgmsfhm) {
		this.fqgmsfhm = fqgmsfhm;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getMqgmsfhm() {
		return mqgmsfhm;
	}

	public void setMqgmsfhm(String mqgmsfhm) {
		this.mqgmsfhm = mqgmsfhm;
	}

	public String getJhrejhgx() {
		return jhrejhgx;
	}

	public void setJhrejhgx(String jhrejhgx) {
		this.jhrejhgx = jhrejhgx;
	}

	public String getJhrexm() {
		return jhrexm;
	}

	public void setJhrexm(String jhrexm) {
		this.jhrexm = jhrexm;
	}

	public String getJhregmsfhm() {
		return jhregmsfhm;
	}

	public void setJhregmsfhm(String jhregmsfhm) {
		this.jhregmsfhm = jhregmsfhm;
	}

	public String getCszqfrq() {
		return cszqfrq;
	}

	public void setCszqfrq(String cszqfrq) {
		this.cszqfrq = cszqfrq;
	}

	public String getCssj() {
		return cssj;
	}

	public void setCssj(String cssj) {
		this.cssj = cssj;
	}

	public String getSwrq() {
		return swrq;
	}

	public void setSwrq(String swrq) {
		this.swrq = swrq;
	}

	public String getSwzmbh() {
		return swzmbh;
	}

	public void setSwzmbh(String swzmbh) {
		this.swzmbh = swzmbh;
	}

	public String getZjxy() {
		return zjxy;
	}

	public void setZjxy(String zjxy) {
		this.zjxy = zjxy;
	}

	public String getHyzk() {
		return hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getByzk() {
		return byzk;
	}

	public void setByzk(String byzk) {
		this.byzk = byzk;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getQfjg() {
		return qfjg;
	}

	public void setQfjg(String qfjg) {
		this.qfjg = qfjg;
	}

	public String getZjlb() {
		return zjlb;
	}

	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
	}

	public String getYxqxqsrq() {
		return yxqxqsrq;
	}

	public void setYxqxqsrq(String yxqxqsrq) {
		this.yxqxqsrq = yxqxqsrq;
	}

	public String getYxqxjzrq() {
		return yxqxjzrq;
	}

	public void setYxqxjzrq(String yxqxjzrq) {
		this.yxqxjzrq = yxqxjzrq;
	}

	public String getJlx() {
		return jlx;
	}

	public void setJlx(String jlx) {
		this.jlx = jlx;
	}

	public String getMlph() {
		return mlph;
	}

	public void setMlph(String mlph) {
		this.mlph = mlph;
	}

	public String getJcwh() {
		return jcwh;
	}

	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}

	public Long getHjywid() {
		return hjywid;
	}

	public void setHjywid(Long hjywid) {
		this.hjywid = hjywid;
	}

	public Long getSpywid() {
		return spywid;
	}

	public void setSpywid(Long spywid) {
		this.spywid = spywid;
	}

	public String getHjdpcs() {
		return hjdpcs;
	}

	public void setHjdpcs(String hjdpcs) {
		this.hjdpcs = hjdpcs;
	}

	public String getBlrsfz() {
		return blrsfz;
	}

	public void setBlrsfz(String blrsfz) {
		this.blrsfz = blrsfz;
	}

	public String getNbggmsfhm() {
		return nbggmsfhm;
	}

	public void setNbggmsfhm(String nbggmsfhm) {
		this.nbggmsfhm = nbggmsfhm;
	}

	public String getCsdxz() {
		return csdxz;
	}

	public void setCsdxz(String csdxz) {
		this.csdxz = csdxz;
	}

	public String getXxjb() {
		return xxjb;
	}

	public void setXxjb(String xxjb) {
		this.xxjb = xxjb;
	}

	public String getRylb() {
		return rylb;
	}

	public void setRylb(String rylb) {
		this.rylb = rylb;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getQwdxz() {
		return qwdxz;
	}

	public void setQwdxz(String qwdxz) {
		this.qwdxz = qwdxz;
	}

	public String getQcdxz() {
		return qcdxz;
	}

	public void setQcdxz(String qcdxz) {
		this.qcdxz = qcdxz;
	}

	public String getWx_code() {
		return wx_code;
	}

	public void setWx_code(String wx_code) {
		this.wx_code = wx_code;
	}

	public byte[] getRkzp() {
		return rkzp;
	}

	public void setRkzp(byte[] rkzp) {
		this.rkzp = rkzp;
	}

	public String getRkzpBase64() {
		return rkzpBase64;
	}

	public void setRkzpBase64(String rkzpBase64) {
		this.rkzpBase64 = rkzpBase64;
	}

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getFqzjlb() {
		return fqzjlb;
	}

	public void setFqzjlb(String fqzjlb) {
		this.fqzjlb = fqzjlb;
	}

	public String getFqzjhm() {
		return fqzjhm;
	}

	public void setFqzjhm(String fqzjhm) {
		this.fqzjhm = fqzjhm;
	}

	public String getMqzjlb() {
		return mqzjlb;
	}

	public void setMqzjlb(String mqzjlb) {
		this.mqzjlb = mqzjlb;
	}

	public String getMqzjhm() {
		return mqzjhm;
	}

	public void setMqzjhm(String mqzjhm) {
		this.mqzjhm = mqzjhm;
	}

	public String getJhryzjlb() {
		return jhryzjlb;
	}

	public void setJhryzjlb(String jhryzjlb) {
		this.jhryzjlb = jhryzjlb;
	}

	public String getJhryzjhm() {
		return jhryzjhm;
	}

	public void setJhryzjhm(String jhryzjhm) {
		this.jhryzjhm = jhryzjhm;
	}

	public String getJhrezjlb() {
		return jhrezjlb;
	}

	public void setJhrezjlb(String jhrezjlb) {
		this.jhrezjlb = jhrezjlb;
	}

	public String getJhrezjhm() {
		return jhrezjhm;
	}

	public void setJhrezjhm(String jhrezjhm) {
		this.jhrezjhm = jhrezjhm;
	}

	public String getFqwwx() {
		return fqwwx;
	}

	public void setFqwwx(String fqwwx) {
		this.fqwwx = fqwwx;
	}

	public String getFqwwm() {
		return fqwwm;
	}

	public void setFqwwm(String fqwwm) {
		this.fqwwm = fqwwm;
	}

	public String getMqwwx() {
		return mqwwx;
	}

	public void setMqwwx(String mqwwx) {
		this.mqwwx = mqwwx;
	}

	public String getMqwwm() {
		return mqwwm;
	}

	public void setMqwwm(String mqwwm) {
		this.mqwwm = mqwwm;
	}

	public String getJhrywwx() {
		return jhrywwx;
	}

	public void setJhrywwx(String jhrywwx) {
		this.jhrywwx = jhrywwx;
	}

	public String getJhrywwm() {
		return jhrywwm;
	}

	public void setJhrywwm(String jhrywwm) {
		this.jhrywwm = jhrywwm;
	}

	public String getJhrewwx() {
		return jhrewwx;
	}

	public void setJhrewwx(String jhrewwx) {
		this.jhrewwx = jhrewwx;
	}

	public String getJhrewwm() {
		return jhrewwm;
	}

	public void setJhrewwm(String jhrewwm) {
		this.jhrewwm = jhrewwm;
	}

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getFjdy() {
		return fjdy;
	}

	public void setFjdy(String fjdy) {
		this.fjdy = fjdy;
	}

	
}