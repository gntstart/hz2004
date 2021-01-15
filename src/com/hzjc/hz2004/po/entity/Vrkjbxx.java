package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zzj_Vrkjbxx")
public class Vrkjbxx implements com.hzjc.wsstruts.po.PO {
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    private String rynbid;    //--记录ID, 用于返回对应
    private String hzxm;    //--户主姓名
    private String yhzgx;    //--与户主关系
    private String hh;    //--户号
    private String gmsfzh;    //--公民身份证号
    private String xm;    //--姓名，
    private String xb;    //--性别
    private String cym;    //--曾用名
    private String mz;    //--民族
    private String csrq; //--出生日期，格式：yyyyMMdd
    private String whcd;    //--文化程度
    private String zy;//	--职业
    private String dzxx;    //--住址
    private String fwcs;    //--服务处所
    private String jgssxq;//	--籍贯省市县/区
    private String sjgjdw;    //--数据归集单位
    private String ryzt;    //--人员状态
    private byte[] zp;    //--照片base64
    private String pcs;     //派出所
    private String jcwh;    //居（村）委会
    private String hb;      //户别
    private String byzk;    //--兵役状况
    private String hyzk;    //-婚姻状况

    public String getByzk() {
        return byzk;
    }

    public void setByzk(String byzk) {
        this.byzk = byzk;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getRynbid() {
        return rynbid;
    }

    public void setRynbid(String rynbid) {
        this.rynbid = rynbid;
    }

    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    public String getYhzgx() {
        return yhzgx;
    }

    public void setYhzgx(String yhzgx) {
        this.yhzgx = yhzgx;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getGmsfzh() {
        return gmsfzh;
    }

    public void setGmsfzh(String gmsfzh) {
        this.gmsfzh = gmsfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCym() {
        return cym;
    }

    public void setCym(String cym) {
        this.cym = cym;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getFwcs() {
        return fwcs;
    }

    public void setFwcs(String fwcs) {
        this.fwcs = fwcs;
    }

    public String getJgssxq() {
        return jgssxq;
    }

    public void setJgssxq(String jgssxq) {
        this.jgssxq = jgssxq;
    }

    public String getSjgjdw() {
        return sjgjdw;
    }

    public void setSjgjdw(String sjgjdw) {
        this.sjgjdw = sjgjdw;
    }


    public byte[] getZp() {
        return zp;
    }

    public void setZp(byte[] zp) {
        this.zp = zp;
    }


    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getJcwh() {
        return jcwh;
    }

    public void setJcwh(String jcwh) {
        this.jcwh = jcwh;
    }

    public String getHb() {
        return hb;
    }

    public void setHb(String hb) {
        this.hb = hb;
    }
}
