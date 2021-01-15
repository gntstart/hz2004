package com.hzjc.hz2004.service.impl.hzpt;

import com.hzjc.hz2004.base.SystemConfig;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoHZ_ZJ_SB;
import com.hzjc.hz2004.po.hzpt.EMS_SENTINFO;
import com.hzjc.hz2004.po.hzpt.ReceiveState;
import com.hzjc.hz2004.service.hzpt.ReceiveStateService;
import com.hzjc.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReceiveStateServiceImpl implements ReceiveStateService {
    @Override
    public void updataReceiveState(String WX_CODE, String cktable) {
        ReceiveState receiveState = new ReceiveState();
        receiveState.setWX_CODE(WX_CODE);
        receiveState.setBjzt(1);
        receiveState.setBjrq(new Date());
        receiveState.setCktable(cktable);
        updateReceive(receiveState);
    }

    public void updateReceive(ReceiveState receiveState) {
        Connection xtba_conn = null;
        PreparedStatement xtba_stmt = null;
        try {

            String sql = "update wxpt.hzpt_receivestate h set h.bjzt = ?,h.bjrq = sysdate,h.cktable = ? where h.wx_code = ?";
            Class.forName(SystemConfig.getJdbcConfig("jdbc.driverClassWxpt"));
            xtba_conn = DriverManager.getConnection(
                    SystemConfig.getJdbcConfig("jdbc.urlWxpt"),
                    SystemConfig.getJdbcConfig("jdbc.userWxpt"),
                    SystemConfig.getJdbcConfig("jdbc.passwordWxpt"));


            xtba_stmt = xtba_conn.prepareStatement(sql);
            xtba_stmt.setInt(1, receiveState.getBjzt());
            xtba_stmt.setString(2, receiveState.getCktable());
            xtba_stmt.setString(3, receiveState.getWX_CODE());
            xtba_stmt.execute();
        } catch (Exception e) {
            throw new java.lang.RuntimeException("错误，更新WX_CODE" + receiveState.getWX_CODE());
        } finally {
            try {
                if (xtba_stmt != null) xtba_stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (xtba_conn != null) xtba_conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Transactional
	@Override
	public void insertEmsSentInfo(PoHZ_ZJ_SB sb, String organid, String organname, String dwdm) {

        Connection xtba_conn = null;
        PreparedStatement xtba_stmt = null;
        ResultSet resultSet = null;
        String wx_code=sb.getWx_code();
        String ywlb = sb.getYwlb();
        String njpm="";
        try {
        	EMS_SENTINFO ems_sentinfo = new EMS_SENTINFO();
            ems_sentinfo.setCreatetime(new Date());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            ems_sentinfo.setId(uuid);
            ems_sentinfo.setIssendsms("1");
            ems_sentinfo.setOrdertype("1");
            ems_sentinfo.setOrganid(organid);
            ems_sentinfo.setOrganname(organname);
            ems_sentinfo.setOrderno(wx_code);
            ems_sentinfo.setStatus("0");
            ems_sentinfo.setRemovetag("0");
            if(ywlb.equals("1")) {//出生登记
            	njpm = "出生登记";
              }else if(ywlb.equals("2")) {//迁出注销
            	  njpm ="迁出注销";
              }else if(ywlb.equals("3")) {//迁入和迁入审批业务
            	  if(sb.getSbzt().equals("1")) {//迁入审批登记
            		  njpm = "迁入审批登记";
            	  }else {//迁入业务
            		  njpm = "迁入业务";
            	  }
              }else if(ywlb.equals("4")) {//户籍删除
            	  njpm = "户籍删除";
              }else if(ywlb.equals("5")) {//变更更正
            	  njpm = "变更更正";
              }else if(ywlb.equals("6")) {//辅项变更
            	  njpm = "辅项变更";
              }else if(ywlb.equals("7")) {//死亡注销
            	  njpm = "死亡注销";
              }else if(ywlb.equals("8")) {//住址变动
            	  njpm = "住址变动";
              }else if(ywlb.equals("9")) {//户籍补录
            	  njpm = "户籍补录";
              }else if(ywlb.equals("16")) {//全户变更
            	  njpm = "全户变更";
              }else {
            	  njpm = ywlb;
              }
            ems_sentinfo.setInternals(njpm);
        	Class.forName(SystemConfig.getJdbcConfig("jdbc.driverClassEms"));
            xtba_conn = DriverManager.getConnection(
                    SystemConfig.getJdbcConfig("jdbc.urlEms"),
                    SystemConfig.getJdbcConfig("jdbc.userEms"),
                    SystemConfig.getJdbcConfig("jdbc.passwordEms"));
            
        	String querysql1 = "select organ_name,dhhm,pcsxz from  hzythpt.sys_organ h  where h.hjjg = ?";//hzythpt
        	xtba_stmt = xtba_conn.prepareStatement(querysql1);
        	xtba_stmt.setString(1, dwdm);
        	resultSet = xtba_stmt.executeQuery();
        	while(resultSet.next()){
                ems_sentinfo.setSender(resultSet.getString(1));
                ems_sentinfo.setSendertel(resultSet.getString(2));
                ems_sentinfo.setSenderaddress(resultSet.getString(3));
            }
        	String querysql2 = "select sjrxm,sjrlxdh,sjdz from  hzythpt.v_hzpt_apply h  where h.wx_code = ?";
        	xtba_stmt = xtba_conn.prepareStatement(querysql2);
        	xtba_stmt.setString(1, wx_code);
        	resultSet = xtba_stmt.executeQuery();
        	while(resultSet.next()){
                ems_sentinfo.setReceiver(resultSet.getString(1));
                ems_sentinfo.setReceiveraddress(resultSet.getString(3));
                ems_sentinfo.setReceivertel(resultSet.getString(2));
            }
        	String querysql3 = "select ywlx,ywms,sqrxm from  hzythpt.t_sl_sbxx h  where h.wx_code = ?";
        	xtba_stmt = xtba_conn.prepareStatement(querysql3);
        	xtba_stmt.setString(1, wx_code);
        	resultSet = xtba_stmt.executeQuery();
        	while(resultSet.next()){
                ems_sentinfo.setMatterid(resultSet.getString(1));//hzythpt.t_sl_sbxx表的id，查询条件为wx_code = 中间表的wx_code
                ems_sentinfo.setServicename(resultSet.getString(2));
                ems_sentinfo.setMatterproposer(resultSet.getString(3));
            }
        	if(ems_sentinfo.getReceiveraddress()!=null) {
        		String insertsql = "insert into hzythpt.ems_sentinfo (id,organid,organname,matterid,servicename,matterproposer,internals,sender,sendertel,senderaddress,receiver,receivertel,receiveraddress,orderno,ordertype,issendsms,status,removetag,createtime,lastedittime,jhsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,sysdate,sysdate)";
                xtba_stmt = xtba_conn.prepareStatement(insertsql);
                xtba_stmt.setString(1, ems_sentinfo.getId());
                xtba_stmt.setString(2, ems_sentinfo.getOrganid());
                xtba_stmt.setString(3, ems_sentinfo.getOrganname());
                xtba_stmt.setString(4, ems_sentinfo.getMatterid());
                xtba_stmt.setString(5, ems_sentinfo.getServicename());
                xtba_stmt.setString(6, ems_sentinfo.getMatterproposer());
                xtba_stmt.setString(7, ems_sentinfo.getInternals());
                xtba_stmt.setString(8, ems_sentinfo.getSender());
                xtba_stmt.setString(9, ems_sentinfo.getSendertel());
                xtba_stmt.setString(10, ems_sentinfo.getSenderaddress());
                xtba_stmt.setString(11, ems_sentinfo.getReceiver());
                xtba_stmt.setString(12, ems_sentinfo.getReceivertel());
                xtba_stmt.setString(13, ems_sentinfo.getReceiveraddress());
                xtba_stmt.setString(14, ems_sentinfo.getOrderno());
                xtba_stmt.setString(15, ems_sentinfo.getOrdertype());
                xtba_stmt.setString(16, ems_sentinfo.getIssendsms());
                xtba_stmt.setString(17, ems_sentinfo.getStatus());
                xtba_stmt.setString(18, ems_sentinfo.getRemovetag());
                xtba_stmt.execute();
        	}
            
        } catch (Exception e) {
            throw new java.lang.RuntimeException("插入邮政揽件表出错:"+e.toString());
        } finally {
            try {
                if (xtba_stmt != null) xtba_stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (xtba_conn != null) xtba_conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
		
	}
}
