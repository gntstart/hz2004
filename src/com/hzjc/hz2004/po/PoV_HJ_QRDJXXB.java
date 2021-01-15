package  com.hzjc.hz2004.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="V_HJ_QRDJXXB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoV_HJ_QRDJXXB implements com.hzjc.wsstruts.po.PO
{
  @GeneratedValue(generator = "generator") 
  @GenericGenerator(name = "generator", strategy = "assigned") 
  @Id	
  private Long qrdjid;
  private Long rynbid;
  private String qrqhb;
  private String qrlb;
  private String qrrq;
  private String qcdgjdq;
  private String qcdssxq;
  private String qcdxz;
  private String qyzbh;
  private String zqzbh;
  private Long nbsfzid;
  private String bdfw;
  private Long hjywid;
  private String cxbz;
  private Long cxrid;
  private String cxsj;
  private Long cxhjywid;
  private Long ryid;
  private Long hhnbid;
  private String gmsfhm;
  private String xm;
  private String cym;
  private String xb;
  private String mz;
  private String dhhm;
  private String csrq;
  private String cssj;
  private String csdgjdq;
  private String csdssxq;
  private String csdxz;
  private String jhrygmsfhm;
  private String jhryxm;
  private String jhryjhgx;
  private String jhregmsfhm;
  private String jhrexm;
  private String jhrejhgx;
  private String fqgmsfhm;
  private String fqxm;
  private String mqgmsfhm;
  private String mqxm;
  private String pogmsfhm;
  private String poxm;
  private String jggjdq;
  private String jgssxq;
  private String zjxy;
  private String whcd;
  private String hyzk;
  private String byzk;
  private String sg;
  private String xx;
  private String zy;
  private String zylb;
  private String fwcs;
  private String xxjb;
  private String hb;
  private String yhzgx;
  private String qtssxq;
  private String qtzz;
  private String bz;
  private Long mlpnbid;
  private String ssxq;
  private String jlx;
  private String mlph;
  private String mlxz;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private String pxh;
  private String ywlx;
  private Long czsm;
  private String sbsj;
  private String sbryxm;
  private String sbrgmsfhm;
  private String slsj;
  private String sldw;
  private Long slrid;
  private String qfjg;
  private String yxqxqsrq;
  private String yxqxjzrq;
  private String hzxm;
  private String hzgmsfhm;
  private Long hhid;
  private String qyzhyxx;
  private String cxfldm;

  private String hjdz_cxfldm;
  private String nyzyrklhczyydm;
  private String zyjszc_pdbz;
  private String jndj_pdbz;
  private String ncjdzzyxbys_pdbz;
  private String jjqx_pdbz;
  private String zczjyhjzwnys_pdbz;
  private String ncjsbtcxy_pdbz;

  public void setCxfldm(String cxfldm) {
    this.cxfldm = cxfldm;
  }

  public String getCxfldm() {
    return cxfldm;
  }

      public String getQyzhyxx() {
        return qyzhyxx;
      }

      public void setQyzhyxx(String qyzhyxx) {
        this.qyzhyxx = qyzhyxx;
  }
  public void setHhid(Long hhid) {
  this.hhid = hhid;
}

public Long getHhid() {
  return hhid;
}

  public void setQrdjid(Long qrdjid) {
    this.qrdjid = qrdjid;
  }

  public Long getQrdjid() {
    return qrdjid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setQrqhb(String qrqhb) {
    this.qrqhb = qrqhb;
  }

  public String getQrqhb() {
    return qrqhb;
  }

  public void setQrlb(String qrlb) {
    this.qrlb = qrlb;
  }

  public String getQrlb() {
    return qrlb;
  }

  public void setQrrq(String qrrq) {
    this.qrrq = qrrq;
  }

  public String getQrrq() {
    return qrrq;
  }

  public void setQcdgjdq(String qcdgjdq) {
    this.qcdgjdq = qcdgjdq;
  }

  public String getQcdgjdq() {
    return qcdgjdq;
  }

  public void setQcdssxq(String qcdssxq) {
    this.qcdssxq = qcdssxq;
  }

  public String getQcdssxq() {
    return qcdssxq;
  }

  public void setQcdxz(String qcdxz) {
    this.qcdxz = qcdxz;
  }

  public String getQcdxz() {
    return qcdxz;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public void setZqzbh(String zqzbh) {
    this.zqzbh = zqzbh;
  }

  public String getZqzbh() {
    return zqzbh;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setHjywid(Long hjywid) {
    this.hjywid = hjywid;
  }

  public Long getHjywid() {
    return hjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxhjywid(Long cxhjywid) {
    this.cxhjywid = cxhjywid;
  }

  public Long getCxhjywid() {
    return cxhjywid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setCym(String cym) {
    this.cym = cym;
  }

  public String getCym() {
    return cym;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setDhhm(String dhhm) {
    this.dhhm = dhhm;
  }

  public String getDhhm() {
    return dhhm;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCssj(String cssj) {
    this.cssj = cssj;
  }

  public String getCssj() {
    return cssj;
  }

  public void setCsdgjdq(String csdgjdq) {
    this.csdgjdq = csdgjdq;
  }

  public String getCsdgjdq() {
    return csdgjdq;
  }

  public void setCsdssxq(String csdssxq) {
    this.csdssxq = csdssxq;
  }

  public String getCsdssxq() {
    return csdssxq;
  }

  public void setCsdxz(String csdxz) {
    this.csdxz = csdxz;
  }

  public String getCsdxz() {
    return csdxz;
  }

  public void setJhrygmsfhm(String jhrygmsfhm) {
    this.jhrygmsfhm = jhrygmsfhm;
  }

  public String getJhrygmsfhm() {
    return jhrygmsfhm;
  }

  public void setJhryxm(String jhryxm) {
    this.jhryxm = jhryxm;
  }

  public String getJhryxm() {
    return jhryxm;
  }

  public void setJhryjhgx(String jhryjhgx) {
    this.jhryjhgx = jhryjhgx;
  }

  public String getJhryjhgx() {
    return jhryjhgx;
  }

  public void setJhregmsfhm(String jhregmsfhm) {
    this.jhregmsfhm = jhregmsfhm;
  }

  public String getJhregmsfhm() {
    return jhregmsfhm;
  }

  public void setJhrexm(String jhrexm) {
    this.jhrexm = jhrexm;
  }

  public String getJhrexm() {
    return jhrexm;
  }

  public void setJhrejhgx(String jhrejhgx) {
    this.jhrejhgx = jhrejhgx;
  }

  public String getJhrejhgx() {
    return jhrejhgx;
  }

  public void setFqgmsfhm(String fqgmsfhm) {
    this.fqgmsfhm = fqgmsfhm;
  }

  public String getFqgmsfhm() {
    return fqgmsfhm;
  }

  public void setFqxm(String fqxm) {
    this.fqxm = fqxm;
  }

  public String getFqxm() {
    return fqxm;
  }

  public void setMqgmsfhm(String mqgmsfhm) {
    this.mqgmsfhm = mqgmsfhm;
  }

  public String getMqgmsfhm() {
    return mqgmsfhm;
  }

  public void setMqxm(String mqxm) {
    this.mqxm = mqxm;
  }

  public String getMqxm() {
    return mqxm;
  }

  public void setPogmsfhm(String pogmsfhm) {
    this.pogmsfhm = pogmsfhm;
  }

  public String getPogmsfhm() {
    return pogmsfhm;
  }

  public void setPoxm(String poxm) {
    this.poxm = poxm;
  }

  public String getPoxm() {
    return poxm;
  }

  public void setJggjdq(String jggjdq) {
    this.jggjdq = jggjdq;
  }

  public String getJggjdq() {
    return jggjdq;
  }

  public void setJgssxq(String jgssxq) {
    this.jgssxq = jgssxq;
  }

  public String getJgssxq() {
    return jgssxq;
  }

  public void setZjxy(String zjxy) {
    this.zjxy = zjxy;
  }

  public String getZjxy() {
    return zjxy;
  }

  public void setWhcd(String whcd) {
    this.whcd = whcd;
  }

  public String getWhcd() {
    return whcd;
  }

  public void setHyzk(String hyzk) {
    this.hyzk = hyzk;
  }

  public String getHyzk() {
    return hyzk;
  }

  public void setByzk(String byzk) {
    this.byzk = byzk;
  }

  public String getByzk() {
    return byzk;
  }

  public void setSg(String sg) {
    this.sg = sg;
  }

  public String getSg() {
    return sg;
  }

  public void setXx(String xx) {
    this.xx = xx;
  }

  public String getXx() {
    return xx;
  }

  public void setZy(String zy) {
    this.zy = zy;
  }

  public String getZy() {
    return zy;
  }

  public void setZylb(String zylb) {
    this.zylb = zylb;
  }

  public String getZylb() {
    return zylb;
  }

  public void setFwcs(String fwcs) {
    this.fwcs = fwcs;
  }

  public String getFwcs() {
    return fwcs;
  }

  public void setXxjb(String xxjb) {
    this.xxjb = xxjb;
  }

  public String getXxjb() {
    return xxjb;
  }

  public void setHb(String hb) {
    this.hb = hb;
  }

  public String getHb() {
    return hb;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setQtssxq(String qtssxq) {
    this.qtssxq = qtssxq;
  }

  public String getQtssxq() {
    return qtssxq;
  }

  public void setQtzz(String qtzz) {
    this.qtzz = qtzz;
  }

  public String getQtzz() {
    return qtzz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getBz() {
    return bz;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJlx() {
    return jlx;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getPcs() {
    return pcs;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setPxh(String pxh) {
    this.pxh = pxh;
  }

  public String getPxh() {
    return pxh;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setCzsm(Long czsm) {
    this.czsm = czsm;
  }

  public Long getCzsm() {
    return czsm;
  }

  public void setSbsj(String sbsj) {
    this.sbsj = sbsj;
  }

  public String getSbsj() {
    return sbsj;
  }

  public void setSbryxm(String sbryxm) {
    this.sbryxm = sbryxm;
  }

  public String getSbryxm() {
    return sbryxm;
  }

  public void setSbrgmsfhm(String sbrgmsfhm) {
    this.sbrgmsfhm = sbrgmsfhm;
  }

  public String getSbrgmsfhm() {
    return sbrgmsfhm;
  }

  public void setSlsj(String slsj) {
    this.slsj = slsj;
  }

  public String getSlsj() {
    return slsj;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setSlrid(Long slrid) {
    this.slrid = slrid;
  }

  public Long getSlrid() {
    return slrid;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setHzxm(String hzxm) {
    this.hzxm = hzxm;
  }

  public String getHzxm() {
    return hzxm;
  }

  public void setHzgmsfhm(String hzgmsfhm) {
    this.hzgmsfhm = hzgmsfhm;
  }

  public void setHjdz_cxfldm(String hjdz_cxfldm) {
    this.hjdz_cxfldm = hjdz_cxfldm;
  }

  public void setJjqx_pdbz(String jjqx_pdbz) {
    this.jjqx_pdbz = jjqx_pdbz;
  }

  public void setJndj_pdbz(String jndj_pdbz) {
    this.jndj_pdbz = jndj_pdbz;
  }

  public void setNcjdzzyxbys_pdbz(String ncjdzzyxbys_pdbz) {
    this.ncjdzzyxbys_pdbz = ncjdzzyxbys_pdbz;
  }

  public void setNyzyrklhczyydm(String nyzyrklhczyydm) {
    this.nyzyrklhczyydm = nyzyrklhczyydm;
  }

  public void setNcjsbtcxy_pdbz(String ncjsbtcxy_pdbz) {
    this.ncjsbtcxy_pdbz = ncjsbtcxy_pdbz;
  }

  public void setZczjyhjzwnys_pdbz(String zczjyhjzwnys_pdbz) {
    this.zczjyhjzwnys_pdbz = zczjyhjzwnys_pdbz;
  }

  public void setZyjszc_pdbz(String zyjszc_pdbz) {
    this.zyjszc_pdbz = zyjszc_pdbz;
  }

  public String getHzgmsfhm() {
    return hzgmsfhm;
  }

  public String getHjdz_cxfldm() {
    return hjdz_cxfldm;
  }

  public String getJjqx_pdbz() {
    return jjqx_pdbz;
  }

  public String getJndj_pdbz() {
    return jndj_pdbz;
  }

  public String getNcjdzzyxbys_pdbz() {
    return ncjdzzyxbys_pdbz;
  }

  public String getNcjsbtcxy_pdbz() {
    return ncjsbtcxy_pdbz;
  }

  public String getNyzyrklhczyydm() {
    return nyzyrklhczyydm;
  }

  public String getZczjyhjzwnys_pdbz() {
    return zczjyhjzwnys_pdbz;
  }

  public String getZyjszc_pdbz() {
    return zyjszc_pdbz;
  }

}
