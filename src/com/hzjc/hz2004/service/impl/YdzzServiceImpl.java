package com.hzjc.hz2004.service.impl;

/**
 * 浙江异地办证业务类
 * <p>Title: </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.*;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.io.*;
import com.hzjc.wsstruts.common.db.DbUtils;
import com.hzjc.wsstruts.vo.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.hz2004.service.YdzzService;
import com.hzjc.util.*;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
@Service
public class YdzzServiceImpl
    extends HjCommon
    implements YdzzService {

//  public Connection getConn(String psJndi) {
//    Connection connection1;
//    DataSource datasource = null;
//    try {
//      InitialContext initialcontext = new InitialContext();
//      try {
//        datasource = (DataSource) initialcontext.lookup(psJndi);
//      }
//      catch (NamingException namingexception) {
//        Context context = (Context) initialcontext.lookup("hz2004jndi");
//        datasource = (DataSource) context.lookup(psJndi);
//      }
//      connection1 = datasource.getConnection();
//      return connection1;
//    }
//    catch (Exception exception) {
//      return null;
//    }
//  }

  /**
   * 浙江异地二代证业务保存
   * @param voYdcjhqxx VoYdcjhqxx[]
   * @throws Exception
   * @return Long
   */
  public String processYdzzxxSave(VoYdcjhqxx[] voYdcjhqxx) throws
      ServiceException, DAOException, Exception {
    String now = StringUtils.getServiceTime();
    Connection conn = null;
    PreparedStatement preparedstatement = null;
    String sCjid = null;
    String sZpid = null;
    ResultSet rs = null;

    conn = getConn("ydcjjndi");
    if (conn == null) { //如果连接不上数据库
      throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                             "无法建立与省库连接", null);
    }
    else {
      /* 设定不自动提交 */
      conn.setAutoCommit(false);

      try {
        for (int i = 0; i < voYdcjhqxx.length; i++) {
          String dwdm = null;
          String dwmc = null;
          StringBuffer strHQL = new StringBuffer();
          strHQL.append("select * from YDCJ_CJDBMB where ip like to_char('%,")
              .append(BaseContext.getUser().getIp())
              .append(",%')");
          preparedstatement = conn.prepareStatement(strHQL.toString());
          rs = preparedstatement.executeQuery(strHQL.toString());
          if (!rs.next()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "由于IP限制，您没有权限进行此操作，请与管理员联系。", null);
          }
          else {
            dwdm = rs.getString("CJDID");
            dwmc = rs.getString("CJDMC");
          }
          if (rs.next()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "您的操作权限同时存在多个办证点，请与管理员联系。", null);
          }

          StringBuffer strXXHQL = new StringBuffer();
          strXXHQL.append("select * from YDCJ_YDCJXXB where gmsfhm='")
              .append(voYdcjhqxx[i].getGmsfhm())
              .append("' and cjzt = 0 ");
          rs = preparedstatement.executeQuery(strXXHQL.toString());
          if (rs.next()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "该人信息已采集过。", null);
          }

          String cjidSQL = "select SID_YDCJ_YDCJXXB.NEXTVAL from dual";
          rs = preparedstatement.executeQuery(cjidSQL);
          if (rs.next()) {
            sCjid = rs.getString("NEXTVAL");
          }
          else {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "获取不到SID_YDCJ_YDCJXXB。", null);
          }

          String zpidSQL = "select SID_YDCJ_YDCJZPB.NEXTVAL from dual";
          rs = preparedstatement.executeQuery(zpidSQL);
          if (rs.next()) {
            sZpid = rs.getString("NEXTVAL");
          }
          else {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "获取不到SID_YDCJ_YDCJZPB。", null);
          }

          //增加异地采集信息
          String cjid = sCjid;
          String ssxq = voYdcjhqxx[i].getSsxq();
          String gmsfhm = voYdcjhqxx[i].getGmsfhm();
          String xm = voYdcjhqxx[i].getXm();
          String xb = voYdcjhqxx[i].getXb();
          String csrq = voYdcjhqxx[i].getCsrq();
          String mz = voYdcjhqxx[i].getMz();
          String xzzssxq = voYdcjhqxx[i].getXzzssxq();
          String zz = voYdcjhqxx[i].getZz();
          String xzz = voYdcjhqxx[i].getXzz();
          String lxdh = voYdcjhqxx[i].getLxdh();
          String zpid = sZpid;
          String cjdid = dwdm;
          String cjdmc = dwmc;
          String cjdip = BaseContext.getUser().getIp();
          String cjssxq = this.getUserInfo().getDwdm().substring(0, 4) + "00";
          Long czyid = this.getUserInfo().getYhid();
          String czydlm = this.getUserInfo().getYhdlm();
          String czyxm = this.getUserInfo().getYhxm();
          String czsj = now;
          String cjzt = "0"; //0已采集，1已受理，2作废
          String bz = voYdcjhqxx[i].getBz();
          String bwtrgmsfhm = voYdcjhqxx[i].getBwtrgmsfhm();
          String bwtrxm = voYdcjhqxx[i].getBwtrxm();
          String bwtrgx = voYdcjhqxx[i].getBwtrgx();

          String insertSQL = "INSERT INTO YDCJ_YDCJXXB (CJID,SSXQ,GMSFHM,XM,"
              + "XB,CSRQ,MZ,ZZ,XZZ,LXDH,ZPID,CJDID,CJDMC,CJDIP,CJSSXQ,CZYID,"
              + "CZYDLM,CZYXM,CZSJ,CJZT,BZ,BWTRGMSFHM,BWTRXM,BWTRGX,XZZSSXQ) "
              + " VALUES(" + cjid + ",'" + ssxq + "','" + gmsfhm + "','" + xm +
              "','"
              + xb + "','" + csrq + "','" + mz + "','" + zz + "','" + xzz +
              "','"
              + lxdh + "'," + zpid + ",'" + cjdid + "','" + cjdmc + "','" +
              cjdip + "','"
              + cjssxq + "'," + czyid + ",'" + czydlm + "','" + czyxm + "','" +
              czsj + "','"
              + cjzt + "','" + bz + "','" + bwtrgmsfhm + "','" + bwtrxm + "','" +
              bwtrgx + "','" + xzzssxq + "')";
          preparedstatement.executeQuery(insertSQL);

          byte[] sdzp = Base64.getDecoder().decode(voYdcjhqxx[i].getZp().getBytes());
          // Base64.decodeBase64(strCzrkXp.getBytes())
          String lrsj = now;
          insertSQL = "INSERT INTO YDCJ_YDCJZPB (ZPID,GMSFHM,XM,LRSJ)"
              + " VALUES(" + zpid + ",'" + gmsfhm + "','" + xm + "','" + lrsj +
              "')";
          preparedstatement.executeQuery(insertSQL);

          /* 清空原BLOB对象 */
          preparedstatement.executeUpdate(
              "UPDATE YDCJ_YDCJZPB SET SDZP = EMPTY_BLOB() WHERE ZPID = " +
              zpid);
          /* 查询此BLOB对象并锁定 */
          String strQuery =
              "SELECT SDZP AS BLOBFIELD FROM YDCJ_YDCJZPB WHERE ZPID = " + zpid +
              " FOR UPDATE";
          rs = preparedstatement.executeQuery(strQuery);
          
          while (rs.next()) {
            if (rs.getClass().getName().toString().equals( //weblogic的连接池
                "weblogic.jdbc.wrapper.ResultSet_oracle_jdbc_driver_OracleResultSetImpl")) {
              //取出此BLOB对象
              //weblogic.jdbc.wrapper.Blob blob = (weblogic.jdbc.wrapper.Blob) rs. getBlob("BLOBFIELD");
             // oracle.sql.BLOB oblob = (oracle.sql.BLOB) blob.getVendorObj();
              //向BLOB对象中写入数据
              BufferedOutputStream out =null;// new BufferedOutputStream(oblob.getBinaryOutputStream());
              BufferedInputStream in =
                  new BufferedInputStream(new ByteArrayInputStream(sdzp));
              int c;
              while ( (c = in.read()) != -1) {
                out.write(c);
              }
              in.close();
              out.close();
            }

            if (rs.getClass().getName().toString().equals( //webSphere的连接池
                "com.ibm.ws.rsadapter.jdbc.WSJdbcResultSet")) {
              //取出此BLOB对象
              oracle.sql.BLOB oblob = (oracle.sql.BLOB) rs.
                  getBlob("BLOBFIELD");
              //向BLOB对象中写入数据
              BufferedOutputStream out = null;
                  //new BufferedOutputStream(oblob.getBinaryOutputStream());
              BufferedInputStream in =
                  new BufferedInputStream(new ByteArrayInputStream(sdzp));
              int c;
              while ( (c = in.read()) != -1) {
                out.write(c);
              }
              in.close();
              out.close();
            }
          }

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
        if (preparedstatement != null) {
          preparedstatement.close();
        }
      }
    }

    return sCjid;


  }

  /**
   * 浙江异地二代证采集信息查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryYdcjxx(String sCxqxkz, String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement preparedstatement = null;
    ResultSet rs = null;
    ResultSet rsCjbmb = null;
    StringBuffer strFromHQL = new StringBuffer();
    StringBuffer strSQL = new StringBuffer();
    String sCjdid = null;
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    strFromHQL.append("select * from YDCJ_YDCJXXB where 1=1 ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append(" and ").append(strHQL);
    }

    conn = getConn("ydcjjndi");
    if (conn == null) { //如果连接不上数据库
      throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                             "无法建立与省库连接", null);
    }
    else {
      try {
        if ( (!this.getUserInfo().getYhdlm().trim().equals("HZADMIN")) &&
            (!"1".equals(sCxqxkz))) {
          strSQL.append("select * from YDCJ_CJDBMB where ip like to_char('%,")
              .append(BaseContext.getUser().getIp())
              .append(",%')");
          preparedstatement = conn.prepareStatement(strSQL.toString());
          rsCjbmb = preparedstatement.executeQuery(strSQL.toString());
          if (rsCjbmb.next()) {
            sCjdid = rsCjbmb.getString("CJDID");
            strFromHQL.append(" and ( cjdid ='" + sCjdid + "' ");
            for (; rsCjbmb.next(); ) {
              sCjdid = rsCjbmb.getString("CJDID");
              strFromHQL.append(" or cjdid ='" + sCjdid + "' ");
            }
            strFromHQL.append(" ) ");
          }
          else {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "由于IP限制，您没有权限进行此操作，请与管理员联系。", null);
          }

        }
        else {
          preparedstatement = conn.prepareStatement(strFromHQL.toString());
        }
        rs = preparedstatement.executeQuery(strFromHQL.toString());

        voList = new ArrayList();
        for (; rs.next(); ) {
          VoYdcjfhxx voYdcjxx = new VoYdcjfhxx();

          voYdcjxx.setCjid(Long.valueOf(rs.getString("CJID")));
          voYdcjxx.setSsxq(rs.getString("SSXQ"));
          voYdcjxx.setGmsfhm(rs.getString("GMSFHM"));
          voYdcjxx.setXm(rs.getString("XM"));
          voYdcjxx.setXb(rs.getString("XB"));
          voYdcjxx.setCsrq(rs.getString("CSRQ"));
          voYdcjxx.setMz(rs.getString("MZ"));
          voYdcjxx.setZz(rs.getString("ZZ"));
          voYdcjxx.setXzz(rs.getString("XZZ"));
          voYdcjxx.setLxdh(rs.getString("LXDH"));
          voYdcjxx.setZpid(Long.valueOf(rs.getString("ZPID")));
          voYdcjxx.setCjdid(rs.getString("CJDID"));
          voYdcjxx.setCjdmc(rs.getString("CJDMC"));
          voYdcjxx.setCjdip(rs.getString("CJDIP"));
          voYdcjxx.setCjssxq(rs.getString("CJSSXQ"));
          voYdcjxx.setCzyid(Long.valueOf(rs.getString("CZYID")));
          voYdcjxx.setCzydlm(rs.getString("CZYDLM"));
          voYdcjxx.setCzyxm(rs.getString("CZYXM"));
          voYdcjxx.setCzsj(rs.getString("CZSJ"));
          voYdcjxx.setCjzt(rs.getString("CJZT"));
          voYdcjxx.setBz(rs.getString("BZ"));
          voYdcjxx.setBwtrgmsfhm(rs.getString("BWTRGMSFHM"));
          voYdcjxx.setBwtrxm(rs.getString("BWTRXM"));
          voYdcjxx.setBwtrgx(rs.getString("BWTRGX"));
          voYdcjxx.setXzzssxq(rs.getString("XZZSSXQ"));

          voList.add(voYdcjxx);
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
        if (preparedstatement != null) {
          preparedstatement.close();
        }
      }
    }

    voQueryResult.setPagelist(voList);

    voQueryResult.setVotype(VoYdcjfhxx.class);

    return voQueryResult;
  }

  /**
   * 浙江异地二代证采集信息照片查询
   * @param strHQL
   * @param voPage
   * @return VoQueryResult
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQueryResult queryYdcjzpxx(String strHQL, VoPage voPage) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement preparedstatement1 = null;
    ResultSet rs = null;
    StringBuffer strFromHQL = new StringBuffer();
    List voList = null;
    VoQueryResult voQueryResult = new VoQueryResult();

    strFromHQL.append(
        "select sdzp,zpid,gmsfhm,xm,lrsj from YDCJ_YDCJZPB where 1=1 ");
    if (strHQL != null && strHQL.trim().length() > 0) {
      strFromHQL.append(" and ").append(strHQL);
    }
    else {
      return null;
    }

    conn = getConn("ydcjjndi");
    if (conn == null) { //如果连接不上数据库
      throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                             "无法建立与省库连接", null);
    }
    else {
      try {

        preparedstatement1 = conn.prepareStatement(strFromHQL.toString());
        rs = preparedstatement1.executeQuery(strFromHQL.toString());

        voList = new ArrayList();

        for (; rs.next(); ) {
          VoYdcjzpfhxx voYdcjxx = new VoYdcjzpfhxx();
          String strColumnValue = null;

          if (rs.getClass().getName().toString().equals( //weblogic的连接池
              "weblogic.jdbc.wrapper.ResultSet_oracle_jdbc_driver_OracleResultSetImpl")) {
            //weblogic.jdbc.wrapper.Blob blob = (weblogic.jdbc.wrapper.Blob) rs. getBlob(1);
            oracle.sql.BLOB oblob = null;//(oracle.sql.BLOB) blob.getVendorObj();

            //直接转换为byte
            InputStream output = oblob.getBinaryStream();
            try {
              byte[] tmpByte = getBytes(output);
              strColumnValue = tmpByte == null ? "" :
                  new String(Base64.getEncoder().encode(tmpByte)); //进行Base64编码
            }
            catch (Exception ex) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                         "BOLB字段转换失败", null);
            }
          }

          if (rs.getClass().getName().toString().equals( //webSphere的连接池
              "com.ibm.ws.rsadapter.jdbc.WSJdbcResultSet")) {
            oracle.sql.BLOB oblob = (oracle.sql.BLOB) rs.getBlob(1);
            //直接转换为byte
            InputStream output = oblob.getBinaryStream();
            try {
              byte[] tmpByte = getBytes(output);
              strColumnValue = tmpByte == null ? "" :
                  new String(Base64.getEncoder().encode(tmpByte)); //进行Base64编码
            }
            catch (Exception ex) {
              throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                         "BOLB字段转换失败", null);
            }
          }

          voYdcjxx.setZpid(Long.valueOf(rs.getString("ZPID")));
          voYdcjxx.setGmsfhm(rs.getString("GMSFHM"));
          voYdcjxx.setXm(rs.getString("XM"));
          voYdcjxx.setLrsj(rs.getString("LRSJ"));
          if (strColumnValue != null || strColumnValue != "") {
            voYdcjxx.setSdzp(strColumnValue);
          }

          voList.add(voYdcjxx);
        }

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
        //关闭游标
        if (conn != null) {
          conn.close();
        }
        DbUtils.close(rs);
        if (preparedstatement1 != null) {
          preparedstatement1.close();
        }
      }

    }

    voQueryResult.setPagelist(voList);
    voQueryResult.setVotype(VoYdcjzpfhxx.class);
    return voQueryResult;
  }

  /**
   * 浙江异地二代证信息作废
   * @param strCjid String
   * @throws ServiceException
   * @throws DAOException
   * @throws Exception
   * @return String
   */
  public String processYdzzxxZF(String strCjid) throws
      ServiceException, DAOException, Exception {
    Connection conn = null;
    PreparedStatement preparedstatement = null;
    ResultSet rs = null;
    ResultSet rsCjbmb = null;
    StringBuffer strFromHQL = new StringBuffer();
    StringBuffer strSQL = new StringBuffer();
    StringBuffer strUpdate = new StringBuffer();
    String sCjdid = null;
    boolean bUp = false;

    strFromHQL.append("select * from YDCJ_YDCJXXB where 1=1 ");
    strFromHQL.append(" and cjzt = '0' ");

    strUpdate.append("UPDATE YDCJ_YDCJXXB SET CJZT = 2 WHERE 1=1 ");
    strUpdate.append(" and cjzt = '0' ");

    if (strCjid != null && strCjid.trim().length() > 0) {
      strFromHQL.append(" and cjid = ").append(strCjid);
      strUpdate.append(" and cjid = ").append(strCjid);
    }

    conn = getConn("ydcjjndi");
    if (conn == null) { //如果连接不上数据库
      throw new DAOException(WSErrCode.ERR_ACTION_SERVICE_NOTEXIST,
                                 "无法建立与省库连接", null);
    }
    else {
      try {
        if (!this.getUserInfo().getYhdlm().trim().equals("HZADMIN")) {
          strSQL.append("select * from YDCJ_CJDBMB where ip like to_char('%,")
              .append(BaseContext.getUser().getIp())
              .append(",%')");
          preparedstatement = conn.prepareStatement(strSQL.toString());
          rsCjbmb = preparedstatement.executeQuery(strSQL.toString());
          if (rsCjbmb.next()) {
            sCjdid = rsCjbmb.getString("CJDID");
          }
          else {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "由于IP限制，您没有权限进行此操作，请与管理员联系。", null);
          }
          if (rsCjbmb.next()) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                       "您的操作权限同时存在多个办证点，请与管理员联系。", null);
          }
          strFromHQL.append(" and cjdid ='" + sCjdid + "'");
          strUpdate.append(" and cjdid ='" + sCjdid + "'");
        }
        else {
          preparedstatement = conn.prepareStatement(strFromHQL.toString());
        }

        rs = preparedstatement.executeQuery(strFromHQL.toString());

        if (rs.next()) {
          bUp = true;
        }
        else {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATASCOPE,
                                     "没有采集ID为" + strCjid + "的信息。", null);
        }
        if (rs.next()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "采集ID为" + strCjid + "的信息有大于等于2条。", null);
        }
        if (bUp) {
          preparedstatement.executeUpdate(strUpdate.toString());
        }

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
        if (conn != null) {
          conn.close();
        }
        DbUtils.close(rs);
        if (preparedstatement != null) {
          preparedstatement.close();
        }
      }
    }

    return strCjid;
  }

  /**
   * InputStream转换为byte
   * @param is
   * @return
   */
  public static byte[] getBytes(InputStream is) throws Exception {
    byte[] data = null;

    Collection chunks = new ArrayList();
    byte[] buffer = new byte[1024 * 1000];
    int read = -1;
    int size = 0;

    while ( (read = is.read(buffer)) != -1) {
      if (read > 0) {
        byte[] chunk = new byte[read];
        System.arraycopy(buffer, 0, chunk, 0, read);
        chunks.add(chunk);
        size += chunk.length;
      }
    }

    if (size > 0) {
      ByteArrayOutputStream bos = null;
      try {
        bos = new ByteArrayOutputStream(size);
        for (Iterator itr = chunks.iterator(); itr.hasNext(); ) {
          byte[] chunk = (byte[]) itr.next();
          bos.write(chunk);
        }
        data = bos.toByteArray();
      }
      finally {
        if (bos != null) {
          bos.close();
        }
      }
    }
    return data;
  }

  public static void main(String agrs[]) {

  }
}
