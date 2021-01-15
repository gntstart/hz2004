package com.hzjc.wsstruts.common;

import java.io.Serializable;
import java.util.*;
import java.io.*;
import org.dom4j.io.*;
import org.dom4j.*;
import org.apache.commons.logging.*;
import java.sql.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.vo.VoXT_XTKZCSB;
import com.hzjc.hz2004.appbase.*;
import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.constant.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.common.config.*;

/**
 * 参考Hibernate中的id文件夹，其中几种生成策略
 * （说明：是否考虑用ThreadLocal得到其中的ID）
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */

public class IDGenerator {
  //日志处理
  private static Log _log = LogFactory.getLog(IDGenerator.class);
  //配置文件的名称
  private static String _IDCfgXml = "conf/hz2004.id.xml";
  ////////////////////////////////////////////////////
  private static final String TAG_ID = "idgen";
  private static final String TAG_IDS = "idgens";
  private static final String TAG_NAME = "name";
  private static final String TAG_TYPE = "type";
  ///////////////////////////////////////////////

  /*
    GENERATORS.put("uuid.hex", UUIDHexGenerator.class);
    GENERATORS.put("uuid.string", UUIDStringGenerator.class);
    GENERATORS.put("hilo", TableHiLoGenerator.class);
    GENERATORS.put("assigned", Assigned.class);
    GENERATORS.put("identity", IdentityGenerator.class);
    GENERATORS.put("sequence", SequenceGenerator.class);
    GENERATORS.put("seqhilo", SequenceHiLoGenerator.class);
    GENERATORS.put("vm", IncrementGenerator.class); //vm is deprecated
    GENERATORS.put("increment", IncrementGenerator.class);
    GENERATORS.put("foreign", ForeignGenerator.class);
   */
  //////////////////////////////////////////////////////////////////////
  // 生成主键的策略，参考Hibernate的主键生成策略
  //////////////////////////////////////////////////////////////////////
  private static final String ID_UUID_HEX = "uuid.hex";
  private static final String ID_UUID_STRING = "uuid.string";
  private static final String ID_HILO = "hilo";
  private static final String ID_ASSIGNED = "assigned";
  private static final String ID_IDENTITY = "identity";
  private static final String ID_SEQUENCE = "sequence";
  private static final String ID_SEQHILO = "seqhilo";
  private static final String ID_VM = "vm";
  private static final String ID_INCREMENT = "increment";
  private static final String ID_FOREIGN = "foreign";
  /////////////////////////////////////////////////////////////////////////
  private static Map _mapid = null;
  private static IDGenerator _idgen = null;

  /**
   *
   */
  private IDGenerator() {
  }

  /**
   *
   */
  private static void initXmlResource() {
    _IDCfgXml = WSConfig.getInsance().getXmlIdGenerator();
  }

  /**
   *
   * @return
   * @throws DAOException
   */
  public static synchronized IDGenerator getInstance() throws DAOException {
    try {
      if (_idgen == null) {
        _idgen = new IDGenerator();
        initXmlResource();
        initCfg();
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    return _idgen;
  }

  /**
   *
   */
  private static void initCfg() throws DAOException {
    try {
      //如果_mapid为空则加载之
      //if (_mapid == null) {
      //加载XML的时候如果标签的名字为id或ids的话，解析会报错，经测试
      InputStream is = IDGenerator.class.getClassLoader().getResourceAsStream(
          _IDCfgXml);
      _log.info(_IDCfgXml);
      SAXReader reader = new SAXReader();
      Document doc = reader.read(is);
      if (doc != null) {
        //Xpath得到所有的//ids/id的元素
        List lstId = doc.selectNodes("//".concat(TAG_IDS).concat("/").concat(
            TAG_ID));
        if (lstId != null && !lstId.isEmpty()) {
          _mapid = new HashMap();
          for (Iterator iter = lstId.iterator(); iter.hasNext(); ) {
            Element eId = (Element) iter.next();
            String strName = eId.attribute(TAG_NAME).getText().trim();
            String strType = eId.attribute(TAG_TYPE).getText().trim().
                toLowerCase();
            String strValue = eId.getTextTrim();
            ID id = new ID(strName, strType, strValue);
            _mapid.put(strName, id);
          }
        }
      }
      //}
    }
    catch (DocumentException ex) {
      throw new DAOException(WSErrCode.ERR_DB_HZ2004IDCFGXML, ex);
    }
  }

  /**
   * 对于多线程，得到主键值应同步方法
   * @param name:某表的ID的名字Key
   * @return
   */
  public synchronized Serializable getId(String name) throws DAOException {
	  return null;
    /*
    try {
      if (_mapid == null) {
        //initCfg();
    	  _mapid = new HashMap<String,String>();
      }
      
      //否则,得到ID
      if (_mapid != null && !_mapid.isEmpty()) {
        ID id = (ID) _mapid.get(name);
        if (id != null) {
          String strType = id.getType();
          if (strType.equalsIgnoreCase(ID_ASSIGNED)) {
            ser = getIdByAssigned(id);
          }
          else if (strType.equalsIgnoreCase(ID_FOREIGN)) {
            ser = getIdByForeign(id);
          }
          else if (strType.equalsIgnoreCase(ID_HILO)) {
            ser = getIdByHilo(id);
          }
          else if (strType.equalsIgnoreCase(ID_IDENTITY)) {
            ser = getIdByIdentity(id);
          }
          else if (strType.equalsIgnoreCase(ID_INCREMENT)) {
            ser = getIdByIncrement(id);
          }
          else if (strType.equalsIgnoreCase(ID_SEQHILO)) {
            ser = getIdBySeqHilo(id);
          }
          else if (strType.equalsIgnoreCase(ID_SEQUENCE)) {
            ser = getIdBySequence(id);
          }
          else if (strType.equalsIgnoreCase(ID_UUID_HEX)) {
            ser = getIdByUuidHex(id);
          }
          else if (strType.equalsIgnoreCase(ID_UUID_STRING)) {
            ser = getIdByUuidString(id);
          }
          else if (strType.equalsIgnoreCase(ID_VM)) {
            ser = getIdByVm(id);
          }
        }
      }
    }
    catch (DAOException ex) {
      throw ex;
    }
    _log.info("取得主键ID=".concat(name).concat("=").concat(String.valueOf(ser)));
    return ser;
    */
  }

  /**
   * 生成规则：ParseLong[DBID(前3位) + DBID(后6位)] * 10000000000L + SequenceID
   * 规则修改：ParseLong[DBID(后6位) + DBID(前3位)] * 10000000000L + SequenceID
   * history: since 2004-06-04
   *
   * select SID_HJHIS_LSBGSPZB.nextval from dual;
   * @param name
   * @return
   */
  private static Serializable getIdBySequence(ID id) throws DAOException {
	  return SpringContextHolder.getCommonService().getId(id);
  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByUuidHex(ID id) {
    Serializable ser = null;
    return ser;
  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByUuidString(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByHilo(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByAssigned(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByIdentity(ID id) {
    Serializable ser = null;
    return ser;
  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdBySeqHilo(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByVm(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByIncrement(ID id) {
    Serializable ser = null;
    return ser;

  }

  /**
   *
   * @param name
   * @return
   */
  private static Serializable getIdByForeign(ID id) {
    Serializable ser = null;
    return ser;
  }
}