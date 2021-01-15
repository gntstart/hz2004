package com.hzjc.hz2004.po.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zzj_swzxyw")
public class Vswzxyw implements com.hzjc.wsstruts.po.PO{
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Id
    private String id;
    private String bsbrid;     //被申请人ID
    private String bsbrname;   //被申请人姓名
    private String bsbridcard; //被申请人身份证
    private String sbrname;    //申请人姓名
    private String sbridcard;  //申请人身份证
    private String swzxlb;     //死亡注销类别CODE
    private String swzmbh;     //死亡证明编号
    private String sbrjtgx;    //申报人家庭关系 CODE
    private String swsj;         //死亡时间
    private String cjsj;         //创建时间
    private String ip;         //创建ip地址
    private int flag;          //JC状态（0 待处理 1已处理 2处理异常）
    private String gxsj;       //状态变更时间
    private String sbh;        //设备号
    private String pcs;        //派出所
    private String ywlsh;      //业务流水号
    private String qrbz;       //确认标志
    private String cwxx;       //错误信息

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBsbrid() {
        return bsbrid;
    }

    public void setBsbrid(String bsbrid) {
        this.bsbrid = bsbrid;
    }

    public String getBsbrname() {
        return bsbrname;
    }

    public void setBsbrname(String bsbrname) {
        this.bsbrname = bsbrname;
    }

    public String getBsbridcard() {
        return bsbridcard;
    }

    public void setBsbridcard(String bsbridcard) {
        this.bsbridcard = bsbridcard;
    }

    public String getSbrname() {
        return sbrname;
    }

    public void setSbrname(String sbrname) {
        this.sbrname = sbrname;
    }

    public String getSbridcard() {
        return sbridcard;
    }

    public void setSbridcard(String sbridcard) {
        this.sbridcard = sbridcard;
    }

    public String getSwzxlb() {
        return swzxlb;
    }

    public void setSwzxlb(String swzxlb) {
        this.swzxlb = swzxlb;
    }

    public String getSwzmbh() {
        return swzmbh;
    }

    public void setSwzmbh(String swzmbh) {
        this.swzmbh = swzmbh;
    }

    public String getSbrjtgx() {
        return sbrjtgx;
    }

    public void setSbrjtgx(String sbrjtgx) {
        this.sbrjtgx = sbrjtgx;
    }

    public String getSwsj() {
        return swsj;
    }

    public void setSwsj(String swsj) {
        this.swsj = swsj;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
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
}
