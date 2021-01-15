package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * 浙江准迁证接收参数实体类
 *
 */
@Entity
@Table(name = "zjyw_zqzqyrxx")
public class ZjywZqzQyrxx implements com.hzjc.wsstruts.po.PO{
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    //主键id
    private String id;
    //浙江准迁证表id
    private String ywlsh;
    //准迁证编号
    private String zqzbh;
    //与申请人关系_家庭关系
    private String ysqrgx_jtgxdm;
    //公民身份号码
    private String gmsfhm;
    //姓名
    private String xm;
    //性别
    private String xbdm;
    //出生日期
    private String csrq;

    public String getYwlsh() {
        return ywlsh;
    }

    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZqzbh() {
        return zqzbh;
    }

    public void setZqzbh(String zqzbh) {
        this.zqzbh = zqzbh;
    }

    public String getYsqrgx_jtgxdm() {
        return ysqrgx_jtgxdm;
    }

    public void setYsqrgx_jtgxdm(String ysqrgx_jtgxdm) {
        this.ysqrgx_jtgxdm = ysqrgx_jtgxdm;
    }

    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXbdm() {
        return xbdm;
    }

    public void setXbdm(String xbdm) {
        this.xbdm = xbdm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }
}
