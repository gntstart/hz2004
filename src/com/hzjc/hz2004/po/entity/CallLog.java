package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CALL_LOG")
public class CallLog implements com.hzjc.wsstruts.po.PO {
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    private String logid;
    private String sid;
    private Date cjsj;
    private Long hs;
    private String xkz;
    private String info;
    private String sfcg;
    private String returnmsg;
    private String errmsg;
    private String orgcode;
    private String orgname;
    private String ip;
    private String accountid;
    private String exesql;//EXESQL

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Long getHs() {
        return hs;
    }

    public void setHs(Long hs) {
        this.hs = hs;
    }

    public String getXkz() {
        return xkz;
    }

    public void setXkz(String xkz) {
        this.xkz = xkz;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSfcg() {
        return sfcg;
    }

    public void setSfcg(String sfcg) {
        this.sfcg = sfcg;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getExesql() {
        return exesql;
    }

    public void setExesql(String exesql) {
        this.exesql = exesql;
    }
}
