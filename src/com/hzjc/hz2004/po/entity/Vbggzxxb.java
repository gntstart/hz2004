package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zzj_bggzxxb")
public class Vbggzxxb implements com.hzjc.wsstruts.po.PO{
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    private String id;          //主键id
    private String ryid;          //人员id
    private String xm;          //姓名
    private String gmsfhm;      //公民sfzhm
    private String yhzgx;      //与户主关系
    private String dhhm;       //电话号码
    private String hyzk;       //婚姻状况
    private byte[] zp;          //ZP
    private String sg;         //身高
    private String mlxz;       //门（楼）详址
    private String fwcs;       //服务处所
    private String whcd;       //文化程度
    private String byzk;       //兵役状况
    private String xx;         //血型
    private String zy;         //职业
    private String zylb;       //职业类别
    private String poxm;       //配偶姓名
    private String pogmsfhm;   //配偶公民身份号码
    private String zjxy;       //宗教信仰
    private String lxdh;       //联系电话
    private int flag;           //JC状态（0 待处理 1已处理 2处理异常）
    private String sbh;        //设备号
    private String pcs;        //派出所
    private String ywlsh;      //业务流水号
    private String qrbz;       //确认标志
    private String cwxx;       //错误信息
    private String cjsj;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRyid() {
        return ryid;
    }

    public void setRyid(String ryid) {
        this.ryid = ryid;
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

    public String getYhzgx() {
        return yhzgx;
    }

    public void setYhzgx(String yhzgx) {
        this.yhzgx = yhzgx;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public byte[] getZp() {
        return zp;
    }

    public void setZp(byte[] zp) {
        this.zp = zp;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getMlxz() {
        return mlxz;
    }

    public void setMlxz(String mlxz) {
        this.mlxz = mlxz;
    }

    public String getFwcs() {
        return fwcs;
    }

    public void setFwcs(String fwcs) {
        this.fwcs = fwcs;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
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

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getZylb() {
        return zylb;
    }

    public void setZylb(String zylb) {
        this.zylb = zylb;
    }

    public String getPoxm() {
        return poxm;
    }

    public void setPoxm(String poxm) {
        this.poxm = poxm;
    }

    public String getPogmsfhm() {
        return pogmsfhm;
    }

    public void setPogmsfhm(String pogmsfhm) {
        this.pogmsfhm = pogmsfhm;
    }

    public String getZjxy() {
        return zjxy;
    }

    public void setZjxy(String zjxy) {
        this.zjxy = zjxy;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getSbh() {
        return sbh;
    }

    public void setSbh(String sbh) {
        this.sbh = sbh;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getYwlsh() {
        return ywlsh;
    }

    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh;
    }

    public String getQrbz() {
        return qrbz;
    }

    public void setQrbz(String qrbz) {
        this.qrbz = qrbz;
    }

    public String getCwxx() {
        return cwxx;
    }

    public void setCwxx(String cwxx) {
        this.cwxx = cwxx;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }
}
