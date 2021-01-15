package com.hzjc.hz2004.vo;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.*;

/**
 * 迁入审批信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrspxxHqFhxx
    extends DefaultVO {

  //户籍审批申请信息
  private Long spywid; //审批业务ID
  private String splx; //审批类型
  private String slrq; //受理日期
  private String xm; //姓名
  private Long ryid; //人员ID
  private String xb; //性别
  private String gmsfhm; //公民身份号码
  private String csrq; //出生日期
  private String mz;//民族
  private String jhny; //结婚年月
  private String yjrclx; //引进人才类型
  private String yrdwjcyrysj; //用人单位及从业人员时间
  private String lxdh; //联系电话
  private String byxx; //毕业学校
  private String whcd; //文化程度
  private String bysj; //毕业时间
  private String zyzl; //专业种类
  private String byzsh; //毕业证书号
  private String zyjszc; //专业技术职称
  private String jszsh; //技术证书号
  private String jsfzjg; //技术发证机关
  private String fmzlmc; //发明专利名称
  private String fmzlh; //发明专利号
  private String zlfzjg; //专利发证机关
  private String hb; //户别
  private String qrhhb; //迁入后户别
  private String zzssxq; //住址省市县区
  private String zzxz; //住址详址
  private String hkszddjjg; //户口所在地登记机关
  private String qrdqx; //迁入地区县
  private String qrdpcs; //迁入地派出所
  private String qrdjwzrq; //迁入地警务责任区
  private String qrdxzjd; //迁入地乡镇街道
  private String qrdjwh; //迁入地居委员会
  private String qrdjlx; //迁入地街路巷
  private String qrdmlph; //迁入地门楼牌号
  private String qrdz; //迁入地址
  private String qrdhkdjjg; //迁入地户口登记机关
  private Long qrdhhid; //迁入地户号ID
  private String qrdhlx; //迁入地户类型
  private String rlhbz; //入立户标志
  private String sqqrly; //申请迁入理由
  private String bz; //备注
  private Long djrid; //登记人ID
  private String djsj; //登记时间
  private Long xydzid; //下一动作ID
  private String spjg; //审批结果
  private String lsbz; //落实标志
  private Long rynbid; //人员内部ID
  private Long hjywid; //户籍业务ID

  private String sqrgmsfhm; //申请人公民身份号码
  private Long spmbid; //审批模板ID
  private String sqrxm; //申请人姓名
  private String sqrzzssxq; //申请人住址省市县区
  private String sqrzzxz; //申请人住址详址
  private String sqrhkdjjg; //申请人户口登记机关
  private String ysqrgx; //与申请人关系

  private String spsm; //审批说明

  //审批动作信息
  private Long dzid; //动作ID
  private String dzmc; //动作名称
  private String ms; //描述
  private String qybz; //启用标志

  //前台需要
  private Long sbhjywid; //上笔户籍业务ID
  private String cxfldm;
  private String nyzyrklhczyydm;
  private String kdqqy_qchjywid;
  private String kdqqy_qcdqbm;
  private String kdqqy_fksj;
  private String kdqqy_fkzt;
  private String kdqqy_fknr;
  private String kdqqy_czyxm;
  private String kdqqy_czydw;


  public VoQrspxxHqFhxx() {
  }

  public VoQrspxxHqFhxx(PoHJSP_HJSPSQB poHjspsqxx, PoXT_SPDZB poSpdzxx) {
    try {
      BeanUtils.copyProperties(this, poHjspsqxx);
      if (poSpdzxx != null) {
        BeanUtils.copyProperties(this, poSpdzxx);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public String getBysj() {
    return bysj;
  }

  public void setBysj(String bysj) {
    this.bysj = bysj;
  }

  public String getByxx() {
    return byxx;
  }

  public void setByxx(String byxx) {
    this.byxx = byxx;
  }

  public String getByzsh() {
    return byzsh;
  }

  public void setByzsh(String byzsh) {
    this.byzsh = byzsh;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getMz() {
    return mz;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public Long getDjrid() {
    return djrid;
  }

  public void setDjrid(Long djrid) {
    this.djrid = djrid;
  }

  public String getDjsj() {
    return djsj;
  }

  public void setDjsj(String djsj) {
    this.djsj = djsj;
  }

  public Long getDzid() {
    return dzid;
  }

  public void setDzid(Long dzid) {
    this.dzid = dzid;
  }

  public String getDzmc() {
    return dzmc;
  }

  public void setDzmc(String dzmc) {
    this.dzmc = dzmc;
  }

  public String getFmzlh() {
    return fmzlh;
  }

  public void setFmzlh(String fmzlh) {
    this.fmzlh = fmzlh;
  }

  public String getFmzlmc() {
    return fmzlmc;
  }

  public void setFmzlmc(String fmzlmc) {
    this.fmzlmc = fmzlmc;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public String getJhny() {
    return jhny;
  }

  public void setJhny(String jhny) {
    this.jhny = jhny;
  }

  public String getJsfzjg() {
    return jsfzjg;
  }

  public void setJsfzjg(String jsfzjg) {
    this.jsfzjg = jsfzjg;
  }

  public String getJszsh() {
    return jszsh;
  }

  public void setJszsh(String jszsh) {
    this.jszsh = jszsh;
  }

  public String getLsbz() {
    return lsbz;
  }

  public void setLsbz(String lsbz) {
    this.lsbz = lsbz;
  }

  public String getLxdh() {
    return lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  public String getMs() {
    return ms;
  }

  public void setMs(String ms) {
    this.ms = ms;
  }

  public String getQrdhkdjjg() {
    return qrdhkdjjg;
  }

  public void setQrdhkdjjg(String qrdhkdjjg) {
    this.qrdhkdjjg = qrdhkdjjg;
  }

  public String getQrdjlx() {
    return qrdjlx;
  }

  public void setQrdjlx(String qrdjlx) {
    this.qrdjlx = qrdjlx;
  }

  public String getQrdjwh() {
    return qrdjwh;
  }

  public void setQrdjwh(String qrdjwh) {
    this.qrdjwh = qrdjwh;
  }

  public String getQrdmlph() {
    return qrdmlph;
  }

  public void setQrdmlph(String qrdmlph) {
    this.qrdmlph = qrdmlph;
  }

  public String getQrdpcs() {
    return qrdpcs;
  }

  public void setQrdpcs(String qrdpcs) {
    this.qrdpcs = qrdpcs;
  }

  public String getQrdqx() {
    return qrdqx;
  }

  public void setQrdqx(String qrdqx) {
    this.qrdqx = qrdqx;
  }

  public String getQrdxzjd() {
    return qrdxzjd;
  }

  public void setQrdxzjd(String qrdxzjd) {
    this.qrdxzjd = qrdxzjd;
  }

  public String getQrdz() {
    return qrdz;
  }

  public void setQrdz(String qrdz) {
    this.qrdz = qrdz;
  }

  public String getQybz() {
    return qybz;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getSlrq() {
    return slrq;
  }

  public void setSlrq(String slrq) {
    this.slrq = slrq;
  }

  public String getSpjg() {
    return spjg;
  }

  public void setSpjg(String spjg) {
    this.spjg = spjg;
  }

  public String getSplx() {
    return splx;
  }

  public void setSplx(String splx) {
    this.splx = splx;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getSqqrly() {
    return sqqrly;
  }

  public void setSqqrly(String sqqrly) {
    this.sqqrly = sqqrly;
  }

  public String getXb() {
    return xb;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public Long getXydzid() {
    return xydzid;
  }

  public void setXydzid(Long xydzid) {
    this.xydzid = xydzid;
  }

  public String getYjrclx() {
    return yjrclx;
  }

  public void setYjrclx(String yjrclx) {
    this.yjrclx = yjrclx;
  }

  public String getYrdwjcyrysj() {
    return yrdwjcyrysj;
  }

  public void setYrdwjcyrysj(String yrdwjcyrysj) {
    this.yrdwjcyrysj = yrdwjcyrysj;
  }

  public String getZlfzjg() {
    return zlfzjg;
  }

  public void setZlfzjg(String zlfzjg) {
    this.zlfzjg = zlfzjg;
  }

  public String getZyjszc() {
    return zyjszc;
  }

  public void setZyjszc(String zyjszc) {
    this.zyjszc = zyjszc;
  }

  public String getZyzl() {
    return zyzl;
  }

  public void setZyzl(String zyzl) {
    this.zyzl = zyzl;
  }

  public String getQrdhlx() {
    return qrdhlx;
  }

  public void setQrdhlx(String qrdhlx) {
    this.qrdhlx = qrdhlx;
  }

  public void setRlhbz(String rlhbz) {
    this.rlhbz = rlhbz;
  }

  public String getHb() {
    return hb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public String getHkszddjjg() {
    return hkszddjjg;
  }

  public void setHkszddjjg(String hkszddjjg) {
    this.hkszddjjg = hkszddjjg;
  }

  public String getQrdjwzrq() {
    return qrdjwzrq;
  }

  public void setQrdjwzrq(String qrdjwzrq) {
    this.qrdjwzrq = qrdjwzrq;
  }

  public String getRlhbz() {
    return rlhbz;
  }

  public String getXm() {
    return xm;
  }

  public String getWhcd() {
    return whcd;
  }

  public void setWhcd(String whcd) {
    this.whcd = whcd;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getZzssxq() {
    return zzssxq;
  }

  public void setZzssxq(String zzssxq) {
    this.zzssxq = zzssxq;
  }

  public String getZzxz() {
    return zzxz;
  }

  public void setZzxz(String zzxz) {
    this.zzxz = zzxz;
  }

  public Long getQrdhhid() {
    return qrdhhid;
  }

  public void setQrdhhid(Long qrdhhid) {
    this.qrdhhid = qrdhhid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getSpmbid() {
    return spmbid;
  }

  public void setSpmbid(Long spmbid) {
    this.spmbid = spmbid;
  }

  public String getSqrgmsfhm() {
    return sqrgmsfhm;
  }

  public void setSqrgmsfhm(String sqrgmsfhm) {
    this.sqrgmsfhm = sqrgmsfhm;
  }

  public String getSqrhkdjjg() {
    return sqrhkdjjg;
  }

  public void setSqrhkdjjg(String sqrhkdjjg) {
    this.sqrhkdjjg = sqrhkdjjg;
  }

  public String getSqrxm() {
    return sqrxm;
  }

  public void setSqrxm(String sqrxm) {
    this.sqrxm = sqrxm;
  }

  public String getSqrzzssxq() {
    return sqrzzssxq;
  }

  public void setSqrzzssxq(String sqrzzssxq) {
    this.sqrzzssxq = sqrzzssxq;
  }

  public String getSqrzzxz() {
    return sqrzzxz;
  }

  public void setSqrzzxz(String sqrzzxz) {
    this.sqrzzxz = sqrzzxz;
  }

  public String getYsqrgx() {
    return ysqrgx;
  }

  public void setYsqrgx(String ysqrgx) {
    this.ysqrgx = ysqrgx;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public String getSpsm() {
    return spsm;
  }

  public void setSpsm(String spsm) {
    this.spsm = spsm;
  }

  public String getQrhhb() {
    return qrhhb;
  }

  public String getCxfldm() {
    return cxfldm;
  }

  public String getNyzyrklhczyydm() {
    return nyzyrklhczyydm;
  }

  public String getKdqqy_qcdqbm() {
    return kdqqy_qcdqbm;
  }

  public String getKdqqy_qchjywid() {
    return kdqqy_qchjywid;
  }

  public String getKdqqy_fkzt() {
    return kdqqy_fkzt;
  }

  public String getKdqqy_fksj() {
    return kdqqy_fksj;
  }

  public String getKdqqy_fknr() {
    return kdqqy_fknr;
  }

  public String getKdqqy_czyxm() {
    return kdqqy_czyxm;
  }

  public String getKdqqy_czydw() {
    return kdqqy_czydw;
  }

  public void setQrhhb(String qrhhb) {
    this.qrhhb = qrhhb;
  }

  public void setCxfldm(String cxfldm) {
    this.cxfldm = cxfldm;
  }

  public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
    this.nyzyrklhczyydm = nyzyrklhczyydm;
  }

  public void setKdqqy_czydw(String kdqqy_czydw) {
    this.kdqqy_czydw = kdqqy_czydw;
  }

  public void setKdqqy_czyxm(String kdqqy_czyxm) {
    this.kdqqy_czyxm = kdqqy_czyxm;
  }

  public void setKdqqy_fknr(String kdqqy_fknr) {
    this.kdqqy_fknr = kdqqy_fknr;
  }

  public void setKdqqy_fksj(String kdqqy_fksj) {
    this.kdqqy_fksj = kdqqy_fksj;
  }

  public void setKdqqy_fkzt(String kdqqy_fkzt) {
    this.kdqqy_fkzt = kdqqy_fkzt;
  }

  public void setKdqqy_qcdqbm(String kdqqy_qcdqbm) {
    this.kdqqy_qcdqbm = kdqqy_qcdqbm;
  }

  public void setKdqqy_qchjywid(String kdqqy_qchjywid) {
    this.kdqqy_qchjywid = kdqqy_qchjywid;
  }

}
