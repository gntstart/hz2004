package com.hzjc.hz2004.service.impl;

import java.util.*;
import java.sql.*;
import java.io.*;
import com.hzjc.wsstruts.common.db.DbUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.service.ZwService;
import com.hzjc.util.*;
import com.hzjc.hz2004.common.HjCommon;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.dao.*;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import com.hzjc.hz2004.po.PoZJLS_SFZYWCZB;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.wsstruts.common.IDGenerator;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.appbase.SysParam;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.wsstruts.common.db.DefaultDataSource;
import javax.sql.DataSource;
import com.hzjc.wsstruts.common.db.ConnectionManager;


/**
 * <p>Title:指纹信息实现类 </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: </p>
 * @author hubin
 * @version 1.0
 */
@Service
public class ZwServiceImpl
    extends HjCommon  implements ZwService{

  /**
   * 浙江指纹信息保存
   * @param voZwxx voZwxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwxxSave(VoZwTjxx[] voZwtjxx) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String now = StringUtils.getServiceTime();
    String sidzwbh = "";

    conn = getConn("zwjndi");
    if (conn == null) { //如果连接不上数据库
      conn = ConnectionManager.getConnection();
      if(conn == null){
          throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                 "无法建立与指纹库的连接", null);
      }
    }
    /* 设定不自动提交 */
    conn.setAutoCommit(false);

    try {
      for (int i = 0; i < voZwtjxx.length; i++) {
        String zwidSQL = "select SID_ZWXX_ZWJBXXB.NEXTVAL from dual";
        ps = conn.prepareStatement(zwidSQL);
        rs = ps.executeQuery(zwidSQL);
        if (rs.next()) {
          sidzwbh =String.valueOf(getIdBySequence(rs.getLong(1)));
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "获取SID_ZWXX_ZWJBXXB失败。", null);
        }

        //增加异地采集信息
        String zwbh = sidzwbh;
        Long ryid = voZwtjxx[i].getRyid();
        String gmsfhm = voZwtjxx[i].getGmsfhm()==null?"":voZwtjxx[i].getGmsfhm();
        String xm = voZwtjxx[i].getXm()==null?"":voZwtjxx[i].getXm();
        String xb = voZwtjxx[i].getXb()==null?"":voZwtjxx[i].getXb();
        String zwbm = voZwtjxx[i].getZwbm()==null?"":voZwtjxx[i].getZwbm();
        String zwtzsj = voZwtjxx[i].getZwtzsj()==null?"":voZwtjxx[i].getZwtzsj();
        float txzlz = String.valueOf(voZwtjxx[i].getTxzlz())==null?0:voZwtjxx[i].getTxzlz();
        String zwzcjg = voZwtjxx[i].getZwzcjg()==null?"":voZwtjxx[i].getZwzcjg();
        String zwcjqid = voZwtjxx[i].getZwcjqid()==null?"":voZwtjxx[i].getZwcjqid();
        String zwcjqdm = voZwtjxx[i].getZwcjqdm()==null?"":voZwtjxx[i].getZwcjqdm();
        String zwsfbbh = voZwtjxx[i].getZwsfbbh()==null?"":voZwtjxx[i].getZwsfbbh();
        String zwsfkfzdm = voZwtjxx[i].getZwsfkfzdm()==null?"":voZwtjxx[i].getZwsfkfzdm();
        String cjsj = now;
        Long czrid = this.getUserInfo().getYhid();
        String czrdwdm = this.getUserInfo().getDwdm();
        String czrip = BaseContext.getUser().getIp();
        String zfbz = "0"; //0在用，1作废
        String zwtx = voZwtjxx[i].getZwtx();

        //测试开始
//        gmsfhm="11111111111111111";
//        zwbm="11";
//        zwtzsj="zwtzsj029848dk328r402uyfj38rjdf82hfkmw38hyrndfiw23y";
//        Random rd = new Random();
//        txzlz= Float.parseFloat(String.valueOf(rd.nextFloat()*10).substring(0,3));
        //测试结束

        if (zwtx.length() <= 0 || zwtx == null){
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATAINVALATE,
                                     "指纹图像获取失败，长度小于0或者没有图像。", null);
        }

        String insertSQL = "INSERT INTO ZWXX_ZWJBXXB (ZWBH,RYID,GMSFHM,XM,"
            + "XB,ZWBM,ZWTZSJ,TXZLZ,ZWZCJG,"
            + "ZWCJQID,ZWCJQDM,ZWSFBBH,ZWSFKFZDM,"
            + "CJSJ,CZRID,CZRDWDM,CZRIP,ZFBZ) "
            + " VALUES(" + zwbh + "," + ryid.toString() + ",'" + gmsfhm + "','" + xm
            + "','" + xb + "','" + zwbm + "','" + zwtzsj + "'," + txzlz + ",'" + zwzcjg
            + "','" + zwcjqid + "','" + zwcjqdm + "','" + zwsfbbh + "','" + zwsfkfzdm
            + "','" + cjsj + "'," + czrid.toString() + ",'" + czrdwdm + "','" + czrip + "','" + zfbz + "')";
        //_log.info(insertSQL);
        ps.executeQuery(insertSQL);

        byte[] bzwtx = Base64.getDecoder().decode(zwtx.getBytes());
        insertSQL = "insert into ZWXX_ZWKZXXB(ZWBH,ZWTX,TXZLZ) "
            + " values(?,?,?)";
        ps = null;
        ps = conn.prepareStatement(insertSQL);
        ps.setLong(1,Long.parseLong(zwbh));
        ps.setBinaryStream(2, new ByteArrayInputStream(bzwtx), bzwtx.length);
        ps.setFloat(3, txzlz);
        //_log.info(insertSQL);
        ps.execute();

        conn.commit();
      }
    }
    catch (ServiceException ex) {
      //回滚事务
      conn.rollback();
      throw ex;
    }
    catch (DAOException ex) {
      //回滚事务
      conn.rollback();
      throw ex;
    }
    catch (Exception ex) {
      //回滚事务
      conn.rollback();
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    finally {
      //关闭游标
      conn.close();
      DbUtils.close(rs);
      if (ps != null) {
        ps.close();
      }
    }

    return sidzwbh;
  }

  /**
   * 浙江指纹基本信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwjbxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StringBuffer strFromHQL = new StringBuffer();
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    strFromHQL.append("select * from ZWXX_ZWJBXXB where 1=1 ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append(" and ").append(strHQL);
    }

    conn = getConn("zwjndi");

    if (conn == null) { //如果连接不上数据库
        conn = ConnectionManager.getConnection();
        if(conn == null){
            throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                   "无法建立与指纹库的连接", null);
        }
    }

    try {
      ps = conn.prepareStatement(strFromHQL.toString());
      //_log.info(strFromHQL.toString());
      rs = ps.executeQuery(strFromHQL.toString());

      voList = new ArrayList();
      for (; rs.next(); ) {
        VoZwjbxxfhxx vozwjbxx = new VoZwjbxxfhxx();

        vozwjbxx.setZwbh(Long.valueOf(rs.getString("ZWBH")));
        vozwjbxx.setRyid(Long.valueOf(rs.getString("RYID")));
        vozwjbxx.setGmsfhm(rs.getString("GMSFHM"));
        vozwjbxx.setXm(rs.getString("XM"));
        vozwjbxx.setXb(rs.getString("XB"));
        vozwjbxx.setZwbm(rs.getString("ZWBM"));
        vozwjbxx.setZwtzsj(rs.getString("ZWTZSJ"));
        vozwjbxx.setTxzlz(rs.getFloat("TXZLZ"));
        vozwjbxx.setZwzcjg(rs.getString("ZWZCJG"));
        vozwjbxx.setZwcjqid(rs.getString("ZWCJQID"));
        vozwjbxx.setZwcjqdm(rs.getString("ZWCJQDM"));
        vozwjbxx.setZwsfbbh(rs.getString("ZWSFBBH"));
        vozwjbxx.setZwsfkfzdm(rs.getString("ZWSFKFZDM"));
        vozwjbxx.setCjsj(rs.getString("CJSJ"));
        vozwjbxx.setCzrid(rs.getString("CZRID") == null ? Long.valueOf("0") :
                          Long.valueOf(rs.getString("CZRID")));
        vozwjbxx.setCzrdwdm(rs.getString("CZRDWDM"));
        vozwjbxx.setCzrip(rs.getString("CZRIP"));
        vozwjbxx.setZfbz(rs.getString("ZFBZ"));
        vozwjbxx.setZfyy(rs.getString("ZFYY"));
        vozwjbxx.setZfsj(rs.getString("ZFSJ"));
        vozwjbxx.setZfczrid(rs.getString("ZFCZRID") == null ? Long.valueOf("0") :
                            Long.valueOf(rs.getString("ZFCZRID")));
        vozwjbxx.setZfczrdwdm(rs.getString("ZFCZRDWDM"));
        vozwjbxx.setZfczrip(rs.getString("ZFCZRIP"));

        voList.add(vozwjbxx);
      }
      conn.commit();
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    finally {
      if (conn != null) {
        conn.close();
      }
      //关闭游标
      DbUtils.close(rs);
      if (ps != null) {
        ps.close();
      }
    }

    voQueryResult.setPagelist(voList);

    voQueryResult.setVotype(VoZwjbxxfhxx.class);

    return voQueryResult;
  }

  /**
   * 浙江指纹扩展信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwkzxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StringBuffer strFromHQL = new StringBuffer();
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    strFromHQL.append("select ZWBH,ZWTX,TXZLZ from ZWXX_ZWKZXXB where 1=1 ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append(" and ").append(strHQL);
    }

    conn = getConn("zwjndi");

    if (conn == null) { //如果连接不上数据库
        conn = ConnectionManager.getConnection();
        if(conn == null){
            throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,"无法建立与指纹库的连接", null);
        }
    }

    try {
      ps = conn.prepareStatement(strFromHQL.toString());
      //_log.info(strFromHQL.toString());
      rs = ps.executeQuery(strFromHQL.toString());

      voList = new ArrayList();
      for (; rs.next(); ) {
        VoZwkzxxfhxx vozwkzxx = new VoZwkzxxfhxx();
        vozwkzxx.setZwbh(Long.valueOf(rs.getString("ZWBH")));
        if (rs.getString("ZWTX") != null) {
          vozwkzxx.setZwtx(new String(Base64.getEncoder().encode(rs.getString("ZWTX").getBytes())));
        }
        vozwkzxx.setTxzlz(rs.getFloat("TXZLZ"));
        voList.add(vozwkzxx);
      }
      conn.commit();
    }
    catch (ServiceException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    finally {
      if (conn != null) {
        conn.close();
      }
      //关闭游标
      DbUtils.close(rs);
      if (ps != null) {
        ps.close();
      }
    }

    voQueryResult.setPagelist(voList);

    voQueryResult.setVotype(VoZwjbxxfhxx.class);

    return voQueryResult;
  }

  /**
   * 浙江指纹受理信息保存
   * @param VoZwslxx voZwslxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwslxxSave(VoZwslxx[] voZwslxx) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement preparedstatement = null;
    ResultSet rs = null;
    String now = StringUtils.getServiceTime();
    long sidnbslid = 0;

    conn = getConn("zwjndi");
    if (conn == null) { //如果连接不上数据库
        conn = ConnectionManager.getConnection();
       if(conn == null){
           throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                  "无法建立与指纹库的连接", null);
       }
    }

    /* 设定不自动提交 */
    conn.setAutoCommit(false);

    try {
      //_log.info("开始事务");

      for (int i = 0; i < voZwslxx.length; i++) {
        PojoInfo  zjyw_slxxbDAO = DAOFactory.createZJYW_SLXXBDAO();
        PojoInfo  zjls_sfzywczbDAO = DAOFactory.createZJLS_SFZYWCZBDAO();
        String strHQL = "from PoZJYW_SLXXB where slh='" + voZwslxx[i].getSlh() + "'";
        List li =super.findAllByHQL(strHQL);
        if (li.size() != 1) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                     "找不到指纹信息对应的证件受理信息，无法完成指纹受理业务保存。", null);
        }
        //得到受理信息
        PoZJYW_SLXXB poSlxx = (PoZJYW_SLXXB) li.get(0);

        String zwidSQL = "select SID_ZWYW_ZWSLXXB.NEXTVAL from dual";
        preparedstatement = conn.prepareStatement(zwidSQL);
        rs = preparedstatement.executeQuery(zwidSQL);
        if (rs.next()) {
          sidnbslid = getIdBySequence(rs.getLong(1));
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "指纹库获取SID_ZWYW_ZWSLXXB失败。", null);
        }

        preparedstatement = null;
        String insertSQL = "INSERT INTO ZWYW_ZWSLXXB (NBSLID,SLH,RYID,RYNBID,"
            + "ZWYBH,ZWYZW,ZWYZCJG,ZWEBH,ZWEZW,ZWEZCJG,HCBZ,CZSJ,CZRID,CZRDWDM,"
            + "GMSFHM,XM,XB,MZ,CSRQ,CSDSSXQ,"
            + "MLPNBID,SSXQ,JLX,MLPH,MLXZ,PCS,ZRQ,XZJD,JCWH,PXH,"
            + "SLZT,SLYY,QFJG,ZZ,ZZLX,LQFS,SFLX,SFJE,ZLHKZT,HKSJ) "
            + " VALUES(?,?,?,?,"
            + "?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?,?,"
            + "?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?,?,?,?,?,?)";

        preparedstatement = conn.prepareStatement(insertSQL);
        preparedstatement.setLong(1, sidnbslid);
        preparedstatement.setString(2, voZwslxx[i].getSlh());
        preparedstatement.setLong(3, Long.parseLong(poSlxx.getRyid().toString()));
        preparedstatement.setLong(4, Long.parseLong(poSlxx.getRynbid().toString()));

        //指纹一编号
        if (voZwslxx[i].getZwybh() == null) {
          preparedstatement.setNull(5, Types.DOUBLE);
        }
        else {
          preparedstatement.setLong(5, Long.parseLong(voZwslxx[i].getZwybh().toString()));
        }
        //指纹一指位
        if (voZwslxx[i].getZwyzw() == null) {
          preparedstatement.setNull(6, Types.CHAR);
        }
        else {
          preparedstatement.setString(6, voZwslxx[i].getZwyzw());
        }
        //指纹一注册结果
        if (voZwslxx[i].getZwyzcjg() == null) {
          preparedstatement.setNull(7, Types.CHAR);
        }
        else {
          preparedstatement.setString(7, voZwslxx[i].getZwyzcjg());
        }
        //指纹二编号
        if (voZwslxx[i].getZwebh() == null) {
          preparedstatement.setNull(8, Types.DOUBLE);
        }
        else {
          preparedstatement.setLong(8, Long.parseLong(voZwslxx[i].getZwebh().toString()));
        }
        //指纹二指位
        if (voZwslxx[i].getZwezw() == null) {
          preparedstatement.setNull(9, Types.CHAR);
        }
        else {
          preparedstatement.setString(9, voZwslxx[i].getZwezw());
        }
        //指纹二注册结果
        if (voZwslxx[i].getZwezcjg() == null) {
          preparedstatement.setNull(10, Types.CHAR);
        }
        else {
          preparedstatement.setString(10, voZwslxx[i].getZwezcjg());
        }
        preparedstatement.setString(11, "0"); //合成标志
        preparedstatement.setString(12, now); //操作时间
        preparedstatement.setLong(13, Long.parseLong(this.getUserInfo().getYhid().toString())); //操作人ID
        preparedstatement.setString(14, this.getUserInfo().getDwdm()); //操作人单位代码

        preparedstatement.setString(15, poSlxx.getGmsfhm());
        //姓名
        if (poSlxx.getXm() == null) {
          preparedstatement.setNull(16, Types.CHAR);
        }
        else {
          preparedstatement.setString(16, poSlxx.getXm());
        }
        //性别
        if (poSlxx.getXb() == null) {
          preparedstatement.setNull(17, Types.CHAR);
        }
        else {
          preparedstatement.setString(17, poSlxx.getXb());
        }
        //民族
        if (poSlxx.getMz() == null) {
          preparedstatement.setNull(18, Types.CHAR);
        }
        else {
          preparedstatement.setString(18, poSlxx.getMz());
        }
        //出生日期
        if (poSlxx.getCsrq() == null) {
          preparedstatement.setNull(19, Types.CHAR);
        }
        else {
          preparedstatement.setString(19, poSlxx.getCsrq());
        }
        //出生地省市县区
        if (poSlxx.getCsdssxq() == null) {
          preparedstatement.setNull(20, Types.CHAR);
        }
        else {
          preparedstatement.setString(20, poSlxx.getCsdssxq());
        }
        //门楼牌内部ID
        if (poSlxx.getMlpnbid() == null) {
          preparedstatement.setNull(21, Types.DOUBLE);
        }
        else {
          preparedstatement.setLong(21, Long.parseLong(poSlxx.getMlpnbid().toString()));
        }
        //省市县区
        if (poSlxx.getSsxq() == null) {
          preparedstatement.setNull(22, Types.CHAR);
        }
        else {
          preparedstatement.setString(22, poSlxx.getSsxq());
        }
        //街路巷
        if (poSlxx.getJlx() == null) {
          preparedstatement.setNull(23, Types.CHAR);
        }
        else {
          preparedstatement.setString(23, poSlxx.getJlx());
        }
        //门楼牌号
        if (poSlxx.getMlph() == null) {
          preparedstatement.setNull(24, Types.CHAR);
        }
        else {
          preparedstatement.setString(24, poSlxx.getMlph());
        }
        //门楼详址
        if (poSlxx.getMlxz() == null) {
          preparedstatement.setNull(25, Types.CHAR);
        }
        else {
          preparedstatement.setString(25, poSlxx.getMlxz());
        }
        //派出所
        if (poSlxx.getPcs() == null) {
          preparedstatement.setNull(26, Types.CHAR);
        }
        else {
          preparedstatement.setString(26, poSlxx.getPcs());
        }
        //责任区
        if (poSlxx.getZrq() == null) {
          preparedstatement.setNull(27, Types.CHAR);
        }
        else {
          preparedstatement.setString(27, poSlxx.getZrq());
        }
        //乡镇街道
        if (poSlxx.getXzjd() == null) {
          preparedstatement.setNull(28, Types.CHAR);
        }
        else {
          preparedstatement.setString(28, poSlxx.getXzjd());
        }
        //居村委会
        if (poSlxx.getJcwh() == null) {
          preparedstatement.setNull(29, Types.CHAR);
        }
        else {
          preparedstatement.setString(29, poSlxx.getJcwh());
        }
        //排序号
        if (poSlxx.getPxh() == null) {
          preparedstatement.setNull(30, Types.CHAR);
        }
        else {
          preparedstatement.setString(30, poSlxx.getPxh());
        }

        preparedstatement.setString(31,poSlxx.getSlzt());
        //受理原因
        if (poSlxx.getSlyy() == null) {
          preparedstatement.setNull(32, Types.CHAR);
        }
        else {
          preparedstatement.setString(32, poSlxx.getSlyy());
        }
        //签发机关
        if (poSlxx.getQfjg() == null) {
          preparedstatement.setNull(33, Types.CHAR);
        }
        else {
          preparedstatement.setString(33, poSlxx.getQfjg());
        }
        //住址
        if (poSlxx.getZz() == null) {
          preparedstatement.setNull(34, Types.CHAR);
        }
        else {
          preparedstatement.setString(34, poSlxx.getZz());
        }
        //制证类型
        if (poSlxx.getZzlx() == null) {
          preparedstatement.setNull(35, Types.CHAR);
        }
        else {
          preparedstatement.setString(35, poSlxx.getZzlx());
        }
        //领证方式
        if (poSlxx.getLqfs() == null) {
          preparedstatement.setNull(36, Types.CHAR);
        }
        else {
          preparedstatement.setString(36, poSlxx.getLqfs());
        }
        //收费类型
        if (poSlxx.getSflx() == null) {
          preparedstatement.setNull(37, Types.CHAR);
        }
        else {
          preparedstatement.setString(37, poSlxx.getSflx());
        }
        //收费金额
        if (poSlxx.getSfje() == null) {
          preparedstatement.setNull(38, Types.FLOAT);
        }
        else {
          preparedstatement.setFloat(38,Float.parseFloat(poSlxx.getSfje().toString()));
        }
        //质量回馈状态
        if (poSlxx.getZlhkzt() == null) {
          preparedstatement.setNull(39, Types.CHAR);
        }
        else {
          preparedstatement.setString(39, poSlxx.getZlhkzt());
        }
        //回馈时间
        if (poSlxx.getZlhkzt() == null) {
          preparedstatement.setNull(40, Types.CHAR);
        }
        else {
          preparedstatement.setString(40, poSlxx.getHksj());
        }
        //_log.info(insertSQL);
        preparedstatement.execute();

        //更新证件受理信息
        poSlxx.setSlzt("06");//已采集指纹
       super.update(poSlxx);

        //增加证件业务操作信息
        PoZJLS_SFZYWCZB posfzywczb = new PoZJLS_SFZYWCZB();
        posfzywczb.setZjywid( (Long) zjls_sfzywczbDAO.getId());
        if (getUserInfo() != null) {
          posfzywczb.setCzyid(this.getUserInfo().getYhid());
          posfzywczb.setCzyxm(this.getUserInfo().getYhxm());
        }
        else {
        }
        posfzywczb.setSlh(poSlxx.getSlh());
        posfzywczb.setSlzt(poSlxx.getSlzt());
        posfzywczb.setYwbz("F6004"); //业务标志
        posfzywczb.setCzsj(now); //操作时间
        posfzywczb.setCzip(BaseContext.getUser().getIp());
        super.create(posfzywczb);
      }

      //_log.info("提交事务");
      conn.commit();

    }
    catch (ServiceException ex) {
      //回滚事务
      conn.rollback();
      throw ex;
    }
    catch (DAOException ex) {
      //回滚事务
      conn.rollback();
      throw ex;
    }
    catch (Exception ex) {
      //回滚事务
      conn.rollback();
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    finally {
      //关闭游标
      conn.close();
      DbUtils.close(rs);
      if (preparedstatement != null) {
        preparedstatement.close();
      }
    }


    return (String.valueOf(sidnbslid));
  }

  /**
   * 生成规则：ParseLong[DBID(前3位) + DBID(后6位)] * 10000000000L + SequenceID
   * 规则修改：ParseLong[DBID(后6位) + DBID(前3位)] * 10000000000L + SequenceID
   * @param name
   * @return
   */
  private long getIdBySequence(long id) throws DAOException {
    long lSeqid = 0;
    try {
      //根据系统控制参数表得到DBID的前3位和DBID的后6位
      VoXT_XTKZCSB voBefore3 = null;
      VoXT_XTKZCSB voAfter6 = null;
      try {
        voBefore3 = SysParam.getXt_xtkzcsb(PublicConstant.
                                           XTKZCS_DBID_BEFORE_3);
        voAfter6 = SysParam.getXt_xtkzcsb(PublicConstant.
                                          XTKZCS_DBID_AFTER_6);
      }
      catch (ServiceException ex) {
        //从内存中取不到系统控制参数,然后,可以从数据库中取.
      }
      //如果为空，则从数据库中取数据
      if (voBefore3 == null || voAfter6 == null) {
        PojoInfo  dao = DAOFactory.createXT_XTKZCSBDAO();
        String strBefore3 =
            "from PoXT_XTKZCSB  where kzlb='" +
            PublicConstant.XTKZCS_DBID_BEFORE_3 + "'";
        String strAfter6 =
            "from PoXT_XTKZCSB  where kzlb='" +
            PublicConstant.XTKZCS_DBID_AFTER_6 + "'";
        List lstPo3 =super.findAllByHQL(strBefore3);
        List lstPo6 =super.findAllByHQL(strAfter6);
        if (lstPo3 != null && !lstPo3.isEmpty()) {
          voBefore3 = new VoXT_XTKZCSB( (PoXT_XTKZCSB) lstPo3.get(0));
        }
        if (lstPo6 != null && !lstPo6.isEmpty()) {
          voAfter6 = new VoXT_XTKZCSB( (PoXT_XTKZCSB) lstPo6.get(0));
        }
      }
      //得到前9位
      long lID = 0;
      if (voBefore3 != null && voAfter6 != null) {
        if (voBefore3.getKzz() != null && voAfter6.getKzz() != null) {
          lID = Long.parseLong(voAfter6.getKzz().trim().concat(voBefore3.
              getKzz().trim()));
        }
        else {
          throw new DAOException(WSErrCode.ERR_DB_GETID,
                                 "系统控制参数表中，控制参数代码为（9000和9001）对应的数据值没有设置。", null);
        }
      }
      else {
        throw new DAOException(WSErrCode.ERR_DB_GETID,
                               "系统控制参数表中，控制参数代码为（9000和9001）对应的数据值没有设置。", null);
      }
      //返回最后取得的ID
      lID = lID * 10000000000L + id;
      lSeqid = lID;

    }
    catch (Exception sqle) {
      throw new DAOException(WSErrCode.ERR_DB_GETID, sqle);
    }
    return lSeqid;
  }

  /**
   * 浙江指纹受理信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryZwslxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StringBuffer strFromHQL = new StringBuffer();
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    strFromHQL.append("select * from ZWYW_ZWSLXXB where 1=1 ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append(" and ").append(strHQL);
    }

    conn = getConn("zwjndi");

    if (conn == null) { //如果连接不上数据库
        conn = ConnectionManager.getConnection();
        if(conn == null){
            throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                   "无法建立与指纹库的连接", null);
        }
    }

      try {
        ps = conn.prepareStatement(strFromHQL.toString());
        //_log.info(strFromHQL.toString());
        rs = ps.executeQuery(strFromHQL.toString());

        voList = new ArrayList();
        for (; rs.next(); ) {
          VoZwslxxfhxx vozwslxx = new VoZwslxxfhxx();
//          BeanUtils.copyProperties(vozwslxx, rs);

          vozwslxx.setNbslid(Long.valueOf(rs.getString("NBSLID")));
          vozwslxx.setSlh(rs.getString("SLH"));
          vozwslxx.setRyid(Long.valueOf(rs.getString("RYID")));
          vozwslxx.setRynbid(Long.valueOf(rs.getString("RYNBID")));
          vozwslxx.setZwybh(rs.getString("ZWYBH"));
          vozwslxx.setZwyzw(rs.getString("ZWYZW"));
          vozwslxx.setZwyzcjg(rs.getString("ZWYZCJG"));
          vozwslxx.setZwebh(rs.getString("ZWEBH"));
          vozwslxx.setZwezw(rs.getString("ZWEZW"));
          vozwslxx.setZwezcjg(rs.getString("ZWEZCJG"));
          vozwslxx.setHcbz(rs.getString("HCBZ"));
          vozwslxx.setCzsj(rs.getString("CZSJ"));
          vozwslxx.setCzrid(Long.valueOf(rs.getString("CZRID")));
          vozwslxx.setCzrdwdm(rs.getString("CZRDWDM"));

          vozwslxx.setGmsfhm(rs.getString("GMSFHM"));
          vozwslxx.setXm(rs.getString("XM"));
          vozwslxx.setXb(rs.getString("XB"));
          vozwslxx.setMz(rs.getString("MZ"));
          vozwslxx.setCsrq(rs.getString("CSRQ"));
          vozwslxx.setCsdssxq(rs.getString("CSDSSXQ"));
          vozwslxx.setMlpnbid(Long.valueOf(rs.getString("MLPNBID")));
          vozwslxx.setSsxq(rs.getString("SSXQ"));
          vozwslxx.setJlx(rs.getString("JLX"));
          vozwslxx.setMlph(rs.getString("MLPH"));
          vozwslxx.setMlxz(rs.getString("MLXZ"));
          vozwslxx.setPcs(rs.getString("PCS"));
          vozwslxx.setZrq(rs.getString("ZRQ"));
          vozwslxx.setXzjd(rs.getString("XZJD"));
          vozwslxx.setJcwh(rs.getString("JCWH"));
          vozwslxx.setPxh(rs.getString("PXH"));

          vozwslxx.setSlzt(rs.getString("SLZT"));
          vozwslxx.setSlyy(rs.getString("SLYY"));
          vozwslxx.setQfjg(rs.getString("QFJG"));
          vozwslxx.setZz(rs.getString("ZZ"));
          vozwslxx.setZzlx(rs.getString("ZZLX"));
          vozwslxx.setLqfs(rs.getString("LQFS"));
          vozwslxx.setSflx(rs.getString("SFLX"));
          vozwslxx.setSfje(Float.valueOf(rs.getString("SFJE")));
          vozwslxx.setZlhkzt(rs.getString("ZLHKZT"));
          vozwslxx.setHksj(rs.getString("HKSJ"));

          voList.add(vozwslxx);
        }
        conn.commit();
      }
      catch (ServiceException ex) {
        throw ex;
      }
      catch (DAOException ex) {
        throw ex;
      }
      catch (Exception ex) {
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
      }
      finally {
        if (conn != null) {
          conn.close();
        }
        //关闭游标
        DbUtils.close(rs);
        if (ps != null) {
          ps.close();
        }
      }


    voQueryResult.setPagelist(voList);

    voQueryResult.setVotype(VoZwjbxxfhxx.class);

    return voQueryResult;
  }

  /**
   * 浙江指纹信息作废
   * @param VoZwZfxx voZwzfxx[]
   * @throws Exception
   * @return Long
   */
  public String processZwxxZF(VoZwZfxx[] voZwzfxx) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String now = StringUtils.getServiceTime();
    String ret = "0";

    conn = getConn("zwjndi");
    if (conn == null) { //如果连接不上数据库
        conn = ConnectionManager.getConnection();
        if(conn == null){
            throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                   "无法建立与指纹库的连接", null);
        }
    }

      /* 设定不自动提交 */
      conn.setAutoCommit(false);

      try {
        for (int i = 0; i < voZwzfxx.length; i++) {
          String insertSQL = "UPDATE ZWXX_ZWJBXXB "
              + " SET ZFBZ='1',ZFYY=?,ZFSJ=?,ZFCZRID=?,ZFCZRDWDM=?,ZFCZRIP=? "
              + " WHERE ZWBH=" + String.valueOf(voZwzfxx[i].getZwbh());
          ps = conn.prepareStatement(insertSQL);
          //指纹一指位
          if (voZwzfxx[i].getZfyy() == null) {
            ps.setNull(1, Types.CHAR);
          }
          else {
            ps.setString(1, voZwzfxx[i].getZfyy());
          }
          ps.setString(2,now);
          ps.setLong(3,Long.parseLong(this.getUserInfo().getYhid().toString()));
          ps.setString(4,this.getUserInfo().getDwdm());
          ps.setString(5,BaseContext.getUser().getIp());

          //_log.info(insertSQL);
          ps.execute();

          conn.commit();

          ret = voZwzfxx[i].getZwbh().toString();
        }

      }
      catch (ServiceException ex) {
        //回滚事务
        conn.rollback();
        throw ex;
      }
      catch (DAOException ex) {
        //回滚事务
        conn.rollback();
        throw ex;
      }
      catch (Exception ex) {
        //回滚事务
        conn.rollback();
        throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
      }
      finally {
        //关闭游标
        conn.close();
        DbUtils.close(rs);
        if (ps != null) {
          ps.close();
        }
      }

    return ret;
  }
}
