package com.hzjc.wsstruts.common.config;

import com.hzjc.wsstruts.exception.*;
import org.apache.commons.logging.*;
import org.dom4j.*;
import org.dom4j.io.*;
import java.util.*;
import java.io.*;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.common.xml.*;

/**
 * 框架配置加载基类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class WSConfig {
  //日志处理
  protected static Log _log = LogFactory.getLog(WSConfig.class);
  private static final String _configxml = "conf/application.config.xml";
  private static Map _mapConfig = new HashMap();
  private static WSConfig _wsconfig = null;
  /////////////////////////////////////////////////////////////////
  //系统配置标记定义
  //一级节点
  private static final String TAG_APP_CONFIG = "app-config";
  public static final String TAG_GLOBAL_CONFIG = "global-config";
  public static final String TAG_DATABASE = "database";
  public static final String TAG_XML_ENCODING = "xml-encoding";
  public static final String TAG_XML_RESOURCE = "xml-resource";
  public static final String TAG_LOG = "log";
  public static final String TAG_HTTP_SESSION = "http-session";
  ///////////////////////////////////////////////////////////////////
  //二级节点
  //<database>
  public static final String TAG_DATABASE_TYPE = "type";
  //<xml-encoding>
  public static final String TAG_XML_ENCODING_DATAEXCHANGE = "dataexchange";
  public static final String TAG_XML_ENCODING_REPORT = "report";
  public static final String TAG_XML_ENCODING_XMLPACKET = "xmlpacket";
  public static final String TAG_XML_ENCODING_CLIENTDATASET = "clientdataset";
  //<xml-resource>
  public static final String TAG_XML_RESOURCE_APPLICATION_BEAN =
      "application_bean";
  public static final String TAG_XML_RESOURCE_HIBERNATE_CFG = "hibernate_cfg";
  public static final String TAG_XML_RESOURCE_ID_GENERATOR = "id_generator";
  public static final String TAG_XML_RESOURCE_WSSTRUTS_EXCEPTION =
      "wsstruts_exception";
  //<log>
  public static final String TAG_LOG_SAVED = "saved";
  public static final String TAG_LOG_SAVETO = "saveto";
  public static final String TAG_LOG_RUNTIME = "runtime"; //毫秒为单位
  //<http-session>
  public static final String TAG_HTTP_SESSION_SERIALIZABLE = "serializable";
  public static final String TAG_HTTP_SESSION_SAVEFILEPATHNAME =
      "savefilepathname";
  //////////////////////////////////////////////////////////////////////////////
  //XML配置的常量值
  public static final String C_BOOL_TRUE = "true";
  public static final String C_BOOL_FALSE = "false";
  public static final String C_SAVETO_DB = "db";
  public static final String C_SAVETO_FILE = "file";

  /**
   *
   */
  protected WSConfig() {
  }

  /**
   *
   * @return
   */
  public static synchronized WSConfig getInsance() {
    if (_wsconfig == null) {
      _wsconfig = configure();
    }
    return _wsconfig;
  }

  /**
   *
   * @return
   * @throws ControlException
   */
  private static WSConfig configure() throws ControlException {
    return configure(_configxml);
  }

  /**
   *
   * @param strXmlResource - 资源文件名称
   * @return
   * @throws ControlException
   */
  private static WSConfig configure(String strXmlResource) throws
      ControlException {
    try {
      InputStream is = XmlUtils.getResourceAsStream(strXmlResource);
      return configure(is);
    }
    catch (Exception ex) {
      throw new ControlException(WSErrCode.ERR_CTRL_LOAD_CFGEXCEPTION, ex);
    }
  }

  /**
   *
   * @param xmlIs - 读入流
   * @return
   * @throws ControlException
   */
  private static WSConfig configure(InputStream xmlIs) throws ControlException {
    try {
      SAXReader reader = new SAXReader();
      Document doc = reader.read(xmlIs);
      return configure(doc);
    }
    catch (ControlException ex) {
      throw ex;
    }
    catch (DocumentException ex) {
      throw new ControlException(WSErrCode.ERR_CTRL_LOAD_CFGEXCEPTION, ex);
    }
  }

  /**
   * 加载系统配置
   * @param doc  - XML文件DOCUMENT
   * @return
   * @throws ControlException
   */
  private static WSConfig configure(Document doc) throws ControlException {
    WSConfig wscfg = new WSConfig();
    try {
      ///////////////////////////////////////////////////////////
      //初始化变量
      ////////////////////////////////////////////////////////////
      _mapConfig.clear();
      ///////////////////////////////////////////////////////////////////////
      //加载所有系统配置信息
      //////////////////////////////////////////////////////////////////////
      if (doc != null) {
        Element eAppConfig = doc.getRootElement();
        //Xpath的节点查询，"//"是相对于当前节点为基础进行的。
        //List lstCfg = eAppConfig.selectNodes("//".concat(TAG_APP_CONFIG));
        //for (Iterator iter = lstCfg.iterator(); iter.hasNext(); ) {
        if (eAppConfig != null) {
          for (Iterator iter = eAppConfig.elementIterator(); iter.hasNext(); ) {
            Element eIter = (Element) iter.next();
            _mapConfig.put(eIter.getName(), PropertyConfig.elementToMap(eIter));
          } //for (Iterator iter = eAppConfig.elementIterator(); iter.hasNext(); )
        } //if (eAppConfig != null) {
      } // if (doc != null) {
      _log.info("系统加载App-Xml-Config配置文件中共有" +
                String.valueOf(_mapConfig.size()) +
                "类配置。");
      return wscfg;
    }
    catch (Exception ex) {
      throw new ControlException(WSErrCode.ERR_CTRL_LOAD_CFGEXCEPTION, ex);
    }
  }

  /**
   * 得到系统某个配置Map值
   * @param strCfgName  - XML配置一级节点名称
   * @return
   */
  public Map getConfigMap(String strCfgName) {
    Map mapCfgValue = null;
    if (strCfgName != null && _mapConfig != null) {
      mapCfgValue = _mapConfig.get(strCfgName) == null ? null :
          (Map) _mapConfig.get(strCfgName);
    }
    return mapCfgValue;
  }

  /**
   *
   * @param strCfgName  - XML配置一级节点
   * @param strPropName - XML配置二级节点属性名称
   * @return
   */
  public String getConfigMapPropValue(String strCfgName,
                                      String strPropName) {
    String strPropValue = null;
    Map mapCfgValue = getConfigMap(strCfgName);
    if (mapCfgValue != null && !mapCfgValue.isEmpty()) {
      Object objPropValue = mapCfgValue.get(strPropName);
      strPropValue = objPropValue == null ? null : String.valueOf(objPropValue);
    }
    return strPropValue;
  }

  /**
   * 日志是否保存
   * @return
   */
  public boolean getLogIsSaved() {
    boolean bSave = false;
    String strSaved = getConfigMapPropValue(TAG_LOG, TAG_LOG_SAVED);
    if (strSaved != null) {
      bSave = strSaved.equalsIgnoreCase(C_BOOL_TRUE);
    }
    return bSave;
  }

  /**
   * 日志保存到的文件方式
   * @return
   */
  public String getLogSaveTo() {
    return getConfigMapPropValue(TAG_LOG, TAG_LOG_SAVETO);
  }

  /**
   * 得到系统运行花费的时间
   * @return
   */
  public int getLogRunTime() {
    int iRunTime = 0;
    String strRunTime = getConfigMapPropValue(TAG_LOG, TAG_LOG_RUNTIME);
    if (strRunTime != null && StringUtils.isNumeric(strRunTime)) {
      iRunTime = Integer.parseInt(strRunTime);
    }
    return iRunTime;
  }

  /**
   * 取得Spring框架定义的BEAN路径
   * @return
   */
  public String getXmlSpringAppBean() {
    return getConfigMapPropValue(TAG_XML_RESOURCE,
                                 TAG_XML_RESOURCE_APPLICATION_BEAN);
  }

  /**
   * 取得HIBERNATE MAP资源的路径
   * @return
   */
  public String getXmlHibernateCfg() {
    return getConfigMapPropValue(TAG_XML_RESOURCE,
                                 TAG_XML_RESOURCE_HIBERNATE_CFG);
  }

  /**
   * 取得ID生成器定义的资源路径
   * @return
   */
  public String getXmlIdGenerator() {
    return getConfigMapPropValue(TAG_XML_RESOURCE,
                                 TAG_XML_RESOURCE_ID_GENERATOR);
  }

  /**
   * 取得定义异常的资源路径
   * @return
   */
  public String getXmlWsstrusException() {
    return getConfigMapPropValue(TAG_XML_RESOURCE,
                                 TAG_XML_RESOURCE_WSSTRUTS_EXCEPTION);
  }

  /**
   *
   * @return
   */
  public String getEncodeDataExchange() {
    return getConfigMapPropValue(TAG_XML_ENCODING,
                                 TAG_XML_ENCODING_DATAEXCHANGE);
  }

  /**
   * 取得报表生成的XML编码
   * @return
   */
  public String getEncodeReport() {
    return getConfigMapPropValue(TAG_XML_ENCODING, TAG_XML_ENCODING_REPORT);
  }

  /**
   * 取得WebService的XML编码
   * @return
   */
  public String getEncodeXmlPacket() {
    return getConfigMapPropValue(TAG_XML_ENCODING, TAG_XML_ENCODING_XMLPACKET);
  }

  /**
   * 取得客户端Delphi ClientDataSet中设置的XML格式
   * @return
   */
  public String getEncodeClientDataSet() {
    return getConfigMapPropValue(TAG_XML_ENCODING,
                                 TAG_XML_ENCODING_CLIENTDATASET);
  }

  /**
   * 判断是否将SESSION序列化到路径
   * @return
   */
  public boolean getSessionSerializable() {
    boolean bSerial = false;
    String strSerial = getConfigMapPropValue(TAG_HTTP_SESSION,
                                             TAG_HTTP_SESSION_SERIALIZABLE);
    if (strSerial != null) {
      bSerial = strSerial.equalsIgnoreCase(C_BOOL_TRUE);
    }
    return bSerial;
  }

  /**
   * 得到SESSION序列化到的文件物理路径及名称
   * @return
   */
  public String getSessionSerialFilePathName() {
    String strVirtualFielPathName = getConfigMapPropValue(TAG_HTTP_SESSION,
        TAG_HTTP_SESSION_SAVEFILEPATHNAME);
    String strPhysicPathName = strVirtualFielPathName == null ? "" :
        strVirtualFielPathName;
    strPhysicPathName = XmlUtils.getResourcePhysicPath(strPhysicPathName);
    return strPhysicPathName;
  }

  /**
   * 每个属性设置节点转换解析类
   * <p>Title: Hz2004</p>
   * <p>Description: 常住人口管理系统Hz2004版</p>
   * <p>Copyright: Copyright (c) 2004</p>
   * <p>Company: 浙江金铖华元新技术有限公司</p>
   * @author kgb_hz@126.com,kgb@primetech.com.cn
   * @version 1.0
   */
  private static class PropertyConfig {
    private static final String TAG_PROPERTY = "property";
    private static final String TAG_PROPERTY_NAME = "name";

    private PropertyConfig() {
    }

    /**
     *
     * @param eNode
     * @return
     */
    public static Map elementToMap(Element eNode) {
      Map eMap = null;
      if (eNode != null) {
        Iterator iterProp = eNode.elementIterator(TAG_PROPERTY);
        if (iterProp != null) {
          eMap = new HashMap();
          for (; iterProp.hasNext(); ) {
            Element eProp = (Element) iterProp.next();
            String strName = eProp.attributeValue(TAG_PROPERTY_NAME);
            String strValue = String.valueOf(eProp.getData());
            eMap.put(strName, strValue);
          }
        }
      }
      return eMap;
    }

  }

}
