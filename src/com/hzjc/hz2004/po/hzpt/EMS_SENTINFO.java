package com.hzjc.hz2004.po.hzpt;

import java.util.Date;

public class EMS_SENTINFO {
	private String id;//主键id uuid
	private String organid;//单位id 3013165
	private String organname;//单位名称  芜湖市公安局
	private String matterid;//事项id 中间表wx_code
	private String servicename;//事项名称
	private String matterproposer;//事项申请人
	private String internals;//内件品名
	private String sender;//寄件人  hzythpt.sys_organ 表的organ_name
	private String sendertel;//寄件人电话    hzythpt.sys_organ 表的dhhm
	private String senderaddress;//寄件人地址    hzythpt.sys_organ 表的pcsxz
	private String receiver;//收件人  hzythpt.v_hzpt_apply的sjrxm（wx_code关联）
	private String receivertel;//收件人电话   hzythpt.v_hzpt_apply的ljrlxdh（wx_code关联）
	private String receiveraddress;//收件人地址   hzythpt.v_hzpt_apply的sjdz（wx_code关联）
	private String orderno;//订单号
	private String ordertype;//寄送类型 1-窗口到用户
	private String issendsms;//自发短信 1-自发短信
	private String status;
	private String removetag;//删除标记
	private Date createtime;//创建时间  插入时间
	private Date lastedittime;//最后修改时间  插入时间
	private Date jhsj;//数据同步时间戳  插入时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganid() {
		return organid;
	}
	public void setOrganid(String organid) {
		this.organid = organid;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getMatterid() {
		return matterid;
	}
	public void setMatterid(String matterid) {
		this.matterid = matterid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSendertel() {
		return sendertel;
	}
	public void setSendertel(String sendertel) {
		this.sendertel = sendertel;
	}
	public String getSenderaddress() {
		return senderaddress;
	}
	public void setSenderaddress(String senderaddress) {
		this.senderaddress = senderaddress;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceivertel() {
		return receivertel;
	}
	public void setReceivertel(String receivertel) {
		this.receivertel = receivertel;
	}
	public String getReceiveraddress() {
		return receiveraddress;
	}
	public void setReceiveraddress(String receiveraddress) {
		this.receiveraddress = receiveraddress;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getIssendsms() {
		return issendsms;
	}
	public void setIssendsms(String issendsms) {
		this.issendsms = issendsms;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getMatterproposer() {
		return matterproposer;
	}
	public void setMatterproposer(String matterproposer) {
		this.matterproposer = matterproposer;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getInternals() {
		return internals;
	}
	public void setInternals(String internals) {
		this.internals = internals;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemovetag() {
		return removetag;
	}
	public void setRemovetag(String removetag) {
		this.removetag = removetag;
	}
	public Date getLastedittime() {
		return lastedittime;
	}
	public void setLastedittime(Date lastedittime) {
		this.lastedittime = lastedittime;
	}
	public Date getJhsj() {
		return jhsj;
	}
	public void setJhsj(Date jhsj) {
		this.jhsj = jhsj;
	}
	
}