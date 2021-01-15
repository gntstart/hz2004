package com.hzjc.zzj.bean;

import java.io.Serializable;

public class VswzxywBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postid;
    private String bsbrid;     //被申请人ID
    private String bsbrname;   //被申请人姓名
    private String bsbridcard; //被申请人身份证
    private String sbrname;    //申请人姓名
    private String sbridcard;  //申请人身份证
    private String swzxlb;     //死亡注销类别CODE
    private String swzmbh;     //死亡证明编号
    private String sbrjtgx;    //申报人家庭关系 CODE
    private String swsj;         //死亡时间
    private String sbh;        //设备号
    private String pcs;        //派出所
    private String ywlsh;    //业务流水号

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
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
}
