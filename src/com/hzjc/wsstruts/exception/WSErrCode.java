/* Generated by Together */

package com.hzjc.wsstruts.exception;

import java.io.*;
import java.util.*;

import org.apache.commons.logging.*;
import org.dom4j.*;
import org.dom4j.io.*;
import com.hzjc.wsstruts.type.PacketTag;
import com.hzjc.wsstruts.common.config.*;

/**
 * 异常、错误代码定义-描述
 * 不过异常处理，还有一层异常：表现层--》在服务器端捕捉的，必须在客户端捕捉
 *   客户端捕捉RemoteException（由网络等其他原因产生的？？？）
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */
public class WSErrCode {
  //日志处理
  private static Log _log = LogFactory.getLog(WSErrCode.class);

  private static Map htErrs = null;
  private static WSErrCode _wserrcode = null;
  //异常配置文件及其用的TAG********************************************************
  private static String _ErrCfgXml = "conf/hz2004.exception.xml";
  private static final String ERR_TAG_ERRS = "errs";
  private static final String ERR_TAG_ERR = "err";
  private static final String ERR_TAG_CODE = "code";
  private static final String ERR_TAG_DESC = "desc";

//私有构造函数，静态类没必要实例化
  private WSErrCode() {
  }

  //============================================================================
  //1：表示层错误代码定义**********************************************************
  //****************************************************************************

   //2：控制层错误代码定义**********************************************************
   //加载全局的变量，包括：Exception异常的配置-数据字典-国标代码-系统控制参数-加载JDBC数据库配置(hibernate.cfg.xml)
   public static final int ERR_CTRL_LOAD_CFGEXCEPTION = 201; //hzz004.exception.xml加载有误
  public static final int ERR_CTRL_LOAD_DATADICTIONARY = 202; //数据字典加载异常
  public static final int ERR_CTRL_LOAD_SYSCTRLPARAM = 203; //系统控制参数异常
  public static final int ERR_CTRL_LOAD_SYSPARAM = 204; //加载系统参数异常
  public static final int ERR_CTRL_LOAD_DBCONFIG = 205; //加载数据库连接的配置文件异常
  public static final int ERR_CTRL_ACTION_NOTEXIST = 206; //applictaion.bean.xml中没有请求的Action配置

  public static final int ERR_CTRL_LOAD_DWXXB = 210; //加载系统单位信息异常
  public final static int ERR_CTRL_LOAD_JLXXXB = 211; //街路巷
  public final static int ERR_CTRL_LOAD_JWHXXB = 212; //居委会
  public final static int ERR_CTRL_LOAD_JWZRQXXB = 213; //警务责任区
  public final static int ERR_CTRL_LOAD_XZQHB = 214; //行政区划
  public final static int ERR_CTRL_LOAD_XZJDXXB = 215; //乡镇街道
  //new 2006
  public final static int ERR_CTRL_LOAD_YHXXB = 216; //用户信息

  //****************************************************************************

   //3：通信包处理层代码定义********************************************************
   public static final int ERR_HANDLER_PACKET_INVALIDATION = 301; //无效的通信请求包
  public static final int ERR_HANDLER_PACKET_SESSIONTIMEOUT = 302; //用户的会话Session超时
  public static final int ERR_HANDLER_PACKET_UNAUTHENTICATION = 303; //该用户不存在，身份认证
  public static final int ERR_HANDLER_PACKET_UNAUTHORIZATION = 304; //没有权限，权限认证
  public static final int ERR_HANDLER_PACKET_PARSE = 305; //解析包发生发生异常
  public static final int ERR_HANDLER_PACKET_BUILD = 306; //生成组包发生异常
  public static final int ERR_HANDLER_PACKET_LOG = 307; //日志处理异常
  public static final int ERR_HANDLER_PACKET_ERRBUILD = 308; //异常发生时组包处理异常
  public static final int ERR_HANDLER_PACKET_DONOTHING = 309; //没进行处理
  public static final int ERR_HANDLER_PACKET_ENCRYPT = 310; //包加密编码发生异常
  public static final int ERR_HANDLER_PACKET_DECRYPT = 311; //包解密编码发生异常
  public static final int ERR_HANDLER_PACKET_IP = 312; //通讯包的客户端IP限制
  //****************************************************************************

