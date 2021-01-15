package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.session.httpsession.WSSessionMgr;
import com.hzjc.hz2004.service.impl.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.*;
import org.hibernate.HibernateException;
import com.hzjc.util.StringUtils;
import java.sql.*;
import com.hzjc.wsstruts.common.db.DbUtils;
import com.hzjc.wsstruts.dao.hibernate.DefaultDAO;

import java.util.*;

import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import java.lang.reflect.*;

/**
 * 业务执行辅助类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class Hz2004ServiceHelper
    extends Hz2004BaseService {
  //日志处理
  private static Log _log = LogFactory.getLog(Hz2004ServiceHelper.class);
  private static Hz2004ServiceHelper _helper = null;

  /*
     private Hz2004ServiceHelper() {
     }
   */

  /**
   *
   * @return
   */
  public synchronized static Hz2004ServiceHelper getInstance() {
    if (_helper == null) {
      //_helper = new Hz2004ServiceHelper();
      _helper = Hz2004ServiceLocator.getInstance().getHz2004ServiceHelper();
    }
    return _helper;
  }

  /**
   *
   * @param strYhid
   * @param strIp
   * @return
   * @throws DAOException
   * @throws ServiceException
   */
  public static boolean VerifyUserIP(String strYhid, String strIp) throws
      DAOException, ServiceException {
    boolean bExist = false;
    try {
      //根据yhid和ip调用中间业务接口，判断该用户是否有该业务操作权限
      //_log.info("客户端IP限制判断-开始");
      bExist = XtywqxServiceImpl.VerifyUserIP(strYhid, strIp);
      //_log.info("客户端IP限制判断-结束");
      if (!bExist) {
        throw new ServiceException(WSErrCode.ERR_HANDLER_PACKET_IP,
                                   "业务功能调用时，用户ＩＰ受到限制！",
                                   null);
      }
      //closeSession();
      return bExist;
    }
    catch (Exception ex) {
      throw new DAOException("业务功能调用时，检验用户ＩＰ是否受到限制时发生异常", ex);
    }
  }

  /**
   *
   * @param sYhid
   * @param sYwid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public static boolean VerifyUserFunction(String strYhid, String strYwid) throws
      ServiceException, DAOException {
    boolean bRight = false;
    try {
      //根据yhid和code调用中间业务接口，判断该用户是否有该业务操作权限
      if (strYwid != null && !"F9801".equals(strYwid)) { //add by hh 20060209 比对审核业务不需要权限认证
        bRight = XtywqxServiceImpl.VerifyUserFunction(strYhid, strYwid);
        if (!bRight) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "用户没有该业务功能操作权限", null);
        }
      }
      return bRight;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
  }

  /**
   * 返回服务器时间
   * @return
   */
  public static String getServerDateTime() {
    return StringUtils.formateDateTime();
  }

  /**
   * 返回中间件版本
   * @return
   */
  public static String getServerVersion() {
    return com.hzjc.hz2004.Copyright.HZ2004_VERSION;
  }

  /**
   * 返回数据库的版本
   * @return
   */
  public static String getDatabaseVersion() {
    return null;
  }

  /**
   *
   * @param strAuthInfo
   * @param strVersion
   */
  public static void setCurClientVersion(String strAuthInfo, String strVersion) {
    //查找得到当前用户认证信息
    AuthToken atCurUser = BaseContext.getBaseUser();
    if (atCurUser != null) {
    	;//atCurUser.setClientversion(strVersion);

      //更新数据库中用户会话信息的版本号
      getInstance().updateUserSessionVersionToDb(strAuthInfo, strVersion);
    }
  }

  /**
   *
   * @param strAuthInfo
   * @param strVersion
   */
  protected void updateUserSessionVersionToDb(String strAuthInfo,
                                              String strVersion) {
    try {
      PojoInfo dao = DAOFactory.createXT_YHHHXXBDAO();
      String strHQL = "from PoXT_YHHHXXB where hhid='" + strAuthInfo + "'";

      List lstPo = super.findAllByHQL(strHQL);
      for (int i = 0; i < lstPo.size(); i++) {
        PoXT_YHHHXXB po = (PoXT_YHHHXXB) lstPo.get(i);
        po.setKhdbb(strVersion);
        super.update(po);
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
  }

  /**
   *
   * @param lMlpnbid
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public String assign2IDSlhByMlpnbid(Long lMlpnbid) throws
      ServiceException, DAOException {
    return IDJdbcDAO.assign2IDSlhByMlpnbid(lMlpnbid);
  }

  /**
   *
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public String assignSfzXmlSjbbh() throws ServiceException,
      DAOException {
    return IDJdbcDAO.assignSfzXmlSjbbh();
  }

  /**
   * 内部JDBC数据访问ＤＡＯ调用存储过程类
   * <p>Title: Hz2004</p>
   * <p>Description: 常住人口管理系统Hz2004版</p>
   * <p>Copyright: Copyright (c) 2004</p>
   * <p>Company: 浙江金铖华元新技术有限公司</p>
   * @author kgb_hz@126.com,kgb@primetech.com.cn
   * @version 1.0
   */
  private static class IDJdbcDAO
      extends DefaultDAO {

    /**
     * 根据门楼牌内部ID，分配得到二代证件受理编号
     * @param lMlpnbid  - 门楼牌内部ID
     * @return
     * @throws DAOException
     */
	    protected static String assign2IDSlhByMlpnbid(Long lMlpnbid) throws
        DAOException {
      String strSlh = null;
      CallableStatement cs = null;
      Connection conn = null;
      try {
        ///////////////////////////////////////////////////////////
        //取得数据库DB的连接
        //////////////////////////////////////////////////////////
    	  conn = SpringContextHolder.getCommonService().getConnection();

        String strRptCall = "{call RPT_GET_SLH(?,?)}";
        cs = conn.prepareCall(strRptCall);
        cs.setString(1, lMlpnbid.toString());
        ////////////////////////////////////////////////
        //注册输出参数
        cs.registerOutParameter(2, Types.CHAR);
        cs.execute();
        strSlh = cs.getString(2);
        DbUtils.close(cs);
      }
      catch (Exception ex) {
        try {
          DbUtils.close(cs);
          DbUtils.close(conn);
        }
        catch (HibernateException ex2) {
          throw convertHibernateAccessException(ex2);
        }
        throw new DAOException(WSErrCode.ERR_DB_GETID,
                               "取得二代证件办理业务受理号发生异常！", ex);
      }
      
      return strSlh;
    }

    /**
     * 生成身份证数据传输数据包编号
     * @return
     * @throws DAOException
     */
    protected static String assignSfzXmlSjbbh() throws DAOException {
      String strSjbbh = null;
      CallableStatement cs = null;
      Connection conn = null;
      try {
        ///////////////////////////////////////////////////////////
        //取得数据库DB的连接
        //////////////////////////////////////////////////////////
        conn = SpringContextHolder.getCommonService().getConnection();
        String strRptCall = "{call RPT_GET_SJBBH(?)}";
        cs = conn.prepareCall(strRptCall);
        ////////////////////////////////////////////////
        //注册输出参数
        cs.registerOutParameter(1, Types.CHAR);
        cs.execute();
        strSjbbh = cs.getString(1);
        DbUtils.close(cs);
      }
      catch (Exception ex) {
        try {
          DbUtils.close(cs);
          DbUtils.close(conn);
        }
        catch (HibernateException ex2) {
          throw convertHibernateAccessException(ex2);
        }
        
        throw new DAOException(WSErrCode.ERR_DB_GETID,
                               "取得身份证XML数据交换包编号发生异常！", ex);
      }
      return strSjbbh;
    }

  }

  ///////////////////////////////////////////////////////////////////////////
  //Add By Kgb 2005-01-05
  //处理流程说明：用户登录、用户注销、用户操作的会话Session全面重新修改过
  //     （针对WebLogic Server的集成Cluster：全面支持Server之间Session内容复制，以及
  //     后台数据库和中间件内存中之间的用户会话Session信息同步synchronized，中间件退出持久
  //     化到数据库，中间件重新启动自动从数据库中下载在线的用户信息。）
  //
  //关键难点切点：1、怎样清除数据库中的用户会话信息，保持数据库中的记录是鲜活的数据
  //            （内存中每隔一段时间自动回收一次《====》数据库中怎么处理？
  //              内存回收以后，关联回收数据库？可能还是不准？）
  ///////////////////////////////////////////////////////////////////////////


  /**
   * 校验判断用户的远端IP，判断用户的IP是否受到限制
   * @param strAuthInfo
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public static boolean handlerUserIP(String strAuthInfo, String strIP) throws
      ServiceException, DAOException {
    //==========================================================
    //得到远端的IP
    //==========================================================
    //String strIP = AxisUtils.getRemoteIP();

    //==========================================================
    //得到用户认证信息
    //==========================================================
    AuthToken auth = BaseContext.getBaseUser();
    if (auth == null) {
      throw new ServiceException("错误，用户认证信息不存在！");
    }

    //==========================================================
    //将用户IP属性添加到用户认证信息中
    //==========================================================
    //edit by kgb 2005-08-29
    //如果客户端包头的IP，不为空则设置其IP；Hander IP
    //双机时，从客户端的包头取IP，为了避免IP为空的情况，故进行判断
    if (strIP != null && !"".equals(strIP.trim())) {
      auth.setIp(strIP);
    }
    //end edit

    //根据yhid和ip调用中间业务接口，判断该用户是否有该业务操作权限
    String strYhid = String.valueOf(auth.getUser().getYhid());
    return VerifyUserIP(strYhid, strIP);
  }

  /**
   * 校验判断用户的会话Session
   * 处理流程:1、判断中间件内存中是否存在，存在则直接返回
   *         2、否则中间件内存中不存在，查询数据库判断是否存在？如果存在同步中间件内存；
   *            不存在，说明用户没有登录过。（实际上是将Cluster中的其他Server上的用户会话信息
   *            广播到其他的Server上。）
   * @param strAuthInfo
   * @return
   */
  public static boolean handlerUserSession(String strAuthInfo) {
    boolean bExist = false;
    //=================================================================
    //判断中间内存的用户会话信息
    //================================================================
    bExist = WSSessionMgr.getInstance().checkSession(strAuthInfo);

    //================================================================
    //内存中不存在，从数据库中查询判断该用户是否登录存在？
    //================================================================
    if (!bExist) {
      _log.warn("用户会话校验：内存中会话信息不存在；同步数据库中的用户会话信息到中间件内存中！");

      //查询用户会话信息表中是否存在
      //XT_YHHHXXBDAO xt_yhhhxxbDAO = DAOFactory.createXT_YHHHXXBDAO();
      String strYhhhxxHQL = "from PoXT_YHHHXXB where hhid='" + strAuthInfo + "'";
      List lstPoYhhhxx = SpringContextHolder.getCommonService().queryAll(strYhhhxxHQL);
      if (lstPoYhhhxx != null && lstPoYhhhxx.size() > 0) {
        PoXT_YHHHXXB poXT_YHHHXXB = (PoXT_YHHHXXB) lstPoYhhhxx.get(0);

        //查询用户信息表将用户信息保存到内存中
        String strYhxxHQL = "from PoXT_YHXXB where yhid=" +
            String.valueOf(poXT_YHHHXXB.getYhid());
        //XT_YHXXBDAO xt_yhxxbDAO = DAOFactory.createXT_YHXXBDAO();
        List lstPoYhxx = SpringContextHolder.getCommonService().queryAll(strYhxxHQL);
        if (lstPoYhxx != null && lstPoYhxx.size() > 0) {
          //将用户信息保存到内存中去
          PoXT_YHXXB poXT_YHXXB = (PoXT_YHXXB) lstPoYhxx.get(0);
       
            AuthToken authtoken = new AuthToken();
            try{
            BeanUtils.copyProperties(authtoken.getUser(),
                poXT_YHXXB);
            }catch(Exception e){
            	throw new ServiceException("拷贝属性出错！", e);
            }
            //将AuthInfo用户的认证令牌添加到Session中
            WSSessionMgr.getInstance().addSessionByAuthToken(strAuthInfo,
                authtoken);
 

          bExist = true;
        } //end if (lstPoYhxx != null && lstPoYhxx.size() > 0)
      } //end if (lstPoYhhhxx != null && lstPoYhhhxx.size() > 0) {
      else {
        bExist = false;
      }
    } //end if (!bExist) {
    return bExist;
  }

  /**
   * WebService SAOP调用中登录注册用户的Session
   * 处理流程:1、判断用户是否合法？不合法，异常返回
   *         2、合法，中间件内存中注册登记用户
   *         3、合法，数据库用户登录信息会话信息保存到临时表中
   * @param strUserId
   * @param strPassword
   * @return
   * @throws BaseException
   */
  public static String loginUser(String strUserId, String strPassword) throws
      BaseException {
    String strAuthInfo = "";
    AuthToken auth = null;
    try {
      //==================================================================
      //调用验证服务接口
      //==================================================================
      auth = Hz2004ServiceLocator.getInstance().getAuthTokenService().
          getSOAPAuthToken(strUserId, strPassword);

      //==================================================================
      //向内存中Session管理容器中注册
      //==================================================================
      strAuthInfo = WSSessionMgr.getInstance().addSessionByAuthToken(auth);

      //==================================================================
      //向数据库用户会话信息表中插入记录？
      //=================================================================
      String strIP = BaseContext.getContext().getRequest().getRemoteAddr();
      getInstance().addUserSessionToDb(auth.getUser().getYhid(), strAuthInfo, strIP);
    }
    catch (UnAuthException ex) {
      throw ex;
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
    return strAuthInfo;
  }

  public static String getKDSUser(String strUserId) throws BaseException,
      InvocationTargetException, IllegalAccessException {
    String strAuthInfo = null;

    //XT_YHHHXXBDAO xt_yhhhxxbDAO = DAOFactory.createXT_YHHHXXBDAO();
    //XT_YHXXBDAO xt_yhxxbDAO = DAOFactory.createXT_YHXXBDAO();

    //判断HZADMIN是否登录过
    String strYhxxHQL = "from PoXT_YHXXB where yhdlm='" + strUserId + "'";
    List lstPoYhxx = SpringContextHolder.getCommonService().queryAll(strYhxxHQL);
    if (lstPoYhxx != null && lstPoYhxx.size() > 0) {
      //获取账户
      PoXT_YHXXB poXT_YHXXB = (PoXT_YHXXB) lstPoYhxx.get(0);

      //判断回话是否存在
      String strYhhhxxHQL = "from PoXT_YHHHXXB where yhid=" +  String.valueOf(poXT_YHXXB.getYhid());
      List lstPoYhhhxx = SpringContextHolder.getCommonService().queryAll(strYhhhxxHQL);
      if (lstPoYhhhxx != null && lstPoYhhhxx.size() > 0) {
        //回话存在，曾经登录过，恢复回话
        PoXT_YHHHXXB poXT_YHHHXXB = (PoXT_YHHHXXB) lstPoYhhhxx.get(0);
        strAuthInfo = poXT_YHHHXXB.getHhid();

        //判断内存
        boolean bExist = WSSessionMgr.getInstance().checkSession(strAuthInfo);
        if(bExist){
          //内存存在，直接返回
          return strAuthInfo;
        }else{
          //内存不存在，加载内存中并返回
          AuthToken authtoken = new AuthToken();
          BeanUtils.copyProperties(authtoken.getUser(), poXT_YHXXB);

          //将AuthInfo用户的认证令牌添加到Session中
          WSSessionMgr.getInstance().addSessionByAuthToken(strAuthInfo, authtoken);
          return strAuthInfo;
        }
      }else{
        //用户没有登录过，那么完成登录
    	  AuthToken auth = new AuthToken();
        BeanUtils.copyProperties(auth, poXT_YHXXB);
        strAuthInfo = WSSessionMgr.getInstance().addSessionByAuthToken(auth);
        getInstance().addUserSessionToDb(auth.getUser().getYhid(), strAuthInfo, "127.0.0.1");
        return strAuthInfo;
      }
    }else{
      throw new BaseException("用户[" + strUserId + "]不存在！");
    }
  }

  /**
   * 用户会话信息保存到数据库中
   * @param lYhid
   * @param strAuthInfo
   */
  protected void addUserSessionToDb(Long lYhid, String strAuthInfo,
                                    String strYhip) {
    try {
      PojoInfo dao = DAOFactory.createXT_YHHHXXBDAO();
      PoXT_YHHHXXB po = new PoXT_YHHHXXB();
      Long lYhhhid = (Long) dao.getId();
      po.setYhhhid(lYhhhid);
      po.setHhid(strAuthInfo);
      po.setYhid(lYhid);
      po.setYhip(strYhip);
      super.create(po);

      //commitTransaction();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
  }

  /**
   * 从数据库中删除用户会话信息
   * @param strAuthInfo
   */
  public void removeUserSessionInDb(String strAuthInfo) {
    try {
      //XT_YHHHXXBDAO dao = DAOFactory.createXT_YHHHXXBDAO();
      String strHQL = "from PoXT_YHHHXXB where hhid='" + strAuthInfo + "'";
      super.delete(super.getObjectListByHql(strHQL));
      
     // commitTransaction();
    }
    catch (DAOException ex) {
      throw ex;
    }
    catch (ServiceException ex) {
      throw ex;
    }
  }

  /**
   * Web网页Http请求方式的登录注册用户
   * 处理流程：同上
   * @param strUserId
   * @param strPassword
   * @param strIp
   * @return
   * @throws BaseException
   */
  public static String loginUser(String strUserId, String strPassword,
                                 String strIp) throws BaseException {
    //=============================================================socket测试开始
    /*try {
      com.hzjc.socket.Client.start("localhost");
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }*/
    //=============================================================socket测试结束
    String strAuthInfo = "";
    AuthToken auth = null;
    try {
      //=================================================================
      //调用用户合法性验证接口
      //=================================================================
      auth = Hz2004ServiceLocator.getInstance().getAuthTokenService().
          getWebAuthToken(strUserId, strPassword, strIp);

      //==================================================================
      //向内存中Session管理容器中注册
      //==================================================================
      strAuthInfo = WSSessionMgr.getInstance().addSessionByAuthToken(auth);

      //==================================================================
      //向数据库用户会话信息表中插入记录？
      //==================================================================
      getInstance().addUserSessionToDb(auth.getUser().getYhid(), strAuthInfo, strIp);
    }
    catch (UnAuthException ex) {
      ex.printStackTrace();
      throw ex;
    }
    return strAuthInfo;
  }

  /**
   * 注销退出业务系统
   * 处理流程:1、中间件内存中销毁用户的会话信息。
   *         2、销毁数据库中用户登录临时会话信息。
   * @param strAuthInfo
   */
  public static void logoutUser(String strAuthInfo) {
    //清除HttpSession
    /*
         javax.servlet.http.HttpSession hs = AxisUtils.getHttpSession();
         if (hs != null) {
      hs.invalidate();
         }
     */
    //从Session容器中注销掉认证明令牌.
    WSSessionMgr.getInstance().removeSession(strAuthInfo);
    //删除用户登录临时会话信息
    getInstance().removeUserSessionInDb(strAuthInfo);
  }

  /**
   * 查询所有在线用户
   * @return
   * @throws <{ServiceException}>
   */
  public static VoUserInfo[] queryAllActiveUsers() throws ServiceException {
	  return null;
  }

  /**
   * 从数据库中同步Session信息到中间件内存
   * 处理流程:1、WebLogicServer重新启动时，从后台数据库中自动加载所有用户的会话信息
   *         （Hz2004CodeServlet中Init方法中，调用该接口。）
   */
  public static void syncSessionMemoryFromDB() {
    String strYhxxHQL =
        "select poYhxx,poYhhhxx.hhid,poYhhhxx.yhip,poYhhhxx.khdbb " +
        "from PoXT_YHXXB as poYhxx,PoXT_YHHHXXB as poYhhhxx " +
        "where poYhxx.yhid = poYhhhxx.yhid";
  }
}
