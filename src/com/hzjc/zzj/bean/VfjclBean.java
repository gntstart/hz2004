package com.hzjc.zzj.bean;

import java.io.Serializable;

public class VfjclBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postid;

    private String ywlsid;        //业务表id

    private String ywlx;        //业务类型code

    private String fjclname;    //材料名称

    private String fjcllx;      //材料类型code

    private String fjclzp;     //材料图片

    private String fjclsize;   //材料大小

    private String fjclgs;     //材料格式

    private String sbh;       //设备号

    private String pcs;        //派出所

    private String ywlsh;      //业务流水号

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getYwlsid() {
        return ywlsid;
    }

    public void setYwlsid(String ywlsid) {
        this.ywlsid = ywlsid;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getFjclname() {
        return fjclname;
    }

    public void setFjclname(String fjclname) {
        this.fjclname = fjclname;
    }

    public String getFjcllx() {
        return fjcllx;
    }

    public void setFjcllx(String fjcllx) {
        this.fjcllx = fjcllx;
    }

    public String getFjclzp() {
        return fjclzp;
    }

    public void setFjclzp(String fjclzp) {
        this.fjclzp = fjclzp;
    }

    public String getFjclsize() {
        return fjclsize;
    }

    public void setFjclsize(String fjclsize) {
        this.fjclsize = fjclsize;
    }

    public String getFjclgs() {
        return fjclgs;
    }

    public void setFjclgs(String fjclgs) {
        this.fjclgs = fjclgs;
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