   //4：动作层代码定义*************************************************************
   public static final int ERR_ACTION_PARAM_ISEMPTY = 401; //Action中Param为空“”
  public static final int ERR_ACTION_PARAM_NOTEXIST = 402; //Action中Param不存在
  public static final int ERR_ACTION_SERVICE_NOTEXIST = 403; //application.bean.xml中没有Service配置
  //new 2006
  public static final int ERR_ACTION_SERVICE_NOACCESS = 404; //没有得到操作权限异常

  public static final int ERR_ACTION_PACKET_READPACKHEADDATA = 410; //Action中读包中的变量数据发生异常
  public static final int ERR_ACTION_PACKET_READPACKCONTROLDATA = 411; //Action中读包中的记录集数据发生异常
  public static final int ERR_ACTION_PACKET_READPACKVARDATA = 412; //Action中读包中的记录集数据发生异常
  public static final int ERR_ACTION_PACKET_READPACKVARXQL = 413; //Action中读包中的记录集数据发生异常
  public static final int ERR_ACTION_PACKET_READPACKRSDATA = 414; //Action中读包中的记录集数据发生异常
  public static final int ERR_ACTION_PACKET_READPACKDATA = 415; //Action中读包中的记录集数据发生异常
  public static final int ERR_ACTION_PACKET_LOADFROMXML = 419; //加载请求包发生异常

  public static final int ERR_ACTION_PACKET_WRITEPACKHEADDATA = 420; //Action中向包中写变量数据有发生异常
  public static final int ERR_ACTION_PACKET_WRITEPACKCONTROLDATA = 421; //Action中向包中写记录集数据发生异常
  public static final int ERR_ACTION_PACKET_WRITEPACKVARDATA = 422; //Action中向包中写记录集数据发生异常
  public static final int ERR_ACTION_PACKET_WRITEPACKRSDATA = 423; //Action中向包中写记录集数据发生异常
  public static final int ERR_ACTION_PACKET_WRITEPACKDATA = 424; //Action中向包中写数据发生异常
  public static final int ERR_ACTION_PACKET_SAVETOXML = 429; //保存请求包发生异常
  public static final int ERR_ACTION_UNKNOW = 499; //其他未知的业务异常
  //****************************************************************************

   //5：业务层代码定义
   public static final int ERR_SERVICE_DAO_NOTEXIST = 501; //application.bean.xml中没有请求的DAO配置不存在
  public static final int ERR_SERVICE_UNAUTHORIZATION = 502; //没有操作权限
  public static final int ERR_SERVICE_BUSSINESSLOGIC = 503; //业务逻辑错误
  public static final int ERR_SERVICE_DATANOTFULL = 504; //数据不完整异常
  public static final int ERR_SERVICE_DATASCOPE = 505; //数据范围异常
  public static final int ERR_SERVICE_DATAINVALATE = 506; //数据格式或长度无效
  public static final int ERR_SERVICE_BUSSINESSLIMIT = 507; //业务限制异常
  public static final int ERR_SERVICE_TRANSACTION = 598; //业务层事务异常
  public static final int ERR_SERVICE_OTHER = 599; //其他未知的业务异常

  //bini_min@hotmail.com _begin
  public static final int ERR_SERVICE_BGGZSXYSP = 508; //变更更正受限于审批
  public static final int ERR_SERVICE_HCYHZ = 509;//户成员户主异常
  public static final int ERR_SERVICE_HCYPO = 510;//户成员配偶异常
  public static final int ERR_SERVICE_QRDJSXYSP = 511;//迁入登记受限于审批
  public static final int ERR_SERVICE_HJBLSXYSP = 512;//户籍补录受限于审批
  public static final int ERR_SERVICE_HJSCSXYSP = 513;//户籍删除受限于审批
  public static final int ERR_SERVICE_HBBGSXYSP = 514;//户别变更受限于审批
  //_end
  //****************************************************************************

