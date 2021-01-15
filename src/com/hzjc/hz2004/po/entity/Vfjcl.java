package com.hzjc.hz2004.po.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "zzj_fjcl")
public class Vfjcl implements com.hzjc.wsstruts.po.PO{
    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    private String id;          //主键id

    private String ywid;        //业务表id

    private String ywlx;        //业务类型code

    private String fjclname;    //材料名称

    private String fjcllx;      //材料类型code

    private byte[] fjclzp;     //材料图片

    private String fjclsize;   //材料大小

    private String fjclgs;     //材料格式

    private String fjclcjsj;     //创建时间

    private int flag;          //JC状态（0 待处理 1已处理 2处理异常）

    private Date gxsj;         //更新时间

    private String sbh;       //设备号
    private String pcs;        //派出所
    private String ywlsh;      //业务流水号

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYwid() {
        return ywid;
    }

    public void setYwid(String ywid) {
        this.ywid = ywid;
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

    public byte[] getFjclzp() {
        return fjclzp;
    }

    public void setFjclzp(byte[] fjclzp) {
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

    public String getFjclcjsj() {
        return fjclcjsj;
    }

    public void setFjclcjsj(String fjclcjsj) {
        this.fjclcjsj = fjclcjsj;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
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
}
