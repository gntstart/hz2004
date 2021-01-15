package com.hzjc.hz2004.po;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="JSV3_QCRKB" )
@DynamicUpdate(true)
@DynamicInsert(true)
public class PoJSV3_QCRKB implements com.hzjc.wsstruts.po.PO, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "generator") 
	@GenericGenerator(name = "generator", strategy = "assigned") 
	@Id
	private Long	czrkrkid;
	private Long	pcid;
	private String	pch;
	private String	sjbz;
	private String	czrkssssxq;
	private String	czrkgmsfhm;
	private String	czrkxm;
	private String	czrkcym;
	private String	czrkxb;
	private String	czrkmz;
	private String	czrkcsrq;
	private String	czrkcsdgj;
	private String	czrkcsdssx;
	private String	czrkcsdxz;
	private String	czrkjggj;
	private String	czrkjgssx;
	private String	czrkwhcd;
	private String	czrkhyzk;
	private String	czrkbyzk;
	private String	czrksg;
	private String	czrkzy;
	private String	czrkfwcs;
	private String	czrkzz;
	private Long	czrkxpid;
	private String	czrkxxjb;
	private String	czrkzxbs;
	private String	czrkxjgajgmc;
	private String	czrkxjgajgjgdm;
	private String	czrkpcsmc;
	private String	czrkpcsjgdm;
	private String	czrkxxtqsj;
	private Long	czrkryid;
	private String	czrkhsqlbs;
	private String	czrkhyqlbs;
	private String	czrkhdqlgj;
	private String	czrkhdqlssx;
	private String	czrkhdqlxz;
	private String	czrkssxzjd;
	private String	czrkjhryhm;
	private String	czrkjhryxm;
	private String	czrkjhrygx;
	private String	czrkjhrehm;
	private String	czrkjhrexm;
	private String	czrkjhregx;
	private String	czrkfqhm;
	private String	czrkfqxm;
	private String	czrkmqhm;
	private String	czrkmqxm;
	private String	czrkpohm;
	private String	czrkpoxm;
	private String	czrkzjxy;
	private String	czrkxx;
	private String	czrkzylb;
	private String	czrkswrq;
	private String	czrkswzxlb;
	private String	czrkqcrq;
	private String	czrkqczxlb;
	private String	czrkqwdgj;
	private String	czrkqwdssx;
	private String	czrkqwdxz;
	private String	czrkcszmbh;
	private String	czrkbwbh;
	private String	czrkjlxmc;
	private String	czrkjwhmc;
	private String	czrkhsqlbz;
	private String	czrkhyqlbz;
	private String	czrkhzqlgj;
	private String	czrkhzqlssx;
	private String	czrkhzqlxz;
	private String	czrkhh;
	private String	czrkhlx;
	private String	czrkyhzgx;
	private String	czrkbwbhb;
	private String	czrklxdh;
	private String	dsdm;
	private Long	rkid;
	private Long	czrkxlh;
	private String	czrkzxrq;
	private String	czrksjxgxsj;
	private String	czrkjlx;
	private String	czrkjwh;
	private String	czrkxzjd;
	private String	czrkmlph;
	private String	czrkmlxz;

	public PoJSV3_QCRKB(){}

	public Long getCzrkrkid(){
		return this.czrkrkid;
	}

	public void setCzrkrkid(Long czrkrkid){
		this.czrkrkid=czrkrkid;
	}

	public Long getPcid(){
		return this.pcid;
	}

	public void setPcid(Long pcid){
		this.pcid=pcid;
	}

	public String getPch(){
		return this.pch;
	}

	public void setPch(String pch){
		this.pch=pch;
	}

	public String getSjbz(){
		return this.sjbz;
	}

	public void setSjbz(String sjbz){
		this.sjbz=sjbz;
	}

	public String getCzrkssssxq(){
		return this.czrkssssxq;
	}

	public void setCzrkssssxq(String czrkssssxq){
		this.czrkssssxq=czrkssssxq;
	}

	public String getCzrkgmsfhm(){
		return this.czrkgmsfhm;
	}

	public void setCzrkgmsfhm(String czrkgmsfhm){
		this.czrkgmsfhm=czrkgmsfhm;
	}

	public String getCzrkxm(){
		return this.czrkxm;
	}

	public void setCzrkxm(String czrkxm){
		this.czrkxm=czrkxm;
	}

	public String getCzrkcym(){
		return this.czrkcym;
	}

	public void setCzrkcym(String czrkcym){
		this.czrkcym=czrkcym;
	}

	public String getCzrkxb(){
		return this.czrkxb;
	}

	public void setCzrkxb(String czrkxb){
		this.czrkxb=czrkxb;
	}

	public String getCzrkmz(){
		return this.czrkmz;
	}

	public void setCzrkmz(String czrkmz){
		this.czrkmz=czrkmz;
	}

	public String getCzrkcsrq(){
		return this.czrkcsrq;
	}

	public void setCzrkcsrq(String czrkcsrq){
		this.czrkcsrq=czrkcsrq;
	}

	public String getCzrkcsdgj(){
		return this.czrkcsdgj;
	}

	public void setCzrkcsdgj(String czrkcsdgj){
		this.czrkcsdgj=czrkcsdgj;
	}

	public String getCzrkcsdssx(){
		return this.czrkcsdssx;
	}

	public void setCzrkcsdssx(String czrkcsdssx){
		this.czrkcsdssx=czrkcsdssx;
	}

	public String getCzrkcsdxz(){
		return this.czrkcsdxz;
	}

	public void setCzrkcsdxz(String czrkcsdxz){
		this.czrkcsdxz=czrkcsdxz;
	}

	public String getCzrkjggj(){
		return this.czrkjggj;
	}

	public void setCzrkjggj(String czrkjggj){
		this.czrkjggj=czrkjggj;
	}

	public String getCzrkjgssx(){
		return this.czrkjgssx;
	}

	public void setCzrkjgssx(String czrkjgssx){
		this.czrkjgssx=czrkjgssx;
	}

	public String getCzrkwhcd(){
		return this.czrkwhcd;
	}

	public void setCzrkwhcd(String czrkwhcd){
		this.czrkwhcd=czrkwhcd;
	}

	public String getCzrkhyzk(){
		return this.czrkhyzk;
	}

	public void setCzrkhyzk(String czrkhyzk){
		this.czrkhyzk=czrkhyzk;
	}

	public String getCzrkbyzk(){
		return this.czrkbyzk;
	}

	public void setCzrkbyzk(String czrkbyzk){
		this.czrkbyzk=czrkbyzk;
	}

	public String getCzrksg(){
		return this.czrksg;
	}

	public void setCzrksg(String czrksg){
		this.czrksg=czrksg;
	}

	public String getCzrkzy(){
		return this.czrkzy;
	}

	public void setCzrkzy(String czrkzy){
		this.czrkzy=czrkzy;
	}

	public String getCzrkfwcs(){
		return this.czrkfwcs;
	}

	public void setCzrkfwcs(String czrkfwcs){
		this.czrkfwcs=czrkfwcs;
	}

	public String getCzrkzz(){
		return this.czrkzz;
	}

	public void setCzrkzz(String czrkzz){
		this.czrkzz=czrkzz;
	}

	public Long getCzrkxpid(){
		return this.czrkxpid;
	}

	public void setCzrkxpid(Long czrkxpid){
		this.czrkxpid=czrkxpid;
	}

	public String getCzrkxxjb(){
		return this.czrkxxjb;
	}

	public void setCzrkxxjb(String czrkxxjb){
		this.czrkxxjb=czrkxxjb;
	}

	public String getCzrkzxbs(){
		return this.czrkzxbs;
	}

	public void setCzrkzxbs(String czrkzxbs){
		this.czrkzxbs=czrkzxbs;
	}

	public String getCzrkxjgajgmc(){
		return this.czrkxjgajgmc;
	}

	public void setCzrkxjgajgmc(String czrkxjgajgmc){
		this.czrkxjgajgmc=czrkxjgajgmc;
	}

	public String getCzrkxjgajgjgdm(){
		return this.czrkxjgajgjgdm;
	}

	public void setCzrkxjgajgjgdm(String czrkxjgajgjgdm){
		this.czrkxjgajgjgdm=czrkxjgajgjgdm;
	}

	public String getCzrkpcsmc(){
		return this.czrkpcsmc;
	}

	public void setCzrkpcsmc(String czrkpcsmc){
		this.czrkpcsmc=czrkpcsmc;
	}

	public String getCzrkpcsjgdm(){
		return this.czrkpcsjgdm;
	}

	public void setCzrkpcsjgdm(String czrkpcsjgdm){
		this.czrkpcsjgdm=czrkpcsjgdm;
	}

	public String getCzrkxxtqsj(){
		return this.czrkxxtqsj;
	}

	public void setCzrkxxtqsj(String czrkxxtqsj){
		this.czrkxxtqsj=czrkxxtqsj;
	}

	public Long getCzrkryid(){
		return this.czrkryid;
	}

	public void setCzrkryid(Long czrkryid){
		this.czrkryid=czrkryid;
	}

	public String getCzrkhsqlbs(){
		return this.czrkhsqlbs;
	}

	public void setCzrkhsqlbs(String czrkhsqlbs){
		this.czrkhsqlbs=czrkhsqlbs;
	}

	public String getCzrkhyqlbs(){
		return this.czrkhyqlbs;
	}

	public void setCzrkhyqlbs(String czrkhyqlbs){
		this.czrkhyqlbs=czrkhyqlbs;
	}

	public String getCzrkhdqlgj(){
		return this.czrkhdqlgj;
	}

	public void setCzrkhdqlgj(String czrkhdqlgj){
		this.czrkhdqlgj=czrkhdqlgj;
	}

	public String getCzrkhdqlssx(){
		return this.czrkhdqlssx;
	}

	public void setCzrkhdqlssx(String czrkhdqlssx){
		this.czrkhdqlssx=czrkhdqlssx;
	}

	public String getCzrkhdqlxz(){
		return this.czrkhdqlxz;
	}

	public void setCzrkhdqlxz(String czrkhdqlxz){
		this.czrkhdqlxz=czrkhdqlxz;
	}

	public String getCzrkssxzjd(){
		return this.czrkssxzjd;
	}

	public void setCzrkssxzjd(String czrkssxzjd){
		this.czrkssxzjd=czrkssxzjd;
	}

	public String getCzrkjhryhm(){
		return this.czrkjhryhm;
	}

	public void setCzrkjhryhm(String czrkjhryhm){
		this.czrkjhryhm=czrkjhryhm;
	}

	public String getCzrkjhryxm(){
		return this.czrkjhryxm;
	}

	public void setCzrkjhryxm(String czrkjhryxm){
		this.czrkjhryxm=czrkjhryxm;
	}

	public String getCzrkjhrygx(){
		return this.czrkjhrygx;
	}

	public void setCzrkjhrygx(String czrkjhrygx){
		this.czrkjhrygx=czrkjhrygx;
	}

	public String getCzrkjhrehm(){
		return this.czrkjhrehm;
	}

	public void setCzrkjhrehm(String czrkjhrehm){
		this.czrkjhrehm=czrkjhrehm;
	}

	public String getCzrkjhrexm(){
		return this.czrkjhrexm;
	}

	public void setCzrkjhrexm(String czrkjhrexm){
		this.czrkjhrexm=czrkjhrexm;
	}

	public String getCzrkjhregx(){
		return this.czrkjhregx;
	}

	public void setCzrkjhregx(String czrkjhregx){
		this.czrkjhregx=czrkjhregx;
	}

	public String getCzrkfqhm(){
		return this.czrkfqhm;
	}

	public void setCzrkfqhm(String czrkfqhm){
		this.czrkfqhm=czrkfqhm;
	}

	public String getCzrkfqxm(){
		return this.czrkfqxm;
	}

	public void setCzrkfqxm(String czrkfqxm){
		this.czrkfqxm=czrkfqxm;
	}

	public String getCzrkmqhm(){
		return this.czrkmqhm;
	}

	public void setCzrkmqhm(String czrkmqhm){
		this.czrkmqhm=czrkmqhm;
	}

	public String getCzrkmqxm(){
		return this.czrkmqxm;
	}

	public void setCzrkmqxm(String czrkmqxm){
		this.czrkmqxm=czrkmqxm;
	}

	public String getCzrkpohm(){
		return this.czrkpohm;
	}

	public void setCzrkpohm(String czrkpohm){
		this.czrkpohm=czrkpohm;
	}

	public String getCzrkpoxm(){
		return this.czrkpoxm;
	}

	public void setCzrkpoxm(String czrkpoxm){
		this.czrkpoxm=czrkpoxm;
	}

	public String getCzrkzjxy(){
		return this.czrkzjxy;
	}

	public void setCzrkzjxy(String czrkzjxy){
		this.czrkzjxy=czrkzjxy;
	}

	public String getCzrkxx(){
		return this.czrkxx;
	}

	public void setCzrkxx(String czrkxx){
		this.czrkxx=czrkxx;
	}

	public String getCzrkzylb(){
		return this.czrkzylb;
	}

	public void setCzrkzylb(String czrkzylb){
		this.czrkzylb=czrkzylb;
	}

	public String getCzrkswrq(){
		return this.czrkswrq;
	}

	public void setCzrkswrq(String czrkswrq){
		this.czrkswrq=czrkswrq;
	}

	public String getCzrkswzxlb(){
		return this.czrkswzxlb;
	}

	public void setCzrkswzxlb(String czrkswzxlb){
		this.czrkswzxlb=czrkswzxlb;
	}

	public String getCzrkqcrq(){
		return this.czrkqcrq;
	}

	public void setCzrkqcrq(String czrkqcrq){
		this.czrkqcrq=czrkqcrq;
	}

	public String getCzrkqczxlb(){
		return this.czrkqczxlb;
	}

	public void setCzrkqczxlb(String czrkqczxlb){
		this.czrkqczxlb=czrkqczxlb;
	}

	public String getCzrkqwdgj(){
		return this.czrkqwdgj;
	}

	public void setCzrkqwdgj(String czrkqwdgj){
		this.czrkqwdgj=czrkqwdgj;
	}

	public String getCzrkqwdssx(){
		return this.czrkqwdssx;
	}

	public void setCzrkqwdssx(String czrkqwdssx){
		this.czrkqwdssx=czrkqwdssx;
	}

	public String getCzrkqwdxz(){
		return this.czrkqwdxz;
	}

	public void setCzrkqwdxz(String czrkqwdxz){
		this.czrkqwdxz=czrkqwdxz;
	}

	public String getCzrkcszmbh(){
		return this.czrkcszmbh;
	}

	public void setCzrkcszmbh(String czrkcszmbh){
		this.czrkcszmbh=czrkcszmbh;
	}

	public String getCzrkbwbh(){
		return this.czrkbwbh;
	}

	public void setCzrkbwbh(String czrkbwbh){
		this.czrkbwbh=czrkbwbh;
	}

	public String getCzrkjlxmc(){
		return this.czrkjlxmc;
	}

	public void setCzrkjlxmc(String czrkjlxmc){
		this.czrkjlxmc=czrkjlxmc;
	}

	public String getCzrkjwhmc(){
		return this.czrkjwhmc;
	}

	public void setCzrkjwhmc(String czrkjwhmc){
		this.czrkjwhmc=czrkjwhmc;
	}

	public String getCzrkhsqlbz(){
		return this.czrkhsqlbz;
	}

	public void setCzrkhsqlbz(String czrkhsqlbz){
		this.czrkhsqlbz=czrkhsqlbz;
	}

	public String getCzrkhyqlbz(){
		return this.czrkhyqlbz;
	}

	public void setCzrkhyqlbz(String czrkhyqlbz){
		this.czrkhyqlbz=czrkhyqlbz;
	}

	public String getCzrkhzqlgj(){
		return this.czrkhzqlgj;
	}

	public void setCzrkhzqlgj(String czrkhzqlgj){
		this.czrkhzqlgj=czrkhzqlgj;
	}

	public String getCzrkhzqlssx(){
		return this.czrkhzqlssx;
	}

	public void setCzrkhzqlssx(String czrkhzqlssx){
		this.czrkhzqlssx=czrkhzqlssx;
	}

	public String getCzrkhzqlxz(){
		return this.czrkhzqlxz;
	}

	public void setCzrkhzqlxz(String czrkhzqlxz){
		this.czrkhzqlxz=czrkhzqlxz;
	}

	public String getCzrkhh(){
		return this.czrkhh;
	}

	public void setCzrkhh(String czrkhh){
		this.czrkhh=czrkhh;
	}

	public String getCzrkhlx(){
		return this.czrkhlx;
	}

	public void setCzrkhlx(String czrkhlx){
		this.czrkhlx=czrkhlx;
	}

	public String getCzrkyhzgx(){
		return this.czrkyhzgx;
	}

	public void setCzrkyhzgx(String czrkyhzgx){
		this.czrkyhzgx=czrkyhzgx;
	}

	public String getCzrkbwbhb(){
		return this.czrkbwbhb;
	}

	public void setCzrkbwbhb(String czrkbwbhb){
		this.czrkbwbhb=czrkbwbhb;
	}

	public String getCzrklxdh(){
		return this.czrklxdh;
	}

	public void setCzrklxdh(String czrklxdh){
		this.czrklxdh=czrklxdh;
	}

	public String getDsdm(){
		return this.dsdm;
	}

	public void setDsdm(String dsdm){
		this.dsdm=dsdm;
	}

	public Long getRkid(){
		return this.rkid;
	}

	public void setRkid(Long rkid){
		this.rkid=rkid;
	}

	public Long getCzrkxlh(){
		return this.czrkxlh;
	}

	public void setCzrkxlh(Long czrkxlh){
		this.czrkxlh=czrkxlh;
	}

	public String getCzrkzxrq(){
		return this.czrkzxrq;
	}

	public void setCzrkzxrq(String czrkzxrq){
		this.czrkzxrq=czrkzxrq;
	}

	public String getCzrksjxgxsj(){
		return this.czrksjxgxsj;
	}

	public void setCzrksjxgxsj(String czrksjxgxsj){
		this.czrksjxgxsj=czrksjxgxsj;
	}

	public String getCzrkjlx(){
		return this.czrkjlx;
	}

	public void setCzrkjlx(String czrkjlx){
		this.czrkjlx=czrkjlx;
	}

	public String getCzrkjwh(){
		return this.czrkjwh;
	}

	public void setCzrkjwh(String czrkjwh){
		this.czrkjwh=czrkjwh;
	}

	public String getCzrkxzjd(){
		return this.czrkxzjd;
	}

	public void setCzrkxzjd(String czrkxzjd){
		this.czrkxzjd=czrkxzjd;
	}

	public String getCzrkmlph(){
		return this.czrkmlph;
	}

	public void setCzrkmlph(String czrkmlph){
		this.czrkmlph=czrkmlph;
	}

	public String getCzrkmlxz(){
		return this.czrkmlxz;
	}

	public void setCzrkmlxz(String czrkmlxz){
		this.czrkmlxz=czrkmlxz;
	}

	public static long getSerialversionuid() {			return serialVersionUID;	}}