   //6：数据库层代码定义(Hibernate Exception)**************************************
   //===========Hibernate初始化配置时Configure发生的异常============================
   public static final int ERR_DB_PROPERTYNOTFOUNDEX = 601;
  public static final int ERR_DB_MAPPINGEX = 602;
  //===========Session操作时发生的异常============================================
  public static final int ERR_DB_JDBCEX = 603;
  public static final int ERR_DB_QUERYEX = 604;
  public static final int ERR_DB_OBJECTNOTFOUNDEX = 605;
  //===========Hibernate中不经常发生Exception=====================================
  public static final int ERR_DB_WRONGCLASSEX = 606;
  public static final int ERR_DB_VALIDATIONFAILURE = 607;
  public static final int ERR_DB_PROPERTYACCESSEX = 608;
  public static final int ERR_DB_INSTANTIATIONEX = 609;
  public static final int ERR_DB_STALEOBJECTSTATEEX = 610;
  public static final int ERR_DB_TRANSIENTOBJECTEX = 611;
  public static final int ERR_DB_PERSISTENTOBJECTEX = 612;
  public static final int ERR_DB_CALLBACKEX = 613;
  public static final int ERR_DB_HIBERNATEEX = 619;
  //============数据库操作的业务逻辑上判断的异常（多用户同时操作的异常）================
  public static final int ERR_DB_OBJECTDELETEDEX = 620;
  public static final int ERR_DB_OBJECTEDITEDEX = 621;
  //============数据库事务异常====================================================
  public static final int ERR_DB_TRANSACTIONEX = 630;
  //==============其他数据库异常=================================
  public static final int ERR_DB_GETID = 640;
  public static final int ERR_DB_HZ2004IDCFGXML = 641;

  //=====================数据库报表JDBC操作异常========================
  public static final int ERR_DB_REPORT_JDBCEX = 650;
  //****************************************************************************
   //其他数据库异常
   public static final int ERR_DB_OTHER = 699;

  //7:基础层错误代码定义*********************************************************
  //***************************************************************************

   //init错误、异常代码-描述初始化**************************************************
   static {
     init();
   }

