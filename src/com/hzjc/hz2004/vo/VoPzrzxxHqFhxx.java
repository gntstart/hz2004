package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoHJXX_PZRZB;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import org.apache.commons.beanutils.*;
import java.lang.reflect.*;

/**
 * 拍照日志信息获取返回信息VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoPzrzxxHqFhxx
    extends DefaultVO {

  //拍照日志信息
  private Long pzrzid; //拍照日志ID
  private Long zplsid; //照片流水ID
  private Long nbslid; //内部受理ID
  private Long yhid; //用户ID
  private String ipdz; //IP地址
  private String yhdlm; //用户登录名
  private String yhdw; //用户单位
  private String yhxm; //用户姓名
  private String bcsj; //保存时间
  private String rksj; //入库时间
  private String gmsfhm; //公民身份号码
  private String slh; //受理号
  private String pzxlh; //拍照序列号
  //证件受理信息
  //private Long nbslid;
  //private String slh;
  private Long ryid; //人员ID
  private Long rynbid; //人员内部ID
  private Long zpid; //照片ID
  private String qfjg; //签发机关
  private String yxqxqsrq; //有效期限起始日期
  private String yxqxjzrq; //有效期限截止日期
  private String zz; //住址
  private String slyy; //申领原因
  private String zzlx; //制证类型
  private String lqfs; //领取方式
  private String sflx; //收费类型
  private String sjblsh; //数据包流水号
  private String slzt; //受理状态
  private Long zjywid; //证件业务ID
  private String cxbz; //冲销标志
  private String cxsj; //冲销时间
  private Long cxrid; //冲销人ID
  private Long cxzjywid; //冲销证件业务ID
  private Float sfje; //收费金额

  public VoPzrzxxHqFhxx() {
  }

  public VoPzrzxxHqFhxx(PoHJXX_PZRZB poPzrzxx, PoZJYW_SLXXB poSlxx) {
    try {
      BeanUtils.copyProperties(this, poPzrzxx);
      BeanUtils.copyProperties(this, poSlxx);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

    return;
  }

  public String getBcsj() {
    return bcsj;
  }

  public void setBcsj(String bcsj) {
    this.bcsj = bcsj;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public Long getCxzjywid() {
    return cxzjywid;
  }

  public void setCxzjywid(Long cxzjywid) {
    this.cxzjywid = cxzjywid;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getIpdz() {
    return ipdz;
  }

  public void setIpdz(String ipdz) {
    this.ipdz = ipdz;
  }

  public String getLqfs() {
    return lqfs;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public Long getPzrzid() {
    return pzrzid;
  }

  public void setPzrzid(Long pzrzid) {
    this.pzrzid = pzrzid;
  }

  public String getPzxlh() {
    return pzxlh;
  }

  public void setPzxlh(String pzxlh) {
    this.pzxlh = pzxlh;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public String getRksj() {
    return rksj;
  }

  public void setRksj(String rksj) {
    this.rksj = rksj;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Float getSfje() {
    return sfje;
  }

  public void setSfje(Float sfje) {
    this.sfje = sfje;
  }

  public String getSflx() {
    return sflx;
  }

  public void setSflx(String sflx) {
    this.sflx = sflx;
  }

  public String getSjblsh() {
    return sjblsh;
  }

  public void setSjblsh(String sjblsh) {
    this.sjblsh = sjblsh;
  }

  public String getSlh() {
    return slh;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getSlyy() {
    return slyy;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public String getSlzt() {
    return slzt;
  }

  public void setSlzt(String slzt) {
    this.slzt = slzt;
  }

  public String getYhdlm() {
    return yhdlm;
  }

  public void setYhdlm(String yhdlm) {
    this.yhdlm = yhdlm;
  }

  public String getYhdw() {
    return yhdw;
  }

  public void setYhdw(String yhdw) {
    this.yhdw = yhdw;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public String getYhxm() {
    return yhxm;
  }

  public void setYhxm(String yhxm) {
    this.yhxm = yhxm;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZplsid() {
    return zplsid;
  }

  public void setZplsid(Long zplsid) {
    this.zplsid = zplsid;
  }

  public String getZz() {
    return zz;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

}