  /**
   *
   */
  private static void init() {
    //if (htErrs == null) {
    htErrs = new TreeMap();
    htErrs.clear();
    ///////////////////////////////////////////////////////////////////////
    //1：表示层错误代码定义
    ///////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////
    //2：控制层错误代码定义
    ///////////////////////////////////////////////////////////////////////
    //加载全局的变量，包括：Exception异常的配置-数据字典-国标代码-系统控制参数-
    htErrs.put(new Integer(ERR_CTRL_LOAD_CFGEXCEPTION),
               "<控制层：加载、映射、解析XML资源配制文件出错>");
    htErrs.put(new Integer(ERR_CTRL_LOAD_DATADICTIONARY), "<控制层：数据字典载入异常>");
    htErrs.put(new Integer(ERR_CTRL_LOAD_DWXXB), "<控制层：系统单位信息表载入异常>");
    htErrs.put(new Integer(ERR_CTRL_LOAD_SYSCTRLPARAM),
               "<控制层：系统控制参数载入异常>");
    htErrs.put(new Integer(ERR_CTRL_LOAD_SYSPARAM),
               "<控制层：系统参数载入异常>");
    htErrs.put(new Integer(ERR_CTRL_LOAD_DBCONFIG),
               "<控制层：JDBC数据库连接的配置文件加载异常>");
    htErrs.put(new Integer(ERR_CTRL_ACTION_NOTEXIST),
               "<控制层：hz2004.application.bean.xml中没有请求的Action配置>");

    ///////////////////////////////////////////////////////////////////
    //3：通信包处理层代码定义
    /////////////////////////////////////////////////////////////////////
    htErrs.put(new Integer(ERR_HANDLER_PACKET_INVALIDATION),
               "<通信包处理层：无效非法的通信包>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_SESSIONTIMEOUT),
               "<通信包处理层：用户的会话Session超时，请重新登录>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_UNAUTHENTICATION),
               "<通信包处理层：身份认证,验证失败>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_UNAUTHORIZATION),
               "<通信包处理层：权限认证,没有操作权限>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_PARSE), "<通信包处理层：解析通信包发生异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_BUILD), "<通信包处理层：生成通信包发生异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_LOG), "<通信包处理层：日志处理异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_ERRBUILD),
               "<通信包处理层：生成异常响应通信包发生异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_ENCRYPT), "<通信包处理层：通信包加密发生异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_DECRYPT), "<通信包处理层：通信包解密发生异常>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_DONOTHING), "<通信包处理层：通信包未进行处理>");
    htErrs.put(new Integer(ERR_HANDLER_PACKET_IP), "<通信包处理层:操作用户IP受到限制>");

    //////////////////////////////////////////////////////////////////
    //4：动作层代码定义
    //////////////////////////////////////////////////////////////////
    htErrs.put(new Integer(ERR_ACTION_PARAM_ISEMPTY),
               "<动作请求层：请求Action中参数Param为空“”>");
    htErrs.put(new Integer(ERR_ACTION_PARAM_NOTEXIST),
               "<动作请求层：请求Action中参数Param不存在>");
    htErrs.put(new Integer(ERR_ACTION_SERVICE_NOTEXIST),
               "<动作请求层：hz2004.application.bean.xml中没有Service配置>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKHEADDATA),
               "<动作请求层：请求Action中解析读取包头部分数据发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKCONTROLDATA),
               "<动作请求层：请求Action中解析读取包控制部分数据发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKVARDATA),
               "<动作请求层：请求Action中解析读取包中变量数据发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKVARXQL),
               "<动作请求层：请求Action中解析读取包变量数据中的查询XQL发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKRSDATA),
               "<动作请求层：请求Action中解析读取包中记录集数据发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_READPACKDATA),
               "<动作请求层：请求Action中解析读取包中的节点元素数据发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_LOADFROMXML),
               "<动作请求层：加载解析请求XML包发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_WRITEPACKHEADDATA),
               "<动作请求层：请求Action中向包头部分写变量数据有发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_WRITEPACKCONTROLDATA),
               "<动作请求层：请求Action中向包控制部分写变量数据有发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_WRITEPACKVARDATA),
               "<动作请求层：请求Action中向包中写变量数据有发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_WRITEPACKRSDATA),
               "<动作请求层：请求Action中向包中写记录集数据有发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_WRITEPACKDATA),
               "<动作请求层：请求Action中向包中写数据部分节点有发生异常>");
    htErrs.put(new Integer(ERR_ACTION_PACKET_SAVETOXML),
               "<动作请求层：保存请求包发生异常>");
    htErrs.put(new Integer(ERR_ACTION_UNKNOW),
               "<动作请求层：请求Action中其它未捕捉的异常>");

    ///////////////////////////////////////////////////////////////////
    //5：业务层代码定义
    ///////////////////////////////////////////////////////////////////
    htErrs.put(new Integer(ERR_SERVICE_DAO_NOTEXIST),
               "<业务层：hz2004.application.bean.xml中请求的DAO配置不存在>");
    htErrs.put(new Integer(ERR_SERVICE_UNAUTHORIZATION),
               "<业务层：操作权限限制>");
    htErrs.put(new Integer(ERR_SERVICE_BUSSINESSLOGIC),
               "<业务层：业务逻辑错误>");
    htErrs.put(new Integer(ERR_SERVICE_BUSSINESSLIMIT),
               "<业务层：业务权限限制>");
    htErrs.put(new Integer(ERR_SERVICE_DATANOTFULL),
               "<业务层：数据不完整>");
    htErrs.put(new Integer(ERR_SERVICE_DATASCOPE),
               "<业务层：数据范围权限限制,用户没有业务数据的操作权限>");
    htErrs.put(new Integer(ERR_SERVICE_DATAINVALATE), "<业务层：数据格式或长度非法无效>");
    htErrs.put(new Integer(ERR_SERVICE_TRANSACTION),
               "<业务层：事务处理异常>");
    htErrs.put(new Integer(ERR_SERVICE_OTHER),
               "<业务层：其它未捕捉的异常>");

    //bini_min@hotmail.com _begin
    htErrs.put(new Integer(ERR_SERVICE_BGGZSXYSP),"<业务层：变更更正受限于审批>");
    htErrs.put( new Integer(ERR_SERVICE_HCYHZ),"<业务层：户成员户主异常>");
    htErrs.put(new Integer(ERR_SERVICE_HCYPO),"<业务层：户成员配偶异常>");
    htErrs.put(new Integer(ERR_SERVICE_QRDJSXYSP),"<业务层：迁入登记受限于审批>");
    htErrs.put(new Integer(ERR_SERVICE_HJBLSXYSP),"<业务层：户籍补录受限于审批>");
    htErrs.put(new Integer(ERR_SERVICE_HJSCSXYSP),"<业务层：户籍删除受限于审批>");
    htErrs.put(new Integer(ERR_SERVICE_HBBGSXYSP),"<业务层：户别变更受限于审批>");
    //_end


    /////////////////////////////////////////////////////////////////
    //6：数据库层代码定义
    ////////////////////////////////////////////////////////////////
    htErrs.put(new Integer(ERR_DB_PROPERTYNOTFOUNDEX),
               "<数据访问层：PO持久化对象中没有数据库配置文件(hibernate.cfg.xml)中定义的属性>");
    htErrs.put(new Integer(ERR_DB_MAPPINGEX),
               "<数据访问层：对象没有对应的配置文件\r\n或传入的对象不是PO\r\n或数据库配置文件(hibernate.cfg.xml和*.hbm.xml)格式非法或\r\nPO定义的配置资源文件(*.hbm.xml)不存在>");
    htErrs.put(new Integer(ERR_DB_JDBCEX),
               "<数据访问层：数据库中没有与PO对象相对应的表字段、列\r\n或对应的字段列类型不相同>");
    htErrs.put(new Integer(ERR_DB_QUERYEX),
               "<数据访问层：HQL查询语句有误(无效的字段名或非法的HQL语法)>");
    htErrs.put(new Integer(ERR_DB_OBJECTNOTFOUNDEX),
               "<数据访问层：数据库记录中不存在要加载的持久化PO对象>");
    htErrs.put(new Integer(ERR_DB_WRONGCLASSEX), "<数据访问层：错误类型,类型转换异常>");
    htErrs.put(new Integer(ERR_DB_VALIDATIONFAILURE), "<数据访问层：确认错误>");
    htErrs.put(new Integer(ERR_DB_PROPERTYACCESSEX), "<数据访问层：属性访问错误>");
    htErrs.put(new Integer(ERR_DB_INSTANTIATIONEX), "<数据访问层：类实例化错误>");
    htErrs.put(new Integer(ERR_DB_STALEOBJECTSTATEEX), "<数据访问层：对象状态失效>");
    htErrs.put(new Integer(ERR_DB_TRANSIENTOBJECTEX), "<数据访问层：短暂临时对象异常>");
    htErrs.put(new Integer(ERR_DB_PERSISTENTOBJECTEX), "<数据访问层：持久化对象异常>");
    htErrs.put(new Integer(ERR_DB_CALLBACKEX), "<数据访问层：回调处理异常>");
    htErrs.put(new Integer(ERR_DB_HIBERNATEEX),
               "<数据访问层：Hibernate数据库(O/R)持久化异常>");

    htErrs.put(new Integer(ERR_DB_OBJECTDELETEDEX),
               "<数据访问层：操作的持久化对象PO在对应的数据库中已被删除>");
    htErrs.put(new Integer(ERR_DB_OBJECTEDITEDEX),
               "<数据访问层：操作的持久化对象PO在对应的数据库中已被修改>");

    htErrs.put(new Integer(ERR_DB_TRANSACTIONEX), "<数据访问层：数据库事务处理异常>");

    htErrs.put(new Integer(ERR_DB_GETID),
               "<数据访问层：取对象的主键唯一值异常>");
    htErrs.put(new Integer(ERR_DB_HZ2004IDCFGXML),
               "<数据访问层：加载主键配置文件hz2004.id.xml异常>");

    htErrs.put(new Integer(ERR_DB_REPORT_JDBCEX),
               "<数据访问层:JDBC调用生成报表异常>");

    htErrs.put(new Integer(ERR_DB_OTHER), "<数据访问层：其他未捕捉的数据库异常>");
  }

  /**
   *
   * @return
   */
  public static synchronized WSErrCode getInstance() {
    if (_wserrcode == null) {
      _wserrcode = new WSErrCode();
      initXmlResource();
    }
    return _wserrcode;
  }

  /**
   * 初始化XML文件资源
   */
  private static void initXmlResource() {
    _ErrCfgXml = WSConfig.getInsance().getXmlWsstrusException();
  }

  /**
   * 从配置文件中hz2004.exception.xml加载所有的错误代码配置定义
   */
  public void loadAllErrCode() throws ControlException {
    try {
      //加载XML配置文件,经测试DOM4J不支持GBK编码，气愤；不行去下补丁
      InputStream is = WSErrCode.class.getClassLoader().getResourceAsStream(
          _ErrCfgXml);
      SAXReader reader = new SAXReader();
      Document doc = reader.read(is);
      //处理XML-Document文档
      if (doc != null) {
        Element root = doc.getRootElement();
        if (root != null) {
          //循环处理所有的错误配置
          for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
            Element eErr = (Element) iter.next();
            //处理每个Err错误配置
            if (eErr != null) {
              Element eErrCode = eErr.element(ERR_TAG_CODE);
              Element eErrDesc = eErr.element(ERR_TAG_DESC);
              if (eErrCode != null && eErrDesc != null) {
                //如果描述不为空，则进行赋值处理
                if (!eErrDesc.getTextTrim().equals("")) {
                  htErrs.put(Integer.valueOf(eErrCode.getTextTrim()),
                             eErrDesc.getTextTrim());
                }
              }
            }
          }
        }
      }
    }
    catch (Exception ex) {
      throw new ControlException(WSErrCode.ERR_CTRL_LOAD_CFGEXCEPTION, ex);
    }
  }

  /**
   * 返回错误代码对应的错误描述
   * @param iErrCode
   * @return
   */
  public static String getErrMsg(int iErrCode) {
    Object obj = htErrs.get(new Integer(iErrCode));
    return obj == null ? "" : String.valueOf(obj);
  }

  /**
   * 组合、得到错误返回的代码
   * @param strActionCode
   * @param iExCode
   * @return
   */
  public static String getRespErrCode(String strActionCode, int iExCode) {
    StringBuffer strBuf = new StringBuffer(10);
    strBuf.append(PacketTag.PACKET_ERRCODE_PREFIX).append(strActionCode.
        substring(1)).append(
        String.valueOf(iExCode));
    return strBuf.toString();
  }

  /**
   * 组合、得到成功返回的代码
   * @param strActionCode
   * @return
   */
  public static String getRespSuccessCode(String strActionCode) {
    StringBuffer strBuf = new StringBuffer(10);
    strBuf.append(PacketTag.PACKET_SUCCESSCODE_PREFIX).append(strActionCode.
        substring(1)).append(PacketTag.PACKET_SUCESSCODE);
    return strBuf.toString();
  }

  public static void main(String args[]) {
    System.out.println(WSErrCode.getRespErrCode("F2103", 302));
    System.out.println(WSErrCode.getRespSuccessCode("F2003"));
  }

}
